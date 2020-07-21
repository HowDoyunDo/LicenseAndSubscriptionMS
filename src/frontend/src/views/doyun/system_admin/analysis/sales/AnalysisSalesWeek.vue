<template>
    <div id="analysisSales" class="contents">
        <h2>매출 분석</h2>
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
                <div style="border:1px solid #ccc; padding:10px; width:10%; min-width:150px; margin-left:7%;">
                    최근
                    <select v-model="selected" style="width:50px;">
                        <option value=2 selected disabled hidden>{{2}}</option>
                        <option v-for="no in nos" :key="no">{{no}}</option>
                    </select> 주
                </div>
            </div>
            <br>

            <div id="result">
                <span id="per">금주/전주 증감 추이</span> <br><br>
                <table class="table_board">
                    <tr>
                        <th colspan="2">기간</th>
                        <th colspan="2">상품 금액 합계</th>
                        <th colspan="2">할인 합계</th>
                        <th colspan="2">결제 합계</th>
                    </tr>
                    <tr>
                        <td colspan="2">금주{{ thisWeek() }}</td>
                        <td>{{ thisWeekObj === '' ? 0 : thisWeekObj.org_price | formatPrice }}원</td>
                        <td rowspan="2" style="color:red">
                            {{ 
                                ( lastWeekObj === '' ? 0 : 
                                ( ((thisWeekObj === '' ? 0 : thisWeekObj.org_price) - (lastWeekObj === '' ? 0 : lastWeekObj.org_price)) 
                                / (lastWeekObj === '' ? 0 : lastWeekObj.org_price) ) * 100 ).toFixed(1)
                            }}% 증가
                        </td>
                        <td>{{ thisWeekObj === '' ? 0 : thisWeekObj.dc_price | formatPrice }}원</td>
                        <td rowspan="2" style="color:red">
                            {{ 
                                ( lastWeekObj === '' ? 0 :
                                ( ((thisWeekObj === '' ? 0 : thisWeekObj.dc_price) - (lastWeekObj === '' ? 0 : lastWeekObj.dc_price)) 
                                / (lastWeekObj === '' ? 0 : lastWeekObj.dc_price) ) * 100 ).toFixed(1)
                            }}% 증가
                        </td>
                        <td>{{ thisWeekObj === '' ? 0 : thisWeekObj.total_price | formatPrice }}원</td>
                        <td rowspan="2" style="color:red">
                            {{ 
                                ( lastWeekObj === '' ? 0 :
                                ( ((thisWeekObj === '' ? 0 : thisWeekObj.total_price) - (lastWeekObj === '' ? 0 : lastWeekObj.total_price)) 
                                / (lastWeekObj === '' ? 0 : lastWeekObj.total_price) ) * 100 ).toFixed(1)
                            }}% 증가
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">전주{{ lastWeek() }}</td>
                        <td>{{ lastWeekObj === '' ? 0 : lastWeekObj.org_price | formatPrice }}원</td>
                        <td>{{ lastWeekObj === '' ? 0 : lastWeekObj.dc_price | formatPrice }}원</td>
                        <td>{{ lastWeekObj === '' ? 0 : lastWeekObj.total_price | formatPrice }}원</td>
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
                        <td>{{al.week_start | formatDate2}} ~<br>{{al.week_end | formatDate2}}</td>
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
            week: 'seltab',
            month: 'none',

            startdate: '',
            enddate: '',

            // orderList
            analysisList: '',
            
            // 주 선택
            selected: 2,
            nos: [],

            // 주 시작일 모음
            weeks: '',
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
        thisWeek() {
            var today = new Date();
            var start = today.getDate() - today.getDay() + 1;
            var end = start + 6;

            start = new Date(today.setDate(start));
            end = new Date(today.setDate(end));
            
            return '(' + this.getDate(start) + ' ~ ' + this.getDate(end) + ')';
        },
        lastWeek() {
            var today = new Date();
            var start = today.getDate() - today.getDay() - 6;
            var end = start + 6;

            start = new Date(today.setDate(start));
            end = new Date(today.setDate(end));
            
            return '(' + this.getDate(start) + ' ~ ' + this.getDate(end) + ')';
        },
    },
    computed: {
        filteredList() {
            return Object.values(this.analysisList).filter(al => {
                var thisWeek = new Date();  // 현재 날짜
                switch( thisWeek.getDay() ) {   // 이번 주의 첫 날짜를 구함
                    case 2: 
                    thisWeek.setDate(thisWeek.getDate-1);
                    break;
                    case 3: 
                    thisWeek.setDate(thisWeek.getDate-2);
                    break;
                    case 4: 
                    thisWeek.setDate(thisWeek.getDate-3);
                    break;
                    case 5: 
                    thisWeek.setDate(thisWeek.getDate-4);
                    break;
                    case 6: 
                    thisWeek.setDate(thisWeek.getDate-5);
                    break;
                    case 7: 
                    thisWeek.setDate(thisWeek.getDate-6);
                    break;
                }

                for(var i = 0; i<this.selected; i++) {  // 선택한 주차까지의 
                    var temp = thisWeek;
                    
                    if(i !== 0) 
                        temp = this.getDate(new Date(temp.setDate(temp.getDate()-7)));
                
                    if(this.getDate(new Date(al.week_start)) === temp)
                        return true;
                }
                return false;
            });
        },
        thisWeekObj: function() {
            var today = new Date();
            var start = today.getDate() - today.getDay() + 1;
            start = this.getDate(new Date(today.setDate(start)));
            console.log(start);

            for(var i=0; i<this.analysisList.length; i++) {
                if(this.analysisList[i].date === start)
                    return this.analysisList[i];
            }
            return '';
        },
        lastWeekObj: function() {
            var today = new Date();
            var start = today.getDate() - today.getDay() - 6;
            start = this.getDate(new Date(today.setDate(start)));
            console.log(start);

            for(var i=0; i<this.analysisList.length; i++) {
                if(this.analysisList[i].date === start)
                    return this.analysisList[i];
            }
            return '';
        },
    },
    async created() {
        for(var i=2; i<53; i++) {
            this.nos.push(i);
        }

        await axios.get('/api/analysis/sales/week', {
            }).then(res => {
                this.analysisList = res.data;
                console.log(res.data)
        });
    }
}
</script>

<style scoped>
    @import "/assets/allList.css";

    .tip {
        border: 2px solid gray;
        color: red;
        font-size: 15px;
        padding: 20px;
        border-radius: 5px;
    }
    .inner {
        margin-left:20px;
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