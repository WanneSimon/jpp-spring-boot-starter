<template>
    <meta-header :metaInfo="metaInfo"></meta-header>
    <div> Home </div>
    <div> <button @click="addCount"> {{countNumber}} </button> </div>
    
    <div style="width: 100%;display: flex;flex-direction: row;justify-content:space-around;"> 
      <div><router-link :to="'/'">index</router-link></div>
      <div><router-link :to="'/r'">hello</router-link></div>
      <div><router-link :to="'/u'">there</router-link></div>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import MetaHeader from '/src/components/vue-meta/meta-header.vue'
import { RouterLink } from 'vue-router'

const countNumber = ref(0)
const metaInfo = ref({
  title: 'Home ' + countNumber.value
})


const addCount = () => {
  countNumber.value ++
}
</script>
