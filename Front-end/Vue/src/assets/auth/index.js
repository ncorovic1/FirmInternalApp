import {router} from '../../main'

// URL and endpoint constants
const API_URL = 'http://localhost:8085'
const LOGIN_URL = API_URL + '/login'
const USERS_URL = API_URL + '/users'
const USER_BY_USERNAME_URL = USERS_URL + '/byusername/'

export default {

    // User object will let us check authentication status
    user: {
        authenticated: false,
        username: '',
        fullname: '',
        role: ''
    },

    // Send a request to the login URL and save the returned JWT
    login(context, creds, redirect) {
        context.$http.post(LOGIN_URL, 
                           JSON.stringify(creds), 
                           {
                               headers: {
                                   'Content-Type': 'text/plain'
                               }
                           })
                           .then(response => {
                                if( response.headers.get('Authorization') != null ) {
                                    localStorage.setItem('Authorization', response.headers.get('Authorization'));
                                    localStorage.setItem('ActivePage', '4');
                                    this.user.authenticated = true;
                                    var us = this.setUser(context, creds.username);
                                }
                                else {
                                    this.user.authenticated = false;
                                }
                            }, error => {
                                this.user.authenticated = false;
                                context.errorLogin = "Credentials you've provided don't match our records!";
                            });
    },

    // Logging out
    logout() {
        this.user.authenticated = false;
        this.user.role          = '';
        this.user.username      = '';
        this.user.fullname      = '';
        localStorage.removeItem('Authorization');
        localStorage.removeItem('Role');
        localStorage.removeItem('Username');
        localStorage.removeItem('Fullname');
    },
    
    // Check if user valid logged with JWT 
    checkAuth() {
        var jwt = localStorage.getItem('Authorization');
        if(jwt != null) {
            this.user.authenticated = true;
            this.user.role          = localStorage.getItem('Role');
            this.user.username      = localStorage.getItem('Username');
            this.user.fullname      = localStorage.getItem('Fullname');
            return true;
        }
        else {
            this.logout();
            return false;      
        }
    },

    // JWT Header
    getAuthHeader() {
        return {
            'Authorization': localStorage.getItem('Authorization')
        }
    },
    
    // Get user by username
    setUser(context, username) {
        context.$http.get(USER_BY_USERNAME_URL + username)
            .then(response => {
                this.user.role     = response.body.role;
                this.user.username = response.body.username;
                this.user.fullname = response.body.firstName + ' ' + response.body.lastName;
                localStorage.setItem('Role',     this.user.role);
                localStorage.setItem('Username', this.user.username);
                localStorage.setItem('Fullname', this.user.fullname);
                window.location.href="/";
            },
            error => {
                alert('Error with user fetch: ' + JSON.stringify(error));
            });
    }
}