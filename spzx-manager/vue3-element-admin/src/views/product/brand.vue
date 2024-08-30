<template>
  <div class="tools-div">
    <el-button type="success" size="normal" @click="addShow()">添 加</el-button>
  </div>

  <el-dialog v-model="dialogVisible" title="添加或修改" width="30%">
    <el-form label-width="120px">
      <el-form-item label="品牌名称">
        <el-input v-model="brand.name" />
      </el-form-item>
      <el-form-item label="品牌图标">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8501/admin/system/fileUpload"
          :show-file-list="false"
          :headers="headers"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="brand.logo" :src="brand.logo" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate()">提交</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <el-table :data="list" style="width: 100%">
    <el-table-column prop="name" label="品牌名称" />
    <el-table-column prop="logo" label="品牌图标" #default="scope">
      <img :src="scope.row.logo" width="50" />
    </el-table-column>
    <el-table-column prop="createTime" label="创建时间" />
    <el-table-column label="操作" align="center" width="200" #default="scope">
      <el-button type="primary" size="normal" @click="editShow(scope.row)">
        修改
      </el-button>
      <el-button type="danger" size="normal" @click="remove(scope.row.id)">
        删除
      </el-button>
    </el-table-column>
  </el-table>

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
import { onMounted, ref } from 'vue'
import {
  DeleteBrandById,
  GetBrandPageList,
  SaveBrand,
  UpdateBrandById,
} from '@/api/brand'
import { getChildState } from 'element-plus/es/components/tree/src/model/node'
import { useApp } from '@/pinia/modules/app'
import { ElMessage, ElMessageBox } from 'element-plus'
// 删除处理
const remove = id => {
  // 提示框
  ElMessageBox.confirm('此操作将永久删除该记录，是否继续?', 'warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(async () => {
    const { code } = await DeleteBrandById(id)
    if (code == 200) {
      // 提示
      ElMessage.success('新增成功')
      // 刷新
      fetchData()
    }
  })
}

// 新增处理
// 定义新增对话框显示变量
const dialogVisible = ref(false)
// 定义窗口显示方法
const addShow = () => {
  brand.value = {}
  dialogVisible.value = true
}
// 定义品牌数据模型
const defaultFormParam = {
  id: '',
  name: '',
  logo: '',
}
const brand = ref(defaultFormParam)
// 实现文件上传
// 定义携带的token头信息
const headers = ref({
  token: useApp().authorization.token,
})
// 上传logo成功回调
const handleAvatarSuccess = res => {
  // 给brand中logo赋值
  brand.value.logo = res.data
  ElMessage.success('上传成功')
}
// 提交方法
const saveOrUpdate = row => {
  if (!brand.value.id) {
    saveData()
  } else {
    editData()
  }
}
// 新增事件方法
const saveData = async () => {
  // 请求
  const { code, data } = await SaveBrand(brand.value)
  // 判断
  if (code == 200) {
    // 提示
    ElMessage.success('新增成功')
    // 关闭窗口
    dialogVisible.value = false
    // 刷新
    fetchData()
  }
}
// 修改处理
//进入修改
const editShow = row => {
  brand.value = row
  dialogVisible.value = true
}
// 定义修改事件方法
const editData = async () => {
  const { code, data } = await UpdateBrandById(brand.value)
  // 判断
  if (code == 200) {
    // 提示
    ElMessage.success('修改成功')
    // 关闭窗口
    dialogVisible.value = false
    // 刷新
    fetchData()
  }
}

// 分页处理
const handleSizeChange = () => {
  // 刷新页面
  fetchData()
}
const handleCurrentChange = () => {
  // 刷新页面
  fetchData()
}

// 定义表格数据模型
const list = ref([])

// 分页条数据模型
const total = ref(0)

// 定义分页数据模型
const defaultPageParam = {
  page: 1,
  limit: 5,
}
const pageParam = ref(defaultPageParam)

// 定义分页查询方法
const fetchData = async () => {
  const { code, data, message } = await GetBrandPageList(
    pageParam.value.page,
    pageParam.value.limit
  )
  if (code == 200) {
    // 赋值数据
    list.value = data.list
    // 赋值总数
    total.value = data.total
  }
}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.tools-div {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}

.avatar-uploader .avatar {
  width: 128px;
  height: 128px;
  display: block;
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 128px;
  height: 128px;
  text-align: center;
}
</style>