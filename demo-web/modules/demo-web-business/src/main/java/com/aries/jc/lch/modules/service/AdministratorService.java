package com.aries.jc.lch.modules.service;

import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.vo.AdministratorVO;

/**
 * The interface Administrator service.
 *
 * @author lichanghao6
 */
public interface AdministratorService {

    /**
     * Find admin by animal id base result.
     *
     * @param id the id
     * @return the base result
     */
    BaseResult<AdministratorVO> findAdminByAnimalId(Integer id);
}
