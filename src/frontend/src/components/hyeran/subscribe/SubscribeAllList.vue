<template>
  <div>
    <table class="table_board">
      <tr>
        <th>구독 정책 번호</th>
        <th>구독 정책명</th>
        <th>이용 기준</th>
        <th>최대 사용 수량</th>
        <th>가격</th>
        <th>정책 공개 여부</th>
        <th>URL</th>
        <th>등록일</th>
      </tr>
      <tr v-for="(list, index) in subAllList" :key="list.no" @click="listClick(index)">
        <td>{{ list.no }}</td>
        <td>
          <span style="text-decoration-line: underline;">{{ list.policy_title }}</span>
        </td>
        <td>{{ list.standard }}</td>
        <td>{{ list.max_count | formatPrice}}</td>
        <td>{{ list.price | formatPrice }}</td>
        <td>{{ list.visible==true?'공개':'비공개'}}</td>
        <td>{{ list.url }}</td>
        <td>{{ list.reg_date | formatDate }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import { subscribeAllList } from "@/api/shr/subscribe";
import moment from "moment";

export default {
  data() {
    return {
      subAllList: ""
    };
  },
  created() {
    this.listData();
  },
  methods: {
    async listData() {
      const { data } = await subscribeAllList();
      this.subAllList = data;
    },
    listClick(index){
      console.log('click');
      const productNo = this.subAllList[index].no;
      console.log('productno는 :' , productNo);
      this.$router.push({name:'subscribeInfo' , params:{ product_no: productNo }});

    }
  },
  filters: {
    formatDate: function(value) {
      return moment(value).format("YYYY/MM/DD HH:mm:ss");
    },
    formatPrice: function(value) {
      if (!value) return "";
      return value.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    }
  }
};
</script>

<style>
</style>