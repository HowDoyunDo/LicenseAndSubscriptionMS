<template>
  <div id="navigator">
    <!-- 시스템관리자 네비게이터 -->
    <div v-if="(this.userInfo.name !== undefined) && (this.userInfo.co_number === undefined)">
      <h4>제품 관리</h4>
      <ul>
          <li>
            <router-link to="/categorylist">카테고리 목록</router-link>
          </li>
          <li>
            <router-link to="/productlist">제품 목록</router-link>
          </li>
          <li>
            <router-link to="/productadd">제품 등록</router-link>
          </li>
      </ul>
      <h4>구독 관리</h4>
      <ul>
          <li>
            <router-link to="/subscribe/alllist">구독 목록</router-link>
          </li>
          <li>
            <router-link to="/subscribe/add">구독 등록</router-link>
          </li>
      </ul>
      <h4>프로모션 관리</h4>
      <ul>
          <li>
            <router-link to="/promotion">프로모션 목록</router-link>
          </li>
            <li>
            <router-link to="/promotion/add">프로모션 등록</router-link>
          </li>
      </ul>
      <h4>주문 관리</h4>
      <ul>
          <li>
            <router-link to="/orderlist">주문 목록</router-link>
          </li>
          <li>
            <router-link to="/subscriptioncancelchange">구독 변경/취소 내역</router-link>
          </li>
      </ul>
      <h4>고객 관리</h4>
      <ul>
          <li>
            <router-link to="/userlist">고객사 관리</router-link>
          </li>
          <!-- <li>
            <router-link to="/">견적 문의 내역</router-link>
          </li> -->
      </ul>

      <h4>통계 분석</h4>
      <ul>
          <li>
            <router-link to="/analysis/sales/day">매출 분석</router-link>
          </li>
          <li>
            <router-link to="/analysis/product">상품 분석</router-link>
          </li>
      </ul>
      <h4>라이선스 관리</h4>
      <ul>
          <li>
            <router-link to="/license/alllist">라이선스 현황</router-link>
          </li>
          <li>
            <router-link to="/batch">라이선스 배치 서비스</router-link>
          </li>
      </ul>
    </div>

    <!-- 고객관리자 네비게이터 -->
    <div v-else-if="this.userInfo.co_number !== undefined">
      <h4>
        제품 조회 및
        <br />구독 신청
      </h4>
      <ul>
          <li>
            <router-link to="/">제품 조회</router-link>
          </li>
      </ul>

      <h4>라이선스 관리</h4>
      <ul>
          <li>
            <router-link to="/license/list">라이선스 관리</router-link>
          </li>
      </ul>
      <h4>관리자 설정</h4>
      <ul>
          <!-- <li>
            <router-link to="/">관리자 목록</router-link>
          </li> -->
          <li>
            <router-link to="/myinfo">관리자 정보수정</router-link>
          </li>
      </ul>
      <!-- <h4>견적 문의</h4>
      <ul>
          <li>
            <router-link to="/">견적 신청</router-link>
          </li>
      </ul> -->
    </div>
    <!-- 비로그인 네비게이터 -->
    <div v-else-if="this.userInfo.name === undefined">
      <h4>
        제품 조회 및
        <br />구독 신청
      </h4>
      <ul>
          <li>
            <router-link to="/">제품 조회</router-link>
          </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
    };
  },
  methods: {
  },
  computed: {
    userInfo: function() {
      return this.$store.state.userinfo.userInfo;
    }
  }
};
</script>

<style scoped>
#navigator {
  min-height: 89.3vh;
  width: 15%;
  align-self: left;
  font-size: 15px;
  position: static;
  margin: 0px;
  padding: 10px 0 0 0;
  background-color: rgb(245, 245, 245);
}
#navigator ul {
  list-style: none;
  margin: 0px;
  padding: 0px;
}
#navigator ul li {
  margin: 5px 25px 5px;
  padding: 0;
}
#navigator h4 {
  font-size: 18px;
  margin: 0px;
  padding: 10px 20px 10px;
  font-weight: normal;
  color: #103366;
}
#navigator a {
  color: #888888;
  text-decoration-line: none;
}
#navigator a:hover {
  color: #3498db;
}
</style>