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
                        <li @click="setAct('1')" v-bind:class="[act == '1' ? 'active' : '']">
                            <router-link to="/">          Home </router-link></li>
                        <li @click="setAct('4')" v-bind:class="[act == '4' ? 'active' : '']">
                            <router-link to="/users">     Users     </router-link> </li>
                        <li @click="setAct('5')" v-bind:class="[act == '5' ? 'active' : '']">
                            <router-link to="/teams">     Teams     </router-link> </li>
                        <li @click="setAct('6')" v-bind:class="[act == '6' ? 'active' : '']">
                            <router-link to="/vacations"> Vacations </router-link> </li>
                        <li @click="setAct('7')" v-bind:class="[act == '7' ? 'active' : '']">
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
        <router-view state="setAct($event)"></router-view>
    </div>
</template>

<script>
    import auth from './assets/auth'

    export default {
        data() {
            return {
                act: '4',
                user: auth.user
            }
        },
        methods: {
            logout() {
                auth.logout();
                window.location.href="/login";
            },
            setAct(arg) {
                this.act = arg;
                localStorage.setItem('ActivePage', arg);
            },
            setState() {
                var url = window.location.href;

                if (url == 'http://localhost:8080' || url == 'http://localhost:8080/')
                    route = 'main';
                else {
                    var split = url.split('/');
                    var route = '';
                    route = (split[split.length-1] == '') ? split[split.length-2] : split[split.length-1];
                }
                switch(route) {
                    case 'main':
                        this.act = '1';
                        break;
                    case 'login':
                        this.act = '0';
                        break;
                    case 'users':
                        this.act = '4';
                        break;
                    case 'teams':
                        this.act = '5';
                        break;
                    case 'vacations':
                        this.act = '6';
                        break;
                    case 'documents':
                        this.act = '7';
                        break;
                    case 'profile':
                        this.act = '50';
                        break;
                    case '404_not_found':
                        this.act = '100';
                        break;
                    default:
                        this.act = '4';
                        window.location.href="/404_not_found"
                }
                this.setAuthorization(route);
            },
            setAuthorization(path) {
                var role = localStorage.getItem('Role');
                if (role == 'ADMIN') 
                    return;
                else if (role == null && path != 'login')
                    window.location.href="/login";
                else if (role == 'EMPLOYEE' && path == 'vacations') {
                    window.location.href="/profile";
                }
            }
        },
        watch: {
            '$route' (to, from) {
                this.setState();
            }
        },
        created() {
            this.setState();
        }
    }
</script>

<style>
    .redL {
        color: red;    
    }
</style>
