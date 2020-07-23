package com.aaa.utils;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @program: qy-109-project
 * @author: xiafengchi
 * @create: 2020/7/10 17:09
 **/

public class DataUtils {

    private DataUtils() {
    }

    /**
    * @Author:xfc
    * @Description:
     *      获取系统当前时间 格式yyyy-MM-dd HH:mm:ss
    * @Date: 2020/7/18 9:19
    * @return: java.lang.String
    **/
    public static String getNewDate(){
        Date dd=new Date();
        //格式化
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sim.format(dd);
        return time;
    }

}
