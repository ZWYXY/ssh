package com.zr.dao;


import com.zr.entity.CustomerEntity;
import com.zr.utils.MyDateFormat;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.persistence.Tuple;
import javax.xml.crypto.dsig.Transform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerDaoImpl extends BaseDaoImpl<CustomerEntity> implements CustomerDao {

    //查询记录数
    @Override
    public int findCount() {
        //调用hibernateTemplate里面的find方法实现
        List<Object> list = (List<Object>) this.getHibernateTemplate()
                .find("select count(*) from CustomerEntity");
        //从list 中获取值
        if(list != null && list.size() != 0) {
            Object obj = list.get(0);
            //变成int类型
            Long lObj = (Long) obj;
            return  lObj.intValue();
        }
        return 0;
    }

    //分页查询操作
    @Override
    public List<CustomerEntity> findPage(int begin, int pageSize) {
    //hibernate中实现分页查询有两种方式
/*
        //第一种  使用hibernate底层代码实现
            //第一步 得到SeesionFactory
        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
            //第二步 得到session对象
        Session session = sessionFactory.getCurrentSession();

        //设置分页信息
        Query query = session.createQuery("from CustomerEntity ");
        query.setFirstResult(begin);
        query.setMaxResults(pageSize);

        List<CustomerEntity> list = query.list();
*/
    //第二种 使用离线对象和hibernateTemplate的方法实现
        //1 创建离线对象 设置对哪个实体类进行操作
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerEntity.class);
        //2 调用hibernateTemplate的findByCriteria
        List<CustomerEntity> list =
                (List<CustomerEntity>)this.getHibernateTemplate()
                        .findByCriteria(detachedCriteria, begin, pageSize);
        return list;
    }

    //条件查询
    @Override
    public List<CustomerEntity> findCondition(CustomerEntity customerEntity) {
    //三种方式实现
/*
        //法一：
            //创建session使用其 createQuery方法
        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
        Session sesion = sessionFactory.getCurrentSession();

        Query query = sesion.createQuery("from CustomerEntity where custName like ?0");
        query.setParameter(0, "%" + customerEntity.getCustName() + "%" );
        List<CustomerEntity> list = query.list();
*/


/*
        //法二：调用hibernateTemplate.find() 缺点：在查询条件较多时 比较麻烦
        List<CustomerEntity> list = (List<CustomerEntity>) this.getHibernateTemplate().find(
                "from CustomerEntity where custName LIKE ?0",
                       "%"+customerEntity.getCustName()+"%");
*/


        //法三： 开发中常用
        //1使用离线对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerEntity.class);
        //2设置对实体类的那个属性进行操作
        detachedCriteria.add(Restrictions.like
                ("custName", "%" + customerEntity.getCustName() + "%"));
        //3调用hibernateTemplate中的方法
        List<CustomerEntity> list = (List<CustomerEntity>) this.getHibernateTemplate().findByCriteria(detachedCriteria);


/*
        SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        String queryString = "from CustomerEntity where custName like '%"+customerEntity.getCustName()+"%'";
        Query<CustomerEntity> query = session.createQuery(queryString);
        List<CustomerEntity> list = query.list();
*/

        return list;
    }

    //多条件组合查询
    @Override
    public List<CustomerEntity> findMoreCondition(CustomerEntity customerEntity) {
/*
    //方法一：
        //使用hibernateTemplate 中find 方法
        //拼接hql语句
        String hql = "from CustomerEntity where 1 = 1";

        //创建list集合，如果值不为空，把值设置到list里面
        List<Object> p = new ArrayList<>();

        Integer i = 0;
        //判断条件值是否为空，如果不为空拼接hql语句
        if(customerEntity.getCustName() != null && !"".equals(customerEntity.getCustName())){
            //拼接hql
            hql = hql + " and custName = ?" + i.toString();
            //把值设置到list里面去
            p.add(customerEntity.getCustName());
            i = i + 1;
        }
        System.out.println(i);
        if(customerEntity.getCustLevel() != null && !"".equals(customerEntity.getCustLevel())){
            hql = hql + " and custLevel = ?" + i.toString();
            p.add(customerEntity.getCustLevel());
            i = i + 1;
        }
        System.out.println(i);
        if(customerEntity.getCustSource() != null && !"".equals(customerEntity.getCustSource())){
            hql = hql + " and custSource = ?" + i.toString();
            p.add(customerEntity.getCustSource());
        }

        System.out.println("hql: " + hql);
        System.out.println("list: " + p);

        return (List<CustomerEntity>) this.getHibernateTemplate().find(hql, p.toArray());
*/
        //方法二：使用离线对象(个人觉得 比那个拼接字符串的方法方便多了)
        DetachedCriteria criteria = DetachedCriteria.forClass(CustomerEntity.class);

        if(customerEntity.getCustName() != null && !"".equals(customerEntity.getCustName())){
            criteria.add(Restrictions.eq("custName", customerEntity.getCustName()));
        }
//        if (customerEntity.getCustLevel() != null && !"".equals(customerEntity.getCustLevel())){
//            criteria.add(Restrictions.eq("custLevel", customerEntity.getCustLevel()));
//        }
        if(customerEntity.getCustSource() != null && !"".equals(customerEntity.getCustSource())){
            criteria.add(Restrictions.eq("custSource", customerEntity.getCustSource()));
        }

        return (List<CustomerEntity>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    //按照客户来源统计客户数量多少
    @Override
    public List countBycustSources() {

        //根据来源统计的sql语句
        String SQL = "SELECT  count(*) AS num, custSource  FROM customer GROUP BY custSource";

        //因为写复杂语句，hibernate建议直接调用底层sql实现
        //调用底层sql语句 实现按照客户来源查询 的步骤
        /*1. 得到sessionFactory对象 两种方式
           this.getHibernateTemplate().getSessionFactory(); //通过hibernateTemplate得到的session是 与本地线程绑定的session 再在配置文件中配置就会报错
           this.getSessionFactory();
        */
        Session session =this.getSessionFactory().getCurrentSession();
        //2. 创建NativeQuery对象
        NativeQuery nativeQuery = session.createNativeQuery(SQL);
        /*
        * 2.1 放到实体类对象里面，没有对应的实体类
        *  sqlQuery 放到实体类对象里面
        *  sqlQuery.addEntity(实体类class);
        *
        *  在这里 我们是多表 查询 ，查询结果并没有对应的实体类，再写一个对应的实体类没有问题，但是很繁琐
        *  这边我们看到我们的查询结果，只有两个字段，因此可以考虑用map集合封装
        *
        * */

        //2.2 考虑用map集合封装查询结果 这边使用对应的方法，把查询结果转换成对应的结构的类的形式 map是一个interface 因此写它的实现类
//        nativeQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class)); //这个方法在5，3版本之后不再能用

        //3. 调用方法得到结果 list()方法 返回list ,默认每个部分是数组形式

        return nativeQuery.list();
    }

    //按照客户等级统计客户数量
    @Override
    public List<CustomerEntity> countBycustLevel() {

        String sql =
         "select c.各等级客户数量 ,d.level" +
         " from  (select count(*) as 各等级客户数量 , ddId from customer group by ddId) c ,datadictionary d" +
         " where c.ddId = d.ddId";

        Session session = this.getSessionFactory().getCurrentSession();
        NativeQuery nativeQuery = session.createNativeQuery(sql);
        return nativeQuery.list();
    }

    /* 这些 增删该查 等基本方法都坐在BaseDaoImpl中实现
    //添加客户的功能
    @Override
    public void add(CustomerEntity customerEntity) {
        this.getHibernateTemplate().save(customerEntity);
    }

    //删除实体类
    @Override
    public void delete(CustomerEntity customerEntity) {
        this.getHibernateTemplate().delete(customerEntity);
    }

    //更新客户信息
    @Override
    public void update(CustomerEntity customerEntity) {
        this.getHibernateTemplate().update(customerEntity);
    }

    //根据id进行查询
    @Override
    public CustomerEntity findOne(int cid) {
        return this.getHibernateTemplate().get(CustomerEntity.class, cid);
    }

    //查询客户列表功能
    @Override
    @SuppressWarnings("all")
    public List<CustomerEntity> findAll() {
        //find的参数是HQL语句，操作实体类，对大小写敏感
        return (List<CustomerEntity>)this.getHibernateTemplate().find("from CustomerEntity");
    }
*/





}