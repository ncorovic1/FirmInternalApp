<template>
    <form class="form-horizontal" role="form">
        <div id="loginbox" style="" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title text-center">Add Vacation</div>
                </div>     

                <div style="padding-top:30px" class="panel-body" >
                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>  
                    <form class="form-horizontal" role="form">
                        <div class="alert alert-danger" v-if="error">
                            <p>{{ error }}</p>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-barcode"></i></span>
                            <input type="date" class="form-control" v-model="vacation.beginDate" placeholder="begin date" required>                                        
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-volume-up"></i></span>
                            <input type="date" class="form-control" v-model="vacation.endDate" placeholder="end date" required>                         
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-barcode"></i></span>
                            <select type="text" class="form-control" v-model="vacation.vacationType.id" placeholder="vacation type" required>   
                                <option v-for="vt in vacTypeList" :value="vt.id">{{ vt.description }}</option>
                            </select>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-tower"></i></span>
                            <select class="form-control" v-model="vacation.user.id" placeholder="u.id" required> 
                                <option v-for="u in userList" :value="u.id"> {{ u.username }} </option>
                            </select>
                        </div>

                        <div style="margin-top:10px" class="form-group">
                            <div class="col-sm-12 controls">
                                <a id="btn-add" @click="addVacation" class="btn btn-success btn-block">
                                    <i class="glyphicon glyphicon-plus" style="float:left"></i>
                                    Add Vacation
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
            'noVacs'
        ],
        data() {
            return {
                error: '',
                noUsers: '',
                vacation: {
                    id: '',
                    beginDate: '',
                    endDate: '',
                    vacationType: {
                        id: '',
                        description: ''    
                    },
                    user: {
                        id: '',
                        firstName: '',
                        lastName: ''
                    }
                },
                userList: [],
                vacTypeList: []
            }
        },
        methods: {
            addVacation() {
                this.$http.post('http://localhost:8082/vacations', 
                                JSON.stringify(this.vacation))
                                .then(response => {});
                            
                /*this.$http.get('http://localhost:8082/vacations')
                            .then(response => {
                                this.vacsList = response.body;
                            });*/
                            
                var us = this.getUser(this.vacation.user.id);
                var vt = this.getVacType(this.vacation.user.id);
                this.vacation.user.firstName = us.firstName;
                this.vacation.user.lastName  = us.lastName;
                this.vacation.vacationType.description = vt.description;
    
                this.$emit('add', this.vacation);
            },
            getUser(id) {
                for(var k in this.userList) {
                    if (this.userList[k].id == id) {
                        return this.userList[k];
                    }
                }
            },
            getVacType(id) {
                for(var k in this.vacTypeList) {
                    if (this.vacTypeList[k].id == id) {
                        return this.vacTypeList[k];
                    }
                }
            }
        },
        created() {
            this.$http.get('http://localhost:8085/users')
                .then(response => {
                    this.userList = response.body;
                    this.vacation.user.id = this.userList[0].id;
                });
            
            this.$http.get('http://localhost:8082/vacationTypes')
                .then(response => {
                    this.vacTypeList = response.body;
                    this.vacation.vacationType.id = this.vacTypeList[0].id;
                });
        }
    }
</script>