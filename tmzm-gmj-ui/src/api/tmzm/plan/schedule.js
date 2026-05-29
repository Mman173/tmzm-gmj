import request from '@/utils/request'

// 查询计划排程列表
export function listSchedule(query) {
  return request({ url: '/tmzm/plan/schedule/list', method: 'get', params: query })
}

// 查询计划排程详细
export function getSchedule(recId) {
  return request({ url: '/tmzm/plan/schedule/' + recId, method: 'get' })
}

// 新增计划排程
export function addSchedule(data) {
  return request({ url: '/tmzm/plan/schedule', method: 'post', data })
}

// 修改计划排程
export function updateSchedule(data) {
  return request({ url: '/tmzm/plan/schedule', method: 'put', data })
}

// 删除计划排程
export function delSchedule(recIds) {
  return request({ url: '/tmzm/plan/schedule/' + recIds, method: 'delete' })
}

// 确认计划排程
export function confirmSchedule(recIds) {
  return request({ url: '/tmzm/plan/schedule/confirm/' + recIds, method: 'put' })
}

// 取消计划排程
export function cancelSchedule(recIds) {
  return request({ url: '/tmzm/plan/schedule/cancel/' + recIds, method: 'put' })
}

// 完成计划排程
export function completeSchedule(recIds) {
  return request({ url: '/tmzm/plan/schedule/complete/' + recIds, method: 'put' })
}
