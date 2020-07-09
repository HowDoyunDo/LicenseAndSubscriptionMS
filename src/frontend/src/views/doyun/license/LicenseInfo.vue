<template>
    <div id="licenseinfo" class="contents">
        <template v-if="type === 'U'">
            <h2>사용자 관리 <code style="color:#3498db; font-size:20px; font-weight:bold"> "{{ license.policy_title }}"</code></h2>
            <br>
            <div class="inner">
                <h4 style="color:gray">보유 사용자 조회</h4>
                <table class="info-table">
                    <tr>
                        <th>이메일</th>
                        <td><input type="text" style="width:300px" v-model="useremail"/></td>
                    </tr>
                    <tr>
                        <th>사용자 이름</th>
                        <td><input type="text" style="width:300px" v-model="username"/></td>
                    </tr>
                    <tr>
                        <th>사용자 부서</th>
                        <td><input type="text" style="width:300px" v-model="userdept"/></td>
                    </tr>
                    <tr>
                        <th>마지막 로그인 날짜</th>
                        <td><input type="datetime-local" style="width:200px" v-model="startdate"/> ~ 
                        <input type="datetime-local" style="width:200px" v-model="enddate"/></td> 
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align:center">
                            <button @click="search_user()">
                                검색
                            </button>
                        </td>
                    </tr>
                </table>
                <br><br>
                
                <h4 style="color:gray">사용자 목록
                    <span v-if="this.delete === false">
                        <button style="width:90px; margin: 10px; float:right" @click="deleteUsr()">삭제</button>
                        <router-link to="/addusers">
                            <button style="width:90px; margin: 10px; float:right">
                                일괄 추가
                            </button>
                        </router-link>
                        <router-link to="/adduser">
                            <button style="width:90px; margin: 10px; float:right">
                                개별 추가
                            </button>
                        </router-link>
                    </span>
                    <!-- 삭제 버튼 누를 시 -->
                    <span v-if="this.delete === true">
                        <button style="width:90px; margin: 10px; float:right" @click="deleteUsr()">취소</button>
                        <button style="width:90px; margin: 10px; float:right" @click="deleteCom()">삭제 완료</button>
                    </span>
                </h4>
                <template v-if="this.delete === false">
                    <table class="info-table" style="text-align: center">
                        <tr>
                            <th style="width:10%">번호</th>
                            <th style="width:15%">사용자 이름</th>
                            <th style="width:30%">이메일</th>
                            <th style="width:15%">사용자 부서명</th>
                            <th style="width:30%">마지막 로그인</th>
                        </tr>
                        <tr v-for="(gu, idx) in generalusers" v-bind:key="gu.no">
                            <td>{{ idx+1 }}</td>
                            <td>{{ gu.name }}</td>
                            <td>{{ gu.email }}</td>
                            <td>{{ gu.dept_name }}</td>
                            <td>{{ date_to_str(gu.last_login) }}</td>
                        </tr>
                    </table>
                </template>
                <!-- 삭제 버튼 누를 시 -->
                <template v-if="this.delete === true">
                    <table class="info-table" style="text-align: center">
                        <tr>
                            <th style="width:10%">삭제</th>
                            <th style="width:15%">사용자 이름</th>
                            <th style="width:30%">이메일</th>
                            <th style="width:15%">사용자 부서명</th>
                            <th style="width:30%">마지막 로그인</th>
                        </tr>
                        <tr v-for="gu in generalusers" v-bind:key="gu.no">
                            <td><input type="checkbox" @change="checkbox(gu.no)"></td>
                            <td>{{ gu.name }}</td>
                            <td>{{ gu.email }}</td>
                            <td>{{ gu.dept_name }}</td>
                            <td>{{ date_to_str(gu.last_login) }}</td>
                        </tr>
                    </table>
                </template>
                <br>
            </div>
        </template>


        <template v-if="type === 'A'">
            <h2>에이전트 관리 <code style="color:#3498db; font-size:20px; font-weight:bold"> "{{ license.policy_title }}"</code></h2>
            <br>
            <div class="inner">
                <h4 style="color:gray">보유 에이전트 조회</h4>
                <span id="tab-mac" @click="selmac()" :style="{background: macstyle}"><a>MAC주소 기준</a></span>
                <span id="tab-ip" @click="selip()" :style="{background: ipstyle}"><a>IP주소 기준</a></span>
                <table class="info-table">
                    <tr>
                        <th>이메일</th>
                        <td><input type="text" style="width:300px" v-model="useremail"/></td>
                    </tr>
                    <tr>
                        <th>사용자 이름</th>
                        <td><input type="text" style="width:300px" v-model="username"/></td>
                    </tr>
                    <tr>
                        <th>사용자 부서</th>
                        <td><input type="text" style="width:300px" v-model="userdept"/></td>
                    </tr>
                    <tr style="border-bottom: 2px solid #ccc">
                        <th>마지막 로그인 날짜</th>
                        <td><input type="datetime-local" style="width:200px" v-model="startdate"/> ~ 
                        <input type="datetime-local" style="width:200px" v-model="enddate"/></td> 
                    </tr>

                    <tr>
                        <th>에이전트 명</th>
                        <td><input type="text" style="width:300px" v-model="agentname"/></td>
                    </tr>
                    <tr v-if="addrtab">
                        <th>MAC Address</th>
                        <td><input type="text" style="width:300px" v-model="macaddr"/></td>
                    </tr>
                    <tr v-if="!addrtab">
                        <th>IP Address</th>
                        <td><input type="text" style="width:300px" v-model="ipaddr"/></td>
                    </tr>

                    <tr>
                        <td colspan="2" style="text-align:center">
                            <button @click="search_agent()">
                                검색
                            </button>
                        </td>
                    </tr>
                </table>
                <br><br>

                <h4 style="color:gray">에이전트 목록
                    <span v-if="this.delete === false">
                        <button style="width:90px; margin: 10px; float:right" @click="deleteUsr()">삭제</button>
                        <router-link to="/addagent">
                            <button style="width:150px; margin: 10px; float:right">
                                에이전트 추가
                            </button>
                        </router-link>
                    </span>
                    <!-- 삭제 버튼 누를 시 -->
                    <span v-if="this.delete === true">
                        <button style="width:90px; margin: 10px; float:right" @click="deleteUsr()">취소</button>
                        <button style="width:90px; margin: 10px; float:right" @click="deleteCom()">삭제 완료</button>
                    </span>
                </h4>

                <template v-if="this.delete === false">
                    <table class="info-table" style="text-align: center">
                        <tr>
                            <th style="width:7%">번호</th>
                            <th style="width:10%">사용자 이름</th>
                            <th style="width:20%">이메일</th>
                            <th style="width:10%">부서명</th>

                            <th style="width:10%">에이전트 명</th>
                            <th style="width:15%">MAC Address</th>
                            <th style="width:15%">IP Address</th>

                            <th style="width:20%">마지막 로그인</th>
                        </tr>
                        <tr v-for="(gu, idx) in generalusers" v-bind:key="gu.no">
                            <td>{{ idx+1 }}</td>
                            <td>{{ gu.name }}</td>
                            <td>{{ gu.email }}</td>
                            <td>{{ gu.dept_name }}</td>

                            <td>{{ gu.agent_name }}</td>
                            <td>{{ gu.mac_address }}</td>
                            <td>{{ gu.ip_address }}</td>

                            <td>{{ date_to_str(gu.last_login) }}</td>
                        </tr>
                    </table>
                </template>

                <template v-if="this.delete === true">
                    <table class="info-table" style="text-align: center">
                        <tr>
                            <th style="width:7%">삭제</th>
                            <th style="width:10%">사용자 이름</th>
                            <th style="width:20%">이메일</th>
                            <th style="width:10%">부서명</th>

                            <th style="width:10%">에이전트 명</th>
                            <th style="width:15%">MAC Address</th>
                            <th style="width:15%">IP Address</th>

                            <th style="width:20%">마지막 로그인</th>
                        </tr>
                        <tr v-for="gu in generalusers" v-bind:key="gu.no">
                            <td><input type="checkbox" @change="checkbox(gu.no)"></td>
                            <td>{{ gu.name }}</td>
                            <td>{{ gu.email }}</td>
                            <td>{{ gu.dept_name }}</td>

                            <td>{{ gu.agent_name }}</td>
                            <td>{{ gu.mac_address }}</td>
                            <td>{{ gu.ip_address }}</td>

                            <td>{{ date_to_str(gu.last_login) }}</td>
                        </tr>
                    </table>
                </template>
                <br>

            </div>
        </template>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            useremail: '',
            username: '',
            userdept: '',
            startdate: '',
            enddate: '',
            delete: false,
            delUsrNos: [],

            //agent 검색 params
            agentname: '',
            macaddr: '',
            ipaddr: '',

            //tab param
            addrtab: true,
            macstyle: '#eaeaea',
            ipstyle: '',
        }
    },
    computed: {
        idx: function() {
            return this.$route.params.licenseno;
        },
        type: function() {
            return this.$route.params.licensetype;
        },
        license: function() {
            return this.$store.state.licenseStore.selectedlicense;
        },
        generalusers: function() {
            return this.$store.state.licenseStore.generalusers;
        }
    },
    methods: {
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
        search_agent() {
            if(this.startdate !== '' && this.enddate === '') {
                alert('검색 날짜 범위를 올바르게 입력해주세요.');
                return;
            }

            axios.get('/api/license/search/agent', {
                params: {
                    licenseNo: this.license.no,

                    email: this.useremail,
                    name: this.username,
                    dept: this.userdept,
                    start: this.startdate,
                    end: this.enddate,

                    agentname: this.agentname,
                    macaddr: this.macaddr,
                    ipaddr: this.ipaddr,
                }
            }).then(res => { 
                if(res.data.length === 0) {
                    alert('검색 결과가 없습니다.');
                    return;
                }
                console.log(res.data);
                this.$store.commit('licenseStore/SELECT_USR', res.data);
            });
        },
        search_user() {
            if(this.startdate !== '' && this.enddate === '') {
                alert('검색 날짜 범위를 올바르게 입력해주세요.');
                return;
            }

            axios.get('/api/license/search', {
                params: {
                    licenseNo: this.license.no,

                    email: this.useremail,
                    name: this.username,
                    dept: this.userdept,
                    start: this.startdate,
                    end: this.enddate,
                }
            }).then(res => { 
                if(res.data.length === 0) {
                    alert('검색 결과가 없습니다.');
                    return;
                }
                console.log(res.data);
                this.$store.commit('licenseStore/SELECT_USR', res.data);
            });
        },
        deleteUsr() {
            this.delete = !this.delete;
            this.delUsrNos = [];
        },
        checkbox(userno) {
            if(this.delUsrNos.indexOf(userno) == -1) {   // userno 포함 X
                this.delUsrNos.push(userno);
            } else {    // userno 포함
                this.delUsrNos.splice(this.delUsrNos.indexOf(userno),1);
            }
        },
        async deleteCom() {
            if (confirm("정말 다음 사용자를 삭제하시겠습니까?") == true){    //확인
                // TODO
                // 1. general_user 내의 delUsrNos를 삭제
                // 2. agent delUsrNos를 포함한 컬럼 삭제
                // 3. license -> current_count --
                var str = '(';
                for(var i = 0; i<this.delUsrNos.length; i++) {
                    if(i == this.delUsrNos.length-1) str += this.delUsrNos[i]+')'
                    else str += this.delUsrNos[i]+', '
                }
                console.log(str)

                if(this.type === 'U') {
                    await axios.get('/api/deluser', {
                            params: {
                                delUsrs: str,
                                licenseNo: this.license.no
                            }
                    }).then(res => { 
                        console.log(res.data)
                    });

                    this.delete = false;
                    alert('사용자를 삭제했습니다.')

                    axios.get('/api/license/userlist', {
                        params: {
                            licenseNo: this.license.no
                        }
                    }).then(res => { 
                        console.log(res.data);
                        this.$store.commit('licenseStore/SELECT_USR', res.data);
                    });
                } else if (this.type === 'A') {
                    await axios.get('/api/delagent', {
                            params: {
                                delUsrs: str,
                                licenseNo: this.license.no
                            }
                    }).then(res => { 
                        console.log(res.data)
                    });

                    this.delete = false;
                    alert('에이전트를 삭제했습니다.')

                    axios.get('/api/license/agentlist', {
                            params: {
                                licenseNo: this.license.no
                            }
                    }).then(res => { 
                        console.log(res.data);
                        this.$store.commit('licenseStore/SELECT_USR', res.data);
                    });
                }
                
            } else {   //취소
                return;
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
    },
    async created() {
        if(this.type === 'U') {
            await axios.get('/api/license/userlist', {
                    params: {
                        licenseNo: this.license.no
                    }
            }).then(res => { 
                console.log(res.data);
                this.$store.commit('licenseStore/SELECT_USR', res.data);
            });
        } else if(this.type === 'A') {
            await axios.get('/api/license/agentlist', {
                    params: {
                        licenseNo: this.license.no
                    }
            }).then(res => { 
                console.log(res.data);
                this.$store.commit('licenseStore/SELECT_USR', res.data);
            });
        }
    },
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
    }
    .info-table input {
        width: 100px;
    }
    .inner {
        margin-left:20px;
        text-align: left;
    }
    #tab-mac {
        border-bottom: none;
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
        padding: 10px;
        display: inline-block;
        cursor: pointer;
    }
    #tab-ip {
        border-bottom: none;
        padding: 10px;
        display: inline-block;
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
        cursor: pointer;
    }
</style>