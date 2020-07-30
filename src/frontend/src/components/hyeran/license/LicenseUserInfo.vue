<template>
  <div>
    <form class="form">
      <table class="table_board">
        <tr>
          <th style="width:10%">번호</th>
          <th style="width:15%">이름</th>
          <th style="width:25%">이메일</th>
          <th style="width:15%">부서명</th>
          <th style="width:20%">전화번호</th>
          <th style="width:30%">마지막 로그인</th>
        </tr>
        <tr v-for="(gu, idx) in adminUserList" v-bind:key="gu.no">
          <td>{{ idx+1 }}</td>
          <td>{{ gu.name }}</td>
          <td>{{ gu.email }}</td>
          <td>{{ gu.co_name }}</td>
          <td>{{ gu.co_tel | phoneFomatter}}</td>
          <td>{{ gu.last_login | format}}</td>
        </tr>
      </table>
    </form>
  </div>
</template>

<script>
import { licenseAdminUser } from "@/api/shr/license";
import moment from "moment";
export default {
  data() {
    return {
      adminUserList: ""
    };
  },
  props: ["license_no"],
  async created() {
    const { data } = await licenseAdminUser({ no: this.license_no });
    this.adminUserList = data;
  },
  filters: {
    format: function(value) {
      return moment(value).format("YYYY-MM-DD HH:mm:ss");
    },

    phoneFomatter(num, type) {
      var formatNum = "";
      if (num.length == 11) {
        if (type == 0) {
          formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, "$1-****-$3");
        } else {
          formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3");
        }
      } else if (num.length == 8) {
        formatNum = num.replace(/(\d{4})(\d{4})/, "$1-$2");
      } else {
        if (num.indexOf("02") == 0) {
          if (type == 0) {
            formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, "$1-****-$3");
          } else {
            formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, "$1-$2-$3");
          }
        } else {
          if (type == 0) {
            formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, "$1-***-$3");
          } else {
            formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, "$1-$2-$3");
          }
        }
      }
      return formatNum;
    }
  }
};
</script>

<style scoped>
</style>