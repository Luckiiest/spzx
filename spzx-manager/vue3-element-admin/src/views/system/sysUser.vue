<template>
  <!---搜索表单-->
  <div class="search-div">
    <el-form label-width="70px" size="normal">
      <el-row>
        <el-col :span="12">
          <el-form-item label="关键字">
            <el-input
              style="width: 100%"
              placeholder="用户名、姓名、手机号码"
              v-model="queryDto.keyword"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间">
            <el-date-picker
              v-model="createTimes"
              type="daterange"
              range-separator="To"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="display: flex">
        <el-button type="primary" size="normal" @click="searchSysUser()">
          搜索
        </el-button>
        <el-button size="normal" @click="resetData">重置</el-button>
      </el-row>
    </el-form>
  </div>

  <!--添加按钮-->
  <div class="tools-div">
    <el-button type="success" size="normal" @click="addShow">添 加</el-button>
  </div>

  <!-- 添加或修改对话框 -->
  <el-dialog v-model="dialogVisible" title="添加或修改" width="40%">
    <el-form label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="sysUser.userName" />
      </el-form-item>
      <el-form-item v-if="sysUser.id == null" label="密码">
        <el-input type="password" show-password v-model="sysUser.password" />
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="sysUser.name" />
      </el-form-item>
      <el-form-item label="手机">
        <el-input v-model="sysUser.phone" />
      </el-form-item>
      <el-form-item label="头像">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8501/admin/system/fileUpload"
          :show-file-list="false"
          :headers="headers"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="sysUser.avatar" :src="sysUser.avatar" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="sysUser.description" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">提交</el-button>
        <el-button @click="hideDialogVisible">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <!---数据表格-->
  <el-table :data="list" style="width: 100%; font-size: 15px">
    <el-table-column prop="userName" label="用户名" />
    <el-table-column prop="name" label="姓名" />
    <el-table-column prop="phone" label="手机" />
    <el-table-column prop="avatar" label="头像" #default="scope">
      <img :src="scope.row.avatar" width="50" />
    </el-table-column>
    <el-table-column prop="description" label="描述" />
    <el-table-column prop="status" label="状态" #default="scope">
      {{ scope.row.status == 1 ? '正常' : '停用' }}
    </el-table-column>
    <el-table-column prop="createTime" label="创建时间" />
    <el-table-column label="操作" align="center" width="280" #default="scope">
      <el-button type="primary" size="normal" @click="editShow(scope.row)">
        修改
      </el-button>
      <el-button type="danger" size="normal" @click="deleteById(scope.row.id)">
        删除
      </el-button>
      <el-button
        type="warning"
        size="normal"
        @click="showAssignRole(scope.row)"
      >
        分配角色
      </el-button>
    </el-table-column>
  </el-table>

  <el-dialog v-model="dialogRoleVisible" title="分配角色" width="40%">
    <el-form label-width="80px">
      <el-form-item label="用户名">
        <el-input disabled :value="sysUser.userName"></el-input>
      </el-form-item>

      <el-form-item label="角色列表">
        <el-checkbox-group v-model="userRoleIds">
          <el-checkbox v-for="role in allRoles" :key="role.id" :label="role.id">
            {{ role.roleName }}
          </el-checkbox>
        </el-checkbox-group>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="doAssign">提交</el-button>
        <el-button @click="dialogRoleVisible = false">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

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
import { onMounted, ref } from 'vue'
import {
  DeleteSysUser,
  DoAssignRoleToUser,
  GetSysUserListByPage,
  SaveSysUser,
  UpdateSysUser,
} from '@/api/sysUser'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useApp } from '@/pinia/modules/app'
import { GetAllRoleList } from '@/api/sysRole'

// 表格数据模型
const list = ref([])

// 分页总记录数
const total = ref(0)

// 定义分页条件模型
const defaultPageForm = {
  page: 1,
  limit: 10,
}
const pageParam = ref(defaultPageForm)
// 定义分页条件
const queryDto = ref({
  keyword: '',
  createTimeBegin: '',
  createTimeEnd: '',
})
// 定义获取选择时间条件
const createTimes = ref([])

// 页面挂载后查询列表数据
onMounted(() => {
  fetchData()
})

// 分页条件查询用户列表
const fetchData = async () => {
  // 判断时间
  if (createTimes.value.length == 2) {
    queryDto.value.createTimeBegin = createTimes.value[0]
    queryDto.value.createTimeEnd = createTimes.value[1]
  }
  const { code, message, data } = await GetSysUserListByPage(
    pageParam.value.page,
    pageParam.value.limit,
    queryDto.value
  )
  // 赋值数据回显
  list.value = data.list
  total.value = data.total
}

// 分配角色===========================
// 定义角色列表模型
const allRoles = ref([])
// 定义控制分配角色的变量
const dialogRoleVisible = ref(false)
// 定义选择角色id的数据模型
const userRoleIds = ref([])
//定义显示对话框事件方法
const showAssignRole = async row => {
  //查询所有角色和用户拥有的角色Id集合
  const { code, message, data } = await GetAllRoleList(row.id)
  // 获取所有的角色
  allRoles.value = data.allRoles
  // 获取当前用户拥有的角色Id
  userRoleIds.value = data.roleIds
  // 赋值分配用户信息
  sysUser.value = row
  dialogRoleVisible.value = true
}
// 定义保存分配角色事件方法
const doAssign = async () => {
  // 封装保存数据对象
  let assignRoleVo = {
    userId: sysUser.value.id,
    roleIdList: userRoleIds.value,
  }
  // 保存用户分配的角色
  const { code, message } = await DoAssignRoleToUser(assignRoleVo)
  if (code == 200) {
    // 提示
    ElMessage.success('分配成功')
    // 关闭对话框
    dialogRoleVisible.value = false
  }
}

// 头像上传处理========================
// 定义头信息数据
const headers = ref({
  token: useApp().authorization.token,
})
// 上传成功的回调方法
const handleAvatarSuccess = response => {
  console.log(response.data)
  sysUser.value.avatar = response.data
}

// 用户删除============================
// 定义删除事件方法
const deleteById = userId => {
  // 确认提示框
  ElMessageBox.confirm('是否确认删除这个角色', 'Warning', {
    confirmButtonText: '确认删除',
    cancelButtonText: '取消删除',
    type: 'warning',
  })
    .then(async () => {
      // 请求保存api
      const { code, data, message } = await DeleteSysUser(userId)
      // 判断
      if (code == 200) {
        ElMessage.success('删除成功')
        // 刷新页面
        fetchData()
      }
    })
    .catch(() => {
      ElMessage.info('取消删除')
    })
}

// 用户修改============================
// 修改回显
const editShow = row => {
  // 赋值回显数据
  sysUser.value = row
  // 显示对话框
  dialogVisible.value = true
}
// 定义修改保存
const updateData = async () => {
  const { code, message, data } = await UpdateSysUser(sysUser.value)
  if (code == 200) {
    // 提示
    ElMessage.success('修改成功')
    // 关闭对话框
    dialogVisible.value = false
    // 刷新
    fetchData()
  } else {
    ElMessage.error(message)
  }
}

// 用户添加================================
// 定义控制对话框的变量
const dialogVisible = ref(false)
// 定义新增显示方法
const addShow = () => {
  sysUser.value = {}
  dialogVisible.value = true
}
// 添加取消事件
const hideDialogVisible = () => {
  dialogVisible.value = false
  fetchData()
}
// 定义用户数据模型
const defaultUserForm = {
  id: '',
  userName: '',
  password: '',
  phone: '',
  avatar: '',
  description: '',
  status: '',
}
const sysUser = ref(defaultUserForm)
const saveData = async () => {
  const { code, message, data } = await SaveSysUser(sysUser.value)
  //判断
  if (code == 200) {
    // 提示
    ElMessage.success('新增成功')
    // 关闭
    dialogVisible.value = false
    // 刷新
    fetchData()
  } else {
    ElMessage.error(message)
  }
}
// 定义保存方法
const submit = async () => {
  if (!sysUser.value.id) {
    saveData()
  } else {
    updateData()
  }
}

// 用户查询================================

// 定义搜索方法
const searchSysUser = () => {
  fetchData()
}

// 定义重置方法
const resetData = () => {
  // 清除条件
  queryDto.value = {}
  // 时间
  createTimes.value = []
  fetchData()
}

// 定义页面条数改变事件
const handleSizeChange = () => {
  fetchData()
}

// 定义当前页改变事件
const handleCurrentChange = () => {
  fetchData()
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
<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
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
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>