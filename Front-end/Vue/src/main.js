// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue         from 'vue';
import VueRouter   from 'vue-router';
import VueResource from 'vue-resource';
import App         from './App';

import Message from './components/Message';
import Hello   from './components/Hello';
import Login   from './components/Login';

require('./assets/css/bootstrap.min.css');
require('./assets/css/bootstrap-theme.min.css');

Vue.config.productionTip = false;
Vue.component('app-message', Message);

//Vue.use for adding core functionality plugins and now it can be used on Vue instance
Vue.use(VueRouter);
Vue.use(VueResource);
Vue.http.options.emulateJSON = true;
Vue.http.interceptors.push((request, next) => {
    next(response => {
        //response.headers['Access-Control-Expose-Headers'] = 'Authentification'
    });
});

//routes is naming conveinance so it can be simply added to VueRouter
const routes = [
    { path:'/hello/:name?', component: Hello },
    { path:'/login',        component: Login },
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
