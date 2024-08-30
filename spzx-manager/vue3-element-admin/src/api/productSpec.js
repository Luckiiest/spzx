import request from '@/utils/request'

const api_name = '/admin/product/productSpec'

// 查询所有的产品规格数据
export const FindAllProductSpec = () => {
  return request({
    url: `${api_name}/findAll`,
    method: 'get',
  })
}

// 请求全部品牌数据
export const GetProductSpecPageList = (pageNum, pageSize) => {
  return request({
    url: `${api_name}/${pageNum}/${pageSize}`,
    method: 'get',
  })
}

// 新增品牌数据
export const SaveProductSpec = productSpec => {
  return request({
    url: `${api_name}/save`,
    method: 'post',
    data: productSpec,
  })
}

// 更新品牌数据
export const UpdateProductSpec = productSpec => {
  return request({
    url: `${api_name}/updateById`,
    method: 'put',
    data: productSpec,
  })
}

// 删除品牌数据
export const DeleteProductSpecById = id => {
  return request({
    url: `${api_name}/deleteById/${id}`,
    method: 'delete',
  })
}
