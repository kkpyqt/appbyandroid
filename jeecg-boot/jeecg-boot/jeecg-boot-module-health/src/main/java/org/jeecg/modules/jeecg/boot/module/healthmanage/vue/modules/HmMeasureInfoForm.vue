<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="用户ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="userId">
              <a-input v-model="model.userId" placeholder="请输入用户ID"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="设备类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="deviceType">
              <a-input v-model="model.deviceType" placeholder="请输入设备类型"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="测量类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="measureType">
              <a-input v-model="model.measureType" placeholder="请输入测量类型"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="测量值" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mValue">
              <a-input v-model="model.mValue" placeholder="请输入测量值"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="测量单位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mUnit">
              <a-input v-model="model.mUnit" placeholder="请输入测量单位"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="设备mac" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="setMacadd">
              <a-input v-model="model.setMacadd" placeholder="请输入设备mac"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="设备IMEI" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="setImei">
              <a-input v-model="model.setImei" placeholder="请输入设备IMEI"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="删除标志" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="delFlag">
              <a-input-number v-model="model.delFlag" placeholder="请输入删除标志" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="版本号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="version">
              <a-input-number v-model="model.version" placeholder="请输入版本号" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="结果类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="levelType">
              <a-input-number v-model="model.levelType" placeholder="请输入结果类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="设备绑定用户" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="userRelId">
              <a-input v-model="model.userRelId" placeholder="请输入设备绑定用户"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="警告" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="adalert">
              <a-input v-model="model.adalert" placeholder="请输入警告"  ></a-input>
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
    name: 'HmMeasureInfoForm',
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
          add: "/jeecg.boot.module.healthmanage/hmMeasureInfo/add",
          edit: "/jeecg.boot.module.healthmanage/hmMeasureInfo/edit",
          queryById: "/jeecg.boot.module.healthmanage/hmMeasureInfo/queryById"
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