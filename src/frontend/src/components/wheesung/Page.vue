<template>
  <div class="app">
    <ul class="pagination">
      <li class="pagination-item">
        <button
          type="button"
          @click="onClickPreviousPage"
          :disabled="isInFirstPage"
          aria-label="Go to previous page"
        >Previous</button>
      </li>

      <li v-for="page in pages" class="pagination-item" :key="page.no">
        <button
          type="button"
          @click="onClickPage(page.name)"
          :disabled="page.isDisabled"
          :class="{ active: isPageActive(page.name) }"
          :aria-label="`Go to page number ${page.name}`"
          style="background-color: gray; margin: 4px;"
        >{{ page.name }}</button>
      </li>

      <li class="pagination-item">
        <button
          type="button"
          @click="onClickNextPage"
          :disabled="isInLastPage"
          aria-label="Go to next page"
        >Next</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
   props: {
    maxVisibleButtons: {
      type: Number,
      required: false,
      default: 5
    },
    totalPages: {
      type: Number,
      required: true
    },
    total: {
      type: Number,
      required: true
    },
    perPage: {
      type: Number,
      required: true
    },
    currentPage: {
      type: Number,
      required: true,
      default: 1
    },
  },
  computed: {
    startPage() {
      if (this.currentPage === 1) {
        return 1;
      }

      if (this.currentPage === this.totalPages) { 
        return this.totalPages - this.maxVisibleButtons + 1;
      }

      return this.currentPage - 1;

    },
    endPage() {
      
      return Math.min(this.startPage + this.maxVisibleButtons - 1, this.totalPages);
      
    },
    pages() {
      const range = [];

      for (let i = this.startPage; i <= this.endPage; i+= 1 ) {
        range.push({
          name: i,
          isDisabled: i === this.currentPage 
        });
      }

      return range;
    },
    isInFirstPage() {
      return this.currentPage === 1;
    },
    isInLastPage() {
      return this.currentPage === this.totalPages;
    },
  },
  methods: {
    onClickPreviousPage() {
      this.$emit('pagechanged', this.currentPage - 1);
    },
    onClickPage(page) {
      this.$emit('pagechanged', page);
      console.log(page)
    },
    onClickNextPage() {
      this.$emit('pagechanged', this.currentPage + 1);
    },
    isPageActive(page) {
      return this.currentPage === page;
    },
    onPageChange(page) {
      console.log(page)
      this.currentPage = page;
    }
  }
};
</script>

<style>
.pagination {
  list-style-type: none;
}

.pagination-item {
  display: inline-block;
}

.active {
  background-color: #4AAE9B;
  color: #ffffff;
}

.app {
  font-family: Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>