package Data;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Object.Vocabulary;
public class InteractWithData {
    public static InteractWithData getInstance() {
        return new InteractWithData();
    }

    public static int insert(Vocabulary voca) throws SQLException, ParseException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet tables = null;

        try {
            String str;
          con = JDBCUtil.getConnection();
            String sqll = "SHOW TABLES";
            int maxIndex = -999;
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sqll);

            while (rs.next()) {
                String tableName = rs.getString(1);
                String query = "SELECT MAX(`index`) AS max_value FROM `" + tableName + "`";
                try (Statement statement = con.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {
                    if (resultSet.next()) {
                        int maxValue = resultSet.getInt("max_value");
                        if (maxValue > maxIndex) {
                            maxIndex = maxValue + 1;
                        }
                    }
                }

            }

            rs.close();
            stmt.close();
            con.close();
            con=JDBCUtil.getConnection();


            voca.setIndex(maxIndex);
            String sql = "INSERT INTO "+ voca.getSector() +" (`index`, word, type, time_learn, day_learn) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, maxIndex);
            pst.setString(2, voca.getWord());
            pst.setString(3, voca.getType());
            pst.setInt(4, voca.getTimeLearn());
            java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(voca.getDayLearn());
            pst.setDate(5, new java.sql.Date(date.getTime()));
            int check = pst.executeUpdate();
            if (check > 0) {
                System.out.println("Insert Success!!!");
            }
            return check;
        } finally {
            JDBCUtil.closeResultSet(tables);
            JDBCUtil.closeStatement(pst);
            JDBCUtil.closeConnection(con);
        }
    }
}