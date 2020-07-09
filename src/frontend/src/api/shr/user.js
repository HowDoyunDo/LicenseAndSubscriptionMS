import { instance } from '../index';

// 고객 관리자 - 비밀번호 체크
function userPwCheck(userData){
    return instance.post('userPwCheck',userData);
}


export { userPwCheck };