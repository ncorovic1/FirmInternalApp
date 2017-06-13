<template>
    <div id="modal">
        <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                        <h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
                    </div>
                    <div class="modal-body">
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
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning btn-md" @click="updateTeam" data-dismiss="modal"><span class="glyphicon glyphicon-ok-sign"></span>Update</button>
                        <button type="button" class="btn btn-danger btn-md" @click="cancelUpdateTeam" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>Cancel</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                        <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>
                    </div>
                    <div class="modal-footer ">
                        <button type="button" class="btn btn-success" data-dismiss="modal" @click="deleteTeam"><span class="glyphicon glyphicon-ok-sign"></span>Yes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>No</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: [
                'team'
            ],
        data() {
            return {
                error: '',
                members: [],
                alone: {
                    id: '',
                    handle: '',
                    info: '',
                    name: ''
                }
            }
        },
        methods: {
            cancelUpdateTeam() {
            },
            updateTeam() {
                this.$http.put('http://localhost:8083/teams/' + this.team.id, 
                                JSON.stringify(this.team));
                this.$emit('update', this.team);
            },
            deleteTeam() {
                this.$http.get('http://localhost:8085/users/byteam/' + this.team.id)
                        .then(response => {
                            this.members = response.body;
                            this.deleteMembers();
                        });
                
                this.$emit('deleteTeam', event.target.value);
            },
            deleteMembers() {
                for (var k in this.members) {
                    this.members[k].team = this.alone;
                    this.$http.put('http://localhost:8085/users/', 
                                    JSON.stringify(this.members[k]));
                }
                while(this.members[this.members.length - 1].team.id == this.team.id) {}
                this.$http.delete('http://localhost:8083/teams/' + this.team.id);
            }
        },
        created() {
            this.$http.get('http://localhost:8083/teams/byname/Alone')
                .then(response => {
                    this.alone = response.body;
                });
        }
    }
</script>