import { createRouter, createWebHistory } from 'vue-router'

const Layout = () => import('@/layout/index.vue')

const routes = [
  {
    path: '/login',
    component: () => import('@/views/login.vue'),
    hidden: true,
    meta: { title: '登录' }
  },
  {
    path: '/404',
    component: () => import('@/views/404.vue'),
    hidden: true,
    meta: { title: '404' }
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/index.vue'),
        name: 'Index',
        meta: { title: '首页', icon: 'HomeFilled', affix: true }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  }
})

export default router
