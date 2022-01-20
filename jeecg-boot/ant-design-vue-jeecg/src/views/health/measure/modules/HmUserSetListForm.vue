<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="用户ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="userId">
              <j-dict-select-tag type="list" v-model="model.userId" dictCode="sys_user,username,id" placeholder="请选择用户ID" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="设备用户名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="setUserName">
              <a-input v-model="model.setUserName" placeholder="请输入设备用户名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="身高" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="height">
              <a-input v-model="model.height" placeholder="请输入身高"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="体重" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="weight">
              <a-input v-model="model.weight" placeholder="请输入体重"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="健康状况" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="health">
              <a-input v-model="model.health" placeholder="请输入健康状况"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="删除标志" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="delFlag">
              <j-dict-select-tag type="radio" v-model="model.delFlag" dictCode="delete_flag" placeholder="请选择删除标志" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="出生日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="birthday">
              <j-date placeholder="请选择出生日期" v-model="model.birthday"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sex">
              <j-dict-select-tag type="radio" v-model="model.sex" dictCode="sex" placeholder="请选择性别" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="用户组别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="health">
              <a-input v-model="model.setId" placeholder="用户组别"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'HmUserSetListForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
            delFlag:0,
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/health/measure/hmUserSetList/add",
          edit: "/health/measure/hmUserSetList/edit",
          queryById: "/health/measure/hmUserSetList/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }

        })
      },
    }
  }
</script>