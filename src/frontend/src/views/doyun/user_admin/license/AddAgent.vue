<template>
    <div id="addagent" class="contents">
        <h2>에이전트 개별 추가 <code style="color:#3498db; font-size:20px; font-weight:bold"> "{{ license.policy_title }}"</code></h2>
        <br>

        <div id="inner">
            <h4 style="color:gray">에이전트 추가 신청서</h4>
            <span id="tab-mac" @click="selmac()" :style="{background: macstyle}"><a>MAC주소 기준</a></span>
            <span id="tab-ip" @click="selip()" :style="{background: ipstyle}"><a>IP주소 기준</a></span>
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
                <tr style="border-bottom: 2px solid #ccc">
                    <th>사용자 부서</th>
                    <td><input type="text" style="width:200px" v-model="dept"/></td>
                </tr>   
                <tr>
                    <th>에이전트 명</th>
                    <td><input type="text" style="width:200px" v-model="agentname"/></td>
                </tr>
                <tr v-if="addrtab">
                    <th>MAC Address</th>
                    <td><input type="text" style="width:200px" v-model="macaddr"/></td>
                </tr>
                <tr v-if="!addrtab">
                    <th>IP Address</th>
                    <td><input type="text" style="width:200px" v-model="ipaddr"/></td>
                </tr>                                         
            </table>
            <br>
            <hr style="border-top:1px solid gray">
            <br>
            <br>
            <h4 style="color:gray" align="center">다음 정보로 에이전트를 등록합니다.</h4>
            <br>
            <div align="center">
                <button style="width:120px" @click="addAgent()">에이전트 추가</button>
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
            email: '',
            password: '',
            name: '',
            dept: '',

            //agent 추가 params
            agentname: '',
            macaddr: '',
            ipaddr: '',

            addrtab: true,
            macstyle: '#eaeaea',
            ipstyle: '',
        }
    },
    computed: {
        license: function() {
            return this.$store.state.licenseStore.selectedlicense;
        },
        userInfo: function() {
            return this.$store.state.userinfo.userInfo
        },
    },
    methods: {
        addAgent() {
            if(this.email === '') {
                alert('사용자의 이메일을 입력해주세요.')
            } else if (this.password === '') {
                alert('사용자의 비밀번호를 입력해주세요.')
            } else if (this.name === '') {
                alert('사용자의 이름을 입력해주세요.')
            } else if (this.dept === '') {
                alert('사용자의 부서를 입력해주세요.')
            } else if (this.agentname === '') {
                alert('에이전트 명을 입력해주세요.')
            } else if (this.macaddr === '' && this.addrtab) {
                alert('MAC Address를 입력해주세요.')
            } else if (this.ipaddr === '' && !this.addrtab) {
                alert('IP Address를 입력해주세요.')
            } else if (this.license.current_count === this.license.max_count) {
                alert('현재 에이전트가 꽉 차 사용자를 추가할 수 없습니다.')
            } else {
                // <!-- TODO -->
                // <!-- 1. general_user 추가 -->
                // <!-- 2. email로 general_user 검색 후 no 추출 -->
                // <!-- 3. user_no / license_no / user_admin_no 를 사용해 agent 등록 -->
                // <!-- 4. license 테이블 current_count +1 하기 -->
                axios.post('/api/addAgent', null, {
                    params: {
                        'email': this.email,
                        'password': this.password,
                        'name': this.name,
                        'dept': this.dept,

                        'agentname': this.agentname,
                        'macaddr': this.macaddr,
                        'ipaddr': this.ipaddr,

                        'adminUserNo': this.userInfo.no,
                        'licenseNo': this.license.no
                    }
                })
                .then(res => { 
                    if(res.data === false) {
                        if(this.addrtab)
                            alert('해당 라이선스 내 중복된 MAC Address를 가진 에이전트가 존재합니다.\n확인 후 다시 입력해주세요.');
                        else
                            alert('해당 라이선스 내 중복된 IP Address를 가진 에이전트가 존재합니다.\n확인 후 다시 입력해주세요.');
                    } else {
                        alert('에이전트를 추가했습니다.');
                        window.history.go(-1);
                    }
                });

            }
        },
        selmac() {
            this.addrtab = true;
            this.macstyle = '#eaeaea';
            this.ipstyle = '';
            this.ipaddr = '';
        },
        selip() {
            this.addrtab = false;
            this.macstyle = '';
            this.ipstyle = '#eaeaea';
            this.macaddr = '';
        }
    }
}
</script>

<style scoped>
    .info-table {
        border: 2px solid #ccc;
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

    #tab-mac {
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
        padding: 10px;
        display: inline-block;
        cursor: pointer;
        border: 1px solid #ccc;
        border-bottom: 1px solid #eaeaea;
    }
    #tab-ip {
        padding: 10px;
        display: inline-block;
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
        cursor: pointer;
        border: 1px solid #ccc;
        border-bottom: 1px solid #eaeaea;
    }
</style>