# UserSystemSB
1.後端:使用spring boot 框架     
2.前端:使用Vue、React、Angular三個選擇其中一個       
3.使用 RESTful 風格設計 API由客戶端(clicent side)發送要求(request)或要求資源(resource)，並依照http協定傳送到伺服器端來接受要求並回應資源(response)      

## 使用技術(前端)
● vue   
● vue-router   
● axios   
● bootstrap  

## 使用技術(後端)
● spring boot  
● Spring Data JPA  
● thymeleaf  
● JavaMailSender    
● mysql  

## 過程中遇到的問題
1.因前後端伺服器位置不同，因此所有的前端請求，到後端都會有問題。    
解決方式:在後端的controller類別中標住@CrossOrigin。

2.直接在後端傳送cookie或session到前端，會有跨域問題，因此無法儲存使用者資料。    
解決方式(暫時):前端發送請求(post)後，將使用者帳號以明文方式儲存到cookie，需要取得資料時再次發送請求(post)並將cookie的值取出放入data。

## 前端框架(VUE)筆記
1.事件綁定
●v-on:click="事件名稱"--->當元件被點擊('click')時，觸發事件。         
●v-model="資料"--->透過v-model可以雙向綁定HTML的標籤與JS的資料
{{資料}}--->將JS的'資料'放在{{}}之中，可以單向輸出到HTML的標籤上。       
2.父元件與子元件之間的溝通
●props--->父元件向子元件傳遞訊息        

●$emit--->子元件向父元件傳遞訊息     

3.Vue全域註冊(Vue3)
app.config.globalProperties.filters = {     
  test,      
  getCookie       
}     
呼叫        
this.filters.test       
this.filters.getCookie        

## JavaScript筆記
export--->JavaScript模組管理的方法，將檔案視作獨立的模組匯出     
import--->匯入JavaScript模組     
例如:     
export function test(){}     
export function getCookie(){}     
import { test, getCookie } from './   method/filters'    



