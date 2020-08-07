<template>
    <div class="contents">
    <div>
      <br />
      <h1>주문 정보</h1>
      <br />
      <div id="inner">
      <form class="form" @submit.prevent="productChange">
        <table class="table_add">
          <tr>
            <th>기업명 [관리자명]</th>
            <td>{{ list.co_name }} [{{list.name}}]</td>
          </tr>
          <tr>
            <th>구독 정책명</th>
            <td>{{ list.policy_title}}</td>
          </tr>
          <tr>
            <th>구독 개월</th>
            <td>{{ list.month_count }}</td>
          </tr>
          <tr>
            <th>구독 기간</th>
            <td>{{ list.start_date }} ~ {{ list.end_date }}</td>
          </tr>
          <tr>
            <th>결제 금액</th>
            <td>{{ list.total_price | formatPrice}} 원</td>
          </tr>
          <tr>
            <th>주문일</th>
            <td>{{ list.reg_date }}</td>
          </tr>
        </table>
        <br />
        <div style="text-align: center;">
          <input class="cssbtn" type="button" value="목록" @click="goList" style="border-radius:4px; width: 80px"/>
        </div>
        <br>
      </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
    data(){
        return{
            no: "",
            list: ""
        };
    },
    methods: {
        goList(){
            this.$router.push("/orderlist");
        }
    },
    created(){
        axios.post("/api/orderinfo",{
            no: this.$route.params.no
        })
        .then(result => {
            this.list = result.data;
        })
    }
}
</script>

<style scoped>
  h1 {
      font-size: 4.5rem;
      color:#000000D9;
  }
.cssbtn {
  background-color: #3498db;
  color: #ffffff;
  border: none;
  width: 120px;
  height: 40px;
}
#inner {
  width: 100%;
  display: inline-block;
  border: 1px solid #ccc;
  padding:10px;
}
</style>