<template>
  <div id="app">
    <!--
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    -->
    <Header />

    <div id="bottom-part">
      <Navigator />
      <router-view />
      <!-- Home.vue or About.vue가 들어가는 자리 -->
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import Navigator from "@/components/Navigator.vue";

export default {
  components: {
    Header,
    Navigator,
  },
  beforeUpdate() {
    switch (document.location.pathname) {
      case "subscribeOrderUrl":
        this.isChecked = true;
        break;
    }
  },
};
</script>

<style>
@import "./assets/css/contents.css";
@import "./assets/css/allList.css";
@import "./assets/css/products.css";
@import "./assets/css/table.css";

@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

#app {
  color: #000000d9;
  font-family: "Jeju Gothic", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  width: 100%;
  height: 100%;
  overflow: auto;
}

#bottom-part {
  display: flex;
}

h1 {
  font-size: 4.5rem;
  color: #000000d9;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  font-weight: normal;
}

button {
  font-weight: normal;
}

input[type="password"] {
  font-family: Helvetica, Arial, sans-serif;
}

::placeholder {
  font-family: "Jeju Gothic", sans-serif;
}
</style>
