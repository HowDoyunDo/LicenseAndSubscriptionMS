<template>
  <div id="products">

    <ul>
      <li>
        <div class="panel panel-info" @click="detail('iworks', 'UI/UX 솔루션')">
          <router-link to='/product'>
            <div class="panel-heading">
              iWorks
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body1">
              <p>
                <!-- <img class="solutions" src="../../assets/solutions/uxui.png" align="right"/> -->
                <br><br>
                UX/UI 솔루션
                <br>
                ―
              </p>
              <br>
              사용자 패턴에 맞는 UI구성. 다양한 업무의 효율적 수용<br>
              데이터 연동 및 다양한 사용자 편의 기능
            </div>
          </router-link>
        </div>  
      </li>
      <li>
        <div class="panel panel-info" @click="detail('ixeb', 'UI/UX 솔루션')">
          <router-link to='/product'>
            <div class="panel-heading">
              iXeb
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body2">
              <p>
                <img class="solutions" src="../../assets/solutions/uxui.png" align="right"/>
                <br><br>
                UX/UI 솔루션
                <br>
                ―
              </p>
              <br>
              현재까지 없었던 새로운 방식의<br>
              Web UI어플리케이션 개발 플랫폼
            </div>
          </router-link>
        </div>  
      </li>
      <li>
        <div class="panel panel-info" @click="detail('igate', '채널통합 솔루션')">
          <router-link to='/product'>
            <div class="panel-heading">
              iGate
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body3">
              <p>
                <img class="solutions" src="../../assets/solutions/channel.png" align="right"/>
                <br><br>
                채널통합 솔루션
                <br>
                ―
              </p>
              <br>
              ESB Interface Hub 기반으로 다양한 내/외부 채널<br>
              통합의 전사 채널 통합 서버 프레임워크
            </div>
          </router-link>
        </div>  
      </li>
      <li>
        <div class="panel panel-info" @click="detail('apim', '채널통합 솔루션')">
          <router-link to='/product'>
            <div class="panel-heading">
              APIM
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body4">
              <p>
                <img class="solutions" src="../../assets/solutions/channel.png" align="right"/>
                <br><br>
                채널통합 솔루션
                <br>
                ―
              </p>
              <br>
              다수의 개발자들이 쉽게 참여 할 수 있는 Open API Platform,<br>
              기업의 API 관리를 위한 솔루션
            </div>
          </router-link>
        </div>  
      </li>
      <li>
        <div class="panel panel-info" @click="detail('ecross', '채널통합 솔루션')">
          <router-link to='/product'>
            <div class="panel-heading">
              eCross
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body5">
              <p>
                <img class="solutions" src="../../assets/solutions/esb.png" align="right"/>
                <br><br>
                EAI/ESB 연계 솔루션
                <br>
                ―
              </p>
              <br>
              GUI 설정기반의 내/외부 연계 솔루션으로<br>
              이기종 시스템간 데이터와 어플리케이션 통합 솔루션
            </div>
          </router-link>
        </div>  
      </li>
      <li>
        <div class="panel panel-info" @click="detail('xtorm', '컨텐츠 통합관리')">
          <router-link to='/product'>
            <div class="panel-heading">
              Xtorm
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body6">
              <p>
                <img class="solutions" src="../../assets/solutions/channel.png" align="right"/>
                <br><br>
                컨텐츠 통합관리
                <br>
                ―
              </p>
              <br>
              기업에서 발생되는 이미지, 도면, 전자문서, 기록물 등의<br>
              비정형 데이터를 효율적으로 관리
            </div>
          </router-link>
        </div>  
      </li>
      <li>
        <div class="panel panel-info" @click="detail('experdb', '오픈소스 데이터 플랫폼')">
          <router-link to='/product'>
            <div class="panel-heading">
              eXperDB
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body7">
              <p>
                <img class="solutions" src="../../assets/solutions/db.png" align="right"/>
                <br><br>
                오픈소스 데이터 플랫폼
                <br>
                ―
              </p>
              <br>
              오픈소스 DBMS PostgreSQL과 다양한 운영관리 기능을<br>
              플랫폼으로 제공. 안정적이고 편리한 데이터 관리 지원
            </div>
          </router-link>
        </div>  
      </li>
      <li>
        <div class="panel panel-info" @click="detail('libeka', '통합도서관시스템')">
          <router-link to='/product'>
            <div class="panel-heading">
              Libeka
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body8">
              <p>
                <img class="solutions" src="../../assets/solutions/book.png" align="right"/>
                <br><br>
                통합도서관리 시스템
                <br>
                ―
              </p>
              <br>
              다양한 관종별 도서관에 적용 가능한<br>
              100% 웹 환경의 통합 전자도서관 솔루션입니다.
            </div>
          </router-link>
        </div>  
      </li>
      <li>
        <div class="panel panel-info" @click="detail('libekas4', '통합도서관시스템')">
          <router-link to='/product'>
            <div class="panel-heading">
              Libeka S4
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body9">
              <p>
                <img class="solutions" src="../../assets/solutions/book.png" align="right"/>
                <br><br>
                통합도서관리 시스템
                <br>
                ―
              </p>
              <br>
              소규모 도서관의 환경에 최적화되어 비 전문 사서도 쉽게<br>
              업무를 처리할 수 있는 작은 도서관용 도서관리시스템입니다.
            </div>
          </router-link> 
        </div> 
      </li>
      <li>
        <div class="panel panel-info" @click="detail('myguard', '정보 보안')">
          <router-link to='/product'>
            <div class="panel-heading">
              MyGuard
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body10">
              <p>
                <img class="solutions" src="../../assets/solutions/security.png" align="right"/>
                <br><br>
                정보 보안
                <br>
                ―
              </p>
              <br>
              산재해있는 제반문서를 기업이 손쉽게 축적/관리하고,<br>
              임의로 유통할 수 없도록 환경제반을 통제
            </div>
          </router-link>
        </div>  
      </li>
      <li>
        <div class="panel panel-info" @click="detail('centdoc', '문서중앙화')">
          <router-link to='/product'>
            <div class="panel-heading">
              문서중앙화
              <img src='../../assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body11">
              <p>
                <img class="solutions" src="../../assets/solutions/centdoc.png" align="right"/>
                <br><br>
                문서중앙화
                <br>
                ―
              </p>
              <br>
              ECM기반의 문서중앙화 시스템 전사 표준분류체계 및<br>
              관리체계 정립 Endpoint 저장통제를 통해 해결
            </div>
          </router-link>
        </div>  
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
    methods: {
        detail(pdt, sol) {
            this.$store.commit('productStore/CHANGE_PDT', pdt);
            this.$store.commit('productStore/CHANGE_SEL', sol);
            this.$store.commit('productStore/ACTIVE_TAB', pdt);

            // solution store와 일치하는 User구독정책 table에서 검색
            axios.get('/api/userpolicylist', {
                params: {
                    sol : this.solution
                }
            }).then(res => { 
                this.$store.commit('productStore/CHANGE_UPLCY', res.data);
                console.log(this.$store.state.productStore.uPolicies);
            });

            // solution store와 일치하는 Agent구독정책 table에서 검색
            axios.get('/api/agentpolicylist', {
                params: {
                    sol : this.solution
                }
            }).then(res => { 
                this.$store.commit('productStore/CHANGE_APLCY', res.data);
                console.log(this.$store.state.productStore.aPolicies);
            });
        }
    },
    computed: {
        solution: function() {
            return this.$store.state.productStore.solution;
        },
        product: function() {
            return this.$store.state.productStore.product;
        },
    }
}
</script>

<style>
@import '../../assets/css/products.css';
</style>