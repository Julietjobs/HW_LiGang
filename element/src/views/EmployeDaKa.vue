<template>
<div>
    <el-row>
        <el-col :span='10' >员工状态 {{state}} </el-col>
        <el-col :span="12" style="margin-bottom: 10px">
            <el-button type="primary" @click="changeState(0)" :disabled='isHere'>上班打卡</el-button>
            <el-button type="success" @click="changeState(1)" :disabled='!isHere'>下班打卡</el-button>
            <el-button type="danger" @click="changeState(2)" :disabled='!isHere'>外出打卡</el-button>
            <el-button type="warning" @click="changeState(3)" :disabled='isHere'>返回打卡</el-button>
        </el-col>
    </el-row>
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
            prop="time"
            label="打卡时间"
            sortable
            :formatter="formatDate"
            >
        </el-table-column>
        <el-table-column
            prop="type"
            label="打卡类型"
            :formatter="formatType"
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
            page:[],
            state:' ',
            isHere:false
          }
      },
      created(){
        this.loaddata();
        this.loadstate();
      },
      methods:{
        loaddata(){
            var account = window.sessionStorage.getItem('account');
            this.$http.get('/queryRecordsByActOrType?account='+account).then(res => {
                this.page = res.data;
            });
        },
        loadstate(){
            var empId = window.sessionStorage.getItem('empId');
            this.$http.get('/queryEmpStateById/'+empId).then(res => {
                if (res.data==0){
                    this.state = '下班';
                    this.isHere = false;
                }else if (res.data==1){
                    this.state = '在班';
                    this.isHere = true;
                }else if (res.data==2){
                    this.state = '外出';
                    this.isHere = false;
                }else if (res.data==3){
                    this.state = '假期';
                    this.isHere = false;
                }else if (res.data==4){
                    this.state = '请假';
                    this.isHere = false;
                }
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
        formatDate(row){
            var date = new Date(row.time);
            var year = date.getFullYear();
            var month = date.getMonth()+1;
            var day = date.getDate();
            var hour = date.getHours();
            hour = hour < 10 ? "0" + hour : hour;
            var minute = date.getMinutes();
            minute = minute < 10 ? "0" + minute : minute;
            var second = date.getSeconds();
            second = second < 10 ? "0" + second : second;
            return year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second;
        },
         changeState(num){
            var empId = window.sessionStorage.getItem('empId');
            this.$http.post('/checkInOrOut/'+empId+'/'+num).then(res => {
                if (res.data) {
                    this.$message({
                        message: '操作成功',
                        type: 'success'
                    });
                    this.loaddata();
                    this.loadstate();
                    if(num == 0){
                        this.isHere = true;}
                    else if(num == 1){
                        this.isHere = false;}
                    else if(num == 2){
                        this.isHere = false;}
                    else if(num == 3){
                        this.isHere = true;}
                } else {
                    this.$message({
                        message: '操作失败',
                        type: 'error'
                    });
                }
            });
        },

      }
 }
  </script>
  