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
        v-for="(list, index) in paginatedData"
        :key="list.no"
        @click="licenseInfoForm(index, list.no, list.policy_no)"
      >
        <td>{{ index + 1 }}</td>
        <td style=" text-decoration: underline">
          {{ list.policy_title
          }}
        </td>
        <td>{{ list.standard == "A" ? "에이전트" : "사용자" }}</td>
        <td v-if="list.max_count !== 0">
          {{ list.current_count }} / {{ list.max_count }}
        </td>
        <td v-if="list.max_count === 0">
          {{ list.current_count }} / 제한 없음
        </td>
        <td v-if="checkOver(list.no)" style="color:#ff6384;">
          {{ list.start_date | formatDate }} ~ {{ list.end_date | formatDate
          }}<br />만료 예정
        </td>
        <td v-else>
          {{ list.start_date | formatDate }} ~ {{ list.end_date | formatDate }}
        </td>
        <td>
          <div v-if="list.activation == 'A'">활성화</div>
          <div v-if="list.activation == 'F'">수량 가득참</div>
          <div v-if="list.activation == 'E'">기간종료</div>
          <div v-if="list.activation == 'C'">취소 신청</div>
        </td>
        <td>{{ list.license_key }}</td>
        <td>
          <button
            class="btn"
            onclick="event.cancelBubble=true"
            @click="mouseclick(index, list.standard)"
          >
            관 리
          </button>
        </td>
        <td onclick="event.cancelBubble=true">
          <template v-if="!list.policy_title.includes('체험판')">
            <a @click="licneseUpdate(list.no, list.policy_no, list.activation, list.end_date)"
              >갱신</a
            >
            /
          </template>
          <a
            @click="
              licneseChange(
                list.no,
                list.policy_no,
                list.policy_title,
                list.activation
              )
            "
            >변경</a
          >
          /
          <a
            @click="
              licneseCancel(
                list.no,
                list.order_no,
                list.user_admin_no,
                list.end_date,
                list.activation
              )
            "
            >취소</a
          >
          <modal2
            v-if="showModal"
            @closeee2="showModal = false"
            :modalData="modalData"
          />
        </td>
      </tr>
    </table>
    <div v-if="licenses.length === 0" style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;">존재하지 않습니다.<hr></div>
    <!-- 페이징 -->
      <br />
      <div class="btn-cover" style="text-align: center">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          이전
        </button>
        <span class="page-count" v-if="licenses.length === 0">
          {{ pageNum + 1 }} / 1
        </span>
        <span class="page-count" v-else>
          {{ pageNum + 1 }} / {{ pageCount }}
        </span>
        <button
          :disabled="pageNum >= pageCount - 1"
          @click="nextPage"
          class="page-btn"
        >
          다음
        </button>
      </div>
      <!-- 페이징 -->
  </div>
</template>

<script>
import LicenseModal from "@/components/hyeran/license/LicenseModal.vue";
import axios from "axios";

export default {
  data() {
    return {
      showModal: "",
      modalData: "",
      /////////////////////////////페이징////////////////////
      pageNum: 0,
      pageSize: 10,
      /////////////////////////////페이징////////////////////
    };
  },
  props: ["licenses"],
  components: {
    modal2: LicenseModal,
  },
  methods: {
    async licneseUpdate(licenseNo, policyNo, activation, enddate) {
      if (activation == "E" || activation == "C") {
        alert("라이선스를 갱신할 수 없습니다.");
      } else {
        // 해당 정책 정보 가져오기
        await axios
          .get("/api/getUpdatePolicy", {
            params: {
              policyNo: policyNo,
            },
          })
          .then((res) => {
            console.log(res.data);
            this.$store.commit("licenseStore/UPDATE_POL", res.data);
          });

        // 프로모션 정보 가져오기
        await axios
          .get("/api/api/promotionPolicyCheckPNo", {
            params: {
              policyNo: policyNo,
            },
          })
          .then((res) => {
            this.$store.commit("productStore/SET_PROMOTION", res.data);
            console.log(res.data);
          });
          
        this.$store.commit("licenseStore/UPDATE_UED", enddate);
        this.$store.commit("licenseStore/UPDATE_LCN", licenseNo);

        // 이동
        this.$router.push({ name: "UpdateLicense" });
      }
    },
    licneseChange(license_no, policy_no, policy_title, activation) {
      if (activation == "E" || activation == "C") {
        alert("변경/취소 할 수 없습니다.");
      } else {
        this.$router.push({
          name: "licenseChange",
          params: {
            license_no: license_no,
            policy_title: policy_title,
          },
        });
      }
    },
    // 라이선스(구독 정책) 상세보기
    licenseInfoForm(index, licenseNo, policyNo) {
      this.$router.push({
        name: "licnesePolicyInfo",
        params: { license_no: licenseNo, policy_no: policyNo },
      });
    },

    licneseCancel(licenseNo, orderNo, userAdminNo, endDate, activation) {
      if (activation == "E" || activation == "C") {
        alert("변경/취소 할 수 없습니다.");
      } else {
        this.showModal = true;
        this.modalData = {
          no: licenseNo,
          order_no: orderNo,
          user_admin_no: userAdminNo,
          end_date: endDate,
        };
      }
    },
    mouseclick(idx, standard) {
      this.$store.commit("licenseStore/SELECT_LIC", this.licenses[idx]);
      this.$router.push({
        name: "LicenseInfo",
        params: { licenseno: idx, licensetype: standard },
      });
    },
    checkOver(lcNo) {
      for (var i = 0; i < this.alertOverLicense.length; i++) {
        if (lcNo === this.alertOverLicense[i].no) return true;
      }
    },
    /////////////////////////////페이징////////////////////
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    resetPageNum(){
      this.pageNum = 0;
    }
    /////////////////////////////페이징////////////////////
  },
  computed: {
    alertOverLicense: function() {
      return this.$store.state.licenseStore.alertOverLicense;
    },
    /////////////////////////////페이징////////////////////
    pageCount() {
      let listLeng = this.licenses.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.licenses.slice(start, end);
    },
    /////////////////////////////페이징////////////////////
  },
};
</script>

<style scoped>
.btn {
  width: 55px;
}
</style>
