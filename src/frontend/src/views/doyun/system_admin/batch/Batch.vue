<template>
    <div id="batch" class="contents">
        <h1>배치 서비스</h1>
        <br>
        <div class="inner">
            <h4 style="color:gray">현재 배치 서비스</h4>
            <p class="tip">[ {{ batch.batch_period_days }}일 ] 주기로 
                마지막 로그인으로부터 [
                <span v-if="batch.login_limit_months !== 0">{{ batch.login_limit_months }}개월 </span> 
                <span v-if="batch.login_limit_days !== 0">{{ batch.login_limit_days }}일 </span>
                <span v-if="batch.login_limit_hours !== 0">{{ batch.login_limit_hours }}시간 </span>] 이상 지난 사용자를 삭제합니다.
            </P>
            <table class="info-table">
                <tr>
                    <th style="width:15%">배치 서비스 주기</th>
                    <th style="width:15%">비로그인 제한 (개월)</th>
                    <th style="width:15%">비로그인 제한 (일)</th>
                    <th style="width:15%">비로그인 제한 (시간)</th>
                    <th style="width:20%">최근 배치 실행 시간</th>
                    <th style="width:20%">다음 배치 실행 시간</th>
                </tr>
                <tr>
                    <td>{{ batch.batch_period_days }}일</td>
                    <td>{{ batch.login_limit_months }}개월</td>
                    <td>{{ batch.login_limit_days }}일</td>
                    <td>{{ batch.login_limit_hours }}시간</td>
                    <td>{{ date_to_str(batch.recent_batch) }}</td>
                    <td>{{ date_to_str(batch.next_batch) }}</td>
                </tr>                                          
            </table>
            <br><br>

            <h4 style="color:gray">배치 서비스 변경</h4>
            <p class="tip">[ {{ batch_period_day }}일 ] 주기로 
                마지막 로그인으로부터 [
                <span v-if="batch.login_limit_months !== ''">{{ login_limit_months }}개월 </span> 
                <span v-if="batch.login_limit_days !== ''">{{ login_limit_days }}일 </span>
                <span v-if="batch.login_limit_hours !== ''">{{ login_limit_hours }}시간 </span>] 이상 지난 사용자를 삭제합니다.
            </P>
            <table class="info-table">
                <tr>
                    <th>배치 서비스 주기</th>
                    <th>비로그인 제한 (개월)</th>
                    <th>비로그인 제한 (일)</th>
                    <th>비로그인 제한 (시간)</th>
                </tr>
                <tr>
                    <td><input type="number" v-model="batch_period_day"/>일</td>
                    <td><input type="number" v-model="login_limit_months"/>개월</td>
                    <td><input type="number" v-model="login_limit_days"/>일</td>
                    <td><input type="number" v-model="login_limit_hours"/>시간</td>
                </tr>                                          
            </table>
            <br>
            <br>
            <hr style="border-top:1px solid gray">
            <br>
            <h4 style="color:gray" align="center">다음 정보로 배치서비스를 변경합니다.</h4>
            <br>
            <div align="center">
                <button style="width:180px" @click="chgbatch()">배치 서비스 변경</button>
            </div>
            <br>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        return {
            batch: '',

            // 배치 변경 데이터
            batch_period_day: '',
            login_limit_months: '',
            login_limit_days: '',
            login_limit_hours: '',
        }
    },
    async created() {
        await axios.get('/api/getBatchInfo', {
            params: {
                sol : this.solution
            }
        }).then(res => { 
            this.batch = res.data;
            console.log(res.data);
        });
    },
    methods: {
        async chgbatch() {
            await axios.get('/api/updateBatch', {
                params: {
                    period: this.batch_period_day,
                    months: this.login_limit_months,
                    days: this.login_limit_days,
                    hours: this.login_limit_hours
                }
            }).then(res => { 
                console.log(res.data);
            });

            await axios.get('/api/getBatchInfo', {
                params: {
                    sol : this.solution
                }
            }).then(res => { 
                this.batch = res.data;
                console.log(res.data);
            });

            alert('배치 서비스를 변경 완료했습니다.');
            this.batch_period_day = '';
            this.login_limit_months = '';
            this.login_limit_days = '';
            this.login_limit_hours = '';
        },
        date_to_str(format) {
            format = new Date(format);
            var year = format.getFullYear(); var month = format.getMonth() + 1;
            if(month<10) month = '0' + month; var date = format.getDate();
            if(date<10) date = '0' + date; var hour = format.getHours();
            if(hour<10) hour = '0' + hour; var min = format.getMinutes();
            if(min<10) min = '0' + min; var sec = format.getSeconds();
            if(sec<10) sec = '0' + sec;

            return year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;
        },
    }
}
</script>

<style scoped>
    h1 {
        font-size: 4.5rem;
        color:#000000D9;
    }
    .info-table {
        border-top: 2px solid #ccc;
        border-bottom: 2px solid #ccc;
        width: 100%;
        max-width: 100%;
        border-spacing: 0;
        border-collapse: collapse;
        table-layout: fixed;
    }
    .info-table tr {
        border-bottom: 1px solid #ccc;    
        font-size: 15px;
    }
    .info-table th {
        background: #eaeaea;
        font-weight: normal;
        border: none;
        width: 20%;
        text-align: center;
        padding: 15px;
    }
    .info-table td {
        padding: 15px;
        text-align: center;
        font-weight: normal;
    }
    .inner {
        text-align: left;
    }
    .tip {
        border: 2px solid gray;
        color: red;
        font-size: 15px;
        padding: 20px;
        border-radius: 5px;
    }
</style>