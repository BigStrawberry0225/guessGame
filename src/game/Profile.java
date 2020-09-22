package game;

import com.jdbc.conn.ConnectionClass;
import com.jdbc.conn.InfoOperate;

public class Profile {

    InfoOperate userInfo = new InfoOperate(new ConnectionClass());
    String[] rank = new String[userInfo.getUserNum()];

    public Profile(){
    }

    public String[] getRank() {
        return userInfo.getUserRank();
    }

    public int getRank(String userID){
        return userInfo.getRank(userID);
    }
    public int getPoint(String userID){
        return userInfo.getPoint(userID);
    }

    public int getUserNum(){
        return userInfo.getUserNum();
    }
}
