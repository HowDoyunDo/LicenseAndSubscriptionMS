<template>
  <div>
    <table class="table_board">
      <tr>
        <th>번호</th>
        <th>구독 정책명</th>
        <th>라이선스</th>
        <th>사용 기준</th>
        <th>사용 기간</th>
        <th>결제 금액</th>
        <th>결제 현황</th>
        <th>주문일</th>
        <th>변경/취소</th>
      </tr>
      <tr
        onmouseover="this.style.background='#CEECF5';"
        onmouseout="this.style.background=''"
        style="cursor:pointer"
        v-for="(list, index) in userSubList"
        :key="list.no"
        @click="listClick(index)"
      >
        <td>{{ list. no }}</td>
        <td>{{ list.policy_title }}</td>
        <td>{{list.standard=='U' ? '사용자' : '에이전트'}}</td>
        <td>33/100 명</td>
        <td>{{list.start_date | formatDate}} ~ {{list.end_date | formatDate}} ({{list.month_count * 30}} 일)</td>
        <td>{{list.total_price | formatPrice}}</td>
        <td>{{list.month_payment==false? '결제완료' : '미결제(정기결제)'}}</td>
        <td>{{list.reg_date | formatDate}}</td>
        <td>변경 / 취소</td>
      </tr>
    </table>
  </div>
</template>

<script>
import { userSubscribeList } from "@/api/shr/subscribe";
export default {
  data() {
    return {
      userSubList: ""
    };
  },
  props: ["userInfo"],
  async created() {
    const { data } = await userSubscribeList(this.userInfo);
    this.userSubList = data;
    console.log(data);
    console.log(this.userSubList);
  },
  methods: {
    listClick(index) {
      const subscribeNo = this.userSubList[index].no;
      this.$router.push({
        name: "subscribeInfo",
        params: { subscribe_no: subscribeNo }
      });
    }
  }
};
</script>

<style>
</style>