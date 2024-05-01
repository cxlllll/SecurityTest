package com.tfl.handler;

import com.alibaba.fastjson.JSON;
import com.tfl.domain.ResponseResult;
import com.tfl.util.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(200, "认证过程报错");
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(responseResult));
    }
}
