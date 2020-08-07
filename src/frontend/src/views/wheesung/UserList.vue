<template>
  <div class="contents">
    <div>
      <h1>관리자 목록</h1>
      <br />
      <table class="table_board">
        <tr style="float:center;">
          <th width="5%">번호</th>
          <th style="width:20%;">기업명 [관리자]</th>
          <th width="5%">타입</th>
          <th style="width:20%; padding: 0 0 0 20px;">이메일</th>
          <th style="width:15%;">등록일</th>
        </tr>
        <tr
          onmouseover="this.style.background='#CEECF5';"
          onmouseout="this.style.background=''"
          style="cursor:pointer"
          v-for="(user, idx) in paginatedData"
          :key="user.no"
          @click="listClick(user.no)"
        >
          <td>{{ idx + 1 }}</td>
          <td>{{ user.co_name }} [{{ user.name }}]</td>
          <td>{{ user.manager_type }}</td>
          <td style="padding: 0 0 0 20px;">{{user.email}}</td>
          <td>{{user.reg_date}}</td>
        </tr>
      </table>
      <div v-if="list.length === 0" style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;">존재하지 않습니다.</div>
      <hr>
      <!-- 페이징 -->
      <br />
      <div class="btn-cover" style="text-align: center">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          이전
        </button>
        <span class="page-count" v-if="list.length === 0">
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
      no: "",
      list: "",
      title: "",
      ca_title: "",
      /////////////////////////////페이징////////////////////
      pageNum: 0,
      pageSize: 10,
      /////////////////////////////페이징////////////////////
    };
  },
  computed:{
    /////////////////////////////페이징////////////////////
    pageCount() {
      let listLeng = this.list.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.list.slice(start, end);
    },
    /////////////////////////////페이징////////////////////
  },
  methods: {
    // 사용자 정보 상세보기 페이지 이동
    listClick(no) {
      this.$router.push({
          name: "userinfo",
          params: {no: no}
      })
    },
    /////////////////////////////페이징////////////////////
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    /////////////////////////////페이징////////////////////
  },
  created() {
    axios.get("/api/userlist").then(result => {
      this.list = result.data;
    });
  },
};
</script>

<style scoped>
@import "/assets/css/allList.css";

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
</style>