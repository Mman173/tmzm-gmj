import request from '@/utils/request'

// 查询装配信息列表
export function listFrameAssembly(query) {
  return request({ url: '/tmzm/device/frameAssembly/list', method: 'get', params: query })
}

// 查询装配信息详细
export function getFrameAssembly(recId) {
  return request({ url: '/tmzm/device/frameAssembly/' + recId, method: 'get' })
}

// 新增装配信息
export function addFrameAssembly(data) {
  return request({ url: '/tmzm/device/frameAssembly', method: 'post', data })
}

// 修改装配信息
export function updateFrameAssembly(data) {
  return request({ url: '/tmzm/device/frameAssembly', method: 'put', data })
}

// 删除装配信息
export function delFrameAssembly(recIds) {
  return request({ url: '/tmzm/device/frameAssembly/' + recIds, method: 'delete' })
}
