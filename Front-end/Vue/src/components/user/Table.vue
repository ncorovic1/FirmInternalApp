<template>
    <div id="table">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <h2 class="text-center">Users</h2>
                    </div>
                
                    <div class="row" v-show="admin">
                        <button @click="userFormToggle" class="btn btn-primary btn-block">
                            <i class="glyphicon glyphicon-plus" style="float:left"></i>
                                {{ formButton }} 
                            <i class="glyphicon glyphicon-plus" style="float:right"></i>
                        </button>
                        <div style="height: 30px;"></div>
                        <app-add v-show="showAdd" :noUsers="userList.length" @add="add($event)"></app-add>
                    </div>

                    <div class="row">    
                        <div class=" col-sm-4 col-sm-offset-4 col-xs-12">
                            <div class="input-group">
                                <div class="input-group-btn search-panel">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span id="search_concept">Filter by {{ filterBy }}</span> <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li @click="filterBy = 'Firstname'" v-show="admin || hr">
                                            <a>Firstname</a>
                                        </li>
                                            <li v-show="admin || hr" class="divider"></li>
                                        <li @click="filterBy = 'Lastname'" v-show="admin || hr">
                                            <a>Lastname</a>
                                        </li>
                                            <li v-show="admin || hr" class="divider"></li>
                                        <li @click="filterBy = 'Role'" v-show="admin || hr">
                                            <a>Role</a>
                                        </li>
                                            <li v-show="admin || hr" class="divider"></li>
                                        <li @click="filterBy = 'Team'">
                                            <a>Team</a>
                                        </li>
                                            <li class="divider"></li>
                                        <li @click="filterBy = 'Gender'">
                                            <a>Gender</a>
                                        </li>
                                    </ul>
                                </div>
                                <input type="hidden" name="search_param" value="all" id="search_param">         
                                <input type="text" class="form-control" name="x" placeholder="Search term..." v-model="keyword">
                                <span class="input-group-btn">
                                    <div class="btn btn-default">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </div>
                                </span>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="height: 30px"></div>
                    <div class="table-responsive">
                        <table id="mytable" class="table table-bordred table-striped table-hover">
                            <thead>
                                <tr>
                                    <th> No.              </th>
                                    <th> Firstname        </th>
                                    <th> Lastname         </th>
                                    <th> Address          </th>
                                    <th> Date of Birth    </th>
                                    <th> Days of Vacation </th>
                                    <th> Gender           </th>
                                    <th> Role             </th>
                                    <th> Email            </th>
                                    <th> Contact          </th>
                                    <th> Team             </th>
                                    <th> Username         </th>
                                    <th v-show="admin"> Edit   </th>
                                    <th v-show="admin"> Delete </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(u, key) in filteredUserList">
                                    <th>{{ key + 1             }}</th>
                                    <td>{{ u.firstName         }}</td>
                                    <td>{{ u.lastName          }}</td>
                                    <td>{{ u.address           }}</td>
                                    <td>{{ userDate(key)       }}</td>
                                    <td>{{ u.daysOfVacation    }}</td>
                                    <td>{{ u.gender            }}</td>
                                    <td>{{ u.role              }}</td>
                                    <td>{{ u.email             }}</td>
                                    <td>{{ u.telephone         }}</td>
                                    <td>{{ u.team.name         }}</td>
                                    <td>{{ u.username          }}</td>
                                    <td v-show="admin">
                                        <p data-placement="top" data-toggle="tooltip" title="Edit">
                                            <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" @click="populateUser(key)"><span class="glyphicon glyphicon-pencil"></span></button>
                                        </p>
                                    </td>
                                    <td v-show="admin">
                                        <p data-placement="top" data-toggle="tooltip" title="Delete">
                                            <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" @click="activeModal = key"><span class="glyphicon glyphicon-trash"></span></button>
                                        </p>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="clearfix"></div>
                        <ul class="pagination pull-right" style="display: none;">
                            <li class="disabled">
                                <a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a>
                            </li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <app-modal :user="user" @deleteUser="userList.splice(activeModal, 1)" @update="update($event)"></app-modal>
    </div>
</template>

<script>
    import Add from './AddForm';
    import Modal from './EditDeleteModal';
    import Auth from '../../assets/auth';
    
    class User {
        constructor(id, fn, ln, add, dob, dov, g, r, em, c, t, user, pass) {
            this.id             = id;
            this.firstName      = fn;
            this.lastName       = ln;
            this.address        = add;
            this.dateOfBirth    = dob;
            this.daysOfVacation = dov;
            this.gender         = g;
            this.role           = r;
            this.email          = em;
            this.telephone      = c;
            this.team           = t;
            this.username       = user;
            this.password       = pass;       
      }
    }
    
    export default {
        components: {
            'app-add': Add,
            'app-modal': Modal
            },
        data() {
            return {
                showAdd: false,
                formButton: 'Add User',
                formButtonValues: [
                    'Add User',
                    'Close Form'
                ],
                filterBy: 'Team',
                keyword: '',
                activeModal: '0',
                admin: false,
                hr: false,
                userList: [],
                user: {
                    team: {
                        id: ''
                    }
                }     
            }
        },
        methods: {
            userFormToggle() {
                this.showAdd = !this.showAdd;
                this.formButton = this.formButtonValues[this.showAdd ? 1 : 0];
            },
            populateUser(key) {
                this.activeModal = key;
                this.user = new User(
                                     this.userList[key].id,
                                     this.userList[key].firstName,
                                     this.userList[key].lastName,
                                     this.userList[key].address,
                                     this.userList[key].dateOfBirth,
                                     this.userList[key].daysOfVacation,
                                     this.userList[key].gender,
                                     this.userList[key].role,
                                     this.userList[key].email,
                                     this.userList[key].telephone,
                                     this.userList[key].team,
                                     this.userList[key].username,
                                     this.userList[key].password
                                     );
            },
            update(arg) {
                this.userList[this.activeModal] = arg;
                this.userList.push();  
            },
            add(args) {
                this.userList.push(args);
                this.userFormToggle();
            },
            userDate(i) {
                var time = new Date(this.userList[i].dateOfBirth);
                time.setHours(time.getHours() + 2);
                this.userList[i].dateOfBirth = time.toISOString().substring(0, 10);
                return this.userList[i].dateOfBirth;
            }
        },
        computed: {
            filteredUserList() {
                this.keyword = this.keyword.toLowerCase();
                function compare(a, b) {
                    if (a.id < b.id)
                        return 1;
                    if (a.id > b.id)
                        return -1;
                  return 0;
                }
                return this.userList.sort(compare).filter((us) => {
                    switch(this.filterBy) {
                        case 'Firstname': 
                            return us.firstName.toLowerCase().includes(this.keyword);
                            break;
                        case 'Lastname': 
                            return us.lastName.toLowerCase().includes(this.keyword);
                            break;
                        case 'Role': 
                            return us.role.toLowerCase().includes(this.keyword);
                            break;
                        case 'Team': 
                            return us.team.name.toLowerCase().includes(this.keyword);
                            break;
                        case 'Gender': 
                            return us.gender.toLowerCase().includes(this.keyword);
                            break;
                    }
                })
            }
        },
        created() {
            switch (localStorage.getItem('Role')) {
                case 'ADMIN': 
                    this.admin = 'true';
                    break;
                case 'HR':
                    this.hr = 'true';
                    break;
            }
            
            this.$http.get('http://localhost:8085/users')
                .then(response => {
                    this.userList = response.body;
                }, error => {
                    //console.log(error)
                });
        },
        route: {
            canActivate() {
                return false;
          }
        }
    }
</script>

<style>

</style>