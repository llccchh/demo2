package com.aries.jc.lch.modules.service;

import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.entity.Animal;
import com.aries.jc.lch.modules.repository.vo.AnimalTypeVO;
import com.aries.jc.lch.modules.repository.vo.AnimalVO;

import java.util.List;

/**
 * The interface Animal service.
 *
 * @author lichanghao6
 */
public interface AnimalService {

    BaseResult<AnimalVO> saveMessage();

    BaseResult<List<AnimalVO>> findAllAnimals();

    BaseResult<Animal> findAnimalById(Integer id);

    BaseResult<AnimalTypeVO> findAnimalType(Integer id);
}
