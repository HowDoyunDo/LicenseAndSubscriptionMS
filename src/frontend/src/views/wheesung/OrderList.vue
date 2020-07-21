<template>
  <div class="contents" style="border-left : 1px solid lightgray;">
    <div>
      <br />
      <div>
        <h2>주문 내역</h2>
      </div>
      <br />
      <table class="table_board">
        <tr style="float:center;">
          <th style="width:5%;">번호</th>
          <th style="width:20%;">기업명 [관리자]</th>
          <th style="width:30%">구독 정책명</th>
          <th style="width:10%;">시작일</th>
          <th style="width:10%;">종료일</th>
          <th style="width:10%;">주문일</th>
        </tr>
        <tr
          onmouseover="this.style.background='#CEECF5';"
          onmouseout="this.style.background=''"
          style="cursor:pointer"
          v-for="(order, idx) in list"
          :key="order.no"
          @click="listClick(order.no)"
        >
          <td>{{ idx + 1 }}</td>
          <td>{{ order.co_name }} [{{ order.name }}]</td>
          <td>{{ order.policy_title }}</td>
          <td>{{order.start_date}}</td>
          <td>{{order.end_date}}</td>
          <td>{{order.reg_date}}</td>
        </tr>
      </table>
    </div>
    <br />
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      list: "",
    };
  },
  methods: {
      listClick(no){
          this.$router.push({
              name: "orderinfo",
              params: {no: no}
          })
      }
  },
  created() {
    axios.get("/api/orderlist").then(result => {
      this.list = result.data;
    });
  }
};
</script>

<style>
.cssbtn {
  background-color: #3498db;
  color: #ffffff;
  border: none;
  width: 200px;
  height: 40px;
}

.table_board th {
  text-align: center;
  font-size: 15px;
  height: 40px;
}

.table_board td {
  height: 40px;
}

</style>