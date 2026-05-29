import request from '@/utils/request'

// 查询转鼓组成信息列表
export function listDrumDetail(query) {
  return request({ url: '/tmzm/device/drumDetail/list', method: 'get', params: query })
}

// 查询转鼓组成信息详细
export function getDrumDetail(recId) {
  return request({ url: '/tmzm/device/drumDetail/' + recId, method: 'get' })
}

// 新增转鼓组成信息
export function addDrumDetail(data) {
  return request({ url: '/tmzm/device/drumDetail', method: 'post', data })
}

// 修改转鼓组成信息
export function updateDrumDetail(data) {
  return request({ url: '/tmzm/device/drumDetail', method: 'put', data })
}

// 删除转鼓组成信息
export function delDrumDetail(recIds) {
  return request({ url: '/tmzm/device/drumDetail/' + recIds, method: 'delete' })
}
