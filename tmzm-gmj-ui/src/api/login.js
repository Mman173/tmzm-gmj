import request from '@/utils/request'

// 登录
export function login(username, password, code, uuid) {
  const data = { username, password, code, uuid }
  return request({ url: '/login', method: 'post', data })
}

// 获取用户信息
export function getInfo() {
  return request({ url: '/getInfo', method: 'get' })
}

// 获取路由
export function getRouters() {
  return request({ url: '/getRouters', method: 'get' })
}

// 退出登录
export function logout() {
  return request({ url: '/logout', method: 'post' })
}
