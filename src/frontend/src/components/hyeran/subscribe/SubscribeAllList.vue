<template>
  <div>
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
        v-for="(list, index) in subAllList"
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
      subAllList: ""
    };
  },
  created() {
    this.listData();
  },
  methods: {
    async listData() {
      const { data } = await subscribeAllList();
      this.subAllList = data;
    },
    listClick(index) {
      // 클릭한 해당 구독정책번호
      const subscribeNo = this.subAllList[index].no;
      this.$router.push({
        name: "subscribeInfo",
        params: { subscribe_no: subscribeNo }
      });
    }
  },
};
</script>

<style>
</style>