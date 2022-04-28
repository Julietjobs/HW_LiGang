<template>
  <el-container >
  <el-header style="font-size: 12px;background-color:#545c64;border-radius: 12px;">
    <el-row type="flex" justify="space-between">
      <el-col :span="10" style="font-size: 20px;color: #ffd04b">没用一点模板前后端全靠手敲的公司考勤管理系统</el-col>
      <el-col :span="6"></el-col>
      <el-col :span="2">
        <el-dropdown @command="handleCommand">
          <el-button style="border-radius: 15px;" >
            {{name}}<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
    
    </el-header>

  <el-container style="height: 100vh; border-radius:12px;background-color:#f0f2f5;">
    <el-aside width="250px" style="border: 2px solid #ffd04b;background-color:#545c64;border-radius:12px;">
    <el-menu 
      router 
      :default-openeds="[activeIndex]"
      unique-opened
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      @open="handleOpen"
      >
        <!-- <el-submenu index="1">
          <template slot="title"><i class="el-icon-notebook-1"></i>内容管理</template>
          <el-menu-item index="/blogDetail">文章发布</el-menu-item>
          <el-menu-item index="/test">测试</el-menu-item>
        </el-submenu> -->
        <el-submenu index="3">
          <template slot="title">我的</template>
          <el-menu-item index="/employer/MyInfo">个人信息</el-menu-item>
          <el-menu-item index="/employer/CheckLeft">查看剩余假期</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">打卡</template>
          <el-menu-item index="/DaKa/EmployeDaKa">打卡&打卡记录</el-menu-item>
        </el-submenu>
        <el-submenu index="5">
          <template slot="title">请假及审核</template>
          <el-menu-item index="/apply/addApply">添加申请</el-menu-item>
          <el-menu-item index="/apply/applyList">请假记录</el-menu-item>
          <el-menu-item index="/apply/ManageApply" :disabled="ismanager">部门经理审核申请</el-menu-item>
          <el-menu-item index="/apply/ManageApplyX" :disabled="ismanager">总经理审核申请</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">人事</template>
          <el-menu-item index="/employer/AddEmployer" :disabled="isrenshi">添加员工</el-menu-item>
          <el-menu-item index="/employer/AllEmployeState" :disabled="isrenshi">员工状态</el-menu-item>
          <el-menu-item index="/employer/EmployerInfo" :disabled="isrenshi">员工详情</el-menu-item>
          <el-menu-item index="/employer/AllEmployer" :disabled="isrenshi">查询所有人员</el-menu-item>
        </el-submenu>
        <el-submenu index="6">
          <template slot="title">财务</template>
          <el-menu-item index="/DaKa/AllDaKa" :disabled="iscaiwu">所有打卡记录</el-menu-item>
          <el-menu-item index="/apply/AllApplyList" :disabled="iscaiwu">所有请假申请</el-menu-item>
        </el-submenu>
    </el-menu>
  </el-aside>
  
    
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</el-container>
</template>

<style>
body,html{
  padding: 0;
  margin: 0;
}
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
</style>

<script>
  export default {
    data() {
      return {
        name : '',
        ismanager : false,
        isrenshi : false,
        iscaiwu: false,
        isCollapse: false,
        activeIndex: '3',
      }
    },
    created() {
      if (window.sessionStorage.getItem('empId') == null) {
        this.$router.replace({path: '/login'});
      }else {
        this.name = window.sessionStorage.getItem('name');
      }
      
      if (window.sessionStorage.getItem('role') == 1 || window.sessionStorage.getItem('role') == 2 ||window.sessionStorage.getItem('role') == 3  ) {
        this.ismanager = false;
        this.isrenshi = false;
        this.iscaiwu = false;
      }else if (window.sessionStorage.getItem('role') == 4 ) {
        this.isrenshi = false;
        this.ismanager = true;
        this.iscaiwu = true;
      }else if (window.sessionStorage.getItem('role') == 5) {
        this.ismanager = true;
        this.isrenshi = true;
        this.iscaiwu = false;
      }else {
        this.ismanager = true;
        this.isrenshi = true;
        this.iscaiwu = true;
      }
    },
    methods:{
      handleCommand(command) {
        if (command === 'logout') {
          window.sessionStorage.removeItem('empId');
          window.sessionStorage.removeItem('name');
          this.$router.replace({path: '/login'});
        }
      },
      handleOpen(key, keyPath) {
        this.activeIndex = key;
      },
    }
  };
</script>