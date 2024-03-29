import MetaHeader from '/src/components/vue-meta/meta-header.vue'
import globalStore from '/src/components/pinia/store.js'
import { RouterLink } from 'vue-router'

export default {
  components: { MetaHeader, RouterLink },
  setup() {    
    const store = globalStore()
    return {
      store
    }
  },
}