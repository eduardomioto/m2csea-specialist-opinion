package br.com.mioto.cloud.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.mioto.cloud.vo.UserVision;

public interface UserVisionBO {

    public List<UserVision> getAllUserVision() throws SQLException;

    public void storeUserVision(List<UserVision> userVision) throws SQLException;
}
