import request from '@/utils/request'

// 查询定脱管机架组成信息列表
export function listFrameDetail(query) {
  return request({ url: '/tmzm/device/frameDetail/list', method: 'get', params: query })
}

// 查询定脱管机架组成信息详细
export function getFrameDetail(recId) {
  return request({ url: '/tmzm/device/frameDetail/' + recId, method: 'get' })
}

// 新增定脱管机架组成信息
export function addFrameDetail(data) {
  return request({ url: '/tmzm/device/frameDetail', method: 'post', data })
}

// 修改定脱管机架组成信息
export function updateFrameDetail(data) {
  return request({ url: '/tmzm/device/frameDetail', method: 'put', data })
}

// 删除定脱管机架组成信息
export function delFrameDetail(recIds) {
  return request({ url: '/tmzm/device/frameDetail/' + recIds, method: 'delete' })
}
