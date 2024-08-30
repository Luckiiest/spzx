<template>
  <div class="tools-div">
    <el-button type="success" size="normal" @click="exportData()">
      导出
    </el-button>
    <el-button type="primary" size="normal" @click="importData()">
      导入
    </el-button>
  </div>

  <!-- 导入对话框 -->
  <el-dialog v-model="dialogImportVisible" title="导入" width="30%">
    <el-form label-width="120px">
      <el-form-item label="分类文件">
        <el-upload
          class="upload-demo"
          action="http://localhost:8501/admin/product/category/importData"
          :on-success="onUploadSuccess"
          :headers="headers"
        >
          <el-button type="primary">上传</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
  </el-dialog>

  <!---懒加载的树形表格-->
  <el-table
    :data="list"
    style="width: 100%"
    row-key="id"
    border
    lazy
    :load="fetchData"
    :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
  >
    <el-table-column prop="name" label="分类名称" />
    <el-table-column prop="imageUrl" label="图标" #default="scope">
      <img :src="scope.row.imageUrl" width="50" />
    </el-table-column>
    <el-table-column prop="orderNum" label="排序" />
    <el-table-column prop="status" label="状态" #default="scope">
      {{ scope.row.status == 1 ? '正常' : '停用' }}
    </el-table-column>
    <el-table-column prop="createTime" label="创建时间" />
  </el-table>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { ExportCategoryData, FindCategoryByParentId } from '@/api/category'
import { ElMessage } from 'element-plus'
import { useApp } from '@/pinia/modules/app'
// 定义上传成功后的处理
const onUploadSuccess = res => {
  const { code } = res
  if (code == 200) {
    ElMessage.success('上传成功')
    dialogImportVisible.value = false
    // 数据刷新
    fetchDataParent()
  }
}

// 文件上传头信息字段
const headers = ref({
  token: useApp().authorization.token,
})

// 定义对话框显示控制变量
const dialogImportVisible = ref(false)
// 定义对话框点击事件
const importData = () => {
  dialogImportVisible.value = true
}

// 导出数据到Excel
const exportData = () => {
  // 利用 ExportCategoryData() 方法获取导出数据
  ExportCategoryData().then(res => {
    // 创建Blob对象，接受文件二进制数据
    const blob = new Blob([res])
    // 创建a标签元素，并将Blob对象转换为URL
    const link = document.createElement('a')
    link.href = window.URL.createObjectURL(blob)
    // 设置下载文件名称
    link.download = '分类数据.xlsx'
    // 模拟点击下载数据
    link.click()
  })
}

// 定义list属性模型
const list = ref([])
// 挂载后请求数据模型
onMounted(async () => {
  fetchDataParent()
})

const fetchDataParent = async () => {
  // 查询一级菜单列表
  const { data, code, message } = await FindCategoryByParentId(0)
  // 赋值
  list.value = data
}

// 加载数据的方法
const fetchData = async (row, treeNode, resolve) => {
  // 获取当前分页id
  let parentId = row.id
  // 向后端发送请求获取数据
  const { data, code, message } = await FindCategoryByParentId(parentId)
  // 返回数据
  resolve(data)
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
.tools-div {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}
</style>