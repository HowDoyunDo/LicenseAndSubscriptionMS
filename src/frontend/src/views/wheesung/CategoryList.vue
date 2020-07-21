<template>
  <div class="contents" style="border-left : 1px solid lightgray;">
    <div>
      <br>
      <h2 style="text-align:center">카테고리 목록</h2>
      <br />
      <table class="table_board">
        <tr style="float:center;">
          <th style="width:150px;">번호</th>
          <th style="width:300px;text-align: left; padding: 0 0 0 20px;">카테고리 명</th>
          <th>등록일</th>
          <th></th>
        </tr>
        <tr v-for="(category, idx) in list" :key="category.no">
          <td>{{ idx + 1 }}</td>
          <td style="text-align: left; padding: 0 0 0 20px;">{{category.title}}</td>
          <td>{{category.reg_date}}</td>
          <td>
            <input
              style="background-color:#3498db; border:none; color: #ffffff; width: 60px; height: 30px;"
              type="button"
              value="삭제"
              @click="categoryDel(category.title)"
            />
          </td>
        </tr>
      </table>
    </div>
    <br />
    <div style="text-align: center;">
      <form @submit.prevent="categoryAdd">
        <table class="table_add form">
          <tr>
            <th>카테고리 등록</th>
            <td>
              <input style="width:450px; margin: 0 15px; 0 0" type="text" v-model="title" />
              <input
                style="background-color:#3498db; border:none; color: #ffffff; width: 60px; height: 30px;"
                type="submit"
                value="등록"
              />
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {
  },
  data() {
    return {
      list: "",
      title: "",
      ca_title: "",
    };
  },
  methods: {
    categoryAdd() {
      if (this.title == null || this.title == "") {
        alert("등록할 카테고리명을 입력해 주세요/");
        return;
      } else {
        axios
          .post("/api/categoryadd", {
            title: this.title
          })
          .then(result => {
            if (result.data === "F") {
              alert("이미 존재하는 카테고리 입니다.");
              this.title = "";
              return;
            } else {
              axios.get("api/categorylist").then(result => {
                this.list = result.data;
              });
              this.title = "";
              return;
            }
          });
      }
    },
    categoryDel(title) {
      axios
        .post("/api/categorydel", {
          ca_title: title
        })
        .then(result => {
          if (result.status == 200) {
            axios.get("api/categorylist").then(result => {
              this.list = result.data;
            });
          }
        });
    },
    test(value) {
      this.pageNum = value;
    }
  },

  computed: {
    resetTitle() {
      return this.title;
    },
  },
  created() {
    axios.get("/api/categorylist").then(result => {
      this.list=result.data
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