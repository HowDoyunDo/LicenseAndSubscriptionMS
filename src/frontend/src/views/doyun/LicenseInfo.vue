<template>
    <div id="licenseinfo" class="contents">
        <template v-if="type === 'U'">
            <h2>사용자 관리 <code style="color:#3498db; font-size:20px;"> "{{ license.policy_title }}"</code></h2>
            <table class="info-table">
                <tr>
                    <th>이메일</th>
                    <td>...</td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td>...</td>
                </tr>
            </table>
        </template>
        <template v-if="type === 'A'">
            <h2>에이전트 관리</h2>
            <table class="info-table">
                <tr>
                    <th>...</th>
                    <td>...</td>
                </tr>
            </table>
        </template>
    </div>
</template>

<script>
export default {
    computed: {
        idx: function() {
            return this.$route.params.licenseno;
        },
        type: function() {
            return this.$route.params.licensetype;
        },
        license: function() {
            return this.$store.state.productStore.solution;
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
        text-align: center;
    }
    .info-table input {
        width: 100px;
    }
</style>