import request from '@/utils/request'

// 查询连轧机架/辊列表
export function listRolling(query) {
  return request({ url: '/tmzm/device/rolling/list', method: 'get', params: query })
}

// 查询连轧机架/辊详细
export function getRolling(recId) {
  return request({ url: '/tmzm/device/rolling/' + recId, method: 'get' })
}

// 新增连轧机架/辊
export function insertRolling(data) {
  return request({ url: '/tmzm/device/rolling', method: 'post', data })
}

// 修改连轧机架/辊
export function updateRolling(data) {
  return request({ url: '/tmzm/device/rolling', method: 'put', data })
}

// 报废连轧机架/辊
export function scrapRolling(recIds) {
  return request({ url: '/tmzm/device/rolling/scrap/' + recIds, method: 'put' })
}

// 上线连轧机架/辊
export function onlineRolling(recIds) {
  return request({ url: '/tmzm/device/rolling/online/' + recIds, method: 'put' })
}

// 下线连轧机架/辊
export function offlineRolling(recIds) {
  return request({ url: '/tmzm/device/rolling/offline/' + recIds, method: 'put' })
}
