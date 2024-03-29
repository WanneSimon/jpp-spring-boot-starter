<template>
    <meta-header :metaInfo="metaInfo"></meta-header>
    <div> Hi ! Welcome ~ </div>
</template>


<script setup>
import { ref } from 'vue'
import MetaHeader from '/src/components/vue-meta/meta-header.vue'
const metaInfo = ref({ title: 'Welcome' })
</script>
