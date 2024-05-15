import Data.InteractWithData;
import Object.Vocabulary;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws SQLException, ParseException {
        Vocabulary v=new Vocabulary("superrr","adj");
        v.setSector("thelittleprince");
        InteractWithData.insert(v);

    }
}
