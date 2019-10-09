package br.com.mioto.cloud.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.mioto.cloud.vo.UserVision;

public interface UserVisionDAO {

    public List<UserVision> getAllUserVision() throws SQLException;

    public void storeUserVision(UserVision userVision) throws SQLException;

    public void updateUserVision(UserVision userVision) throws SQLException;
}
