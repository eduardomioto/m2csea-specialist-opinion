package br.com.mioto.cloud.bo.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mioto.cloud.bo.UserVisionBO;
import br.com.mioto.cloud.dao.UserVisionDAO;
import br.com.mioto.cloud.vo.UserVision;

@Component
public class UserVisionBOImpl implements UserVisionBO {

    @Autowired
    private UserVisionDAO userVisionDAO;

    @Override
    public List<UserVision> getAllUserVision() throws SQLException {
        return  userVisionDAO.getAllUserVision();
    }

    @Override
    public void storeUserVisions(List<UserVision> userVisionList) throws SQLException {
        for (final UserVision userVision : userVisionList) {
            userVisionDAO.storeUserVision(userVision);
        }
    }

    @Override
    public void storeUserVision(UserVision userVision) throws SQLException {
        userVisionDAO.storeUserVision(userVision);
    }

}
