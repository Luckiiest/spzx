<template>
  <div class="search-div">
    <!-- 搜索表单 -->
    <el-form label-width="70px" size="small">
      <el-form-item label="角色名称">
        <el-input
          style="width: 100%"
          class="search-input"
          placeholder="角色名称"
          v-model="queryDto.roleName"
        ></el-input>
      </el-form-item>
      <el-row style="display: flex">
        <el-button type="primary" size="normal" @click="searchSysRole">
          搜索
        </el-button>
        <el-button size="normal" @click="resetData">重置</el-button>
      </el-row>
    </el-form>

    <!-- 添加按钮 -->
    <div class="tools-div">
      <el-button type="success" size="normal" @click="addShow">添 加</el-button>
      <el-button
        size="normal"
        type="danger"
        @click="handlerMuchDel"
        v-if="mutipleSelection.length"
      >
        批量删除
      </el-button>
    </div>

    <!-- 添加表单 -->
    <el-dialog v-model="dialogVisible" title="添加或修改角色" width="30%">
      <el-form label-width="120px">
        <el-form-item label="角色名称">
          <el-input v-model="sysRole.roleName" />
        </el-form-item>
        <el-form-item label="角色Code">
          <el-input v-model="sysRole.roleCode" />
        </el-form-item>
        <el-form-item label="角色描述">
          <el-input v-model="sysRole.description" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!--- 角色表格数据 -->
    <el-table
      :data="list"
      @selection-change="handleSelectionChange"
      style="width: 100%; font-size: 15px"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="roleName" label="角色名称" width="180" />
      <el-table-column prop="roleCode" label="角色code" width="180" />
      <el-table-column prop="description" label="角色描述" width="180" />
      <el-table-column prop="createTime" label="创建时间" />
      <el-button type="primary" @click="doAssign">提交</el-button>

      <el-table-column label="操作" align="center" width="280" #default="scope">
        <el-button type="primary" size="normal" @click="editShow(scope.row)">
          修改
        </el-button>
        <el-button type="danger" size="normal" @click="deleteById(scope.row)">
          删除
        </el-button>
        <el-button
          type="warning"
          size="normal"
          @click="showAssignMenu(scope.row)"
        >
          分配菜单
        </el-button>
      </el-table-column>
    </el-table>

    <!-- 分配菜单的对话框 
    // tree组件添加ref属性，后期方便进行tree组件对象的获取
    -->
    <el-dialog v-model="dialogMenuVisible" title="分配菜单" width="40%">
      <el-form label-width="80px">
        <el-tree
          :data="sysMenuTreeList"
          ref="tree"
          show-checkbox
          default-expand-all
          :check-on-click-node="true"
          node-key="id"
          :props="defaultProps"
        />
        <el-form-item>
          <el-button type="primary" @click="doAssign">提交</el-button>
          <el-button @click="dialogMenuVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!--分页条-->
    <el-pagination
      v-model:current-page="pageParams.page"
      v-model:page-size="pageParams.limit"
      :page-sizes="[2, 5, 10, 20, 50, 100]"
      :total="total"
      layout="total, sizes, prev, pager, next"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script setup>
import { h, onMounted, ref } from 'vue'
import {
  DeleteSysRole,
  DoAssignMenuIdToSysRole,
  GetSysRoleListByPage,
  GetSysRoleMenuIds,
  SaveSysRole,
  UpdateSysRole,
} from '@/api/sysRole'
import { param } from '@/utils'
import { ElMessage, ElMessageBox } from 'element-plus'

// 分配菜单保存====================

// 定义分配角色保存的事件方法
const doAssign = async () => {
  // 获取选中的节点
  const checkedNodes = tree.value.getCheckedNodes()
  // 遍历过滤需要的数据[{id:1},{id:2}]
  const checkedNodesIds = checkedNodes.map(node => {
    return {
      menuId: node.id,
      isHalf: 0,
    }
  })
  // 获取半选中的节点
  const halfCheckedNodes = tree.value.getHalfCheckedNodes()
  // 遍历过滤需要的数据[{id:1},{id:2}]
  const halfCheckedNodesIds = halfCheckedNodes.map(node => {
    return {
      menuId: node.id,
      isHalf: 0,
    }
  })
  // 合并选中和半选中节点
  const menuIdList = [...checkedNodesIds, ...halfCheckedNodesIds]
  // 整合数据
  const assginMenuDto = {
    roleId: roleId.value,
    menuIdList: menuIdList,
  }
  console.log(assginMenuDto)
  // 请求后端保存
  const { code, message, data } = await DoAssignMenuIdToSysRole(assginMenuDto)
  // 判断
  if (code == 200) {
    // 提示
    ElMessage.success('分配菜单成功')
    // 关闭对话框
    dialogMenuVisible.value = false
  }
}
// 定义变量，接收角色Id
const roleId = ref()

// 分配菜单回显=====================
// 定义分配角色对话框的变量
const dialogMenuVisible = ref(false)
// 定义树形结构列表数据模型
const sysMenuTreeList = ref([])
// 定义tree对象
const tree = ref()
// 定义显示的true内容属性
const defaultProps = ref({
  children: 'children',
  label: 'title',
})
// 定义显示回显菜单列表的方法
const showAssignMenu = async row => {
  // 赋值角色Id
  roleId.value = row.id
  // 显示对话框
  dialogMenuVisible.value = true
  // 查询需要回显的数据
  const { code, data } = await GetSysRoleMenuIds(row.id)
  // 赋值树形列表
  sysMenuTreeList.value = data.sysMenusTree
  // 回显选中的菜单Id
  tree.value.setCheckedKeys(data.menuIds)
}

// 批量删除=======================
// 定义空数组，临时存放批量删除数据
const mutipleSelection = ref([])
// 点击多选框事件
const handleSelectionChange = val => {
  mutipleSelection.value = val
}
// 点击批量删除事件
const handlerMuchDel = async () => {
  // 使用 Promise.all 来等待所有异步操作完成
  await Promise.all(
    mutipleSelection.value.map(async row => {
      const id = row.id
      await DeleteSysRole(id)
    })
  )
  // 当所有 DeleteSysRole 函数调用完成后执行 fetchData 函数
  fetchData()
}

// 角色删除=======================
const deleteById = id => {
  // 确认提示框
  ElMessageBox.confirm('是否确认删除这个角色', 'Warning', {
    confirmButtonText: '确认删除',
    cancelButtonText: '取消删除',
    type: 'warning',
  })
    .then(async () => {
      // 请求保存api
      const { code, data, message } = await DeleteSysRole(id)
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

// 角色修改=======================
// 定义显示修改对话框的事件方法
const editShow = row => {
  // 赋值回显数据
  sysRole.value = row
  // 显示修改对话框
  dialogVisible.value = true
}

// 角色新增====修改整合============
// 定义角色数据模型
const defaultForm = {
  id: '',
  roleName: '',
  roleCode: '',
  description: '',
}
const sysRole = ref(defaultForm)
const submit = async () => {
  // 判断数据模型中是否存在id，存在则修改，不存在则新增
  if (!sysRole.value.id) {
    // 请求保存api
    const { code, data, message } = await SaveSysRole(sysRole.value)
    // 保存
    if (code == 200) {
      // 提示成功
      ElMessage.success('角色新增成功')
      // ElMessage({
      //   message: '角色新增成功',
      //   type: 'success',
      // })
      // 隐藏对话框
      dialogVisible.value = false
      // 刷新页面
      fetchData()
      sysRole.value = {}
    }
  } else {
    // 请求修改api
    const { code, data, message } = await UpdateSysRole(sysRole.value)
    // 保存
    if (code == 200) {
      // 提示成功
      ElMessage.success('角色修改成功')
      // 隐藏对话框
      dialogVisible.value = false
      // 刷新页面
      fetchData()
    }
  }
}

// 控制对话框是否展示
const dialogVisible = ref(false)

// 进入添加
const addShow = () => {
  dialogVisible.value = true
}

// 分页查询=======================

// 定义条件模型
const queryDto = ref({
  roleName: '',
})

// 封装当前分页数据
const defaultPageForm = {
  page: 1, // 页码
  limit: 5, // 每页记录数
}

// 设置为响应式数据
const pageParams = ref(defaultPageForm)

// 抽取查询分页列表方法
const fetchData = async () => {
  // 获取列表数据
  const { code, message, data } = await GetSysRoleListByPage(
    pageParams.value.page,
    pageParams.value.limit,
    queryDto.value
  )
  // 判断
  if (code == 200) {
    total.value = data.total
    list.value = data.list
  }
}

// 定义页面条数改变事件
const handleSizeChange = () => {
  fetchData()
}

// 定义当前页改变事件
const handleCurrentChange = () => {
  fetchData()
}

// 定义重置方法
const resetData = () => {
  // 去除条件内容
  queryDto.value = {}
  fetchData()
}

// 定义搜索事件
const searchSysRole = () => {
  fetchData()
}

// 定义页面挂载后发起请求
onMounted(async () => {
  // 获取列表数据
  fetchData()
})

// 分页条总记录数
let total = ref(0)

// 定义表格数据模型
let list = ref([
  // {
  //   id: 9,
  //   roleName: '系统管理员',
  //   roleCode: 'xtgly',
  //   createTime: '2023-07-31',
  // },
  // {
  //   id: 10,
  //   roleName: '商品管理员',
  //   roleCode: 'spgly',
  //   createTime: '2023-07-31',
  // },
])
</script>

<style scoped>
.search-div {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}
::v-deep .el-form-item__label {
  /* color: white; */
  font-size: 14px;
  line-height: 40px;
}
.search-input {
  height: 40px;
}
.tools-div {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}
</style>