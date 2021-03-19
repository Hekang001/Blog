package com.kang.blog.service;

import com.kang.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
