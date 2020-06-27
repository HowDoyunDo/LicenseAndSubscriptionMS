<template>
  <div>
    <h1>TABLE PAGINATION</h1>
    <paginated-list :list-array="pageArray" />

    <paginate
      :page-count="5"
      :click-handler="functionName"
      :prev-text="'Prev'"
      :next-text="'Next'"
      :container-class="'className'"
    ></paginate>
  </div>
</template>

<script>
import axios from "axios";
import PaginatedList from "./PaginatedList";
import Paginate from "vuejs-paginate";

export default {
  name: "simple-pagination",
  components: {
    PaginatedList,
    Paginate
  },
  data() {
    return {
      pageArray: []
    };
  },
  created() {
    axios
      .get("http://sample.bmaster.kro.kr/contacts")
      .then(response => {
        console.log(response);
        this.pageArray = response.data.contacts;
      })
      .catch(err => {
        console.log(err);
      });
  }
};
</script>

<style>
h1 {
  color: #454545;
  text-align: center;
}
</style>
