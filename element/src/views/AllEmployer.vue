<template>
<div>
    <el-row>
        <el-form :inline="true" :model="seach" class="demo-form-inline">
        <el-form-item label="员工类型">
            <el-select v-model="seach.state" placeholder=" -- ">
                <el-option label="员工" value="/queryAllEmployeeInfo"></el-option>
                <el-option label="经理" value="/queryAllEmployerInfo"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="loaddate">查询</el-button>
        </el-form-item>
        </el-form>
    </el-row>

    <el-table
            :data="page"
            border
            style="width: 100%"
            >
        <el-table-column
            prop="empId"
            label="员工ID"
        >
        </el-table-column>
        <el-table-column
            prop="account"
            label="工号">
        </el-table-column>
        
        <el-table-column
            prop="name"
            label="姓名"
            >
        </el-table-column>
        <el-table-column
            prop="gender"
            label="性别"
            :formatter="formatGender"
            >
        </el-table-column>
        <el-table-column
            prop="phone"
            label="手机号"
            >
        </el-table-column>
        <el-table-column
            prop="role"
            label="职位"
            :formatter="formatRole">
        </el-table-column>
    </el-table>
</div>

</template>

<script>
export default {
    data(){
        return{
            seach:{
                account:'',
                state:'/queryAllEmployeeInfo'
            },
            page:{}
        }
    },
    created(){
        this.loaddate();
    },
    methods:{
        loaddate(){
            this.$http.get(this.seach.state).then(res => {
                console.log(res.data);
                this.page = res.data;
            });
        },
        formatGender(row){
            if(row.gender == 0){
                return '女'
            }else{
                return '男'
            }
        },
        formatRole(row){
            if(row.role == 0){
                return '员工'
            }else if(row.role == 1){
                return '部门经理'
            }else if(row.role == 2){
                return '副总经理'
            }else if(row.role == 3){
                return '总经理'
            }else if(row.role == 4){
                return '人事人员'
            }else if(row.role == 5){
                return '财务人员'
            }
        }
        
    },
}
</script>