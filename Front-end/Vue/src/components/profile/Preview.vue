<template>
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 input-group">
                <h3 class="text-center" style="border-radius: 10px; background-color: #c4e3f3;">{{ currentTime }} </h3>
            </div>
        </div>       
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            {{ user.firstName + ' ' + user.lastName }}
                        </h3>
                        <h3 class="panel-title subtitle">
                            {{ '@' + user.username }}
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-3 col-lg-3 " align="center"> 
                                <img alt="User Pic" src="http://www.clker.com/cliparts/U/p/K/K/m/P/boy-comic.svg" class="img-circle img-responsive" v-if="user.gender == 'male'">
                                <img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive" v-else>
                            </div>

                            <div class=" col-md-9 col-lg-9 "> 
                                <table class="table table-user-information">
                                    <tbody>
                                        <tr>
                                            <td>Username:</td>
                                            <td>{{ user.username }}</td>
                                        </tr>
                                        <tr>
                                            <td>Role:</td>
                                            <td>{{ user.role }}</td>
                                        </tr>
                                        <tr>
                                            <td>Days of Vacatioin:</td>
                                            <td>{{ user.daysOfVacation }}</td>
                                        </tr>
                                        <tr>
                                            <td>Date of Birth</td>
                                            <td>{{ getTime(new Date(user.dateOfBirth), 10) }}</td>
                                        </tr>
                                        <tr>
                                            <td>Gender</td>
                                            <td>{{ user.gender }}</td>
                                        </tr>
                                        <tr>
                                            <td>Home Address</td>
                                            <td>{{ user.address }}</td>
                                        </tr>
                                        <tr>
                                            <td>Email</td>
                                            <td><a href="mailto:info@support.com">{{ user.email }}</a></td>
                                        </tr>
                                        <tr>
                                            <td>Phone Number</td>
                                            <td>{{ user.telephone }}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer text-center">
                        <p data-placement="top" data-toggle="tooltip" title="Edit">
                            <button class="btn btn-warning btn-md" data-title="Edit" data-toggle="modal" data-target="#editProfile">
                                <i class="glyphicon glyphicon-edit"></i>
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>
      <app-editp :user="user" @edit="update($event)"></app-editp>
    </div>
</template>

<script>
    import Edit from './Edit';
    var my_var;
    export default {
        components: {
            'app-editp': Edit
        },
        data() {
            return {
                user: [],
                time: 0
            }
        },
        methods: {
            update(arg) {
                this.user = arg;
            },
            getTime(arg, trim) {
                if ( isNaN( arg.getTime() ) )
                    return '';
                var timer = arg;
                timer.setHours(timer.getHours() + 2);
                return timer.toISOString().substring(0, trim).replace('T', ' ');
            }
        },
        mounted() {
            window.setInterval(() => {
                this.time = new Date();
            }, 1000);
        },
        computed: {
            currentTime() {
                return this.getTime(this.time, 19);
            }
        },
        created() {
            this.$http.get('http://localhost:8085/users/byusername/' + localStorage.getItem('Username'))
                .then(response => {
                    this.user = response.body;
                });
            this.time = new Date();
        }
    }
</script>

<style scoped>
    .user-row {
        margin-bottom: 14px;
    }

    .user-row:last-child {
        margin-bottom: 0;
    }

    .dropdown-user {
        margin: 13px 0;
        padding: 5px;
        height: 100%;
    }

    .dropdown-user:hover {
        cursor: pointer;
    }

    .table-user-information > tbody > tr {
        border-top: 1px solid rgb(221, 221, 221);
    }

    .table-user-information > tbody > tr:first-child {
        border-top: 0;
    }

    .table-user-information > tbody > tr > td {
        border-top: 0;
    }
    .toppad {
        margin-top:20px;
    }
    .subtitle {
        color: grey;
    }
</style>