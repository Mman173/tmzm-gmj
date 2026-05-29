import request from '@/utils/request'

// 查询穿孔辊列表
export function listPunchroll(query) {
  return request({ url: '/tmzm/device/punchroll/list', method: 'get', params: query })
}

// 查询穿孔辊详细
export function getPunchroll(recId) {
  return request({ url: '/tmzm/device/punchroll/' + recId, method: 'get' })
}

// 新增穿孔辊
export function insertPunchroll(data) {
  return request({ url: '/tmzm/device/punchroll', method: 'post', data })
}

// 修改穿孔辊
export function updatePunchroll(data) {
  return request({ url: '/tmzm/device/punchroll', method: 'put', data })
}

// 报废穿孔辊
export function scrapPunchroll(recIds) {
  return request({ url: '/tmzm/device/punchroll/scrap/' + recIds, method: 'put' })
}

// 上线穿孔辊
export function onlinePunchroll(recIds) {
  return request({ url: '/tmzm/device/punchroll/online/' + recIds, method: 'put' })
}

// 下线穿孔辊
export function offlinePunchroll(recIds) {
  return request({ url: '/tmzm/device/punchroll/offline/' + recIds, method: 'put' })
}
