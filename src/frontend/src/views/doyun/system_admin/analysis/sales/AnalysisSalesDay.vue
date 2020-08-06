<template>
    <div id="analysisSales" class="contents">
        <h1>매출 분석</h1>
        <br>

        <div class="inner">
            <p class="tip">
                통계 자료는 국세청 및 기타 제출용 자료로 사용이 불가능하며, 라이선스 운영의 참고 자료로 이용하시기 바랍니다.
            </P>
            <span v-bind:class="day" @click="seltab(1)">일별 매출</span>
            <span v-bind:class="week" @click="seltab(2)">주별 매출</span>
            <span id="lasttab" v-bind:class="month" @click="seltab(3)">월별 매출</span>
            <span id="last"><span style="visibility: hidden;">asd</span></span>
            <div id="option">
                <span id="per">기간</span> <br><br>
                <span v-bind:class="today_per" style="margin-left:7%" @click="selPer(1)">오늘</span>
                <span v-bind:class="three_per" @click="selPer(2)">3일</span>
                <span v-bind:class="week_per" @click="selPer(3)">7일</span>
                <span v-bind:class="month_per" @click="selPer(4)">1개월</span>
                <span v-bind:class="threemonth_per" @click="selPer(5)">3개월</span>
                <span v-bind:class="sixmonth_per" @click="selPer(6)">6개월</span> <br><br>
                <div style="margin-left:7%">
                    <input type="datetime-local" style="width:200px" v-model="startdate"> ~
                    <input type="datetime-local" style="width:200px" v-model="enddate">
                </div>

                <br>
                <button style="margin-left:7%" @click="clrAll()">초기화</button>
            </div>
            <br>

            <div id='chart' style="border:1px solid #ccc; padding: 30px 30px 50px; width:100%; height:500px;">
                <span id="per">차트</span> <br><br>
                <canvas id="planet-chart"></canvas>
            </div>
            <br>

            <div id="result">
                <span id="per">결과</span> <br><br>
                <table class="table_board">
                    <tr>
                        <th width="15%">일자</th>
                        <th width="5%">주문 수</th>
                        <th width="5%">정책 수</th>
                        <th width="15%">상품 금액</th>
                        <th width="15%">할인 금액</th>
                        <th width="5%" style="visibility: hidden;"></th>
                        <th width="15%">결제 금액</th>
                        <!-- <th width="15%">환불 합계</th> -->
                    </tr>
                    <tr v-for="al in filteredList" :key="al.date">
                        <td>{{al.date}}</td>
                        <td>{{al.order_cnt}}</td>
                        <td>{{al.pol_cnt}}</td>
                        <td>{{al.org_price | formatPrice}}원</td>
                        <td>{{al.dc_price | formatPrice}}원</td>
                        <td style="visibility: hidden; width: 5%"></td>
                        <td>{{al.total_price | formatPrice}}원</td>
                        <!-- <td>d</td> -->
                    </tr>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import Chart from 'chart.js'

export default {
    data() {
        return {
            // 탭 선택
            day: 'seltab',
            week: 'none',
            month: 'none',

            startdate: '',
            enddate: '',

            // 기간 선택
            today_per: 'per',
            three_per: 'per',
            week_per: 'per',
            month_per: 'per',
            threemonth_per: 'per',
            sixmonth_per: 'per',

            // orderList
            analysisList: '',

            // 기간 선택 기준
            standard: '',

            // 차트 변수
            chart: '',
        }
    },
    methods: {
        seltab(tabno) {
            switch(tabno) {
                case 1: this.$router.push("/analysis/sales/day");
                    break;
                case 2: this.$router.push("/analysis/sales/week");
                    break;
                case 3: this.$router.push("/analysis/sales/month");
                    break;
            }
        },
        clrPer() {
            this.today_per = 'per';
            this.three_per = 'per';
            this.week_per = 'per';
            this.month_per = 'per';
            this.threemonth_per = 'per';
            this.sixmonth_per = 'per';
        },
        selPer(tabno) {
            switch(tabno) {
                case 1:
                    if(this.today_per === 'selper') {
                        this.clrPer();
                        this.standard = '';
                    } else {
                        this.clrPer();
                        this.today_per = 'selper';

                        var temp = new Date();
                        this.standard = this.getDate(temp);
                    }
                    break;
                case 2:
                    if(this.three_per === 'selper') {
                        this.clrPer();
                        this.standard = '';
                    } else {
                        this.clrPer();
                        this.three_per = 'selper';

                        temp = new Date();
                        temp.setDate(temp.getDate() - 3);
                        this.standard = this.getDate(temp);
                    }
                    break;
                case 3:
                    if(this.week_per === 'selper') {
                        this.clrPer();
                        this.standard = '';
                    } else {
                        this.clrPer();
                        this.week_per = 'selper';

                        temp = new Date();
                        temp.setDate(temp.getDate() - 7);
                        this.standard = this.getDate(temp);
                    }
                    break;
                case 4:
                    if(this.month_per === 'selper') {
                        this.clrPer();
                        this.standard = '';
                    } else {
                        this.clrPer();
                        this.month_per = 'selper';

                        temp = new Date();
                        temp.setMonth(temp.getMonth() - 1);
                        this.standard = this.getDate(temp);
                    }
                    break;
                case 5:
                    if(this.threemonth_per === 'selper') {
                        this.clrPer();
                        this.standard = '';
                    } else {
                        this.clrPer();
                        this.threemonth_per = 'selper';

                        temp = new Date();
                        temp.setMonth(temp.getMonth() - 3);
                        this.standard = this.getDate(temp);
                    }
                    break;
                case 6:
                    if(this.sixmonth_per === 'selper') {
                        this.clrPer();
                        this.standard = '';
                    } else {
                        this.clrPer();
                        this.sixmonth_per = 'selper';

                        temp = new Date();
                        temp.setMonth(temp.getMonth() - 6);
                        this.standard = this.getDate(temp);
                    }
                    break;
            }
        },
        leadingZeros(n, digits) {
            var zero = '';
            n = n.toString();

            if (n.length < digits) {
                for (var i = 0; i < digits - n.length; i++)
                    zero += '0';
            }
            return zero + n;
        },
        getDate(value) {
            return ( this.leadingZeros(value.getFullYear(), 4) + '-' +
            this.leadingZeros(value.getMonth() + 1, 2) + '-' +
            this.leadingZeros(value.getDate(), 2));
        },
        clrAll() {
            this.startdate = ''
            this.enddate = ''

            // 기간 선택
            this.today_per = 'per'
            this.three_per = 'per'
            this.week_per = 'per'
            this.month_per = 'per'
            this.threemonth_per = 'per'
            this.sixmonth_per = 'per'

            this.standard = ''
        },
        createChart(chartId) {
            var ctx = document.getElementById(chartId); 
            var config = { 
                type: 'bar',
                data: {
                    labels: this.label,
                    datasets: [{
                        label: '결제 금액(원)',
                        data: this.total_price,
                        backgroundColor: 
                            // 'rgba(255, 99, 132, 0.2)',
                            // 'rgba(54, 162, 235, 0.2)',
                            // 'rgba(255, 206, 86, 0.2)',
                            // 'rgba(75, 192, 192, 0.2)',
                            // 'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                        borderColor: 
                            // 'rgba(255, 99, 132, 1)',
                            // 'rgba(54, 162, 235, 1)',
                            // 'rgba(255, 206, 86, 1)',
                            // 'rgba(75, 192, 192, 1)',
                            // 'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                        borderWidth: 1,
                    }]
                },
                options: {
                    // responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            }

            if(this.chart !== '') 
                this.chart.destroy();
            this.chart = new Chart(ctx, config);   // eslint-disable-line no-unused-vars
        }
    },
    computed: {
        filteredList() {
            return Object.values(this.analysisList).filter(al => {
                return (
                    ( this.standard === '' ? true : (Date.parse(al.date) >=  Date.parse(this.standard)) )
                    && ( this.startdate === '' ? true : (Date.parse(al.date) > Date.parse(this.startdate)) )
                    && ( this.enddate === '' ? true : (Date.parse(al.date) < Date.parse(this.enddate)) )
                )
            });
        },
        label() {
            var dates = [];
            this.filteredList.forEach((element) => {
                dates.push(element.date);
            });
            return dates;
        },
        total_price() {
            var price = [];
            this.filteredList.forEach((element) => {
                price.push(element.total_price);
            });
            return price;
        }
    },
    async created() {
        await axios.get('/api/analysis/sales/day', {
            }).then(res => {
                this.analysisList = res.data;
        });
        this.createChart('planet-chart', this.planetChartData);
    },
    updated() {
        this.createChart('planet-chart', this.planetChartData);
    }
}
</script>

<style scoped>
    @import "/assets/allList.css";

    h1 {
        font-size: 4.5rem;
        color:#000000D9;
    }
    .tip {
        border: 2px solid gray;
        color: red;
        font-size: 15px;
        padding: 20px;
        border-radius: 5px;
    }
    .inner {
        text-align: left;
    }
    .none {
        font-size: 15px;
        border: 1px solid #ccc;
        border-right: none;
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
        padding: 10px 20px 10px;
        width:10%;
        display: inline-block;
        cursor: pointer;
        text-align: center;
    }
    #lasttab {
        border-right: 1px solid #ccc;
    }
    #last{
        display: inline-block;
        width:70%;
        height: 100%;
        border-bottom: 1px solid #ccc;
        padding: 10px 20px 10px;
    }
    #option {
        border: 1px solid #ccc;
        border-top: none;
        padding: 30px;
    }
    #result {
        border: 1px solid #ccc;
        padding: 30px;
    }
    .per {
        padding:5px 10px 5px;
        border:1px solid #ccc;
        border-radius: 4px;
        margin-right: 5px;
        cursor: pointer;
    }
    #per {
        background-color: #234679;
        padding:7px 20px 7px;
        font-size: 15px;
        color: #fff;
        border-radius: 4px;
        margin-right: 2px;
    }
    .seltab {
        font-size: 15px;
        border: 1px solid #ccc;
        border-right: none;
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
        padding: 10px 20px 10px;
        width:10%;
        display: inline-block;
        cursor: pointer;
        text-align: center;
        background: #d9edf7;
        border-bottom: none;
    }
    .selper {
        background: #d9edf7;
        padding:5px 10px 5px;
        border:1px solid #ccc;
        border-radius: 4px;
        margin-right: 5px;
        cursor: pointer;
    }
</style>