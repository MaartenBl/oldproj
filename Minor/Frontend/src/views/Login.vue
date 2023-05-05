<template>

<div class="col-sm-3 col-centered wrapper">
    <h1>Login</h1>
    <router-link to="/Register">
       <p>Don't have an account yet</p>
    </router-link>
    <form action="">
        <input type="text" placeholder="Email/Username">
        <input type="password" placeholder="Password">
        <router-link to="/Home">
            <button type="submit">Submit</button>
        </router-link>
    </form>
</div>
  
</template>

<style scoped>

*{
    color: black;
    text-decoration: none;
}

.wrapper{
  margin: auto;
  margin-top: 150px;
  background: #fff;
  border: 1px solid black;
  border-radius: 10px;
  box-shadow: 0 3px 12px #999;
  overflow: hidden;
}

h1{
    padding: 20px 0;
    text-align: center;
    margin: auto;
    font-weight: 400;
    font-size: 32px;
}

p{
    text-align: center;
    font-weight: 100;
}

form input{
    width: 80%;
    margin: 10px 10%;
    margin-bottom: 10px;
    padding: 5px;
}

form button{
  width: 80%;
  padding: 10px;
  margin: 10px 10%;
  margin-bottom: 50px;

  color: white;
  background: #00887A;
  border: none;
  border-radius: 5px;
}

</style>