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
                            <input type="text" class="form-control" v-model="document.title" placeholder="title" required>                                        
                        </div>
                        
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-volume-up"></i></span>
                            <textarea type="text" class="form-control" v-model="document.content" placeholder="content" required></textarea>                           
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning btn-md" @click="updateDocument" data-dismiss="modal"><span class="glyphicon glyphicon-ok-sign"></span>Update</button>
                        <button type="button" class="btn btn-danger btn-md" @click="cancelUpdateDocument" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>Cancel</button>
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
                        <button type="button" class="btn btn-success" data-dismiss="modal" @click="deleteDocument"><span class="glyphicon glyphicon-ok-sign"></span>Yes</button>
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
                'document'
            ],
        data() {
            return {
                error: '',
            }
        },
        methods: {
            cancelUpdateDocument() {
            },
            updateDocument() {
                var time = Date.now()
                this.document.modifiedAt = time;
                
                this.$http.put('http://localhost:8084/documents', 
                                JSON.stringify(this.document));
                
                this.$emit('update', this.document);
            },
            deleteDocument() {
                this.$http.delete('http://localhost:8084/documents/' + this.document.id)
                this.$emit('deleteDocument', event.target.value);
            }
        }
    }
</script>