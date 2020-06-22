<template>
    <div id="product">
        <Detail>
            <!-- slot 위치 -->
            <br>
            <ul class="user" style="height: 340px;">
                <th>{{ solution }} _사용자 정책</th>
                <li v-for="upolicy in uPolicies" v-bind:key="upolicy.no">
                    <div class="panel panel-info">
                        <div class="panel-heading">{{ solution }}</div>
                        <div class="panel-body">
                            <br>
                            <table align="center">
                                <tr>
                                    <th>최대 사용자</th>
                                    <th>사용료/월</th>
                                </tr>
                                <tr>
                                    <td>{{ upolicy.max_count }}명</td>
                                    <td>￦{{ upolicy.price }}</td>
                                </tr>
                            </table>

                            <br>

                            포함제품<br>
                            product1<br>
                            product2<br><br>

                            <button style="width:90px">구독 신청</button>
                        </div>
                    </div>
                </li>
            </ul>

            <br>

            <ul class="agent" style="height: 340px;">
                <th>{{ solution }} _에이전트 정책</th>

            </ul>
        </Detail>

    </div>
</template>

<script>
import Detail from '@/components/doyun/Detail';

export default {
    components: {
        Detail,
    },
    computed: {
        solution: function() {
            return this.$store.state.productStore.solution;
        },
        product: function() {
            return this.$store.state.productStore.product;
        },
        uPolicies: function() {
            return this.$store.state.productStore.uPolicies;
        },
    }
}
</script>

<style>
  #product ul {
    list-style: none;
    margin-right: auto;
    margin-left: auto;
    width:1000px;
    border-bottom: 1px solid lightgray;
  }
  .user th, .agent th {
    border-bottom: 1px solid lightgray;
  }
  .user li, .agent li {
    padding: 10px;
    height: 300px;
    float: left;
  }
  .user .panel-body, .agent .panel-body {
      text-align: center;
      height: 250px;
  }
  .panel {
    width: 230px;
    margin: 0;
  }

  .user table ul, .agent table ul{
    border-top: 2px solid #ccc;
    border-bottom: 2px solid #ccc;
    width: 100%;
    max-width: 100%;
    border-spacing: 0;
    border-collapse: collapse;
    table-layout: fixed;
    text-align: center;
  }
  .user table th, .agent table th {
    background: #eaeaea;
    font-weight: bold;
    border: none;
    padding: 0 5px 0 5px;
  }
  .user table tr, .agent table tr {
    border-bottom: 1px solid #ccc;
  } 
</style>