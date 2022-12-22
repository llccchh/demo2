package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

/**
 * @author liuyinghao5
 * @date 2022/11/17 13:48
 */

public class DhRequestHeaderDTO {

    private String serverId;
    private String dk;
    private String sId;

    public DhRequestHeaderDTO(String serverId, String dk, String sId) {
        this.serverId = serverId;
        this.dk = dk;
        this.sId = sId;
    }

    public DhRequestHeaderDTO() {
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getDk() {
        return dk;
    }

    public void setDk(String dk) {
        this.dk = dk;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }
}
