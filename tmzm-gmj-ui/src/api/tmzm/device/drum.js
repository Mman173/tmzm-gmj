import request from '@/utils/request'

// 查询转鼓列表
export function listDrum(query) {
  return request({ url: '/tmzm/device/drum/list', method: 'get', params: query })
}

// 查询转鼓详细
export function getDrum(recId) {
  return request({ url: '/tmzm/device/drum/' + recId, method: 'get' })
}

// 新增转鼓
export function insertDrum(data) {
  return request({ url: '/tmzm/device/drum', method: 'post', data })
}

// 修改转鼓
export function updateDrum(data) {
  return request({ url: '/tmzm/device/drum', method: 'put', data })
}

// 报废转鼓
export function scrapDrum(recIds) {
  return request({ url: '/tmzm/device/drum/scrap/' + recIds, method: 'put' })
}

// 上线转鼓
export function onlineDrum(recIds) {
  return request({ url: '/tmzm/device/drum/online/' + recIds, method: 'put' })
}

// 下线转鼓
export function offlineDrum(recIds) {
  return request({ url: '/tmzm/device/drum/offline/' + recIds, method: 'put' })
}
