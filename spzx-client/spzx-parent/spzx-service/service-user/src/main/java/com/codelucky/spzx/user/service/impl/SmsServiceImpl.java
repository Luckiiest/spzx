package com.codelucky.spzx.user.service.impl;

import com.codelucky.spzx.user.service.SmsService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.codelucky.spzx.util.HttpUtils.doPost;


@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 发送短信验证码
     *
     * @param phone
     */
    @Override
    public void sendValidateCode(String phone) {
        String validateCode = null;
        // 查询redis中是否已经存储了改手机号对应的验证码
        validateCode = (String) redisTemplate.opsForValue().get("phone:code:" + phone);
        if (!StringUtils.isEmpty(validateCode)) {
            return;
        }
        // 生成验证码
        validateCode = RandomStringUtils.randomNumeric(4);
        // 存储验证码
        redisTemplate.opsForValue().set("phone:code:" + phone, validateCode, 60, TimeUnit.SECONDS);
        // 发送短信
        sendSms(phone, validateCode);
    }

    /**
     * 定义发送短信验证码的方法
     *
     * @param phone
     * @param validateCode
     */
    private void sendSms(String phone, String validateCode) {
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "b887346fd3bc42d797427785656bfdd2";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:" + validateCode);
        bodys.put("template_id", "CST_ptdie100");  //注意，CST_ptdie100该模板ID仅为调试使用，调试结果为"status": "OK" ，即表示接口调用成功，然后联系客服报备自己的专属签名模板ID，以保证短信稳定下发
        bodys.put("phone_number", phone);


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com
             * /aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //需要赠送身份证OCR识别，vip优惠券,技术支持。请直接联系客服。
    //商品说明可以在商品介绍里查看

    /**
     *重要提示：
     *如您的返回结果中，没有我们接口的返回报文，或者连header的信息都打印出来了。可能是您的代码环境未能适配该请求示例。
     *那么，以下两个命令行，您可以二选一，选择一个适合你环境的加入到请求示例中。即可打印我们接口的返回报文。
     *或者直接联系客服  VX 15622205140
     *
     *System.out.println(EntityUtils.toString(response.getEntity()));
     *
     *System.out.println(response.body().string());
     */
}
