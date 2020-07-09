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
// 정책번호로 프로모션 진행 체크
function promotionCheckPNo(productNoList){
    return instance.post('promotionCheckPNo', productNoList)
}
export {promotionAllList, promotionAdd, promotionOneList, promotionModify, promotionDelete,
        promotionCheckPNo} ;