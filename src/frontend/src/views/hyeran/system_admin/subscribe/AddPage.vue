<template>
  <div class="contents">
    <h2>구독 정책 등록</h2>
    <form class="form" @submit.prevent="subscribeAdd">
      <table class="table_add">
        <tr>
          <th>구독 정책명</th>
          <td>
            <input type="text" v-model="policy_title" />
          </td>
        </tr>
        <tr>
          <th>정책 공개 여부</th>
          <td>
            <input type="radio" name="public_radio" value="true" v-model="visible" /> 공개
            <input type="radio" name="public_radio" value="false" v-model="visible" /> 비공개
          </td>
        </tr>
        <tr>
          <th>이용 기준</th>
          <td>
            <input type="radio" name="standard" value="U" v-model="standard" /> 사용자
            <input type="radio" name="standard" value="A" v-model="standard" /> 에이전트
          </td>
        </tr>
        <tr>
          <th>최대 사용 수량</th>
          <td>
            <input type="number" min="1" v-model="max_count" /> 개
            <!--v-model.number="maxCount" -->
          </td>
        </tr>
        <tr>
          <th>가격</th>
          <td>
            <input
              type="text"
              min="0"
              v-model="format"
              @keyup="numberFormat"
              style="width : 100px; text-align:center;"
            /> 원
            <!-- @blur="formatPrice" -->
          </td>
        </tr>
        <tr>
          <th>제품 선택 {{ selected }}</th>
          <td>
            <select v-model="selected" class="select-box" @change="addProduct()">
              <option disabled value>제품을 선택해주세요</option>
              <option
                v-for="(list) in productList"
                :value="list.no"
                :key="list.no"
              >{{list.name}} - {{list.comments}}</option>
            </select>
          </td>
        </tr>
      </table>
      <br />

      <div v-if="selected != ''">
        <table class="table_add">
          <tr>
            <th>선택된 제품</th>
          </tr>
        </table>
        <!-- 목록 컴포넌트 -->
        <div class="select-product">
          <ProductOneList />
          <!-- <hr />
          <div>합계 : 원</div> -->
        </div>
      </div>

      <div class="submit_btn">
        <button type="submit">구독 정책 등록</button>
      </div>
    </form>
  </div>
</template>

<script>
import ProductOneList from "@/components/hyeran/product/ProductOneList.vue";
import { subscribeAdd } from "@/api/shr/subscribe";
import { productAllList } from "@/api/shr/product";

export default {
  data() {
    return {
      productList: "",
      format: "",
      selected: "",
      policy_title: "",
      visible: "true",
      standard: "",
      max_count: ""
    };
  },
  components: {
    ProductOneList
  },
  methods: {
    numberFormat(event) {
      console.log(event.target.value);
      let num = event.target.value;
      this.format = this.comma(this.uncomma(num));
    },
    // 콤마 찍기
    comma(str) {
      str = String(str);
      return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
    },
    // 콤마 풀기
    uncomma(str) {
      str = String(str);
      return str.replace(/[^\d]+/g, "");
    },

    // 전송, 구독 정책 등록
    async subscribeAdd() {
      // 폼 유효성 검사
      if (
         this.format == null || this.format == "" ||
         this.productList == null ||  this.productList == "" ||
         this.selected == null ||  this.selected == "" ||
         this.policy_title == null ||  this.policy_title == "" ||
         this.visible == null ||  this.visible == "" ||
         this.standard == null ||  this.standard == "" ||
         this.max_count == null ||  this.max_count == ""
      ) {
        alert("필수 입력 값을 입력해주세요");
        return false;
      } else {
        // 등록할 데이터
        const subscribeAddData = {
          policy_title: this.policy_title,
          visible: this.visible,
          standard: this.standard,
          max_count: this.max_count,
          price: this.uncomma(this.format),
          selectedList: this.$store.getters.getProductList
        };

        // submit
        const { data } = await subscribeAdd(subscribeAddData);
        console.log(data);
        if(data==1){
          alert('등록 완료');
          this.$router.push('/subscribe/alllist');
        }else{
          alert('등록 실패');
        }
      }
    },

    addProduct() {
      this.$store.dispatch("selectOneProduct", this.selected);
    }
  },
  async created() {
    // 제품 선택 전체 list
    const { data } = await productAllList();
    this.productList = data;
  }
};
</script>

<style>
.form {
  width: 100%;
  height: 100%;
}
.table_add {
  border: 2px solid #ccc;
  width: 100%;
  max-width: 100%;
  height: 100%;
  border-spacing: 0;
  border-collapse: collapse;
  table-layout: fixed;
}
.table_add tr {
  border-bottom: 1px solid #ccc;
}
.table_add th {
  text-align: center;
  width: 22%;
  height: 40px;
  background: #eaeaea;
  font-weight: bold;
}
.table_add td {
  width: 78%;
  padding: 5px;
}
.form input[type="text"],
input[type="number"],
.select-box {
  font-size: 100%;
  border: 1px solid #dae1e7;
  -webkit-box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  padding: 0.5rem 0.75rem;
  margin: 5px;
  width: 90%;
  max-width: 95%;
}
input[type="number"] {
  width: 100px;
  text-align: center;
}
.select-product {
  border: 2px solid #dae1e7;
  border-top: none;
  overflow: auto;
  font-size: smaller;
  padding: 15px;
}
button[type="submit"] {
  width: auto;
  padding: 0 15px;
  margin: 10px;
}

.submit_btn {
  text-align: center;
}
</style>