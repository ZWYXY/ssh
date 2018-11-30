package com.zr.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zr.entity.CustomerEntity;
import com.zr.entity.LinkMan;
import com.zr.service.CustomerService;
import com.zr.service.LinkManService;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("prototype")
@Controller(value = "linkManAction")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {


    /*  配置文件方式 在 linkManAction 中注入 linkManService
    *   第一步： 声明linKMan 属性
    *   第二步： 生成set方法
    *   private LinkManService linkManService;
    *   public void setLinkManService(LinkManService linkManService) { this.linkManService = linkManService; }
    *   第三步：在spring配置文件中配置
    * */

    /*
     *   注解方式 在 LinkMan 中注入LinkManService
     *       第一步：定义Service类型属性 并生成对应的set方法
     *       第二步：在set方法上使用注解
     *       额外的步骤 ：需要在Spring配置文件中 开启注解扫描
     *
     *   Annotations：
     *   @Autowired
     *   @Qualifier(value = "linkManService")
     *   @Resource(name = "linkManService")  这个注解相当于AutoWired 和 Qualifier 注解一起使用
     *
     *   @Autowired
     *   private LinkManService linkManService; 字段注入方式 不被spring 推荐
     *   推荐使用方式：（举例）
     *   声明linkMan 实例
     *   生成对应的set方法，注解在set方法上
     *
     * */
    private LinkManService linkManService;
    @Autowired
    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    //注入CustomerService的对象
    private CustomerService customerService;
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*模型驱动封装 步骤：
    * 1.实现ModelDriven接口
    * 2.new LinkMan实体类
    * 3.重写实现类中的getModel方法 ，并将刚才定义的实体类对象返回
    * tips:表单输入项neme属性的名字要和对象的字段（filed）名字一致
    *
    * 使用模型驱动封装的目的：获取表单提交数据
     * */
    private  LinkMan linkMan = new LinkMan();
    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    //1 To AddLinkMan Page
    public String toAddPage(){
        //1.1查询出所有客户，把所有客户组成的 list 集合传递到页面中显示（放到域对象或者值栈中）
        //调用customerService里面的方法得到所有客户
        List<CustomerEntity> listCustomer  = customerService.findAll();
        ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);//封装到request域中
        return  "toAddPage";
    }

    //2 添加新联系人到数据库
    /*struts2 实现文件上传
     *
     * 需要上传 文件（流）
     * 需要上传 文件名称
     * （1）在action定义成员变量 变量命名有需要遵守的规范
     *       - 一个表示上传文件
     *       - 一个表示文件名称
     *  (2) 生成变量的set  和 get 方法
     * */
    // 变量的名称   需要是表单里面文件上传的name属性值
    private File upload;
    // 文件上传名称 需要是文件上传项的name属性值+FileName
    private String uploadFileName;
    public File getUpload() {return upload;}
    public void setUpload(File upload) { this.upload = upload;}
    public String getUploadFileName() { return uploadFileName;}
    public void setUploadFileName(String uploadFileName) { this.uploadFileName = uploadFileName; }
    public String addLinkMan(){

        /*
        *  问题：可以获取到封装的联系人信息
        *  但是有cid是客户的id 不能直接封装 ， 那么如何获取？
        *  解决： 把cid封装到LinkMan实体类中的Customer对象里面
        * */

        //法一：原始方式
/*
        String  scid = ServletActionContext.getRequest().getParameter("cid");
        int cid = Integer.parseInt(scid);
        //创建Customer对象
        CustomerEntity customer = new CustomerEntity();
        customer.setCid(cid);

        linkMan.setCustomerEntity(customer);
*/
        //法二：简化方式
        /*直接在对应的jsp页面中 customer.cid 就OK了 这是使用了模型驱动封装
        * 第一个customerEntity 就表示LinkMan中的customerEntity属性，第二个cid 表示封装到CustomerEntity对象中的cid属性中去
        *
        * */

        //判断是否需要上传文件
        if(upload != null){
            //写上传代码
                //服务器中创建文件放置位置
            File serviceFile = new File("F:/temporaryFiles" + "/" + uploadFileName);
                //把要上传的文件复制到服务器
            try {
                FileUtils.copyFile(upload, serviceFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        linkManService.add(linkMan);
        return  "addLinkMan";
    }

    //3.to linkMan list
    public String list(){
       List<LinkMan> list =  linkManService.findAll();
       ServletActionContext.getRequest().setAttribute("list", list);
        return "list";
    }

    //4.modify LinkMan information
    public String modify(){
        //use modelDriven get lkmId
        int lkmId = linkMan.getLkmId();
        LinkMan linkMan = linkManService.findById(lkmId);

        //所有客户list集合
        List<CustomerEntity> customerlist = customerService.findAll();

        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("linkMan", linkMan);
        request.setAttribute("customerList", customerlist);

        return "modify";
    }

    //5.To dispatcher to listPage when modify success
    public String update(){
        linkManService.update(linkMan);
        return "update";
    }

    //6.delete linkMan
    public String delete(){
        linkManService.delete(linkMan);
        return "delete";
    }

    //7. 跳转到多条件查询页面
    public String toMoreConditionPage(){

        List<CustomerEntity> customerEntities = customerService.findAll();
        ServletActionContext.getRequest().setAttribute("customerEntities", customerEntities);

        return  "toMoreConditionPage";
    }

    //8. 多条件查询
    public String moreCondition(){

        List<LinkMan> linkMans = linkManService.findMoreCondition(linkMan);
        ServletActionContext.getRequest().setAttribute("list", linkMans);


        return "moreCondition";
    }

    //9. 查询所有联系人返回json
    public String findAllLinkManByJson() throws IOException {
        List<LinkMan> list = linkManService.findAll();

        Map<String, Object> map = new HashMap<>();
        map.put("total",list.size());
        map.put("rows",list);

        String jxon = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jxon);


        return NONE;
    }


    //属性封装 得到当前datagrid传递的值
    private int page; //当前页
    private int rows; //每页行数
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }

    //10. 使用datagrid 分页实现
    public void dataGridPaging() throws IOException {
        //返回分页数据 需要 total:总记录数  rows:分页数据  的json格式

        //参数需要 当前页 开始位置 每页记录数
        int begin = (page-1)*rows;
        //1. 得到分页的每一页 list
        List<LinkMan> list = linkManService.findPagingJson(begin, rows);
        System.out.println(list);
        //2. 得到总记录数
        int count = linkManService.findCount();
        //3. 把list 和count 组装成需要的map集合
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("rows", list);

        String jxon = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
        HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType("application/json;charset=utf-8");

        response.getWriter().write(jxon);





    }





}
