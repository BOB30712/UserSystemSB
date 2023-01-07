import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '../views/LogIn.vue')
  },
  {
    path: '/userpage',
    name: 'userpage',
    component: () => import(/* webpackChunkName: "userpage" */ '../views/UserPage.vue')
  },
  {
    path: '/registerpage',
    name: 'registerpage',
    component: () => import('../views/RegisterPage.vue')
  },
  {
    path: '/forgotpassword',
    name: 'forgotpassword',
    component: () => import('../views/ForgotPassword.vue')
  },
  {
    path: '/axiospage',
    name: 'axiospage',
    component: () => import(/* webpackChunkName: "userpage" */ '../views/AxiosPage.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
