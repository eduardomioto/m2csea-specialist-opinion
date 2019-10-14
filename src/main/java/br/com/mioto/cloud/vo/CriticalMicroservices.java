package br.com.mioto.cloud.vo;

public class CriticalMicroservices {

    private String microservice;
    private String vision;
    private String order;

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
    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    @Override
    public String toString() {
        return "CriticalMicroservices [microservice=" + microservice + ", vision=" + vision + ", order=" + order + "]";
    }


}
