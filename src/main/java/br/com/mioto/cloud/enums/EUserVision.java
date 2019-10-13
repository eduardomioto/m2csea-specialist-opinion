package br.com.mioto.cloud.enums;

public enum EUserVision {

    UV_1   (1,"Dependência de outros serviços","Tempo de Resposta"),
    UV_2   (2,"Dependência de outros serviços","Cobertura de Testes Unitários"),
    UV_3   (3,"Dependência de outros serviços","Débito Técnico"),
    UV_4   (4,"Dependência de outros serviços","Consumo de Recursos Computacionais"),
    UV_5   (5,"Dependência de outros serviços","Disponibilidade"),
    UV_6   (6,"Tempo de Resposta","Cobertura de Testes Unitários"),
    UV_7   (7,"Tempo de Resposta","Débito Técnico"),
    UV_8   (8,"Tempo de Resposta","Consumo de Recursos Computacionais"),
    UV_9   (9,"Tempo de Resposta","Disponibilidade"),
    UV_10  (10,"Cobertura de Testes Unitários","Débito Técnico"),
    UV_11  (11,"Cobertura de Testes Unitários","Consumo de Recursos Computacionais"),
    UV_12  (12,"Cobertura de Testes Unitários","Disponibilidade"),
    UV_13  (13,"Débito Técnico","Consumo de Recursos Computacionais"),
    UV_14  (14,"Débito Técnico","Disponibilidade"),
    UV_15  (15,"Consumo de Recursos Computacionais","Disponibilidade");

    public int id;
    public String valorA;
    public String valorB;

    EUserVision(int id, String valorA, String valorB) {
        this.setId(id);
        this.setValorA(valorA);
        this.setValorB(valorB);
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getValorA() {
        return valorA;
    }


    public void setValorA(String valorA) {
        this.valorA = valorA;
    }


    public String getValorB() {
        return valorB;
    }


    public void setValorB(String valorB) {
        this.valorB = valorB;
    }


    public static EUserVision getEnum(Integer id) {
        for (final EUserVision element : EUserVision.values()) {
            if(element.getId() == id) {
                return element;
            }
        }
        return null;
    }
}
