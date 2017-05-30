<template>
    <div id="app">
        <nav class="navbar navbar-default">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li><router-link to="/">      Message </router-link> </li>
                    <li><router-link to="/login"> Login   </router-link> </li>
                    <li><router-link to="/hello"> Hello   </router-link> </li>
                </ul>
            </div>    
        </nav>
        <!-- Router Link so index.html is not rendered every time and app state is not lost -->
        <!-- use :to if parametar is variable and not full route -->
        <div class="container">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>

export default {
  data() {
    return {
    
    }
  }
}
</script>

<style>
</style>
