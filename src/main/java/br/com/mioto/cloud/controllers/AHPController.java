package br.com.mioto.cloud.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mioto.cloud.bo.AhpBO;
import br.com.mioto.cloud.external.AHP;
import br.com.mioto.cloud.vo.AHPSimplifiedVO;

@CrossOrigin
@RestController
public class AHPController {

    private static final Logger log = LoggerFactory.getLogger(AHPController.class);

    @Autowired
    private AhpBO ahpBO;

    @RequestMapping(value = "/microservices/ahp/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AHPSimplifiedVO> getAHPCalculus() {
        log.info("AHPController >> getAHPCalculus");
        AHP ahp = null;
        final AHPSimplifiedVO vo = new AHPSimplifiedVO();
        try {
            ahp = ahpBO.calculateAHP();

            final Map<String, Double> map = new HashMap<String, Double>();

            for (final Map.Entry<Integer,Double> entry :ahp.getWeightsMap().entrySet()) {
                map.put(getLabel(entry.getKey()), entry.getValue());
            }


            vo.setNrOfPairwiseComparisons( ahp.getNrOfPairwiseComparisons());
            vo.setConsistencyIndex(ahp.getConsistencyIndex());
            vo.setConsistencyRatio(ahp.getConsistencyRatio());
            vo.setRankingMap(ahp.getRankingMap());
            vo.setWeightsMap(map);

        } catch (final Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    public String getLabel(Integer visionId) {
        String label = null;
        switch (visionId) {
            case 1:
                label = "Dependência de outros serviços";
                break;
            case 2:
                label = "Tempo de Resposta";
                break;
            case 3:
                label = "Cobertura de Testes Unitários";
                break;
            case 4:
                label = "Débito Técnico";
                break;
            case 5:
                label = "Consumo de Recursos Computacionais";
                break;
            case 6:
                label = "Disponibilidade";
                break;
            default:
                break;
        }
        return label;
    }
}
