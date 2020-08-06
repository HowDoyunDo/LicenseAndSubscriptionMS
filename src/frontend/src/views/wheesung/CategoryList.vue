<template>
  <div class="contents">
    <div>
      <h1>카테고리 목록</h1>
      <br />
      <table class="table_board">
        <tr style="float:center;">
          <th style="width:100px;">번호</th>
          <th style="width:100px;">아이콘</th>
          <th style="width:300px;text-align: left; padding: 0 0 0 40px;">
            카테고리 명
          </th>
          <th>등록일</th>
          <th></th>
        </tr>
        <tr v-for="(category, idx) in list" :key="category.no">
          <td>{{ idx + 1 }}</td>
          <td>
            <img :src="category.category_image" width="30px" height="30px" />
          </td>
          <td style="text-align: left; padding: 0 0 0 40px;">
            {{ category.title }}
          </td>
          <td>{{ category.reg_date }}</td>
          <td>
            <input
              style="background-color:#3498db; border:none; color: #ffffff; width: 60px; height: 30px; border-radius:4px"
              type="button"
              value="삭제"
              @click="categoryDel(category.title)"
            />
          </td>
        </tr>
      </table>
    </div>
    <br />
    <div>
      <form @submit.prevent="categoryAdd">
        <h1>카테고리 등록</h1>
        <br />
        <div id="inner">
          <table class="table_add form">
            <tr>
              <th>카테고리 명</th>
              <td>
                <input
                  style="width:600px; margin: 0 15px; 0 0"
                  type="text"
                  placeholder="카테고리 명을 입력하세요."
                  v-model="title"
                />
              </td>
            </tr>
            <tr>
              <th>카테고리 이미지</th>
              <td>
                <input
                  type="file"
                  style="width:450px; margin: 0 15px; 0 0"
                  id="categoryimage"
                  @change="inputCategoryImage"
                  accept="image/gif, image/jpeg, image/png, image/jpg, image/ico"
                />
              </td>
            </tr>
          </table>
        

          <br />
          <div style="text-align:center">
            <input
              style="background-color:#3498db; border:none; color: #ffffff; width: 60px; height: 30px; border-radius:4px"
              type="submit"
              value="등록"
            />
          </div>
          <br>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  data() {
    return {
      list: "",
      title: "",
      ca_title: "",
      category_image: "",
    };
  },
  methods: {
    categoryAdd() {
      if (this.title == null || this.title == "") {
        alert("등록할 카테고리명을 입력해 주세요.");
        return;
      } else if (this.category_image == "" || this.category_image == null) {
        alert("카테고리 이미지를 넣어주세요");
      } else {
        console.log(this.category_image);
        axios
          .post("/api/categoryadd", {
            title: this.title,
            category_image: this.category_image,
          })
          .then((result) => {
            if (result.data === "F") {
              alert("이미 존재하는 카테고리 입니다.");
              this.title = "";
              return;
            } else {
              axios.get("api/categorylist").then((result) => {
                this.list = result.data;
              });
              this.title = "";
              document.getElementById("categoryimage").value = "";
              return;
            }
          });
      }
    },
    categoryDel(title) {
      axios
        .post("/api/categorydel", {
          ca_title: title,
        })
        .then((result) => {
          console.log(result);
          if (result.data == "S") {
            axios.get("api/categorylist").then((result) => {
              this.list = result.data;
            });
          } else {
            alert("카테고리 하위 제품이 있습니다.");
          }
        });
    },
    inputCategoryImage(e) {
      if (e.target.files[0] == undefined) {
        this.category_image = "";
      }
      if (e.target.files.length > 0) {
        if (
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "gif" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "jpg" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "jpeg" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "png" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "ico"
        ) {
          const file = e.target.files[0];
          const reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onload = (e) => {
            this.category_image = e.target.result;
          };
        } else {
          alert("이미지 파일을 올려주세요.");
          this.category_image = "";
          document.getElementById("categoryimage").value = "";
        }
      }
    },
  },

  computed: {
    resetTitle() {
      return this.title;
    },
  },
  created() {
    axios.get("/api/categorylist").then((result) => {
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

.table_board td img {
  background: gray;
}
#inner {
  width: 100%;
  display: inline-block;
  border: 1px solid #ccc;
  padding:10px;
}
</style>
