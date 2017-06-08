<template>
    <form class="form-horizontal" role="form">
        <div id="loginbox" style="" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title text-center">Add Vacation</div>
                </div>     

                <div style="padding-top:30px" class="panel-body" >
                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>  
                    <form class="form-horizontal" role="form">
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
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-tower"></i></span>
                            <select class="form-control" v-model="vacation.user_id" placeholder="user id" required> 
                                <option v-for="n in noVacs" :value="n"> {{ n }} </option>
                            </select>
                        </div>

                        <div style="margin-top:10px" class="form-group">
                            <div class="col-sm-12 controls">
                                <a id="btn-add" @click="addVacation" class="btn btn-success btn-block">
                                    <i class="glyphicon glyphicon-plus" style="float:left"></i>
                                    Add Vacation
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
            'noVacs'
        ],
        data() {
            return {
                error: '',
                vacation: {
                    id: '',
                    begin_date: '',
                    end_date: '',
                    vacation_type: 'Remote Work',
                    user_id: '1'
                },
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
            addVacation() {
                this.vacation.id = this.noVacs + 1;
                this.$emit('add', this.vacation);
            }
        }
    }
</script>