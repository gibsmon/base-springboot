package com.poliymorf.dagaitem.controller.configController;

import com.poliymorf.dagaitem.data.annotaion.IgnoreResponseBinding;
import com.poliymorf.dagaitem.data.common.BaseResponse;
import com.poliymorf.dagaitem.data.enums.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice(basePackages = "com.poliymorf.dagaitem.controller")
@Slf4j
public final class CustomResponseAdvise implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getContainingClass().isAnnotationPresent(RestController.class)) {
            if (!Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreResponseBinding.class)) {

                if (o instanceof List || o instanceof Map) {
                    return buildSuccessResponse(o);
                } else if (o instanceof ResponseEntity<?>) {
                    log.info("Response is instance of response entity");
                    return o;
                } else if (o instanceof InputStreamResource) {
                    log.info("Response is instance of InputStreamResource");
                    return o;
                } else if (o instanceof byte[]) {
                    log.info("Response is instance of byte[]");
                    return o;
                } else {
//                    BaseResponse<?> baseResponse = BeanMapper.map(o, BaseResponse.class);
//                    if (Objects.isNull(baseResponse.getCode()))
                        return buildSuccessResponse(o);
//                    return o;
                }
            }
        }
        return o;
    }

    public static BaseResponse<?> buildSuccessResponse(Object data) {
        return BaseResponse.builder()
                .code(Message.SUCCESS.code)
                .message(Message.SUCCESS.message)
                .data(data)
                .build();
    }

}
