<template>
  <div>
    <div style="margin: 0 0 10px 0;">
      <select v-model="selected" style="margin: 0 5px 0 0; height: 40px; float:left">
        <option value="policy_title">구독 정책명</option>
        <option value="co_name">기업명</option>
        <option value="name">관리자명</option>
      </select>
      <input
        v-if="selected==='policy_title'"
        style="margin: 0; height:40px; width: 260px;"
        id="myInput"
        type="text"
        v-on:input="keyword = $event.target.value"
        placeholder="검색어 입력"
      />
      <input
        v-if="selected==='co_name'"
        style="margin: 0; height:40px; width: 260px;"
        id="myInput"
        type="text"
        v-on:input="keyword = $event.target.value"
        placeholder="검색어 입력"
      />
      <input
        v-if="selected==='name'"
        style="margin: 0; height:40px; width: 260px;"
        id="myInput"
        type="text"
        v-on:input="keyword = $event.target.value"
        placeholder="검색어 입력"
      />
    </div>
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
        v-for="(list, index) in filteredList"
        :key="list.no"
        @click="licneseChange(index, list.no, list.policy_no)"
      >
        <td>{{index + 1}}</td>
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
      licenses: "",
      selected: "policy_title",
      keyword: "",
    };
  },
  async created() {
    const { data } = await licenseAllList();
    this.licenses = data;
  },
  computed: {
    filteredList() {
      return Object.values(this.licenses).filter((post) => {
        if (this.selected === "policy_title") {
          return post.policy_title
            .toLowerCase()
            .includes(this.keyword.toLowerCase());
        } else if (this.selected === "co_name") {
          return post.co_name
            .toLowerCase()
            .includes(this.keyword.toLowerCase());
        } else if (this.selected === "name") {
          return post.name.toLowerCase().includes(this.keyword.toLowerCase());
        }
      });
    },
  },
  methods: {
    // userShow() {
    //   this.$router.push("/license/useradmin");
    // },
    licneseChange(index, licenseNo, policyNo) {
      this.$router.push({
        name: "licnesePolicyInfo",
        params: {
          license_no: licenseNo,
          policy_no: policyNo,
          pageTypeAdmin: true,
        },
      });
    },
  },
};
</script>

<style scoped>
#myInput {
  background-image: url("../../../assets/images/searchIcon2.png");
  background-size: 23px;
  background-position: 8px 8px;
  background-repeat: no-repeat;
  width: 260px;
  height: 35px;
  font-size: 16px;
  padding: 12px 20px 12px 42px;
  border: 1px solid #ddd;
}
</style>