<template>
  <div>
    <h1 v-if="modifyToggle==true">프로모션 수정</h1>
    <h1 v-else>프로모션 상세보기</h1>
    <br />
    <form class="form" @submit.prevent="promotionAddSubmit">
      <table class="table_add">
        <tr>
          <th>프로모션명</th>
          <td>
            <input v-if="modifyToggle==true" type="text" v-model="title" />
            <div v-else>{{title }}</div>
          </td>
        </tr>
      </table>

      <br />
      <table class="table_add">
        <tr>
          <th>선택된 제품</th>
        </tr>
      </table>
      <div class="select-product">
        <ProductOneList :promotionProduct="promotionProduct" :discountPrice="discount/100" />
      </div>

      <br />
      <table class="table_add">
        <tr>
          <th>할인율</th>
          <td>
            <div v-if="modifyToggle==true">
              <input
                type="number"
                min="1"
                max="100"
                v-model.number="discount"
                @keyup="discountPriceForm"
                @blur="discountPriceForm"
              /> %
            </div>
            <div v-else>{{ discount }} %</div>
          </td>
        </tr>
        <tr>
          <th>사용 기간</th>
          <td v-if="modifyToggle==true">
            <input type="date" v-model="start_date" /> &nbsp;
            <input type="time" v-model="start_date_time" />
            ~
            <input type="date" v-model="end_date" /> &nbsp;
            <input type="time" v-model="end_date_time" />
          </td>
          <td
            v-else
            style="letter-spacing: 0.5px; word-spacing: 5px;"
          >{{start_date}} {{start_date_time}} ~ {{end_date}} {{end_date_time}}</td>
        </tr>
        <tr>
          <th>진행 여부</th>
          <td v-if="modifyToggle==true">
            <input type="radio" name="promotion_type" value="true" v-model="type" /> 진행 함
            <input type="radio" name="promotion_type" value="false" v-model="type" /> 진행 안 함
          </td>
          <td v-else>{{type == true ? '진행 함' : '진행 안 함'}}</td>
        </tr>
      </table>
      <br />

      <div class="submit_btn">
        <div v-if="modifyToggle==true">
          <button type="button" @click="promotionModifySubmit">수정 완료</button>
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
import { promotionMixin } from "../mixins/promotionAdd";
import { promotionModify, promotionDelete } from "@/api/shr/promotion";

export default {
  mixins: [promotionMixin],
  data() {
    return {
      promotionNo: "",
      modifyToggle: false
    };
  },

  async created() {
    this.promotionNo = this.$route.params.promotion_no;

    // 프로모션 목록 스토어 저장
    await this.$store.dispatch(
      "promotionStore/selectOnePromotion",
      this.promotionNo
    );

    // 초기값 세팅
    this.defaultSetting();
  },
 
  methods: {
    // 수정 submit button
    async promotionModifySubmit() {
      if (!this.promotionFormValidation()) {
        const promotionData = {
          promotion_no: this.promotionNo,
          title: this.title,
          discount: this.discount,
          start_date: this.start_date,
          start_date_time: this.start_date_time,
          end_date: this.end_date,
          end_date_time: this.end_date_time,
          type: this.type
        };
        const { data } = await promotionModify(promotionData);

        if (data == 1) {
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
      this.$router.push({ name: "promotion" });
    },
    // 취소
    cancel() {
      this.modifyToggle = false;
      this.defaultSetting();
    },
    modifyClick() {
      if (confirm("수정하시겠습니까?")) {
        this.modifyToggle = true;
      }
    },
    // 삭제
    async deleteClick() {
      if (confirm("삭제하시겠습니까?")) {
        const { data } = await promotionDelete({
          promotion_no: this.promotionNo
        });
        if (data == 1) {
          alert("삭제 완료");
          this.$router.push("/promotion");
        } else {
          alert("삭제 실패");
          this.cancel();
        }
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
button {
  width: auto;
  padding: 0 15px;
  margin: 10px;
}
input[type="date"],
input[type="time"],
input[type="radio"] {
  text-align: center;
  border-style: none;
  font-size: 100%;
  border: 1px solid #dae1e7;
  -webkit-box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  padding: 0.5rem 0.75rem;
  margin: 5px;
   outline:none;
}
</style>