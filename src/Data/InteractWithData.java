package Data;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

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

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sqll);
            ArrayList<Integer> art=new ArrayList<>();
            while (rs.next()) {
                String tableName = rs.getString(1);
                String query = "SELECT `index` FROM `" + tableName + "`";
                try (Statement statement = con.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        int ind = resultSet.getInt("index");
                        art.add(ind);
                    }
                }
            }
            Collections.sort(art);
            int nIndex=-1;
            if(art.get(art.size()-1)==art.size()-1){
                nIndex=art.size();
            }else{
                for(int i=0;i<art.size();i++){
                    if(art.get(i)!=i){
                        nIndex=i;
                        break;
                    }
                }
            }
            for(int i:art){
                System.out.print(i+" ");
            }


            rs.close();
            stmt.close();
            con.close();
            con=JDBCUtil.getConnection();

            voca.setIndex(nIndex);
            String sql = "INSERT INTO "+ voca.getSector() +" (`index`, word, type, time_learn, day_learn) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1,nIndex);
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