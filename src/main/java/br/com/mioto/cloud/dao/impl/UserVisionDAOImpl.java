package br.com.mioto.cloud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.mioto.cloud.dao.UserVisionDAO;
import br.com.mioto.cloud.vo.UserVision;

@Component
public class UserVisionDAOImpl extends BaseDAOImpl implements UserVisionDAO  {

   @Override
public List<UserVision> getAllUserVision() throws SQLException {
       final Connection conn =  getConnection();
       final String query = "SELECT " +
               "    uv.vision_comparison_id, " +
               "    uv.value as rating, " +
               "    vi.vision_name as visionA , " +
               "    vie.vision_name as visionB " +
               "from \r\n" +
               "    user_visions as uv, " +
               "    vision_comparison as comp, " +
               "    visions as vi, " +
               "    visions as vie " +
               "where \r\n" +
               "    comp.main_vision = vi.vision_id and " +
               "    comp.compared_vision = vie.vision_id and " +
               "    comp.id = uv.vision_comparison_id";
       final Statement st = conn.createStatement();
       final ResultSet rs = st.executeQuery(query);


       final List<UserVision> listUserVision = new ArrayList<>();
       while (rs.next())
       {
           final UserVision userVision = new UserVision();
           userVision.setVisionA(rs.getString("visionA"));
           userVision.setVisionB(rs.getString("visionB"));
           userVision.setVisionComparisonId(rs.getInt("vision_comparison_id"));
           userVision.setRating(rs.getDouble("rating"));
           listUserVision.add(userVision);
       }
       st.close();
       return listUserVision;
   }

    @Override
    public void storeUserVision(UserVision userVision) throws SQLException {
        final Connection conn =  getConnection();
        final String query = "INSERT INTO user_visions (user_id, vision_comparison_id, value) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE vision_comparison_id=?, value=?";

        final PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt (1, userVision.getUserId());
        preparedStmt.setInt (2, userVision.getVisionComparisonId());
        preparedStmt.setDouble (3, userVision.getRating());
        preparedStmt.setInt (4, userVision.getVisionComparisonId());
        preparedStmt.setDouble (5, userVision.getRating());
        preparedStmt.execute();
        conn.close();
    }
}
