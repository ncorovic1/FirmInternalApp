<template>
    <div class="container">
        <div class="row">
        <div class="col-md-5 toppad pull-right col-md-offset-3">
            <a href="edit.html" >Edit Profile</a>
            <br>
            <p class=" text-info">May 05,2014,03:00 pm </p>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        {{ user.firstname + ' ' + user.lastname }}
                    </h3>
                    <h3 class="panel-title subtitle">
                        {{ '@' + user.username }}
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"></div>

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
                                        <td>{{ user.daysov }}</td>
                                    </tr>
                                    <tr>
                                        <td>Date of Birth</td>
                                        <td>{{ user.dateob }}</td>
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
                                        <td>{{ user.contact }}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                 <div class="panel-footer text-center">
                    <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning text-center"><i class="glyphicon glyphicon-edit"></i></a>
                </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                user: {
                    id:        '1',
                    firstname: 'Amra',
                    lastname:  'Hadziarapovic',
                    address:   'Cadordzina 45a',
                    dateob:    '1994-01-01',
                    daysov:    '22',
                    gender:    'Female',
                    role:      'ADMIN',
                    email:     'amra.hadziarapovic@gmail.com',
                    contact:   '062/908-445',
                    team:      '1',
                    username:  'amra',
                    password:  '1234'
                }

            }
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