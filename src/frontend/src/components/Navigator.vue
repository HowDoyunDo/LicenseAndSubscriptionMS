<template>
  <div id="navigator">
    <!-- 시스템관리자 네비게이터 -->
    <div v-if="(this.userInfo.name !== undefined) && (this.userInfo.co_number === undefined)">
      <h4 @click="toggleProduct">제품 관리</h4>
      <ul>
        <template v-if="productMenu">
          <li>
            <router-link to="/categorylist">카테고리 목록</router-link>
          </li>
          <li>
            <router-link to="/productlist">제품 목록</router-link>
          </li>
          <li>
            <router-link to="/productadd">제품 등록</router-link>
          </li>
          <br />
        </template>
      </ul>
      <h4 @click="toggleSubscription">구독 관리</h4>
      <ul>
        <template v-if="subscriptionMenu">
          <li>
            <router-link to="/subscribe/alllist">구독 목록</router-link>
          </li>
          <li>
            <router-link to="/subscribe/add">구독 등록</router-link>
          </li>
          <br />
        </template>
      </ul>
      <h4 @click="togglePromotion">프로모션 관리</h4>
      <ul>
        <template v-if="promotionMenu">
          <li>
            <router-link to="/promotion">프로모션 목록</router-link>
          </li>
            <li>
            <router-link to="/promotion/add">프로모션 등록</router-link>
          </li>
          <br />
        </template>
      </ul>
      <h4 @click="toggleOrder">주문 관리</h4>
      <ul>
        <template v-if="orderMenu">
          <li>
            <router-link to="/orderlist">주문 목록</router-link>
          </li>
          <li>
            <router-link to="/subscriptioncancelchange">구독 변경/취소 내역</router-link>
          </li>
          <br />
        </template>
      </ul>
      <h4 @click="toggleCustomer">고객 관리</h4>
      <ul>
        <template v-if="customerMenu">
          <li>
            <router-link to="/userlist">고객사 관리</router-link>
          </li>
          <!-- <li>
            <router-link to="/">견적 문의 내역</router-link>
          </li> -->
          <br />
        </template>
      </ul>

      <h4 @click="toggleAnalysis">통계 분석</h4>
      <ul>
        <template v-if="analysisMenu">
          <li>
            <router-link to="/analysis/sales/day">매출 분석</router-link>
          </li>
          <li>
            <router-link to="/analysis/product">상품 분석</router-link>
          </li>
          <br />
        </template>
      </ul>
      <h4 @click="toggleLicense">라이선스 관리</h4>
      <ul>
        <template v-if="licenseMenu">
          <li>
            <router-link to="/license/alllist">라이선스 현황</router-link>
          </li>
          <li>
            <router-link to="/batch">라이선스 배치 서비스</router-link>
          </li>
          <br />
        </template>
      </ul>
    </div>

    <!-- 고객관리자 네비게이터 -->
    <div v-else-if="this.userInfo.co_number !== undefined">
      <h4 @click="toggleList">
        제품 조회 및
        <br />구독 신청
      </h4>
      <ul>
        <template v-if="listMenu">
          <li>
            <router-link to="/">제품 조회</router-link>
          </li>
          <br />
        </template>
      </ul>

      <h4 @click="toggleUlicense">라이선스 관리</h4>
      <ul>
        <template v-if="ulicenseMenu">
          <li>
            <router-link to="/license/list">라이선스 관리</router-link>
          </li>
          <br />
        </template>
      </ul>
      <h4 @click="toggleSetting">관리자 설정</h4>
      <ul>
        <template v-if="settingMenu">
          <!-- <li>
            <router-link to="/">관리자 목록</router-link>
          </li> -->
          <li>
            <router-link to="/myinfo">관리자 정보수정</router-link>
          </li>
          <br />
        </template>
      </ul>
      <!-- <h4 @click="toggleQna">견적 문의</h4>
      <ul>
        <template v-if="qnaMenu">
          <li>
            <router-link to="/">견적 신청</router-link>
          </li>
          <br />
        </template>
      </ul> -->
    </div>
    <!-- 비로그인 네비게이터 -->
    <div v-else-if="this.userInfo.name === undefined">
      <h4 @click="toggleNlist">
        제품 조회 및
        <br />구독 신청
      </h4>
      <ul>
        <template v-if="nlistMenu">
          <li>
            <router-link to="/">제품 조회</router-link>
          </li>
          <br />
        </template>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      productMenu: false,
      orderMenu: false,
      customerMenu: false,
      promotionMenu: false,
      analysisMenu: false,
      licenseMenu: false,
      listMenu: false,
      subscriptionMenu: false,
      ulicenseMenu: false,
      settingMenu: false,
      qnaMenu: false,
      nlistMenu: false
    };
  },
  methods: {
    toggleProduct() {
      this.productMenu = !this.productMenu;
    },
    toggleOrder() {
      this.orderMenu = !this.orderMenu;
    },
    toggleCustomer() {
      this.customerMenu = !this.customerMenu;
    },
    togglePromotion() {
      this.promotionMenu = !this.promotionMenu;
    },
    toggleAnalysis() {
      this.analysisMenu = !this.analysisMenu;
    },
    toggleLicense() {
      this.licenseMenu = !this.licenseMenu;
    },
    toggleList() {
      this.listMenu = !this.listMenu;
    },
    toggleSubscription() {
      this.subscriptionMenu = !this.subscriptionMenu;
    },
    toggleUlicense() {
      this.ulicenseMenu = !this.ulicenseMenu;
    },
    toggleSetting() {
      this.settingMenu = !this.settingMenu;
    },
    toggleQna() {
      this.qnaMenu = !this.qnaMenu;
    },
    toggleNlist() {
      this.nlistMenu = !this.nlistMenu;
    }
  },
  computed: {
    userInfo: function() {
      return this.$store.state.userinfo.userInfo;
    }
  }
};
</script>

<style>
#navigator {
  width: 15%;
  align-self: left;
  padding: 5px;
  margin: 5px 0 0 10px;
  font-size: 15px;
  position: static;
}
#navigator ul {
  padding: 0 0 0 10px;
  list-style: none;
  border-bottom: 2px solid lightgray;
}
#navigator h4 {
  cursor: pointer;
}
</style>