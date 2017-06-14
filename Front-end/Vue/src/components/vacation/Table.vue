<template>
    <div id="table">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row" v-show="admin || hr">
                        <h2 class="text-center">Vacation Types </h2>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <div style="margin-bottom: 5px" class="input-group">
                                <input type="text" class="form-control" v-model="newVacType.description" placeholder="vacation type description" required>
                                <input type="number" class="form-control" v-model="newVacType.factor" placeholder="vacation type factor" min="-1" max="1" required> 
                                <span class="input-group-addon" @click="addVacType">ADD</span>
                            </div>
                            <div style="margin-bottom: 5px" class="input-group">
                                <span class="input-group-addon" @click="edVacType">E</span>
                                <select type="text" v-model="chosenVT" id="chosenVt" class="form-control" placeholder="vacation type" required>   
                                    <option v-for="vt in vacTypeList" :value="vt.id">
                                        {{ vt.description + ' - [' + vt.factor +']'}}
                                    </option>
                                </select>
                                <span class="input-group-addon" @click="delVacType">D</span>
                            </div>
                        </div>
                        <div style="height: 30px;"></div>
                    </div>
                    
                    <div class="row">
                        <h2 class="text-center">Vacations</h2>
                    </div>
                    <div class="row" v-show="admin || hr">
                        <button @click="vacationFormToggle" class="btn btn-success btn-block"> 
                            <i class="glyphicon glyphicon-plus" style="float:left"></i>
                                {{ formButton }} 
                            <i class="glyphicon glyphicon-plus" style="float:right"></i>
                        </button>
                        <div style="height: 30px;"></div>
                        <app-add v-show="showAdd" :vacation="vacation" :noVacs="vacList.length" @add="add($event)"></app-add>
                    </div>

                    <div class="row">    
                        <div class="col-xs-8 col-xs-offset-2">
                            <div class="input-group">
                                <div class="input-group-btn search-panel">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span id="search_concept">Filter by {{ filterBy }}</span> <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li @click="filterBy = 'User'"><a>User</a></li>
                                            <li class="divider"></li>
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
                                    <th>No.                  </th>
                                    <th>Begin date           </th>
                                    <th>End date             </th>
                                    <th>Vacation description </th>
                                    <th>User                 </th>
                                    <th v-show="admin || hr">Edit   </th>
                                    <th v-show="admin || hr">Delete </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(v, key) in filteredVacList">
                                    <td>{{ key + 1                                  }}</td>
                                    <td>{{ v.beginDate                              }}</td>
                                    <td>{{ v.endDate                                }}</td>
                                    <td>{{ v.vacationType.description               }}</td>
                                    <td>{{ v.user.firstName + ' ' + v.user.lastName }}</td>
                                    <td v-show="admin || hr">
                                        <p data-placement="top" data-toggle="tooltip" title="Edit">
                                            <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" @click="populateVacation(key)"><span class="glyphicon glyphicon-pencil"></span></button>
                                        </p>
                                    </td>
                                    <td v-show="admin || hr">
                                        <p data-placement="top" data-toggle="tooltip" title="Delete">
                                            <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" @click="populateVacation(key)"><span class="glyphicon glyphicon-trash"></span></button>
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
        <app-eddel :vacation="vacation" @deleteVacation="vacList.splice(activeModal, 1)" @update="update"></app-eddel>
    </div>
</template>

<script>
    import Add        from './AddForm';
    import EditDelete from './EditDeleteModal';
    import Auth       from '../../assets/auth';
    
    class Vacation {
        constructor(id, bd, ed, vt, us) {
            this.id           = id;
            this.beginDate    = bd;
            this.endDate      = ed;
            this.vacationType = vt;
            this.user         = us;
        }
    }
    
    export default {
        components: {
            'app-add': Add,
            'app-eddel': EditDelete
        },
        data() {
            return {
                showAdd: false,
                formButton: 'Add Vacation',
                formButtonValues: [
                    'Add Vacation',
                    'Close Form'
                ],
                filterBy: 'User',
                keyword: '',
                activeModal: '0',
                admin: false,
                hr: false,
                vacList: [],
                vacation: [],
                vacTypeList: [],
                newVacType: {
                    id: "",
                    description: "",
                    factor: 0
                },
                chosenVT: ''
            }
        },
        methods: {
            vacationFormToggle() {
                this.showAdd = !this.showAdd;
                this.formButton = this.formButtonValues[this.showAdd ? 1 : 0];
            },
            populateVacation(key) {
                this.activeModal = key;
                this.vacation = new Vacation(
                    this.vacList[key].id,
                    this.vacList[key].beginDate,
                    this.vacList[key].endDate,
                    this.vacList[key].vacationType,
                    this.vacList[key].user
                );
            },
            update() {
                this.vacList[this.activeModal] = this.vacation;
                this.vacList.push();
                this.vacation = [];
            },
            add(args) {
                this.vacList.push(args);
                this.vacationFormToggle();
            },
            addVacType() {
                if (this.newVacType.description.length > 2) {
                    this.$http.post('http://localhost:8082/vacationTypes', 
                                    JSON.stringify(this.newVacType))
                                    .then(response => {
                                        this.populateVacType();
                                    });
                }
            },
            edVacType() {
                if (this.newVacType.description.length > 2) {
                    this.newVacType.id = this.chosenVT;
                    this.$http.put('http://localhost:8082/vacationTypes/' + this.chosenVT, 
                                    JSON.stringify(this.newVacType))
                                    .then(response => {
                                        for (var k in this.vacTypeList)
                                            if (k.id == this.newVacType.id) {
                                                this.vacTypeList[ind].description = this.newVacType.description;
                                                this.vacTypeList[ind].factor = this.newVacType.factor;
                                                break;
                                            }
                                    });
                }
            },
            delVacType() {
                this.$http.delete('http://localhost:8082/vacationTypes/' + this.chosenVT)
                    .then(response => {
                        this.populateVacType();
                    });
            },
            populateVacType() {
                this.$http.get('http://localhost:8082/vacationTypes')
                    .then(response => {
                        this.vacTypeList = response.body;
                        this.chosenVT = this.vacTypeList[0].id;
                    });
            }
        },
        computed: {
            filteredVacList() {
                this.keyword = this.keyword.toLowerCase();
                function compare(a, b) {
                    if (a.id < b.id)
                        return 1;
                    if (a.id > b.id)
                        return -1;
                  return 0;
                }
                return this.vacList.sort(compare).filter((va) => {
                    switch(this.filterBy) {
                        case 'User': 
                            return va.user.id.toString().toLowerCase().includes(this.keyword);
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
                
            this.$http.get('http://localhost:8082/vacations')
                .then(response => {
                    this.vacList = response.body;
                });
            
            this.populateVacType();
        }
    }
</script>