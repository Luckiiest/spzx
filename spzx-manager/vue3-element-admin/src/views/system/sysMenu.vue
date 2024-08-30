<template>
  <!-- 新增按钮 -->
  <div class="tools-div">
    <el-button type="success" size="normal" @click="addShow">添 加</el-button>
  </div>

  <!-- 新增窗口 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%">
    <el-form label-width="120px">
      <el-form-item label="菜单标题">
        <el-input v-model="sysMenu.title" />
      </el-form-item>
      <el-form-item label="路由名称">
        <el-input v-model="sysMenu.component" />
      </el-form-item>
      <el-form-item label="排序">
        <el-input v-model="sysMenu.sortValue" />
      </el-form-item>
      <el-form-item label="状态">
        <el-radio-group v-model="sysMenu.status">
          <el-radio :label="1">正常</el-radio>
          <el-radio :label="0">停用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">提交</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <!-- 展示菜单列表 tree -->
  <el-table
    :data="list"
    style="width: 100%; margin-bottom: 20px"
    row-key="id"
    border
    default-expand-all
  >
    <el-table-column width="240" prop="title" label="菜单标题" />
    <el-table-column width="200" prop="component" label="路由名称" />
    <el-table-column width="80" prop="sortValue" label="排序" />
    <el-table-column width="150" prop="status" label="状态" #default="scope">
      {{ scope.row.status == 1 ? '正常' : '停用' }}
    </el-table-column>
    <el-table-column width="200" prop="createTime" label="创建时间" />

    <el-table-column label="操作" align="center" #default="scope">
      <el-button type="success" size="normal" @click="addShow(scope.row)">
        添加下级节点
      </el-button>
      <el-button type="primary" size="normal" @click="editShow(scope.row)">
        修改
      </el-button>
      <el-button type="danger" size="normal" @click="remove(scope.row)">
        删除
      </el-button>
    </el-table-column>
  </el-table>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import {
  FindNodes,
  RemoveSysMenuById,
  SaveSysMenu,
  UpdateSysMenu,
} from '@/api/sysMenu'
import { ElMessage, ElMessageBox } from 'element-plus'

// 菜单查询=====================
// 定义表格模型数据
const list = ref([])
// 定义页面挂载钩子
onMounted(async () => {
  fetchData()
})
// 定义查询方法
const fetchData = async () => {
  const { code, message, data } = await FindNodes()
  if (code == 200) {
    console.log(data)
    // 赋值数据列表
    list.value = data
  }
}

// 删除菜单==============
const remove = row => {
  ElMessageBox.confirm(`确认删除菜单（${row.title}）吗`, {
    confirmButtonText: '确认删除',
    cancelButtonText: '取消删除',
    type: 'warning',
  }).then(async () => {
    // 请求新增接口
    const { code, message } = await RemoveSysMenuById(row.id)
    // 判断
    if (code == 200) {
      // 提示
      ElMessage.success('删除成功')
      // 隐藏
      dialogVisible.value = false
      // 刷新数据
      fetchData()
    } else {
      ElMessage.error(message)
    }
  })
}

// 菜单修改=====================
// 定义修改对话框显示
const editShow = row => {
  // 设置标题
  dialogTitle.value = `修改（${row.title}）菜单`
  // 赋值默认回显
  sysMenu.value = row
  //显示对话框
  dialogVisible.value = true
}
// 定义修改方法
const updateData = async () => {
  // 请求修改接口
  const { code, message } = await UpdateSysMenu(sysMenu.value)
  // 判断
  if (code == 200) {
    // 提示
    ElMessage.success('修改成功')
    // 隐藏
    dialogVisible.value = false
    // 刷新数据
    fetchData()
  }
}

// 菜单新增====================
// 定义对话框显示变量
const dialogVisible = ref(false)
// 定义对话框标题变量
const dialogTitle = ref('添加菜单')
// 定义新增事件方法
const addShow = row => {
  // 设置回复默认值
  sysMenu.value = {
    status: 1,
  }
  if (!row.id) {
    dialogTitle.value = '添加菜单'
    // 添加一级菜单
    sysMenu.value.parentId = 0
  } else {
    dialogTitle.value = `添加（${row.title}）下级菜单`
    sysMenu.value.parentId = row.id
  }
  // 显示
  dialogVisible.value = true
}
// 定义菜单数据模型
const defaultForm = {
  id: '',
  parentId: 0,
  title: '',
  url: '', // 未定
  component: '',
  icon: '',
  sortValue: 1,
  status: 1,
}
// 设置为响应式
const sysMenu = ref(defaultForm)
// 定义新增保存方法
const saveData = async () => {
  // 请求新增接口
  const { code, message } = await SaveSysMenu(sysMenu.value)
  // 判断
  if (code == 200) {
    // 提示
    ElMessage.success('新增成功')
    // 隐藏
    dialogVisible.value = false
    // 刷新数据
    fetchData()
  }
}
// 定义提交方法
const saveOrUpdate = () => {
  // 判断是否有id
  if (!sysMenu.value.id) {
    // 执行新增
    saveData()
  } else {
    // 执行修改
    updateData()
  }
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