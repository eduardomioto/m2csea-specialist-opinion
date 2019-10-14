package br.com.mioto.cloud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.mioto.cloud.dao.CriticalityDAO;
import br.com.mioto.cloud.vo.CriticalityVO;

@Component
public class CriticalityDAOImpl extends BaseDAOImpl implements CriticalityDAO  {

    @Override
    public List<CriticalityVO> getMostCriticalMicroservices() throws SQLException {
        final Connection conn =  getConnection();
        final String query = "select * from criticality_factor";

        // (microservice, factor, value, vision, dt_transaction) VALUES(?, ?, ?, ?, NOW())

        final PreparedStatement preparedStmt = conn.prepareStatement(query);
//        preparedStmt.setString (1, vo.getMicroservice());
//        preparedStmt.setInt (2, vo.getCriticalityFactor());
//        preparedStmt.setString (3, vo.getValue());
//        preparedStmt.setString (4, vo.getVision());

        preparedStmt.execute();
        conn.close();

        conn.close();

        return null;

    }
}
