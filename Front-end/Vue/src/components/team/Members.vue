<template>
    <div class="modal fade" id="members" tabindex="-1" role="dialog" aria-labelledby="members" aria-hidden="true">
        <div class="modal-dialog" id="sad">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                    <h4 class="modal-title custom_align" id="Heading">Members of team {{ team.name }}</h4>
                </div>
                <div class="modal-body">
                    <div style="margin-bottom: 25px" class="input-group">
                        <input type="hidden" :value="trigger">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <select v-if="hasUWMembers" class="form-control" placeholder="handle" v-model="chosenUser" required>
                            <option v-for="(u, key) in usersWM" :value="key">
                                    {{ u.firstName + ' ' + u.lastName }}
                            </option>
                        </select>
                        <select v-else class="form-control" disabled>
                            <option disabled>
                                    {{ 'All users are in this team!' }}
                            </option>
                        </select>
                        <span class="input-group-addon" @click="addMemb()"> ADD </span>
                    </div>
                    <hr>
                    <div v-if="!hasMembers">
                        <input type="text" class="form-control" value="This team has no members yet!" placeholder="noMember" disabled>
                    </div>
                    <div style="margin-bottom: 25px" class="input-group" v-for="(m, key) in members">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" class="form-control" :value="m.firstName + ' ' + m.lastName" placeholder="handle" required>
                        <span class="input-group-addon" @click="removeMemb(key)"> REMOVE </span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-md" data-dismiss="modal"><span class="glyphicon glyphicon-ok-sign"></span>Ok</button>
                </div>
            </div>
        </div>
    </div>
</template>



<script>
    export default {
        props: [
                'teamId'
            ],
        data() {
            return {
                error: '',
                chosenUser: '0',
                termination: 0,
                users: [],
                members: [],
                usersWM: [],
                team: {
                    id: '',
                    handle: '',
                    info: '',
                    name: ''
                },
                alone: {
                    id: '',
                    handle: '',
                    info: '',
                    name: ''
                },
            }
        },
        methods: {
            addMemb() {
                if (this.hasUWMembers) {
                    this.usersWM[this.chosenUser].team = this.team;
                    this.$http.put('http://localhost:8085/users/', 
                                    JSON.stringify(this.usersWM[this.chosenUser]));
                    
                    this.members.push(this.usersWM[this.chosenUser]);
                    this.usersWM.splice(this.chosenUser, 1); 
                    this.chosenUser = '0';
                }
            },
            removeMemb(index) {
                this.members[index].team = this.alone;
                this.$http.put('http://localhost:8085/users/', 
                                JSON.stringify(this.members[index]));
                                
                this.usersWM.push(this.members[index]); 
                this.members.splice(index, 1); 
            },
            getMembers(teamId) {
                this.$http.get('http://localhost:8085/users/byteam/' + teamId)
                    .then(response => {
                        this.members = response.body;
                        this.getUsersWM(); 
                    });
            },
            getUsersWM() {
                this.usersWM = [];
                var indices = [];
                for (var k in this.members) {
                    indices.push(this.members[k].id);
                }
                for (var k in this.users) {
                    if ( !(indices.indexOf(this.users[k].id) > -1) )
                        this.usersWM.push(this.users[k]);
                }
            },
            getTeam(id) {
                this.$http.get('http://localhost:8083/teams/' + id)
                    .then(response => {
                        this.team = response.body;
                    });
            }
        },
        computed: {
            hasUWMembers() {
                return this.usersWM.length > 0 ? true : false;
            },
            hasMembers() {
                return this.members.length > 0 ? true : false;
            },
            trigger() {
                if (this.teamId != 0 && this.teamId != this.termination) {
                    this.termination = this.teamId;
                    this.getMembers(this.teamId);
                    this.getTeam(this.teamId);
                }
                return this.teamId;
            }
        },
        created() {
            this.$http.get('http://localhost:8085/users')
                .then(response => {
                    this.users = response.body;
                });
                
            this.$http.get('http://localhost:8083/teams/byname/Alone')
                .then(response => {
                    this.alone = response.body;
                });
        }
    }
</script>