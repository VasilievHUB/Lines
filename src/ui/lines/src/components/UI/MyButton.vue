<template>
  <button class="btn">
    <slot></slot>
  </button>
</template>

<script>
export default {
  name: 'my-button'
}
</script>

<style scoped>
.btn {
  padding: 10px 15px;
  background: none;
  color: teal;
  border: 1px solid teal;
}
.btn:hover{
  padding: 10px 15px;
  background: none;
  color: rgb(149, 168, 168);
  border: 1px solid teal;
}
</style>