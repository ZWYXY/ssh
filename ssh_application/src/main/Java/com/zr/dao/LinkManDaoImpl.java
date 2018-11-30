package com.zr.dao;

import com.zr.entity.CustomerEntity;
import com.zr.entity.LinkMan;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Scope(value = "prototype")
@Repository(value = "LinkManDao")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {

    @Resource // 注入sessionFactory的方式
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override //多条件组合查询
    public List<LinkMan> findMoreCondition(LinkMan linkMan) {
/*

//方法一：hql 语句拼接
    Integer i = 0;
    List<Object> list = new ArrayList<>();
    String hql = "from LinkMan where 1 = 1";
    if(linkMan.getLkmName() != null && !"".equals(linkMan.getLkmName())){
        hql = hql + " and lkmName = ?" + i.toString();
        list.add(linkMan.getLkmName());
        i = i + 1;
    }
    if (linkMan.getCustomerEntity().getCid() != null && linkMan.getCustomerEntity().getCid() > 0) {
        hql = hql + " and customerEntity.cid = ?" + i.toString() ;
        list.add(linkMan.getCustomerEntity().getCid());
    }
    return (List<LinkMan>) this.getHibernateTemplate().find(hql, list.toArray());
 */
        //方法二：使用离线对象查询
            //第一步：获取离线对象
        DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
            //第二步：判断       为想要查询的对象的属性设值 并将其添加到criteria中
        if(linkMan.getLkmName() != null && !"".equals(linkMan.getLkmName())){
            criteria.add(Restrictions.eq("lkmName", linkMan.getLkmName()));
        }
        if(linkMan.getLkmName() != null && !"".equals(linkMan.getLkmName())){
            criteria.add
            (Restrictions.eq("customerEntity.cid", linkMan.getCustomerEntity().getCid()));
        }//第三步：使用离线对象查询
        return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    //分页的实现
    @Override
    public List<LinkMan> findPage(int begin, int rows) {
        //第二种 使用离线对象和hibernateTemplate的方法实现
        //1 创建离线对象 设置对哪个实体类进行操作
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        //2 调用hibernateTemplate的findByCriteria
        List<LinkMan> list =
                (List<LinkMan>)this.getHibernateTemplate()
                        .findByCriteria(detachedCriteria, begin, rows);
      return list;
    }

    //查询 linkman 总数
    @Override
    public int findCount() {
        List list =  this.getHibernateTemplate().find("select count(*) from LinkMan");
        if(list !=null && list.size() != 0 ){
            Object obj = list.get(0);
            //obj 转换成int 类型
            Long lObj = (Long) obj;
            return lObj.intValue();
        }
        return 0;
    }

    /*
    @Override // 添加联系人
    public void add(LinkMan linkMan) {
        this.getHibernateTemplate().save(linkMan);
    }

    @Override // 查找所有联系人
    public List<LinkMan> listLinkMan() {
        return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan ");
    }

    @Override // 按ID查找联系人
    public LinkMan findOne(int lkmId) {
        return this.getHibernateTemplate().get(LinkMan.class, lkmId);
    }

    @Override // 更新联系人信息
    public void update(LinkMan linkMan) {
        this.getHibernateTemplate().update(linkMan);
    }

    @Override  //删除联系人
    public void delete(LinkMan linkMan) {
        this.getHibernateTemplate().delete(linkMan);
    }

*/
}
