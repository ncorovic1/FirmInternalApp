<template>
    <form class="form-horizontal" role="form">
        <div id="loginbox" style="" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title text-center">Add Team</div>
                </div>     

                <div style="padding-top:30px" class="panel-body" >
                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>  
                    <form id="loginform" class="form-horizontal" role="form">
                        
                        <div class="alert alert-danger" v-if="err.status">
                            <p>{{ err.value }}</p>
                        </div>
                        
                        <div v-bind:class="['input-group', errors.has('handle') ? 'has-error' : 'space']">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                            <input type="text" class="form-control" v-model="team.handle" name="handle" v-validate="'required'" placeholder="handle" required>   
                        </div>
                        <span class="help-block" v-show="errors.has('handle') != ''">
                            <strong>{{ errors.first('handle') }}</strong>
                        </span>
                        
                        <div v-bind:class="['input-group', errors.has('info') ? 'has-error' : 'space']">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                            <input type="text" class="form-control" v-model="team.info" name="info" v-validate="'required'" placeholder="info" required>   
                        </div>
                        <span class="help-block" v-show="errors.has('info') != ''">
                            <strong>{{ errors.first('info') }}</strong>
                        </span>
                        
                        <div v-bind:class="['input-group', errors.has('name') ? 'has-error' : 'space']">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                            <input type="text" class="form-control" v-model="team.name" name="name" v-validate="'required'" placeholder="name" required>   
                        </div>
                        <span class="help-block" v-show="errors.has('name') != ''">
                            <strong>{{ errors.first('name') }}</strong>
                        </span>

                        <div style="margin-top:10px" class="form-group">
                            <div class="col-sm-12 controls">
                                <a id="btn-add" @click="addTeam" class="btn btn-success btn-block">
                                    <i class="glyphicon glyphicon-plus" style="float:left"></i>
                                    Add Team
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
            'noTeams'
        ],
        data() {
            return {
                team: {
                    id: '',
                    handle: '',
                    info: '',
                    name: ''
                },
                err: {
                    status: false,
                    value: 'Empty fields are not allowed!'
                }
            }
        },
        methods: {
            addTeam() {
                if( this.errors.errors == '' ) {
                    if( this.team.handle == '' || this.team.info == '' || this.team.name == '' ) {
                        this.err.status = true;
                        return;
                    }
                    else {
                        this.$http.post('http://localhost:8083/teams', 
                                        JSON.stringify(this.team)
                                        ).then(response => {
                                    });
                            
                        this.$http.get('http://localhost:8083/teams/byname/' + this.team.name)
                                    .then(response => {
                                        this.team.id = response.body.id;
                                    });
                        this.$emit('add', this.team);
                    }
                }
                
            }
        }
    }
</script>

<style>
    .space {
        margin-bottom: 25px;
    }
</style>