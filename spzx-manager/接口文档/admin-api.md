# 尚品甑选API接口文档


**简介**:尚品甑选API接口文档


**HOST**:http://139.198.163.91:8401


**联系人**:qy

**Version**:1.0

**接口路径**:/v3/api-docs/admin-api


[TOC]






# 用户管理


## 修改


**接口地址**:`/admin/system/sysUser/updateById`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "username": "",
  "password": "",
  "name": "",
  "phone": "",
  "avatar": "",
  "description": "",
  "status": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysUser|SysUser|body|true|SysUser|SysUser|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;password|密码||false|string||
|&emsp;&emsp;name|姓名||false|string||
|&emsp;&emsp;phone|手机||false|string||
|&emsp;&emsp;avatar|头像||false|string||
|&emsp;&emsp;description|描述||false|string||
|&emsp;&emsp;status|状态（1：正常 0：停用）||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 新增


**接口地址**:`/admin/system/sysUser/save`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "username": "",
  "password": "",
  "name": "",
  "phone": "",
  "avatar": "",
  "description": "",
  "status": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysUser|SysUser|body|true|SysUser|SysUser|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;password|密码||false|string||
|&emsp;&emsp;name|姓名||false|string||
|&emsp;&emsp;phone|手机||false|string||
|&emsp;&emsp;avatar|头像||false|string||
|&emsp;&emsp;description|描述||false|string||
|&emsp;&emsp;status|状态（1：正常 0：停用）||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分页列表


**接口地址**:`/admin/system/sysUser/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||
|sysUserQuery|查询对象|query|true|SysUserQuery|SysUserQuery|
|&emsp;&emsp;keyword|||false|string||
|&emsp;&emsp;createTimeBegin|||false|string||
|&emsp;&emsp;createTimeEnd|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoSysUser|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoSysUser|PageInfoSysUser|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|SysUser|array|SysUser|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;&emsp;&emsp;password|密码|string||
|&emsp;&emsp;&emsp;&emsp;name|姓名|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机|string||
|&emsp;&emsp;&emsp;&emsp;avatar|头像|string||
|&emsp;&emsp;&emsp;&emsp;description|描述|string||
|&emsp;&emsp;&emsp;&emsp;status|状态（1：正常 0：停用）|integer||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"username": "",
				"password": "",
				"name": "",
				"phone": "",
				"avatar": "",
				"description": "",
				"status": 0
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


## 更新状态


**接口地址**:`/admin/system/sysUser/updateStatus/{id}/{status}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|status||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取详情


**接口地址**:`/admin/system/sysUser/getById/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除


**接口地址**:`/admin/system/sysUser/removeById/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


# 角色管理


## 修改


**接口地址**:`/admin/system/sysRole/updateById`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "roleName": "",
  "roleCode": "",
  "description": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysRole|SysRole|body|true|SysRole|SysRole|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;roleName|角色名称||false|string||
|&emsp;&emsp;roleCode|角色编码||false|string||
|&emsp;&emsp;description|描述||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 新增


**接口地址**:`/admin/system/sysRole/save`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "roleName": "",
  "roleCode": "",
  "description": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysRole|SysRole|body|true|SysRole|SysRole|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;roleName|角色名称||false|string||
|&emsp;&emsp;roleCode|角色编码||false|string||
|&emsp;&emsp;description|描述||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 根据用户分配角色


**接口地址**:`/admin/system/sysRole/doAssign`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "userId": 0,
  "roleIdList": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|assginRoleVo|分配菜单|body|true|AssginRoleVo|AssginRoleVo|
|&emsp;&emsp;userId|用户id||false|integer(int64)||
|&emsp;&emsp;roleIdList|角色id列表||false|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分页列表


**接口地址**:`/admin/system/sysRole/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||
|sysRoleQuery|查询对象|query|true|SysRoleQuery|SysRoleQuery|
|&emsp;&emsp;roleName|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoSysRole|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoSysRole|PageInfoSysRole|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|SysRole|array|SysRole|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;roleName|角色名称|string||
|&emsp;&emsp;&emsp;&emsp;roleCode|角色编码|string||
|&emsp;&emsp;&emsp;&emsp;description|描述|string||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"roleName": "",
				"roleCode": "",
				"description": ""
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


## 根据用户获取角色数据


**接口地址**:`/admin/system/sysRole/toAssign/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取详情


**接口地址**:`/admin/system/sysRole/getById/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取全部角色


**接口地址**:`/admin/system/sysRole/findAll`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除


**接口地址**:`/admin/system/sysRole/removeById/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


# 菜单管理


## 修改菜单


**接口地址**:`/admin/system/sysMenu/updateById`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "parentId": 0,
  "title": "",
  "component": "",
  "sortValue": 0,
  "status": 0,
  "children": [
    {
      "id": 0,
      "createTime": "",
      "parentId": 0,
      "title": "",
      "component": "",
      "sortValue": 0,
      "status": 0,
      "children": [],
      "select": true
    }
  ],
  "select": true
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysMenu|SysMenu|body|true|SysMenu|SysMenu|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;parentId|所属上级||false|integer(int64)||
|&emsp;&emsp;title|菜单标题||false|string||
|&emsp;&emsp;component|组件名称||false|string||
|&emsp;&emsp;sortValue|排序||false|integer(int32)||
|&emsp;&emsp;status|状态(0:禁止,1:正常)||false|integer(int32)||
|&emsp;&emsp;children|||false|array|SysMenu|
|&emsp;&emsp;select|||false|boolean||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 新增菜单


**接口地址**:`/admin/system/sysMenu/save`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "parentId": 0,
  "title": "",
  "component": "",
  "sortValue": 0,
  "status": 0,
  "children": [
    {
      "id": 0,
      "createTime": "",
      "parentId": 0,
      "title": "",
      "component": "",
      "sortValue": 0,
      "status": 0,
      "children": [],
      "select": true
    }
  ],
  "select": true
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysMenu|SysMenu|body|true|SysMenu|SysMenu|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;parentId|所属上级||false|integer(int64)||
|&emsp;&emsp;title|菜单标题||false|string||
|&emsp;&emsp;component|组件名称||false|string||
|&emsp;&emsp;sortValue|排序||false|integer(int32)||
|&emsp;&emsp;status|状态(0:禁止,1:正常)||false|integer(int32)||
|&emsp;&emsp;children|||false|array|SysMenu|
|&emsp;&emsp;select|||false|boolean||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 给角色分配权限


**接口地址**:`/admin/system/sysMenu/doAssign`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "roleId": 0,
  "menuIdList": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|assginMenuVo|分配菜单|body|true|AssginMenuVo|AssginMenuVo|
|&emsp;&emsp;roleId|角色id||false|integer(int64)||
|&emsp;&emsp;menuIdList|菜单id列表||false|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 根据角色获取菜单


**接口地址**:`/admin/system/sysMenu/toAssign/{roleId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取菜单


**接口地址**:`/admin/system/sysMenu/findNodes`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除菜单


**接口地址**:`/admin/system/sysMenu/removeById/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


# 商品单位管理


## 修改


**接口地址**:`/admin/product/productUnit/updateById`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "name": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|productUnit|ProductUnit|body|true|ProductUnit|ProductUnit|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;name|名称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 新增


**接口地址**:`/admin/product/productUnit/save`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "name": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|productUnit|ProductUnit|body|true|ProductUnit|ProductUnit|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;name|名称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分页列表


**接口地址**:`/admin/product/productUnit/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoProductUnit|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoProductUnit|PageInfoProductUnit|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|ProductUnit|array|ProductUnit|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;name|名称|string||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"name": ""
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


## 获取详情


**接口地址**:`/admin/product/productUnit/getById/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultProductUnit|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||ProductUnit|ProductUnit|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;name|名称|string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"createTime": "",
		"name": ""
	}
}
```


## 获取全部信息


**接口地址**:`/admin/product/productUnit/findAll`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListProductUnit|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|ProductUnit|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;name|名称|string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"createTime": "",
			"name": ""
		}
	]
}
```


## 删除


**接口地址**:`/admin/product/productUnit/removeById/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


# 商品规格


## 修改


**接口地址**:`/admin/product/productSpec/updateById`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "specName": "",
  "specValue": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|productSpec|ProductSpec|body|true|ProductSpec|ProductSpec|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;specName|规格名称||false|string||
|&emsp;&emsp;specValue|规格值||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 新增


**接口地址**:`/admin/product/productSpec/save`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "specName": "",
  "specValue": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|productSpec|ProductSpec|body|true|ProductSpec|ProductSpec|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;specName|规格名称||false|string||
|&emsp;&emsp;specValue|规格值||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分页列表


**接口地址**:`/admin/product/productSpec/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoProductSpec|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoProductSpec|PageInfoProductSpec|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|ProductSpec|array|ProductSpec|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;specName|规格名称|string||
|&emsp;&emsp;&emsp;&emsp;specValue|规格值|string||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"specName": "",
				"specValue": ""
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


## 获取详情


**接口地址**:`/admin/product/productSpec/getById/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取全部品牌


**接口地址**:`/admin/product/productSpec/findAll`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除


**接口地址**:`/admin/product/productSpec/removeById/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


# Product


## 修改


**接口地址**:`/admin/product/product/updateById`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "name": "",
  "brandId": 0,
  "category1Id": 0,
  "category2Id": 0,
  "category3Id": 0,
  "unitName": "",
  "sliderUrls": "",
  "specValue": "",
  "status": 0,
  "auditStatus": 0,
  "auditMessage": "",
  "brandName": "",
  "category1Name": "",
  "category2Name": "",
  "category3Name": "",
  "productSkuList": [
    {
      "id": 0,
      "createTime": "",
      "skuCode": "",
      "skuName": "",
      "productId": 0,
      "thumbImg": "",
      "salePrice": 0,
      "marketPrice": 0,
      "costPrice": 0,
      "stockNum": 0,
      "saleNum": 0,
      "skuSpec": "",
      "weight": "",
      "volume": "",
      "status": 0,
      "skuSpecList": []
    }
  ],
  "detailsImageUrls": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|product|Product|body|true|Product|Product|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;name|商品名称||false|string||
|&emsp;&emsp;brandId|品牌ID||false|integer(int64)||
|&emsp;&emsp;category1Id|一级分类id||false|integer(int64)||
|&emsp;&emsp;category2Id|二级分类id||false|integer(int64)||
|&emsp;&emsp;category3Id|三级分类id||false|integer(int64)||
|&emsp;&emsp;unitName|计量单位||false|string||
|&emsp;&emsp;sliderUrls|轮播图||false|string||
|&emsp;&emsp;specValue|商品规格值json串||false|string||
|&emsp;&emsp;status|线上状态：0-初始值，1-上架，-1-自主下架||false|integer(int32)||
|&emsp;&emsp;auditStatus|审核状态||false|integer(int32)||
|&emsp;&emsp;auditMessage|审核信息||false|string||
|&emsp;&emsp;brandName|品牌||false|string||
|&emsp;&emsp;category1Name|一级分类||false|string||
|&emsp;&emsp;category2Name|二级分类||false|string||
|&emsp;&emsp;category3Name|三级分类||false|string||
|&emsp;&emsp;productSkuList|ProductSku||false|array|ProductSku|
|&emsp;&emsp;&emsp;&emsp;id|||false|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|||false|string||
|&emsp;&emsp;&emsp;&emsp;skuCode|商品编号||false|string||
|&emsp;&emsp;&emsp;&emsp;skuName|skuName||false|string||
|&emsp;&emsp;&emsp;&emsp;productId|商品ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;thumbImg|缩略图路径||false|string||
|&emsp;&emsp;&emsp;&emsp;salePrice|售价||false|number||
|&emsp;&emsp;&emsp;&emsp;marketPrice|市场价||false|number||
|&emsp;&emsp;&emsp;&emsp;costPrice|成本价||false|number||
|&emsp;&emsp;&emsp;&emsp;stockNum|库存数||false|integer||
|&emsp;&emsp;&emsp;&emsp;saleNum|销量||false|integer||
|&emsp;&emsp;&emsp;&emsp;skuSpec|sku规格信息json||false|string||
|&emsp;&emsp;&emsp;&emsp;weight|重量||false|string||
|&emsp;&emsp;&emsp;&emsp;volume|体积||false|string||
|&emsp;&emsp;&emsp;&emsp;status|线上状态：0-初始值，1-上架，-1-自主下架||false|integer||
|&emsp;&emsp;&emsp;&emsp;skuSpecList|sku规格信息||false|array|string|
|&emsp;&emsp;detailsImageUrls|详情图片列表||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 新增


**接口地址**:`/admin/product/product/save`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "name": "",
  "brandId": 0,
  "category1Id": 0,
  "category2Id": 0,
  "category3Id": 0,
  "unitName": "",
  "sliderUrls": "",
  "specValue": "",
  "status": 0,
  "auditStatus": 0,
  "auditMessage": "",
  "brandName": "",
  "category1Name": "",
  "category2Name": "",
  "category3Name": "",
  "productSkuList": [
    {
      "id": 0,
      "createTime": "",
      "skuCode": "",
      "skuName": "",
      "productId": 0,
      "thumbImg": "",
      "salePrice": 0,
      "marketPrice": 0,
      "costPrice": 0,
      "stockNum": 0,
      "saleNum": 0,
      "skuSpec": "",
      "weight": "",
      "volume": "",
      "status": 0,
      "skuSpecList": []
    }
  ],
  "detailsImageUrls": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|product|Product|body|true|Product|Product|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;name|商品名称||false|string||
|&emsp;&emsp;brandId|品牌ID||false|integer(int64)||
|&emsp;&emsp;category1Id|一级分类id||false|integer(int64)||
|&emsp;&emsp;category2Id|二级分类id||false|integer(int64)||
|&emsp;&emsp;category3Id|三级分类id||false|integer(int64)||
|&emsp;&emsp;unitName|计量单位||false|string||
|&emsp;&emsp;sliderUrls|轮播图||false|string||
|&emsp;&emsp;specValue|商品规格值json串||false|string||
|&emsp;&emsp;status|线上状态：0-初始值，1-上架，-1-自主下架||false|integer(int32)||
|&emsp;&emsp;auditStatus|审核状态||false|integer(int32)||
|&emsp;&emsp;auditMessage|审核信息||false|string||
|&emsp;&emsp;brandName|品牌||false|string||
|&emsp;&emsp;category1Name|一级分类||false|string||
|&emsp;&emsp;category2Name|二级分类||false|string||
|&emsp;&emsp;category3Name|三级分类||false|string||
|&emsp;&emsp;productSkuList|ProductSku||false|array|ProductSku|
|&emsp;&emsp;&emsp;&emsp;id|||false|integer||
|&emsp;&emsp;&emsp;&emsp;createTime|||false|string||
|&emsp;&emsp;&emsp;&emsp;skuCode|商品编号||false|string||
|&emsp;&emsp;&emsp;&emsp;skuName|skuName||false|string||
|&emsp;&emsp;&emsp;&emsp;productId|商品ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;thumbImg|缩略图路径||false|string||
|&emsp;&emsp;&emsp;&emsp;salePrice|售价||false|number||
|&emsp;&emsp;&emsp;&emsp;marketPrice|市场价||false|number||
|&emsp;&emsp;&emsp;&emsp;costPrice|成本价||false|number||
|&emsp;&emsp;&emsp;&emsp;stockNum|库存数||false|integer||
|&emsp;&emsp;&emsp;&emsp;saleNum|销量||false|integer||
|&emsp;&emsp;&emsp;&emsp;skuSpec|sku规格信息json||false|string||
|&emsp;&emsp;&emsp;&emsp;weight|重量||false|string||
|&emsp;&emsp;&emsp;&emsp;volume|体积||false|string||
|&emsp;&emsp;&emsp;&emsp;status|线上状态：0-初始值，1-上架，-1-自主下架||false|integer||
|&emsp;&emsp;&emsp;&emsp;skuSpecList|sku规格信息||false|array|string|
|&emsp;&emsp;detailsImageUrls|详情图片列表||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分页列表


**接口地址**:`/admin/product/product/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||
|productQuery|查询对象|query|true|ProductQuery|ProductQuery|
|&emsp;&emsp;keyword|关键字||false|string||
|&emsp;&emsp;brandId|品牌id||false|integer(int64)||
|&emsp;&emsp;category1Id|一级分类id||false|integer(int64)||
|&emsp;&emsp;category2Id|||false|integer(int64)||
|&emsp;&emsp;category3Id|||false|integer(int64)||
|&emsp;&emsp;order|排序（综合排序:1 价格升序:2 价格降序:3）||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoProduct|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoProduct|PageInfoProduct|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|Product|array|Product|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;name|商品名称|string||
|&emsp;&emsp;&emsp;&emsp;brandId|品牌ID|integer||
|&emsp;&emsp;&emsp;&emsp;category1Id|一级分类id|integer||
|&emsp;&emsp;&emsp;&emsp;category2Id|二级分类id|integer||
|&emsp;&emsp;&emsp;&emsp;category3Id|三级分类id|integer||
|&emsp;&emsp;&emsp;&emsp;unitName|计量单位|string||
|&emsp;&emsp;&emsp;&emsp;sliderUrls|轮播图|string||
|&emsp;&emsp;&emsp;&emsp;specValue|商品规格值json串|string||
|&emsp;&emsp;&emsp;&emsp;status|线上状态：0-初始值，1-上架，-1-自主下架|integer||
|&emsp;&emsp;&emsp;&emsp;auditStatus|审核状态|integer||
|&emsp;&emsp;&emsp;&emsp;auditMessage|审核信息|string||
|&emsp;&emsp;&emsp;&emsp;brandName|品牌|string||
|&emsp;&emsp;&emsp;&emsp;category1Name|一级分类|string||
|&emsp;&emsp;&emsp;&emsp;category2Name|二级分类|string||
|&emsp;&emsp;&emsp;&emsp;category3Name|三级分类|string||
|&emsp;&emsp;&emsp;&emsp;productSkuList|ProductSku|array|ProductSku|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuCode|商品编号|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuName|skuName|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;productId|商品ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;thumbImg|缩略图路径|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;salePrice|售价|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;marketPrice|市场价|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;costPrice|成本价|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stockNum|库存数|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;saleNum|销量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuSpec|sku规格信息json|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;weight|重量|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;volume|体积|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;status|线上状态：0-初始值，1-上架，-1-自主下架|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuSpecList|sku规格信息|array|string|
|&emsp;&emsp;&emsp;&emsp;detailsImageUrls|详情图片列表|string||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"name": "",
				"brandId": 0,
				"category1Id": 0,
				"category2Id": 0,
				"category3Id": 0,
				"unitName": "",
				"sliderUrls": "",
				"specValue": "",
				"status": 0,
				"auditStatus": 0,
				"auditMessage": "",
				"brandName": "",
				"category1Name": "",
				"category2Name": "",
				"category3Name": "",
				"productSkuList": [
					{
						"id": 0,
						"createTime": "",
						"skuCode": "",
						"skuName": "",
						"productId": 0,
						"thumbImg": "",
						"salePrice": 0,
						"marketPrice": 0,
						"costPrice": 0,
						"stockNum": 0,
						"saleNum": 0,
						"skuSpec": "",
						"weight": "",
						"volume": "",
						"status": 0,
						"skuSpecList": []
					}
				],
				"detailsImageUrls": ""
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


## 更新上下架状态


**接口地址**:`/admin/product/product/updateStatus/{id}/{status}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|status||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新审核状态


**接口地址**:`/admin/product/product/updateAuditStatus/{id}/{auditStatus}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|auditStatus||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取详情


**接口地址**:`/admin/product/product/getById/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除


**接口地址**:`/admin/product/product/removeById/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


# CategoryBrand


## 修改


**接口地址**:`/admin/product/categoryBrand/updateById`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "brandId": 0,
  "categoryId": 0,
  "categoryName": "",
  "brandName": "",
  "logo": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|categoryBrand|CategoryBrand|body|true|CategoryBrand|CategoryBrand|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;brandId|品牌ID||false|integer(int64)||
|&emsp;&emsp;categoryId|分类ID||false|integer(int64)||
|&emsp;&emsp;categoryName|||false|string||
|&emsp;&emsp;brandName|||false|string||
|&emsp;&emsp;logo|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 新增


**接口地址**:`/admin/product/categoryBrand/save`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "brandId": 0,
  "categoryId": 0,
  "categoryName": "",
  "brandName": "",
  "logo": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|categoryBrand|CategoryBrand|body|true|CategoryBrand|CategoryBrand|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;brandId|品牌ID||false|integer(int64)||
|&emsp;&emsp;categoryId|分类ID||false|integer(int64)||
|&emsp;&emsp;categoryName|||false|string||
|&emsp;&emsp;brandName|||false|string||
|&emsp;&emsp;logo|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分页列表


**接口地址**:`/admin/product/categoryBrand/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||
|roleQuery|查询对象|query|true|CategoryBrandQuery|CategoryBrandQuery|
|&emsp;&emsp;brandId|||false|integer(int64)||
|&emsp;&emsp;categoryId|||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoCategoryBrand|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoCategoryBrand|PageInfoCategoryBrand|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|CategoryBrand|array|CategoryBrand|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;brandId|品牌ID|integer||
|&emsp;&emsp;&emsp;&emsp;categoryId|分类ID|integer||
|&emsp;&emsp;&emsp;&emsp;categoryName||string||
|&emsp;&emsp;&emsp;&emsp;brandName||string||
|&emsp;&emsp;&emsp;&emsp;logo||string||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"brandId": 0,
				"categoryId": 0,
				"categoryName": "",
				"brandName": "",
				"logo": ""
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


## 获取详情


**接口地址**:`/admin/product/categoryBrand/getById/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 根据分类id获取品牌列表


**接口地址**:`/admin/product/categoryBrand/findBrandByCategoryId/{categoryId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|categoryId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除


**接口地址**:`/admin/product/categoryBrand/removeById/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


# Brand


## 修改


**接口地址**:`/admin/product/brand/updateById`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "name": "",
  "logo": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|brand|Brand|body|true|Brand|Brand|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;name|品牌名称||false|string||
|&emsp;&emsp;logo|品牌图标||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 新增


**接口地址**:`/admin/product/brand/save`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "createTime": "",
  "name": "",
  "logo": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|brand|Brand|body|true|Brand|Brand|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;name|品牌名称||false|string||
|&emsp;&emsp;logo|品牌图标||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分页列表


**接口地址**:`/admin/product/brand/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoBrand|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoBrand|PageInfoBrand|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|Brand|array|Brand|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;name|品牌名称|string||
|&emsp;&emsp;&emsp;&emsp;logo|品牌图标|string||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"name": "",
				"logo": ""
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


## 获取详情


**接口地址**:`/admin/product/brand/getById/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取全部品牌


**接口地址**:`/admin/product/brand/findAll`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除


**接口地址**:`/admin/product/brand/removeById/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


# 后台登录管理


## 登录


**接口地址**:`/admin/system/index/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "username": "",
  "password": "",
  "codeKey": "",
  "captcha": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|loginVo|登录对象|body|true|LoginVo|LoginVo|
|&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;password|密码||false|string||
|&emsp;&emsp;codeKey|验证码图片key||false|string||
|&emsp;&emsp;captcha|输入的验证码||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取用户信息


**接口地址**:`/admin/system/index/userinfo`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取用户菜单


**接口地址**:`/admin/system/index/menus`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取图片验证码


**接口地址**:`/admin/system/index/generateValidateCode`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultValidateCodeVo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||ValidateCodeVo|ValidateCodeVo|
|&emsp;&emsp;codeKey|存储在Redis中的验证码key|string||
|&emsp;&emsp;codeValue|返回给前端的验证码字符串|string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"codeKey": "",
		"codeValue": ""
	}
}
```


# 上传管理接口


## fileUpload


**接口地址**:`/admin/system/fileUpload`


**请求方式**:`POST`


**请求数据类型**:`multipart/form-data`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file||query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultString|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": ""
}
```


# 分类管理


## 导入


**接口地址**:`/admin/product/category/importData`


**请求方式**:`POST`


**请求数据类型**:`multipart/form-data`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file||query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 根据parentId获取下级节点


**接口地址**:`/admin/product/category/findByParentId/{parentId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|parentId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListCategory|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|Category|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;name|分类名称|string||
|&emsp;&emsp;imageUrl|imageUrl|string||
|&emsp;&emsp;parentId|父分类id|integer(int64)||
|&emsp;&emsp;status|是否显示[0-不显示，1显示]|integer(int32)||
|&emsp;&emsp;orderNum|排序|integer(int32)||
|&emsp;&emsp;hasChildren|是否有子节点|boolean||
|&emsp;&emsp;children|子节点列表|array|Category|


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"createTime": "",
			"name": "",
			"imageUrl": "",
			"parentId": 0,
			"status": 0,
			"orderNum": 0,
			"hasChildren": true,
			"children": [
				{
					"id": 0,
					"createTime": "",
					"name": "",
					"imageUrl": "",
					"parentId": 0,
					"status": 0,
					"orderNum": 0,
					"hasChildren": true,
					"children": [
						{}
					]
				}
			]
		}
	]
}
```


## 导出


**接口地址**:`/admin/product/category/exportData`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK||


**响应参数**:


暂无


**响应示例**:
```javascript

```


# 会员接口


## 获取分页列表


**接口地址**:`/admin/user/userInfo/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||
|userInfoQuery|查询对象|query|true|UserInfoQuery|UserInfoQuery|
|&emsp;&emsp;keyword|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTimeBegin|||false|string||
|&emsp;&emsp;createTimeEnd|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoUserInfo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoUserInfo|PageInfoUserInfo|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|UserInfo|array|UserInfo|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;&emsp;&emsp;password|密码|string||
|&emsp;&emsp;&emsp;&emsp;nickName|昵称|string||
|&emsp;&emsp;&emsp;&emsp;avatar|头像|string||
|&emsp;&emsp;&emsp;&emsp;sex|性别|integer||
|&emsp;&emsp;&emsp;&emsp;phone|电话号码|string||
|&emsp;&emsp;&emsp;&emsp;memo|备注|string||
|&emsp;&emsp;&emsp;&emsp;openId|微信open id|string||
|&emsp;&emsp;&emsp;&emsp;unionId|微信开放平台unionID|string||
|&emsp;&emsp;&emsp;&emsp;lastLoginIp|最后一次登录ip|string||
|&emsp;&emsp;&emsp;&emsp;lastLoginTime|最后一次登录时间|string||
|&emsp;&emsp;&emsp;&emsp;status|状态：1为正常，0为禁止|integer||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"username": "",
				"password": "",
				"nickName": "",
				"avatar": "",
				"sex": 0,
				"phone": "",
				"memo": "",
				"openId": "",
				"unionId": "",
				"lastLoginIp": "",
				"lastLoginTime": "",
				"status": 0
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


## 更新状态


**接口地址**:`/admin/user/userInfo/updateStatus/{userId}/{status}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||
|status||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取用户地址


**接口地址**:`/admin/user/userInfo/findUserAddressByUserId/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


# 地区管理


## 根据parentCode获取下级节点


**接口地址**:`/admin/user/region/findByParentCode/{parentCode}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|parentCode||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListRegion|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|Region|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;code|地区编码|string||
|&emsp;&emsp;parentCode|上级地区code|integer(int64)||
|&emsp;&emsp;name|地区名称|string||
|&emsp;&emsp;level|地区级别：1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县|integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"createTime": "",
			"code": "",
			"parentCode": 0,
			"name": "",
			"level": 0
		}
	]
}
```


# SysOperLog


## 获取分页列表


**接口地址**:`/admin/system/sysOperLog/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||
|sysOperLogQuery|查询对象|query|true|SysOperLogQuery|SysOperLogQuery|
|&emsp;&emsp;title|||false|string||
|&emsp;&emsp;operName|||false|string||
|&emsp;&emsp;createTimeBegin|||false|string||
|&emsp;&emsp;createTimeEnd|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoSysOperLog|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoSysOperLog|PageInfoSysOperLog|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|SysOperLog|array|SysOperLog|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;title|模块标题|string||
|&emsp;&emsp;&emsp;&emsp;method|方法名称|string||
|&emsp;&emsp;&emsp;&emsp;requestMethod|请求方式|string||
|&emsp;&emsp;&emsp;&emsp;operatorType|操作类别（0其它 1后台用户 2手机端用户）|string||
|&emsp;&emsp;&emsp;&emsp;operName|操作人员|string||
|&emsp;&emsp;&emsp;&emsp;operUrl|请求URL|string||
|&emsp;&emsp;&emsp;&emsp;operIp|主机地址|string||
|&emsp;&emsp;&emsp;&emsp;operParam|请求参数|string||
|&emsp;&emsp;&emsp;&emsp;jsonResult|返回参数|string||
|&emsp;&emsp;&emsp;&emsp;status|操作状态（0正常 1异常）|integer||
|&emsp;&emsp;&emsp;&emsp;errorMsg|错误消息|string||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"title": "",
				"method": "",
				"requestMethod": "",
				"operatorType": "",
				"operName": "",
				"operUrl": "",
				"operIp": "",
				"operParam": "",
				"jsonResult": "",
				"status": 0,
				"errorMsg": ""
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


# OrderInfo


## 获取分页列表


**接口地址**:`/admin/order/orderInfo/{page}/{limit}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|当前页码|path|true|integer(int32)||
|limit|每页记录数|path|true|integer(int32)||
|orderInfoQuery|查询对象|query|true|OrderInfoQuery|OrderInfoQuery|
|&emsp;&emsp;orderNo|||false|string||
|&emsp;&emsp;orderStatus|||false|integer(int32)||
|&emsp;&emsp;receiverName|||false|string||
|&emsp;&emsp;receiverPhone|||false|string||
|&emsp;&emsp;payType|||false|integer(int32)||
|&emsp;&emsp;createTimeBegin|||false|string||
|&emsp;&emsp;createTimeEnd|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageInfoOrderInfo|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageInfoOrderInfo|PageInfoOrderInfo|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;list|OrderInfo|array|OrderInfo|
|&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;userId|会员_id|integer||
|&emsp;&emsp;&emsp;&emsp;nickName|昵称|string||
|&emsp;&emsp;&emsp;&emsp;orderNo|订单号|string||
|&emsp;&emsp;&emsp;&emsp;couponId|使用的优惠券|integer||
|&emsp;&emsp;&emsp;&emsp;totalAmount|订单总额|number||
|&emsp;&emsp;&emsp;&emsp;couponAmount|优惠券|number||
|&emsp;&emsp;&emsp;&emsp;originalTotalAmount|原价金额|number||
|&emsp;&emsp;&emsp;&emsp;feightFee|运费|number||
|&emsp;&emsp;&emsp;&emsp;payType|支付方式【1->微信】|integer||
|&emsp;&emsp;&emsp;&emsp;orderStatus|订单状态【0->待付款；1->待发货；2->已发货；3->待用户收货，已完成；-1->已取消】|integer||
|&emsp;&emsp;&emsp;&emsp;receiverName|收货人姓名|string||
|&emsp;&emsp;&emsp;&emsp;receiverPhone|收货人电话|string||
|&emsp;&emsp;&emsp;&emsp;receiverTagName|收货人地址标签|string||
|&emsp;&emsp;&emsp;&emsp;receiverProvince|省份/直辖市|string||
|&emsp;&emsp;&emsp;&emsp;receiverCity|城市|string||
|&emsp;&emsp;&emsp;&emsp;receiverDistrict|区|string||
|&emsp;&emsp;&emsp;&emsp;receiverAddress|详细地址|string||
|&emsp;&emsp;&emsp;&emsp;paymentTime|支付时间|string||
|&emsp;&emsp;&emsp;&emsp;deliveryTime|发货时间|string||
|&emsp;&emsp;&emsp;&emsp;receiveTime|确认收货时间|string||
|&emsp;&emsp;&emsp;&emsp;remark|订单备注|string||
|&emsp;&emsp;&emsp;&emsp;cancelTime|取消订单时间|string||
|&emsp;&emsp;&emsp;&emsp;cancelReason|取消订单原因|string||
|&emsp;&emsp;&emsp;&emsp;orderItemList|OrderItem|array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;orderId|order_id|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuId|商品sku编号|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuName|商品sku名字|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;thumbImg|商品sku图片|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuPrice|商品sku价格|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuNum|商品购买的数量|integer||
|&emsp;&emsp;pageNum||integer(int32)||
|&emsp;&emsp;pageSize||integer(int32)||
|&emsp;&emsp;size||integer(int32)||
|&emsp;&emsp;startRow||integer(int64)||
|&emsp;&emsp;endRow||integer(int64)||
|&emsp;&emsp;pages||integer(int32)||
|&emsp;&emsp;prePage||integer(int32)||
|&emsp;&emsp;nextPage||integer(int32)||
|&emsp;&emsp;isFirstPage||boolean||
|&emsp;&emsp;isLastPage||boolean||
|&emsp;&emsp;hasPreviousPage||boolean||
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;navigatePages||integer(int32)||
|&emsp;&emsp;navigatepageNums||array|integer(int32)|
|&emsp;&emsp;navigateFirstPage||integer(int32)||
|&emsp;&emsp;navigateLastPage||integer(int32)||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"total": 0,
		"list": [
			{
				"id": 0,
				"createTime": "",
				"userId": 0,
				"nickName": "",
				"orderNo": "",
				"couponId": 0,
				"totalAmount": 0,
				"couponAmount": 0,
				"originalTotalAmount": 0,
				"feightFee": 0,
				"payType": 0,
				"orderStatus": 0,
				"receiverName": "",
				"receiverPhone": "",
				"receiverTagName": "",
				"receiverProvince": "",
				"receiverCity": "",
				"receiverDistrict": "",
				"receiverAddress": "",
				"paymentTime": "",
				"deliveryTime": "",
				"receiveTime": "",
				"remark": "",
				"cancelTime": "",
				"cancelReason": "",
				"orderItemList": [
					{
						"id": 0,
						"createTime": "",
						"orderId": 0,
						"skuId": 0,
						"skuName": "",
						"thumbImg": "",
						"skuPrice": 0,
						"skuNum": 0
					}
				]
			}
		],
		"pageNum": 0,
		"pageSize": 0,
		"size": 0,
		"startRow": 0,
		"endRow": 0,
		"pages": 0,
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": true,
		"hasNextPage": true,
		"navigatePages": 0,
		"navigatepageNums": [],
		"navigateFirstPage": 0,
		"navigateLastPage": 0
	}
}
```


## 获取详情


**接口地址**:`/admin/order/orderInfo/show/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取订单统计数据


**接口地址**:`/admin/order/orderInfo/getOrderStatisticsData`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|orderStatisticsQuery|查询对象|query|true|OrderStatisticsQuery|OrderStatisticsQuery|
|&emsp;&emsp;orderDateBegin|||false|string||
|&emsp;&emsp;orderDateEnd|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {}
}
```