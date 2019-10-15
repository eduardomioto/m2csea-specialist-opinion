package br.com.mioto.cloud.bo.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mioto.cloud.bo.AhpBO;
import br.com.mioto.cloud.bo.CriticalityBO;
import br.com.mioto.cloud.dao.CriticalityDAO;
import br.com.mioto.cloud.external.AHP;
import br.com.mioto.cloud.vo.CriticalityCompleteVO;
import br.com.mioto.cloud.vo.CriticalityVO;

/**
 * The Class CriticalityBOImpl.
 */
@Component
public class CriticalityBOImpl implements CriticalityBO {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(CriticalityBOImpl.class);

    /** The criticality DAO. */
    @Autowired
    private CriticalityDAO criticalityDAO;

    @Autowired
    private AhpBO AhpBO;

    /**
     * Save criticality.
     *
     * @param vo the vo
     * @throws SQLException the SQL exception
     */
    @Override
    public List<CriticalityVO> getMostCriticalMicroservices() throws SQLException {
        return criticalityDAO.getMostCriticalMicroservices();
    }

    public List<CriticalityCompleteVO> check() throws SQLException{

        final AHP ahp = AhpBO.calculateAHP();
        //obter os pesos de cada entrada

        this.getMostCriticalMicroservices();


        return null;
    }

}
