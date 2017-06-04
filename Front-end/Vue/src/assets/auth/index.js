import {router} from '../../main'

// URL and endpoint constants
const API_URL = 'http://localhost:8085/'
const LOGIN_URL = API_URL + 'login/'
const USERS_URL = API_URL + 'users/'

export default {

  // User object will let us check authentication status
  user: {
    authenticated: false
  },

  // Send a request to the login URL and save the returned JWT
  login(context, creds, redirect) {  
      context.$http.post('http://localhost:8085/login', 
                        creds, 
                        {
                            headers: {
                                'Content-Type': 'text/plain'
                                }
                        })
                        .then(response => {
                            if( response.headers.get('Authorization') != null) {
                                localStorage.setItem('Authorization', response.headers.get('Authorization'));
                                this.user.authenticated = true;
                                window.location.href="/users";
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
    localStorage.removeItem('Authorization')
    this.user.authenticated = false
  },
    
  // Check if user valid logged with JWT 
  checkAuth() {
    var jwt = localStorage.getItem('Authorization')
    if(jwt) {
      this.user.authenticated = true
    }
    else {
      this.user.authenticated = false      
    }
  },

  // JWT Header
  getAuthHeader() {
    return {
      'Authorization': localStorage.getItem('Authorization')
    }
  },
    
  // Get user by username
  getUser(username) {
//    this.$http.get(USERS_URL, 
//                   {
//                        headers: {
//                            getAuthHeader()
//                        }
//                    })
//                    .then(response => {
//                        console.log(response);
//                    }, error => {
//                        console.log(error)
//                    });
  },
 
  // Signing up/Adding new user
  signup(context, creds, redirect) {
    //
  }
}