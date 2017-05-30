<template>
    <!-- $route is available from VueRouter imported in app -->
    <h1> hello <span v-show="$route.params.name ? true : false"> {{ $route.params.name }} </span> </h1>
</template>

<script>
    export default {
        //created method will execute once, because Vue doesnt destory component once it is created
        //same route with different argument will be created only once
        //this is a simple way of handling code when parameters are changed
        watch: {
            '$route' (to, from) {
                alert( 'Hello' + (to.params.name ? to.params.name : '') );
            }
        },
        created() {
            alert( 'Hello' + (this.$route.params.name ? this.$route.params.name : '') );
        }
    }
    // this.$router.push('Home') 
</script>