package com.sv.honda.redis;


import com.sv.honda.entity.ProductEntity;
import com.sv.honda.service.impl.ProductServiceImpl;
import com.alibaba.fastjson.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class LoadData {

//    private Logger logger = Logger.getLogger(LoadData.class);

    @Resource
    StringRedisTemplate stringRedisTemplate;
//    @Resource
//    Jedis jedis;


    @Resource
    private ProductServiceImpl productService;

    @PostConstruct
    public void loadData(){
        new Thread(){
            public void run(){
                while(true){
                    try {
                        Thread.sleep(1000*2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    logger.info("加载数据......");
                    List<ProductEntity> mapList = productService.findAllProducts();
                    //清空缓存数据
                    Set<String> set = stringRedisTemplate.keys("depart-*");
                    for(String key : set){
//                        jedis.del(key);
                        stringRedisTemplate.opsForValue().getOperations().delete(key);
                    }


                    //加载所有缓存数据
                    for (ProductEntity x : mapList) {
//                        String cname = productService.findProductById(x.getProductId()).getProductName();


                        String value = JSONObject.toJSONString(x);
                        stringRedisTemplate.opsForValue().set("depart-"+x.getProductId(),value);
//                        if (stringRedisTemplate.hasKey("depart-"+ cname) == false){
//
//                        }
                    }
                    break;
                }
            }
        }.start();
    }
}
