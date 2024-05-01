package com.tfl.service;


import com.tfl.domain.ResponseResult;
import com.tfl.domain.User;

public interface LoginServcie {
    ResponseResult login(User user);

    ResponseResult logout();

}
