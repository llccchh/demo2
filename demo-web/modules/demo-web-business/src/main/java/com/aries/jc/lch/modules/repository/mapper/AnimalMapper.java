package com.aries.jc.lch.modules.repository.mapper;

import com.aries.jc.lch.modules.repository.entity.Animal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author lichanghao6
 */

@Mapper
@Component
public interface AnimalMapper extends BaseMapper<Animal> {
}
