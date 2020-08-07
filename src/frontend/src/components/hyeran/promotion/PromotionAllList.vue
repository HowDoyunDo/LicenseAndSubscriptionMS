<template>
  <div>
    <div class="search-wrapper btnn">
      <select v-model="selected" style="margin: 0 5px 0 0; height: 30px; float: left">
        <option value="title">프로모션명</option>
        <option value="product_name">제품명</option>
      </select>
      <input
        v-if="selected==='title'"
        id="myInput"
        type="text"
        style="float: left;"
        v-on:input="keyword = $event.target.value"
        @input="resetPageNum"
        placeholder="검색어 입력"
      />
      <input
        v-if="selected==='product_name'"
        id="myInput"
        type="text"
        style="float: left;"
        v-on:input="keyword = $event.target.value"
        @input="resetPageNum"
        placeholder="검색어 입력"
      />
      <button class="submit_btn" @click="promotionAdd">등록</button>
    </div>
    <table class="table_board">
      <tr>
        <th style="width:10%;">번호</th>
        <th style="width:21%;">프로모션명</th>
        <th style="width:12%;">제품명(번호)</th>
        <th style="width:10%;">할인율</th>
        <th style="width:15%;">프로모션 기간</th>
        <th style="width:10%;">진행여부</th>
        <th style="width:15%;">프로모션 등록일</th>
      </tr>
      <tr
        onmouseover="this.style.background='#CEECF5';"
        onmouseout="this.style.background=''"
        style="cursor:pointer"
        v-for="(list, index) in paginatedData"
        @click="listClick(index)"
        :key="list.no"
      >
        <td>{{ index + 1 }}</td>
        <td style="text-align: left;">
          <span style="text-decoration-line: underline;">{{ list.title }}</span>
        </td>
        <td style="text-align: left;">{{list.product_name}} ({{ list.product_no }})</td>
        <td>{{ list.discount }}%</td>
        <td>{{ list.start_date | formatDate}} ~ {{ list.end_date | formatDate}}</td>
        <td>{{ list.type == true ? '진행중' : '진행안함'}}</td>
        <td>{{ list.reg_date | formatDate }}</td>
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
</template>

<script>
import { promotionAllList } from "@/api/shr/promotion";

export default {
  data() {
    return {
      promotionAllList: "",
      keyword: "",
      selected: "title",

      /////////////////////////////페이징////////////////////
      pageNum: 0,
      pageSize: 10,
      /////////////////////////////페이징////////////////////
    };
  },
  computed: {
    filteredList() {
      return Object.values(this.promotionAllList).filter((post) => {
        if (this.selected === "title") {
          return post.title.toLowerCase().includes(this.keyword.toLowerCase());
        } else if (this.selected === "product_name") {
          return post.product_name
            .toLowerCase()
            .includes(this.keyword.toLowerCase());
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
  async created() {
    const { data } = await promotionAllList();
    this.promotionAllList = data;
  },
  methods: {
    // 상세보기
    listClick(index) {
      // 클릭한 해당 프로모션번호
      const promotionNo = this.promotionAllList[index].promotion_no;
      this.$router.push({
        name: "promotionInfo",
        params: { promotion_no: promotionNo },
      });
    },
    promotionAdd() {
      this.$router.push("/promotion/add");
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
};
</script>

<style scoped>
#myInput {
  background-image: url("../../../assets/images/searchIcon2.png");
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
.btnn {
  text-align: right;
}
button {
  width: 80px;
}
</style>