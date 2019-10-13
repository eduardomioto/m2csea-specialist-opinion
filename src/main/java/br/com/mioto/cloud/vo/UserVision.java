package br.com.mioto.cloud.vo;

public class UserVision {

    private Integer userId;
    private Integer visionComparisonId;
    private String visionA;
    private String visionB;
    private Double rating;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVisionComparisonId() {
        return visionComparisonId;
    }

    public void setVisionComparisonId(Integer visionComparisonId) {
        this.visionComparisonId = visionComparisonId;
    }

    public String getVisionA() {
        return visionA;
    }

    public void setVisionA(String visionA) {
        this.visionA = visionA;
    }

    public String getVisionB() {
        return visionB;
    }

    public void setVisionB(String visionB) {
        this.visionB = visionB;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
