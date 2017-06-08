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
                                <input type="text" class="form-control" v-model="vacType" placeholder="new vacation type" required>     
                                <span class="input-group-addon" @click="addVacType">ADD</span>
                            </div>
                            <select type="text" class="form-control" placeholder="vacation type" required>   
                                <option v-for="vt in vacTypeList" :value="vt.value">{{ vt.text }}</option>
                            </select>
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
                        <app-add v-show="showAdd" :vacation="vacation" :noVacs="vacList.length" @add="add"></app-add>
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
                                    <th>No.           </th>
                                    <th>Begin date    </th>
                                    <th>End date      </th>
                                    <th>Vacation type </th>
                                    <th>User id       </th>
                                    <th v-show="admin || hr">Edit   </th>
                                    <th v-show="admin || hr">Delete </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(v, key) in filteredVacList">
                                    <td>{{ key + 1         }}</td>
                                    <td>{{ v.begin_date    }}</td>
                                    <td>{{ v.end_date      }}</td>
                                    <td>{{ v.vacation_type }}</td>
                                    <td>{{ v.user_id       }}</td>
                                    <td v-show="admin || hr">
                                        <p data-placement="top" data-toggle="tooltip" title="Edit">
                                            <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" @click="populateVacation(key)"><span class="glyphicon glyphicon-pencil"></span></button>
                                        </p>
                                    </td>
                                    <td v-show="admin || hr">
                                        <p data-placement="top" data-toggle="tooltip" title="Delete">
                                            <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" @click="activeModal = key"><span class="glyphicon glyphicon-trash"></span></button>
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
            this.id            = id;
            this.begin_date    = bd;
            this.end_date      = ed;
            this.vacation_type = vt;
            this.user_id       = us;
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
                admin: true,
                hr: true,
                vacList: [
                    new Vacation(
                        '1',
                        '2017-01-01',
                        '2017-01-01',
                        'Remote Work',
                        '1'
                        ),
                    new Vacation(
                        '2',
                        '2017-01-01',
                        '2017-01-01',
                        'Sick Day',
                        '2'
                        )
                ],
                vacation: [],
                vacTypeList: [{
                    value: 'Remote Work',
                    text: 'Remote Work'
                }, {
                    value: 'Bonus Day',
                    text: 'Bonus Day'
                }, {
                    value: 'Regular Vacation',
                    text: 'Regular vacation'
                }, {
                    value: 'Personal Day',
                    text: 'Personal Day'
                }, {
                    value: 'Sick Day',
                    text: 'Sick Day'
                }],
                vacType: ''
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
                                     this.vacList[key].begin_date,
                                     this.vacList[key].end_date,
                                     this.vacList[key].vacation_type,
                                     this.vacList[key].user_id
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
                if (this.vacType.length > 2)
                    this.vacTypeList.unshift({'text': this.vacType, 'value': this.vacType})
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
                            return va.user_id.toLowerCase().includes(this.keyword);
                            break;
                    }
                })
            }
        }
    }
</script>