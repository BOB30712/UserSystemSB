<template>
  <div class="row d-flex justify-content-center">
    <h1 class="text-center">這是使用者資料畫面</h1>
    <div class="col-6">
      <h2 class="mb-3">編號:{{ user.userno }}</h2>
      <h2 class="mb-3">使用者:{{ user.username }}</h2>
      <h2 class="mb-3">帳號:{{ user.accountnumber }}</h2>
      <h2 class="mb-3">密碼:{{ user.password }}</h2>
      <h2 class="mb-3">信箱:{{ user.useremail }}</h2>
      <h2 class="mb-3">註冊日期:{{ user.createddate }}</h2>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      count: 0,
      user: {
        userno: '',
        username: '',
        accountnumber: '',
        password: '',
        useremail: '',
        createddate: ''
      }
    }
  },
  methods: {
    getUserdata () {
      console.log('這是使用者資料')
      this.filters.test()
      this.count = this.filters.getCookie('result')
      console.log('cookie:' + this.count)
      this.axios({
        method: 'post',
        url: 'http://localhost:8080/getResult',
        data: {
          result: this.filters.getCookie('result')
        }
      })
        .then((response) => {
          if (response.data !== null) {
            this.user.userno = response.data.userno
            this.user.username = response.data.username
            this.user.accountnumber = response.data.accountnumber
            this.user.password = response.data.password
            this.user.useremail = response.data.useremail
            this.user.createddate = response.data.createddate
          } else {
            alert('帳號不存在')
          }
          console.log(response)
        })
        .catch((error) => console.log(error))
    }
  },
  mounted () {
    this.getUserdata()
    const account = this.filters.getCookie('result')
    if (account === '') {
      alert('尚未登入帳號')
      this.$router.push('/login')
    }
  }
}
</script>
