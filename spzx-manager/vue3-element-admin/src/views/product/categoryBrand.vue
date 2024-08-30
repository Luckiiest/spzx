<template>
  <!-- 搜索表单 -->
  <div class="search-div">
    <el-form label-width="70px" size="normal">
      <el-row>
        <el-col :span="12">
          <el-form-item label="品牌">
            <el-select
              class="m-2"
              placeholder="选择品牌"
              size="normal"
              style="width: 100%"
              v-model="queryDto.brandId"
            >
              <el-option
                v-for="item in brandList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类">
            <el-cascader
              :props="categoryProps"
              style="width: 100%"
              v-model="searchCategoryIdList"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="display: flex">
        <el-button type="primary" size="normal" @click="fetchData()">
          搜索
        </el-button>
        <el-button size="normal" @click="resetData()">重置</el-button>
      </el-row>
    </el-form>
  </div>

  <!-- 添加按钮 -->
  <div class="tools-div">
    <el-button type="success" size="normal" @click="addShow()">添 加</el-button>
  </div>
  <!-- 添加对话框 -->
  <el-dialog v-model="dialogVisible" title="添加或修改" width="30%">
    <el-form label-width="120px">
      <el-form-item label="品牌">
        <el-select
          class="m-2"
          placeholder="选择品牌"
          size="small"
          v-model="categoryBrand.brandId"
        >
          <el-option
            v-for="item in brandList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="分类">
        <el-cascader
          :props="categoryProps"
          v-model="categoryBrand.categoryId"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">提交</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <!-- 数据展示列表 -->
  <el-table :data="list" style="width: 100%">
    <el-table-column prop="categoryName" label="分类" />
    <el-table-column prop="brandName" label="品牌" />
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

  <!-- 分页组件 -->
  <el-pagination
    v-model:current-page="pageParams.page"
    v-model:page-size="pageParams.limit"
    :page-sizes="[5, 10, 20, 50, 100]"
    layout="total, sizes, prev, pager, next"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { GetBrandList } from '@/api/brand'
import {
  DeleteCategoryBrandById,
  GetCategoryBrand,
  SaveCategoryBrand,
  UpdateCategoryBrandById,
} from '@/api/categoryBrand'
import { FindCategoryByParentId } from '@/api/category'
import { ElMessage, ElMessageBox } from 'element-plus'
// ================数据模型定义  start ===============================================
// 定义搜索表单数据模型
const brandList = ref([
  //   {
  //     id: 2,
  //     createTime: '2023-05-06 09:31:19',
  //     name: '华为',
  //     logo: 'http://139.198.127.41:9000/sph/20230506/华为.png',
  //   },
])

// 页面加载后获取品牌列表数据
onMounted(async () => {
  // 查询所有品牌
  findAllBrand()
  // 页面加载查询
  fetchData()
})

// 查询所有品牌
const findAllBrand = async () => {
  // 请求品牌数据
  const { data } = await GetBrandList()
  // 赋值回显
  brandList.value = data
}

const props = {
  lazy: true,
  value: 'id',
  label: 'name',
  leaf: 'leaf',
  async lazyLoad(node, resolve) {
    // 判断node节点中是否有id
    if (typeof node.value == 'undefined') node.value = 0
    const { data } = await FindCategoryByParentId(node.value)
    // 判断是否有下一级分类标识
    // 设置是否有下一级,赋值leaf属性,出现>
    data.forEach(item => (item.leaf = !item.hasChildren))

    // 加载数据的方法
    // const data = [
    //   {
    //     id: 643,
    //     createTime: '2023-05-22 15:31:18',
    //     name: '玩具乐器',
    //     imageUrl:
    //       'https://lilishop-oss.oss-cn-beijing.aliyuncs.com/0f423fb60f084b2caade164fae25a9a0.png',
    //     parentId: 0,
    //     status: 1,
    //     orderNum: 10,
    //     hasChildren: true,
    //     children: null,
    //   },
    // ]
    resolve(data) // 返回数据
  },
}
const categoryProps = ref(props)

// 定义表格数据模型
const list = ref([
  // {
  //   id: 2,
  //   createTime: '2023-05-24 15:19:24',
  //   brandId: 1,
  //   categoryId: 99,
  //   categoryName: '定制服务',
  //   brandName: '小米',
  //   logo: 'http://139.198.127.41:9000/sph/20230506/小米.png',
  // },
])

// 分页条数据模型
const total = ref(0)

// 定义条件数据模型
const defaultFromParam = {
  id: '',
  brandId: '',
  categoryId: '',
}

// 定义显示添加对话框变量
const dialogVisible = ref(false)
// 定义新增表单数据模型
const categoryBrand = ref(defaultFromParam)

// 定义分页数据模型
const defaultPageFormParams = {
  page: 1,
  limit: 5,
}
//======数据模型定义end========================================================

// 删除处理
const remove = async id => {
  ElMessageBox.confirm('此操作将永久删除该记录, 是否继续?', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      await DeleteCategoryBrandById(id)
      ElMessage.success('删除成功')
      fetchData()
    })
    .catch(() => {
      ElMessage.info('取消删除')
    })
}

// 修改回显
const editShow = row => {
  // 赋值回显
  categoryBrand.value = row
  // 显示对话框
  dialogVisible.value = true
}
// 修改方法实现
const updateData = async () => {
  // 判断是否选择了分类
  if (categoryBrand.value.categoryId.length != 3) {
    ElMessage.info('请选择分类选项')
    return
  }
  categoryBrand.value.categoryId = categoryBrand.value.categoryId[2]

  const { code, message } = await UpdateCategoryBrandById(categoryBrand.value)
  if (code == 200) {
    dialogVisible.value = false
    ElMessage.success('操作成功')
    fetchData()
  } else {
    ElMessage.warning(message)
  }
}

// 添加处理
// 定义显示对话框方法
const addShow = () => {
  categoryBrand.value = {}
  dialogVisible.value = true
}
// 新增方法
const saveData = async () => {
  // 判断是否选择了分类
  if (categoryBrand.value.categoryId.length != 3) {
    ElMessage.info('请选择分类选项')
    return
  }
  categoryBrand.value.categoryId = categoryBrand.value.categoryId[2]
  // 判断是否选择了品牌
  if (!categoryBrand.value.brandId) {
    ElMessage.info('请选择品牌选项')
    return
  }

  // 保存新增方法
  const { code, message } = await SaveCategoryBrand(categoryBrand.value)
  if (code == 200) {
    ElMessage.success('新增成功')
    dialogVisible.value = false
    fetchData()
  } else {
    ElMessage.error(message)
  }
}
// 添加提交方法
const saveOrUpdate = () => {
  // 判断是否存在id
  if (!categoryBrand.value.id) {
    saveData()
  } else {
    updateData()
  }
}

// 分页处理
// 分页处理
const handleSizeChange = () => {
  // 刷新页面
  fetchData()
}
const handleCurrentChange = () => {
  // 刷新页面
  fetchData()
}

// 定义分页请求条件
const pageParams = ref(defaultPageFormParams)
// 定义请求条件
const queryDto = ref(defaultFromParam)
// 定义分类id接收的变量
const searchCategoryIdList = ref([])

// 请求分类品牌列表数据
const fetchData = async () => {
  // 判断分类是否选择了
  if (searchCategoryIdList.value.length == 3) {
    queryDto.value.categoryId = searchCategoryIdList.value[2]
  }
  const { code, data } = await GetCategoryBrand(
    pageParams.value.page,
    pageParams.value.limit,
    queryDto.value
  )
  if (code == 200) {
    list.value = data.list
    total.value = data.total
  }
}

// 重置功能
const resetData = () => {
  queryDto.value = {}
  searchCategoryIdList.value = []
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