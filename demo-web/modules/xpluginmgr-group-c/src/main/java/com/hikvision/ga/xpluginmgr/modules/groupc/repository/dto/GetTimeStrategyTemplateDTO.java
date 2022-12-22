package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

/**
 * @author liuyinghao5
 * @date 2022/11/10 12:22
 */

public class GetTimeStrategyTemplateDTO {

    private Integer id;

    private Integer templateId;

    public GetTimeStrategyTemplateDTO(Integer id, Integer templateId) {
        this.id = id;
        this.templateId = templateId;
    }

    public GetTimeStrategyTemplateDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "GETTimeStrategyTEMPLATEDTO{" +
                "id=" + id +
                ", templateId=" + templateId +
                '}';
    }
}
