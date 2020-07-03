<template>
  <div>
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

        <th style="width:10%">상태</th>
        <th style="width:20%">라이선스 키</th>
        <th style="width:10%">라이선스 관리</th>
        <th style="width:10%">변경 / 취소</th>
      </tr>
      <tr
        onmouseenter="this.style.background='#CEECF5';"
        onmouseleave="this.style.background=''"
        style="cursor:pointer;"
        v-for="(list, index) in licenses"
        :key="list.no"
        @click="licenseInfoForm(index, list.no, list.policy_no)"
      >
        <td>{{list.no}}</td>
        <td style=" text-decoration: underline">{{list.policy_title}}[{{list.policy_no}}]</td>
        <td>{{list.standard == 'A' ? '사용자' : ' 에이전트'}}</td>
        <td>{{ list.current_count }} / {{ list.max_count }}</td>
        <td>{{list.start_date | formatDate }} ~ {{list.end_date | formatDate}}</td>
        <td>
          <div v-if="list.activation=='A'">활성화</div>
          <div v-if="list.activation=='F'">수량 가득참</div>
          <div v-if="list.activation=='E'">기간종료</div>
          <div v-if="list.activation=='C'">취소</div>
        </td>
        <td>{{list.license_key}}</td>
        <td>
          <button
            class="btn"
            onclick="event.cancelBubble=true"
            @click="mouseclick(index, list.standard)"
          >관 리</button>
        </td>
        <td onclick="event.cancelBubble=true">
          <a @click="licneseChange">변경</a> /
          <a @click="licneseCancel(list.no, list.order_no, list.user_admin_no,list.end_date)">취소</a>
          <modal2 v-if="showModal" @closeee2="showModal=false" :modalData="modalData" />
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import LicenseModal from "@/components/hyeran/license/LicenseModal.vue";
export default {
  data() {
    return {
      showModal: "",
      modalData: ""
    };
  },
  props: ["licenses"],
  components: {
    modal2: LicenseModal
  },
  methods: {
    licneseChange() {
      this.$router.push({
        name: "",
        params: {}
      });
    },
    // 라이선스(구독 정책) 상세보기
    licenseInfoForm(index, licenseNo, policyNo) {
      this.$router.push({
        name: "licnesePolicyInfo",
        params: { license_no: licenseNo, policy_no: policyNo }
      });
    },
    licneseCancel(licenseNo, orderNo, userAdminNo, endDate) {
      this.showModal = true;
      this.modalData = {
        no: licenseNo,
        order_no: orderNo,
        user_admin_no: userAdminNo,
        end_date: endDate
      };
    },
    mouseclick(idx, standard) {
      this.$store.commit("licenseStore/SELECT_LIC", this.licenses[idx]);
      this.$router.push({
        name: "LicenseInfo",
        params: { licenseno: idx, licensetype: standard }
      });
    }
  }
};
</script>

<style>
.btn {
  width: 55px;
  margin-top: 1px;
}
</style>