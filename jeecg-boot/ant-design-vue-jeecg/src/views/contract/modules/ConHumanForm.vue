<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="8">
            <a-form-model-item label="编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="humanCode">
              <a-input v-model="model.humanCode" placeholder="请输入编码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="8">
            <a-form-model-item label="姓名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入姓名"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="8">
            <a-form-model-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sex">
              <j-dict-select-tag type="radio" v-model="model.sex" dictCode="sex" placeholder="请选择性别" />
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="6">
            <a-form-model-item label="年龄" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="age">
              <a-input-number v-model="model.age" placeholder="请输入年龄" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="8">
            <a-form-model-item label="出生日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="birthday">
              <j-date placeholder="请选择出生日期" v-model="model.birthday"  style="width: 100%" />
            </a-form-model-item>

          </a-col>
          <a-col :span="10">
            <a-form-model-item label="身份证号码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="idNumber">
              <a-input v-model="model.idNumber" placeholder="请输入身份证号码"  ></a-input>
            </a-form-model-item>
          </a-col>

        </a-row>

        <a-row>
          <a-col :span="8">
            <a-form-model-item label="电话1" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mobilePhone1">
              <a-input v-model="model.mobilePhone1" placeholder="请输入电话号码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="8">
            <a-form-model-item label="座机电话" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tel">
              <a-input v-model="model.tel" placeholder="请输入座机电话"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="8">
            <a-form-model-item label="电话2" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mobilePhone2">
              <a-input v-model="model.mobilePhone2" placeholder="请输入电话号码2"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
         <a-row>
           <a-col :span="8">
           <a-form-model-item label="民族" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nation">
             <a-input v-model="model.nation" placeholder="请输入民族"  ></a-input>
           </a-form-model-item>
           </a-col>
           <a-col :span="8">
             <a-form-model-item label="政治面貌" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="political">
               <j-dict-select-tag type="list" v-model="model.political" dictCode="affiliation" placeholder="请选择政治面貌" />
             </a-form-model-item>
           </a-col>
           <a-col :span="8">
             <a-form-model-item label="籍贯" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nativePlace">
               <a-input v-model="model.nativePlace" placeholder="请输入籍贯"  ></a-input>
             </a-form-model-item>
           </a-col>


         </a-row>
        <a-row>
          <a-col :span="8">
            <a-form-model-item label="来源" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="soureType">
              <a-input-number v-model="model.soureType" placeholder="请输入来源" style="width: 100%" />
            </a-form-model-item>
          </a-col>
            <a-col :span="8">
              <a-form-model-item label="身高" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="height">
                <a-input v-model="model.height" placeholder="请输入身高"  ></a-input>
              </a-form-model-item>
            </a-col>
            <a-col :span="8">
              <a-form-model-item label="体重" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="weight">
                <a-input v-model="model.weight" placeholder="请输入体重"  ></a-input>
              </a-form-model-item>
            </a-col>

          </a-row>
        <a-row>
          <a-col :span="6">
            <a-form-model-item label="健康状况" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="health">
              <a-input v-model="model.health" placeholder="请输入健康状况"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="16">
            <a-form-model-item label="Email" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="email">
              <a-input v-model="model.email" placeholder="请输入Email"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row> </a-row>

        <a-row>
          <a-col :span="6">
            <a-form-model-item label="区域" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="area">
              <a-input v-model="model.area" placeholder="请输入区域"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="14">
            <a-form-model-item label="联系地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="contactAddress">
              <a-input v-model="model.contactAddress" placeholder="请输入联系地址"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="4">
            <a-form-model-item label="邮编" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="zipCode">
              <a-input v-model="model.zipCode" placeholder="请输入邮编"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="14">
            <a-form-model-item label="常用地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="commonAddr">
              <a-input v-model="model.commonAddr" placeholder="请输入常用地址"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="10">
            <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="remarks">
              <a-input v-model="model.remarks" placeholder="请输入备注"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="8">
            <a-form-model-item label="员工类别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="jobStatus">
              <j-dict-select-tag type="list" v-model="model.jobStatus" dictCode="job_status" placeholder="请选择工作类别" />
            </a-form-model-item>
          </a-col>
          <a-col :span="8">
            <a-form-model-item label="到职时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="arrivalTime">
              <j-date placeholder="请选择到职时间" v-model="model.arrivalTime"  style="width: 100%" />
            </a-form-model-item>
          </a-col>

          <a-col :span="8">
            <a-form-model-item label="岗位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="postId">
              <a-input-number v-model="model.postId" placeholder="请输入岗位" style="width: 100%" />
            </a-form-model-item>
          </a-col>
        </a-row>
          <a-row>
            <a-col :span="8">
            <a-form-model-item label="入职日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="employDate">
              <j-date placeholder="请选择入职日期" v-model="model.employDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
            <a-col :span="8">
              <a-form-model-item label="转正日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="formalDate">
                <j-date placeholder="请选择转正日期" v-model="model.formalDate"  style="width: 100%" />
              </a-form-model-item>
            </a-col>
            <a-col :span="8">
              <a-form-model-item label="离职日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="resignDate">
                <j-date placeholder="请选择离职日期" v-model="model.resignDate"  style="width: 100%" />
              </a-form-model-item>
            </a-col>
          </a-row>





          <a-row>
          <a-col :span="6">
            <a-form-model-item label="学历" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="education">
              <a-input v-model="model.education" placeholder="请输入学历"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="8">
            <a-form-model-item label="毕业学校" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="school">
              <a-input v-model="model.school" placeholder="请输入毕业学校"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="10">
            <a-form-model-item label="专业" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="major">
              <a-input v-model="model.major" placeholder="请输入专业"  ></a-input>
            </a-form-model-item>
          </a-col>
          </a-row>

          <a-row>


          </a-row>
          <a-row>
            <a-col :span="8">
              <a-form-model-item label="毕业时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="graduationTime">
                <j-date placeholder="请选择毕业时间" v-model="model.graduationTime"  style="width: 100%" />
              </a-form-model-item>
            </a-col>
          <a-col :span="8">
            <a-form-model-item label="外语语种" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="foreignLanguage">
              <a-input v-model="model.foreignLanguage" placeholder="请输入外语语种"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="8">
            <a-form-model-item label="外语水平" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="foreignLanguageLevel">
              <a-input v-model="model.foreignLanguageLevel" placeholder="请输入外语水平"  ></a-input>
            </a-form-model-item>
          </a-col>
          </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="计算机水平" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="computerLevel">
              <a-input v-model="model.computerLevel" placeholder="请输入计算机水平"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="10">
            <a-form-model-item label="人员状态 " :labelCol="labelCol" :wrapperCol="wrapperCol" prop="humanState">
              <j-dict-select-tag type="list" v-model="model.humanState" dictCode="ployee_status" placeholder="请选择人员状态 " />
            </a-form-model-item>
          </a-col>
        </a-row>



         <a-row>
          <a-col :span="36">
            <a-form-model-item label="教育经历" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="educationExperience">
              <a-textarea v-model="model.educationExperience" rows="4" placeholder="请输入教育经历" />
            </a-form-model-item>
          </a-col>
         </a-row>
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="工作经历" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="workExperience">
              <a-textarea v-model="model.workExperience" rows="4" placeholder="请输入工作经历" />
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
    name: 'ConHumanForm',
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
          add: "/contract/conHuman/add",
          edit: "/contract/conHuman/edit",
          queryById: "/contract/conHuman/queryById"
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