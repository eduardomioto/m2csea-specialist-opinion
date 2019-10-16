package br.com.mioto.cloud.controllers;

import java.util.ArrayList;
import java.util.List;

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

import br.com.mioto.cloud.bo.CriticalityBO;
import br.com.mioto.cloud.vo.CriticalityVO;

@CrossOrigin
@RestController
public class CriticalityController {

    private static final Logger log = LoggerFactory.getLogger(CriticalityController.class);

    @Autowired
    private CriticalityBO criticalityBO;

    @RequestMapping(value = "/microservices/criticality/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CriticalityVO>> getMostCriticalMicroservices() {
        log.info("AHPController >> getMostCriticalMicroservices");
        List<CriticalityVO> listVisions = new ArrayList<>();
        try {
            listVisions = criticalityBO.getMostCriticalMicroservices();


        } catch (final Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
        return new ResponseEntity<List<CriticalityVO>>(listVisions, HttpStatus.OK);
    }


}
