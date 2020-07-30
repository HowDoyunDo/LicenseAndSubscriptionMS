<template>
  <div class="contents">
    <h1>구독 신청서 작성</h1>
    <p class="tip">서로 다른 정책의 제품들의 일괄 구매를 원하시는 고객님께서는 견적 문의 부탁드립니다.</p>
    <div class="info-border">
      <table class="info-table">
        <tr>
          <th>정책명</th>
          <td>{{policyList.policy_title}}</td>
        </tr>
        <tr>
          <th>이용 기준</th>
          <td>{{policyList.standard=='A' ? '에이전트' : '사용자'}}</td>
        </tr>
        <tr>
          <th>최대 사용 수</th>
          <td>{{policyList.max_count}}</td>
        </tr>
        <tr>
          <th>이용 개월 수</th>
          <td>
            <div v-if="visible=='t'">
              <input type="number" min="1" id="usemonth" v-model="usemonth" @change="usemonthCheck"/> 개월
            </div>
            <div v-else>{{usemonth}} 개월</div>
            <div style="font-size:12px; color:red;">* 개월 수는 30일로 계산됩니다.</div>
          </td>
        </tr>
        <tr>
          <th>구독 시작일</th>
          <td>{{nowDate}}</td>
        </tr>
        <tr>
          <th>구독 종료일</th>
          <td>{{endDate}}</td>
        </tr>
         <tr v-if="promotion_type=='t'">
          <th>프로모션 적용 <br> [정책] </th>
          <td>
            <template>
              <ul>
                <li
                  v-for="promotion in promotionPolicyList"
                  v-bind:key="promotion.promotion_no"
                  style="margin-bottom:10px"
                >
                  프로모션 명 : {{ promotion.title }}
                  <br />
                  할인률 : {{ promotion.discount }}%
                  <br />정책 정가(할인액) : <span> {{ promotion.policy_price*usemonth |formatPrice}}원</span>
                  <span style="color: red"> (- {{(promotion.discount/100 * promotion.policy_price) *usemonth | formatPrice}}원)</span>
                </li>
              </ul>
              <div v-if="promotionPolicyList==''" style="margin-top: -11px;">적용된 프로모션이 없습니다.</div>
            </template>
          </td>
        </tr>
        <tr v-if="promotion_type=='t'">
          <th>프로모션 적용 <br> [제품] </th>
          <td>
            <template>
              <ul>
                <li
                  v-for="promotion in promotionList"
                  v-bind:key="promotion.no"
                  style="margin-bottom:10px"
                >
                  프로모션 명 : {{ promotion.title }}
                  <br />
                  할인률 : {{ promotion.discount }}%
                  <br />제품 정가(할인액) : <span> {{ promotion.product_price |formatPrice}}원</span>
                  <span style="color: red"> (- {{(promotion.discount/100 * promotion.product_price) | formatPrice}}원)</span>
                </li>
              </ul>
              <div v-if="promotionList==''" style="margin-top: -11px;">적용된 프로모션이 없습니다.</div>
            </template>
          </td>
        </tr>
      </table>
      <hr style="border-top: 1px solid black;" />
      <div
        id="price-title"
        style="width: 80%; text-align:right; font-size:17px; font-weight: normal; float:left"
      >
        <p style="color: gray">정책 정가 :</p>
        <p style="color: red">총 할인 금액 :</p>
        <p v-if="promotionPolicyList!=''" style="color: red; font-size:12px">정책 할인 :</p>
        <p v-if="promotionList!=''" style="color: red;  font-size:12px">제품 할인 :</p>
      </div>
      <div
        id="final-price"
        style="width: 20%; text-align:right; font-size:17px; font-weight: normal; float:left"
      >
        <p style="color: gray">{{policyTotal | formatPrice}} 원</p>
        <p v-if="discountTotal!=0" style="color: red"> {{discountTotal | formatPrice }} 원
        <p v-else-if="discountTotal==0" style="color: red"> 0 원
        </p>
        <p v-if="promotionPolicyList!=''" style="color: red; font-size:12px">-  {{ discountTotal_policy | formatPrice }} 원</p>
        <p v-if="promotionList!=''" style="color: red; font-size:12px">-  {{ discountTotal_product | formatPrice }} 원</p>
      </div>
      <br />
      <br />
      <hr style="border-top: 1px solid gray;" />
      <div
        id="price-title"
        style="width: 80%; text-align:right; font-size:18px; font-weight: normal; float:left"
      >
        <p style="color: black">결제 금액 :</p>
      </div>
      <div
        id="final-price"
        style="width: 20%; text-align:right; font-size:18px; font-weight: normal; float:left"
      >{{ policyTotal - discountTotal_policy| formatPrice }} 원</div>
      <br />
      <br />
   <br><br>


     <hr style="border-top: 1px solid black;">
                <div class="selPayM" align="center">
                    <h4 style="font-weight: normal">결제 방식 선택</h4>
                    <button id="cards" @click="selPayM('cards')"
                        :style="{background: cardsCol}">
                        신용카드
                    </button>
                    <button id="trans" @click="selPayM('trans')"
                        :style="{background: transCol}">
                        실시간계좌이체
                    </button>
                </div>
                <hr style="border-top: 1px solid gray;">
                <br>
                <p align="center" style="font-size:17px"><input type="checkbox" v-model="chk"/> 다음 결제정보에 대해 이해하고 있으며, 결제를 진행합니다.</p>
                <div align="center">
                    <button style="width:90px" align="center" @click="gopay()">
                        결제하기
                    </button>
                </div>
                <br>

 </div>

  </div>
</template>
<script>
import { subscribeOneList } from "@/api/shr/subscribe";
import { promotionCheckPNo, promotionPolicyCheckPNo} from "@/api/shr/promotion";
import moment from "moment";
import axios from 'axios'
var IMP = window.IMP; // 생략해도 괜찮습니다.
IMP.init("imp67730889"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.

export default {
  data() {
    return {
      policy_no: this.$route.params.policy_no,
      promotion_type: this.$route.params.promotion_type,
      visible: this.$route.params.visible,
      usemonth: this.$route.params.usemonth,
      policyList: "",
      start_date: "",
      promotionList: "",
      promotionPolicyList : '',
      chk: false,
      // 결제 방식 선택 배경색
            cardsCol: '',
            transCol: '',
    };
  },
  // 네비게이션 라우터가드
  beforeRouteEnter(to, from, next) {
    var regexp = /[^0-9]/gi;
    //usermonth: 공개정책(t))이면 수정가능, 비공개정책(f)은 수정 불가

    // 정책 번호 check, subscribeOneList : axios
    subscribeOneList({ subscribeNo: to.params.policy_no })
      .then(() => {
        if (
          to.params.usemonth <= 0 || to.params.usemonth > 36 ||
          regexp.exec(to.params.usemonth) !== null
        ) {
          alert(" 잘못된 접근입니다.\n 이용 개월 수가 올바르지 않습니다.");
        } else if (
          to.params.promotion_type != "t" &&
          to.params.promotion_type != "f"
        ) {
          alert(" 잘못된 접근입니다.\n 프로모션이 올바르지 않습니다.");
        } else if (to.params.visible != "t" && to.params.visible != "f") {
          alert(" 잘못된 접근입니다.\n 공개/비공개가 올바르지 않습니다.");
        } else {
          next();
        }
      })
      .catch(function(error) {
        if (error) {
          alert(" 잘못된 접근입니다.\n 정책번호가 올바르지 않습니다.");
        }
      });
  },
  async created() {
    
    // 정책번호로 가져오기 정책들
    const { data } = await subscribeOneList({ subscribeNo: this.policy_no });
    this.policyList = data[0];
    console.log(this.policyList);

    // 해당 정책 번호가 진행하는 프로모션 체크
    const response = await promotionPolicyCheckPNo({ policy_no : this.policy_no });
    this.promotionPolicyList = response.data;

    // 해당 제품 번호가 진행하는 프로모션 체크
    const promotionCheck = [];
    for (let i = 0; i < this.policyList.selectedList.length; i++) {
      promotionCheck[i] = this.policyList.selectedList[i].no;
    }
    console.log('해당제품번호 : ' , promotionCheck);
    // 제품 여러개ㅇㅇㅇ in?
    const res = await promotionCheckPNo({ promotionProduct: promotionCheck });
    this.promotionList = res.data;
  },
  computed: {
    nowDate() {
      // 구독 시작일 현재일로 고정
      return moment().format("YYYY-MM-DD");
    },
    endDate() {
      const enddatee = moment(this.nowDate)
        .add(this.usemonth * 30, "d")
        .format("YYYY-MM-DD");
      return enddatee;
    },
    // 정책 정가
    policyTotal(){
      return this.policyList.price*this.usemonth;
    },
    // 정책 프로모션 할인가
    discountTotal_policy(){
      let sum=0;
      Object.values(this.promotionPolicyList).filter(item=>{
        sum += Number(item.policy_price*(item.discount/100));
      });
      return sum*this.usemonth;
    },
    // 제품 프로모션 할인가
    discountTotal_product(){
      let sum=0;
      Object.values(this.promotionList).filter(item=>{
        sum += Number(item.product_price*(item.discount/100));
      });
      return sum;
    },
    // 정책+제품 프로모션 총 할인가
    discountTotal (){
      return this.discountTotal_policy + this.discountTotal_product == 0
               ? 0 : this.discountTotal_policy + this.discountTotal_product
    }


    //   filteredList() {
    //   return Object.values(this.subAllList).filter(post => {
    //     console.log(Object.values(post));
    //     return post.policy_title
    //       .toLowerCase()
    //       .includes(this.keyword.toLowerCase());
    //   });
    // }


  },
  methods: {
    // usemonth 체크
    usemonthCheck() {
      if (!(this.usemonth >= 1 && this.usemonth <= 36)) {
        alert("1개월 이상 최대 36개월까지 가능합니다.");
        this.usemonth = 1;
      }
    },
    // 결제
    gopay() {   // 결제 수행
            if(this.paymethod === '') {
                alert('결제 방식을 선택해주세요.');
            } else if(this.chk === false) {
                alert('결제정보를 확인하고 동의해주세요.');
            } else if(this.chk === true && this.paymethod !== '') {
                // IMP.request_pay(param, callback) 호출
                IMP.request_pay({ // param
                    pg : 'html5_inicis',
                    pay_method : this.paymethod,
                    merchant_uid : 'INZENT_Solutions' + new Date().getTime(),
                    name : this.policyList.policy_title + '/mxc' + this.policyList.max_count, 
                    // name : this.type == 'U' ? (this.uPolicies[this.idx].policy_title + '/mxc' + this.uPolicies[this.idx].max_count) : (this.aPolicies[this.idx].policy_title + '/mxc' + this.aPolicies[this.idx].max_count),
                    amount : 100,// this.type == 'U' ? this.promotions[i].price * (this.promotions[i].discount/100) * this.usemonth : this.promotions[i].price * (this.promotions[i].discount/100) * this.usemonth,
                    buyer_email : 'iamport@siot.do',
                    buyer_name : '구매자이름',
                    buyer_tel : '010-1234-5678',
                    buyer_addr : '서울특별시 강남구 삼성동',
                    buyer_postcode : '123-456'
                }, async rsp => { // callback
                    var msg = '';
                    // 결제 성공 시 로직,
                    if ( rsp.success ) {
                        // TODO
                        // 1. 주문내역 추가
                        await axios.get('/api/addOrderList', {
                            params: {
                                policyNo: this.policy_no,
                                //  userinfo????
                                userNo: this.userInfo.no,
                                monthCount: this.usemonth,
                                startDate: this.start_date,
                                endDate: this.endDate,
                                orgPrice: this.policyTotal,
                                dcPrice: this.discountTotal,
                                totalPrice: this.policyTotal - this.discountTotal_policy,
                                monthPay: false,
                            }
                        }).then(res => { 
                            console.log(res.data);
                        });
                        
                        // 2. 라이센스 추가
                        await axios.get('/api/addLicense', {
                            params: {
                                userAdminNo: this.userInfo.no,
                                policyNo: this.policy_no,
                                licenseKey: 'INZENTS' + this.start_date.toISOString(),
                                currentCount: 0,
                                maxCount: this.policyList.max_count,
                                startDate: this.start_date,
                                endDate: this.endDate,
                            }
                        }).then(res => { 
                            console.log(res.data);
                        });

                        alert('구독 신청이 완료되었습니다.')
                        this.$router.push("/license/list");

                    // 결제 실패 시 로직,
                    } else {
                        msg = '결제에 실패하였습니다.\n';
                        msg += '내용 : ' + rsp.error_msg;
                        alert(msg);
                    }
                });
            }
        },
        selPayM(payM) {
            this.paymethod = payM;   // 결제 방식 선택
            switch(payM) {
            case 'cards':
                this.cardsCol = '#99CCCC'
                this.transCol = ''
                break;
            case 'trans':
                this.cardsCol = ''
                this.transCol = '#99CCCC'
                break;
            }
        },
  }
};
</script>

<style scoped>
h1 {
    font-size: 4.5rem;
    color:#000000D9;
}
.tip {
  border: 2px solid gray;
  color: red;
  font-size: 15px;
  padding: 20px;
  border-radius: 5px;
}

.info-border {
  border: 2px solid lightgray;
  border-radius: 5px;
  padding: 10px;
  /* 추가 */  
  /* height: 932px;  */
}
.info-table {
  border-top: 2px solid #ccc;
  border-bottom: 2px solid #ccc;
  width: 100%;
  max-width: 100%;
  border-spacing: 0;
  border-collapse: collapse;
  table-layout: fixed;
}
.info-table tr {
  border-bottom: 1px solid #ccc;
  font-size: 15px;
}
.info-table th {
  background: #eaeaea;
  font-weight: normal;
  border: none;
  width: 20%;
  text-align: center;
  padding: 15px;
}
.info-table td {
  padding: 15px;
  font-weight: normal;
}
.info-table input {
  width: 100px;
}
.selPayM button {
  width: 20%;
  height: 80px;
  margin: 0 20px 0;
  background: gray;
  font-size: 20px;
}
.nav-tabs li.active a {
  background-color: #d9edf7;
}
input {
   outline:none;
}
</style>