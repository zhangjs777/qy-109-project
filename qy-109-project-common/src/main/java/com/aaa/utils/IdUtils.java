package com.aaa.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/18 9:13
 * @description:
 **/

public class IdUtils {
    private IdUtils(){

    }

    /**
    * @Author:xfc
    * @Description:
     *           获取UUID
    * @Date: 2020/7/18 9:14
    * @return: java.lang.String
    *
    **/
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Long getLongID(){
        // 1.获取系统当前时间的毫秒数
        Long timeMillis = System.currentTimeMillis();
        // 2.创建Random对象
        Random random = new Random();
        // 3.做一个随机数，随机区间是0-999之间随机
        Integer randomNum = random.nextInt(999);
        // 4.生成最终的id(当前系统时间的毫秒数+随机数 来实现)
        /**
         * %:占位符
         * d:数字
         * 03:三位数，如果不够三位自动向前补0
         */
        Long id = Long.valueOf(timeMillis + String.format("%03d", randomNum));
        return id;
    }

}
