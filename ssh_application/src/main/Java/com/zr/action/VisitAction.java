package com.zr.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zr.entity.CustomerEntity;
import com.zr.entity.UserEntity;
import com.zr.entity.VisitEntity;
import com.zr.service.CustomerService;
import com.zr.service.UserService;
import com.zr.service.VisitService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Scope("prototype")
@Controller
public class VisitAction extends ActionSupport implements ModelDriven<VisitEntity> {

    //使用模型驱动封装 获取表单数据
    private VisitEntity visitEntity = new VisitEntity();
    @Override
    public VisitEntity getModel() {
        return visitEntity;
    }

    //注入VisitService属性
    private VisitService visitService;
    @Autowired
    public void setVisitService(VisitService visitService){
        this.visitService = visitService;
    }

    //注入CustomerService属性
    private CustomerService customerService;
    @Autowired
    public void setCustomerService(CustomerService customerService){
        this.customerService = customerService;
    }

    //注入UserService属性
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


    //转到跳转页面
    public String toAddPage(){
        //查找所有Customer 目的：为了在下拉列表中显示所有联系人姓名
        List<CustomerEntity> customerList = customerService.findAll();
        List<UserEntity> userList = userService.findAll();

        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("customerList", customerList);
        request.setAttribute("userList", userList);

        return "toAddPage";
    }
    //保存 客户拜访信息
    public String add(){
        visitService.save(visitEntity);
        return "add";
    }
    //到list页面
    public String list(){
        List<VisitEntity> visitEntityList = visitService.findAll();
        ServletActionContext.getRequest().setAttribute("visitEntityList", visitEntityList);
        return "list";
    }
}

