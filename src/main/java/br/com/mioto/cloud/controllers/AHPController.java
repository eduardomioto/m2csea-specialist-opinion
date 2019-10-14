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

import br.com.mioto.cloud.bo.UserVisionBO;
import br.com.mioto.cloud.vo.UserVision;

@CrossOrigin
@RestController
public class AHPController {

    private static final Logger log = LoggerFactory.getLogger(AHPController.class);

    @Autowired
    private UserVisionBO userVisionBO;

    @RequestMapping(value = "/microservices/ahp/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserVision>> calculateAHP() {
        log.info("AHPController >> calculateAHP");
        List<UserVision> listVisions = new ArrayList<>();
        try {
            listVisions = userVisionBO.getAllUserVision();


        } catch (final Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
        return new ResponseEntity<List<UserVision>>(listVisions, HttpStatus.OK);
    }


}
