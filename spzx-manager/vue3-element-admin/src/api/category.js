import request from '@/utils/request'

const api_name = '/admin/product/category'

// 根据parentId获取下级节点
export const FindCategoryByParentId = parentId => {
  return request({
    url: `${api_name}/findCategoryListByParentId/${parentId}`,
    method: 'get',
  })
}

// 导出全部数据内容到Excel中
export const ExportCategoryData = () => {
  return request({
    url: `${api_name}/exportData`,
    method: 'get',
    responseType: 'blob',
  })
}
