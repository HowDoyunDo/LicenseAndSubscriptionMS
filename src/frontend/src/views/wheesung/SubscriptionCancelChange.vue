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
      <div style="margin: 0 0 10px 0;">
        <select
          v-if="clicked === true"
          v-model="selected"
          style="margin: 0 5px 0 0; height: 30px;"
        >
          <option value="co_name">기업명</option>
          <option value="name">관리자명</option>
        </select>
        <select
          v-if="clicked === false"
          v-model="selected"
          style="margin: 0 5px 0 0; height: 30px;"
        >
          <option value="co_name">기업명</option>
          <option value="name">관리자명</option>
        </select>
        <input
          v-if="selected === 'co_name'"
          style="margin: 8px 0 0 0;"
          id="myInput"
          type="text"
          v-on:input="keyword = $event.target.value"
          placeholder="검색어 입력"
        />
        <input
          v-if="selected === 'name'"
          style="margin: 8px 0 0 0;"
          id="myInput"
          type="text"
          v-on:input="keyword = $event.target.value"
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
          v-for="(change, idx) in changefilteredList"
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

      <table v-if="!clicked" class="table_board">
        <tr style="float:center;">
          <th style="width:5%">번호</th>
          <th style="width:20%;">기업명 [관리자]</th>
          <th style="width:30%">구독 정책명</th>
          <th style="width:10%">취소일</th>
        </tr>
        <tr
          style="cursor:default"
          v-for="(cancel, idx) in cancelfilteredList"
          :key="cancel.no"
        >
          <td>{{ idx + 1 }}</td>
          <td>{{ cancel.co_name }} [{{ cancel.name }}]</td>
          <td>{{ cancel.policy_title }}</td>
          <td>{{ cancel.cancel_date }}</td>
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
      clicked: true,
      cancellist: "",
      changelist: "",
      changeColor: "#3498db",
      cancelColor: "black",
      selected: "co_name",
      keyword: "",
    };
  },
  computed: {
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
  },
  methods: {
    changeclick() {
      this.clicked = true;
      if (this.clicked == true) {
        this.changeColor = "#3498db";
        this.cancelColor = "black";
      }
    },
    cancelclick() {
      this.clicked = false;
      if (this.clicked == false) {
        this.cancelColor = "#3498db";
        this.changeColor = "black";
      }
    },
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
  margin-bottom: 12px;
}
</style>
