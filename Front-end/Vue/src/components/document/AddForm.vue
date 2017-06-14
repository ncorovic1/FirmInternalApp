<template>
    <form class="form-horizontal" role="form">
        <div id="loginbox" style="" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title text-center">Add Document</div>
                </div>     

                <div style="padding-top:30px" class="panel-body" >
                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>  
                    <form class="form-horizontal" role="form">
                        <div class="alert alert-danger" v-if="error">
                            <p>{{ error }}</p>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-volume-up"></i></span>
                            <textarea type="text" class="form-control" v-model="document.content" placeholder="content" required></textarea>                           
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-barcode"></i></span>
                            <input type="text" class="form-control" v-model="document.title" placeholder="title" required>   
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-tower"></i></span>
                            <select class="form-control" v-model="document.author.id" placeholder="u.id" required> 
                                <option v-for="u in userList" :value="u.id"> {{ u.firstName }} </option>
                            </select>
                        </div>                   

                        <div style="margin-top:10px" class="form-group">
                            <div class="col-sm-12 controls">
                                <a id="btn-add" @click="addDocument" class="btn btn-success btn-block">
                                    <i class="glyphicon glyphicon-plus" style="float:left"></i>
                                    Add Document
                                    <i class="glyphicon glyphicon-plus" style="float:right"></i>
                                </a>
                            </div>
                        </div>
                    </form>     
                </div>                     
            </div>  
        </div>
    </form>   
</template>

<script>
    export default {
        props: [
            'noDocs'
        ],
        data() {
            return {
                error: '',
                document: {
                    id: '',
                    title: '',
                    author: {
                        id: '',
                        firstName: '',
                        lastName: ''
                    },
                    createdAt: '',
                    modifiedAt: '',
                    content: ''
                },
                userList: []
            }
        },
        methods: {
            addDocument() {
                this.document.id = this.noDocs + 1;
                var time = Date.now();
                this.document.createdAt  = time;
                this.document.modifiedAt = time;
                
                var us = this.getUser(this.document.author.id);
                this.document.author.firstName = us.firstName;
                this.document.author.lastName  = us.lastName;
                
                this.$http.post('http://localhost:8084/documents/' + us.id, 
                                JSON.stringify(this.document))
                                .then(response => {
                                    this.$emit('add', this.document);
                                });
            },
            getUser(id) {
                for(var k in this.userList) {
                    if (this.userList[k].id == id) {
                        return this.userList[k];
                    }
                }
            },
        },
        created() {
            this.$http.get('http://localhost:8085/users')
                .then(response => {
                    this.userList = response.body;
                    this.document.author.id = this.userList[0].id;
                });
        }
    }
</script>