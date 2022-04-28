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

export default{
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
        }
    }
}
</script>
