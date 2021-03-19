package com.kang.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**统一异常处理
 *
 * 拦截所有的异常 使其跳转到自定义的错误中
 */
//会拦截所有标注有Controller的控制器
@ControllerAdvice
public class ControllerExceptionHandler {
//获取用户日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request URL : {}，Exception : {}", request.getRequestURL(),e);

        /**
         * 根据ResponseStatus决定是否需要抛出异常
         */
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL()); //获取url
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }
}
