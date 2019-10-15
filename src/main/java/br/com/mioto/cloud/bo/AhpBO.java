package br.com.mioto.cloud.bo;

import java.sql.SQLException;

import br.com.mioto.cloud.external.AHP;

public interface AhpBO {

    public AHP calculateAHP() throws SQLException;

}
