<template>
<div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 v-if="sendEmail" class="modal-title" id="exampleModalLabel"> <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>寄送信件中</h5>
        <h5 v-else class="modal-title" id="exampleModalLabel">已寄送驗證碼到{{Email}}</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <input v-model="inputNumer" type="text" class="form-control" placeholder="請輸入驗證碼">
          <p class="text-danger mb-0" v-if="Answer">驗證{{Email}}信箱的號碼錯誤</p>
        </div>
      </div>
      <div class="modal-footer">
        <button @click.prevent="hide" type="button" class="btn btn-primary">驗證</button>
        <button  type="button" class="btn btn-danger">關閉</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
/* eslint-disable */
import Modal from 'bootstrap/js/dist/modal'

export default {
  data () {
    return {
      Modal: {},
      Answer: false,
      sendEmail: true,
      oldEmail: '',
      inputNumer: '',
      ConfirmNumber: ''
    }
  },
  props: {
    Email: String
  },
  computed: {
    newEmail () {
      console.log('email是' + this.Email)
      this.checkEmail()
      return this.Email
    }
  },
  methods: {
    show () {
      this.oldEmail = this.newEmail
      this.Modal.show()
    },
    hide () {
      console.log(this.inputNumer, this.ConfirmNumber)
      if (this.inputNumer == this.ConfirmNumber) {
        this.Modal.hide()
        this.$emit('result', '$emit成功')
      } else {
        this.Answer = true
      }
    },
    checkEmail () {
      this.axios({
        method: 'post',
        url: 'http://localhost:8080/checkEmail',
        data: {
          useremail: this.Email
        }
      })
        .then((response) => {
          this.sendEmail = false
          this.ConfirmNumber = response.data
          console.log(response)
          console.log(this.ConfirmNumber)
        })
        .catch((error) => console.log(error))
    }
  },
  mounted () {
    this.Modal = new Modal(document.getElementById('Modal'))
  }
}
</script>
