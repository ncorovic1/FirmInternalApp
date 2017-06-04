<template>
    <div id="table">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <h2 class="text-center">Users</h2>
                    </div>
                
                    <div class="row" v-show="admin">
                        <button @click="userFormToggle" class="btn btn-primary btn-block"> {{ formButton }} </button>
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
                                    <th>{{ key + 1     }}</th>
                                    <td>{{ u.firstname }}</td>
                                    <td>{{ u.lastname  }}</td>
                                    <td>{{ u.address   }}</td>
                                    <td>{{ u.dateob    }}</td>
                                    <td>{{ u.daysov    }}</td>
                                    <td>{{ u.gender    }}</td>
                                    <td>{{ u.role      }}</td>
                                    <td>{{ u.email     }}</td>
                                    <td>{{ u.contact   }}</td>
                                    <td>{{ u.team      }}</td>
                                    <td>{{ u.username  }}</td>
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
        <app-modal :user="user" @deleteUser="userList.splice(activeModal, 1)" @update="update"></app-modal>
    </div>
</template>

<script>
    import Add from './AddForm';
    import Modal from './EditDeleteModal';
    import Auth from '../../assets/auth';
    
    class User {
        constructor(id, fn, ln, add, dob, dov, g, r, em, c, t, user, pass) {
            this.id        = id;
            this.firstname = fn;
            this.lastname  = ln;
            this.address   = add;
            this.dateob    = dob;
            this.daysov    = dov;
            this.gender    = g;
            this.role      = r;
            this.email     = em;
            this.contact   = c;
            this.team      = t;
            this.username  = user;
            this.password  = pass;       
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
                filterBy: 'Firstname',
                keyword: '',
                activeModal: '0',
                admin: true,
                hr: false,
                userList: [
                    new User(
                        '1',
                        'Nino',
                        'Corovic',
                        'Cadordzina 45a',
                        '27/01/1995',
                        '24',
                        'male',
                        'ADMIN',
                        'nino.corovic@gmail.com',
                        '062/908-445',
                        '1',
                        'nino',
                        '1234'
                        ),
                    new User(
                        '2',
                        'Dino',
                        'Corovic',
                        'Cadordzina 45a',
                        '27/01/1995',
                        '24',
                        'male',
                        'HR',
                        'dino.corovic@gmail.etf.unsa.ba.com',
                        '062/908-445',
                        '2',
                        'dino',
                        '1234'
                        )
                ],
                user: []
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
                                     this.userList[key].firstname,
                                     this.userList[key].lastname,
                                     this.userList[key].address,
                                     this.userList[key].dateob,
                                     this.userList[key].daysov,
                                     this.userList[key].gender,
                                     this.userList[key].role,
                                     this.userList[key].email,
                                     this.userList[key].contact,
                                     this.userList[key].team,
                                     this.userList[key].username,
                                     this.userList[key].password
                                     );
            },
            update() {
                this.userList[this.activeModal] = this.user;
                this.userList.push();  
                this.user = [];
            },
            add(args) {
                this.userList.push(args);
                this.userFormToggle();
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
                            return us.username.toLowerCase().includes(this.keyword);
                            break;
                        case 'Lastname': 
                            return us.lastname.toLowerCase().includes(this.keyword);
                            break;
                        case 'Role': 
                            return us.role.toLowerCase().includes(this.keyword);
                            break;
                        case 'Team': 
                            //alert(JSON.stringify(us));
                            return us.team.toLowerCase().includes(this.keyword);
                            break;
                        case 'Gender': 
                            return us.gender.toLowerCase().includes(this.keyword);
                            break;
                    }
                })
            }
        },
        created() {
            this.$http.get('http://localhost:8085/users',
                            {
                                headers: {
                                  'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaW5vLEFETUlOIiwiZXhwIjoxNDk3MTQyNjA5fQ.RR7l5033nLh3OZ9I3fUsX_B549d5GTZJROCXj678PrLavrFsVly1lnESDR9_GsqdWiGNzdLGgcMt4cmnaNCAaw',
                                  'Accept': 'application/json'
                                }
                            }).then(response => {
                                //console.log(response);
                            }, error => {
                                //console.log(error)
                            });
        }
    }
</script>