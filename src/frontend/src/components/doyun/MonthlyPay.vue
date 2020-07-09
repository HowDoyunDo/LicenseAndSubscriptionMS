<template>
    <div id="monthlyPay">
        <form>
            <div>
                <label for="card_number">카드 번호 XXXX-XXXX-XXXX-XXXX</label>
                <input id="card_number" type="text" name="card_number" v-model="cd">
            </div>
            <div>
                <label for="expiry">카드 유효기간 YYYY-MM</label>
                <input id="expiry" type="text" name="expiry" v-model="exp">
            </div>
            <div>
                <label for="birth">생년월일 YYMMDD</label>
                <input id="birth" type="text" name="birth" v-model="birth">
            </div>
            <div>
                <label for="pwd_2digit">카드 비밀번호 앞 두자리 XX</label>
                <input id="pwd_2digit" type="text" name="pwd_2digit" v-model="pw">
            </div>
            <input hidden type="text" v-model="cuid" name="customer_uid">
            <button @click="monthlyPay()">결제</button>
        </form>
            <!-- <form action="/api/subscription/issue-billing" method="post">
                <div>
                    <label for="card_number">카드 번호 XXXX-XXXX-XXXX-XXXX</label>
                    <input id="card_number" type="text" name="card_number">
                </div>
                <div>
                    <label for="expiry">카드 유효기간 YYYY-MM</label>
                    <input id="expiry" type="text" name="expiry">
                </div>
                <div>
                    <label for="birth">생년월일 YYMMDD</label>
                    <input id="birth" type="text" name="birth">
                </div>
                <div>
                    <label for="pwd_2digit">카드 비밀번호 앞 두자리 XX</label>
                    <input id="pwd_2digit" type="text" name="pwd_2digit">
                </div>
                <input hidden type="text" value="gildong_0001_1234" name="customer_uid">
                <input type="submit" value="결제하기">
            </form> -->
    </div>
</template>

<script>
import axios from 'axios';
axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";

export default {
    data() {
        return {
            // 카드 번호
            cd:'',

            // 카드 유효기간
            exp:'',

            // 생년월일
            birth: '',

            // 카드 비밀번호 앞 두자리
            pw:'',

            // 카드 별명
            cuid:'gildong_0001_1234',

            // 엑세스 토큰
            access_token: '',
            // 빌링키
            issueBilling: ''
        }
    },
    methods: {
        async monthlyPay() {

                try {
                    // 정기 결제 구현 중 중단
                    
                    // await axios({
                    //     url: "/api/subscription/issue-billing",
                    //     method: "post", // POST method
                    //     headers: { 
                    //         "Content-Type": "application/json",
                    //         "Access-Control-Allow-Origin": "*"
                    //     }, // "Content-Type": "application/json"
                    // }).then(res => { 
                    //     console.log(res.data);
                    // });
                    axios.post('https://api.iamport.kr/users/getToken', {
                        headers: { 
                            "Content-Type": "application/json"
                        },
                        data: {
                            imp_key: "imp_apikey", // REST API키
                            imp_secret: "ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f" // REST API Secret
                        }
                    }).then(res => { 
                        console.log(res.data);
                    });

                    // 인증 토큰 발급 받기
                    // const getToken = 
                    // await axios({
                    //     url: "https://api.iamport.kr/users/getToken",
                    //     method: "post", // POST method
                    //     headers: { 
                    //         "Content-Type": "application/json",
                    //         "Access-Control-Allow-Origin": "*"
                    //     }, // "Content-Type": "application/json"
                    //     data: {
                    //         imp_key: "9040290319981277", // REST API키
                    //         imp_secret: "B3akhjZenpgznkUteRin509LIU4OOUKqYVKgeKuB9Kaw550jWjG2kQO2LSRANB0MwL2KvwmFhwbmCcGz" // REST API Secret
                    //     }
                    // }).then(res => { 
                    //     console.log(res.data);
                    // });
                    // const { access_token } = getToken.data.response; // 인증 토큰

                    // // 빌링키 발급 요청
                    // await axios({
                    //     url: `https://api.iamport.kr/subscribe/customers/${this.cuid}`,
                    //     method: "post",
                    //     headers: { "Authorization": access_token }, // 인증 토큰 Authorization header에 추가
                    //     data: {
                    //         card_number : this.cd, // 카드 번호
                    //         expiry : this.exp, // 카드 유효기간
                    //         birth : this.birth, // 생년월일
                    //         pwd_2digit: this.pw, // 카드 비밀번호 앞 두자리
                    //     }
                    // });

                    // if (code === 0) { // 빌링키 발급 성공
                    //     alert("Billing has successfully issued");
                    // } else { // 빌링키 발급 실패
                    //     alert( "failed");
                    // }
                } catch (e) {
                    console.log('error catch');
                }

        }
    }
}
</script>

<style>
    .monthlyPay {
        text-align: center;
    }
</style>