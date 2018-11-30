package Java;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Demo2 {

    @Test
    public void forTestCollection(){
        //集合
        List list = new ArrayList<>();
        Set set = new HashSet<>();
        Collection collection1 = new ArrayList();
        Collection collection2 = new HashSet();
        //map  //map的key 和 value 都可以为null 但是不能把map 本身作为一个key或者value添加给自身
        Map<String , Integer> map = new HashMap<>();

        list.add("element1");
        list.add("element2");
        list.add(0,"element4");

        list.add("element3");
        list.addAll(list); //list 可以把自己装进去
        list.addAll(list);
        System.out.println(list.size());


       System.out.println(list.indexOf("element1"));

        Iterator iterator = list.iterator();
        if(!list.isEmpty()){
            System.out.println("list 不为空");
        }
        while(iterator.hasNext()) {
            Object element = iterator.next();
            System.out.println("remove:" + element );
            iterator.remove();
        }


        if (!iterator.hasNext()) {
            System.out.print("no more element!" + "\t");
        }
        if (list.isEmpty()){
        System.out.println("list is Empty");
        }



    }

}
