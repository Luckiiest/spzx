import request from '@/utils/request'

const api_name = '/admin/system/sysOperLog'

// 保存用户分配角色
export const GetSysOperLogData = (queryDto, pageNum, pageSize) => {
  return request({
    url: `${api_name}/findByPage/${pageNum}/${pageSize}`,
    method: 'get',
    params: queryDto,
  })
}
