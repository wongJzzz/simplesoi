package entsdksamples.soi;

import java.sql.*;
import java.util.*;

import org.postgresql.Driver;

public class PgSOI {

    public Map<String, String> _IdName;

    public  PgSOI(Map<String, String> _IdCode) throws SQLException {
        _IdName = new Hashtable<>();
        QueryCityName(_IdCode);
    }

    public Connection ConnPG(){
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            String _host = "192.168.1.34";
            String _db = "CityCode";
            String _user = "postgres";
            String _port = "5432";
            String _pwd = "esri@123";
            c = DriverManager
                    .getConnection(String.format("jdbc:postgresql://%s:%s/%s", _host, _port, _db),
                            _user, _pwd);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return c;
    }

    public void QueryCityName(Map<String, String> _IdCode) throws SQLException {
        Connection c = ConnPG();
        String cityname = null;
        if(c!=null){
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            for (Object key : _IdCode.keySet()) {
                Object val = _IdCode.get(key);
                String citycode = val.toString();
                ResultSet rs = stmt.executeQuery("SELECT cityname FROM city WHERE citycide = '" + citycode + "'");
                while (rs.next()){
                    cityname = rs.getString("cityname");
                }
                _IdName.put((String) key, cityname);
                rs.close();
            }
            stmt.close();
            c.close();
        }
    }


}
