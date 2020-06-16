<template>
    <div id="navigator">
        <h4>제품 조회(로그인 전)</h4>
        <ul>
            <li><router-link to='/'>제품 조회</router-link></li>
            <br>
        </ul>

        <h4>제품 조회 및<br>구독 신청</h4>
        <ul>
            <li><router-link to="/category/login">제품 조회</router-link></li>
            <br>
        </ul>

        <h4>라이센스 관리</h4>
        <ul>
            <li><router-link to='/license/list'>라이센스 관리</router-link></li>
            <br>
        </ul>
        
        <h4 v-on:click="toggleAnalysis">통계 분석</h4>
        <ul>
            <template v-if='showAnalysis'>
                <li><router-link to='/analysis/sales'>매출 분석</router-link></li>
                <li><router-link to='/analysis/product'>상품 분석</router-link></li>
                <br>
            </template>
        </ul>

        <h4>배치 서비스</h4>
        <ul>
            <li><router-link to='/batch'>배치 서비스</router-link></li>
            <br>
        </ul>
    </div>
</template>

<script>
export default {
    data() {
        return {
            showAnalysis: false
        }
    },
    methods: {
        toggleAnalysis() {
            this.showAnalysis = !this.showAnalysis;
        }
    }
}
</script>

<style>
    #navigator {
        width: 15%;
        align-self: left;
        padding: 5px;
        font-size: 15px;
        position: static;  
        
    }
    #navigator ul {
        padding: 0 0 0 10px;
        list-style: none;
        border-bottom: 1px solid lightgray;
    }
</style>