<template>
  <!---搜索表单-->
  <div class="search-div">
    <el-form label-width="70px" size="normal">
      <el-row>
        <el-col>
          <el-form-item label="关键字">
            <el-input
              style="width: 100%"
              placeholder="订单号"
              v-model="queryDto.keyword"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="display: flex">
        <el-button type="primary" size="normal" @click="searchOrderInfo()">
          搜索
        </el-button>
        <el-button size="normal" @click="resetData()">重置</el-button>
      </el-row>
    </el-form>
  </div>

  <!---数据表格-->
  <el-table
    :data="list"
    table-layout="auto"
    class="tableAuto"
    style="font-size: 15px"
  >
    <el-table-column prop="userId" label="会员id" />
    <el-table-column prop="nickName" label="昵称" />
    <el-table-column prop="orderNo" label="订单号" />
    <el-table-column prop="couponId" label="使用的优惠券" />
    <el-table-column prop="totalAmount" label="订单总额" />
    <el-table-column prop="couponAmount" label="优惠券" />
    <el-table-column prop="originalTotalAmount" label="原价金额" />
    <el-table-column prop="feightFee" label="运费" />
    <el-table-column prop="payType" label="支付方式" #default="scope">
      {{ scope.row.payType == 1 ? '微信' : '支付宝' }}
    </el-table-column>
    <el-table-column prop="orderStatus" label="订单状态" />
    <el-table-column prop="receiverName" label="收货人姓名" />
    <el-table-column prop="receiverPhone" label="收货人电话" />
    <el-table-column prop="receiverTagName" label="收货人地址标签" />
    <el-table-column prop="receiverProvince" label="省份/直辖市" />
    <el-table-column prop="receiverCity" label="城市" />
    <el-table-column prop="receiverDistrict" label="区" />
    <el-table-column prop="receiverAddress" label="详细地址" />
    <el-table-column prop="paymentTime" label="支付时间" />
    <el-table-column prop="deliveryTime" label="发货时间" />
    <el-table-column prop="receiveTime" label="确认收货时间" />
    <el-table-column prop="remark" label="订单备注" />
    <el-table-column prop="cancelTime" label="取消订单时间" />
    <el-table-column prop="cancelReason" label="取消订单原因" />
    <el-table-column prop="createTime" label="创建时间" />
    <el-table-column label="更多" #default="scope">
      <el-button prop="orderItemList" @click="orderShow(scope.row)">
        订单项
      </el-button>
    </el-table-column>
  </el-table>

  <!-- 订单项 -->
  <el-dialog v-model="dialogVisible" title="Shipping address" width="800">
    <el-table :data="orderItem" table-layout="auto" class="tableAuto">
      <el-table-column prop="orderId" label="订单Id" />
      <el-table-column prop="skuId" label="商品Sku编号" />
      <el-table-column prop="skuName" label="商品sku名称" />
      <!-- <el-table-column prop="thumbImg" label="商品sku图片" /> -->
      <el-table-column
        prop="thumbImg"
        label="商品sku图片"
        #default="scope"
        width="200"
      >
        <div style="height: 50px; float: left">
          <img
            :key="index"
            @click="showViewer(scope.row.thumbImg)"
            :src="scope.row.thumbImg"
            width="50"
          />
        </div>
      </el-table-column>
      <el-table-column prop="skuPrice" label="商品sku价格" />
      <el-table-column prop="skuNum" label="商品购买数量" />
      <el-table-column prop="createTime" label="创建时间" />
    </el-table>
  </el-dialog>
  <el-image-viewer
    v-if="showImageViewer"
    @close="closeViewer()"
    :url-list="currentImageUrl ? [currentImageUrl] : []"
  />

  <!-- 分页模块 -->
  <el-pagination
    v-model:current-page="pageParam.page"
    v-model:page-size="pageParam.limit"
    :page-sizes="[2, 5, 10, 20, 50, 100]"
    layout="total, sizes, prev, pager, next"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>



<script setup>
import { GetOrderInfoList, getOrderItem } from '@/api/orderInfo'
import { ElImageViewer } from 'element-plus'
import { onMounted, ref } from 'vue'

// ====================定义数据模型start============================
// 定义表格数据模型
const list = ref([])
// 定义分页条件模型
const defaultPageForm = {
  page: 1,
  limit: 5,
}
const pageParam = ref(defaultPageForm)
// 定义分页总数
const total = ref()
// 请求条件数据模型
const queryDto = ref({
  keyword: '',
})
// 定义对话框显示变量
const dialogVisible = ref(false)
// 定义订单项数据模型
const orderItem = ref([])

// 定义图片预览处理
// 定义当前图片
const currentImageUrl = ref('')
const showImageViewer = ref(false) //组件是否显示
const showViewer = imageUrl => {
  //预览大图
  showImageViewer.value = true
  currentImageUrl.value = imageUrl
}
const closeViewer = () => {
  //必须要这个事件 不然点击右上角关闭按钮没有反应
  showImageViewer.value = false
  currentImageUrl.value = ''
}
// ====================定义数据模型end============================

onMounted(() => {
  fetchData()
})

// 定义请求数据函数
const fetchData = async () => {
  const { data, code } = await GetOrderInfoList(
    queryDto.value,
    pageParam.value.page,
    pageParam.value.limit
  )
  if (code == 200) {
    // 赋值数据列表
    list.value = data.list
    // 赋值分页总数
    total.value = data.total
  }
}

// 定义显示订单项函数
const orderShow = async row => {
  dialogVisible.value = true
  const { code, data } = await getOrderItem(row.id)
  if (code == 200) {
    orderItem.value = data
  }
}

// 定义分页处理
// 定义页面条数改变事件
const handleSizeChange = () => {
  fetchData()
}

// 定义当前页改变事件
const handleCurrentChange = () => {
  fetchData()
}

// 定义搜索事件
const searchOrderInfo = () => {
  fetchData()
}

//定义重置
const resetData = () => {
  queryDto.value.keyword = ''
  fetchData()
}
</script>

<style>
.search-div {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}
.tableAuto.el-table .cell {
  white-space: nowrap;
}
</style>