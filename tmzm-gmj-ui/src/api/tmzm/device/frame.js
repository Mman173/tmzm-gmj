import request from '@/utils/request'

// 查询定脱管机架列表
export function listFrame(query) {
  return request({ url: '/tmzm/device/frame/list', method: 'get', params: query })
}

// 查询定脱管机架详细
export function getFrame(recId) {
  return request({ url: '/tmzm/device/frame/' + recId, method: 'get' })
}

// 新增定脱管机架
export function insertFrame(data) {
  return request({ url: '/tmzm/device/frame', method: 'post', data })
}

// 修改定脱管机架
export function updateFrame(data) {
  return request({ url: '/tmzm/device/frame', method: 'put', data })
}

// 报废定脱管机架
export function scrapFrame(recIds) {
  return request({ url: '/tmzm/device/frame/scrap/' + recIds, method: 'put' })
}

// 查询定脱管机架历史
export function historyFrame(recId) {
  return request({ url: '/tmzm/device/frame/history/' + recId, method: 'get' })
}

// 变更目的地
export function changeDestination(data) {
  return request({ url: '/tmzm/device/frame/changeDestination', method: 'put', data })
}
