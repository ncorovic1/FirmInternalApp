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
                            <input type="text" class="form-control" v-model="user.firstName" placeholder="firstname" required> 
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-menu-right"></i></span>
                            <input type="text" class="form-control" v-model="user.lastName" placeholder="lastname" required>   
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                            <input type="text" class="form-control" v-model="user.address" placeholder="address" required>    
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            <input type="date" class="form-control" v-model="user.dateOfBirth" placeholder="date of birth" required>   
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-heart"></i></span>
                            <input type="number" class="form-control" v-model="user.daysOfVacation" placeholder="days of vacation" min="0" required>  
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
                            <input type="text" class="form-control" v-model="user.telephone" placeholder="telephone" required> 
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-tags"></i></span>
                            <select class="form-control" v-model="user.team.id" placeholder="team id" required>
                                <option v-for="t in teamList" :value="t.id">{{ t.name }}</option>
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
                    firstName: '',
                    lastName: '',
                    address: '',
                    dateOfBirth: '',
                    daysOfVacation: '0',
                    gender: 'male',
                    role: 'EMPLOYEE',
                    email: '',
                    telephone: '',
                    team: {
                        id: 1,
                        name: ''
                    },
                    username: '',
                    password: ''
                },
                error: '',
                teamList: '',
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
                this.user.email = this.mail;
                this.user.dateOfBirth = new Date(this.user.dateOfBirth).getTime();

                this.$http.post('http://localhost:8085/users', 
                                JSON.stringify(this.user)
                                ).then(response => {
                            });
                
                this.$http.get('http://localhost:8085/users/byusername/' + this.user.username)
                            .then(response => {
                                this.user.id = response.body.id;
                                this.user.team.name = this.getTeam(this.user.team.id).name;
                                this.$emit('add', this.user);
                            });
            },
            getTeam(id) {
                for(var k in this.teamList) {
                    if (this.teamList[k].id == id) {
                        return this.teamList[k];
                    }
                }
            }
        },
        created() {
            this.$http.get('http://localhost:8083/teams')
                            .then(response => {
                                this.teamList = response.body;
                            });
        },
        computed: {
            mail() {
                return this.email.mb + '@' + this.email.ms + '.' + this.email.ex;
            }
        }
    }
</script>