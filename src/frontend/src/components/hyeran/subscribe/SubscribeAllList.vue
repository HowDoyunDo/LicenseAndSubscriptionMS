<template>
  <div>
    <div class="search-wrapper btnn">
      <input
        style="float:left; height:40px"
        id="myInput"
        type="text"
        v-on:input="keyword = $event.target.value"
        @input="resetPageNum"
        placeholder="검색어 입력"
      />
      <button class="submit_btn" @click="subscribeAdd">등록</button>
    </div>
    <table class="table_board">
      <tr>
        <th style="width:6%;">번호</th>
        <th style="width:25%;">구독 정책명</th>
        <th style="width:10%;">이용 기준</th>
        <th style="width:10%">수량</th>
        <th style="width:8%;">가격</th>
        <th style="width:9%;">공개여부</th>
        <th style="width:19%;">URL</th>
        <th style="width:15%;">등록일</th>
      </tr>
      <tr
        onmouseover="this.style.background='#CEECF5';"
        onmouseout="this.style.background=''"
        style="cursor:pointer"
        v-for="(list, index) in paginatedData"
        :key="list.no"
        @click="listClick(list.no)"
      >
        <td>{{ index+1 }}</td>
        <td style="text-align: left;">
          <span style="text-decoration-line: underline;">{{ list.policy_title }}</span>
        </td>
        <td>{{ list.standard=='U' ? '사용자' : '에이전트' }}</td>
        <td v-if="list.max_count === 0">제한 없음</td>
        <td v-else>{{ list.max_count | formatPrice}}</td>
        <td>{{ list.price | formatPrice }}</td>
        <td>{{ list.visible==true?'공개':'비공개'}}</td>
        <td>{{ list.url }}</td>
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
import { subscribeAllList } from "@/api/shr/subscribe";

export default {
  data() {
    return {
      subAllList: "",
      keyword: "",

      /////////////////////////////페이징////////////////////
      pageNum: 0,
      pageSize: 10,
      /////////////////////////////페이징////////////////////
    };
  },
  computed: {
    filteredList() {
      return Object.values(this.subAllList).filter((post) => {
        return post.policy_title
          .toLowerCase()
          .includes(this.keyword.toLowerCase());
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
    this.listData();
  },
  methods: {
    async listData() {
      const { data } = await subscribeAllList();
      this.subAllList = data;
    },
    subscribeAdd() {
      this.$router.push("/subscribe/add");
    },
    listClick(index) {
      // 클릭한 해당 구독정책번호
      const subscribeNo = index;
      this.$router.push({
        name: "subscribeInfo",
        params: { subscribe_no: subscribeNo },
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
  margin-bottom: 10px;
}
input {
  outline: none;
}
.btnn {
  text-align: right;
}
button {
  width: 80px;
  height: 40px;
}
</style>