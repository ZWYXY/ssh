package com.zr.action;


import com.opensymphony.xwork2.ActionSupport;
import com.zr.entity.UserEntity;
import com.zr.service.UserService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class UserAction extends ActionSupport {

    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    //属性封装获取username和password
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //登录的方法
    public String login(){
        //封装到实体类对象
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        //调用service方法实现
        UserEntity userExist = userService.login(user);
        //判断
        if(userExist != null){
            //成功
            //使用session保持登录状态
            HttpServletRequest request = ServletActionContext.getRequest();
            request.getSession().setAttribute("userEntity", userExist);
            return "login_success";
        } else {
            //失败
            return "login";
        }
    }


}
