<template>
    <h1>這是axios頁面</h1>
    <button v-on:click="test">Plus</button>
</template>

<script>
// import axios from 'axios'
export default {
  methods: {
    test () {
      console.log('測試成功')
      this.axios({
        method: 'get',
        url: 'http://localhost:8080/getString'
      })
        .then((response) => console.log(response))
        .catch((error) => console.log(error))
      /*
      axios({
        method: 'get',
        url: 'http://localhost:8080/getString'
      })
        .then((response) => console.log(response))
        .catch((error) => console.log(error))
      */
    }
  }
}
</script>
