<template>
<el-form ref="apply" :model="apply" label-width="100px">
    <el-form-item label="请假开始时间">
      <el-col :span="10">
      <el-date-picker type="date" placeholder="选择日期" v-model="apply.startTime" style="width: 100%;"></el-date-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="请假天数">
        <el-input v-model="apply.duringTime"></el-input>
    </el-form-item>
    <el-form-item label="请假原因">
        <el-input v-model="apply.reason"></el-input>
    </el-form-item>
    <el-form-item label="请假类型">
        <el-select v-model="apply.type" placeholder="请选择类型">
            <el-option label="事假" value="0"></el-option>
            <el-option label="年假" value="1"></el-option>
            <el-option label="婚假" value="2"></el-option>
            <el-option label="产检" value="3"></el-option>
            <el-option label="产假" value="4"></el-option>
            <el-option label="哺乳" value="5"></el-option>
            <el-option label="陪产" value="6"></el-option>
            <el-option label="外出" value="7"></el-option>
        </el-select>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button @click="resetForm">重置</el-button>
    </el-form-item>
    </el-form>
</template>
<script>
  export default {
    data() {
      return {
        apply: {}
      }
    },
    created() {
      if(this.$route.query.type!=null){
        this.apply.type = this.$route.query.type;
      }
    },
    
    methods: {
      onSubmit() {
        
        var empId = window.sessionStorage.getItem('empId');
        this.apply.startTime = this.apply.startTime.toISOString().substring(0,10) +" "+ this.apply.startTime.toISOString().substring(11,19);
        console.log('applyVacation/'+empId+'/'+this.apply.startTime+'/'+this.apply.duringTime+'/'+this.apply.reason+'/'+this.apply.type);
        this.$http.post('applyVacation/'+empId+'/'+this.apply.startTime+'/'+this.apply.duringTime+'/'+this.apply.reason+'/'+this.apply.type).then(res => {
          if (res.data) {
            this.$message({
              message: '添加成功',
              type: 'success'
            });
            this.$router.push('/blogDetail');
          } else {
            this.$message({
              message: '添加失败',
              type: 'error'
            });
          }
        });
        // this.$http.get('queryAllEmployeeInfo').then(res => {
        //   console.log(res);
        // });
      },
      resetForm() {
        this.apply={};
      }
    }
  }
</script>