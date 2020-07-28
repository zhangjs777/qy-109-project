package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.DictionaryMapper;
import com.aaa.model.Dictionary;
import com.aaa.redis.RedisService;
import com.aaa.utils.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.aaa.staticproperties.RedisProperties.EX;
import static com.aaa.staticproperties.RedisProperties.NX;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/16 10:27
 * @description:
 **/

@Service
public class DictService extends BaseService<Dictionary> {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    private final static String DICT_KEY = "dictListKey";


    /**
    * @Author:xfc
    * @Description:
     *          查询所有的字典信息
    * @Date:2020/7/16
    * @param pageNo:
     * @param pageSize:
     * @param redisService:
    * @return: com.github.pagehelper.PageInfo<com.aaa.model.Dictionary>

    **/
    public PageInfo<Dictionary> getAllDict(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, RedisService redisService){

        //1.根据前台传来的pageNo，pageSize 进行分页
        PageHelper.startPage(pageNo,pageSize);
        //2. 先去redis中查询数据，看数据是否存在缓存中
        //定义一个字符串用来接收从redis中查出来的数据
        String dictList="";
        // 从redis中查询数据
        try {
            dictList = redisService.getString(DICT_KEY);
        } catch (Exception e){
            System.out.println("redis获取key的过程中出现异常！");
            //异常处理,即redis获取key的过程中出现异常后，重查一遍
            dictList = redisService.getString(DICT_KEY);
            //对数据进行判断
            if ("".equals(dictList) || null == dictList){
                //值为空，redis异常导致查询失败
                return null;
            } else {
                //查询到了值，对数据分页，返回数据到上一层
                return new PageInfo<Dictionary>(JSONUtils.toList(dictList, Dictionary.class));
            }
        }
        //没有异常发生，程序正常运行
        //对数据进行判断
        if ("".equals(dictList) || null == dictList){
            //值为空，redis中没数据，去数据库中查询数据，存入到redis中
            List<Dictionary> allDict = dictionaryMapper.selectAll();
            //判断查出的dictList是否有值
            if (0 < allDict.size()){
                //说明查询成功，把数据存入redis中
                //对redis的set方法做异常处理
                String setResult = "";
                try {
                    setResult = redisService.set(DICT_KEY, allDict, NX, EX, 1800);
                    System.out.println("setResult的结果值"+setResult);
                } catch (Exception e2){
                    System.out.println("redis做set时出现异常");
                    //重新set一次
                    redisService.set(DICT_KEY, allDict,NX, EX, 1800);
                }
                //判断最终redis是否set数据成功
                if ("OK".equals(setResult.toUpperCase())){
                    //true 成功 返回数据的分页结果
                    //必须从redis中取数据，再返回，否则会报指针异常
                    List<Dictionary> dicts = JSONUtils.toList(redisService.getString(DICT_KEY), Dictionary.class);
                    return new PageInfo<Dictionary>(dicts);
                } else {
                    //失败
                    return null;
                }
            } else {
                //说明数据库中没有数据
                return null;
            }
        } else {
            //从redis中查询到了值，对数据分页，返回数据到上一层
            return new PageInfo<Dictionary>(JSONUtils.toList(dictList, Dictionary.class));
        }

    }

    /**
    * @Author:xfc
    * @Description:
     *      新增一条字典数据
    * @Date:2020/7/16
    * @param dictionary:
     * @param redisService:
    * @return: java.lang.Boolean
    **/
    public Boolean addDict(Dictionary dictionary, RedisService redisService) throws Exception{

        //调用BaseService中的新增方法
        Integer addResult=super.add(dictionary);
        //判断新增结果
        if (0<addResult){
            //说明添加数据成功
            redisService.delOne(dictionary);
            //返回true
            return true;
        }else {
            //添加失败返回false
            return false;
        }

    }


    /**
    * @Author:xfc
    * @Description:
     *          更新数据字典
    * @Date:2020/7/16
    * @param dictionary:
     * @param redisService:
    * @return: java.lang.Boolean
    **/
    public Boolean updateDict(Dictionary dictionary, RedisService redisService)throws Exception{
        //调用BaseService中的更新方法
        Integer updateResult=super.update(dictionary);
        //判断更新结果
        if (0<updateResult){
            //说明更新成功
            redisService.delOne(DICT_KEY);
            //返回true;
            return true;
        }else {
            //更新失败，返回false
            return false;
        }
    }


    /**
    * @Author:xfc
    * @Description:
     *          删除字典数据
    * @Date:2020/7/16
    * @param dictionary:
     * @param redisService:
    * @return: java.lang.Boolean
    *
    **/
    public Boolean delDict(Dictionary dictionary, RedisService redisService)throws Exception{
        //调用BaseService中的删除方法
        Integer delResult=super.delete(dictionary);
        //判断删除结果
        if (0<delResult){

            redisService.delOne(DICT_KEY);
            return true;
        }else{
            return false;
        }
    }


}
