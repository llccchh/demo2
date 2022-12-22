package com.aries.jc.lch.modules.service.impl;

import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.dao.AnimalDao;
import com.aries.jc.lch.modules.repository.vo.AdministratorVO;
import com.aries.jc.lch.modules.service.AdministratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lichanghao6
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Resource
    private AnimalDao animalDao;


    @Override
    public BaseResult<AdministratorVO> findAdminByAnimalId(Integer id) {
        return BaseResult.success(animalDao.findAdminByAnimalId(id));
    }

}
