package com.tfl.handler;

import com.alibaba.fastjson.JSON;
import com.tfl.domain.ResponseResult;
import com.tfl.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(200, "授权过程报错");
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(responseResult));
    }
}
