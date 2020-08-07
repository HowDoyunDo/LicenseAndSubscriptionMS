<template>
  <div class="contents">
    <div>
      <h1>주문 내역</h1>
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
          @input="resetPageNum"
          placeholder="검색어 입력"
        />
        <input
          style="margin: 0; width:260px; height:40px"
          id="myInput"
          type="text"
          v-if="selected === 'name'"
          v-on:input="keyword = $event.target.value"
          @input="resetPageNum"
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
          v-for="(order, idx) in paginatedData"
          :key="order.no"
          @click="listClick(order.no)"
        >
          <td>{{ idx + 1 }}</td>
          <td>{{ order.co_name }} [{{ order.name }}]</td>
          <td>{{ order.policy_title }}</td>
          <td>{{ order.start_date }}</td>
          <td>{{ order.end_date }}</td>
          <td>{{ order.reg_date }}</td>
        </tr>
      </table>
      <div v-if="filteredList.length === 0" style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;">존재하지 않습니다.</div>
      <hr>

      <!-- 페이징 -->
      <br />
      <div class="btn-cover" style="text-align: center">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          이전
        </button>
        <span class="page-count" v-if="filteredList.length === 0">
          {{ pageNum + 1 }} / 1
        </span>
        <span class="page-count" v-else>
          {{ pageNum + 1 }} / {{ pageCount }}
        </span>
        <button
          :disabled="pageNum >= pageCount - 1"
          @click="nextPage"
          class="page-btn"
        >
          다음
        </button>
      </div>
      <!-- 페이징 -->
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
      /////////////////////////////페이징////////////////////
      pageNum: 0,
      pageSize: 10,
      /////////////////////////////페이징////////////////////
    };
  },
  methods: {
    listClick(no) {
      this.$router.push({
        name: "orderinfo",
        params: { no: no },
      });
    },
    /////////////////////////////페이징////////////////////
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    resetPageNum(){
      this.pageNum = 0;
    }
    /////////////////////////////페이징////////////////////
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
    /////////////////////////////페이징////////////////////
    pageCount() {
      let listLeng = this.filteredList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.filteredList.slice(start, end);
    },
    /////////////////////////////페이징////////////////////
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
  color: #000000d9;
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
  height: 40px;
  font-size: 16px;
  padding: 12px 20px 12px 42px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
}
</style>
