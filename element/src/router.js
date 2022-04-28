import Vue from 'vue'
import Router from 'vue-router'
import ListArticle from "./views/ListArticle.vue"
import AddEmployer from "./views/AddEmployer.vue"
import AddApply from "./views/AddApply.vue"
import Login from "./views/login.vue"
import Home from "./views/home.vue"
import ApplyList from "./views/ApplyList.vue"
import Test from "./views/test.vue"
import ManageApply from "./views/ManageApply.vue"
import ManageApplyX from "./views/ManageApplyX.vue"
import EmployeDaKa from "./views/EmployeDaKa.vue"
import AllEmployeState from "./views/AllEmployeState.vue"
import AllDaKa from "./views/AllDaKa.vue"
import CheckLeft from "./views/CheckLeft.vue"
import AllEmployer from "./views/AllEmployer.vue"
import EmployerInfo from "./views/EmployerInfo.vue"
import MyInfo from "./views/MyInfo.vue"
import AllApplyList from "./views/AllApplyList.vue"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '登录',
      component: Login
    }, {
      path: '/login',
      name: '登录',
      component: Login,
    },{
      path: '/home',
      name: '',
      component: Home,
    }, {
      path: '/home',
      component: Home,
      name: '管理',
      children: [
        {
          path: '/apply/addApply',
          name: '添加申请',
          component: AddApply,
        }, {
          path: '/employer/AddEmployer',
          name: '添加员工',
          component: AddEmployer,
        }, {
          path: '/blogDetail',
          name: '博客详情',
          component: ListArticle,
        }, {
          path: '/apply/applyList',
          name: '请假记录',
          component: ApplyList,
        }, {
          path: '/test',
          name: '测试',
          component: Test,
        }, {
          path: '/apply/ManageApply',
          name: '审核请假',
          component: ManageApply,
        }, {
          path: '/apply/ManageApplyX',
          name: '审核3天以上请假',
          component: ManageApplyX,
        }, {
          path: '/DaKa/EmployeDaKa',
          name: '员工打卡',
          component: EmployeDaKa,
        }, {
          path: '/employer/AllEmployeState',
          name: '员工状态',
          component: AllEmployeState,
        }, {
          path: '/Daka/AllDaKa',
          name: '员工状态',
          component: AllDaKa,
        }, {
          path: '/employer/CheckLeft',
          name: '剩余假期',
          component: CheckLeft,
        }, {
          path: '/employer/AllEmployer',
          name: '查询所有人员',
          component: AllEmployer,
        }, {
          path: '/employer/EmployerInfo',
          name: '查询人员详情',
          component: EmployerInfo,
        }, {
          path: '/employer/MyInfo',
          name: '我的详情',
          component: MyInfo,
        }, {
          path: '/apply/AllApplyList',
          name: '所有请假申请',
          component: AllApplyList,
        }
      ]
    }
  ]
})