import Vue from 'vue';
import Router from 'vue-router';

const _import = require('./_import_' + process.env.NODE_ENV);

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Index',
      component: _import('index')
    },
    {
      path: '/transition',
      name: 'Transition',
      component: _import('transition')
    },
    {
      path: '/block',
      name: 'Block',
      component: _import('block')
    },
    {
      path: '/block/detail/:id',
      name: 'BlockDetail',
      component: _import('blockDetail')
    },
    {
      path: '/transition/detail/:id',
      name: 'TransitionDetail',
      component: _import('transitionDetail')
    },
    {
      path: '/address/detail/:id',
      name: 'AddressDetail',
      component: _import('addressDetail')
    },
    {
      path: '/asset',
      name: 'Asset',
      component: _import('asset')
    },
    {
      path: '/project',
      name: 'Project',
      component: _import('project')
    },
    {
      path: '/project/detail/:id',
      name: 'ProjectDetail',
      component: _import('projectDetail')
    },
    {
      path: '/m',
      component: _import('mobile/index'),
      children: [{
        path: '',
        component: _import('mobile/home')
      },
      {
        path: 'transition',
        component: _import('mobile/transition')
      },
      {
        path: 'block',
        component: _import('mobile/block')
      },
      {
        path: 'transition/detail/:id',
        component: _import('mobile/transitionDetail')
      },
      {
        path: 'block/detail/:id',
        component: _import('mobile/blockDetail')
      },
      {
        path: 'asset',
        component: _import('mobile/asset')
      },
      {
        path: 'project',
        component: _import('mobile/project')
      },
      {
        path: 'project/detail/:id',
        component: _import('mobile/projectDetail')
      },
      {
        path: 'address/detail/:id',
        component: _import('mobile/addressDetail')
      }]
    }
  ]
});
