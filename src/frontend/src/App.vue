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
    Navigator
  }
};
</script>

<style>
@import "./css/contents.css";
@import "./css/allList.css";


#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 100%;
  height: 100%;
  overflow: auto;
}

#bottom-part {
  display: flex;
}
</style>
