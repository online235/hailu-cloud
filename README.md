> 项目结构
 
 ```
 could
  ├── api-admin      后台管理系统接口
  ├── api-auth       认证接口
  ├── api-mall       商城接口
  ├── api-notify     通知接口
  ├── common         通用模块
  ├── gateway        网关
 ```
 
> redis 库
```
目前统一使用0库作为存储吧，用KEY作为数据区分，后面直接可以迁移到redis cluster
```
> 异常处理
```
异常统一往Contrller层抛，由全局统一处理异常返回，
如果有事务控制在service接口添加@Transactional(rollbackFor = xxxException.class)回滚事务
```
> service层
```
service返回真实数据，不要包装ApiResponse，ApiResponse来统一包装
```

