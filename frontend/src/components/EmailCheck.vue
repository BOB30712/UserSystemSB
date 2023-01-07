<template>
<div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">信箱驗證</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <input v-model="ConfirmNumber" type="text" class="form-control" placeholder="請輸入驗證碼">
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
import Modal from 'bootstrap/js/dist/modal'

export default {
  data () {
    return {
      Modal: {},
      Answer: false,
      ConfirmNumber: ''
    }
  },
  props: {
    Email: String
  },
  methods: {
    show () {
      this.Modal.show()
    },
    hide () {
      if (this.ConfirmNumber === '2023') {
        this.Modal.hide()
        this.$emit('result', '$emit成功')
      } else {
        this.Answer = true
      }
    }
  },
  mounted () {
    this.Modal = new Modal(document.getElementById('Modal'))
  }
}
</script>
