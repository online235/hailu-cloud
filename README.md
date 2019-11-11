## 项目结构

```
 could
  ├── api-admin             后台管理系统接口
  ├── api-auth              认证接口
  ├── api-mall              商城接口
  ├── api-basic-service     基础服务(短信，邮件，文件上传，百度编辑器等)
  ├── api-scheduling        定时任务相关
  ├── common                通用模块
  ├── gateway               网关
```

## redis库使用说明

```
目前统一使用0库作为存储吧，用KEY作为数据区分，后面直接可以迁移到redis cluster
```

## 规范说明

### 异常处理

> 异常统一往Contrller层抛，由全局统一处理异常返回

```java
@ApiOperation(value = "刷新accessToken", notes = "<pre>" +
        "{\n" +
        "    'code': 200,\n" +
        "    'message': null,\n" +
        "    'data': null\n" +
        "}" +
        "</pre>")
@ApiImplicitParams({
        @ApiImplicitParam(name = "refreshToken", required = true, paramType = "path", dataType = "String")
})
@GetMapping("/token/refresh/{refreshToken}")
public String refreshToken(
        @PathVariable("refreshToken")
        @NotBlank(message = "refreshToken不能为空") String refreshToken) throws RefreshTokenExpiredException {

    return authService.refreshAccessToken(refreshToken);
}
```

### service层

> service返回真实数据，不要包装ApiResponse, 如果涉及事务加@Transactional,暂时不考虑分布式事务

```java
@Override
@Transactional(rollbackFor = Exception.class)
public MemberModel login(String phone, String code) throws BusinessException {
    // ...
    return memberModel;
}
```

### controller层

> 直接返回数据，无需包装ApiResponse，会有全局处理器来封装这个结果，如:

```java
@ApiOperation(value = "验证码登录-心安&商城会员使用", notes = "<pre>" +
        "{\n" +
        "    'code': 200,\n" +
        "    'message': null,\n" +
        "    'data': null\n" +
        "}" +
        "</pre>")
@ApiImplicitParams({
        @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
})
@PostMapping("/login")
public MemberModel login(
        @NotBlank(message = "手机号码不能为空")
        @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确") String phone,
        @NotBlank(message = "验证码不能为空")
        @Pattern(regexp = "^\\d{6}$", message = "验证码格式不正确") String code) throws BusinessException {

    return authService.login(phone, code);
}
```

> 或者是没有返回值的

```java
@ApiOperation(value = "第三方免费短信接口-超过免费次数会限制发送", notes = "<pre>" +
        "{\n" +
        "    'code': 200,\n" +
        "    'message': null,\n" +
        "    'data': null\n" +
        "}" +
        "</pre>")
@ApiImplicitParams({
        @ApiImplicitParam(name = "phone", required = true, value = "手机号码", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "message", required = true, value = "短信内容", paramType = "query", dataType = "String"),
})
@GetMapping("/send/free")
public void send(
        @NotBlank(message = "手机号码不能为空")
        @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确") String phone,
        @NotBlank(message = "短信内容不能为空") String message) throws BusinessException {

    freeSmsService.send(phone, message);
}
```

## ehcache缓存

> 参数配置说明

| 参数                              | 描述                                                                                                                                                                                                                                                                                                                                                            |
| ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| name                            | 缓存的名称                                                                                                                                                                                                                                                                                                                                                         |
| maxElementsInMemory             | 内存中允许存储的最大的元素个数，0代表无限个                                                                                                                                                                                                                                                                                                                                        |
| clearOnFlush                    | 内存数量最大时是否清除                                                                                                                                                                                                                                                                                                                                                   |
| eternal                         | 设置缓存中对象是否为永久的，如果是，超时设置将被忽略，对象从不过期。根据存储数据的不同，例如一些静态不变的数据如省市区等可以设置为永不过时                                                                                                                                                                                                                                                                                         |
| timeToIdleSeconds               | 设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大                                                                                                                                                                                                                                                                                       |
| timeToLiveSeconds               | 缓存数据的生存时间（TTL），也就是一个元素从构建到消亡的最大时间间隔值，这只能在元素不是永久驻留时有效，如果该值是0就意味着元素可以停顿无穷长的时间                                                                                                                                                                                                                                                                                   |
| overflowToDisk                  | 内存不足时，是否启用磁盘缓存                                                                                                                                                                                                                                                                                                                                                |
| maxEntriesLocalDisk             | 当内存中对象数量达到maxElementsInMemory时，Ehcache将会对象写到磁盘中                                                                                                                                                                                                                                                                                                               |
| maxElementsOnDisk               | 硬盘最大缓存个数                                                                                                                                                                                                                                                                                                                                                      |
| diskSpoolBufferSizeMB           | 这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区                                                                                                                                                                                                                                                                                                       |
| diskPersistent                  | 是否在VM重启时存储硬盘的缓存数据。默认值是false                                                                                                                                                                                                                                                                                                                                   |
| diskExpiryThreadIntervalSeconds | 磁盘失效线程运行时间间隔，默认是120秒                                                                                                                                                                                                                                                                                                                                          |
| maxEntriesLocalHeap             | 是用来限制当前缓存在堆内存上所能保存的最大元素数量的。Ehcache规定如果在CacheManager上没有指定maxBytesLocalHeap时必须在各个Cache上指定maxBytesLocalHeap或者maxEntriesLocalHeap，但maxEntriesLocalHeap和maxBytesLocalHeap不能同时出现。也就是说我们不能在一个Cache上同时指定maxBytesLocalHeap和maxEntriesLocalHeap，当然我们也不能在Cache上指定maxEntriesLocalHeap的同时在CacheManager上指定maxBytesLocalHeap。但同时在CacheManager和Cache上指定maxBytesLocalHeap则是允许的 |

## 分布式ID

> 表

```sql
CREATE TABLE `global_worker_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
  `hostname` varchar(64) NOT NULL COMMENT '主机',
  `port` varchar(64) NOT NULL COMMENT '端口',
  `type` int(11) NOT NULL COMMENT '节点类型: 1容器, 2物理机',
  `launch_date` date NOT NULL COMMENT '启动日期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='全局UID生成机器列表';
```

> 分布式ID构成

```
该配置可支持28个节点以整体并发量14400 UID/s的速度持续运行68年
```

| 标识位 | 当前时间 | 机器ID | 自增序列号 |
|:---:|:----:|:----:|:-----:|
| 1位  | 31位  | 23位  | 9位    |


