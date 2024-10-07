package com.zxm.club.gateway.exception;

import cn.dev33.satoken.exception.SaTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxm.club.gateway.entity.Result;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    private ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 对捕捉到的异常进行Result形式的返回，然后把这个result序列化为字节数组
     * <p>
     * Mono<Void>是什么东西？所以后端返回给前端的是一个序列化后的字节数组吗？
     * mvc处理返回值的时候也是把它变成字节数组吗？ObjectMapper能干什么，这个有什么用？
     * 这段代码是API是webflux的吗？这段代码是响应式编程吗？
     * <p>
     * Mono<Void> 是 Reactor 库中的一种响应式类型，用于表示一个可能在未来某个时刻完成的操作，异步(后台)进行，不会影响主线程
     * ObjectMapper 是 Jackson 库提供的一个核心类，主要用于在 Java 对象和 JSON 之间进行转换。
     * ErrorWebExceptionHandler 是 WebFlux 提供的接口，专门用于处理异步响应中的错误。
     */
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpResponse response = serverWebExchange.getResponse();
        Integer code = 200;
        String message = "全局异常拦截器";
        if (throwable instanceof SaTokenException) {
            code = 401;
            message = "没有相关权限,网关层";
        }

        Result result = Result.fail(code, message);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);  //表示响应体的内容是JSON格式。
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            byte[] bytes = null;
            try {
                bytes = objectMapper.writeValueAsBytes(result);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return dataBufferFactory.wrap(bytes);
        }));
    }
}
