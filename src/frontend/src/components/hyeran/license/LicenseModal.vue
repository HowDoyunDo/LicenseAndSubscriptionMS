<template>
  <transition name="modal">
    <!-- <v-stepper v-model="e1"> -->
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container" style="  height: 300px;
">
          <div class="modal-header">
            <slot name="header">
              <h2 class="cancel-h2" v-if="pwCheck==false">
                구독을 취소하시겠습니까?
                <a href class="modal-default-button" @click.prevent="close">&times;</a>
              </h2>
              <h2 v-if="pwCheck==true">관리자 비밀번호 입력</h2>
            </slot>
          </div>
          <!-- modal-header -->
          <div class="modal-body">
            <div v-if="pwCheck==false">
              결제 기간이 종료되는
              <strong style="color : red">{{endMonth }}</strong> 까지
              <br />구독 항목을 계속 이용할 수 있습니다.
            </div>
            <div v-if="pwCheck==true">
              <input type="password" class="pwcheck" v-model="adminPw" />
            </div>
          </div>
          <div class="modal-bottom" style="margin-top: -8px;">
            <button type="submit" @click.prevent="close">취소</button>
            <button v-if="pwCheck==false" type="submit" @click.prevent="pwCheck=true">확인</button>
            <button v-if="pwCheck==true" type="submit" @click.prevent="adminPwCheck">확인</button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { userPwCheck } from "@/api/shr/user";
import { licenseCancel } from "@/api/shr/license";
import moment from "moment";

export default {
  data() {
    return {
      pwCheck: false,
      adminPw: "",
      endDate:""
    };
  },
  props: ["modalData"],
  computed: {
    //  구독 취소 날짜 지정
    endMonth() {
      const startOfMonth = moment()
        .startOf("month")
        .format("YYYY-MM");

      const dataMonth = moment(this.modalData.end_date).format("YYYY-MM");
      
      if (startOfMonth < dataMonth) {
        const endOfMonth = moment()
          .endOf("month")
          .format("YYYY-MM-DD HH:mm:ss");
          // this.endDate = moment(endOfMonth).format
        return endOfMonth;
      } else {
        return moment(this.modalData.end_date).format("YYYY-MM-DD HH:mm:ss");
      }
    }
  },
  methods: {
    close() {
      this.$emit("closeee2");
    },

    // 구독 취소 시 PW 확인
    async adminPwCheck() {
      const userData = {
        email: await this.$store.state.userinfo.userInfo.email,
        password: this.adminPw
      };
      const { data } = await userPwCheck(userData);
      if (data == "") {
        alert("비밀번호가 일치하지 않습니다.");
      } else {
        // 구독 종료일
        this.modalData.end_date = this.endMonth;
        const { data } = await licenseCancel(this.modalData);
        if (data == 1) {
          alert("구독이 취소되었습니다.");
          this.close();
        } else {
          alert("구독 취소 실패");
        }
      }
    }
  }
};
</script>

<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}
.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}
.modal-container {
  width: 500px;
  height: 300px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}
.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}
.modal-body {
  margin: 20px 0;
  font-size: 17px;
  line-height: 26px;
}
.modal-default-button {
  float: right;
  text-decoration: none;
  color: #999;
}
.modal-bottom {
  margin-top: -8px;
}
/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */
.modal-enter {
  opacity: 0;
}
.modal-leave-active {
  opacity: 0;
}
.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
button {
  margin: 10px;
}
.cancel-h2 {
  font-size: 26px;
  font: bold;
}
.pwcheck {
  font-size: 100%;
  border: 1px solid #dae1e7;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  padding: 0.5rem 0.75rem;
  margin: 5px;
  width: 90%;
  max-width: 95%;
}
input{
     outline:none;

}
</style>