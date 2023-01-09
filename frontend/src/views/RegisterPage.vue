<template>
  <div class="row d-flex justify-content-center">
    <h1 class="text-center">帳號註冊</h1>
    <div class="col-10">
      <form>
        <div class="mb-3">
          <label for="nameinput" class="form-label fw-bold">使用者名稱</label>
          <input v-model="user.username" type="text" class="form-control" id="nameinput">
        </div>
        <div>
          <label for="accountinput" class="form-label fw-bold">帳號</label>
        </div>
        <div class="input-group mb-3">
          <input :disabled="isAccountCorrect" v-model="user.accountnumber" type="text" class="form-control" id="accountinput" placeholder="請輸入並驗證帳號" aria-label="請輸入並驗證信箱" aria-describedby="account-check">
          <button :disabled="isAccountCorrect" @click.prevent="checkAccount" class="btn btn-outline-primary" type="button" id="account-check">
            <p disabled class="mb-0" v-if="isAccountCorrect"><i class="bi bi-check2-circle"></i></p>
            <p class="mb-0" v-else>CHECK</p>
          </button>
        </div>
        <div class="mb-3">
          <label for="passwordinput" class="form-label fw-bold">密碼</label>
          <input v-model="user.password" type="password" class="form-control" id="passwordinput" placeholder="請輸入密碼">
        </div>
        <div>
          <label for="emailinput" class="form-label fw-bold">信箱</label>
        </div>
        <div class="input-group mb-3">
          <input :disabled="isEmailCorrect" v-model="user.userEmail" type="email" class="form-control" id="emailinput" placeholder="請輸入並驗證信箱" aria-label="請輸入並驗證信箱" aria-describedby="email-check">
          <button :disabled="isEmailCorrect" @click.prevent="opemModal" class="btn btn-outline-primary" type="button" id="email-check">
            <p class="mb-0" v-if="isEmailCorrect"><i class="bi bi-check2-circle"></i></p>
            <p class="mb-0" v-else>CHECK</p>
          </button>
        </div>
      <button :disabled="!isCorrect" @click.prevent="registerUser" type="submit" class="w-100 mt-3 btn btn-outline-primary">{{submit}}</button>
      </form>
    </div>
  </div>
  <emailcheck ref="emailcheck" :Email="user.userEmail" @result="getResult"/>
</template>

<script>
import emailcheck from '@/components/EmailCheck.vue'

export default {
  data () {
    return {
      Modal: {},
      submit: '請輸入資料並驗證帳號與信箱',
      user: {
        username: '',
        accountnumber: '123456',
        password: '',
        userEmail: ''
      },
      isCorrect: false,
      isAccountCorrect: false,
      isEmailCorrect: false
    }
  },
  methods: {
    getResult (data) {
      console.log(data)
      this.isEmailCorrect = true
      this.isCorrect = this.isCorrecttoSubmit()
    },
    opemModal () {
      const Component = this.$refs.emailcheck
      Component.show()
    },
    closeModal () {
      const Component = this.$refs.emailcheck
      Component.hide()
    },
    isCorrecttoSubmit () {
      if (this.isAccountCorrect && this.isEmailCorrect) {
        this.submit = '申請帳號'
        return true
      } else {
        return false
      }
    },
    checkAccount () {
      this.axios({
        method: 'post',
        url: 'http://localhost:8080/checkAccount',
        data: {
          accountnumber: this.user.accountnumber
        }
      })
        .then((response) => {
          if (response.data === '帳號驗證成功') {
            alert('帳號驗證成功')
            this.isAccountCorrect = true
            this.isCorrect = this.isCorrecttoSubmit()
          } else {
            alert('帳號驗證失敗')
          }
          console.log(response)
        })
        .catch((error) => console.log(error))
    },
    registerUser () {
      this.axios({
        method: 'post',
        url: 'http://localhost:8080/Register',
        data: {
          username: this.user.username,
          accountnumber: this.user.accountnumber,
          password: this.user.password,
          useremail: this.user.userEmail
        }
      })
        .then((response) => {
          if (response.data !== '資料有誤') {
            alert('註冊成功')
            document.cookie = 'result=' + response.data
            this.$router.push('/userpage')
          } else {
            alert('資料有誤，請重新輸入')
          }
          console.log(response)
        })
        .catch((error) => console.log(error))
    }
  },
  components: {
    emailcheck
  }
}
</script>
