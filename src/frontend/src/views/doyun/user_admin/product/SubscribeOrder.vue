<template>
  <div id="order" class="contents">
    <h1>구독 신청</h1>
    <p class="tip">
      서로 다른 정책의 제품들의 일괄 구매를 원하시는 고객님께서는 견적 문의
      부탁드립니다.
    </p>
    <div class="info-border">
      <template v-if="type === 'U'">
        <table class="info-table">
          <tr>
            <th>정책명</th>
            <td>{{ uPolicies[idx].policy_title }}</td>
          </tr>
          <tr>
            <th>이용 기준</th>
            <td>사용자</td>
          </tr>
          <tr>
            <th>최대 사용 수</th>
            <td v-if="uPolicies[idx].max_count === 0">
              제한 없음
            </td>
            <td v-if="uPolicies[idx].max_count !== 0">
              {{ uPolicies[idx].max_count }} 명
            </td>
          </tr>
          <tr v-if="per === 0">
            <th>이용 개월 수</th>
            <td v-if="uPolicies[idx].price === 0">30일</td>
            <td v-else>
              <input type="number" min="1" id="usemonth" v-model="usemonth" />
              개월
              <div style="font-size:13px; color:red;">
                * 개월 수는 30일로 계산됩니다.
              </div>
            </td>
          </tr>
          <tr>
            <th>구독 시작일</th>
            <td>{{ getStartDate() }}</td>
          </tr>
          <tr>
            <th>구독 종료일</th>
            <td v-if="per === 0">{{ getEndDate() }}</td>
            <td v-if="per === 1">
              {{ getEndDate()
              }}<span style="color:rgba(255, 159, 64, 1)">
                [무료 1개월 포함]</span
              >
            </td>
            <td v-if="per === 2">
              {{ getEndDate()
              }}<span style="color:rgba(255, 99, 132, 1)">
                [무료 2개월 포함]</span
              >
            </td>
          </tr>
          <tr>
            <th>프로모션 적용</th>
            <td>
              <template
                v-if="this.$store.state.productStore.promotions.length !== 0"
              >
                <ul>
                  <li
                    v-for="promotion in promotions"
                    v-bind:key="promotion.no"
                    style="margin-bottom:10px"
                  >
                    프로모션 명 : {{ promotion.title }} <br />
                    할인률 : {{ promotion.discount }}% <br />
                    할인 금액 :
                    <span style="color: red"
                      >{{
                        numberWithCommas(
                          promotion.policy_price *
                            (promotion.discount / 100) *
                            usemonth
                        )
                      }}원</span
                    >
                  </li>
                </ul>
              </template>
              <template
                v-if="this.$store.state.productStore.promotions.length === 0"
              >
                적용된 프로모션이 없습니다.
              </template>
            </td>
          </tr>
        </table>

        <hr style="border-top: 1px solid black;" />
        <div
          id="price-title"
          style="width: 80%; text-align:right; font-size:17px; font-weight: normal; float:left"
        >
          <p style="color: gray">정가 :</p>
          <p style="color: red">할인 금액 :</p>
        </div>
        <div
          id="final-price"
          style="width: 20%; text-align:right; font-size:17px; font-weight: normal; float:left"
        >
          <p style="color: gray">
            {{ numberWithCommas(uPolicies[idx].price * usemonth) }}원
          </p>
          <p style="color: red">- {{ numberWithCommas(alldiscount) }}원</p>
        </div>
        <br /><br /><br />
        <hr style="border-top: 1px solid gray;" />
        <div
          id="price-title"
          style="width: 80%; text-align:right; font-size:17px; font-weight: normal; float:left"
        >
          <p style="color: black">결제 금액 :</p>
        </div>
        <div
          id="final-price"
          style="width: 20%; text-align:right; font-size:17px; font-weight: normal; float:left"
        >
          {{
            numberWithCommas(uPolicies[idx].price * usemonth - alldiscount)
          }}원
        </div>
        <br /><br />

        <!-- 정기 결제 버튼 -->
        <template v-if="uPolicies[idx].price !== 0 || monthlyPay === 'seltab'">
          <div v-bind:id="allPay" @click="selMonthly(1)">
            일괄 결제
          </div>
          <div
            v-bind:id="monthlyPay"
            @click="selMonthly(2)"
            style="border-right:1px solid #ccc"
          >
            정기 결제
          </div>
          <span id="last"><span style="visibility: hidden;">asd</span></span>
        </template>

        <div
          id="monthly"
          v-if="uPolicies[idx].price !== 0 && allPay == 'seltab'"
        >
          <div class="selPayM" align="center">
            <h4 style="font-weight: normal;">결제 방식 선택</h4>
            <button
              id="cards"
              @click="selPayM('cards')"
              :style="{ background: cardsCol }"
            >
              신용카드
            </button>
            <button
              id="trans"
              @click="selPayM('trans')"
              :style="{ background: transCol }"
            >
              실시간계좌이체
            </button>
          </div>
        </div>
        <div
          id="monthly"
          v-if="uPolicies[idx].price !== 0 && monthlyPay == 'seltab'"
          style="text-align:center"
        >
          <h4 style="font-weight: normal;">월 {{ numberWithCommas(uPolicies[idx].price - alldiscount/usemonth) }}원이 결제됩니다.</h4>
        </div>

        <br />
        <p
          align="center"
          style="font-size:17px"
          v-if="uPolicies[idx].price !== 0"
        >
          <input type="checkbox" v-model="chk" /> 다음 결제정보에 대해 이해하고
          있으며, 결제를 진행합니다.
        </p>
        <div align="center">
          <button
            v-if="uPolicies[idx].price === 0"
            style="width:110px"
            @click="exper()"
          >
            체험판 발급
          </button>

          <button
            v-else-if="uPolicies[idx].price !== 0 && monthlyPay !== 'seltab'"
            style="width:110px"
            @click="gopay()"
          >
            결제하기
          </button>

          <button v-else style="width:110px" @click="monthlyPayM()">
            결제하기
          </button>
        </div>
        <br />
      </template>

      <template v-if="type === 'A'">
        <table class="info-table">
          <tr>
            <th>정책명</th>
            <td>{{ aPolicies[idx].policy_title }}</td>
          </tr>
          <tr>
            <th>이용 기준</th>
            <td>에이전트 정책</td>
          </tr>
          <tr>
            <th>최대 사용 수</th>
            <td v-if="aPolicies[idx].max_count === 0">
              제한 없음
            </td>
            <td v-if="aPolicies[idx].max_count !== 0">
              {{ aPolicies[idx].max_count }}
            </td>
          </tr>
          <tr v-if="per === 0">
            <th>이용 개월 수</th>
            <td v-if="aPolicies[idx].price === 0">30일</td>
            <td v-else>
              <input type="number" min="1" id="usemonth" v-model="usemonth" />
              개월
              <div style="font-size:12px; color:red;">
                * 개월 수는 30일로 계산됩니다.
              </div>
            </td>
          </tr>
          <tr>
            <th>구독 시작일</th>
            <td>{{ getStartDate() }}</td>
          </tr>
          <tr>
            <th>구독 종료일</th>
            <td v-if="per === 0">{{ getEndDate() }}</td>
            <td v-if="per === 1">
              {{ getEndDate()
              }}<span style="color:rgba(255, 159, 64, 1)">
                [무료 1개월 포함]</span
              >
            </td>
            <td v-if="per === 2">
              {{ getEndDate()
              }}<span style="color:rgba(255, 99, 132, 1)">
                [무료 2개월 포함]</span
              >
            </td>
          </tr>
          <tr>
            <th>프로모션 적용</th>
            <td>
              <template
                v-if="this.$store.state.productStore.promotions.length !== 0"
              >
                <ul>
                  <li
                    v-for="promotion in promotions"
                    v-bind:key="promotion.no"
                    style="margin-bottom:10px"
                  >
                    프로모션 명 : {{ promotion.title }} <br />
                    할인률 : {{ promotion.discount }}% <br />
                    할인 금액 :
                    <span style="color: red"
                      >{{
                        numberWithCommas(
                          promotion.policy_price *
                            (promotion.discount / 100) *
                            usemonth
                        )
                      }}원</span
                    >
                  </li>
                </ul>
              </template>
              <template
                v-if="this.$store.state.productStore.promotions.length === 0"
              >
                적용된 프로모션이 없습니다.
              </template>
            </td>
          </tr>
        </table>

        <hr style="border-top: 1px solid black;" />
        <div>
          <div
            id="price-title"
            style="width: 80%; text-align:right; font-size:17px; font-weight: normal; float:left"
          >
            <p style="color: gray">정가 :</p>
            <p style="color: red">할인 금액 :</p>
          </div>
          <div
            id="final-price"
            style="width: 20%; text-align:right; font-size:17px; font-weight: normal; float:left"
          >
            <p style="color: gray">
              {{ numberWithCommas(aPolicies[idx].price * usemonth) }} 원
            </p>
            <p style="color: red">- {{ numberWithCommas(alldiscount) }} 원</p>
          </div>
        </div>
        <br /><br /><br />
        <hr style="border-top: 1px solid gray;" />
        <div
          id="price-title"
          style="width: 80%; text-align:right; font-size:17px; font-weight: normal; float:left"
        >
          <p style="color: black">결제 금액 :</p>
        </div>
        <div
          id="final-price"
          style="width: 20%; text-align:right; font-size:17px; font-weight: normal; float:left"
        >
          {{ numberWithCommas(aPolicies[idx].price * usemonth - alldiscount) }}
          원
        </div>
        <br /><br />

        <!-- 정기 결제 버튼 -->
        <div v-bind:id="allPay" @click="selMonthly(1)">
          일괄 결제
        </div>
        <div
          v-bind:id="monthlyPay"
          @click="selMonthly(2)"
          style="border-right:1px solid #ccc"
        >
          정기 결제
        </div>
        <span id="last"><span style="visibility: hidden;">asd</span></span>

        <div
          id="monthly"
          v-if="aPolicies[idx].price !== 0 && allPay == 'seltab'"
        >
          <div class="selPayM" align="center">
            <h4 style="font-weight: normal">결제 방식 선택</h4>
            <button
              id="cards"
              @click="selPayM('cards')"
              :style="{ background: cardsCol }"
            >
              신용카드
            </button>
            <button
              id="trans"
              @click="selPayM('trans')"
              :style="{ background: transCol }"
            >
              실시간계좌이체
            </button>
          </div>
        </div>

        <br />
        <p
          align="center"
          style="font-size:17px"
          v-if="uPolicies[idx].price !== 0"
        >
          <input type="checkbox" v-model="chk" /> 다음 결제정보에 대해 이해하고
          있으며, 결제를 진행합니다.
        </p>
        <div align="center">
          <button
            v-if="uPolicies[idx].price === 0"
            style="width:110px"
            @click="exper()"
          >
            체험판 발급
          </button>
          <button v-else style="width:110px" @click="gopay()">
            결제하기
          </button>
        </div>
        <br />
      </template>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
axios.defaults.headers.common["Access-Control-Allow-Headers"] = "*";
axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded'
// import promotionPolicyCheckPNo from "../../../../api/shr/promotion";
var IMP = window.IMP; // 생략해도 괜찮습니다.
IMP.init("imp67730889"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.

export default {
  data() {
    return {
      usemonth: 1,
      enddate: "",
      chk: false,
      paymethod: "",
      year: "", // today.getFullYear(); // 년도
      month: "", // today.getMonth() + 1;  // 월
      date: "", // today.getDate();  // 날짜

      // 결제 방식 선택 배경색
      cardsCol: "",
      transCol: "",
      promotionList: "",

      allPay: "seltab",
      monthlyPay: "none",
    };
  },
  methods: {
    getStartDate: function() {
      return (
        this.leadingZeros(this.startdate.getFullYear(), 4) +
        "-" +
        this.leadingZeros(this.startdate.getMonth() + 1, 2) +
        "-" +
        this.leadingZeros(this.startdate.getDate(), 2)
      );
    },
    getEndDate: function() {
      // 1/2년 구독 정책 시
      if (this.per === 1) {
        this.usemonth = 12;
        this.enddate.setMonth(this.enddate.getMonth() + 13);
      } else if (this.per === 2) {
        this.usemonth = 24;
        this.enddate.setMonth(this.enddate.getMonth() + 26);
      } else {
        this.enddate.setDate(this.enddate.getDate() + this.usemonth * 30);
      }
      return (
        this.leadingZeros(this.enddate.getFullYear(), 4) +
        "-" +
        this.leadingZeros(this.enddate.getMonth() + 1, 2) +
        "-" +
        this.leadingZeros(this.enddate.getDate(), 2)
      );
    },
    leadingZeros(n, digits) {
      var zero = "";
      n = n.toString();

      if (n.length < digits) {
        for (var i = 0; i < digits - n.length; i++) zero += "0";
      }
      return zero + n;
    },
    numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    gopay() {
      // 결제 수행
      if (this.paymethod === "") {
        alert("결제 방식을 선택해주세요.");
      } else if (this.chk === false) {
        alert("결제정보를 확인하고 동의해주세요.");
      } else if (this.chk === true && this.paymethod !== "") {
        // Samesite Setting
        document.cookie = "safeCookie1=foo; SameSite=Lax";
        document.cookie = "safeCookie2=foo";
        document.cookie = "crossCookie=bar; SameSite=None; Secure";

        // IMP.request_pay(param, callback) 호출
        IMP.request_pay(
          {
            // param
            pg: "html5_nicepay",
            pay_method: this.paymethod,
            merchant_uid: "INZENT_Solutions" + new Date().getTime(),
            name:
              this.type == "U"
                ? this.uPolicies[this.idx].policy_title +
                  "/mxc" +
                  this.uPolicies[this.idx].max_count
                : this.aPolicies[this.idx].policy_title +
                  "/mxc" +
                  this.aPolicies[this.idx].max_count,
            amount: 100, // this.type == 'U' ? this.promotions[i].price * (this.promotions[i].discount/100) * this.usemonth : this.promotions[i].price * (this.promotions[i].discount/100) * this.usemonth,
            buyer_email: "iamport@siot.do",
            buyer_name: "구매자이름",
            buyer_tel: "010-1234-5678",
            buyer_addr: "서울특별시 강남구 삼성동",
            buyer_postcode: "123-456",
          },
          async (rsp) => {
            // callback
            var msg = "";
            // 결제 성공 시 로직,
            if (rsp.success) {
              // TODO
              // 1. 주문내역 추가
              await axios
                .get("/api/addOrderList", {
                  params: {
                    policyNo:
                      this.type === "U"
                        ? this.uPolicies[this.idx].no
                        : this.aPolicies[this.idx].no,
                    userNo: this.userInfo.no,
                    monthCount: this.usemonth,
                    startDate: this.startdate,
                    endDate: this.enddate,
                    orgPrice:
                      this.type === "U"
                        ? this.uPolicies[this.idx].price * this.usemonth
                        : this.aPolicies[this.idx].price * this.usemonth,
                    dcPrice: this.alldiscount,
                    totalPrice:
                      this.type === "U"
                        ? this.uPolicies[this.idx].price * this.usemonth -
                          this.alldiscount
                        : this.aPolicies[this.idx].price * this.usemonth -
                          this.alldiscount,
                    monthPay: false,
                  },
                })
                .then((res) => {
                  console.log(res.data);
                });

              // 2. 라이센스 추가
              await axios
                .get("/api/addLicense", {
                  params: {
                    userAdminNo: this.userInfo.no,
                    policyNo:
                      this.type === "U"
                        ? this.uPolicies[this.idx].no
                        : this.aPolicies[this.idx].no,
                    licenseKey: "INZENTS" + this.startdate.toISOString(),
                    currentCount: 0,
                    maxCount:
                      this.type === "U"
                        ? this.uPolicies[this.idx].max_count
                        : this.aPolicies[this.idx].max_count,
                    startDate: this.startdate,
                    endDate: this.enddate,
                  },
                })
                .then((res) => {
                  console.log(res.data);
                });

              alert("구독 신청이 완료되었습니다.");
              this.$router.push("/license/list");

              // 결제 실패 시 로직,
            } else {
              msg = "결제에 실패하였습니다.\n";
              msg += "내용 : " + rsp.error_msg;
              alert(msg);
            }
          }
        );
      }
    },
    async exper() {
      // 1. 주문내역 추가
      await axios
        .get("/api/addOrderList", {
          params: {
            policyNo:
              this.type === "U"
                ? this.uPolicies[this.idx].no
                : this.aPolicies[this.idx].no,
            userNo: this.userInfo.no,
            monthCount: this.usemonth,
            startDate: this.startdate,
            endDate: this.enddate,
            orgPrice:
              this.type === "U"
                ? this.uPolicies[this.idx].price * this.usemonth
                : this.aPolicies[this.idx].price * this.usemonth,
            dcPrice: this.alldiscount,
            totalPrice:
              this.type === "U"
                ? this.uPolicies[this.idx].price * this.usemonth -
                  this.alldiscount
                : this.aPolicies[this.idx].price * this.usemonth -
                  this.alldiscount,
            monthPay: false,
          },
        })
        .then((res) => {
          console.log(res.data);
        });

      // 2. 라이센스 추가
      await axios
        .get("/api/addLicense", {
          params: {
            userAdminNo: this.userInfo.no,
            policyNo:
              this.type === "U"
                ? this.uPolicies[this.idx].no
                : this.aPolicies[this.idx].no,
            licenseKey: "INZENTS" + this.startdate.toISOString(),
            currentCount: 0,
            maxCount:
              this.type === "U"
                ? this.uPolicies[this.idx].max_count
                : this.aPolicies[this.idx].max_count,
            startDate: this.startdate,
            endDate: this.enddate,
          },
        })
        .then((res) => {
          console.log(res.data);
        });

      alert("체험판 신청이 완료되었습니다.");
      this.$router.push("/license/list");
    },
    selPayM(payM) {
      this.paymethod = payM; // 결제 방식 선택
      switch (payM) {
        case "cards":
          this.cardsCol = "#337AB7";
          this.transCol = "";
          break;
        case "trans":
          this.cardsCol = "";
          this.transCol = "#337AB7";
          break;
      }
    },
    selMonthly(value) {
      switch (value) {
        case 1:
          this.allPay = "seltab";
          this.monthlyPay = "none";
          break;
        case 2:
          this.allPay = "none";
          this.monthlyPay = "seltab";
          break;
      }
    },
    monthlyPayM() {
      if(this.chk === false) {
        alert("결제정보를 확인하고 동의해주세요.");
      } else {
        // Samesite Setting
        document.cookie = "safeCookie1=foo; SameSite=Lax";
        document.cookie = "safeCookie2=foo";
        document.cookie = "crossCookie=bar; SameSite=None; Secure";

        IMP.request_pay(
          {
            // param
            pg: "kcp_billing",
            pay_method: "card", // "card"만 지원됩니다
            merchant_uid: "INZENT_Solutions" + new Date().getTime(), // 빌링키 발급용 주문번호
            customer_uid: "inzentUserNo" + this.userinfo + '/date' + new Date().getTime(), // 카드(빌링키)와 1:1로 대응하는 값
            name: this.type == "U"
                ? this.uPolicies[this.idx].policy_title +
                  "/mxc" +
                  this.uPolicies[this.idx].max_count
                : this.aPolicies[this.idx].policy_title +
                  "/mxc" +
                  this.aPolicies[this.idx].max_count,
            amount: 0, // 0 으로 설정하여 빌링키 발급만 진행합니다.
            buyer_email: "gildong@gmail.com",
            buyer_name: "홍길동",
            buyer_tel: "010-4242-4242",
            buyer_addr: "서울특별시 강남구 신사동",
            buyer_postcode: "01181",
          },
          async function(rsp) {
            // callback
            if (rsp.success) {
              axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

              // 빌링키 발급 성공
              console.log("success");
              // 인증 토큰 발급 받기
              const getToken = await axios({
                url: "https://api.iamport.kr/users/getToken",
                method: "post", // POST method
                headers: { "Content-Type": "application/json" }, // "Content-Type": "application/json"
                data: {
                  imp_key: "imp_apikey", // REST API키
                  imp_secret: "ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f" // REST API Secret
                }
              });
              const { access_token } = getToken.data.response; // 인증 토큰

              // 결제 요청
              const paymentResult = await axios({
                url: 'https://api.iamport.kr/subscribe/payments/again',
                method: "post",
                headers: { "Authorization": access_token }, // 인증 토큰 Authorization header에 추가
                data: {
                  customer_uid: "inzentUserNo" + this.userinfo + '/date' + new Date().getTime(),
                  merchant_uid: "INZENT_Solutions" + new Date().getTime(), // 새로 생성한 결제(재결제)용 주문 번호
                  amount: 100,
                  name: this.type == "U"
                    ? this.uPolicies[this.idx].policy_title +
                      "/mxc" +
                      this.uPolicies[this.idx].max_count
                    : this.aPolicies[this.idx].policy_title +
                      "/mxc" +
                      this.aPolicies[this.idx].max_count,
                }
              });

              const { code } = paymentResult;
              if (code === 0) { // 카드사 통신에 성공(실제 승인 성공 여부는 추가 판단이 필요합니다.)
                if ( paymentResult.status === "paid" ) { //카드 정상 승인
                  alert('카드 정상 승인.');
                  // 1. 주문내역 추가
                  await axios
                    .get("/api/addOrderList", {
                      params: {
                        policyNo:
                          this.type === "U"
                            ? this.uPolicies[this.idx].no
                            : this.aPolicies[this.idx].no,
                        userNo: this.userInfo.no,
                        monthCount: this.usemonth,
                        startDate: this.startdate,
                        endDate: this.enddate,
                        orgPrice:
                          this.type === "U"
                            ? this.uPolicies[this.idx].price * this.usemonth
                            : this.aPolicies[this.idx].price * this.usemonth,
                        dcPrice: this.alldiscount,
                        totalPrice:
                          this.type === "U"
                            ? this.uPolicies[this.idx].price * this.usemonth -
                              this.alldiscount
                            : this.aPolicies[this.idx].price * this.usemonth -
                              this.alldiscount,
                        monthPay: true,
                      },
                    })
                    .then((res) => {
                      console.log(res.data);
                    });

                  // 2. 라이센스 추가
                  await axios
                    .get("/api/addLicense", {
                      params: {
                        userAdminNo: this.userInfo.no,
                        policyNo:
                          this.type === "U"
                            ? this.uPolicies[this.idx].no
                            : this.aPolicies[this.idx].no,
                        licenseKey: "INZENTS" + this.startdate.toISOString(),
                        currentCount: 0,
                        maxCount:
                          this.type === "U"
                            ? this.uPolicies[this.idx].max_count
                            : this.aPolicies[this.idx].max_count,
                        startDate: this.startdate,
                        endDate: this.enddate,
                      },
                    })
                    .then((res) => {
                      console.log(res.data);
                    });

                  alert("구독 신청이 완료되었습니다.");
                  this.$router.push("/license/list");
                } else { //카드 승인 실패 (ex. 고객 카드 한도초과, 거래정지카드, 잔액부족 등)
                  //paymentResult.status : failed 로 수신됩니다.
                  alert('카드 승인 실패.');
                }
              } else { // 카드사 요청에 실패 (paymentResult is null)
                alert('카드사 요청 실패.');
              }

            } else {
              // 빌링키 발급 실패
              alert('결제에 실패하였습니다. \n에러 내용: ' + rsp.error_msg);
            }
          }
        );
      }
    },
  },
  computed: {
    uPolicies: function() {
      return this.$store.state.productStore.uPolicies;
    },
    aPolicies: function() {
      return this.$store.state.productStore.aPolicies;
    },
    idx: function() {
      return this.$route.params.policyidx;
    },
    type: function() {
      return this.$route.params.policytype;
    },
    per: function() {
      return this.$route.params.per;
    },
    startdate: function() {
      return this.$store.state.productStore.startdate;
    },
    promotions: function() {
      return this.$store.state.productStore.promotions;
    },
    discountTotal_policy() {
      let sum = 0;
      Object.values(this.promotionPolicyList).filter((item) => {
        sum += Number(item.policy_price * (item.discount / 100));
      });
      return sum * this.usemonth;
    },
    alldiscount: function() {
      // 할인액 산정
      this.$store.commit("productStore/DEL_ALLDC");

      for (var i = 0; i < this.promotions.length; i++) {
        this.$store.commit(
          "productStore/ADD_ALLDC",
          this.promotions[i].policy_price *
            (this.promotions[i].discount / 100) *
            this.usemonth
        );
      }

      return this.$store.state.productStore.alldiscount;
    },
    userInfo: function() {
      return this.$store.state.userinfo.userInfo;
    },
  },
  async created() {
    // 구독 시작일, 종료일 설정
    this.$store.commit("productStore/SET_STARTDATE", new Date());
    this.enddate = new Date();
    // alert((new Date).toISOString());

  },
  beforeUpdate() {
    this.enddate = new Date();
  },
};
</script>

<style scoped>
h1 {
  font-size: 4.5rem;
  color: #000000d9;
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
#monthly {
  border-bottom: 1px solid #ccc;
  padding: 20px;
  background: #d9edf7;
}

#none {
  font-size: 15px;
  border: 1px solid #ccc;
  border-right: none;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  padding: 10px 20px 10px;
  width: 10%;
  display: inline-block;
  cursor: pointer;
  text-align: center;
}
#last {
  display: inline-block;
  width: 80%;
  height: 100%;
  border-bottom: 1px solid #ccc;
  padding: 10px 20px 10px;
}
#seltab {
  font-size: 15px;
  border: 1px solid #ccc;
  border-bottom: 1px solid #d9edf7;
  border-right: none;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  padding: 10px 20px 10px;
  width: 10%;
  display: inline-block;
  cursor: pointer;
  text-align: center;
  background: #d9edf7;
}
</style>
