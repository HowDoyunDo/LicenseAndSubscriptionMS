<template>
  <div class="contents">
    <div>
      <h1>주문 내역</h1>
      <br>
      <div style="margin: 0 0 10px 0;">
        <select v-model="selected" style="margin: 0 5px 0 0; height: 30px;">
          <option value="co_name">기업명</option>
          <option value="name">관리자명</option>
        </select>
        <input
          style="margin: 8px 0 0 0;"
          id="myInput"
          type="text"
          v-if="selected==='co_name'"
          v-on:input="keyword = $event.target.value"
          placeholder="검색어 입력"
        />
        <input
          style="margin: 8px 0 0 0;"
          id="myInput"
          type="text"
          v-if="selected==='name'"
          v-on:input="keyword = $event.target.value"
          placeholder="검색어 입력"
        />
      </div>
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
          v-for="(order, idx) in filteredList"
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
      keyword: "",
      selected: "co_name",
    };
  },
  methods: {
    listClick(no) {
      this.$router.push({
        name: "orderinfo",
        params: { no: no },
      });
    },
  },
  computed: {
    filteredList() {
      return Object.values(this.list).filter((post) => {
        if (this.selected === "co_name") {
          return post.co_name
            .toLowerCase()
            .includes(this.keyword.toLowerCase());
        } else if (this.selected === "name") {
          return post.name.toLowerCase().includes(this.keyword.toLowerCase());
        }
      });
    },
  },
  created() {
    axios.get("/api/orderlist").then((result) => {
      this.list = result.data;
    });
  },
};
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

#myInput {
  background-image: url("../../assets/images/searchIcon2.png");
  background-size: 23px;
  background-position: 8px 8px;
  background-repeat: no-repeat;
  width: 260px;
  height: 35px;
  font-size: 16px;
  padding: 12px 20px 12px 42px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}
</style>