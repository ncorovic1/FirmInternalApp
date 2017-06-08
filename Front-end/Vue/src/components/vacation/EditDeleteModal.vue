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
                            <input type="date" class="form-control" v-model="vacation.begin_date" placeholder="begin date" required>                                        
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-volume-up"></i></span>
                            <input type="date" class="form-control" v-model="vacation.end_date" placeholder="end date" required>                         
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-barcode"></i></span>
                            <select type="text" class="form-control" v-model="vacation.vacation_type" placeholder="vacation type" required>   
                                <option v-for="vt in vacTypeList" :value="vt.value">{{ vt.text }}</option>
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
            }
        },
        methods: {
            cancelUpdateVacation() {
            },
            updateVacation() {
                this.$emit('update', event.target.value);
            },
            deleteVacation() {
                this.$emit('deleteVacation', event.target.value);
            }
        }
    }
</script>