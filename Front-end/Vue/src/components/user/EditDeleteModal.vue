<template>
    <div id="modal">
        <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                        <h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
                    </div>
                    <div class="modal-body">
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
                                <option value="ADMIN">    ADMIN    </option>
                                <option value="HR">       HR       </option>
                                <option value="EMPLOYEE"> EMPLOYEE </option>
                            </select> 
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input type="text" class="form-control" placeholder="e-mail" v-model="mb" required>
                            <span class="input-group-addon">@</span>
                            <input type="text" class="form-control" placeholder="mailserver" v-model="ms" required> 
                            <span class="input-group-addon">.</span>
                            <input type="text" class="form-control" placeholder="extension" v-model="ex" required> 
                        </div>

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                            <input type="text" class="form-control" v-model="user.telephone" placeholder="telephone" required> 
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-tags"></i></span>
                            <select class="form-control" v-model="user.team.id" placeholder="team id" required>
                                <option v-for="n in noTeams" :value="n"> {{ n }} </option>
                            </select>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input type="text" class="form-control" v-model="user.username" placeholder="username" required>                                        
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning btn-md" @click="updateUser" data-dismiss="modal"><span class="glyphicon glyphicon-ok-sign"></span>Update</button>
                        <button type="button" class="btn btn-danger btn-md" @click="cancelUpdateUser" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>Cancel</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                        <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>
                    </div>
                    <div class="modal-footer ">
                        <button type="button" class="btn btn-success" data-dismiss="modal" @click="deleteUser"><span class="glyphicon glyphicon-ok-sign"></span>Yes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>No</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: [
                'user'
            ],
        data() {
            return {
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
                }]
            }
        },
        methods: {
            cancelUpdateUser() {
            },
            updateUser() {
                this.$http.put('http://localhost:8085/users', 
                                JSON.stringify(this.user))
                                .then( response => {
                                    this.$emit('update', this.user);  
                                });
            },
            deleteUser() {
                var user_id = this.user.id;
                
                this.$http.delete('http://localhost:8084/documents/deletebyauthor/' + user_id);
                this.$http.delete('http://localhost:8082/vacations/deletebyuser/'   + user_id);
                this.$http.delete('http://localhost:8085/users/' + user_id);

                this.$emit('deleteUser', event.target.value);
            }
        },
        computed: {
            mail() {
                return this.mb + '@' + this.ms + '.' + this.ex;
            },
            mb: {
                get: function () {
                    if (this.user.email)
                        return this.user.email.split("@")[0];
                },
                set: function (mb) {
                    this.user.email = mb + '@' + this.ms + '.' + this.ex;
                }
            },
            ms: {
                get: function () {
                    if (this.user.email) {
                        var arr = this.user.email.split("@")[1].split(".");
                        return arr.splice(0, arr.length - 1);
                    }
                },
                set: function (ms) {
                    this.user.email = this.mb + '@' + ms + '.' + this.ex;
                }
            },
            ex: {
                get: function () {
                    if (this.user.email)
                        return this.user.email.split(".").slice(-1)[0];
                },
                set: function (ex) {
                    this.user.email = this.mb + '@' + this.ms + '.' + ex;
                }
            }
        },
        created() {
            this.$http.get('http://localhost:8085/teams')
                .then(response => {
                    this.noTeams = Object.keys(response.body._embedded.teams).length;
                });
        },
    }
</script>
