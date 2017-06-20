<template>
    <div class="container">
        <div id="loginbox" v-if="!showResetMail" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2" >                    
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">Sign In</div>
                    <a style="float:right; font-size: 90%; position: relative; top:-10px" @click="showResetMail = true">
                        Forgot password</a>
                </div>     

                <div style="padding-top:30px" class="panel-body" >
                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>  
                    <form id="loginform" class="form-horizontal" role="form">
                        
                        <div class="alert alert-danger" v-if="errorLogin">
                            <p>{{ errorLogin }}</p>
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
        
        <div class="row" v-else style="margin-top:50px;">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="text-center">
                            <i class="fa fa-lock fa-4x"></i>
                            <h2 class="text-center">Forgot Password?</h2>
                            <p>You can reset your password here.</p>
                            
                            <div class="alert alert-danger" v-if="err.status">
                                <p>{{ err.value }}</p>
                            </div>

                            <div class="panel-body" v-if="!showResetPass">
                                <form id="register-form" role="form" autocomplete="off" class="form" method="post">
                                    <div class="form-group">
                                        <div v-bind:class="['input-group', errors.has('email') ? 'has-error' : 'space']">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                                            <input id="email" v-model="request.email" placeholder="email address" class="form-control" type="email" name="email" v-validate="'required|email'">
                                        </div>
                                        <span class="help-block" v-show="errors.has('email') != ''">
                                            <strong>{{ errors.first('email') }}</strong>
                                        </span>
                                    </div>
                                    <div class="form-group">
                                        <button name="recover-mail" class="btn btn-lg btn-primary btn-block" @click="requestURK" type="button">Send confirmation code</button>
                                    </div>
                                    <input type="hidden" class="hide" name="token" id="token" value=""> 
                                    <h5 @click="showResetPass = true"><a>Already have confirmation code</a></h5>
                                </form>
                            </div>

                            <div class="panel-body" v-else>
                                <form>
                                    <div class="form-group">
                                    
                                        <div v-bind:class="['input-group', errors.has('urk') ? 'has-error' : 'space']">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-wrench"></i></span>
                                            <input v-model="request.urk" placeholder="confirmation code" class="form-control" v-validate="'required|alpha_num|min:10|max:10'" type="text" name="urk">
                                        </div>
                                        <span class="help-block" v-show="errors.has('urk') != ''">
                                            <strong>{{ errors.first('urk') }}</strong>
                                        </span>
                                        
                                        <div v-bind:class="['input-group', errors.has('pw') ? 'has-error' : 'space']">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock color-blue"></i></span>
                                            <input v-model="request.pw" placeholder="new password" class="form-control"  v-validate="'required|min:6'" type="password" name="pw">
                                        </div>
                                        <span class="help-block" v-show="errors.has('pw') != ''">
                                            <strong>{{ errors.first('pw') }}</strong>
                                        </span>
                                        
                                        <div v-bind:class="['input-group', errors.has('pwc') ? 'has-error' : 'space']">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                            <input type="password" class="form-control" v-model="request.pwc" placeholder="confirm password" v-validate="'required|confirmed:pw|min:6'" name="pwc">
                                        </div>
                                        <span class="help-block" v-show="errors.has('pwc') != ''">
                                            <strong>{{ errors.first('pwc') }}</strong>
                                        </span>
                                        
                                    </div>
                                    <div class="form-group">
                                        <button name="recover-submit" class="btn btn-lg btn-primary btn-block" @click="requestReset" type="button">Reset Password</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
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
            request: {
                email: '',
                urk: '',
                pw:  '',
                pwc: ''
            },
            errorLogin: '',
            showResetMail: false,
            showResetPass: false,
            err: {
                status: false,
                value: 'Empty fields are not allowed!'
            }
        }
      },
      methods: {
        loginRequest(event) {    
            auth.login(this, this.creds, 'hello');
        },
        requestURK() {
            if( this.errors.errors == '' ) {
                if( this.request.email == '' ) {
                    this.err.status = true;
                    return;
                }
                else {
                    this.$http.post('http://localhost:8085/users/sendemail/' + this.request.email)
                        .then(response => {
                            this.showResetPass = true;
                        }, error => {
                            console.log(error);
                        });
                }
            }
        },
        requestReset() {
            if( this.errors.errors == '' ) {
                if( this.request.urk == '' || this.request.pw == '' || this.request.pwc == '' ) {
                    this.err.status = true;
                    return;
                }
                else {
                    var path = this.request.urk + '/' + this.request.pw + '/' + this.request.email + '/' 
                    this.$http.post('http://localhost:8085/users/resetpassword/' + path)
                        .then(response => {
                            this.showResetMail = false;
                            this.showResetPass = false;
                        }, error => {
                            console.log(error);
                        });
                }
            }
        }
      }
    }
</script>

<style>    
    .space {
        margin-bottom: 25px;
    }
</style>
