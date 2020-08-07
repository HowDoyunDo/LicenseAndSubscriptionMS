<template>
  <div class="contents">
    <div>
      <div style="margin: 20px 0 10px;">
        <h1
          style="cursor:pointer;"
          :style="{ color: changeColor }"
          @click="changeclick"
        >
          변경 내역
        </h1>
        <h1 style="cursor:default">&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;</h1>
        <h1
          style="cursor:pointer;"
          :style="{ color: cancelColor }"
          @click="cancelclick"
        >
          취소 내역
        </h1>
      </div>
      <br />
      <div style="margin: 0 0 10px 0; height:40px">
        <select
          v-if="clicked === true"
          v-model="selected"
          style="margin: 0 5px 0 0; height: 40px; float:left"
        >
          <option value="co_name">기업명</option>
          <option value="name">관리자명</option>
        </select>
        <select
          v-if="clicked === false"
          v-model="selected"
          style="margin: 0 5px 0 0; height: 40px;"
        >
          <option value="co_name">기업명</option>
          <option value="name">관리자명</option>
        </select>
        <input
          v-if="selected === 'co_name'"
          style="height: 40px; width:260px;"
          id="myInput"
          type="text"
          v-on:input="keyword = $event.target.value"
          @input="resetPageNum"
          placeholder="검색어 입력"
        />
        <input
          v-if="selected === 'name'"
          style="height: 40px; width:260px;"
          id="myInput"
          type="text"
          v-on:input="keyword = $event.target.value"
          @input="resetPageNum"
          placeholder="검색어 입력"
        />
      </div>
      <table v-if="clicked" class="table_board">
        <tr style="float:center;">
          <th style="width:5%;">번호</th>
          <th style="width:20%;">기업명 [관리자]</th>
          <th style="width:30%">구독 정책명</th>
          <th style="width:10%;">시작일</th>
          <th style="width:10%;">종료일</th>
          <th style="width:10%;">변경일</th>
        </tr>
        <tr
          style="cursor:default"
          v-for="(change, idx) in changepaginatedData"
          :key="change.no"
        >
          <td>{{ idx + 1 }}</td>
          <td>{{ change.co_name }} [{{ change.name }}]</td>
          <td>{{ change.policy_title }}</td>
          <td>{{ change.start_date }}</td>
          <td>{{ change.end_date }}</td>
          <td>{{ change.change_date }}</td>
        </tr>
      </table>
      <div v-if="changefilteredList.length === 0 && clicked" style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;">존재하지 않습니다.<hr></div>
      <!-- 페이징 -->
      <br v-if="clicked" />
      <div v-if="clicked" class="btn-cover" style="text-align: center">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          이전
        </button>
        <span class="page-count" v-if="changefilteredList.length === 0">
          {{ pageNum + 1 }} / 1
        </span>
        <span class="page-count" v-else>
          {{ pageNum + 1 }} / {{ changepageCount }}
        </span>
        <button
          :disabled="pageNum >= changepageCount - 1"
          @click="nextPage"
          class="page-btn"
        >
          다음
        </button>
      </div>
      <!-- 페이징 -->

      <table v-if="!clicked" class="table_board">
        <tr style="float:center;">
          <th style="width:5%">번호</th>
          <th style="width:20%;">기업명 [관리자]</th>
          <th style="width:30%">구독 정책명</th>
          <th style="width:10%">취소일</th>
        </tr>
        <tr
          style="cursor:default"
          v-for="(cancel, idx) in cancelpaginatedData"
          :key="cancel.no"
        >
          <td>{{ idx + 1 }}</td>
          <td>{{ cancel.co_name }} [{{ cancel.name }}]</td>
          <td>{{ cancel.policy_title }}</td>
          <td>{{ cancel.cancel_date }}</td>
        </tr>
      </table>
      <div v-if="cancelfilteredList.length === 0 && !clicked" style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;">존재하지 않습니다.<hr></div>
      <!-- 페이징 -->
      <br v-if="!clicked" />
      <div v-if="!clicked" class="btn-cover" style="text-align: center">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          이전
        </button>
        <span class="page-count" v-if="cancelfilteredList.length === 0">
          {{ pageNum + 1 }} / 1
        </span>
        <span class="page-count" v-else>
          {{ pageNum + 1 }} / {{ cancelpageCount }}
        </span>
        <button
          :disabled="pageNum >= cancelpageCount - 1"
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
      clicked: true,
      cancellist: "",
      changelist: "",
      changeColor: "#3498db",
      cancelColor: "black",
      selected: "co_name",
      keyword: "",
      /////////////////////////////페이징////////////////////
      pageNum: 0,
      pageSize: 10,
      /////////////////////////////페이징////////////////////
    };
  },
  computed: {
    // 변경 내역 필터링 리스트
    changefilteredList() {
      return Object.values(this.changelist).filter((post) => {
        if (this.selected === "name") {
          return post.name.toLowerCase().includes(this.keyword.toLowerCase());
        } else if (this.selected === "co_name") {
          return post.co_name
            .toLowerCase()
            .includes(this.keyword.toLowerCase());
        }
      });
    },
    // 취소 내역 필터링 리스트
    cancelfilteredList() {
      return Object.values(this.cancellist).filter((post) => {
        if (this.selected === "name") {
          return post.name.toLowerCase().includes(this.keyword.toLowerCase());
        } else if (this.selected === "co_name") {
          return post.co_name
            .toLowerCase()
            .includes(this.keyword.toLowerCase());
        }
      });
    },
    /////////////////////////////페이징////////////////////
    changepageCount() {
      let listLeng = this.changefilteredList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;

      return page;
    },
    changepaginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.changefilteredList.slice(start, end);
    },
    cancelpageCount() {
      let listLeng = this.cancelfilteredList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;

      return page;
    },
    cancelpaginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.cancelfilteredList.slice(start, end);
    },
    /////////////////////////////페이징////////////////////
  },
  methods: {
    // 변경 페이지 css 활성화
    changeclick() {
      this.clicked = true;
      if (this.clicked == true) {
        this.changeColor = "#3498db";
        this.cancelColor = "black";
      }
    },
    // 취소 페이지 css 활성화
    cancelclick() {
      this.clicked = false;
      if (this.clicked == false) {
        this.cancelColor = "#3498db";
        this.changeColor = "black";
      }
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
  created() {
    axios.get("/api/cancellist").then((result) => {
      this.cancellist = result.data;
    });
    axios.get("/api/changelist").then((result) => {
      this.changelist = result.data;
    });
  },
};
</script>

<style scoped>
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
h1 {
  display: inline;
  font-size: 4.5rem;
  color: #000000d9;
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
}
</style>
