<template>
  <!-- 添加按钮 -->
  <div class="tools-div">
    <el-button type="success" size="normal" @click="addShow">添 加</el-button>
  </div>

  <!-- 修改或添加对话框 -->
  <el-dialog v-model="dialogVisible" title="添加或修改" width="40%">
    <el-form label-width="120px">
      <el-form-item label="规格名称">
        <el-input v-model="productSpec.specName" />
      </el-form-item>
      <el-form-item>
        <el-button size="default" type="success" @click="addSpec">
          添加新规格
        </el-button>
      </el-form-item>
      <el-form-item
        label=""
        v-for="(item, index) in productSpec.specValue"
        :key="index"
      >
        <el-row>
          <el-col :span="10">
            <el-input
              v-model="item.key"
              placeholder="规格"
              style="width: 90%"
            />
          </el-col>
          <el-col :span="10">
            <el-input
              v-model="item.valueList"
              placeholder="规格值(如:白色,红色)"
              style="width: 90%"
            />
          </el-col>
          <el-col :span="4">
            <el-button size="default" type="danger" @click="delSpec(index)">
              删除
            </el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item align="right">
        <el-button type="primary" @click="saveOrUpdate()">提交</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <!-- 数据列表 -->
  <el-table :data="list" style="width: 100%">
    <el-table-column prop="specName" width="240px" label="规格名称" />
    <el-table-column label="规格值" width="600px" #default="scope">
      <div
        v-for="(item1, index1) in scope.row.specValue"
        :key="index1"
        style="padding: 5px 0; margin: 5px 0; width: 100%"
      >
        {{ item1.key }}：
        <spans
          v-for="(item2, index2) in item1.valueList"
          :key="index2"
          class="div-atrr"
        >
          {{ item2 }}
        </spans>
      </div>
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

  <!-- 分页模块 -->
  <el-pagination
    v-model:current-page="pageParams.page"
    v-model:page-size="pageParams.limit"
    :page-sizes="[10, 20, 50, 100]"
    layout="total, sizes, prev, pager, next"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>

<script setup>
import { onMounted, ref } from 'vue'
import {
  DeleteProductSpecById,
  GetProductSpecPageList,
  SaveProductSpec,
  UpdateProductSpec,
} from '@/api/productSpec'
import { ElMessage, ElMessageBox } from 'element-plus'
import { DeleteBrandById } from '@/api/brand'
// 定义分页数据模型
const defaultPageParam = {
  page: 1,
  limit: 10,
}
const pageParams = ref(defaultPageParam)
// 定义分页总记录数
const total = ref(0)

// 表格数据模型
const list = ref([])

// 定义规格数据模型
const defaultFromParams = {
  id: '',
  specName: '',
  specValue: [
    {
      key: '',
      valueList: '',
    },
  ],
}
const productSpec = ref(defaultFromParams)

// 新增规格处理
// 对话框变量定义
const dialogVisible = ref(false)
const addShow = () => {
  dialogVisible.value = true
}
// 新增规格项方法定义
const addSpec = () => {
  productSpec.value.specValue.push({})
}
// 删除规格项方法定义
const delSpec = index => {
  // splice(arg1,arg2) 移除指定位置数组元素
  // arg1: 开始的索引
  // arg2: 移除数组元素的个数
  productSpec.value.specValue.splice(index, 1)
}
// 新增方法
const saveData = async () => {
  // 克隆数据模型,避免双向绑定的数据受到影响
  const productSpecClone = JSON.parse(JSON.stringify(productSpec.value))
  // 转换规格值的类型和格式
  productSpecClone.specValue.forEach(item => {
    if (typeof item.valueList == 'string') {
      item.valueList = item.valueList.split(',')
    }
  })
  productSpecClone.specValue = JSON.stringify(productSpecClone.specValue)
  // 请求接口
  const { code, message } = await SaveProductSpec(productSpecClone)
  if (code == 200) {
    ElMessage.success('新增商品规格成功')
    // 关闭对话框
    dialogVisible.value = false
    // 刷新数据列表
    fetchData()
  } else {
    ElMessage.error(message)
  }
}
// 修改规格定义
const editShow = row => {
  // 显示对话框
  dialogVisible.value = true
  // 赋值数据回显
  productSpec.value = row
}
// 修改保存方法
const updateData = async () => {
  // 克隆数据模型,避免双向绑定的数据受到影响
  const productSpecClone = JSON.parse(JSON.stringify(productSpec.value))
  // 转换规格值的类型和格式
  productSpecClone.specValue.forEach(item => {
    if (typeof item.valueList == 'string') {
      item.valueList = item.valueList.split(',')
    }
  })
  productSpecClone.specValue = JSON.stringify(productSpecClone.specValue)
  // 请求接口
  const { code, message } = await UpdateProductSpec(productSpecClone)
  if (code == 200) {
    ElMessage.success('修改商品规格成功')
    // 关闭对话框
    dialogVisible.value = false
    // 刷新数据列表
    fetchData()
  } else {
    ElMessage.error(message)
  }
}
// 新增提交方法
const saveOrUpdate = () => {
  // 判断
  if (!productSpec.value.id) {
    saveData()
  } else {
    updateData()
  }
}
// 删除方法
const remove = async id => {
  ElMessageBox.confirm('此操作将永久删除该记录，是否继续?', 'warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(async () => {
    const { code, message } = await DeleteProductSpecById(id)
    if (code == 200) {
      ElMessage.success('删除成功!')
      fetchData()
    } else {
      ElMessage.error(message)
    }
  })
}

// 页面挂载后查询数据
onMounted(() => {
  fetchData()
})

// 查询方法
const fetchData = async () => {
  // 请求查询接口
  const { data } = await GetProductSpecPageList(
    pageParams.value.page,
    pageParams.value.limit
  )
  // 转换数据为数组类型
  data.list.forEach(item => {
    item.specValue = JSON.parse(item.specValue)
  })
  //   赋值列表数据
  list.value = data.list
  //   赋值总记录数
  total.value = data.total
}

// 分页处理方法
const handleCurrentChange = () => {
  fetchData()
}
const handleSizeChange = () => {
  fetchData()
}
</script>


<style scoped>
.tools-div {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}

.div-atrr {
  display: inline-block;
  padding: 2px 10px;
  margin: 0px 10px;
  /* background-color: rgb(234, 255, 250); */
  border: 1px dashed black;
  border-radius: 10px;
}
</style>