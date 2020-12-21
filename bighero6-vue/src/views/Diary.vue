<template>
<div id = "main">
    <book/> 
 </div>   
</template>

<style scoped>



</style>

<script>


export default {
name: 'Diary',
components: {
     Book: () => import('@/components/mainpage/Book'), 
    test: () => import('@/components/mainpage/Calendar'),
},
}
</script>
