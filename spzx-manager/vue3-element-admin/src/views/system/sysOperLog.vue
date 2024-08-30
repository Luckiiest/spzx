<template>
  <!---搜索表单-->
  <div class="search-div">
    <el-form label-width="70px" size="normal">
      <el-row>
        <el-col>
          <el-form-item label="关键字">
            <el-input
              style="width: 100%"
              placeholder="模块标题、操作人员、主机地址"
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
  <el-table :data="list" style="font-size: 15px">
    <el-table-column
      show-overflow-tooltip
      width="230"
      prop="title"
      label="模块标题"
    />
    <el-table-column
      show-overflow-tooltip
      width="200"
      prop="method"
      label="方法名称"
    />
    <el-table-column
      show-overflow-tooltip
      width="100"
      prop="requestMethod"
      label="请求方式"
    />
    <el-table-column
      show-overflow-tooltip
      width="100"
      prop="operatorType"
      label="操作类别"
    ></el-table-column>
    <el-table-column
      show-overflow-tooltip
      width="100"
      prop="operName"
      label="操作人员"
    />
    <el-table-column
      show-overflow-tooltip
      width="200"
      prop="operUrl"
      label="请求URL"
    />
    <el-table-column
      show-overflow-tooltip
      width="150"
      prop="operIp"
      label="主机地址"
    />
    <el-table-column
      show-overflow-tooltip
      width="200"
      prop="operParam"
      label="请求参数"
    />
    <el-table-column
      show-overflow-tooltip
      width="200"
      prop="jsonResult"
      label="返回参数"
    />
    <el-table-column
      show-overflow-tooltip
      width="100"
      prop="status"
      label="操作状态"
      #default="scope"
    >
      {{ scope.row.status == 0 ? '正常' : '异常' }}
    </el-table-column>
    <el-table-column
      show-overflow-tooltip
      width="200"
      prop="errorMsg"
      label="错误消息"
    />
    <el-table-column
      show-overflow-tooltip
      width="200"
      prop="createTime"
      label="创建时间"
    />
  </el-table>

  <!-- 分页模块 -->
  <el-pagination
    v-model:current-page="pageParam.page"
    v-model:page-size="pageParam.limit"
    :page-sizes="[5, 10, 20, 50, 100]"
    layout="total, sizes, prev, pager, next"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>
<script setup>
import { GetSysOperLogData } from '@/api/sysOperLog'
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
// ====================定义数据模型end============================

onMounted(() => {
  fetchData()
})

// 定义请求数据函数
const fetchData = async () => {
  const { data, code } = await GetSysOperLogData(
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