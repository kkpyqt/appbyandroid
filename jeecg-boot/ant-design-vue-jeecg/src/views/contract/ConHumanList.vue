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
      <a-button type="primary" icon="download" @click="handleExportXls('员工表')">导出</a-button>
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

    <con-human-modal ref="modalForm" @ok="modalFormOk"></con-human-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ConHumanModal from './modules/ConHumanModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: 'ConHumanList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      ConHumanModal
    },
    data () {
      return {
        description: '员工表管理页面',
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
            title:'编码',
            align:"center",
            dataIndex: 'humanCode'
          },
          {
            title:'姓名',
            align:"center",
            dataIndex: 'name'
          },
          {
            title:'性别',
            align:"center",
            dataIndex: 'sex_dictText'
          },
          {
            title:'年龄',
            align:"center",
            dataIndex: 'age'
          },
          {
            title:'出生日期',
            align:"center",
            dataIndex: 'birthday',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'电话号码',
            align:"center",
            dataIndex: 'mobilePhone1'
          },
          {
            title:'电话号码2',
            align:"center",
            dataIndex: 'mobilePhone2'
          },
          {
            title:'座机电话',
            align:"center",
            dataIndex: 'tel'
          },
          {
            title:'身份证号码',
            align:"center",
            dataIndex: 'idNumber'
          },
          {
            title:'来源',
            align:"center",
            dataIndex: 'soureType'
          },
          {
            title:'区域',
            align:"center",
            dataIndex: 'area'
          },
          {
            title:'身份证地址',
            align:"center",
            dataIndex: 'address'
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'remarks'
          },
          {
            title:'入职日期',
            align:"center",
            dataIndex: 'employDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'离职日期',
            align:"center",
            dataIndex: 'resignDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'工作类别',
            align:"center",
            dataIndex: 'jobStatus_dictText'
          },
          {
            title:'常用地址',
            align:"center",
            dataIndex: 'commonAddr'
          },
          {
            title:'转正日期',
            align:"center",
            dataIndex: 'formalDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'民族',
            align:"center",
            dataIndex: 'nation'
          },
          {
            title:'政治面貌',
            align:"center",
            dataIndex: 'political_dictText'
          },
          {
            title:'籍贯',
            align:"center",
            dataIndex: 'nativePlace'
          },
          {
            title:'身高',
            align:"center",
            dataIndex: 'height'
          },
          {
            title:'体重',
            align:"center",
            dataIndex: 'weight'
          },
          {
            title:'健康状况',
            align:"center",
            dataIndex: 'health'
          },
          {
            title:'学历',
            align:"center",
            dataIndex: 'education'
          },
          {
            title:'毕业学校',
            align:"center",
            dataIndex: 'school'
          },
          {
            title:'专业',
            align:"center",
            dataIndex: 'major'
          },
          {
            title:'联系地址',
            align:"center",
            dataIndex: 'contactAddress'
          },
          {
            title:'邮编',
            align:"center",
            dataIndex: 'zipCode'
          },
          {
            title:'Email',
            align:"center",
            dataIndex: 'email'
          },
          {
            title:'外语语种',
            align:"center",
            dataIndex: 'foreignLanguage'
          },
          {
            title:'外语水平',
            align:"center",
            dataIndex: 'foreignLanguageLevel'
          },
          {
            title:'计算机水平',
            align:"center",
            dataIndex: 'computerLevel'
          },
          {
            title:'毕业时间',
            align:"center",
            dataIndex: 'graduationTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'到职时间',
            align:"center",
            dataIndex: 'arrivalTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'教育经历',
            align:"center",
            dataIndex: 'educationExperience'
          },
          {
            title:'工作经历',
            align:"center",
            dataIndex: 'workExperience'
          },
          {
            title:'删除标识',
            align:"center",
            dataIndex: 'delFlag'
          },
          {
            title:'人员状态 ',
            align:"center",
            dataIndex: 'humanState_dictText'
          },
          {
            title:'岗位',
            align:"center",
            dataIndex: 'postId'
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
          list: "/contract/conHuman/list",
          delete: "/contract/conHuman/delete",
          deleteBatch: "/contract/conHuman/deleteBatch",
          exportXlsUrl: "/contract/conHuman/exportXls",
          importExcelUrl: "contract/conHuman/importExcel",
          
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
        fieldList.push({type:'string',value:'humanCode',text:'编码',dictCode:''})
        fieldList.push({type:'string',value:'name',text:'姓名',dictCode:''})
        fieldList.push({type:'string',value:'sex',text:'性别',dictCode:'sex'})
        fieldList.push({type:'int',value:'age',text:'年龄',dictCode:''})
        fieldList.push({type:'date',value:'birthday',text:'出生日期'})
        fieldList.push({type:'string',value:'mobilePhone1',text:'电话号码',dictCode:''})
        fieldList.push({type:'string',value:'mobilePhone2',text:'电话号码2',dictCode:''})
        fieldList.push({type:'string',value:'tel',text:'座机电话',dictCode:''})
        fieldList.push({type:'string',value:'idNumber',text:'身份证号码',dictCode:''})
        fieldList.push({type:'int',value:'soureType',text:'来源',dictCode:''})
        fieldList.push({type:'string',value:'area',text:'区域',dictCode:''})
        fieldList.push({type:'string',value:'address',text:'身份证地址',dictCode:''})
        fieldList.push({type:'string',value:'remarks',text:'备注',dictCode:''})
        fieldList.push({type:'date',value:'employDate',text:'入职日期'})
        fieldList.push({type:'date',value:'resignDate',text:'离职日期'})
        fieldList.push({type:'int',value:'jobStatus',text:'工作类别',dictCode:'job_status'})
        fieldList.push({type:'string',value:'commonAddr',text:'常用地址',dictCode:''})
        fieldList.push({type:'date',value:'formalDate',text:'转正日期'})
        fieldList.push({type:'string',value:'nation',text:'民族',dictCode:''})
        fieldList.push({type:'string',value:'political',text:'政治面貌',dictCode:'affiliation'})
        fieldList.push({type:'string',value:'nativePlace',text:'籍贯',dictCode:''})
        fieldList.push({type:'string',value:'height',text:'身高',dictCode:''})
        fieldList.push({type:'string',value:'weight',text:'体重',dictCode:''})
        fieldList.push({type:'string',value:'health',text:'健康状况',dictCode:''})
        fieldList.push({type:'string',value:'education',text:'学历',dictCode:''})
        fieldList.push({type:'string',value:'school',text:'毕业学校',dictCode:''})
        fieldList.push({type:'string',value:'major',text:'专业',dictCode:''})
        fieldList.push({type:'string',value:'contactAddress',text:'联系地址',dictCode:''})
        fieldList.push({type:'string',value:'zipCode',text:'邮编',dictCode:''})
        fieldList.push({type:'string',value:'email',text:'Email',dictCode:''})
        fieldList.push({type:'string',value:'foreignLanguage',text:'外语语种',dictCode:''})
        fieldList.push({type:'string',value:'foreignLanguageLevel',text:'外语水平',dictCode:''})
        fieldList.push({type:'string',value:'computerLevel',text:'计算机水平',dictCode:''})
        fieldList.push({type:'date',value:'graduationTime',text:'毕业时间'})
        fieldList.push({type:'date',value:'arrivalTime',text:'到职时间'})
        fieldList.push({type:'Text',value:'educationExperience',text:'教育经历',dictCode:''})
        fieldList.push({type:'Text',value:'workExperience',text:'工作经历',dictCode:''})
        fieldList.push({type:'int',value:'delFlag',text:'删除标识',dictCode:'delete_flag'})
        fieldList.push({type:'int',value:'humanState',text:'人员状态 ',dictCode:'ployee_status'})
        fieldList.push({type:'int',value:'postId',text:'岗位',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>