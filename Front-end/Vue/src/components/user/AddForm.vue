<template>
    <form class="form-horizontal" role="form">
        <div id="loginbox" style="" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title text-center">Add User</div>
                </div>     

                <div style="padding-top:30px" class="panel-body" >
                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>  
                    <form id="loginform" class="form-horizontal" role="form">
                        
                        <div class="alert alert-danger" v-if="error">
                            <p>{{ error }}</p>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-menu-left"></i></span>
                            <input type="text" class="form-control" v-model="user.firstname" placeholder="firstname" required> 
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <input type="text" class="form-control" v-model="user.lastname" placeholder="lastname" required>   
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                            <input type="text" class="form-control" v-model="user.address" placeholder="address" required>    
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            <input type="date" class="form-control" v-model="user.dateob" placeholder="date of birth" required>   
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-heart"></i></span>
                            <input type="number" class="form-control" v-model="user.daysov" placeholder="days of vacation" min="0" required>  
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-grain"></i></span>
                            <select class="form-control" v-model="user.gender" placeholder="gender" required>
                                <option v-for="g in genderList" :value="g.value">{{ g.text }}</option>
                            </select>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-tower"></i></span>
                            <select class="form-control" v-model="user.role" placeholder="role" required> 
                                <option v-for="r in roleList" :value="r.value">{{ r.text }}</option>
                            </select>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input type="text" class="form-control" v-model="email.mb" placeholder="mailbox" required> 
                            <span class="input-group-addon">@</span>
                            <input type="text" class="form-control" v-model="email.ms" placeholder="mailserv" required> 
                            <span class="input-group-addon">.</span>
                            <input type="text" class="form-control" v-model="email.ex" placeholder="extension" required> 
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                            <input type="text" class="form-control" v-model="user.contact" placeholder="telephone" required> 
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-tags"></i></span>
                            <select class="form-control" v-model="user.team" placeholder="team id" required>
                                <option v-for="n in noTeams" :value="n"> {{ n }} </option>
                            </select>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input type="text" class="form-control" v-model="user.username" placeholder="username" required>         
                        </div>

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input type="password" class="form-control" v-model="user.password" placeholder="password" required>
                        </div>

                        <div style="margin-top:10px" class="form-group">
                            <div class="col-sm-12 controls">
                                <a id="btn-add" @click="addUser" class="btn btn-success btn-block">
                                    <i class="glyphicon glyphicon-plus" style="float:left"></i>
                                    Add User
                                    <i class="glyphicon glyphicon-plus" style="float:right"></i>
                                </a>
                            </div>
                        </div>
                    </form>     
                </div>                     
            </div>  
        </div>
    </form>   
</template>

<script>
    export default {
        props: [
            'noUsers'
        ],
        data() {
            return {
                email: {
                    mb: '',
                    ms: '',
                    ex: ''
                },
                user: {
                    id: '',
                    firstname: '',
                    lastname: '',
                    address: '',
                    dateob: '',
                    daysov: '0',
                    gender: 'male',
                    role: 'EMPLOYEE',
                    email: '',
                    contact: '',
                    team: '1',
                    username: '',
                    password: ''
                },
                error: '',
                noTeams: '',
                genderList: [{
                    value: 'male',
                    text: 'male'
                }, {
                    value: 'female',
                    text: 'female'
                }],
                roleList: [{
                    value: 'ADMIN',
                    text: 'ADMIN'
                }, {
                    value: 'HR',
                    text: 'HR'
                }, {
                    value: 'EMPLOYEE',
                    text: 'EMPLOYEE'
                }],
                roleList: [{
                    value: 'ADMIN',
                    text: 'ADMIN'
                }, {
                    value: 'HR',
                    text: 'HR'
                }, {
                    value: 'EMPLOYEE',
                    text: 'EMPLOYEE'
                }]
            }
        },
        methods: {
            addUser() {
                this.user.id = this.noUsers + 1;
                this.user.email = this.mail;
                this.$emit('add', this.user);
            }
        },
        created() {
            //povuci iz baze
            this.noTeams = 2;
        },
        computed: {
            mail() {
                return this.email.mb + '@' + this.email.ms + '.' + this.email.ex;
            }
        }
    }
</script>