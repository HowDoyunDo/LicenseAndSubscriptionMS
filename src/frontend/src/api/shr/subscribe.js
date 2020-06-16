import axios from 'axios';


const instance = axios.create({
    baseURL: 'http://localhost:9000/'
});

// 시스템관리자 - 전체 구독 목록 조회
function subscribeAllList(){
    return instance.post('subscribeAllLis');
}


export {subscribeAllList};