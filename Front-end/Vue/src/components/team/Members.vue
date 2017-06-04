<template>
    <div class="modal fade" id="members" tabindex="-1" role="dialog" aria-labelledby="members" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                    <h4 class="modal-title custom_align" id="Heading">Members of team {{ team.name }}</h4>
                </div>
                <div class="modal-body">
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <select type="text" class="form-control" placeholder="handle" v-model="chosenUser" required>
                            <option v-for="(u, key) in usersWithoutMembers" :value="key">
                                    {{ u.firstname + ' ' + u.lastname }}
                            </option>
                        </select>
                        <span class="input-group-addon" @click="addMemb()"> ADD </span>
                    </div>
                    <hr>
                    <div style="margin-bottom: 25px" class="input-group" v-for="(m, key) in members">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" class="form-control" :value="m.firstname + ' ' + m.lastname" placeholder="handle" required>
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
                'team'
            ],
        data() {
            return {
                error: '',
                chosenUser: '',
                users: [
                    {
                        firstname: 'Zino',
                        lastname: 'Corovic'
                    },
                    {
                        firstname: 'Lino',
                        lastname: 'Corovic'
                    },
                    {
                        firstname: 'Pino',
                        lastname: 'Corovic'
                    }
                ],
                members: [
                    {
                        firstname: 'Nino',
                        lastname: 'Corovic'
                    },
                    {
                        firstname: 'Dino',
                        lastname: 'Corovic'
                    },
                    {
                        firstname: 'Kino',
                        lastname: 'Corovic'
                    }
                ]
            }
        },
        methods: {
            addMemb() {
                if(this.chosenUser)
                    this.members.push(this.users[this.chosenUser]);
            },
            removeMemb(index) {
                this.users.push(this.members[index]); 
                this.members.splice(index, 1); 
            }
        },
        computed: {
            usersWithoutMembers() {
                return this.users;
            }
        }
    }
</script>