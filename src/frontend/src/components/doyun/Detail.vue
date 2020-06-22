<template>
    <div id="detail" class="contents">
        <ul class="nav nav-tabs">

            <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
                    {{ solution }} 
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" role="menu">
                    <li>
                        <a @click="detail('iworks', 'UI/UX 솔루션')">
                            <router-link to='/product'>
                                UI/UX 솔루션
                            </router-link>
                        </a>
                    </li>
                    <li>
                        <a @click="detail('igate', '채널통합 솔루션')">
                            <router-link to='/product'>
                                채널통합 솔루션
                            </router-link>
                        </a>
                    </li>
                    <li>
                        <a @click="detail('xtorm', '컨텐츠 통합관리')">
                            <router-link to='/product'>
                                컨텐츠 통합관리
                            </router-link>
                        </a>
                    </li>
                    <li>
                        <a @click="detail('experdb', '오픈소스 데이터 플랫폼')">
                            <router-link to='/product'>
                                오픈소스 데이터 플랫폼
                            </router-link>
                        </a>
                    </li>
                    <li>
                        <a @click="detail('libeka', '통합도서관시스템')">
                            <router-link to='/product'>
                                통합도서관시스템
                            </router-link>
                        </a>
                    </li>
                    <li>
                        <a @click="detail('myguard', '정보 보안')">
                            <router-link to='/product'>
                                정보 보안
                            </router-link>
                        </a>
                    </li>
                    <li>
                        <a @click="detail('centdoc', '문서중앙화')">
                            <router-link to='/product'>
                                문서중앙화
                            </router-link>
                        </a>
                    </li>
                </ul>
            </li>

            <template v-if="solution === 'UI/UX 솔루션'">
                <li role="presentation" :class="iworks">
                    <a @click="detail('iworks', 'UI/UX 솔루션')">
                        <router-link to='/product'>
                            iWorks
                        </router-link>
                    </a>
                </li>
                <li role="presentation" :class="ixeb">
                    <a @click="detail('ixeb', 'UI/UX 솔루션')">
                        <router-link to='/product'>
                            iXeb
                        </router-link>
                    </a>
                </li>
            </template>

            <template v-if="solution === '채널통합 솔루션'">
                <li role="presentation" :class="igate">
                    <a @click="detail('igate', '채널통합 솔루션')">
                        <router-link to='/product'>
                            iGate
                        </router-link>
                    </a>
                </li>
                <li role="presentation" :class="apim">
                    <a @click="detail('apim', '채널통합 솔루션')">
                        <router-link to='/product'>
                            APIM
                        </router-link>
                    </a>
                </li>
                <li role="presentation" :class="ecross">
                    <a @click="detail('ecross', '채널통합 솔루션')">
                        <router-link to='/product'>
                            eCross
                        </router-link>
                    </a>
                </li>
            </template>

            <template v-if="solution === '컨텐츠 통합관리'">
                <li role="presentation" :class="xtorm">
                    <a @click="detail('xtorm', '컨텐츠 통합관리')">
                        <router-link to='/product'>
                            Xtorm
                        </router-link>
                    </a>
                </li>
            </template>

            <template v-if="solution === '오픈소스 데이터 플랫폼'">
                <li role="presentation" :class="experdb">
                    <a @click="detail('experdb', '오픈소스 데이터 플랫폼')">
                        <router-link to='/product'>
                            eXperDB
                        </router-link>
                    </a>
                </li>
            </template>

            <template v-if="solution === '통합도서관시스템'">
                <li role="presentation" :class="libeka">
                    <a @click="detail('libeka', '통합도서관시스템')">
                        <router-link to='/product'>
                            Libeka
                        </router-link>
                    </a>
                </li>
                <li role="presentation" :class="libekas4">
                    <a @click="detail('libekas4', '통합도서관시스템')">
                        <router-link to='/product'>
                            Libeka S4
                        </router-link>
                    </a>
                </li>
            </template>

            <template v-if="solution === '정보 보안'">
                <li role="presentation" :class="myguard">
                    <a @click="detail('myguard', '정보 보안')">
                        <router-link to='/product'>
                            MyGuard
                        </router-link>
                    </a>
                </li>
            </template>

            <template v-if="solution === '문서중앙화'">
                <li role="presentation" :class="centdoc">
                    <a @click="detail('centdoc', '문서중앙화')">
                        <router-link to='/product'>
                            문서중앙화
                        </router-link>
                    </a>
                </li>
            </template>
        </ul>

        <!-- 구독 정책 내용 삽입 -->
        <slot></slot>

        <img :src="imgsrc" />
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
        }
    },
    methods: {
        detail(pdt, sol) {
            this.$store.commit('productStore/CHANGE_PDT', pdt);
            this.$store.commit('productStore/CHANGE_SEL', sol);
            this.$store.commit('productStore/ACTIVE_TAB', pdt);
            // solution store와 일치하는 구독정책 table에서 검색
            axios.get('/api/policylist', {
                params: {
                    sol : this.solution
                }
            }).then(res => { 
                this.$store.commit('productStore/CHANGE_UPLCY', res.data);
                console.log(this.$store.state.productStore.uPolicies);
            })
        }
    },
    computed: {
        solution: function() {
            return this.$store.state.productStore.solution;
        },
        product: function() {
            return this.$store.state.productStore.product;
        },
        imgsrc: function() {
            return require('@/assets/details/'+ this.$store.state.productStore.product +'.png');
        },

        iworks: function() {
            return this.$store.state.productStore.iworks;
        },
        ixeb: function() {
            return this.$store.state.productStore.ixeb;
        },
        igate: function() {
            return this.$store.state.productStore.igate;
        },
        apim: function() {
            return this.$store.state.productStore.apim;
        },
        ecross: function() {
            return this.$store.state.productStore.ecross;
        },
        xtorm: function() {
            return this.$store.state.productStore.xtorm;
        },
        experdb: function() {
            return this.$store.state.productStore.experdb;
        },
        libeka: function() {
            return this.$store.state.productStore.libeka;
        },
        libekas4: function() {
            return this.$store.state.productStore.libekas4;
        },
        myguard: function() {
            return this.$store.state.productStore.myguard;
        },
        centdoc: function() {
            return this.$store.state.productStore.centdoc;
        },
    }
}
</script>

<style>
#product { 
    width: 85%;
    border-left: 1px solid lightgray;
    padding: 40px;
    min-height: 426px;
    text-align: center;
}
.btn-group {
    margin: 10px;
}
.nav-tabs li.active a {
    background-color: #d9edf7;
}
</style>