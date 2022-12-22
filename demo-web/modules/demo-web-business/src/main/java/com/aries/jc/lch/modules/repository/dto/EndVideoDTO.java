package com.aries.jc.lch.modules.repository.dto;

/**
 * @author lichanghao6
 */
public class EndVideoDTO {

    private String cameraIndexCode;
    private String taskId;
    private Integer type;

    public EndVideoDTO(String cameraIndexCode, String taskId, Integer type) {
        this.cameraIndexCode = cameraIndexCode;
        this.taskId = taskId;
        this.type = type;
    }

    public EndVideoDTO() {
    }

    public String getCameraIndexCode() {
        return cameraIndexCode;
    }

    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EndVideoDTO{" +
                "cameraIndexCode='" + cameraIndexCode + '\'' +
                ", taskId=" + taskId +
                ", type=" + type +
                '}';
    }
}
