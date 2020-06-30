<template>
    <div id="product" class="contents">
        <Detail>
            <!-- slot 위치 -->
            <br>
            <ul class="user" style="float:left">
                <th>{{ solution }} _사용자 정책</th>
                <li v-for="(upolicy, i) in uPolicies" v-bind:key="upolicy.no">
                    <div class="panel panel-info">
                        <div class="panel-heading">{{ solution }}</div>
                        <div class="panel-body">
                            <table align="center">
                                <tr>
                                    <th>최대 사용자</th>
                                    <th>사용료/월</th>
                                </tr>
                                <tr>
                                    <td v-if="upolicy.max_count === 0">제한없음</td>
                                    <td v-else>{{ upolicy.max_count }}명</td>
                                    <td>{{ numberWithCommas(upolicy.price) }}원</td>
                                </tr>
                            </table>

                            <template v-if="solution === 'UI/UX 솔루션'">
                                <p style="margin: 10px 0 10px">iWorks<br>
                                iXeb<br></p>
                            </template>

                            <template v-if="solution === '채널통합 솔루션'">
                                <p style="margin: 10px 0 10px">iGate<br>
                                APIM<br>
                                eCross<br></p>
                            </template>

                            <template v-if="solution === '컨텐츠 통합관리'">
                                <p style="margin: 10px 0 10px">Xtorm<br></p>
                            </template>

                            <template v-if="solution === '오픈소스 데이터 플랫폼'">
                                <p style="margin: 10px 0 10px">eXperDB<br></p>
                            </template>

                            <template v-if="solution === '통합도서관시스템'">
                                <p style="margin: 10px 0 10px">Libeka<br>
                                Libeka S4<br></p>
                            </template>

                            <template v-if="solution === '정보 보안'">
                                <p style="margin: 10px 0 10px">MyGuard<br></p>
                            </template>

                            <template v-if="solution === '문서중앙화'">
                                <p style="margin: 10px 0 10px">Xtorm<br>
                                MyGuard<br></p>
                            </template>

                            <router-link :to='{ name: "SubscribeOrder", params: { policyidx: i, policytype: upolicy.standard }}'>
                                <button style="width:90px; margin-bottom:10px" @click="getPromotion(i, upolicy.standard)">
                                    구독 신청
                                    </button>
                            </router-link>
                        </div>
                    </div>
                </li>
            </ul>

            <br>

            <ul class="agent" style="float:left">
                <th>{{ solution }} _에이전트 정책</th>
                <li v-for="(apolicy, i) in aPolicies" v-bind:key="apolicy.no">
                    <div class="panel panel-info">
                        <div class="panel-heading">{{ solution }}</div>
                        <div class="panel-body">
                            <table align="center">
                                <tr>
                                    <th>최대 사용자</th>
                                    <th>사용료/월</th>
                                </tr>
                                <tr>
                                    <td v-if="apolicy.max_count === 0">제한없음</td>
                                    <td v-else>{{ apolicy.max_count }}</td>
                                    <td>{{ numberWithCommas(apolicy.price) }}원</td>
                                </tr>
                            </table>

                            <template v-if="solution === 'UI/UX 솔루션'">
                                <p style="margin: 10px 0 10px">iWorks<br>
                                iXeb<br></p>
                            </template>

                            <template v-if="solution === '채널통합 솔루션'">
                                <p style="margin: 10px 0 10px">iGate<br>
                                APIM<br>
                                eCross<br></p>
                            </template>

                            <template v-if="solution === '컨텐츠 통합관리'">
                                <p style="margin: 10px 0 10px">Xtorm<br></p>
                            </template>

                            <template v-if="solution === '오픈소스 데이터 플랫폼'">
                                <p style="margin: 10px 0 10px">eXperDB<br></p>
                            </template>

                            <template v-if="solution === '통합도서관시스템'">
                                <p style="margin: 10px 0 10px">Libeka<br>
                                Libeka S4<br></p>
                            </template>

                            <template v-if="solution === '정보 보안'">
                                <p style="margin: 10px 0 10px">MyGuard<br></p>
                            </template>

                            <template v-if="solution === '문서중앙화'">
                                <p style="margin: 10px 0 10px">Xtorm<br>
                                MyGuard<br></p>
                            </template>

                            <router-link :to='{ name: "SubscribeOrder", params: { policyidx: i, policytype: apolicy.standard }}'>
                                <button style="width:90px" @click="getPromotion(i, apolicy.standard)">
                                    구독 신청
                                </button>
                            </router-link>
                        </div>
                    </div>
                </li>
            </ul>
        </Detail>

        <button id="subBtn" @click="subBtn()">
            바로 구독 신청
        </button>
    </div>
</template>

<script>
import Detail from '@/components/doyun/Detail';
import axios from 'axios';

export default {
    methods: {
        getPromotion(idx, type) {
            // 프로모션 정보를 가져와 store에 저장
            if(type === 'U') {
                axios.get('/api/promotion', {
                params: {
                    startdate : this.date_to_str(new Date()),
                    policyno : this.uPolicies[idx].no
                }
                }).then(res => { 
                    this.$store.commit('productStore/SET_PROMOTION', res.data);
                });

            } else if (type === 'A') {
                axios.get('/api/promotion', {
                params: {
                    startdate : this.date_to_str(new Date()),
                    policyno : this.aPolicies[idx].no
                }
                }).then(res => { 
                    this.$store.commit('productStore/SET_PROMOTION', res.data);
                });
            }
        },
        date_to_str(format) {
            var year = format.getFullYear();
            var month = format.getMonth() + 1;
            if(month<10) month = '0' + month;
            var date = format.getDate();
            if(date<10) date = '0' + date;
            var hour = format.getHours();
            if(hour<10) hour = '0' + hour;
            var min = format.getMinutes();
            if(min<10) min = '0' + min;
            var sec = format.getSeconds();
            if(sec<10) sec = '0' + sec;

            return year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;
        },
        numberWithCommas(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        },
        subBtn() {
            // var divId = document.getElementById('product');
            // divId.scrollTop = divId.scrollHeight;
            
            document.documentElement.scrollTop = document.body.scrollHeight;
        }
    },
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
        aPolicies: function() {
            return this.$store.state.productStore.aPolicies;
        },
    },
}
</script>

<style scoped>
  #product ul {
    list-style: none;
    margin-right: auto;
    margin-left: auto;
    width:100%;
  }
  .user li, .agent li {
    padding: 10px;
    float: left;
  }
  .user .panel-body, .agent .panel-body {
      text-align: center;
  }
  .panel {
    width: 210px;
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
    text-align: center;
    padding: 0 5px 0 5px;
  }
  .user table tr, .agent table tr {
    border-bottom: 1px solid #ccc;
  }
  .user table, .agent table {
    width: 180px;
  }
  p {
      color: blueviolet;
  }
  #subBtn {
      font-size: 20px;
      width:200px;
      height:70px;
      position:fixed;
      right:50px;
      bottom:50px;
      z-index:1000
  }
</style>