package com.aries.jc.lch.modules.service.impl;

import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.dao.AnimalDao;
import com.aries.jc.lch.modules.repository.entity.Animal;
import com.aries.jc.lch.modules.repository.vo.AnimalTypeVO;
import com.aries.jc.lch.modules.repository.vo.AnimalVO;
import com.aries.jc.lch.modules.service.AnimalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lichanghao6
 */
@Service
public class AnimalServiceImpl implements AnimalService {

    @Resource
    private AnimalDao animalDao;

    @Override
    public BaseResult<AnimalVO> saveMessage() {
        Animal animal = new Animal();
        animal.setAdminId(1);
        animal.setName("aaaaa");
        animal.setType(1);
        animalDao.save(animal);
        return BaseResult.success(null);
    }

    @Override
    public BaseResult<List<AnimalVO>> findAllAnimals() {
        return BaseResult.success(animalDao.findAllAnimals());
    }

    @Override
    public BaseResult<Animal> findAnimalById(Integer id) {
        return BaseResult.success(animalDao.findAnimalById(id));
    }

    @Override
    public BaseResult<AnimalTypeVO> findAnimalType(Integer id) {
        AnimalTypeVO vo = animalDao.findAnimalType(id);
        if (vo == null) {
            return BaseResult.fail("555", "没有该动物信息");
        }

        Animal animal = animalDao.findAnimalById(id);
        animal.setVisitNum(animal.getVisitNum() + 1);
        animalDao.save(animal);

        return BaseResult.success(animalDao.findAnimalType(id));
    }

}
