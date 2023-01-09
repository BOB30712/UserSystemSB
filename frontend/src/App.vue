<template>
  <div class="container">
  <nav class="text-center">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/login">登入</router-link> |
    <router-link to="/registerpage">註冊</router-link> |
    <router-link to="/forgotpassword">忘記密碼</router-link> |
    <router-link to="/userpage">使用者</router-link> |
    <router-link to="/axiospage">AxiosPage</router-link> |
  </nav>
  <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
