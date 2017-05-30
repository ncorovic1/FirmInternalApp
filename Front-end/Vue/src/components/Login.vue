<template>
    <div class="back-img">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title">Sign In</div>
                    <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div>
                </div>     

                <div style="padding-top:30px" class="panel-body" >
                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>  
                    <form id="loginform" class="form-horizontal" role="form">
                        
                        <div class="alert alert-danger" v-if="error">
                            <p>{{ error }}</p>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="login-username" type="text" class="form-control" v-model="creds.username" value="" placeholder="username">                                        
                        </div>

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="login-password" type="password" class="form-control" v-model="creds.password" placeholder="password">
                        </div>

                        <div style="margin-top:10px" class="form-group">
                            <div class="col-sm-12 controls">
                              <a id="btn-login" @click="loginRequest" class="btn btn-success">Login  </a>
                            </div>
                        </div>
                        <p> {{ creds.username }} </p>
                        <p> {{ creds.password }} </p>
                    </form>     
                </div>                     
            </div>  
        </div>
    </div>
</template>

<script>
    import auth from '../assets/auth'
    export default {
      name: 'login',
      data () {
        return {
            creds: {
                username: '',
                password: ''
            },
            error: ''
        }
      },
      methods: {
        loginRequest(event) {    
            this.$http.post('http://localhost:8085/login', 
                            JSON.stringify(this.creds), 
                            {
                                headers: {
                                    'Content-Type': 'text/plain'
                                    }
                            })
                            .then(response => {
                                console.log(response);
                                console.log(response.status);
                                console.log(response.headers);
                                console.log(response.headers.get('Authorization'));
                            }, error => {
                                console.log(error)
                            });
            /*auth.login(this, JSON.stringify({username: this.creds.username, password: this.creds.password}), 'hello');*/
        }
      }
    }
</script>

<style scoped>    
    .back-img {    
        /* Full height */
        height: 100%; 

        /* Center and scale the image nicely */
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
    }
</style>
