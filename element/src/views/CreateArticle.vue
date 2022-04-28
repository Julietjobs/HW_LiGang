<template>
<el-form ref="form" :model="employer" label-width="80px">
    <el-form-item label="员工姓名">
        <el-input v-model="employer.name"></el-input>
    </el-form-item>
    <el-form-item label="员工工号">
        <el-input v-model="employer.account"></el-input>
    </el-form-item>
    <el-form-item label="员工性别">
        <el-select v-model="employer.gender" placeholder="请选择性别">
            <el-option label="男" value="1"></el-option>
            <el-option label="女" value="0"></el-option>
        </el-select>
    </el-form-item>
    <el-form-item label="员工电话">
        <el-input v-model="employer.phone"></el-input>
    </el-form-item>
    <el-form-item label="员工密码">
        <el-input v-model="employer.password"></el-input>
    </el-form-item>
    <el-form-item label="员工邮件">
        <el-input v-model="employer.email"></el-input>
    </el-form-item>
    <el-form-item label="员工角色">
        <el-select v-model="employer.role" placeholder="请选择员工类型">
            <el-option label="员工" value="0"></el-option>
            <el-option label="部门经理" value="1"></el-option>
            <el-option label="副总经理" value="2"></el-option>
            <el-option label="总经理" value="3"></el-option>
            <el-option label="人事人员" value="4"></el-option>
            <el-option label="财务人员" value="5"></el-option>
        </el-select>
    </el-form-item>
    <el-form-item label="创建时间">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="选择日期" v-model="employer.createTime" style="width: 100%;"></el-date-picker>
    </el-col>
  </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
    </el-form-item>
    </el-form>
</template>
<script>
  export default {
    data() {
      return {
        employer: {}
      }
    },
    methods: {
      onSubmit() {
        this.employer.createTime = this.employer.createTime.toISOString().substring(0,10);
        this.$http.post('insertEmpInfo',this.employer).then(res => {
          console.log(res);
        });
        // this.$http.get('queryAllEmployeeInfo').then(res => {
        //   console.log(res);
        // });
      }
    }
  }
</script>