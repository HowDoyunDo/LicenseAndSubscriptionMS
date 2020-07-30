<template>
    <div id="addusers" class="contents">
        <h1>사용자 일괄 추가 <code style="color:#3498db; font-size:20px; font-weight:normal"> "{{ license.policy_title }}"</code></h1>
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
            <div id="tip">
                중복된 이메일이 포함될 경우 해당 이메일을 사용하는 사용자의 정보가 업데이트 됩니다.
            </div>
            <br>

            <h4 style="color:gray">일괄 추가 파일 업로드</h4>
            

            <div class="file_input">
                <label>
                    <img src="@/assets/icons/upload.png" style="width:33px; height:30px;"/>
                    파일 업로드
                    <input type="file" id="file" ref="file" @change="handleFileUpload()" onchange="javascript:document.getElementById('file_route').value=this.value">
                </label>
                <input type="text" readonly="readonly" title="File Route" id="file_route">
            </div>
            <br><br>

            <hr style="border-top:1px solid gray">
            <br>
            <br>
            <br>
            <h4 style="color:gray" align="center">다음 정보로 사용자를 등록합니다.</h4>
            <br>
            <div align="center">
                <button style="width:120px" @click="submitFile()">사용자 추가</button>
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
            dept: '',
            file: ''
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
        },
        handleFileUpload() {
            this.file = this.$refs.file.files[0];
        },
        async submitFile() {
            var fileext = document.getElementById('file').value;
            fileext = fileext.slice(fileext.indexOf('.') + 1).toLowerCase();
            
            if(fileext === '') {
                alert('업로드된 파일이 없습니다. \n파일을 업로드해 주세요.');
                return;
            } else if(fileext !== 'xlsx') {
                alert('업로드된 파일은 엑셀파일이 아닙니다. \n확장자를 다시 확인해주세요.');
                return;
            }

            let formData = new FormData();
            formData.append('file', this.file);

            await axios.post('/api/addUsers', 
                formData,
                {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                    params: {
                        'adminUserNo': this.userInfo.no,
                        'licenseNo': this.license.no
                    }
                }).then(res => {
                    if(res.data === true) {
                        alert('사용자 일괄 추가를 완료했습니다.\n중복된 이메일의 사용자가 존재할 경우 사용자의 \n정보가 업데이트됩니다.')
                        window.history.go(-1);
                    } else {
                        alert('파일 내에 추가할 사용자가 없습니다. \n다시 확인하고 업로드해주세요.')
                    }
                }).catch(e => {
                    console.log(e);
                });
        }
    }
}
</script>

<style scoped>
    code {
        font-family: 'Jeju Gothic', sans-serif;
    }
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
        font-weight: normal;
        text-align: left;
    }
    #inner {
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
        font-weight: normal;
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
        margin: 0 0 10px 20px;
        padding: 10px;
        border: 2px solid gray;
        border-radius: 5px;
        color: red;
    }

    .file_input label {
        margin: 0 10px 0 20px;
        padding: 10px;
        border: 1px solid lightgray;
        border-radius: 5px;
        text-align: center;
        position:relative;
        cursor:pointer;
        display:inline-block;
        vertical-align:middle;
    }
    .file_input label input {
        position:absolute;
        width:0;
        height:0;
        overflow:hidden;
    }
    .file_input input[type=text] {
        width:30%;
        line-height:28px;
        font-size:12px;
        padding:0 0 0 5px;
        border:1px solid #777;
        border-radius: 5px;
    }
</style>