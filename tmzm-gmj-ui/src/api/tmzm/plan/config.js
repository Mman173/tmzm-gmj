import request from '@/utils/request'

// 查询生产规格配置列表
export function listConfig(query) {
  return request({ url: '/tmzm/plan/config/list', method: 'get', params: query })
}

// 查询生产规格配置详细
export function getConfig(recId) {
  return request({ url: '/tmzm/plan/config/' + recId, method: 'get' })
}

// 新增生产规格配置
export function addConfig(data) {
  return request({ url: '/tmzm/plan/config', method: 'post', data })
}

// 修改生产规格配置
export function updateConfig(data) {
  return request({ url: '/tmzm/plan/config', method: 'put', data })
}

// 删除生产规格配置
export function delConfig(recIds) {
  return request({ url: '/tmzm/plan/config/' + recIds, method: 'delete' })
}
