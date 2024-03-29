// const componentModules = import.meta.globEager('/src/views/**/*.vue') // 立即引入
const componentModules = import.meta.glob('/src/views/**/*.vue') // 懒加载

import Layout from '/src/components/layout/layout.vue'
//import EmptyView from '/src/views/components/empty-view.vue'

function _importView(_path){
  return componentModules[`/src/views/${_path}`]
}
function _importFrontView(_path){
  return componentModules[`/src/views/front/${_path}`]
}
function _import(_path){
  return componentModules[`/src/${_path}`]
}

const routes = [
    { path: '/', redirect: '/index' },
    { 
      path: "/",
      component: Layout, 
      children: [
        { name: 'index',   path: 'index', 
          component: _importView('home.vue'),
          co_menu: { label: 'index', disabled: false, icon: '',},
        },
        { 
          name: 'hello', 
          path: 'r', 
          component: _importView('front/hello/index.vue'),
          co_menu: { label: 'hello', disabled: false, icon: '', permission: null},
        },
        { 
          name: 'there', 
          path: 'u', 
          component: _importView('front/there/index.vue'),
          co_menu: { label: 'index', disabled: false, icon: '',  permission: null},
        },
      ]
    },
    // others
    // ========
    // { path: "/404", name: "notFound", component: _importView('home.vue') },
    { path: "/404", name: "notFound", redirect: '/home' },
    {
      path: "/:pathMatch(.*)", 
      redirect: "/404",
    }
  ]

export default routes