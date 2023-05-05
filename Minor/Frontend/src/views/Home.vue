<template>
  <div>
    <SideBar/>
    <router-link to="/Dashboard" class="link">
      <DashboardPreview/>
    </router-link>
  </div>
</template>

<script>
import SideBar from '@/components/SideBar.vue'
import DashboardPreview from '@/components/DashboardPreview.vue'

export default {
  name: 'Home',
  components: {
    SideBar,
    DashboardPreview
  }
}
</script>