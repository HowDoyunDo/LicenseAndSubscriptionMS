import { instance } from '../index';

// 시스템 관리자 - 라이선스 전체 목록
function licenseAllList(){
    return instance.post('licenseList');
}

// 고객 관리자 - 해당 라이선스 목록
function licenseOneList(licenseNo){
    return instance.post('licenseOneList',licenseNo);
}

// 고객 관리자 - 구독 취소 신청
function licenseCancel(licenseData){
    return instance.post('licenseCancel', licenseData);
}

export { licenseAllList, licenseOneList, licenseCancel };