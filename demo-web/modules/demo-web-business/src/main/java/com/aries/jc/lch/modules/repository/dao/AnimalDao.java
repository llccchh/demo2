package com.aries.jc.lch.modules.repository.dao;

import com.aries.jc.lch.modules.repository.entity.Animal;
import com.aries.jc.lch.modules.repository.vo.AdministratorVO;
import com.aries.jc.lch.modules.repository.vo.AnimalTypeVO;
import com.aries.jc.lch.modules.repository.vo.AnimalVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Animal dao.
 *
 * @author lichanghao6
 */
@Repository
public interface AnimalDao extends JpaRepository<Animal, Integer> {

    @Query(value = "select new com.aries.jc.lch.modules.repository.vo.AnimalVO(a.id, a.name, a.type, ad.name, ad.age) from Animal as a left join Administrator as ad on a.adminId = ad.id")
    List<AnimalVO> findAllAnimals();

    @Query(value = "select new com.aries.jc.lch.modules.repository.entity.Animal(a.id, a.name, a.type, a.adminId, a.visitNum) from Animal as a where a.id = :id")
    Animal findAnimalById(Integer id);

    @Query(value = "select new com.aries.jc.lch.modules.repository.vo.AdministratorVO(ad.id, ad.name, ad.age) from Animal as a left join Administrator as ad on a.adminId = ad.id where a.id = :id")
    AdministratorVO findAdminByAnimalId(Integer id);

    @Query(value = "select new com.aries.jc.lch.modules.repository.vo.AnimalTypeVO(at.id, at.name, at.hobby, a.name, ad.name, ad.age) from Animal as a left join AnimalType as at on a.type = at.id left join Administrator as ad on ad.id = a.adminId where a.id = :id")
    AnimalTypeVO findAnimalType(Integer id);



}
