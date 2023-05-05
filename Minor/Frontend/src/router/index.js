import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/Home',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/Dashboard',
    name: 'Dashboard',
    component: () => import('../views/Dashboard.vue')
  },
  {
    path: '/Record',
    name: 'Record',
    component: () => import('../views/Record.vue')
  },
  {
    path: '/Archive',
    name: 'Archive',
    component: () => import('../views/Archive.vue')
  },
  {
    path: '/Login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/Register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/Statistics',
    name: 'Statistics',
    component: () => import('../views/Statistics.vue')
  }
  
]

const router = new VueRouter({
  routes
})

export default router