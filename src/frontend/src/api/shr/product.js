import { instance } from '../index';

// 시스템관리자 - 전체 제품 조회
function productAllList(){
    return instance.get('productAllList');
}

// 시스템관리자 - 해당 제품번호 조회
function productOneList(productNo) {
    return instance.post('productList', productNo);
}

// 시스템관리자 - 해당 제품번호 삭제
// function productDelete(productNo){
//     return instance.post('productDelete', productNo);
// }



export { productOneList, productAllList };