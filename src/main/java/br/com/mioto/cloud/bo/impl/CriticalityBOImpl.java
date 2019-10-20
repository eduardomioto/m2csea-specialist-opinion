package br.com.mioto.cloud.bo.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mioto.cloud.bo.AhpBO;
import br.com.mioto.cloud.bo.CriticalityBO;
import br.com.mioto.cloud.dao.CriticalityDAO;
import br.com.mioto.cloud.external.AHP;
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
    private List<CriticalityVO> getMicroservicesCriticalityFactor(String days) throws SQLException {
        return criticalityDAO.getMicroservicesCriticalityFactor(days);
    }

    @Override
    public List<CriticalityVO> getMostCriticalMicroservices(String days) throws SQLException{

        final AHP ahp = AhpBO.calculateAHP();
        final Map<Integer, Double> map = ahp.getWeightsMap();

        final List<CriticalityVO> lista = this.getMicroservicesCriticalityFactor(days);
        for (final CriticalityVO criticalityVO : lista) {
            final Double weight = map.get(criticalityVO.getVisionId());
            final Double criticalityResult = criticalityVO.getCriticalityFactor() * weight;
            criticalityVO.setCriticalityResult(criticalityResult);
        }

        Collections.sort(lista, Collections.reverseOrder());

        return lista;
    }

}
