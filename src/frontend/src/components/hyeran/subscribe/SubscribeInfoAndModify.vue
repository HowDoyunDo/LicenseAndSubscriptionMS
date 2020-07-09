<template>
  <div>
    <h2 v-if="modifyToggle==true || modifyAllToggle==true">구독 정책 수정</h2>
    <h2 v-else>구독 정책 상세보기</h2>
    <br />
    <form class="form">
      <table class="table_add">
        <tr>
          <th>구독 정책명</th>
          <td>
            <input
              v-if="modifyToggle==true || modifyAllToggle==true"
              type="text"
              v-model="policy_title"
            />
            <div v-else>{{ policy_title}}</div>
          </td>
        </tr>
        <tr>
          <th>공개 여부</th>
          <td>
            <div v-if="modifyToggle==true || modifyAllToggle==true">
              <input type="radio" name="public_radio" value="true" v-model="visible" /> 공개
              <input type="radio" name="public_radio" value="false" v-model="visible" /> 비공개
            </div>
            <div v-else>{{ visible ? '공개' : '비공개' }}</div>
          </td>
        </tr>
        <tr>
          <th>이용 기준</th>
          <td v-if="modifyAllToggle==true">
            <input type="radio" name="standard" value="U" v-model="standard" /> 사용자
            <input type="radio" name="standard" value="A" v-model="standard" /> 에이전트
          </td>
          <td v-else>{{ standard=='U' ? '사용자' : '에이전트' }}</td>
        </tr>
        <tr>
          <th>수량</th>
          <td v-if="modifyAllToggle==true">
            <input type="number" min="1" v-model="max_count" /> 개
          </td>
          <td v-else>{{max_count}} 개</td>
        </tr>
        <tr>
          <th>가격</th>
          <td v-if="modifyAllToggle==true">
            <input
              type="text"
              min="0"
              v-model="format"
              @keyup="numberFormat"
              style="width : 150px; text-align:center;"
            /> 원
            <!-- @blur="formatPrice" -->
          </td>
          <td v-else>{{format}} 원</td>
        </tr>
        <tr>
          <th>URL</th>
          <td>
            <input v-if="modifyAllToggle==true" type="text" v-model="url" />
            <div v-else>{{url}}</div>
          </td>
        </tr>
        <tr>
          <th>등록일</th>
          <td>{{reg_date | formatDate}}</td>
        </tr>
      </table>
      <br />

      <table class="table_add">
        <tr>
          <th>포함 제품</th>
        </tr>
      </table>
      <div class="select-product">
        <div>
          <table class="table_board">
            <tr>
              <th style="width:15%;">제품번호</th>
              <th style="width:15%;">카테고리명</th>
              <th style="width:25%;">제품명</th>
              <th style="width:15%;">가격</th>
              <th style="width:15%;">등록일</th>
              <th style="width:15%;">상세보기</th>
            </tr>
            <tr v-for="(product) in list" :key="product.id">
              <td>{{product.no}}</td>
              <td>{{product.category_title}}</td>
              <td>
                <span style="text-decoration-line: underline;">{{product.name}}</span>
              </td>
              <td>{{product.price | formatPrice}}</td>
              <td>{{product.reg_date | formatDate}}</td>
              <td>
                <a href="#" @click="openInfo(product.no)">상세보기</a>
                <modal v-if="showModal" @closeee="showModal=false" />
              </td>
            </tr>
          </table>

          <hr />
          <div class="product-sum" style="font-color : black;">제품 합계 : {{productPrice}} 원</div>
        </div>
      </div>

      <div class="submit_btn">
        <div v-if="modifyToggle==true || modifyAllToggle==true">
          <button type="button" @click="subscribeModifySubmit">수정 완료</button>
          <button type="button" @click="cancel">취소</button>
        </div>
        <div v-else>
          <button type="button" @click="modifyClick">수정</button>
          <button type="button" @click="deleteClick">삭제</button>
          <button type="button" @click="listClick">목록</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { subscribeModify, licenseCheck, subscribeDelete } from "@/api/shr/subscribe";
import ProductModal from "@/components/hyeran/product/ProductModal.vue";
import { formatMixin } from "../mixins/numberFormat";
import { subscribeMixin } from "../mixins/subscribeInfo";

export default {
  mixins: [formatMixin, subscribeMixin],
  data() {
    return {
      subscribe_no: "",
      productPrice: "",
      url: "",
      reg_date: "",
      list: "",
      modifyToggle: false,
      modifyAllToggle: false,
      showModal: false
    };
  },
  components: {
    modal: ProductModal
  },
  async created() {
    this.subscribe_no = this.$route.params.subscribe_no;

    //  정책번호로 해당 정책 list setting 위한 스토어 저장
    await this.$store.dispatch(
      "subscribeStore/selectOneSubscribe",
      this.subscribe_no
    );
    // 초기값 세팅
    this.defaultSetting();
  },
  methods: {
    // 수정완료 submit
    async subscribeModifySubmit() {
      if (!this.formValidation()) {
        let res = null;
        if (this.modifyToggle == true) {
          // 라이선스 발급된 정책 수정
          const subModifyData = {
            no: this.subscribe_no,
            policy_title: this.policy_title,
            visible: this.visible
          };
          res = await subscribeModify(subModifyData);
        } else if (this.modifyAllToggle == true) {
          const sunModifyData = {
            no: this.subscribe_no,
            policy_title: this.policy_title,
            standard: this.standard,
            max_count: this.max_count,
            price: this.uncomma(this.format),
            visible: this.visible,
            url: this.url
          };
          res = await subscribeModify(sunModifyData);
        }
        if (res.data == 1) {
          alert("수정 완료");
          location.reload();
        } else {
          alert("수정 실패");
          this.cancel();
        }
      }
    },
    // 목록
    listClick() {
      this.$router.push({ name: "subscribeAllList" });
    },
    // 수정하기 버튼
    async modifyClick() {
      if (confirm("수정하시겠습니까?")) {
        // 라이선스 발급이 안됐으면 전체 수정 가능,
        // 아니면 정책명, 공개여부만 수정 가능

        // modifyToggle -> 라이선스 발급 됐을 때
        // modifyAllToogle ->  라이선스 발급 안됐을 때 전체 수정 가능
        // 1. 해당 구독정책번호가 라이선스발급이 되었는지 체크
        const licenseCount = await licenseCheck(this.subscribe_no);
        if (licenseCount.data == 1) {
          // 라이선스 존재 -> 일부 수정
          this.modifyToggle = true;
        } else {
          // 라이선스 존재 X -> 전체 수정 가능
          this.modifyAllToggle = true;
        }
      }
    },
    // 삭제 버튼
    async deleteClick() {
      if (confirm("삭제하시겠습니까?")) {
        // 1. 해당 구독정책번호가 라이선스발급이 되었는지 체크
        const licenseCount = await licenseCheck(this.subscribe_no);
        if (licenseCount.data == 1) {
          // 라이선스 존재 -> 삭제 불가
          alert("삭제 불가 - 라이선스가 발급되어 있습니다. ");
        } else {
          const { data } = await subscribeDelete({
            no: this.subscribe_no
          });
          console.log('dadta!!!!!!!!!!!!', data);
          if (data == 1) {
            alert("삭제 완료");
            this.$router.push("/subscribe/alllist");
          } else {
            alert("삭제 실패");
          }
        }
      }
    },
    //상세보기
    openInfo(productNo) {
      console.log("상세보기", productNo);
      // 상세보기 클릭 시 제품 설명 새창으로.
      this.showModal = true;
    },

    // 취소
    cancel() {
      this.modifyToggle = false;
      this.modifyAllToggle = false;
      this.defaultSetting();
    }
  }
};
</script>


<style>
button {
  width: auto;
  padding: 0 15px;
  margin: 10px;
}
</style>