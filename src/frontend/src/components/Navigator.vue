<template>
  <div id="navigator">
    <!-- 시스템관리자 네비게이터 -->
    <div v-if="(this.userInfo.name !== undefined) && (this.userInfo.co_number === undefined)">
      <h4>제품 관리</h4>
      <ul>
          <li>
            <router-link to="/categorylist" :class="categorylist" @click.native="selMenu(1)">카테고리 목록</router-link>
          </li>
          <li>
            <router-link to="/productlist" :class="productlist" @click.native="selMenu(2)">제품 목록</router-link>
          </li>
          <li>
            <router-link to="/productadd" :class="productadd" @click.native="selMenu(3)">제품 등록</router-link>
          </li>
      </ul>
      <h4>구독 관리</h4>
      <ul>
          <li>
            <router-link to="/subscribe/alllist" :class="subscribeAlllist" @click.native="selMenu(4)">구독 목록</router-link>
          </li>
          <li>
            <router-link to="/subscribe/add" :class="subscribeAdd" @click.native="selMenu(5)">구독 등록</router-link>
          </li>
      </ul>
      <h4>프로모션 관리</h4>
      <ul>
          <li>
            <router-link to="/promotion" :class="promotion" @click.native="selMenu(6)">프로모션 목록</router-link>
          </li>
            <li>
            <router-link to="/promotion/add" :class="promotionAdd" @click.native="selMenu(7)">프로모션 등록</router-link>
          </li>
      </ul>
      <h4>주문 관리</h4>
      <ul>
          <li>
            <router-link to="/orderlist" :class="orderlist" @click.native="selMenu(8)">주문 목록</router-link>
          </li>
          <li>
            <router-link to="/subscriptioncancelchange" :class="subscriptioncancelchange" @click.native="selMenu(9)">구독 변경/취소 내역</router-link>
          </li>
      </ul>
      <h4>고객 관리</h4>
      <ul>
          <li>
            <router-link to="/userlist" :class="userlist" @click.native="selMenu(10)">고객사 관리</router-link>
          </li>
          <li>
            <router-link to="/pricequestionlist" :class="pricequestionlist" @click.native="selMenu(19)">견적 문의 내역</router-link>
          </li>
      </ul>

      <h4>통계 분석</h4>
      <ul>
          <li>
            <router-link to="/analysis/sales/day" :class="analysisSales" @click.native="selMenu(11)">매출 분석</router-link>
          </li>
          <li>
            <router-link to="/analysis/product" :class="analysisProduct" @click.native="selMenu(12)">상품 분석</router-link>
          </li>
      </ul>
      <h4>라이선스 관리</h4>
      <ul>
          <li>
            <router-link to="/license/alllist" :class="licenseAlllist" @click.native="selMenu(13)">라이선스 현황</router-link>
          </li>
          <li>
            <router-link to="/batch" :class="batch" @click.native="selMenu(14)">라이선스 배치 서비스</router-link>
          </li>
      </ul>
    </div>

    <!-- 고객관리자 네비게이터 -->
    <div v-else-if="this.userInfo.co_number !== undefined">
      <h4>
        제품 조회 및 구독 신청
      </h4>
      <ul>
          <li>
            <router-link to="/" :class="home" @click.native="selMenu(15)">제품 조회</router-link>
          </li>
      </ul>

      <h4>라이선스 관리</h4>
      <ul>
          <li>
            <router-link to="/license/list" :class="licenseList" @click.native="selMenu(16)">라이선스 관리</router-link>
          </li>
      </ul>
      <h4>관리자 설정</h4>
      <ul>
          <!-- <li>
            <router-link to="/">관리자 목록</router-link>
          </li> -->
          <li>
            <router-link to="/myinfo" :class="myinfo" @click.native="selMenu(17)">관리자 정보수정</router-link>
          </li>
      </ul>
      <h4>견적 문의</h4>
      <ul>
          <li>
            <router-link to="/pricequestion" :class="pricequestion" @click.native="selMenu(18)">견적 신청</router-link>
          </li>
      </ul>
    </div>
    <!-- 비로그인 네비게이터 -->
    <div v-else-if="this.userInfo.name === undefined">
      <h4>
        제품 조회 및 구독 신청
      </h4>
      <ul>
          <li>
            <router-link to="/" :class="home">제품 조회</router-link>
          </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      categorylist: '',
      productlist: '',
      productadd: '',
      subscribeAlllist: '',
      subscribeAdd: '',
      promotion: '',
      promotionAdd: '',
      orderlist: '',
      subscriptioncancelchange: '',
      userlist: '',
      analysisSales: '',
      analysisProduct: '',
      licenseAlllist: '',
      batch: '',
      home: '',
      licenseList: '',
      myinfo: '',
      pricequestion: '',
      pricequestionlist: '',
    };
  },
  methods: {
    clrMenu() {
      this.categorylist = '';
      this.productlist = '';
      this.productadd = '';
      this.subscribeAlllist = '';
      this.subscribeAdd = '';
      this.promotion = '';
      this.promotionAdd = '';
      this.orderlist = '';
      this.subscriptioncancelchange = '';
      this.userlist = '';
      this.analysisSales = '';
      this.analysisProduct = '';
      this.licenseAlllist = '';
      this.batch = '';
      this.home = '';
      this.licenseList = '';
      this.myinfo = '';
      this.pricequestion = '';
      this.pricequestionlist = '';
    },
    selMenu(value)  {
      switch(value) {
        case 1: this.clrMenu(); this.categorylist = 'selected';
          break;
        case 2: this.clrMenu(); this.productlist = 'selected';
          break; 
        case 3: this.clrMenu(); this.productadd = 'selected';
          break;
        case 4: this.clrMenu(); this.subscribeAlllist = 'selected';
          break;
        case 5: this.clrMenu(); this.subscribeAdd = 'selected';
          break;
        case 6: this.clrMenu(); this.promotion = 'selected';
          break;
        case 7: this.clrMenu(); this.promotionAdd = 'selected';
          break;
        case 8: this.clrMenu(); this.orderlist = 'selected';
          break;
        case 9: this.clrMenu(); this.subscriptioncancelchange = 'selected';
          break;
        case 10: this.clrMenu(); this.userlist = 'selected';
          break;
        case 11: this.clrMenu(); this.analysisSales = 'selected';
          break;
        case 12: this.clrMenu(); this.analysisProduct = 'selected';
          break;
        case 13: this.clrMenu(); this.licenseAlllist = 'selected';
          break;
        case 14: this.clrMenu(); this.batch = 'selected';
          break;
        case 15: this.clrMenu(); this.home = 'selected';
          break;
        case 16: this.clrMenu(); this.licenseList = 'selected';
          break;
        case 17: this.clrMenu(); this.myinfo = 'selected';
          break;
        case 18: this.clrMenu(); this.pricequestion = 'selected';
          break;
        case 19: this.clrMenu(); this.pricequestionlist = 'selected';
          break;
      }
    }
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
  width: 300px;
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
  margin: 5px 30px 5px;
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
#navigator .selected {
  color: #3498db;
}
</style>