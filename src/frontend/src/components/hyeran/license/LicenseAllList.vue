<template>
  <div>
    A : 활성화
    F : 수량 가득참
    E : 기간종료
    C : 취소
    
    <table class="table_board">
      <tr>
        <th style="width:5%">번호</th>
        <th style="width:17%">구독 정책명</th>
        <th style="width:20%">라이선스 키</th>
        <th style="width:7%">이용 기준</th>
        <th style="width:8%">
          현재/최대
          <br />사용 수량
        </th>
        <th style="width:13%">사용 기간</th>
        <th style="width:10%">기업명 [관리자]</th>
        <th style="width:10%">상태</th>
      </tr>
      <tr
        onmouseenter="this.style.background='#CEECF5';"
        onmouseleave="this.style.background=''"
        style="cursor:pointer;"
        v-for="(list, index) in licenses"
        :key="list.no"
        @click="licneseChange(index, list.no, list.policy_no)"
      >
        <td>{{list.no}}</td>
        <td style=" text-decoration: underline">{{list.policy_title}}[{{list.policy_no}}]</td>
        <td>{{list.license_key}}</td>
        <td>{{list.standard == 'A' ? '사용자' : ' 에이전트'}}</td>
        <td>{{ list.current_count }} / {{ list.max_count }}</td>
        <td>{{list.start_date | formatDate }} ~ {{list.end_date | formatDate}}</td>
        <td>{{list.co_name}} [{{list.name}}]</td>
        <td>
          <div v-if="list.activation=='A'">활성화</div>
          <div v-if="list.activation=='F'">수량 가득참</div>
          <div v-if="list.activation=='E'">기간종료</div>
          <div v-if="list.activation=='C'">취소 신청</div>
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
    // userShow() {
    //   this.$router.push("/license/useradmin");
    // },
     licneseChange(index, licenseNo, policyNo) {
       this.$router.push({
        name: "licnesePolicyInfo",
        params: { license_no: licenseNo, policy_no: policyNo, pageTypeAdmin : true }
      });
    },
  }
};
</script>

<style>
</style>