package entsdksamples.soi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.postgresql.Driver;

public class PgSOI {

    String _host = "192.168.1.34";
    String _port = "5432";
    String _db = "cityccde";
    String _user = "postgre";
    String _pwd = "esri@123";

    public PgSOI(String citycode){

    }

    public String QueryCityName(String citycode){
        String cityname = "null";
        Connection c;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(String.format("jdbc:postgresql://%s:%s/%s", _host, _port, _db),
                            "postgres", "123");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM citycode where citycode = '"+citycode+"'");
            while (rs.next()){
                cityname = rs.getString("ciyename");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/postgresql/postgresql_java.html

        return cityname;
    }
}
