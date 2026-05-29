import request from '@/utils/request'

// 查询匹配配置列表
export function listMatch(query) {
  return request({ url: '/tmzm/plan/match/list', method: 'get', params: query })
}

// 查询匹配配置详细
export function getMatch(recId) {
  return request({ url: '/tmzm/plan/match/' + recId, method: 'get' })
}

// 新增匹配配置
export function addMatch(data) {
  return request({ url: '/tmzm/plan/match', method: 'post', data })
}

// 修改匹配配置
export function updateMatch(data) {
  return request({ url: '/tmzm/plan/match', method: 'put', data })
}

// 删除匹配配置
export function delMatch(recIds) {
  return request({ url: '/tmzm/plan/match/' + recIds, method: 'delete' })
}
