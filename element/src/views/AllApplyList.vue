<template>
<div>
    <el-row>
        <el-form :inline="true" :model="seach" class="demo-form-inline">
        <el-form-item label="员工ID">
            <el-input v-model="seach.account" placeholder="请输入员工ID"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="loaddata">查询</el-button>
        </el-form-item>
        </el-form>
    </el-row>

    <h3>正在审核:</h3>
 <el-table
            :data="page1"
            border
            style="width: 100%"
            :row-class-name="tableRowClassName">
        <el-table-column
            prop="applyId"
            label="申请序号">
        </el-table-column>
        <el-table-column
            prop="name"
            label="用户名"
        >
        </el-table-column>
        <el-table-column
            prop="account"
            label="工号">
        </el-table-column>
        
        <el-table-column
            prop="duringTime"
            label="请假天数">
        </el-table-column>
        <el-table-column
            prop="state"
            label="请假状态"
            :formatter="formatState"
            >
        </el-table-column>
        <el-table-column
            prop="type"
            label="请假类型"
            :formatter="formatType"
            >
        </el-table-column>
    </el-table>
    <h3>已处理:</h3>
   <el-table
            :data="page"
            border
            style="width: 100%"
            :row-class-name="tableRowClassName">
        <el-table-column
            prop="applyRecordId"
            label="申请序号">
        </el-table-column>
        <el-table-column
            prop="name"
            label="用户名"
        >
        </el-table-column>
        <el-table-column
            prop="account"
            label="工号">
        </el-table-column>
        
        <el-table-column
            prop="duringTime"
            label="请假天数">
        </el-table-column>
        <el-table-column
            prop="state"
            label="请假状态"
            :formatter="formatState"
            >
        </el-table-column>
        <el-table-column
            prop="type"
            label="请假类型"
            :formatter="formatType"
            >
        </el-table-column>
    </el-table>
</div>

</template>

<style >
.el-table .warning-row {
    background: rgb(243, 172, 170);
  }

.el-table .loading-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>

<script>
export default {
     data(){
          return{
                page:[],
                page1: [],
                seach:{
                    account:'',
            }
          }
      },
      created(){
        //this.loaddata();
      },
      methods:{
        tableRowClassName({row, rowIndex}) {
        if (row.state === 0) {
          return 'warning-row';
        } else if (row.state === 2) {
          return 'success-row';
        }else{
            return 'loading-row';
        }
        },
        loaddata(){
            //var empId = window.sessionStorage.getItem('empId');
            this.$http.get('/getApplyRecordById/'+this.seach.account).then(res => {
                this.page = res.data;
            });
            this.$http.get('/queryApplyingById/'+this.seach.account).then(res => {
                this.page1 = res.data;
            });
            
        },
        formatState(row){
            if (row.state == 0) {
                return '拒绝';
            } else if (row.state == 1) {
                return '审核中';
            } else if (row.state == 2) {
                return '通过';
            }
        },
        formatType(row){
            if (row.type == 0) {
                return '事假';
            } else if (row.type == 1) {
                return '年假';
            } else if (row.type == 2) {
                return '婚假';
            } else if (row.type == 3) {
                return '产检';
            } else if (row.type == 4) {
                return '产假';
            } else if (row.type == 5) {
                return '哺乳';
            } else if (row.type == 6) {
                return '陪产';
            } else if (row.type == 7) {
                return '外出';
            } 
        },
        search() {
            this.$http.get('/queryRecordsByActOrType?account='+this.seach.account+'&type='+this.seach.state).then(res => {
                console.log(res.data);
                this.page = res.data;
            });
        }
      }
 }
  </script>
  