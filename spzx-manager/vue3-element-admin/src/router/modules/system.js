/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-24 19:27:21
 * @Author: huzhushan@126.com
 * @HomePage: https://huzhushan.gitee.io/vue3-element-admin
 * @Github: https://github.com/huzhushan/vue3-element-admin
 * @Donate: https://huzhushan.gitee.io/vue3-element-admin/donate/
 */
// home.js
const Layout = () => import('@/layout/index.vue')
const sysUser = () => import('@/views/system/sysUser.vue')
const sysRole = () => import('@/views/system/sysRole.vue')
const sysMenu = () => import('@/views/system/sysMenu.vue')
const sysOperLog = () => import('@/views/system/sysOperLog.vue')

export default [
  {
    path: '/system',
    component: Layout,
    name: 'system',
    meta: {
      title: '系统管理',
    },
    icon: 'HomeFilled',
    children: [
      {
        path: '/sysUser',
        name: 'sysUser',
        component: sysUser,
        meta: {
          title: '用户管理',
          affix: true,
        },
      },
      {
        path: '/sysRole',
        name: 'sysRole',
        component: sysRole,
        meta: {
          title: '角色管理',
          affix: true,
        },
      },
      {
        path: '/sysMenu',
        name: 'sysMenu',
        component: sysMenu,
        meta: {
          title: '菜单管理',
          affix: true,
        },
      },
      {
        path: '/sysOperLog',
        name: 'sysOperLog',
        component: sysOperLog,
        meta: {
          title: '操作日志',
          affix: true,
        },
      },
    ],
  },
]
