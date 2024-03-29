<template>
  <div> 
    <meta-header :metaInfo="{ title: 'hello' }"></meta-header>
    <div> hello page 1</div>
    
    <div style="width: 100%;display: flex;flex-direction: row;justify-content:space-around;"> 
      <div><router-link :to="'/'">index</router-link></div>
      <div><router-link :to="'/r'">hello</router-link></div>
      <div><router-link :to="'/u'">there</router-link></div>
    </div>
  </div>
</template>

<script src="./index.js">
</script>

<style lang="scss" scoped>
// import other css
// @import '/src/assets/css/manage.scss';
</style>