import request from '@/utils/request'

const api_name = '/admin/product/categoryBrand'

/**
 * 分页条件查询分类品牌
 * @param{*} queryData
 * @returns
 */
export const GetCategoryBrand = (pageNum, pageSize, categoryBrandDto) => {
  return request({
    url: `${api_name}/${pageNum}/${pageSize}`,
    method: 'get',
    params: categoryBrandDto,
  })
}
/**
 * 新增品牌分类
 * @param {} queryData
 * @returns
 */
export const SaveCategoryBrand = categoryBrand => {
  return request({
    url: `${api_name}/save`,
    method: 'post',
    data: categoryBrand,
  })
}
/**
 * 修改品牌分类
 * @param {} categoryBrand
 * @returns
 */
export const UpdateCategoryBrandById = categoryBrand => {
  return request({
    url: `${api_name}/updateById`,
    method: 'put',
    data: categoryBrand,
  })
}

// 根据分类的id获取品牌数据
export const FindBrandByCategoryId = categoryId => {
  return request({
    url: `${api_name}/findBrandByCategoryId/${categoryId}`,
    method: 'get',
  })
}

// 根据id删除数据
export const DeleteCategoryBrandById = id => {
  return request({
    url: `${api_name}/deleteById/${id}`,
    method: 'delete',
  })
}
