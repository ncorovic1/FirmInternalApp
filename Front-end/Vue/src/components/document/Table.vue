<template>
    <div id="table">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <h2 class="text-center">Documents</h2>
                    </div>
                    
                    <div class="row" v-show="admin || hr">
                        <button @click="docFormToggle" class="btn btn-default btn-block"> 
                            <i class="glyphicon glyphicon-plus" style="float:left"></i>
                                {{ formButton }} 
                            <i class="glyphicon glyphicon-plus" style="float:right"></i>
                        </button>
                        <div style="height: 30px;"></div>
                        <app-add v-show="showAdd" :noDocs="docList.length" @add="add($event)"></app-add>
                    </div>

                    <div class="row">    
                        <div class="col-xs-8 col-xs-offset-2">
                            <div class="input-group">
                                <div class="input-group-btn search-panel">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span id="search_concept">Filter by {{ filterBy }}</span> <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li @click="filterBy = 'Author'"><a>Author</a></li>
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
                                    <th>No.         </th>
                                    <th>Title       </th>
                                    <th>Author      </th>                                    
                                    <th>Created At  </th>
                                    <th>Modified At </th>
                                    <th v-show="admin || hr">Edit   </th>
                                    <th v-show="admin || hr">Delete </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(d, key) in filteredDocumentList">
                                    <td>{{ key + 1                                      }}</td>
                                    <td>{{ d.title                                      }}</td>
                                    <td>{{ d.author.firstName + ' ' + d.author.lastName }}</td>          
                                    <td>{{ createDate(d.createdAt)                      }}</td>
                                    <td>{{ createDate(d.modifiedAt)                     }}</td>
                                    <td v-show="admin || hr">
                                        <p data-placement="top" data-toggle="tooltip" title="Edit">
                                            <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" @click="populateDocument(key)"><span class="glyphicon glyphicon-pencil"></span></button>
                                        </p>
                                    </td>
                                    <td v-show="admin || hr">
                                        <p data-placement="top" data-toggle="tooltip" title="Delete">
                                            <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" @click="populateDocument(key)"><span class="glyphicon glyphicon-trash"></span></button>
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
        <app-eddel :document="document" @deleteDocument="docList.splice(activeModal, 1)" @update="update($event)"></app-eddel>
    </div>
</template>

<script>
    import Add        from './AddForm';
    import EditDelete from './EditDeleteModal';
    import Auth       from '../../assets/auth';
    
    class Document {
        constructor(id, c, cat, mat, t, a) {
            this.id          = id;
            this.content     = c;
            this.createdAt   = cat;
            this.modifiedAt  = mat;
            this.title       = t;
            this.author      = a;
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
                formButton: 'Add Document',
                formButtonValues: [
                    'Add Document',
                    'Close Form'
                ],
                filterBy: 'Author',
                keyword: '',
                activeModal: '0',
                admin: true,
                hr: true,
                docList: [],
                document: []
            }
        },
        methods: {
            docFormToggle() {
                this.showAdd = !this.showAdd;
                this.formButton = this.formButtonValues[this.showAdd ? 1 : 0];
            },
            populateDocument(key) {
                this.activeModal = key;
                this.document = new Document(
                     this.docList[key].id,
                     this.docList[key].content,
                     this.docList[key].createdAt,
                     this.docList[key].modifiedAt,
                     this.docList[key].title,
                     this.docList[key].author
                 );
            },
            update(arg) {
                this.docList[this.activeModal] = arg;
                this.docList.push();
                this.document = [];
            },
            add(args) {
                this.docList.push(args);
                this.docFormToggle();
            },
            createDate(d) {
                var time = new Date(d);
                time.setHours(time.getHours() + 2);
                return time.toISOString().substring(0, 19).replace('T', ' ');
            }
        },
        computed: {
            filteredDocumentList() {
                this.keyword = this.keyword.toLowerCase();
                function compare(a, b) {
                    if (a.modifiedAt < b.modifiedAt)
                        return 1;
                    if (a.modifiedAt > b.modifiedAt)
                        return -1;
                  return 0;
                }
                return this.docList.sort(compare).filter((doc) => {
                    switch(this.filterBy) {
                        case 'Author': 
                            var auth = doc.author.firstName + ' ' + doc.author.lastName;
                            return auth.toLowerCase().includes(this.keyword);
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
            
            this.$http.get('http://localhost:8084/documents')
                .then(response => {
                    this.docList = response.body;
                });
        }
    }
</script>