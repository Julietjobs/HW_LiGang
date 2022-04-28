<template>
    <el-row>
  <el-col :sm="12" :lg="6">
    <el-result :icon="page.leftBuRu>10?'success':'warning'" :title='page.leftBuRu+"天"' subTitle="剩余哺乳假">
      <template slot="extra">
        <el-button :disabled='!page.leftBuRu!=0'  type="primary" size="medium" @click="toApply(5)">请假</el-button>
      </template>
    </el-result>
  </el-col>
  <el-col :sm="12" :lg="6">
    <el-result :icon="page.leftChanJia>10?'success':'warning'" :title='page.leftChanJia+"天"' subTitle="剩余产假">
      <template slot="extra">
        <el-button :disabled='!page.leftChanJia!=0' type="primary" size="medium"  @click="toApply(4)">请假</el-button>
      </template>
    </el-result>
  </el-col>
  <el-col :sm="12" :lg="6">
    <el-result :icon="page.leftChanJian>10?'success':'warning'" :title='page.leftChanJian+"天"' subTitle="剩余产检假">
      <template slot="extra">
        <el-button :disabled='!page.leftChanJian!=0' type="primary" size="medium" @click="toApply(3)">请假</el-button>
      </template>
    </el-result>
  </el-col>
    <el-col :sm="12" :lg="6">
        
        <el-result :icon="page.leftHunJia>10?'success':'warning'" :title='page.leftHunJia+"天"' subTitle="剩余婚假">
        <template slot="extra">
            <el-button :disabled='!page.leftHunJia!=0' type="primary" size="medium" @click="toApply(2)">请假</el-button>
        </template>
        </el-result>
    </el-col>
    <el-col :sm="12" :lg="6">
        <el-result :icon="page.leftPeiChan>10?'success':'warning'" :title='page.leftPeiChan+"天"' subTitle="剩余陪产假">
        <template slot="extra">
            <el-button :disabled='!page.leftPeiChan!=0' type="primary" size="medium" @click="toApply(6)">请假</el-button>
        </template>
        </el-result>
    </el-col>
    <el-col :sm="12" :lg="6">
        <el-result :icon="page.leftYear>10?'success':'warning'" :title='page.leftYear+"天"' subTitle="剩余年假">
        <template slot="extra">
            <el-button :disabled='!page.leftYear!=0' type="primary" size="medium"  @click="toApply(1)">请假</el-button>
        </template>
        </el-result>
    </el-col>
</el-row>
</template>
<script>
export default {
    data(){
        return{
            page:[]
        }
    },
    created() {
        this.loaddata();
    },
    methods: {
        loaddata() {
            var empId = window.sessionStorage.getItem('empId');
            this.$http.get('/getLeftTimeById/'+empId).then(res => {
                console.log(res.data);
                this.page = res.data;
            });
        },
        toApply(num){
            this.$router.push({path: '/apply/addApply', query: {type: num}});
            // this.$router.push('/apply/addApply');
        }
    },
}
</script>
