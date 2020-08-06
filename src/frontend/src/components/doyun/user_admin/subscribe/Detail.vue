<template>
  <div id="detail">
    <ul class="nav nav-tabs">
      <li role="presentation" class="dropdown">
        <a
          class="dropdown-toggle"
          data-toggle="dropdown"
          href="#"
          role="button"
          aria-expanded="false"
          style="background-color: rgb(245, 245, 245); border: 1px solid #ddd"
        >
          <template v-for="category in categories">
            <template v-if="product.title === category.title">{{
              product.title
            }}</template>
          </template>
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu" role="menu" style="width:300px;">
          <li v-for="category in categories" :key="category.no">
            <a @click="detail(category.title)">
              <router-link to="/product" style="text-decoration: none;">
                {{ category.title }}
              </router-link>
            </a>
          </li>
        </ul>
      </li>

      <li
        v-for="tab in tabs"
        :key="tab.no"
        role="presentation"
        :class="isActive(tab.name)"
      >
        <a
          @click="seltab(tab.name)"
          style="border-top: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;"
        >
          <router-link
            to="/product"
            style="text-decoration: none; border-bottom: none;"
          >
            {{ tab.name }}
          </router-link>
        </a>
      </li>
    </ul>

    <div id="inner">
      <img :src="product.info_image" />
    </div>
    <br /><br />

    <!-- 구독 정책 내용 삽입 -->
    <h1 id="subhead">구독 신청하기</h1>
    <!-- 1/2년 구독 정책 info -->
    <p class="tip1" style="width:410px; display:inline-block; margin-right:7px">
      1년 구독 진행 시 12개월 + 1개월[무료] 기간으로 진행됩니다.
    </p>
    <p class="tip2" style="width:410px; display:inline-block">
      2년 구독 진행 시 24개월 + 2개월[무료] 기간으로 진행됩니다.
    </p>
    <slot></slot>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      categories: "",
    };
  },
  methods: {
    async detail(sol) {
      await axios
        .get("/api/productsInCategory", {
          params: {
            sol: sol,
          },
        })
        .then((res) => {
          this.$store.commit("productStore/CHANGE_PDT", res.data[0]);
          this.$store.commit("productStore/CHANGE_TABS", res.data);
          console.log("pdt: " + this.product.name);
          console.log("tabs: " + this.tabs);
        });

      // solution store와 일치하는 User구독정책 table에서 검색
      await axios
        .get("/api/userpolicylist", {
          params: {
            sol: this.product.title,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.$store.commit("productStore/CHANGE_UPLCY", res.data);
        });
      // solution store와 일치하는 Agent구독정책 table에서 검색
      await axios
        .get("/api/agentpolicylist", {
          params: {
            sol: this.product.title,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.$store.commit("productStore/CHANGE_APLCY", res.data);
        });
    },
    seltab(tabName) {
      this.tabs.forEach((element) => {
        if (element.name === tabName) {
          this.$store.commit("productStore/CHANGE_PDT", element);
        }
      });
    },
    isActive(tabName) {
      if (tabName === this.product.name) return "active";
    },
  },
  computed: {
    product: function() {
      return this.$store.state.productStore.product;
    },
    tabs: function() {
      return this.$store.state.productStore.tabs;
    },
  },
  async created() {
    await axios.get("/api/categorylist", {}).then((res) => {
      this.categories = res.data;
    });
  },
};
</script>

<style scoped>
h1 {
  font-size: 4.5rem;
  color: #000000d9;
}
.btn-group {
  margin: 10px;
}
.nav-tabs li.active a {
  background-color: #d9edf7;
}
#inner {
  width: 100%;
  display: inline-block;
  border: 1px solid #ccc;
  border-top: none;
}
.tip1 {
  background-color: #3476ca;
  color:#fff;
  font-size: 15px;
  padding: 10px;
  border-radius: 5px;
  height: 42px;
}
.tip2 {
  background-color: #345497;
  color:#fff;
  font-size: 15px;
  padding: 10px;
  border-radius: 5px;
  height: 42px;
}
</style>
