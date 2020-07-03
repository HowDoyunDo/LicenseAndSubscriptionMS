<template>
    <div id="licenselist" class="contents">
        <h2>라이센스 관리 -- 안씀</h2>
        <table class="info-table">
            <tr>
                <th style="width:5%">번호</th>
                <th style="width:20%">구독 정책명(라이센스 상세)</th>
                <th style="width:10%">이용 기준</th>
                <th style="width:10%">현재/최대<br>사용 수량</th>
                <th style="width:10%">시작일</th>
                <th style="width:10%">종료일</th>
                <th style="width:10%">결제 현황</th>
                <th style="width:20%">라이센스 키</th>
                <th style="width:10%">주문일</th>
                <th style="width:10%">변경 / 취소</th>
            </tr>
            <tr v-for='(license, idx) in licenses' v-bind:key='license.no' @mouseenter="mouseenter()" @mouseleave="mouseleave()" @click="mouseclick(idx, license.standard)" :style="{background: indexcolor}" >
                    <td>{{ idx+1 }}</td>
                    <td style="color:#3498db">{{ license.policy_title }}</td>
                    <td v-if="license.standard === 'U'">사용자 정책</td>
                    <td v-if="license.standard === 'A'">에이전트 정책</td>
                    <td>{{ license.current_count }} / {{ license.max_count }}</td>
                    <td>{{ license.start_date }}</td>
                    <td>{{ license.end_date }}</td>
                    <td>일반 결제 <br>완료</td>
                    <td>{{ license.license_key }}</td>
                    <td>{{ date_to_str(license.reg_date) }}</td>
                    <td>
                        <a href="/">변경</a> /
                        <a href="#">취소</a> 
                    </td>
            </tr>
        </table>

    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            licenses: [],
            indexcolor: ''
        }
    },
    async created() {
        await axios.get('/api/license/list', {
                params: {
                    adminNo: 1
                }
        }).then(res => { 
            console.log(res.data);
            this.licenses = res.data;

        });
    },
    methods: {
        mouseenter() {
            this.indexcolor = '#d9edf7';
        },
        mouseleave() {
            this.indexcolor = '#fff';
        },
        mouseclick(idx, standard) {
            console.log(this.licenses[idx].policy_title);
            this.$store.commit('licenseStore/SELECT_LIC', this.licenses[idx]);
            this.$router.push({ name: "LicenseInfo", params: { licenseno: idx, licensetype: standard }});
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
    #licenselist {
        width: 85%;
        border-left: 1px solid lightgray;
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
        font-weight: bold;
        border: none;
        width: 20%;
        text-align: center;
        padding: 15px;
    }
    .info-table td {
        padding: 15px;
        font-weight: bold;
        text-align: center;
    }
    .info-table input {
        width: 100px;
    }
</style>