import request from '@/utils/request'

const api_name = '/admin/order/orderInfo'

// 订单统计
export const GetOrderStatisticsData = queryDto => {
  return request({
    url: `${api_name}/getOrderStatisticsData`,
    method: 'get',
    params: queryDto,
  })
}

// 查询订单信息
export const GetOrderInfoList = (queryDto, pageNum, pageSize) => {
  return request({
    url: `${api_name}/getOrderInfoList/${pageNum}/${pageSize}`,
    method: 'get',
    params: queryDto,
  })
}

// 根据订单id查询订单项
export const getOrderItem = orderId => {
  return request({
    url: `${api_name}/getOrderItem/${orderId}`,
    method: 'get',
  })
}
