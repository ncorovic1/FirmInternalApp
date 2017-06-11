<template>
    <div class="container">
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
            auth.login(this, this.creds, 'hello');
        }
      }
    }
</script>

<style scoped>    

</style>
