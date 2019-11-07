### 项目结构
 
 ```
 could
  ├── api-admin      后台管理系统接口
  ├── api-auth       认证接口
  ├── api-mall       商城接口
  ├── api-notify     通知接口
  ├── common         通用模块
  ├── gateway        网关
 ```
 
### redis 库
```
目前统一使用0库作为存储吧，用KEY作为数据区分，后面直接可以迁移到redis cluster
```
### 异常处理
> 异常统一往Contrller层抛，由全局统一处理异常返回
```java
    @ApiOperation(value = "刷新accessToken", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': null,\n" +
            "  'data': null\n" +
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
>直接返回数据，无需包装ApiResponse，会有全局处理器来封装这个结果，如:
```java
    @ApiOperation(value = "验证码登录-心安&商城会员使用", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': null,\n" +
            "  'data': null\n" +
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
            "  'code': 200,\n" +
            "  'message': null,\n" +
            "  'data': null\n" +
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


