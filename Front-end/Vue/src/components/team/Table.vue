<template>
    <div id="table">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <h2 class="text-center">Teams</h2>
                    </div>
                    
                    <div class="row">
                        <button @click="teamFormToggle" class="btn btn-warning btn-block">  
                            <i class="glyphicon glyphicon-plus" style="float:left"></i>
                                {{ formButton }} 
                            <i class="glyphicon glyphicon-plus" style="float:right"></i>
                        </button>
                        <div style="height: 30px;"></div>
                        <app-add v-show="showAdd" :team="team" :noTeams="teamList.length" @add="add($event)"></app-add>
                    </div>

                    <div class="row">    
                        <div class="col-xs-8 col-xs-offset-2">
                            <div class="input-group">
                                <div class="input-group-btn search-panel">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span id="search_concept">Filter by {{ filterBy }}</span> <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li @click="filterBy = 'Handle'"><a>Handle</a></li>
                                            <li class="divider"></li>
                                        <li @click="filterBy = 'Info'"><a>Info</a></li>
                                            <li class="divider"></li>
                                        <li @click="filterBy = 'Name'"><a>Name</a></li>
                                    </ul>
                                </div>
                                <input type="hidden" name="search_param" value="all" id="search_param">         
                                <input type="text" class="form-control" name="x" placeholder="Search term..." v-model="keyword">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="height: 30px"></div>
                    <div class="table-responsive">
                        <table id="mytable" class="table table-bordred table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>No.      </th>
                                    <th>Handle   </th>
                                    <th>Info     </th>
                                    <th>Name     </th>
                                    <th>Members  </th>
                                    <th>Edit   </th>
                                    <th v-show="admin || hr">Delete </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(t, key) in filteredTeamList">
                                    <td>{{ key + 1        }}</td>
                                    <td>{{ '@' + t.handle }}</td>
                                    <td>{{ t.info         }}</td>
                                    <td>{{ t.name         }}</td>
                                    <td>
                                        <p data-placement="top" data-toggle="tooltip" title="Members">
                                            <button class="btn btn-primary btn-xs" data-title="Members" data-toggle="modal" data-target="#members" @click="populateMembers(key)"><span class="glyphicon glyphicon-th-list"></span>
                                            <span class="glyphicon glyphicon-th-list"></span>
                                            <span class="glyphicon glyphicon-th-list"></span>
                                            <span class="glyphicon glyphicon-th-list"></span></button>
                                        </p>
                                    </td>
                                    <td>
                                        <p data-placement="top" data-toggle="tooltip" title="Edit">
                                            <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" @click="populateTeam(key)"><span class="glyphicon glyphicon-pencil"></span></button>
                                        </p>
                                    </td>
                                    <td v-show="admin || hr">
                                        <p data-placement="top" data-toggle="tooltip" title="Delete">
                                            <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" @click="populateTeam(key)"><span class="glyphicon glyphicon-trash"></span></button>
                                        </p>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <div class="clearfix"></div>
                        <ul class="pagination pull-right" style="display: none;">
                          <li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
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
        <app-memb :teamId="teamId"></app-memb>
        <app-eddel :team="team" @deleteTeam="teamList.splice(activeModal, 1)" @update="update($event)"></app-eddel>
    </div>
</template>

<script>
    import Add        from './AddForm';
    import Members    from './Members';
    import EditDelete from './EditDeleteModal';
    import Auth       from '../../assets/auth';
    
    class Team {
        constructor(id, h, i, n) {
            this.id     = id;
            this.handle = h;
            this.info   = i;
            this.name   = n;
      }
    }
    
    export default {
        components: {
            'app-add': Add,
            'app-memb': Members,
            'app-eddel': EditDelete
            },
        data() {
            return {
                showAdd: false,
                formButton: 'Add Team',
                formButtonValues: [
                    'Add Team',
                    'Close Form'
                ],
                filterBy: 'Handle',
                keyword: '',
                activeModal: '0',
                admin: false,
                hr: false,
                teamList: [],
                team: [],
                teamId: 0
            }
        },
        methods: {
            teamFormToggle() {
                this.showAdd = !this.showAdd;
                this.formButton = this.formButtonValues[this.showAdd ? 1 : 0];
            },
            populateTeam(key) {
                this.activeModal = key;
                this.team = new Team(
                     this.teamList[key].id,
                     this.teamList[key].handle,
                     this.teamList[key].info,
                     this.teamList[key].name
                );
            },
            populateMembers(key) {
                this.activeModal = key;
                this.teamId = this.teamList[key].id;
            },
            update(arg) {
                this.teamList[this.activeModal] = arg;
                this.teamList.push();
            },
            add(args) {
                this.teamList.push(args);
                this.teamFormToggle();
            }
        },
        computed: {
            filteredTeamList() {
                this.keyword = this.keyword.toLowerCase();
                function compare(a, b) {
                    if (a.id < b.id)
                        return 1;
                    if (a.id > b.id)
                        return -1;
                  return 0;
                }
                return this.teamList.sort(compare).filter((te) => {
                    switch(this.filterBy) {
                        case 'Handle': 
                            return te.handle.toLowerCase().includes(this.keyword);
                            break;
                        case 'Info': 
                            return te.info.toLowerCase().includes(this.keyword);
                            break;
                        case 'Name': 
                            return te.name.toLowerCase().includes(this.keyword);
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
            
            this.$http.get('http://localhost:8083/teams')
                .then(response => {
                    this.teamList = response.body;
                    this.teamId = response.body[0].id;
                });
        }
    }
</script>