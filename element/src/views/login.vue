<template>
  <el-form class="login-container" label-position="left"
           label-width="0px" >
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">登录</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  export default{
    data(){
      return {
       loginForm:{
         username:'',
         password:''
       }
      }
    },
    methods: {
      submitClick: function () {
        var _this = this;
        if (_this.loginForm.username == ''||_this.loginForm.password == '') {
          this.$alert('账号或密码不能为空', '提示', {
            confirmButtonText: '确定',
          });
        }else{
          _this.$http.get('/login')
          _this.$http.get('/login/'+_this.loginForm.username+'/'+_this.loginForm.password).then(function (response) {
            console.log(response);
            if (response.data!='') {
              _this.$alert('登录成功', '提示', {
                confirmButtonText: '确定',
              });
              window.sessionStorage.setItem('empId',response.data.empId);
              window.sessionStorage.setItem('account',response.data.account);
              window.sessionStorage.setItem('name',response.data.name);
              window.sessionStorage.setItem('role',response.data.role); 
              _this.$router.replace({path: '/home'});   
            }else{
              _this.$alert('账号或密码错误', '提示', {
                confirmButtonText: '确定',
              });
            }
          });
        }
      }
    }
  }
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
