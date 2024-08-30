import request from '@/utils/request'

const api_name = '/admin/system/sysUser'

// 保存用户分配角色
export const DoAssignRoleToUser = assignRoleVo => {
  return request({
    url: `/admin/system/sysRoleUser/doAssign`,
    method: 'post',
    data: assignRoleVo,
  })
}

// 删除用户数据
export const GetSysUserListByPage = (pageNum, pageSize, queryDto) => {
  return request({
    url: `${api_name}/findByPage/${pageNum}/${pageSize}`,
    method: 'get',
    params: queryDto,
  })
}

// 新增用户数据
export const SaveSysUser = sysUser => {
  return request({
    url: `${api_name}/saveSysUser`,
    method: 'post',
    data: sysUser,
  })
}

// 修改用户数据
export const UpdateSysUser = sysUser => {
  return request({
    url: `${api_name}/updateSysUser`,
    method: 'put',
    data: sysUser,
  })
}

// 删除用户数据
export const DeleteSysUser = userId => {
  return request({
    url: `${api_name}/deleeById/${userId}`,
    method: 'delete',
  })
}
