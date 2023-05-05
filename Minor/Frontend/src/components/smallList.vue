<template>
    <li>
      {{ item.tagId }}
      <button v-on:click="$emit('remove')">Remove</button>
    </li>
</template>

<script>
export default {
    name: 'smallList',
    props: ['item'],
}
</script>