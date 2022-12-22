package com.aries.jc.lch.modules.controller;

import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.vo.AdministratorVO;
import com.aries.jc.lch.modules.service.AdministratorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lichanghao6
 */
@RestController
@RequestMapping("administrator")
public class AdministratorController {

    @Resource
    private AdministratorService administratorService;

    @GetMapping("findAdminByAnimalId")
    @ResponseBody
    public BaseResult<AdministratorVO> findAdminByAnimalId(@RequestParam("id") Integer id) {
        return administratorService.findAdminByAnimalId(id);
    }

}
