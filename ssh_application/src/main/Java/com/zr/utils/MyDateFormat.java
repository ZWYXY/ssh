package com.zr.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateFormat {

    public String getCurrentDate() {
        // 获取当前时间到毫秒值
        Date d = new Date();
        // 创建日期格式化对象（将日期转化成字符串）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分s秒");
        String str = sdf.format(d);
        return str;
    }

}
