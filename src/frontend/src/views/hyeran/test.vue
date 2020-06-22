<template>
  <div>
  <form id="app" @submit="checkForm" action="/something" method="post" novalidate="true">
    
    <p v-if="errors.length">
      <b>아래 오류를 수정하세요.:</b>
      <ul>
        <li v-for="error in errors" :key="error.id">{{ error }}</li>
      </ul>
    </p>
    
    <p>
      <label for="name">이름</label>
      <input type="text" name="name" id="name" v-model="name">
    </p>

    <p>
      <label for="email">이메일</label>
      <input type="email" name="email" id="email" v-model="email">
    </p>

    <p>
      <label for="movie">최애 영화</label>
      <select name="movie" id="movie" v-model="movie">
        <option>토이스토리</option>
        <option>알라딘</option>
        <option>겨울왕국</option>
      </select>
    </p>

    <p>
      <input type="submit" value="Submit">  
    </p>

  </form>


  </div>
</template>

<script>
export default {
data() {
  return {
   errors: [],
    name: null,
    email: null,
    movie: null
  }
},
  methods: {
    checkForm(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.name) {
        this.errors.push("이름은 필수입니다.");
      }
      if (!this.email) {
        this.errors.push("이메일은 필수입니다.");
      } else if (!this.validEmail(this.email)) {
        this.errors.push("이메일 형식을 확인하세요.");
      }
      if (!this.errors.length) return true;
    },
    // validEmail: function(email) {
    //   var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    //   return re.test(email);
    // }
  }


}
</script>

<style>

</style>