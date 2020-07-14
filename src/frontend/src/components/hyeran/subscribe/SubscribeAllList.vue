<template>
  <div>
    <div class="search-wrapper">
      <input id="myInput" type="text" v-model="keyword" placeholder="검색어 입력" />
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
        v-for="(list, index) in filteredList"
        :key="list.no"
        @click="listClick(index)"
      >
        <td>{{ list.no }}</td>
        <td style="text-align: left;">
          <span style="text-decoration-line: underline;">{{ list.policy_title }}</span>
        </td>
        <td>{{ list.standard=='U' ? '사용자' : '에이전트' }}</td>
        <td>{{ list.max_count | formatPrice}}</td>
        <td>{{ list.price | formatPrice }}</td>
        <td>{{ list.visible==true?'공개':'비공개'}}</td>
        <td>{{ list.url }}</td>
        <td>{{ list.reg_date | formatDate }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import { subscribeAllList } from "@/api/shr/subscribe";

export default {
  data() {
    return {
      subAllList: "",
      keyword: ""
    };
  },
  computed: {
    filteredList() {
      return Object.values(this.subAllList).filter(post => {
        return post.policy_title
          .toLowerCase()
          .includes(this.keyword.toLowerCase());
      });
    }
  },
  created() {
    this.listData();
  },
  methods: {
    async listData() {
      const { data } = await subscribeAllList();
      this.subAllList = data;
      // console.log(typeof this.subAllList);

      // let newArray = [];
      // newArray =  Object.keys(this.subAllList).map(function(_) {
      //   return this.subAllList[_];
      // });
      // console.log(newArray);
      // console.log('오브오브오브',Object.values(this.subAllList))
    },
    listClick(index) {
      // 클릭한 해당 구독정책번호
      const subscribeNo = this.subAllList[index].no;
      this.$router.push({
        name: "subscribeInfo",
        params: { subscribe_no: subscribeNo }
      });
    }
  }
  //   filters: {
  //   activePolicyList(value) {
  //     if (value.standard == "A") {
  //       console.log(value);
  //       return "";
  //     }
  //     // return this.policyList.filter(function(standard) {
  //     //   console.log(standard);
  //     // return value ;

  //     // });
  //   }
  // },
};
</script>

<style>
/* .field {
 font-size: 100%;
    border: 1px solid #dae1e7;
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
    padding: 0.5rem 0.75rem;
    margin: 5px;
    width: 490px;
    max-width: 95%;
     outline:none;
} */

#myInput { 
  /* background-image: image('@/assets/images/searchIcon.png'); */
  background-image: url('/src/assets/images/minus.png');
  background-repeat: no-repeat;
  background-position: left center;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}
</style>