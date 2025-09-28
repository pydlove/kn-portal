package com.aiocloud.ruankao.portal.web.ruankao.service;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkUser;
import com.aiocloud.ruankao.portal.web.ruankao.service.impl.TokenServiceImpl;

/**
 *
 * @description: TokenService.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 20:55
 */
public interface TokenService {

    void storeToken(String token, RkUser user);

    TokenServiceImpl.TokenInfo validateToken(String token);

    Boolean removeToken();
}
