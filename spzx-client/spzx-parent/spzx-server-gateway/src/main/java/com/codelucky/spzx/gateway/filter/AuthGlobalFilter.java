package com.codelucky.spzx.gateway.filter;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson2.JSON;
import com.codelucky.spzx.model.entity.user.UserInfo;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 过滤用户认证访问方法
     * <p>
     * 认证根据
     * 路径中带有auth
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 获取当前访问的Path
        String path = request.getURI().getPath();
        //api接口，异步请求，校验用户必须登录
        if (antPathMatcher.match("/api/**/auth/**", path)) {
            UserInfo userInfo = this.getUserInfo(request);
            if (userInfo == null) {
                return out(response, ResultCodeEnum.LOGIN_AUTH);
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 相应到浏览器的信息
     *
     * @param response
     * @param resultCodeEnum
     * @return
     */
    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
        // 设置结果集
        Result result = Result.build(null, resultCodeEnum);
        // 将当前ResultCodeEnum转换为String
        String resultCode = JSON.toJSONString(result);
        // 生成数据缓存
        DataBuffer wrap = response.bufferFactory().wrap(resultCode.getBytes(StandardCharsets.UTF_8));
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(wrap));
    }

    /**
     * 根据token获取用户信息
     *
     * @param request
     * @return
     */
    private UserInfo getUserInfo(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst("token");
        if (!StringUtils.isEmpty(token)) {
            String strJSON = redisTemplate.opsForValue().get("user:login:" + token);
            if (!StringUtils.isEmpty(strJSON)) {
                return JSON.parseObject(strJSON, UserInfo.class);
            }
        }
        return null;
    }
}
