package com.tfl.service;

import com.tfl.domain.LoginUser;
import com.tfl.domain.ResponseResult;
import com.tfl.domain.User;
import com.tfl.util.JwtUtil;
import com.tfl.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginServcie{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        // 通过authentication进行用户验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 验证通过则会返回对象 验证不通过则返回空。
        if(ObjectUtils.isEmpty(authenticate)){
            throw new RuntimeException("登陆失败");
        }
        // 如果认证通过了 则使用userid生成一个jwt 返回给客户端
         LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        redisCache.setCacheObject("login:"+id,loginUser);
        // 把用户的完整信息存入redis里 userid作为key
        return new ResponseResult(200,map);
    }

    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
       LoginUser loginUser =  (LoginUser)authentication.getPrincipal();
        User user = loginUser.getUser();
// 删除redis中的值
        redisCache.deleteObject("token:"+user.getId());

        return new ResponseResult(200,"退出登录");
    }
}
