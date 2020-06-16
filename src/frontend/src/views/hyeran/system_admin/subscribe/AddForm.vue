<template>
  <div class="contents">
    <h2>구독 정책 등록</h2>
    <form class="form">
      <table class="table_add">
        <tr>
          <th>구독 정책명</th>
          <td>
            <input type="text" />
          </td>
        </tr>
        <tr>
          <th>정책 공개 여부</th>
          <td>
            <input type="radio" name id checked="checked" /> 공개
            <input type="radio" name id /> 비공개
          </td>
        </tr>
        <tr>
          <th>이용 기준</th>
          <td>
            <input type="radio" name id /> 사용자
            <input type="radio" name id /> 에이전트
          </td>
        </tr>
        <tr>
          <th>최대 사용 수량</th>
          <td>
            <input type="number" name id min="1" /> 개
          </td>
        </tr>
        <tr>
          <th>가격</th>
          <td>
            <input type="text" name id min="0" style="width : 100px; text-align:center;" /> 원
          </td>
        </tr>
        <tr>
          <th>제품 선택</th>
          <td>
            <select name id class="select-box">
              <option value="1">iWorks [￦280,000]</option>
              <option value="1">iXeb [￦380,000]</option>
              <option value="1">옵션1</option>
            </select>
          </td>
        </tr>
      </table>
      <br />
      <table class="table_add">
        <th>
          <span>선택된 제품</span>
        </th>
      </table>
      <!-- 목록 컴포넌트 -->
      <div class="select-product">
        선택한 것만 출력
        <SubscribeOneList />
        <hr />
        <div>합계 : 원</div>
      </div>
      <div class="submit_btn">
        <button type="submit">구독 정책 등록</button>
      </div>
    </form>
  </div>
</template>

<script>
import SubscribeOneList from "@/components/subscribe/SubscribeOneList.vue";
export default {
  components: {
    SubscribeOneList
  }
};
</script>

<style>
.form {
  width: 100%;
  height: 100%;
}
.table_add {
  border: 2px solid #ccc;
  width: 100%;
  max-width: 100%;
  height: 100%;
  border-spacing: 0;
  border-collapse: collapse;
  table-layout: fixed;
}
.table_add th {
  text-align: center;
  width: 22%;
  background: #eaeaea;
  font-weight: bold;
}
.table_add tr {
  border-bottom: 1px solid #ccc;
}
.table_add td {
  width: 78%;
}
.form input[type="text"],
input[type="number"],
.select-box {
  font-size: 100%;
  border: 1px solid #dae1e7;
  -webkit-box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  padding: 0.5rem 0.75rem;
  margin: 5px;
  width: 90%;
  max-width: 95%;
}
input[type="number"] {
  width: 100px;
  text-align: center;
}
.select-product {
  border: 2px solid #dae1e7;
  border-top: none;
  overflow: auto;
  font-size: smaller;
  padding : 15px;
}
button[type="submit"] {
  width: auto;
  padding: 0 15px;
  margin: 10px;
}

.submit_btn {
  text-align: center;
}
</style>