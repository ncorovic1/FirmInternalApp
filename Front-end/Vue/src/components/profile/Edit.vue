<template>
    <div id="modal">
        <div class="modal fade" id="editProfile" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
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
                            <input type="text" class="form-control" v-model="user.username" placeholder="username" required>                           
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input type="text" class="form-control" v-model="user.address" placeholder="Home address" required>                                        
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input type="text" class="form-control" v-model="user.telephone" placeholder="Contact" required>                                        
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input type="text" class="form-control" placeholder="e-mail" v-model="mb" required>
                            <span class="input-group-addon">@</span>
                            <input type="text" class="form-control" placeholder="mailserver" v-model="ms" required> 
                            <span class="input-group-addon">.</span>
                            <input type="text" class="form-control" placeholder="extension" v-model="ex" required> 
                        </div>
                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning btn-md" @click="updateUser" data-dismiss="modal"><span class="glyphicon glyphicon-ok-sign"></span>Update</button>
                        <button type="button" class="btn btn-danger btn-md" @click="cancelUpdateUser" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>Cancel</button>
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
                noTeams: ''
            }
        },
        methods: {
            cancelUpdateUser() {
            },
            updateUser() {
                this.$http.put('http://localhost:8085/users', 
                    JSON.stringify(this.user))
                    .then( response => {
                        this.$emit('edit', this.user);  
                    });
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
        }
    }
</script>
