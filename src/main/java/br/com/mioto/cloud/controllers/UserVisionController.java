package br.com.mioto.cloud.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mioto.cloud.bo.FinderBO;
import br.com.mioto.cloud.bo.UserVisionBO;
import br.com.mioto.cloud.vo.Microservice;
import br.com.mioto.cloud.vo.UserVision;

@CrossOrigin
@RestController
public class UserVisionController {

    private static final Logger log = LoggerFactory.getLogger(UserVisionController.class);

    @Autowired
    private UserVisionBO userVisionBO;

    @Autowired
    private FinderBO finderBO;

    @RequestMapping(value = "/microservices/vision/user/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserVision>> getUserVision() {
        log.info("UserVisionController >> getUserVision");
        List<UserVision> listMicroservices = new ArrayList<>();
        
        Iterable<Microservice> microservicesFullList = finderBO.findAll();
        // This call should be made to finder by rest

        try {
            listMicroservices = userVisionBO.getAllUserVision();

            if(listMicroservices != null && !listMicroservices.isEmpty()){
                for (Microservice micro: microservicesFullList) {
                    boolean microserviceFound = false;
                    for (UserVision microservice: listMicroservices) {
                        if(microservice.getMicroservice().equals(micro.getName())){
                            microserviceFound = true;
                            break;
                        }
                    }
                    if(!microserviceFound){
                        UserVision userVision = new UserVision();
                        userVision.setRating(0);
                        userVision.setMicroservice(micro.getName());
                        listMicroservices.add(userVision);
                    }
                }
            }else{
                for (Microservice microservice: microservicesFullList) {
                    UserVision userVision = new UserVision();
                    userVision.setRating(0);
                    userVision.setMicroservice(microservice.getName());
                    listMicroservices.add(userVision);
                }
            }

        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
        return new ResponseEntity<List<UserVision>>(listMicroservices, HttpStatus.OK);
    }

    @RequestMapping(value = "/microservices/vision/user/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity  postUserVision(@RequestBody UserVision userVision) {
        log.info("UserVisionController >> postUserVision");
        log.info("userVision: {}", userVision);
        try {
            userVisionBO.storeUserVision(userVision);
        } catch (SQLException e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @RequestMapping(value = "/microservices/vision/user/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity  updateUserVision(@RequestBody UserVision userVision) {
        log.info("UserVisionController >> updateUserVision");
        log.info("userVision: {}", userVision);
        try {
            userVisionBO.updateUserVision(userVision);
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
}
