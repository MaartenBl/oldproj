<template>
    <div class="side_bar">
        <h1 class="rider_name">Rider X</h1>
        <hr>
        <ul>
            <router-link to="/Home">
                <li>Main Dashboard</li>
            </router-link>

            <router-link to="/Record">
                <li>Record a Run</li>
            </router-link>

            <router-link to="/Archive">
                <li>Archive</li>
            </router-link>
        </ul>
        <router-link to="/Login">
            <p>Log Out</p>
        </router-link>
    </div>
</template>

<script>
export default {
}
</script>

<style scoped>

*{
    color: black;
    text-decoration: none;
}

.side_bar{
    position: fixed;
    top: 10;
    left: 0;
    width : 20%;
    height: 100%;
    background: #fff;
    padding: 6px 28px;
    border-right: 1px solid black;
    box-shadow: 0 6px 12px #999;
}
ul{
    padding: 0;
    list-style: none;
    font-weight: 100;
    font-size: 32px;
}
li{
    padding: 5px 0;
}
p{ 
    position: fixed;
    left: 28px;
    bottom: 0;
    font-weight: 100;
    font-size: 32px;
}
</style>