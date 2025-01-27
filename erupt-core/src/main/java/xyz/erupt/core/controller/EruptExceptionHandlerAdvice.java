package xyz.erupt.core.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.erupt.core.exception.EruptApiErrorTip;
import xyz.erupt.core.exception.EruptWebApiRuntimeException;
import xyz.erupt.core.view.EruptApiModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YuePeng
 * date 2020-09-30
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class EruptExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(EruptWebApiRuntimeException.class)
    public Map<String, Object> eruptWebApiRuntimeException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("error", "erupt web api error");
        map.put("message", e.getMessage());
        map.put("timestamp", new Date());
        map.put("path", request.getServletPath());
        map.put("status", response.getStatus());
        e.printStackTrace();
        return map;
    }

    @ExceptionHandler(EruptApiErrorTip.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public EruptApiModel eruptApiErrorTip(EruptApiErrorTip e) {
        e.eruptApiModel.setErrorIntercept(false);
        return e.eruptApiModel;
    }

}
