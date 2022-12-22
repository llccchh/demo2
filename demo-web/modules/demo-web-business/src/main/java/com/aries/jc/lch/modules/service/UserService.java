package com.aries.jc.lch.modules.service;

import com.aries.jc.common.BaseResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lichanghao6
 */
public interface UserService {
    void javaBean2yaml2(HttpServletResponse httpServletResponse) throws IOException;
}
