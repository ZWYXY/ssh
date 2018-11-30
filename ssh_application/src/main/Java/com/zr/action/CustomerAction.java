package com.zr.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zr.entity.CustomerEntity;
import com.zr.entity.DataDictionary;
import com.zr.entity.PageBean;
import com.zr.service.CustomerService;
import com.zr.service.DDService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerAction extends ActionSupport implements ModelDriven<CustomerEntity> {

    private CustomerService customerService;
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    private CustomerEntity customerEntity = new CustomerEntity();
    @Override
    public CustomerEntity getModel() {
        return customerEntity;
    }

    //属性封装获取 分页的“当前页”
    private Integer currentPage;
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage; }
    public Integer getCurrentPage() {
        return currentPage; }

    //使用 注解注入ddService 属性
    private DDService ddService;
    @Autowired
    public void setDdService(DDService ddService) {
        this.ddService = ddService;
    }

    //1 到添加页面
    public String toAddPage(){
       List<DataDictionary> list = ddService.findAll();
       ServletActionContext.getRequest().setAttribute("dataDictionaryList", list);
       return "toAddPage";
    }

    //2 添加方法
    public String add(){
        customerService.add(customerEntity);
        return  "add";
    }

/*向值栈中放list集合 步骤*/
    //一、定义list变量
    private List<CustomerEntity> list;
    //二、生成变量的get方法
    public List<CustomerEntity> getList() { return list; }

/*向值栈中放对象 步骤*/
    //一、定义对象变量
    private PageBean pageBean;
    //二、生成变量的 get 方法
    public PageBean getPageBean(){
        return  pageBean;
    }

    //3. 查询所有客户信息
    public String list(){
        /**
         * 三、在执行的方法里面向list集合设置值
         *         list = customerService.findAll();
         */
        //三、在执行的方法里面向list设值
        pageBean = customerService.listPage(currentPage);
        return "list";
    }

    //4. 根据cid删除某一客户信息
    public String delete(){
        //使用模型驱动获取cid
        int cid = customerEntity.getCid();
        //删除规范写法：首先根据id查询，之后调用方法删除
        //1 根据id查询
        CustomerEntity c = customerService.findById(cid);
        //判断查询结果
        if(c != null){
            //2不为空调用方法删除
            customerService.delete(c);
        }
        return "delete";
    }

    //5. 修改 根据id查询
    public String showCustomer(){

        //从模型驱动封装获取cid
        int cid = customerEntity.getCid();
        CustomerEntity c = customerService.findById(cid);
        //放到request域里面去
        ServletActionContext.getRequest().setAttribute("customer", c);
        return "showCustomer";
    }

    //6. 更新customer数据
    public String update(){
        customerService.update(customerEntity);
        return "update";
    }

    //7. 分页的方法
    public String listPage(){
        //调用service的方法实现封装
        PageBean pageBean = customerService.listPage(currentPage);
        //放到域对象中
        ServletActionContext.getRequest().setAttribute("pageBean" , pageBean);
        return "listPage";
    }

    //8. 1.条件查询之--根据客户名称查询
    public String listCondition(){
        //如果输入客户的名称，根据客户名称查询
        //如果不输入任何内容，查询所有
        if(customerEntity.getCustName() != null && !"".equals(customerEntity.getCustName())){
            //不为空
            list = customerService.findCondition(customerEntity);
        } else {
            //不输入任何内容查询所有
            list();
        }
         return "listCondition";
    }
    //  2.条件查询之--实现多条件组合查询
    //  1>转到查询客户信息页面
    public String toSelectCustomerPage(){
        return "toSelectCustomerPage";
    }
    //  2>多条件查询
    public String moreCondition(){
        list = customerService.findMoreCondition(customerEntity); //因为之前定义了值栈，所以可以在这边直接使用
        return "moreCondition";
    }

    //9. 按照客户来源 统计客户数量
    public String countBycustSources(){

       list = customerService.countBycustSources();
        return "countBycustSources";
    }

    //10. 按照客户等级 统计客户数量
    public String countBycustLevel(){
        list  = customerService.countBycustLevel();
        return "countBycustLevel";
    }

    //11. 返回所有客户json数据 的方法  tips:需要注意的是 需要使用response返回json 则不能在 struts.xml中配置结果集
    public void returnAllCustomerByJson() throws IOException {
        //查询所有客户
        List<CustomerEntity> list = customerService.findAll();
        //将查询出的list 集合转换成map集合 使其在转换成json后能够被easyUI的datagrid 识别
        Map<String, Object> map = new HashMap<>();
        map.put("total", list.size());
        map.put("rows", list);
        //使用fastJson 将map集合转换成json
        String  string = JSON.toJSONString(map);

        //调用 response 将 json返回给前端页面
        HttpServletResponse response= ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        //在前端页面中显示 json
        response.getWriter().write(string);
    }

    //属性封装 得到当前datagrid传递的值
    private int page; //当前页
    private int rows;
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

    //12. dataGrid 分页实现
    public void dataGridPaging() throws IOException {
        //返回分页数据 需要 total:总记录数  rows:分页数据  的json格式
        //参数需要 当前页 开始位置 每页记录数
        int begin = (page-1)*rows;
        //1 得到分页list集合
        List<CustomerEntity> list = customerService.findPageJson(begin, rows);
        //2 得到总记录数
        int count = customerService.findCount();
        //3 把list和count 构造成需要的list集合
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        map.put("rows", list);
        //使用fastJson 将map集合转换成json
        String  string = JSON.toJSONString(map);

        //调用 response 将 json返回给前端页面
        HttpServletResponse response= ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        //在前端页面中显示 json
        response.getWriter().write(string);
    }






}
