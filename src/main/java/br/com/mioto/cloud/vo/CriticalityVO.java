package br.com.mioto.cloud.vo;

public class CriticalityVO {

    private String microservice;
    private String vision;
    private Integer visionId;
    private Integer criticalityFactor;
    private Double criticalityResult;
    private String value;


    public Double getCriticalityResult() {
        return criticalityResult;
    }
    public void setCriticalityResult(Double criticalityResult) {
        this.criticalityResult = criticalityResult;
    }
    public String getMicroservice() {
        return microservice;
    }
    public void setMicroservice(String microservice) {
        this.microservice = microservice;
    }
    public String getVision() {
        return vision;
    }
    public void setVision(String vision) {
        this.vision = vision;
    }
    public Integer getCriticalityFactor() {
        return criticalityFactor;
    }
    public void setCriticalityFactor(Integer criticalityFactor) {
        this.criticalityFactor = criticalityFactor;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Integer getVisionId() {
        return visionId;
    }
    public void setVisionId(Integer visionId) {
        this.visionId = visionId;
    }
    @Override
    public String toString() {
        return "CriticalityVO [microservice=" + microservice + ", vision=" + vision + ", visionId=" + visionId + ", criticalityFactor=" + criticalityFactor
                + ", value=" + value + "]";
    }



}
