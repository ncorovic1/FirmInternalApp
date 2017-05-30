<template>
    <div>
        <h1> {{ message }} </h1>
        <app-input :msg="message" @messageChanged="message = $event"></app-input>
    </div>
</template>

<script>
    import Input from './input';
    
    export default {
        components: {
            'app-input': Input
            },
        data() {
            return {
                message: 'This is a message!'
            }
        },
        loginRequest(event) {
            this.$http.get('http://localhost:8085/users/login', (data) => {
            this.quote = data;
          })
          .error((err) => console.log(err))
            console.log(this.user);
        }
    }
</script>