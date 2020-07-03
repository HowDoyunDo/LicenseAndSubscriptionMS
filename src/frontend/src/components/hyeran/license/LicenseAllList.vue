<template>
  <div>
    <h1> 시스템관리자용, </h1>
    <table class="table_board">
      <tr>
        <th style="width:5%">번호</th>
        <th style="width:17%">구독 정책명(라이센스 상세)</th>
        <th style="width:7%">이용 기준</th>
        <th style="width:8%">
          현재/최대
          <br />사용 수량
        </th>
        <th style="width:13%">사용 기간</th>

        <th style="width:10%">결제 현황</th>
        <th style="width:20%">라이센스 키</th>
        <th style="width:10%">주문일</th>
        <th style="width:10%">변경 / 취소</th>
      </tr>
      <tr
        onmouseenter="this.style.background='#CEECF5';"
        onmouseleave="this.style.background=''"
        style="cursor:pointer"
        v-for="(list, index) in licenses"
        :key="list.no"
        @click="listClick(index)"
      >
        <td>{{list.no}}</td>
        <td
          onclick="event.cancelBubble=true"
          @click="licenseInfoForm(list.policy_no)"
          onmouseenter="this.style.color='blue';"
          onmouseleave="this.style.color=''"
           style="text-decoration: underline"
        >{{list.policy_title}}[{{list.policy_no}}]</td>
        <td>에이전트</td>
        <td>30/100</td>
        <td>{{list.start_date | formatDate }} ~ {{list.end_date | formatDate}}</td>
        <td>일반 결제 <br>완료</td>
        <td>{{list.license_key}}</td>
        <td>2020-07-01</td>
        <td onclick="event.cancelBubble=true">
          <a @click="test">변경</a> /
          <a>취소</a>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import { licenseAllList } from "@/api/shr/license";

export default {
  data() {
    return {
      licenses: ""
    };
  },
  async created() {
    const { data } = await licenseAllList();
    this.licenses = data;
  },
  methods: {
    listClick() {
      console.log("클릭");
      this.$router.push("/subscribe/alllist");
    },
    test() {
      console.log("testestesteste");
    },
    // 라이선스(구독 정책) 상세보기
    licenseInfoForm(policyNo) {
      console.log("dlicenseInfoForm", policyNo);
      
    }
  }
};
</script>

<style>
</style>