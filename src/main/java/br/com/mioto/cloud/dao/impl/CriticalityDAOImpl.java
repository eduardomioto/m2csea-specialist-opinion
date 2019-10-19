package br.com.mioto.cloud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.mioto.cloud.dao.CriticalityDAO;
import br.com.mioto.cloud.vo.CriticalityVO;

@Component
public class CriticalityDAOImpl extends BaseDAOImpl implements CriticalityDAO  {

    @Override
    public List<CriticalityVO> getMicroservicesCriticalityFactor(String days) throws SQLException {
        final Connection conn =  getConnectionMetrics();
        final String query = "  SELECT avg(cf.factor) as factor, cf.vision, cf.microservice " +
                "    FROM criticality_factor cf " +
                "    WHERE dt_transaction >= DATE(NOW()) - INTERVAL " + days + " DAY " +
                "    GROUP BY cf.vision, cf.microservice ";

        final Statement st = conn.createStatement();
        final ResultSet rs = st.executeQuery(query);

        final List<CriticalityVO> listUserVision = new ArrayList<CriticalityVO>();

        final PreparedStatement preparedStmt = conn.prepareStatement(query);

        while (rs.next())
        {
            final CriticalityVO vo = new CriticalityVO();
            vo.setCriticalityFactor(rs.getInt("factor"));
            vo.setMicroservice(rs.getString("microservice"));
            vo.setVision(rs.getString("vision"));

            switch (rs.getString("vision")) {
                case "computational-resource-usage":
                    vo.setVisionId(5);
                    break;
                case "response-time":
                    vo.setVisionId(2);
                    break;
                case "availability":
                    vo.setVisionId(6);
                    break;
                case "tech-debit":
                    vo.setVisionId(4);
                    break;
                case "microservices-interdependecies":
                    vo.setVisionId(1);
                    break;
                case "unit-test-coverage":
                    vo.setVisionId(3);
                    break;
                default:
                    break;
            }

            listUserVision.add(vo);
        }
        st.close();
        preparedStmt.execute();
        conn.close();

        return listUserVision;

    }
}
