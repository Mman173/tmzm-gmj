import request from '@/utils/request'

// 查询加工信息列表
export function listFrameProcessing(query) {
  return request({ url: '/tmzm/device/frameProcessing/list', method: 'get', params: query })
}

// 查询加工信息详细
export function getFrameProcessing(recId) {
  return request({ url: '/tmzm/device/frameProcessing/' + recId, method: 'get' })
}

// 新增加工信息
export function addFrameProcessing(data) {
  return request({ url: '/tmzm/device/frameProcessing', method: 'post', data })
}

// 修改加工信息
export function updateFrameProcessing(data) {
  return request({ url: '/tmzm/device/frameProcessing', method: 'put', data })
}

// 删除加工信息
export function delFrameProcessing(recIds) {
  return request({ url: '/tmzm/device/frameProcessing/' + recIds, method: 'delete' })
}
