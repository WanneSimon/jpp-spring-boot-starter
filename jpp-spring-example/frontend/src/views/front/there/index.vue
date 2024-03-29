<template>
  <div> 
    <meta-header :metaInfo="{ title: 'there' }"></meta-header>
    <div> hi there page 2</div>
    
    <div style="width: 100%;display: flex;flex-direction: row;justify-content:space-around;"> 
      <div><router-link :to="'/'">index</router-link></div>
      <div><router-link :to="'/r'">hello</router-link></div>
      <div><router-link :to="'/u'">there</router-link></div>
    </div>
  </div>
</template>

<script setup>
import MetaHeader from '/src/components/vue-meta/meta-header.vue'
import globalStore from '/src/components/pinia/store.js'
import { RouterLink } from 'vue-router'

const store = globalStore()
</script>

<style lang="scss" scoped>
// import other css
// @import '/src/assets/css/manage.scss';
</style>