<template>
<div>
    <el-row>
        <el-form :inline="true" :model="seach" class="demo-form-inline">
        <el-form-item label="员工ID">
            <el-input v-model="seach.account" placeholder="请输入员工ID"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="loaddate">查询</el-button>
        </el-form-item>
        </el-form>
    </el-row>

    <el-descriptions class="margin-top" :title="page.name"  :column="3"  border>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        empId
      </template>
        <span>{{page.empId}}</span>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        用户名
      </template>
        <span>{{page.name}}</span>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        手机号
      </template>
        <span>{{page.phone}}</span>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-time"></i>
        入职时间
      </template>
        <span>{{formatDate(page.createTime)}}</span>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        角色
      </template>
      <el-tag size="small">{{formatRole(page.role)}}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-message"></i>
        邮箱
      </template>
      {{page.email}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-male"></i>
        性别
      </template>
      {{formatGender(page.gender)}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-loading"></i>
        密码
      </template>
      {{page.password}}
    </el-descriptions-item>
  </el-descriptions>

</div>

</template>

<script>
export default {
    data(){
        return{
            seach:{
                account:'0',
            },
            page:{}
        }
    },
    created(){
        this.loaddate();
    },
    methods:{
        loaddate(){
            this.$http.get('/queryEmpInfoById/'+this.seach.account).then(res => {
                this.page = res.data;
            });
        },
        formatDate(startTime){
            var date = new Date(startTime);
            var year = date.getFullYear();
            var month = date.getMonth()+1;
            var day = date.getDate();
            return year+'-'+month+'-'+day;
        },
        formatRole(role){
            if(role == 0){
                return '员工'
            }else if(role == 1){
                return '部门经理'
            }else if(role == 2){
                return '副总经理'
            }else if(role == 3){
                return '总经理'
            }else if(role == 4){
                return '人事人员'
            }else if(role == 5){
                return '财务人员'
            }
        },
        formatGender(gender){
            if(gender == 0){
                return '女'
            }else{
                return '男'
            }
        },
        
    },
}
</script>