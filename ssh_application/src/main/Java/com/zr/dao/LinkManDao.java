package com.zr.dao;

import com.zr.entity.LinkMan;

import java.util.List;

public interface LinkManDao extends BaseDao<LinkMan> {
   /**
    * LinkManDao provide some good ways that connect to DBMS.
    *
    *
    *
    * @author zhourui
    * @since 1.0.1
    */

    List<LinkMan> findMoreCondition(LinkMan linkMan);

    List<LinkMan> findPage(int begin, int rows);

    int findCount();

    /*
    void add(LinkMan linkMan);

    LinkMan findOne(int lkmId);//按Id查询

    List<LinkMan> listLinkMan();//查询所有联系人

    void update(LinkMan linkMan);

    void delete(LinkMan linkMan);
*/
}
