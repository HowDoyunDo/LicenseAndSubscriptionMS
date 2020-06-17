<template>
    <div id="product" class="contents">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">iWorks</a></li>
            <li role="presentation"><a href="#">iXeb</a></li>

            <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
                    {{selected}}<span class="caret"></span>
                </a>
                <ul class="dropdown-menu" role="menu">
                    <li><a  v-on:click="select('UI/UX 솔루션')" href="#">UI/UX 솔루션</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </li>
        </ul>
      
    </div>
</template>

<script>
export default {
    data() {
        return {
            selected: '',
        }
    },
    methods: {
        select(sol) {
            this.selected = sol;
        }
    }
}
</script>

<style>
#product { 
    width: 85%;
    border-left: 1px solid lightgray;
    padding: 40px;
    min-height: 426px;    
}
.btn-group {
    margin: 10px;
}
.nav-tabs li.active a {
    background-color: #d9edf7;
}
</style>