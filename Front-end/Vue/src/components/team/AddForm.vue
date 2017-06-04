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
                        
                        <div class="alert alert-danger" v-if="error">
                            <p>{{ error }}</p>
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-volume-up"></i></span>
                            <input type="text" class="form-control" v-model="team.handle" placeholder="handle" required>                           
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                            <input type="text" class="form-control" v-model="team.info" placeholder="info" required>                                        
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-barcode"></i></span>
                            <input type="text" class="form-control" v-model="team.name" placeholder="name" required>                                        
                        </div>

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
            'team',
            'noTeams'
        ],
        data() {
            return {
                error: ''
            }
        },
        methods: {
            addTeam() {
                this.team.id = this.noTeams + 1;
                this.$emit('add', event.target.value);
            }
        }
    }
</script>