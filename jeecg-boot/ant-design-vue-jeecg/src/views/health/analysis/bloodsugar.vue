<template>
  <div>
    <index-chart v-if="indexStyle==1"></index-chart>

    <div style="width: 100%;text-align: right;margin-top: 20px">
       显示样式：
      <a-radio-group v-model="indexStyle">
        <a-radio :value="1">统计图表</a-radio>

      </a-radio-group>
    </div>
  </div>
</template>

<script>
  import IndexChart from './bloodsugar_linechat'
  // import IndexTask from "./IndexTask"
  // import IndexBdc from './IndexBdc'

  export default {
    name: "Analysis",
    components: {
      IndexChart
    },
    data() {
      return {
        indexStyle:1
      }
    },
    created() {

    },
    methods: {

    }
  }
</script>