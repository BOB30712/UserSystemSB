<template>
  <div class="row d-flex justify-content-center">
    <h1 class="text-center">申請新密碼</h1>
    <div class="col-6">
      <form>
        <div class="mb-3">
        <label for="accountinput" class="form-label fw-bold">帳號</label>
        <input v-model="account" type="text" class="form-control" id="accountinput" placeholder="請輸入帳號">
        <p :class="{ 'text-success': isCorrect,'text-danger': !isCorrect }" class="mb-0">{{message}}</p>
      </div>
      <button v-on:click.prevent="checkAccount" type="submit" class="w-100 mt-3 btn btn-outline-primary">
        <span v-if="sendEmail" class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>送出
      </button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      isCorrect: true,
      sendEmail: false,
      message: '',
      account: ''
    }
  },
  methods: {
    checkAccount () {
      // this.isCorrect = !this.isCorrect
      this.sendEmail = true
      this.axios({
        method: 'post',
        url: 'http://localhost:8080/forgotPassword',
        data: {
          accountnumber: this.account
        }
      })
        .then((response) => {
          this.sendEmail = false
          console.log(response)
          if (response.data === '已重新設定密碼') {
            this.isCorrect = true
            this.message = `已寄送新密碼到${this.account}的信箱`
          } else {
            this.isCorrect = false
            this.message = `${this.account}帳號不存在，請重新輸入`
          }
        })
        .catch((error) => {
          this.sendEmail = false
          console.log(error)
        })
    }
  }
}
</script>
