<template>
    <div id="addusers" class="contents">
        <h2>사용자 일괄 추가 <code style="color:#3498db; font-size:20px;"> "{{ license.policy_title }}"</code></h2>
        <br>


        <div id="inner">
            <h4 style="color:gray">일괄 추가 양식 다운로드</h4>
            <a href="" @click="addUsersForm()">
                <span id="downfile">
                    <img src="@/assets/icons/download.png" style="width:30px; height:30px;"/>
                    사용자 일괄 추가 양식
                </span>
            </a>
            <div id="tip">
                다음 양식과 데이터 타입에 맞게 작성 후 업로드 해 주십시오.
            </div>
            <br>

            <h4 style="color:gray">일괄 추가 파일 업로드</h4>
            <span id="upfile">
                <img src="@/assets/icons/upload.png" style="width:33px; height:30px;"/>
                파일 업로드
            </span>
            <br><br>

            <hr style="border-top:1px solid gray">
            <br>
            <br>
            <br>
            <h4 style="color:gray" align="center">다음 정보로 사용자를 등록합니다.</h4>
            <br>
            <div align="center">
                <button style="width:120px">사용자 추가</button>
            </div>
            <br>
        <!-- TODO -->
        <!-- 1. general_user 추가 -->
        <!-- 2. email로 general_user 검색 후 no 추출 -->
        <!-- 3. user_no / license_no / user_admin_no 를 사용해 agent 등록 -->
        <!-- 4. license 테이블 current_count +1 하기 -->
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            email: '',
            password: '',
            name: '',
            dept: ''
        }
    },
    computed: {
        license: function() {
            return this.$store.state.licenseStore.selectedlicense;
        },
    },
    methods: {
        async addUsersForm() {
            await axios({
                method: 'POST',
                url: '/api/addusers/form',
                responseType: 'blob',
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(res =>{
                const url = window.URL.createObjectURL(new Blob([res.data], { type: res.headers['content-type'] }));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', 'INZENT_사용자 일괄 추가 양식.xlsx');
                document.body.appendChild(link);
                link.click();
            })
        }
    }
}
</script>

<style scoped>
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
        text-align: left;
    }
    #inner {
        margin-left: 20px;
        padding: 10px;
        border: 1px solid lightgray;
        border-radius: 5px;
    }
    #downfile {
        margin: 0 0 10px 20px;
        padding: 10px;
        border: 1px solid lightgray;
        border-radius: 5px;
        color: #0f9d58;
        text-align: center;
        display: inline-block;
    }
    #upfile {
        margin-left: 20px;
        padding: 10px;
        border: 1px solid lightgray;
        border-radius: 5px;
        text-align: center;
        display: inline-block;
    }
    #tip {
        margin-left: 20px;
        padding: 10px;
        border: 2px solid gray;
        border-radius: 5px;
        color: red;
    }
</style>