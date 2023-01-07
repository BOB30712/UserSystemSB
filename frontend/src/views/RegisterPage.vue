<template>
  <div class="row d-flex justify-content-center">
    <h1 class="text-center">帳號註冊</h1>
    <div class="col-10">
      <form>
        <div class="mb-3">
          <label for="nameinput" class="form-label fw-bold">使用者名稱</label>
          <input type="text" class="form-control" id="nameinput">
        </div>
        <div>
          <label for="accountinput" class="form-label fw-bold">帳號</label>
        </div>
        <div class="input-group mb-3">
          <input type="text" class="form-control" id="accountinput" placeholder="請輸入並驗證帳號" aria-label="請輸入並驗證信箱" aria-describedby="account-check">
          <button class="btn btn-outline-primary" type="button" id="account-check">CHECK</button>
        </div>
        <div class="mb-3">
          <label for="passwordinput" class="form-label fw-bold">密碼</label>
          <input type="password" class="form-control" id="passwordinput" placeholder="請輸入密碼">
        </div>
        <div>
          <label for="emailinput" class="form-label fw-bold">信箱</label>
        </div>
        <div class="input-group mb-3">
          <input v-model="userEmail" type="email" class="form-control" id="emailinput" placeholder="請輸入並驗證信箱" aria-label="請輸入並驗證信箱" aria-describedby="email-check">
          <button @click.prevent="opemModal" class="btn btn-outline-primary" type="button" id="email-check">CHECK</button>
        </div>
      <button type="submit" class="w-100 mt-3 btn btn-outline-primary">申請帳號</button>
      </form>
    </div>
  </div>
  <emailcheck ref="emailcheck" :Email="userEmail" @result="getResult"/>
</template>

<script>
import emailcheck from '@/components/EmailCheck.vue'

export default {
  data () {
    return {
      Modal: {},
      userEmail: ''
    }
  },
  methods: {
    getResult (data) {
      console.log(data)
    },
    opemModal () {
      const Component = this.$refs.emailcheck
      Component.show()
    },
    closeModal () {
      const Component = this.$refs.emailcheck
      Component.hide()
    }
  },
  components: {
    emailcheck
  }
}
</script>
