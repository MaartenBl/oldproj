<template>
    <div class="wrapper">
        <img src="https://i.imgur.com/qeT9zsm.jpg" alt="Picture of Test Area">
        <p>Test Area</p>
    </div>
</template>

<style scoped>

.wrapper{
    position: fixed;
    left: 25%;
    width: 30%;
    margin: 40px 40px;

    background: #fff;

    border: 1px solid black;
    box-shadow: 0 6px 12px #999;
}

img{
    width: 100%;
    height: 28vh;
    object-fit: cover;
    border-bottom: 1px solid black;
}

p{
    text-align: center;
    font-weight: 100;
    font-size: 32px;
    padding: 15px;
    margin: 0;
    color: #000;
}

</style>