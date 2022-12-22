package com.aries.jc.lch.modules.controller;

import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.entity.Animal;
import com.aries.jc.lch.modules.repository.mapper.AnimalMapper;
import com.aries.jc.lch.modules.repository.vo.AnimalTypeVO;
import com.aries.jc.lch.modules.repository.vo.AnimalVO;
import com.aries.jc.lch.modules.service.AnimalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lichanghao6
 */
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Resource
    private AnimalService animalService;

    @Resource
    private AnimalMapper animalMapper;

    @GetMapping
    public BaseResult<AnimalVO> saveMessage() {
        return animalService.saveMessage();
    }

    @GetMapping("/findAll")
    @ResponseBody
    public BaseResult<List<AnimalVO>> findAllAnimals() {
        return animalService.findAllAnimals();
    }

    @GetMapping("/findAnimalById")
    @ResponseBody
    public BaseResult<List<Animal>> findAnimalById(@RequestParam("id") Integer id) {
        LambdaQueryWrapper<Animal> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
        objectLambdaQueryWrapper.eq(Animal::getId,id);
        return BaseResult.success(animalMapper.selectList(objectLambdaQueryWrapper));
        //return BaseResult.success(animalMapper.selectById(id));
    }

    @GetMapping("findAnimalType")
    @ResponseBody
    public BaseResult<AnimalTypeVO> findAnimalType(@RequestParam("id") Integer id) {
        return animalService.findAnimalType(id);
    }


//    public List<Student> getStudent(){
//
//        //
//        LambdaQueryWrapper<Student> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
////        objectLambdaQueryWrapper.eq(Student::getId, 1);
//        objectLambdaQueryWrapper.ge(Student::getId,1);
//
//        return studentMapper.selectList(objectLambdaQueryWrapper);
//    }

}
