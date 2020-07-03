import {instance}  from '../index';

// 프로모션 전체 목록
function promotionAllList(){
    return instance.get('promotionAllList');
} 

// 프로모션 등록
function promotionAdd(promotionAddData){
    return instance.post('promotion-add', promotionAddData)
}
// 프로모션 상세보기
function promotionOneList(promotionNo) {
    return instance.post('promotioneOneList', promotionNo);
}
// 프로모션 수정
function promotionModify(promotionData){
    return instance.post('promotionModify' , promotionData)
}
// 프로모션 삭제
function promotionDelete(promotionNo){
    return instance.post('promotionDelete',promotionNo)
}
export {promotionAllList, promotionAdd, promotionOneList, promotionModify, promotionDelete} ;