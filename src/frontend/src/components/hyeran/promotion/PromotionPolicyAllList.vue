<template>
  <div>
    <div class="search-wrapper btnn">
      <select v-model="selected" style="margin: 0 5px 0 0; height: 40px; float: left">
        <option value="title">프로모션명</option>
        <option value="policy_title">정책명</option>
      </select>
      <input
        v-if="selected==='title'"
        id="myInput"
        type="text"
        style="float: left; height: 40px; width: 260px;"
        v-on:input="keyword = $event.target.value"
        placeholder="검색어 입력"
      />
      <input
        v-if="selected==='policy_title'"
        id="myInput"
        type="text"
        style="float: left; height: 40px; width: 260px;"
        v-on:input="keyword = $event.target.value"
        placeholder="검색어 입력"
      />
      <button class="submit_btn" @click="promotionAdd" style="height:40px">등록</button>
    </div>

    <table class="table_board">
      <tr>
        <th style="width:5%;">번호</th>
        <th style="width:18%;">프로모션명</th>
        <th style="width:15%;">정책명(번호)</th>
        <th style="width:5%;">할인율</th>
        <th style="width:15%;">프로모션 기간</th>
        <th style="width:10%;">진행여부</th>
        <th style="width:15%;">프로모션 등록일</th>
      </tr>
      <tr
        onmouseover="this.style.background='#CEECF5';"
        onmouseout="this.style.background=''"
        style="cursor:pointer"
        v-for="(list, index) in filteredList"
        @click="listClick(index)"
        :key="list.no"
      >
        <td>{{ list.promotion_no }}</td>
        <td style="text-align: left;">
          <span style="text-decoration-line: underline;">{{ list.title }}</span>
        </td>
        <td style="text-align: left;">{{list.policy_title}} ({{ list.policy_no }})</td>
        <td>{{ list.discount }}%</td>
        <td>{{ list.start_date | formatDate}} ~ {{ list.end_date | formatDate}}</td>
        <td>{{ list.type == true ? '진행중' : '진행안함'}}</td>
        <td>{{ list.reg_date | formatDate }}</td>
       
      </tr>
    </table>
  </div>
</template>

<script>
import { promotionPolicyAllList } from "@/api/shr/promotion";

export default {
  data() {
    return {
      promotionPolicyAllList: "",
      keyword: "",
      selected: "title",
    };
  },
  computed: {
    filteredList() {
      return Object.values(this.promotionPolicyAllList).filter((post) => {
        if (this.selected === "title") {
          return post.title.toLowerCase().includes(this.keyword.toLowerCase());
        } else if (this.selected === "policy_title") {
          return post.policy_title
            .toLowerCase()
            .includes(this.keyword.toLowerCase());
        }
      });
    },
  },
  async created() {
    const { data } = await promotionPolicyAllList();
    this.promotionPolicyAllList = data;
  },
  methods: {
    // 상세보기
    listClick(index) {
     // 클릭한 해당 프로모션번호
      const promotionNo = this.promotionPolicyAllList[index].promotion_no;
      this.$router.push({
        name: "promotionPolicyInfo",
        params: { promotion_no: promotionNo }
      });
    },
    promotionAdd() {
      this.$router.push("/promotion/add");
    },
  },
};
</script>

<style scoped>
button {
  width: 80px;
  float: right;
}
#myInput {
  background-image: url("../../../assets/images/searchIcon2.png");
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