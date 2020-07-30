<template>
  <div>
    <table class="table_board">
      <tr>
        <th style="width:10%;">번호</th>
        <th style="width:18%;">프로모션명</th>
        <th style="width:15%;">정책명(번호)</th>
        <th style="width:10%;">할인율</th>
        <th style="width:15%;">프로모션 기간</th>
        <th style="width:10%;">진행여부</th>
        <th style="width:15%;">프로모션 등록일</th>
      </tr>
      <tr
        onmouseover="this.style.background='#CEECF5';"
        onmouseout="this.style.background=''"
        style="cursor:pointer"
        v-for="(list, index) in promotionPolicyAllList"
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
      promotionPolicyAllList: ""
    };
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

  }
};
</script>

<style scoped>
</style>