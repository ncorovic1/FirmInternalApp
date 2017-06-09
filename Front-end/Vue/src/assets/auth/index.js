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
                                    this.user.authenticated = true;
                                    var us = this.setUser(context, creds.username);
                                }
                                else {
                                    this.user.authenticated = false;
                                }
                            }, error => {
                                this.user.authenticated = false;
                                context.error = "Credentials you've provided don't match our records!";
                            });
    },

    // Logging out
    logout() {
        this.user.authenticated = false;
        this.user.role          = '';
        this.user.username      = '';
        localStorage.removeItem('Authorization');
        localStorage.removeItem('Role');
        localStorage.removeItem('Username');
    },
    
    // Check if user valid logged with JWT 
    checkAuth() {
        var jwt = localStorage.getItem('Authorization');
        if(jwt != null) {
            alert('checked');
            this.user.authenticated = true;
            this.user.role          = localStorage.getItem('Role');
            this.user.username      = localStorage.getItem('Username');
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
                                localStorage.setItem('Role',     this.user.role);
                                localStorage.setItem('Username', this.user.username);
                                window.location.href="/users";
                            },
                            error => {
                                alert('Error with user fetch: ' + JSON.stringify(error));
                            });
    }
}