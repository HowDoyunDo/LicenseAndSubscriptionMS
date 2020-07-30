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
                <div style="border:1px solid #ccc; padding:10px; width:10%; min-width:350px; margin-left:7%;">
                    <select v-model="startYear" style="width:70px;">
                        <option :value="startYear" selected disabled hidden>{{startYear}}</option>
                        <option v-for="year in years" :key="year">{{year}}</option>
                    </select>년

                    <select v-model="startMonth" style="width:40px;">
                        <option :value="startMonth" selected disabled hidden>{{startMonth}}</option>
                        <option v-for="month in months" :key="month">{{month}}</option>
                    </select>월

                    ~
                    <select v-model="endYear" style="width:70px;">
                        <option :value="endYear" selected disabled hidden>{{endYear}}</option>
                        <option v-for="year in years" :key="year">{{year}}</option>
                    </select>년

                    <select v-model="endMonth" style="width:40px;">
                        <option :value="endMonth" selected disabled hidden>{{endMonth}}</option>
                        <option v-for="month in months" :key="month">{{month}}</option>
                    </select>월 
                </div>
            </div>
            <br>

            <div id="result">
                <span id="per">금월/전월 증감 추이</span> <br><br>
                <table class="table_board">
                    <tr>
                        <th colspan="2">기간</th>
                        <th colspan="2">상품 금액 합계</th>
                        <th colspan="2">할인 합계</th>
                        <th colspan="2">결제 합계</th>
                    </tr>
                    <tr>
                        <td colspan="2">금월({{ (new Date).getFullYear() + '-' + leadingZeros((new Date).getMonth() + 1, 2)}})</td>
                        <td>{{ thisMonthObj === '' ? 0 : thisMonthObj.org_price | formatPrice }}원</td>
                        <td rowspan="2" style="color:red">
                            {{ 
                                ( lastMonthObj === '' ? 0 : 
                                ( ((thisMonthObj === '' ? 0 : thisMonthObj.org_price) - (lastMonthObj === '' ? 0 : lastMonthObj.org_price)) 
                                / (lastMonthObj === '' ? 0 : lastMonthObj.org_price) ) * 100 ).toFixed(1)
                            }}% 증가
                        </td>
                        <td>{{ thisMonthObj === '' ? 0 : thisMonthObj.dc_price | formatPrice }}원</td>
                        <td rowspan="2" style="color:red">
                            {{ 
                                ( lastMonthObj === '' ? 0 :
                                ( ((thisMonthObj === '' ? 0 : thisMonthObj.dc_price) - (lastMonthObj === '' ? 0 : lastMonthObj.dc_price)) 
                                / (lastMonthObj === '' ? 0 : lastMonthObj.dc_price) ) * 100 ).toFixed(1)
                            }}% 증가
                        </td>
                        <td>{{ thisMonthObj === '' ? 0 : thisMonthObj.total_price | formatPrice }}원</td>
                        <td rowspan="2" style="color:red">
                            {{ 
                                ( lastMonthObj === '' ? 0 :
                                ( ((thisMonthObj === '' ? 0 : thisMonthObj.total_price) - (lastMonthObj === '' ? 0 : lastMonthObj.total_price)) 
                                / (lastMonthObj === '' ? 0 : lastMonthObj.total_price) ) * 100 ).toFixed(1)
                            }}% 증가
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">전월({{ (new Date).getFullYear() + '-' + leadingZeros((new Date).getMonth(), 2) }})</td>
                        <td>{{ lastMonthObj === '' ? 0 : lastMonthObj.org_price | formatPrice }}원</td>
                        <td>{{ lastMonthObj === '' ? 0 : lastMonthObj.dc_price | formatPrice }}원</td>
                        <td>{{ lastMonthObj === '' ? 0 : lastMonthObj.total_price | formatPrice }}원</td>
                    </tr>
                </table>
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
                        <td>{{al.year_month}}</td>
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

export default {
    data() {
        return {
            // 탭 선택
            day: 'none',
            week: 'none',
            month: 'seltab',

            // orderList
            analysisList: '',
            
            // 년, 월 선택
            startYear: (new Date).getFullYear(),
            startMonth: this.leadingZeros((new Date).getMonth() - 1, 2),
            endYear: (new Date).getFullYear(),
            endMonth: this.leadingZeros((new Date).getMonth() + 1, 2),
            years: [],
            months: [],
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
    },
    computed: {
        filteredList() {
            return Object.values(this.analysisList).filter(al => {
                return (
                    ( Date.parse(al.year_month) >=  Date.parse(this.startYear+'-'+this.startMonth) )
                    && ( Date.parse(al.year_month) <=  Date.parse(this.endYear+'-'+this.endMonth) )
                );
            });
        },
        thisMonthObj: function() {
            var thisMonth = (new Date).getFullYear() + '-' + this.leadingZeros((new Date).getMonth() + 1, 2);

            for(var i=0; i<this.analysisList.length; i++) {
                if(this.analysisList[i].year_month === thisMonth)
                    return this.analysisList[i];
            }
            return '';
        },
        lastMonthObj: function() {
            var lastMonth = (new Date).getFullYear() + '-' + this.leadingZeros((new Date).getMonth(), 2);

            for(var i=0; i<this.analysisList.length; i++) {
                if(this.analysisList[i].year_month === lastMonth)
                    return this.analysisList[i];
            }
            return '';
        },
    },
    async created() {
        for(var i=1; i<13; i++) {
            if(i > 9)
                this.months.push(i);
            else
                this.months.push('0' + i);
        }

        var thisYear = this.leadingZeros((new Date()).getFullYear(), 4)
        
        for(i=0; i<9; i++) {    
            this.years.push(Number(thisYear) - i);
        }

        await axios.get('/api/analysis/sales/month', {
            }).then(res => {
                this.analysisList = res.data;
                console.log(res.data)
        });
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
</style>