package br.com.mioto.cloud.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.mioto.cloud.vo.CriticalityVO;

public interface CriticalityBO {

    List<CriticalityVO> getMostCriticalMicroservices(String days) throws SQLException;
}
