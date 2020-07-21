import {instance}  from '../index';

// 제품 프로모션 전체 목록
function promotionAllList(){
    return instance.get('promotionAllList');
} 
// 정책 프로모션 전체 목록
function promotionPolicyAllList(){
    return instance.get('promotionPolicyAllList');
} 
// 제품 프로모션 등록
function promotionAdd(promotionAddData){
    return instance.post('promotion-add', promotionAddData)
}
// 정책 프로모션 등록
function promotionPolicyAdd(promotionAddData){
    return instance.post('promotion-policy-add', promotionAddData)
}
// 제품 프로모션 상세보기
function promotionOneList(promotionNo) {
    return instance.post('promotioneOneList', promotionNo);
}
// 정책 프로모션 상세보기
function promotionPolicyOneList(promotionNo) {
    return instance.post('promotionPolicyOneList', promotionNo);
}
// 제품 프로모션 수정
function promotionModify(promotionData){
    return instance.post('promotionModify' , promotionData)
}
// 정책 프로모션 수정
function promotionPolicyModify(promotionData){
    return instance.post('promotionPolicyModify' , promotionData)
}
// 제품 프로모션 삭제
function promotionDelete(promotionNo){
    return instance.post('promotionDelete',promotionNo)
}
// 정책 프로모션 삭제
function promotionPolicyDelete(promotionNo){
    return instance.post('promotionPolicyDelete',promotionNo)
}
// 제품 번호로 프로모션 진행 체크
function promotionCheckPNo(productNoList){
    return instance.post('promotionCheckPNo', productNoList)
}
// 제품 번호로 프로모션 진행 체크
function promotionPolicyCheckPNo(policytNo){
    return instance.post('promotionPolicyCheckPNo', policytNo)
}

export {promotionAllList, promotionAdd, promotionOneList, promotionModify, promotionDelete,
        promotionCheckPNo, promotionPolicyAllList, promotionPolicyOneList, promotionPolicyAdd,
        promotionPolicyModify, promotionPolicyDelete, promotionPolicyCheckPNo} ;