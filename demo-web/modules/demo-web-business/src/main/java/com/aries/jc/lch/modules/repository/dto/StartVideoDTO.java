package com.aries.jc.lch.modules.repository.dto;

/**
 * @author lichanghao6
 */
public class StartVideoDTO {

    private String cameraIndexCode;
    private Integer recordType;
    private Integer type;

    public StartVideoDTO(String cameraIndexCode, Integer recordType, Integer type) {
        this.cameraIndexCode = cameraIndexCode;
        this.recordType = recordType;
        this.type = type;
    }

    public StartVideoDTO() {
    }

    public String getCameraIndexCode() {
        return cameraIndexCode;
    }

    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CameraVideoDTO{" +
                "cameraIndexCode='" + cameraIndexCode + '\'' +
                ", recordType=" + recordType +
                ", type=" + type +
                '}';
    }
}
