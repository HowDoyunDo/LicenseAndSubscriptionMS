<template>
    <div id="header">
        <router-link to="/"><img src="../../public/inzent_logo.png" style="width:155px; height:25px; margin-left:16px"/></router-link>
        
        <ul>
            <li>SIGN IN</li> 
            <li> | </li>
            <li>SIGN UP</li>
        </ul>
    </div>
</template>

<script>
export default {
    
}
</script>

<style>
    div #header {
        height: 100px;
        background-color: #103366;
        /* background-color: #b0544f; */
        margin:0
    }
    div #header h4 {
        text-align: right;
        color: white;
        vertical-align: bottom;
        margin: 0 10px 10px 0; 
    }

    div #header img {
        display: inline-block;
        margin:  35px 0 0 10px;
    }

    div #header ul { list-style:none; color: white; vertical-align: bottom; margin: 0 10px 10px 0; }
    div #header li { float: right; color: white; margin-right: 10px; }
</style>