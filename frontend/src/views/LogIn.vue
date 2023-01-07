<template>
  <div class="row d-flex justify-content-center">
    <h1 class="text-center">這是使用者登入畫面</h1>
    <div class="col-6">
      <form>
        <div class="mb-3">
        <label for="accountinput" class="form-label fw-bold">帳號</label>
        <input v-model="user.account" type="text" class="form-control" id="accountinput" placeholder="請輸入帳號">
      </div>
      <div class="mb-3">
        <label for="passwordinput" class="form-label fw-bold">密碼</label>
        <input v-model="user.password" type="password" class="form-control" id="passwordinput" placeholder="請輸入密碼">
      </div>
      <button v-on:click.prevent="login" type="submit" class="w-100 mt-3 btn btn-outline-primary">登入</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      count: 0,
      user: {
        account: '',
        password: ''
      }
    }
  },
  methods: {
    login () {
      console.log('測試成功')
      console.log('次數:' + this.count)
      console.log('帳號:' + this.user.account)
      console.log('密碼:' + this.user.password)
      // this.count++
      // document.cookie = 'result=' + this.count
      this.axios({
        method: 'post',
        url: 'http://localhost:8080/login',
        data: {
          accountnumber: this.user.account,
          password: this.user.password
        }
      })
        .then((response) => {
          if (response.data !== '資料有誤') {
            document.cookie = 'result=' + response.data
            this.$router.push('/userpage')
          } else {
            alert('帳號不存在')
          }
          console.log(response)
        })
        .catch((error) => console.log(error))
    }
  },
  mounted () {
    const account = this.filters.getCookie('result')
    if (account !== '') {
      this.$router.push('/userpage')
    }
  }
}
</script>
