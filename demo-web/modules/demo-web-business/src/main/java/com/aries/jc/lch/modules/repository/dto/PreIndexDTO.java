package com.aries.jc.lch.modules.repository.dto;

/**
 * @author lichanghao6
 */
public class PreIndexDTO {

    private String presetName;
    private String presetIndex;
    private String cameraIndexCode;

    public PreIndexDTO(String presetName, String presetIndex, String cameraIndexCode) {
        this.presetName = presetName;
        this.presetIndex = presetIndex;
        this.cameraIndexCode = cameraIndexCode;
    }

    public PreIndexDTO() {
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public String getPresetIndex() {
        return presetIndex;
    }

    public void setPresetIndex(String presetIndex) {
        this.presetIndex = presetIndex;
    }

    public String getCameraIndexCode() {
        return cameraIndexCode;
    }

    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode;
    }

    @Override
    public String toString() {
        return "PreIndexDTO{" +
                "presetName='" + presetName + '\'' +
                ", presetIndex='" + presetIndex + '\'' +
                ", cameraIndexCode='" + cameraIndexCode + '\'' +
                '}';
    }
}
