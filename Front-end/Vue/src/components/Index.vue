<template>  
    <div class="flex-center position-ref full-height">
         <div class="content">
            <div class="title m-b-md">
                Firm Internal App
            </div>

            <div class="links">
                <router-link to="/users" @click="setAct('4')">     Users     </router-link> 
                <router-link to="/teams" @click="setAct('5')">     Teams     </router-link>
                <router-link to="/vacations" @click="setAct('6')"> Vacations </router-link>
                <router-link to="/documents" @click="setAct('7')"> Documents </router-link>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        methods: {
            setAct(arg) {
                this.$emit('state', arg);
            }
        }
    }
</script>

<style>
    .full-height {
        height: 100vh;
    }
    .flex-center {
        position: relative;
        top: 20vh;
        display: flex;
        justify-content: center;
    }
    .position-ref {
        position: relative;
    }
    .top-right {
        position: absolute;
        right: 10px;
        top: 18px;
    }
    .content {
        text-align: center;
    }
    .title {
        font-size: 6vw;
    }
    .links > a {
        color: #636b6f;
        padding: 0 25px;
        font-size: 12px;
        font-weight: 600;
        letter-spacing: .1rem;
        text-decoration: none;
        text-transform: uppercase;
    }
    .m-b-md {
        margin-bottom: 30px;
    }
    
    @media screen and (max-width: 550px) {
        .links > a {
            display: none;
            display: block;
            margin-bottom: 25px;
        }
        .title {
            font-size: 9vw;
        }
    }
</style>