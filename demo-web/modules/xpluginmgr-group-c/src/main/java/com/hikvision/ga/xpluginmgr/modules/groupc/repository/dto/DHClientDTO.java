package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;


import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotNull;

/**
 * @author liuyinghao5
 */
public class DHClientDTO {
    @NotNull
    @JSONField(
            name = "PublicKey"
    )
    private String publicKey;
    @NotNull
    @JSONField(
            name = "P"
    )
    private String p;
    @NotNull
    @JSONField(
            name = "G"
    )
    private String g;
    @NotNull
    @JSONField(
            name = "AES-supports"
    )
    private String supports;
    @NotNull
    @JSONField(
            name = "SecuSID"
    )
    private String secuSIDA;
    @JSONField(
            name = "CurveType"
    )
    private String curveType;

    public DHClientDTO() {
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getP() {
        return this.p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getG() {
        return this.g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getSupports() {
        return this.supports;
    }

    public void setSupports(String supports) {
        this.supports = supports;
    }

    public String getSecuSIDA() {
        return this.secuSIDA;
    }

    public void setSecuSIDA(String secuSIDA) {
        this.secuSIDA = secuSIDA;
    }

    public String getCurveType() {
        return this.curveType;
    }

    public void setCurveType(String curveType) {
        this.curveType = curveType;
    }
}
