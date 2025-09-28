
package com.aiocloud.ruankao.portal.web.ruankao.vo;


import com.aiocloud.kn.portal.dao.ruankao.dto.RkMenuDTO;
import com.aiocloud.kn.portal.dao.system.dto.KnMenuDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 * @description: KnMenusVO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-07-25 16:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RkMenuVO extends RkMenuDTO {

    private List<RkMenuVO> childrenMenu;
}
