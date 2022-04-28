<template>
<div>
    <el-table
            :data="page"
            border
            style="width: 100%"
            :default-sort = "{prop: 'time', order: 'descending'}"
            >
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
            prop="state"
            label="状态"
            :formatter="formatState"
            >
        </el-table-column>
    </el-table>
</div>
    
   
</template>

<style >
</style>

<script>
export default {
     data(){
          return{
            page:[]
          }
      },
      created(){
        this.loaddata();
      },
      methods:{
        loaddata(){
            var account = window.sessionStorage.getItem('account');
            this.$http.get('/queryEmpState?account=').then(res => {
                console.log(res.data);
                this.page = res.data;
            });
        },
        formatType(row){
            if (row.type == 0) {
                return '上班打卡';
            } else if (row.type == 1) {
                return '下班打卡';
            } else if (row.type == 2) {
                return '外出打卡';
            } else if (row.type == 3) {
                return '返回打卡';
            }
        },
        /*
    * 0：下班
    * 1：在班
    * 2：外出
    * 3：假期
    * 4：请假
    * */
        formatState(row){
            if (row.state == 0) {
                return '下班';
            } else if (row.state == 1) {
                return '在班';
            } else if (row.state == 2) {
                return '外出';
            } else if (row.state == 3) {
                return '假期';
            } else if (row.state == 4) {
                return '请假';
            }
        }

      }
 }
  </script>
  