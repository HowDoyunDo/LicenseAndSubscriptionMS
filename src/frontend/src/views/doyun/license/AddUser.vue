<template>
    <div id="adduser" class="contents">
        <h2>사용자 개별 추가 <code style="color:#3498db; font-size:20px;"> "{{ license.policy_title }}"</code></h2>
        <br>

        <div id="inner">
            <h4 style="color:gray">사용자 추가 신청서</h4>
            <table class="info-table">
                <tr>
                    <th>사용자 이메일</th>
                    <td><input type="email" style="width:300px" v-model="email"/></td>
                </tr>
                <tr>
                    <th>사용자 비밀번호</th>
                    <td><input type="password" style="width:200px" v-model="password"/></td>
                </tr>
                <tr>
                    <th>사용자 이름</th>
                    <td><input type="text" style="width:200px" v-model="name"/></td>
                </tr>
                <tr>
                    <th>사용자 부서</th>
                    <td><input type="text" style="width:200px" v-model="dept"/></td>
                </tr>                                            
            </table>
            <br>
            <hr style="border-top:1px solid gray">
            <br>
            <br>
            <h4 style="color:gray" align="center">다음 정보로 사용자를 등록합니다.</h4>
            <br>
            <div align="center">
                <button style="width:120px">사용자 추가</button>
            </div>
            <br>
        </div>

        <!-- TODO -->
        <!-- 1. general_user 추가 -->
        <!-- 2. email로 general_user 검색 후 no 추출 -->
        <!-- 3. user_no / license_no / user_admin_no 를 사용해 agent 등록 -->
        <!-- 4. license 테이블 current_count +1 하기 -->
    </div>
</template>

<script>
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
</style>