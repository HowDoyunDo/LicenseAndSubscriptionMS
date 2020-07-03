import { instance } from '../index';

// 시스템관리자 - 전체 구독 목록 조회
function subscribeAllList() {
    return instance.get('alllist');
}

// 시스템관리자 - 구독 정책 등록
function subscribeAdd(addData) {
    return instance.post('subscribeAdd', addData);
}

// 시스템관리자 - 구독 정책 상세보기, 해당구독정책의 제품목록도 포함되어있음, subscribeNo:구독정책번호
function subscribeOneList(subscribeNo) {
    return instance.post('subscribeOneList', subscribeNo);
}

// 시스템관리자 - 구독 정책 수정(일부 or 전체가능)
function subscribeModify(subModifyData) {

    return instance.post('subscribeModify', subModifyData);
}

// 시스템관리자 - 구독 정책 수정 시 라이선스 발급 여부 체크 
function licenseCheck(subscribeNo) {
    return instance.post('licenseCheck', { policy_no: subscribeNo });
}

// 고객관리자 - 로그인한 유저의 구독 목록
function userSubscribeList(userInfo){
    return instance.post('userSubscribeList',userInfo);
}

export { subscribeAllList, subscribeAdd, subscribeOneList, subscribeModify, licenseCheck, 
         userSubscribeList };