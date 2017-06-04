// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue         from 'vue';
import VueRouter   from 'vue-router';
import VueResource from 'vue-resource';
import App         from './App';

import Message from './components/Message';
import Hello   from './components/Hello';
import Login   from './components/Login';
import Users   from './components/user/Table';
import Teams   from './components/team/Table';

require('./assets/css/bootstrap.min.css');
require('./assets/css/bootstrap-theme.min.css');
require('./assets/js/jquery.min.js');
require('./assets/js/bootstrap.min.js');

Vue.config.productionTip = false;
Vue.component('app-message', Message);

//Vue.use for adding core functionality plugins and now it can be used on Vue instance
Vue.use(VueRouter);
Vue.use(VueResource);
Vue.http.options.emulateJSON = true;
Vue.http.interceptors.push((request, next) => {
//    request.headers.set('Access-Control-Allow-Origin', '*');
//    request.headers.set('Access-Control-Allow-Methods', 'POST,PUT, GET, OPTIONS, DELETE');
//    request.headers.set('Access-Control-Max-Age', '3600');
//    request.headers.set('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept,AUTH-TOKEN');
//    request.headers.set('Access-Control-Expose-Headers', 'Authorization');
//    request.headers.set('Authorization', 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaW5vLEFETUlOIiwiZXhwIjoxNDk3MTM0MDA5fQ.W6_iPBSj9VzTOtaLIhyZHoIdEGpCVyTIKSLE1xqOpAgXNBoQwDofTLYhNvtybDkXXgzNh_10Bv1eGMusWcwm6A');
    //console.log(JSON.stringify(request));
    console.log(request);
    next(response => {
    });
});

//Vue.config(function ($httpProvider) {
//  $httpProvider.defaults.headers.common = {};
//  $httpProvider.defaults.headers.post = {};
//  $httpProvider.defaults.headers.put = {};
//  $httpProvider.defaults.headers.patch = {};
//});

//routes is naming conveinance so it can be simply added to VueRouter
const routes = [
    { path:'/hello/:name?', component: Hello },
    { path:'/login',        component: Login },
    { path:'/users',        component: Users },
    { path:'/teams',        component: Teams },
    { path:'/',             component: Message }
];

const router = new VueRouter({
    //instead of  -> routes: routes <-, because second one has the same name as "routes"
    routes,
    //If back end is not hosted on the same server, it is ok to redirect all request to front end routes directly
    mode: 'history'
});

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    template: '<App/>',
    components: { App }
})
