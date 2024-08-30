<template>
  <div class="search-div">
    <el-form label-width="90px" size="normal">
      <el-row>
        <el-col :span="20">
          <el-form-item label="订单日期">
            <el-date-picker
              v-model="createTimes"
              type="daterange"
              range-separator="To"
              start-placeholder="开始日期"
              end-placeholder="截止日期"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
            />
          </el-form-item>
        </el-col>
        <el-col :span="1" class="btn-search">
          <el-button type="primary" size="normal" @click="fetchData()">
            搜索
          </el-button>
        </el-col>
        <el-col :span="1" class="btn-reset">
          <el-button type="primary" size="normal" @click="resetData()">
            重置
          </el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
  <div ref="orderTotalAmountDiv" style="width: 100%; height: 100%"></div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import * as echarts from 'echarts' // 导入Echart库中所有的图形报表组件
import { GetOrderStatisticsData } from '@/api/orderInfo'

// 定义chart数据模型，用来选中div组件
const orderTotalAmountDiv = ref()
// 定义时间模型
const createTimes = ref([])
// 定义查询条件模型
const queryDto = ref({
  createTimeBegin: '',
  createTimeEnd: '',
})

// 需要在onMounted钩子函数中对div区域使用echarts进行初始化
onMounted(async () => {
  // 获取数据
  fetchData()
})

// 请求数据方法
const fetchData = async () => {
  // 封装数据
  if (createTimes.value != null && createTimes.value.length == 2) {
    queryDto.value.createTimeBegin = createTimes.value[0]
    queryDto.value.createTimeEnd = createTimes.value[1]
  }
  //  请求查询接口
  const { data } = await GetOrderStatisticsData(queryDto.value)
  // 基于准备好的dom，初始化echarts实例
  setOptions(data.dateList, data.amountList)
}

// 重置方法
const resetData = () => {
  queryDto.value = {}
  fetchData()
}
const setOptions = (dateList, amountList) => {
  var orderTotalAmountChart = echarts.init(orderTotalAmountDiv.value)
  // 绘制图表
  orderTotalAmountChart.setOption({
    title: {
      text: '订单数据统计',
    },
    tooltip: {},
    xAxis: {
      data: dateList,
    },
    yAxis: {},
    series: [
      {
        name: '订单总金额（万元）',
        type: 'bar',
        data: amountList,
      },
    ],
  })
}
</script>

<style scoped>
.search-div {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}
.btn-search,
.btn-reset {
  margin: 0 10px;
}
</style>