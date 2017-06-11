<template>
    <div id="app">
        <nav class="navbar navbar-default" v-show="user.authenticated">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">FirmInternalApp</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <!--
                        <li @click="act = '1'" v-bind:class="[act == '1' ? 'active' : '']">
                            <router-link to="/">          Message </router-link></li>
                        <li @click="act = '2'" v-bind:class="[act == '2' ? 'active' : '']">
                            <router-link to="/login">     Login     </router-link> </li>
                        <li @click="act = '3'" v-bind:class="[act == '3' ? 'active' : '']">
                            <router-link to="/hello">     Hello     </router-link> </li>
                        -->
                        <li @click="act = '4'" v-bind:class="[act == '4' ? 'active' : '']">
                            <router-link to="/users">     Users     </router-link> </li>
                        <li @click="act = '5'" v-bind:class="[act == '5' ? 'active' : '']">
                            <router-link to="/teams">     Teams     </router-link> </li>
                        <li @click="act = '6'" v-bind:class="[act == '6' ? 'active' : '']">
                            <router-link to="/vacations"> Vacations </router-link> </li>
                        <li @click="act = '7'" v-bind:class="[act == '7' ? 'active' : '']">
                            <router-link to="/documents"> Documents </router-link> </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> {{ user.username }} <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><router-link to="/profile"> Profile </router-link></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#" @click="logout">Log out</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <!-- Router Link so index.html is not rendered every time and app state is not lost -->
        <!-- use :to if parametar is variable and not full route -->
        <router-view></router-view>
    </div>
</template>

<script>
    import auth from './assets/auth'

    export default {
        data() {
            return {
                'act': '4',
                user: auth.user
            }
        },
        methods: {
            logout() {
                auth.logout();
                window.location.href="/login";
            }
        }
    }
</script>

<style>
</style>
