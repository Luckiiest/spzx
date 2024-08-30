import request from '@/utils/request'

const api_name = '/admin/product/brand'

// 请求全部品牌数据
export const GetBrandList = () => {
  return request({
    url: `${api_name}/findAll`,
    method: 'get',
  })
}

// 请求分页数据
export const GetBrandPageList = (pageNum, pageSize) => {
  return request({
    url: `${api_name}/${pageNum}/${pageSize}`,
    method: 'get',
  })
}

// 新增品牌数据
export const SaveBrand = brand => {
  return request({
    url: `${api_name}/save`,
    method: 'post',
    data: brand,
  })
}
// 修改品牌数据
export const UpdateBrandById = brand => {
  return request({
    url: `${api_name}/updateById`,
    method: 'put',
    data: brand,
  })
}
// 根据id删除品牌数据
export const DeleteBrandById = id => {
  return request({
    url: `${api_name}/deleteById/${id}`,
    method: 'delete',
  })
}
