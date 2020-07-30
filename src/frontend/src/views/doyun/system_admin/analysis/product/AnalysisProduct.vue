<template>
    <div id="analysisSales" class="contents">
        <h1>상품 분석</h1>
        <br>

        <div class="inner">
            <p class="tip">
                통계 자료는 국세청 및 기타 제출용 자료로 사용이 불가능하며, 라이선스 운영의 참고 자료로 이용하시기 바랍니다.
            </P>
            <div id="option">
                <span id="per">기간</span> <br><br>
                <span v-bind:class="today_per" style="margin-left:7%" @click="selPer(1)">오늘</span>
                <span v-bind:class="three_per" @click="selPer(2)">3일</span>
                <span v-bind:class="week_per" @click="selPer(3)">7일</span>
                <span v-bind:class="month_per" @click="selPer(4)">1개월</span>
                <span v-bind:class="threemonth_per" @click="selPer(5)">3개월</span>
                <span v-bind:class="sixmonth_per" @click="selPer(6)">6개월</span> <br><br>
                <div style="margin-left:7%">
                    <input type="datetime-local" style="width:200px" v-model="startdate" @change="changeDate()"> ~
                    <input type="datetime-local" style="width:200px" v-model="enddate" @change="changeDate()">
                </div>
                <br><hr>

                <span id="per">정책 가격</span> <br><br>
                <div style="border:1px solid #ccc; padding:10px; width:10%; min-width:280px; margin-left:7%;">
                    <input type="number" style="width:100px" v-model="startPrice"/>~<input type="number" style="width:100px" v-model="endPrice"/>원
                </div>

                <hr>
                <button style="margin-left:7%" @click="clrAll()">초기화</button>
            </div>
            <br>

            <span v-bind:class="open" @click="seltab(1)">공개 정책</span>
            <span v-bind:class="close" @click="seltab(2)">비공개 정책</span>
            <span v-bind:class="user" @click="seltab(3)">사용자 정책</span>
            <span id="lasttab" v-bind:class="agent" @click="seltab(4)">에이전트 정책</span>
            <span id="last"><span style="visibility: hidden;">asd</span></span>
            <div id="result">
                <span id="per">결과</span> <br><br>
                <table class="table_board">
                    <tr>
                        <th width="5%">순위</th>
                        <th width="5%">정책 번호</th>
                        <th width="20%">정책 명</th>
                        <th width="10%">정책 가격</th>
                        <th width="5%">주문 수</th>
                        <th width="10%">판매 금액</th>
                        <th width="10%">할인 금액</th>
                        <th width="5%" style="visibility: hidden;"></th>
                        <th width="10%">결제 금액</th>
                        <!-- <th width="15%">환불 합계</th> -->
                    </tr>
                    <tr v-for="(al, idx) in filteredList" :key="al.date">
                        <td>{{idx+1}}</td>
                        <td>{{al.policy_no}}</td>
                        <td>{{al.policy_title}}</td>
                        <td>{{al.price | formatPrice}}원</td>
                        <td>{{al.order_cnt}}</td>
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
            open: 'seltab',
            close: 'none',
            user: 'none',
            agent: 'none',

            startPrice: '',
            endPrice: '',

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

            // 정책 탭
            poltab: 1,
        }
    },
    methods: {
        seltab(tabno) {
            switch(tabno) {
                case 1: this.open = 'seltab'; this.close = 'none'; this.user = 'none'; this.agent = 'none';
                    break;
                case 2: this.open = 'none'; this.close = 'seltab'; this.user = 'none'; this.agent = 'none';
                    break;
                case 3: this.open = 'none'; this.close = 'none'; this.user = 'seltab'; this.agent = 'none';
                    break;
                case 4: this.open = 'none'; this.close = 'none'; this.user = 'none'; this.agent = 'seltab';
                    break;
            }
            this.poltab = tabno;
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
                        this.startdate = '';
                    } else {
                        this.clrPer();
                        this.today_per = 'selper';

                        var temp = new Date();
                        this.startdate = this.getDate(temp);
                    }
                    break;
                case 2:
                    if(this.three_per === 'selper') {
                        this.clrPer();
                        this.startdate = '';
                    } else {
                        this.clrPer();
                        this.three_per = 'selper';

                        temp = new Date();
                        temp.setDate(temp.getDate() - 3);
                        this.startdate = this.getDate(temp);
                    }
                    break;
                case 3:
                    if(this.week_per === 'selper') {
                        this.clrPer();
                        this.startdate = '';
                    } else {
                        this.clrPer();
                        this.week_per = 'selper';

                        temp = new Date();
                        temp.setDate(temp.getDate() - 7);
                        this.startdate = this.getDate(temp);
                    }
                    break;
                case 4:
                    if(this.month_per === 'selper') {
                        this.clrPer();
                        this.startdate = '';
                    } else {
                        this.clrPer();
                        this.month_per = 'selper';

                        temp = new Date();
                        temp.setMonth(temp.getMonth() - 1);
                        this.startdate = this.getDate(temp);
                    }
                    break;
                case 5:
                    if(this.threemonth_per === 'selper') {
                        this.clrPer();
                        this.startdate = '';
                    } else {
                        this.clrPer();
                        this.threemonth_per = 'selper';

                        temp = new Date();
                        temp.setMonth(temp.getMonth() - 3);
                        this.startdate = this.getDate(temp);
                    }
                    break;
                case 6:
                    if(this.sixmonth_per === 'selper') {
                        this.clrPer();
                        this.startdate = '';
                    } else {
                        this.clrPer();
                        this.sixmonth_per = 'selper';

                        temp = new Date();
                        temp.setMonth(temp.getMonth() - 6);
                        this.startdate = this.getDate(temp);
                    }
                    break;
            }

            axios.get('/api/analysis/product', {
                params: {
                    start: this.startdate,
                    end: this.enddate,
                }
                }).then(res => {
                    this.analysisList = res.data;
            });
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
        changeDate() {
            axios.get('/api/analysis/product', {
                params: {
                    start: this.startdate,
                    end: this.enddate,
                }
                }).then(res => {
                    this.analysisList = res.data;
            });
        },
        clrAll() {
            this.startPrice = '',
            this.endPrice = '',

            this.startdate = '',
            this.enddate = '',

            this.clrPer()

            axios.get('/api/analysis/product', {
                params: {
                    start: this.startdate,
                    end: this.enddate,
                }
                }).then(res => {
                    this.analysisList = res.data;
            });
        }
    },
    computed: {
        filteredList() {
            return Object.values(this.analysisList).filter(al => {
                
                var policy = '';
                switch(this.poltab) {
                    case 1: policy = al.visible === true;
                    break;
                    case 2: policy = al.visible === false;
                    break;
                    case 3: policy = al.standard === 'U';
                    break;
                    case 4: policy = al.standard === 'A';
                    break;
                }

                return (
                    policy
                    && (this.startPrice === '' ? true : (al.price >= this.startPrice))
                    && (this.endPrice === '' ? true : (al.price <= this.endPrice))
                )
            });
        },
    },
    async created() {
        await axios.get('/api/analysis/product', {
            params: {
                start: this.startdate,
                end: this.enddate,
            }
            }).then(res => {
                this.analysisList = res.data;
                console.log(res.data);
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
        width:60%;
        height: 100%;
        border-bottom: 1px solid #ccc;
        padding: 10px 20px 10px;
    }
    #option {
        border: 1px solid #ccc;
        padding: 30px;
    }
    #result {
        border: 1px solid #ccc;
        border-top: none;
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