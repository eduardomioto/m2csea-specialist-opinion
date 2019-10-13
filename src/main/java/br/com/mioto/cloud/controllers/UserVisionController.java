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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mioto.cloud.bo.UserVisionBO;
import br.com.mioto.cloud.vo.UserVision;

@CrossOrigin
@RestController
public class UserVisionController {

    private static final Logger log = LoggerFactory.getLogger(UserVisionController.class);

    @Autowired
    private UserVisionBO userVisionBO;

    @RequestMapping(value = "/microservices/vision/user/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserVision>> getUserVision() {
        log.info("UserVisionController >> getUserVision");
        List<UserVision> listVisions = new ArrayList<>();


        try {
            listVisions = userVisionBO.getAllUserVision();

        } catch (final Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
        return new ResponseEntity<List<UserVision>>(listVisions, HttpStatus.OK);
    }

    @RequestMapping(value = "/microservices/vision/user/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity  postUserVision(@RequestBody List<UserVision> userVision) {
        log.info("UserVisionController >> postUserVision");
        log.info("userVision: {}", userVision);
        try {
            userVisionBO.storeUserVision(userVision);
        } catch (final SQLException e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    public static void main(String[] args) {
        final List<UserVision> userVisionList =  new ArrayList<UserVision>();
        final UserVision uservision = new UserVision();
        uservision.setUserId(1);
        uservision.setRating(1.25);
        uservision.setVisionComparisonId(1);
        userVisionList.add(uservision);
        userVisionList.add(uservision);
        userVisionList.add(uservision);
        userVisionList.add(uservision);
        userVisionList.add(uservision);
        userVisionList.add(uservision);
        userVisionList.add(uservision);
        userVisionList.add(uservision);

        final ObjectMapper Obj = new ObjectMapper();
        try {
            final String jsonStr = Obj.writeValueAsString(userVisionList);
            System.out.println(jsonStr);
        } catch (final JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
