const Layout = () => import('@/layout/index.vue')
const category = () => import('@/views/product/category.vue')
const categoryBrand = () => import('@/views/product/categoryBrand.vue')
const brand = () => import('@/views/product/brand.vue')
const productSpec = () => import('@/views/product/productSpec.vue')
const product = () => import('@/views/product/product.vue')
export default [
  {
    path: '/product',
    component: Layout,
    name: 'product',
    meta: {
      title: '商品管理',
    },
    icon: 'Histogram',
    children: [
      {
        path: '/category',
        name: 'category',
        component: category,
        meta: {
          title: '分类管理',
          affix: true,
        },
      },
      {
        path: '/brand',
        name: 'brand',
        component: brand,
        meta: {
          title: '品牌管理',
          affix: true,
        },
      },
      {
        path: '/categoryBrand',
        name: 'categoryBrand',
        component: categoryBrand,
        meta: {
          title: '分类品牌',
          affix: true,
        },
      },
      {
        path: '/productSpec',
        name: 'productSpec',
        component: productSpec,
        meta: {
          title: '商品规格',
          affix: true,
        },
      },
      {
        path: '/product',
        name: 'product',
        component: product,
        meta: {
          title: '商品列表',
          affix: true,
        },
      },
    ],
  },
]
