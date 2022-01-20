<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('测量信息')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <hm-measure-info-modal ref="modalForm" @ok="modalFormOk"></hm-measure-info-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import HmMeasureInfoModal from './modules/HmMeasureInfoModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: 'HmMeasureInfoList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      HmMeasureInfoModal
    },
    data () {
      return {
        description: '测量信息管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'创建日期',
            align:"center",
            dataIndex: 'createTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'用户ID',
            align:"center",
            dataIndex: 'userId'
          },
          {
            title:'设备类型',
            align:"center",
            dataIndex: 'deviceType'
          },
          {
            title:'测量类型',
            align:"center",
            dataIndex: 'measureType'
          },
          {
            title:'测量值',
            align:"center",
            dataIndex: 'mValue'
          },
          {
            title:'测量单位',
            align:"center",
            dataIndex: 'mUnit'
          },
          {
            title:'设备mac',
            align:"center",
            dataIndex: 'setMacadd'
          },
          {
            title:'设备IMEI',
            align:"center",
            dataIndex: 'setImei'
          },
          {
            title:'删除标志',
            align:"center",
            dataIndex: 'delFlag'
          },
          {
            title:'版本号',
            align:"center",
            dataIndex: 'version'
          },
          {
            title:'结果类型',
            align:"center",
            dataIndex: 'levelType'
          },
          {
            title:'设备绑定用户',
            align:"center",
            dataIndex: 'userRelId'
          },
          {
            title:'警告',
            align:"center",
            dataIndex: 'adalert'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/health/measure/hmMeasureInfo/list",
          delete: "/health/measure/hmMeasureInfo/delete",
          deleteBatch: "/health/measure/hmMeasureInfo/deleteBatch",
          exportXlsUrl: "/health/measure/hmMeasureInfo/exportXls",
          importExcelUrl: "health/measure/hmMeasureInfo/importExcel",

        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'date',value:'createTime',text:'创建日期'})
        fieldList.push({type:'string',value:'userId',text:'用户ID',dictCode:'sys_user,realname,id'})
        fieldList.push({type:'string',value:'deviceType',text:'设备类型',dictCode:'measure_set_type'})
        fieldList.push({type:'string',value:'measureType',text:'测量类型',dictCode:'measure_type'})
        fieldList.push({type:'string',value:'mValue',text:'测量值',dictCode:''})
        fieldList.push({type:'string',value:'mUnit',text:'测量单位',dictCode:''})
        fieldList.push({type:'string',value:'setMacadd',text:'设备mac',dictCode:''})
        fieldList.push({type:'string',value:'setImei',text:'设备IMEI',dictCode:''})
        fieldList.push({type:'int',value:'delFlag',text:'删除标志',dictCode:''})
        fieldList.push({type:'int',value:'version',text:'版本号',dictCode:''})
        fieldList.push({type:'int',value:'levelType',text:'结果类型',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>