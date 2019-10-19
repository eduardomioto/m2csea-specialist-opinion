package br.com.mioto.cloud.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.mioto.cloud.vo.CriticalityVO;

public interface CriticalityDAO {

    List<CriticalityVO> getMicroservicesCriticalityFactor(String days) throws SQLException;
}
