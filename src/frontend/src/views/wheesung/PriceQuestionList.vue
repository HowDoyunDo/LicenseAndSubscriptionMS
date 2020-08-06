<template>
  <div class="contents">
    <div>
      <h1>견적 문의 내역</h1>
      <br />
      <div class="search-wrapper btnn" style="height:50px">
        <select v-model="selected" style="margin: 0 5px 0 0; height: 40px; float: left">
          <option value="co_name">기업명</option>
          <option value="name">관리자명</option>
        </select>
        <input
          style="margin: 0; width:260px; height:40px"
          id="myInput"
          type="text"
          v-if="selected === 'co_name'"
          v-on:input="keyword = $event.target.value"
          placeholder="검색어 입력"
        />
        <input
          style="margin: 0; width:260px; height:40px"
          id="myInput"
          type="text"
          v-if="selected === 'name'"
          v-on:input="keyword = $event.target.value"
          placeholder="검색어 입력"
        />
      </div>

      <table class="table_board">
        <tr style="float:center;">
          <th style="width:5%;">번호</th>
          <th style="width:20%;">기업명 [관리자]</th>
          <th style="width:30%">인원(수량)</th>
          <th style="width:10%;">정책 기준</th>
          <th style="width:10%;">등록일</th>
          <th style="width:10%;">상태</th>
        </tr>
        <tr
          onmouseover="this.style.background='#CEECF5';"
          onmouseout="this.style.background=''"
          style="cursor:pointer"
          v-for="(pricequestion, idx) in filteredList"
          :key="pricequestion.no"
          @click="listClick(pricequestion.no)"
        >
          <td>{{ idx + 1 }}</td>
          <td>{{ pricequestion.co_name }} [{{ pricequestion.name }}]</td>
          <td>{{ pricequestion.count }}</td>
          <td v-if="pricequestion.standard ==='A'">에이전트</td>
          <td v-if="pricequestion.standard ==='U'">사용자</td>
          <td>{{ pricequestion.reg_date }}</td>
          <td v-if="pricequestion.status ==='S'">답변완료</td>
          <td v-if="pricequestion.status ==='W'">대기</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      list: "",
      no: "",

      keyword: "",
      selected: "co_name",
    };
  },
  methods: {
    listClick(no) {
      this.$router.push({
        name: "pricequestioninfo",
        params: { no: no },
      });
    },
  },
  computed: {
    filteredList() {
      return Object.values(this.list).filter((post) => {
        if (this.selected === "co_name") {
          return post.co_name.toLowerCase().includes(this.keyword.toLowerCase());
        } else if (this.selected === "name") {
          return post.name.toLowerCase().includes(this.keyword.toLowerCase());
        }
      });
    },
  },
  created() {
    axios.get("/api/pricequestionlist").then((result) => {
      this.list = result.data;
    });
  },
};
</script>

<style scoped>
#myInput {
  background-image: url("../../assets/images/searchIcon2.png");
  background-size: 23px;
  background-position: 8px 8px;
  background-repeat: no-repeat;
  width: 260px;
  height: 40px;
  font-size: 16px;
  padding: 12px 20px 12px 42px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
}
</style>