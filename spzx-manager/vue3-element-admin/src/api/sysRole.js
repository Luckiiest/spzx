import request from '@/utils/request'

const api_name = '/admin/system/sysRole'

// 保存分配菜单
export const DoAssignMenuIdToSysRole = assginMenuDto => {
  return request({
    url: `/admin/system/sysRoleMenu/doAssign`,
    method: 'post',
    data: assginMenuDto,
  })
}

//获取所有菜单和当前用户原来的菜单id集合
export const GetSysRoleMenuIds = roleId => {
  return request({
    url: `/admin/system/sysRoleMenu/findSysRoleMenuByRoleId/${roleId}`,
    method: 'get',
  })
}

// 获取所有角色和当前用户原来的角色
export const GetAllRoleList = userId => {
  return request({
    url: `${api_name}/findAllRoles/${userId}`,
    method: 'get',
  })
}

// 删除角色数据
export const DeleteSysRole = roleId => {
  return request({
    url: `${api_name}/deleteSysRoleById/${roleId}`,
    method: 'delete',
  })
}

// 修改角色数据
export const UpdateSysRole = sysRole => {
  return request({
    url: `${api_name}/updateSysRole`,
    method: 'put',
    data: sysRole,
  })
}

// 添加角色数据
export const SaveSysRole = sysRole => {
  return request({
    url: `${api_name}/saveSysRole`,
    method: 'post',
    data: sysRole,
  })
}

// 分页查询角色数据
export const GetSysRoleListByPage = (pageNum, pageSize, queryDto) => {
  return request({
    url: `${api_name}/findByPage/${pageNum}/${pageSize}`,
    method: 'get',
    params: queryDto,
  })
}
