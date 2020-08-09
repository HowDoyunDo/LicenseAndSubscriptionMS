<template>
  <div id="licenseinfo" class="contents">
    <template v-if="type === 'U'">
      <h1>
        사용자 관리
        <code style="color:#3498db; font-size:20px; font-weight:normal">
          "{{ license.policy_title }}"</code
        >
      </h1>
      <br />
      <div class="inner">
        <h4 style="color:gray">보유 사용자 조회</h4>
        <table class="info-table">
          <tr>
            <th>이메일</th>
            <td style="text-align:left">
              <input
                type="text"
                style="width:300px"
                v-model="useremail"
                @input="resetPageNum"
              />
            </td>
          </tr>
          <tr>
            <th>사용자 이름</th>
            <td style="text-align:left">
              <input
                id="username"
                type="text"
                style="width:300px"
                @input="username = $event.target.value"
                v-model="username"
                v-on:input="resetPageNum"
              />
            </td>
          </tr>
          <tr>
            <th>사용자 부서</th>
            <td style="text-align:left">
              <input
                type="text"
                style="width:300px"
                @input="userdept = $event.target.value"
                v-model="userdept"
                v-on:input="resetPageNum"
              />
            </td>
          </tr>
          <tr>
            <th>마지막 로그인 날짜</th>
            <td style="text-align:left">
              <input
                type="datetime-local"
                style="width:200px"
                v-model="startdate"
                @input="resetPageNum"
              />
              ~
              <input
                type="datetime-local"
                style="width:200px"
                v-model="enddate"
                @input="resetPageNum"
              />
            </td>
          </tr>
          <tr>
            <td colspan="2" style="text-align:center; padding:5px;">
              <button style="vertical-align:middle; margin:0" @click="reset()">
                초기화
              </button>
            </td>
          </tr>
        </table>

        <br /><br />

        <h4 style="color:gray">
          사용자 목록
          <span v-if="this.delete === false && this.active === false">
            <button style="margin: 10px; float:right" @click="deleteUsr()">
              삭제
            </button>
            <button
              style="margin: 10px; float:right; width: 120px"
              @click="chgActive()"
            >
              활성화 변경
            </button>
            <router-link to="/addusers">
              <button style="width: 100px; margin: 10px; float:right">
                일괄 추가
              </button>
            </router-link>
            <router-link to="/adduser">
              <button style="width: 100px; margin: 10px; float:right">
                개별 추가
              </button>
            </router-link>
          </span>
          <!-- 삭제 버튼 누를 시 -->
          <span v-if="this.delete === true">
            <button style="margin: 10px; float:right" @click="deleteUsr()">
              취소
            </button>
            <button
              style="width:100px; margin: 10px; float:right"
              @click="deleteCom()"
            >
              삭제 완료
            </button>
          </span>
          <span v-if="this.active === true">
            <button style="margin: 10px; float:right" @click="chgActive()">
              취소
            </button>
            <button
              style="width:100px; margin: 10px; float:right"
              @click="changeCom()"
            >
              활성화 완료
            </button>
          </span>
        </h4>
        <template v-if="this.delete === false && this.active === false">
          <table class="info-table" style="text-align: center">
            <tr>
              <th style="width:10%">번호</th>
              <th style="width:15%">사용자 이름</th>
              <th style="width:30%">이메일</th>
              <th style="width:15%">사용자 부서명</th>
              <th style="width:30%">마지막 로그인</th>
              <th style="width:15%">활성화 여부</th>
            </tr>
            <tr v-for="(gu, idx) in paginatedData" v-bind:key="gu.no">
              <td>{{ idx + 1 }}</td>
              <td>{{ gu.name }}</td>
              <td>{{ gu.email }}</td>
              <td>{{ gu.dept_name }}</td>
              <td>{{ date_to_str(gu.last_login) }}</td>
              <td v-if="gu.activation === true">
                <img
                  style="width:20px; height:20px;"
                  src="@/assets/images/check.png"
                />
              </td>
              <td v-if="gu.activation === false" />
            </tr>
          </table>

          <div
            v-if="filteredList.length === 0"
            style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;"
          >
            존재하지 않습니다.
          </div>
          <hr />
        </template>
        <!-- 삭제 버튼 누를 시 -->
        <template v-if="this.delete === true">
          <table class="info-table" style="text-align: center">
            <tr>
              <th style="width:10%">번호</th>
              <th style="width:15%">사용자 이름</th>
              <th style="width:30%">이메일</th>
              <th style="width:15%">사용자 부서명</th>
              <th style="width:30%">마지막 로그인</th>
              <th style="width:15%; color:#3498db">삭제</th>
            </tr>
            <tr v-for="(gu, idx) in paginatedData" v-bind:key="gu.no">
              <td>{{ idx + 1 }}</td>
              <td>{{ gu.name }}</td>
              <td>{{ gu.email }}</td>
              <td>{{ gu.dept_name }}</td>
              <td>{{ date_to_str(gu.last_login) }}</td>
              <td>
                <input
                  type="checkbox"
                  style="width:15px; height:15px;"
                  @change="checkbox(gu.no)"
                />
              </td>
            </tr>
          </table>

          <div
            v-if="filteredList.length === 0"
            style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;"
          >
            존재하지 않습니다.
          </div>
          <hr />
        </template>
        <!-- 활성화 버튼 누를 시 -->
        <template v-if="this.active === true">
          <table class="info-table" style="text-align: center">
            <tr>
              <th style="width:10%">번호</th>
              <th style="width:15%">사용자 이름</th>
              <th style="width:30%">이메일</th>
              <th style="width:15%">사용자 부서명</th>
              <th style="width:30%">마지막 로그인</th>
              <th style="width:15%; color:#3498db">활성화 변경</th>
            </tr>
            <tr v-for="(gu, idx) in paginatedData" v-bind:key="gu.no">
              <td>{{ idx + 1 }}</td>
              <td>{{ gu.name }}</td>
              <td>{{ gu.email }}</td>
              <td>{{ gu.dept_name }}</td>
              <td>{{ date_to_str(gu.last_login) }}</td>
              <td>
                <input
                  type="checkbox"
                  style="width:15px; height:15px;"
                  v-model="gu.activation"
                  @change="checkActive(gu.no)"
                />
              </td>
            </tr>
          </table>

          <div
            v-if="filteredList.length === 0"
            style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;"
          >
            존재하지 않습니다.
          </div>
          <hr />
        </template>

          <div class="btn-cover" style="text-align: center">
            <button
              :disabled="pageNum === 0"
              @click="prevPage"
              class="page-btn"
            >
              이전
            </button>
            <span class="page-count" v-if="filteredList.length === 0">
              {{ pageNum + 1 }} / 1
            </span>
            <span class="page-count" v-else>
              {{ pageNum + 1 }} / {{ pageCount }}
            </span>
            <button
              :disabled="pageNum >= pageCount - 1"
              @click="nextPage"
              class="page-btn"
            >
              다음
            </button>
          </div>
      </div>
    </template>

    <template v-if="type === 'A'">
      <h1>
        에이전트 관리
        <code style="color:#3498db; font-size:20px; font-weight:normal">
          "{{ license.policy_title }}"</code
        >
      </h1>
      <br />
      <div class="inner">
        <h4 style="color:gray">보유 에이전트 조회</h4>
        <span id="tab-mac" @click="selmac()" :style="{ background: macstyle }"
          ><a>MAC주소 기준</a></span
        >
        <span id="tab-ip" @click="selip()" :style="{ background: ipstyle }"
          ><a>IP주소 기준</a></span
        >
        <table class="info-table">
          <tr>
            <th>이메일</th>
            <td style="text-align:left">
              <input type="text" style="width:300px" v-model="useremail" />
            </td>
          </tr>
          <tr>
            <th>사용자 이름</th>
            <td style="text-align:left">
              <input
                type="text"
                style="width:300px"
                @input="username = $event.target.value"
              />
            </td>
          </tr>
          <tr>
            <th>사용자 부서</th>
            <td style="text-align:left">
              <input type="text" style="width:300px" v-model="userdept" />
            </td>
          </tr>
          <tr style="border-bottom: 2px solid #ccc">
            <th>마지막 로그인 날짜</th>
            <td style="text-align:left">
              <input
                type="datetime-local"
                style="width:200px"
                v-model="startdate"
              />
              ~
              <input
                type="datetime-local"
                style="width:200px"
                v-model="enddate"
              />
            </td>
          </tr>

          <tr>
            <th>에이전트 명</th>
            <td>
              <input type="text" style="width:300px" v-model="agentname" />
            </td>
          </tr>
          <tr v-if="addrtab">
            <th>MAC Address</th>
            <td><input type="text" style="width:300px" v-model="macaddr" /></td>
          </tr>
          <tr v-if="!addrtab">
            <th>IP Address</th>
            <td><input type="text" style="width:300px" v-model="ipaddr" /></td>
          </tr>
        </table>

        <br /><br />

        <h4 style="color:gray">
          에이전트 목록
          <span v-if="this.delete === false && this.active === false">
            <button style="margin: 10px; float:right" @click="deleteUsr()">
              삭제
            </button>
            <button
              style="margin: 10px; float:right; width: 120px"
              @click="chgActive()"
            >
              활성화 변경
            </button>
            <router-link to="/addagent">
              <button style="width:150px; margin: 10px; float:right">
                에이전트 추가
              </button>
            </router-link>
          </span>
          <!-- 삭제 버튼 누를 시 -->
          <span v-if="this.delete === true">
            <button style="margin: 10px; float:right" @click="deleteUsr()">
              취소
            </button>
            <button
              style="width:100px; margin: 10px; float:right"
              @click="deleteCom()"
            >
              삭제 완료
            </button>
          </span>
          <span v-if="this.active === true">
            <button style="margin: 10px; float:right" @click="chgActive()">
              취소
            </button>
            <button
              style="width:100px; margin: 10px; float:right"
              @click="changeCom()"
            >
              활성화 완료
            </button>
          </span>
        </h4>

        <template v-if="this.delete === false && this.active === false">
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
              <th style="width:10%">활성화 여부</th>
            </tr>
            <tr v-for="(gu, idx) in paginatedData" v-bind:key="gu.no">
              <td>{{ idx + 1 }}</td>
              <td>{{ gu.name }}</td>
              <td>{{ gu.email }}</td>
              <td>{{ gu.dept_name }}</td>

              <td>{{ gu.agent_name }}</td>
              <td>{{ gu.mac_address }}</td>
              <td>{{ gu.ip_address }}</td>

              <td>{{ date_to_str(gu.last_login) }}</td>
              <td v-if="gu.activation === true">
                <img
                  style="width:20px; height:20px;"
                  src="@/assets/images/check.png"
                />
              </td>
              <td v-if="gu.activation === false" />
            </tr>
          </table>

          <div
            v-if="filteredList.length === 0"
            style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;"
          >
            존재하지 않습니다.
          </div>
          <hr />
        </template>

        <template v-if="this.delete === true">
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
              <th style="width:10%; color: #3498db">삭제</th>
            </tr>
            <tr v-for="(gu, idx) in paginatedData" v-bind:key="gu.no">
              <td>{{ idx + 1 }}</td>
              <td>{{ gu.name }}</td>
              <td>{{ gu.email }}</td>
              <td>{{ gu.dept_name }}</td>

              <td>{{ gu.agent_name }}</td>
              <td>{{ gu.mac_address }}</td>
              <td>{{ gu.ip_address }}</td>

              <td>{{ date_to_str(gu.last_login) }}</td>
              <td>
                <input
                  type="checkbox"
                  style="width:15px; height:15px;"
                  @change="checkbox(gu.no)"
                />
              </td>
            </tr>
          </table>

          <div
            v-if="filteredList.length === 0"
            style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;"
          >
            존재하지 않습니다.
          </div>
          <hr />
        </template>

        <template v-if="this.active === true">
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
              <th style="width:10%; color: #3498db">활성화 변경</th>
            </tr>
            <tr v-for="(gu, idx) in paginatedData" v-bind:key="gu.no">
              <td>{{ idx + 1 }}</td>
              <td>{{ gu.name }}</td>
              <td>{{ gu.email }}</td>
              <td>{{ gu.dept_name }}</td>

              <td>{{ gu.agent_name }}</td>
              <td>{{ gu.mac_address }}</td>
              <td>{{ gu.ip_address }}</td>

              <td>{{ date_to_str(gu.last_login) }}</td>
              <td>
                <input
                  type="checkbox"
                  style="width:15px; height:15px;"
                  v-model="gu.activation"
                  @change="checkActive(gu.no)"
                />
              </td>
            </tr>
          </table>

          <div
            v-if="filteredList.length === 0"
            style="text-align:center; width:100%; height: 50px; display:inline-block; padding-top:20px; font-size:15px;"
          >
            존재하지 않습니다.
          </div>
          <hr />
        </template>

        <div class="btn-cover" style="text-align: center">
          <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
            이전
          </button>
          <span class="page-count" v-if="filteredList.length === 0">
            {{ pageNum + 1 }} / 1
          </span>
          <span class="page-count" v-else>
            {{ pageNum + 1 }} / {{ pageCount }}
          </span>
          <button
            :disabled="pageNum >= pageCount - 1"
            @click="nextPage"
            class="page-btn"
          >
            다음
          </button>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      useremail: "",
      username: "",
      userdept: "",
      startdate: "",
      enddate: "",
      delete: false,
      active: false,
      delUsrNos: [],
      activeUsrNos: [],

      //agent 검색 params
      agentname: "",
      macaddr: "",
      ipaddr: "",

      //tab param
      addrtab: true,
      macstyle: "#eaeaea",
      ipstyle: "",

      /////////////////////////////페이징////////////////////
      pageNum: 0,
      pageSize: 10,
      /////////////////////////////페이징////////////////////
    };
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
    },
    filteredList() {
      return Object.values(this.generalusers).filter((post) => {
        return (
          post.email.toLowerCase().includes(this.useremail.toLowerCase()) &&
          post.name.toLowerCase().includes(this.username.toLowerCase()) &&
          post.dept_name.toLowerCase().includes(this.userdept.toLowerCase()) &&
          (this.startdate === ""
            ? true
            : Date.parse(post.last_login) > Date.parse(this.startdate)) &&
          (this.enddate === ""
            ? true
            : Date.parse(post.last_login) < Date.parse(this.enddate))
        );
      });
    },

    /////////////////////////////페이징////////////////////
    pageCount() {
      let listLeng = this.filteredList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.filteredList.slice(start, end);
    },
    /////////////////////////////페이징////////////////////
  },
  methods: {
    date_to_str(format) {
      format = new Date(format);
      var year = format.getFullYear();
      var month = format.getMonth() + 1;
      if (month < 10) month = "0" + month;
      var date = format.getDate();
      if (date < 10) date = "0" + date;
      var hour = format.getHours();
      if (hour < 10) hour = "0" + hour;
      var min = format.getMinutes();
      if (min < 10) min = "0" + min;
      var sec = format.getSeconds();
      if (sec < 10) sec = "0" + sec;

      return (
        year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec
      );
    },
    deleteUsr() {
      this.delete = !this.delete;
      this.delUsrNos = [];
    },
    chgActive() {
      // this.activeUsrNos
      for (var i = 0; i < this.generalusers.length; i++) {
        for (var j = 0; j < this.activeUsrNos.length; j++) {
          if (this.activeUsrNos[j] === this.generalusers[i].no) {
            this.generalusers[i].activation = !this.generalusers[i].activation;
          }
        }
      }

      this.active = !this.active;
      this.activeUsrNos = [];
    },
    checkbox(userno) {
      if (this.delUsrNos.indexOf(userno) == -1) {
        // userno 포함 X
        this.delUsrNos.push(userno);
      } else {
        // userno 포함
        this.delUsrNos.splice(this.delUsrNos.indexOf(userno), 1);
      }
    },
    checkActive(userno) {
      if (this.activeUsrNos.indexOf(userno) == -1) {
        this.activeUsrNos.push(userno);
      } else {
        this.activeUsrNos.splice(this.activeUsrNos.indexOf(userno, 1));
      }
    },
    async deleteCom() {
      if (confirm("정말 다음 사용자를 삭제하시겠습니까?") == true) {
        //확인
        // TODO
        // 1. general_user 내의 delUsrNos를 삭제
        // 2. agent delUsrNos를 포함한 컬럼 삭제
        // 3. license -> current_count --
        var str = "(";
        for (var i = 0; i < this.delUsrNos.length; i++) {
          if (i == this.delUsrNos.length - 1) str += this.delUsrNos[i] + ")";
          else str += this.delUsrNos[i] + ", ";
        }

        if (this.type === "U") {
          await axios
            .get("/api/deluser", {
              params: {
                delUsrs: str,
                licenseNo: this.license.no,
              },
            })
            .then((res) => {
              console.log(res.data);
            });

          this.delete = false;
          alert("사용자를 삭제했습니다.");

          axios
            .get("/api/license/userlist", {
              params: {
                licenseNo: this.license.no,
              },
            })
            .then((res) => {
              console.log(res.data);
              this.$store.commit("licenseStore/SELECT_USR", res.data);
            });
        } else if (this.type === "A") {
          await axios
            .get("/api/delagent", {
              params: {
                delUsrs: str,
                licenseNo: this.license.no,
              },
            })
            .then((res) => {
              console.log(res.data);
            });

          this.delete = false;
          alert("에이전트를 삭제했습니다.");

          axios
            .get("/api/license/agentlist", {
              params: {
                licenseNo: this.license.no,
              },
            })
            .then((res) => {
              console.log(res.data);
              this.$store.commit("licenseStore/SELECT_USR", res.data);
            });
        }
      } else {
        //취소
        return;
      }
    },
    async changeCom() {
      if (confirm("정말 활성화 여부를 변경하시겠습니까?") == true) {
        //확인
        var str = "(";
        for (var i = 0; i < this.activeUsrNos.length; i++) {
          if (i == this.activeUsrNos.length - 1)
            str += this.activeUsrNos[i] + ")";
          else str += this.activeUsrNos[i] + ", ";
        }
        console.log(str);

        await axios
          .get("/api/userActive", {
            params: {
              activeUsrs: str,
              licenseNo: this.license.no,
            },
          })
          .then((res) => {
            if(res.data === 'T') {
              alert('활성화가 변경되었습니다.')
              window.history.go(0);
            } else if (res.data === 'M') {
              alert('최대 사용량을 초과해 활성화를 변경할 수 없습니다.')
              window.history.go(0);
            }
          });

        this.active = false;
      } else {
        //취소
        return;
      }
    },
    selmac() {
      this.addrtab = true;
      this.macstyle = "#eaeaea";
      this.ipstyle = "";
      this.ipaddr = "";
    },
    selip() {
      this.addrtab = false;
      this.macstyle = "";
      this.ipstyle = "#eaeaea";
      this.macaddr = "";
    },
    /////////////////////////////페이징////////////////////
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    resetPageNum() {
      this.pageNum = 0;
    },
    /////////////////////////////페이징////////////////////
    reset() {
      document.getElementById("username").values = "";
      this.useremail = "";
      this.username = "";
      this.userdept = "";
      this.startdate = "";
      this.enddate = "";
      this.agentname = "";
      this.macaddr = "";
      this.ipaddr = "";
    },
  },
  async created() {
    if (this.type === "U") {
      await axios
        .get("/api/license/userlist", {
          params: {
            licenseNo: this.license.no,
          },
        })
        .then((res) => {
          this.$store.commit("licenseStore/SELECT_USR", res.data);
        });
    } else if (this.type === "A") {
      await axios
        .get("/api/license/agentlist", {
          params: {
            licenseNo: this.license.no,
          },
        })
        .then((res) => {
          this.$store.commit("licenseStore/SELECT_USR", res.data);
        });
    }
  },
};
</script>

<style scoped>
code {
  font-family: "Jeju Gothic", sans-serif;
}
h1 {
  font-size: 4.5rem;
  color: #000000d9;
}
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
  font-weight: normal;
  border: none;
  width: 20%;
  text-align: center;
  padding: 15px;
}
.info-table td {
  height: 58px;
  padding: 15px;
  font-weight: normal;
}
.info-table input {
  width: 100px;
}
.inner {
  padding: 10px;
  text-align: left;
  width: 100%;
  display: inline-block;
  border: 1px solid #ccc;
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
