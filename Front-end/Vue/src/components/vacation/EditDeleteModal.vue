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
                            <span class="input-group-addon"><i class="glyphicon glyphicon-barcode"></i></span>
                            <input type="date" class="form-control" v-model="vacation.beginDate" placeholder="begin date" required>                                        
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-volume-up"></i></span>
                            <input type="date" class="form-control" v-model="vacation.endDate" placeholder="end date" required>                         
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-barcode"></i></span>
                            <select type="text" class="form-control" id="vacTypeId" v-model="vacTypeId" placeholder="vacation type" required>   
                                <option v-for="vt in vacTypeList" :value="vt.id">
                                    {{ vt.description + ' - [' + vt.factor +']'}}
                                </option>                            
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning btn-md" @click="updateVacation" data-dismiss="modal"><span class="glyphicon glyphicon-ok-sign"></span>Update</button>
                        <button type="button" class="btn btn-danger btn-md" @click="cancelUpdateVacation" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>Cancel</button>
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
                        <button type="button" class="btn btn-success" data-dismiss="modal" @click="deleteVacation"><span class="glyphicon glyphicon-ok-sign"></span>Yes</button>
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
                'vacation'
            ],
        data() {
            return {
                error: '',
                vacTypeList: [],
            }
        },
        methods: {
            cancelUpdateVacation() {
            },
            updateVacation() {
                var e = document.getElementById('vacTypeId');
                var f = e.options[e.selectedIndex];
                
                this.vacation.vacationType.id = f.value;
                this.vacation.vacationType.description = f.text.split('-')[0]; 
                this.vacation.vacationType.factor = f.text.split('-')[1].split('[')[1].split(']')[0];
                alert(JSON.stringify(this.vacation));
                this.$http.put('http://localhost:8082/vacations/' + this.vacation.id, 
                                JSON.stringify(this.vacation));
                
                this.$emit('update', event.target.value);
            },
            deleteVacation() {
                this.$http.delete('http://localhost:8082/vacations/' + this.vacation.id)
                this.$emit('deleteVacation', event.target.value);
            }
        },
        computed: {
            vacTypeId() {
                return this.vacation.vacationType != null ? this.vacation.vacationType.id : this.vacTypeList[0];
            }
        },
        created() {
            this.$http.get('http://localhost:8082/vacationTypes')
                .then(response => {
                    this.vacTypeList = response.body;
                });
        }
    }
</script>