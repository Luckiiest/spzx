import request from '@/utils/request'

const api_name = '/admin/system/sysMenu'

// 获取菜单列表
export const FindNodes = () => {
  return request({
    url: `${api_name}/findNodes`,
    method: 'get',
  })
}

// 新增菜单
export const SaveSysMenu = sysMenu => {
  return request({
    url: `${api_name}/save`,
    method: 'post',
    data: sysMenu,
  })
}

// 删除菜单
export const RemoveSysMenuById = id => {
  return request({
    url: `${api_name}/removeById/${id}`,
    method: 'delete',
  })
}

// 更改菜单
export const UpdateSysMenu = sysMenu => {
  return request({
    url: `${api_name}/updateById`,
    method: 'put',
    data: sysMenu,
  })
}
