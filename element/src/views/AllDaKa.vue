<template>
<div>
    <el-row>
        <el-form :inline="true" :model="seach" class="demo-form-inline">
        <el-form-item label="员工工号">
            <el-input v-model="seach.account" placeholder="请输入员工工号"></el-input>
        </el-form-item>
        <el-form-item label="打卡类型">
            <el-select v-model="seach.state" placeholder=" -- ">
                <el-option label=" -- " value=""></el-option>
                <el-option label="上班打卡" value="0"></el-option>
                <el-option label="下班打卡" value="1"></el-option>
                <el-option label="外出打卡" value="2"></el-option>
                <el-option label="返回打卡" value="3"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="search">查询</el-button>
        </el-form-item>
        </el-form>
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

<script>
export default {
    data(){
        return{
            page:[],
            seach:{
                account:'',
                state:''
            }
        }
    },
    created() {
        this.loaddata();
    },
    methods: {
        loaddata() {
            this.$http.get('/queryAllRecords').then(res => {
                console.log(res.data);
                this.page = res.data;
            });
        },
        formatType(row) {
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
        formatDate(row) {
            var date = new Date(row.time);
            var Y = date.getFullYear() + '-';
            var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = date.getMinutes() + ':';
            var s = date.getSeconds();
            return Y + M + D + h + m + s;
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