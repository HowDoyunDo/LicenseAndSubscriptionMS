import { instance } from '../index';

// 시스템관리자 - 전체 구독 목록 조회
function subscribeAllList() {
    return instance.get('alllist');
}


// 시스템관리자 - 구독 정책 등록
function subscribeAdd(addData) {
    console.log('adddddddd data ')
    console.log(addData.selectedList);
    
    return instance.post('/subscribeAdd',addData);
}



export { subscribeAllList, subscribeAdd };