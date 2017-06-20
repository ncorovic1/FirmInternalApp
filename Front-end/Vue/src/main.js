// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue         from 'vue';
import VueRouter   from 'vue-router';
import VueResource from 'vue-resource';
import VeeValidate from 'vee-validate';
import App         from './App';

import Message   from './components/Message';
import Hello     from './components/Hello';
import Login     from './components/Login';
import Users     from './components/user/Table';
import Teams     from './components/team/Table';
import Vacations from './components/vacation/Table';
import Documents from './components/document/Table';
import Render    from './components/document/Render';
import Profile   from './components/profile/Preview';
import NotFound  from './components/error/NotFound';
import MainPage  from './components/Index';
import Auth      from './assets/auth'

require('./assets/css/bootstrap.min.css');
require('./assets/css/bootstrap-theme.min.css');
require('./assets/js/jquery.min.js');
require('./assets/js/bootstrap.min.js');

Vue.config.productionTip = false;
//If page is refreshed, check for Auth information
Auth.checkAuth();

//Vue.use for adding core functionality plugins and now it can be used on Vue instance
Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(VeeValidate);
Vue.http.options.emulateJSON = true;
Vue.http.interceptors.push((request, next) => {
    if(!request.url.includes("login") && !request.url.includes("sendemail") && !request.url.includes("resetpassword")) {
        request.headers.set('Content-Type', 'application/json');
        request.headers.set('Accept', 'application/json');
        request.headers.set('Authorization', localStorage.getItem('Authorization'));
    }
    next(response => {
        console.log(response);
    });
});

//routes is naming conveinance so it can be simply added to VueRouter
const routes = [
    { path:'/hello/:name?',  component: Hello },
    { path:'/login',         component: Login },
    { path:'/',              component: MainPage },
    { path:'/users',         component: Users },
    { path:'/teams',         component: Teams },
    { path:'/vacations',     component: Vacations },
    { path:'/documents',     component: Documents },
    { path:'/document/:id',  component: Render },
    { path:'/profile',       component: Profile },
    { path:'/404_not_found', component: NotFound },
    { path:'/Message',       component: Message }
];

const router = new VueRouter({
    //instead of  -> routes: routes <-, because second one has the same name as "routes"
    routes,
    //If back end is not hosted on the same server, it is ok to redirect all request to front end routes directly
    mode: 'history'
});

//router.beforeEach((to, from, next) => {
//    if(to.path == '/teams')
//        next(false);
//})

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    template: '<App/>',
    components: { App }
})

const eventBus = new Vue()
