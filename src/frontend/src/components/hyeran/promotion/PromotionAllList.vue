<template>
  <div>
    <table class="table_board">
      <tr>
        <th style="width:8%;">번호</th>
        <th style="width:21%;">프로모션명</th>
        <th style="width:12%;">제품명(번호)</th>
        <th style="width:8%;">할인률</th>
        <th style="width:15%;">프로모션 기간</th>
        <th style="width:10%;">진행여부</th>
        <th style="width:15%;">프로모션 등록일</th>
        <th style="width:10%;">수정/삭제</th>
      </tr>
      <tr
        onmouseover="this.style.background='#CEECF5';"
        onmouseout="this.style.background=''"
        style="cursor:pointer"
        v-for="(list, index) in promotionAllList"
        @click="listClick(index)"
        :key="list.no"
      >
        <td>{{ list.promotion_no }}</td>
        <td style="text-align: left;">
          <span style="text-decoration-line: underline;">{{ list.title }}</span>
        </td>
        <td style="text-align: left;">{{list.product_name}} ({{ list.product_no }})</td>
        <td>{{ list.discount }}%</td>
        <td>{{ list.start_date | formatDate}} ~ {{ list.end_date | formatDate}}</td>
        <td>{{ list.type == true ? '진행중' : '진행안함'}}</td>
        <td>{{ list.reg_date | formatDate }}</td>
        <td>
          <a href>수정</a> /
          <a href>삭제</a>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import { promotionAllList } from "@/api/shr/promotion";

export default {
  data() {
    return {
      promotionAllList: ""
    };
  },
  async created() {
    const { data } = await promotionAllList();
    this.promotionAllList = data;
  },
  methods: {
    listClick(index) {
      console.log(index);
    }
  }
};
</script>

<style>
</style>