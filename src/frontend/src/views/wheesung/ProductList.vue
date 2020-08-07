<template>
  <div class="contents">
    <div>
      <h1>제품 목록</h1>
      <br />
      <div class="search-wrapper btnn" style="height:50px">
        <select
          v-model="selected"
          style="margin: 0 5px 0 0; height: 40px; float: left"
        >
          <option value="category">카테고리</option>
          <option value="product">제품명</option>
        </select>
        <input
          style="margin: 0; width:260px; height:40px"
          id="myInput"
          type="text"
          v-if="selected === 'category'"
          v-on:input="keyword = $event.target.value"
          @input="resetPageNum"
          placeholder="검색어 입력"
        />
        <input
          style="margin: 0; width:260px; height:40px"
          id="myInput"
          type="text"
          v-if="selected === 'product'"
          v-on:input="keyword = $event.target.value"
          @input="resetPageNum"
          placeholder="검색어 입력"
        />
      </div>
      <table class="table_board">
        <tr style="float:center;">
          <th style="width:5%;">번호</th>
          <th style="width:15%;">[카테고리] 제품명</th>
          <th style="width:30%;">제품 설명</th>
          <th style="width:8%">등록일</th>
          <th style="width:8%;">수정 / 삭제</th>
        </tr>
        <tr v-for="(product, idx) in paginatedData" :key="product.no">
          <td>{{ idx + 1 }}</td>
          <td>[{{ product.title }}] {{ product.name }}</td>
          <td>{{ product.comments }}</td>
          <td>{{ product.reg_date }}</td>
          <td>
            <a @click="productChangeForm(product.no)" style="cursor:pointer;"
              >수정</a
            >
            /
            <a @click="productDel(product.name)" style="cursor:pointer;"
              >삭제</a
            >
          </td>
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
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      list: "",
      name: "",
      comments: "",
      price: 0,

      keyword: "",
      selected: "category",

      /////////////////////////////페이징////////////////////
      pageNum: 0,
      pageSize: 10,
      /////////////////////////////페이징////////////////////
    };
  },
  computed: {
    // 제품 정보 필터링
    filteredList() {
      return Object.values(this.list).filter((post) => {
        if (this.selected === "category") {
          return post.title.toLowerCase().includes(this.keyword.toLowerCase());
        } else if (this.selected === "product") {
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
  methods: {
    // 제품 삭제
    productDel(pname) {
      axios
        .post("/api/productdel", {
          name: pname,
        })
        .then((result) => {
          if (result.status == 200) {
            axios.get("/api/productlist").then((result) => {
              this.list = result.data;
            });
          }
        });
    },
    // 제품 정보 수정
    productChangeForm(no) {
      this.$router.push({
        name: "productchangeform",
        params: { no: no },
      });
    },
    // 제품 등록 페이지 이동
    movetoadd() {
      this.$router.push("/productadd");
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
    axios.get("/api/productlist").then((result) => {
      this.list = result.data;
    });
  },
  filters: {
    formatPrice(value) {
      if (!value) return "";
      return value.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
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
  height: 35px;
  font-size: 16px;
  padding: 12px 20px 12px 42px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

.page-count {
  font-size: 15px;
}
</style>
