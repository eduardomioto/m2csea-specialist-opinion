package br.com.mioto.cloud.vo;

import br.com.mioto.cloud.bo.impl.AHP;

public class CriticalityCompleteVO {

    private CriticalMicroservices criticalMicroservices;
    private AHP ahp;
    public CriticalMicroservices getCriticalMicroservices() {
        return criticalMicroservices;
    }
    public void setCriticalMicroservices(CriticalMicroservices criticalMicroservices) {
        this.criticalMicroservices = criticalMicroservices;
    }
    public AHP getAhp() {
        return ahp;
    }
    public void setAhp(AHP ahp) {
        this.ahp = ahp;
    }
    @Override
    public String toString() {
        return "CriticalityVO [criticalMicroservices=" + criticalMicroservices + ", ahp=" + ahp + "]";
    }


}
