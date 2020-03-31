package sample;

import java.sql.*;

public class Jdbc_Gragon {

    private static String url = "jdbc:mysql://localhost:3306/dragonnest?serverTimezone=UTC";
    private static String username = "root" ;
    private static String password = "D13727763751" ;

    private static Connection con=null;
    static{
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2.获得数据库的连接

        try {
            con= DriverManager.getConnection(url , username , password ) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return con;
    }

    public static void main(String[] args) throws SQLException {

        //操纵数据库
        Statement stmt=con.createStatement(); // 建立一个对象，通过con来执行SQL语句，实现增删查改
        ResultSet rs=stmt.executeQuery("select * from Human");//通过创建ResultSet 的对象结束MySQL返回的对象

        while(rs.next()){
            System.out.println(rs.getString("Huser")+","+rs.getString("Hpassword")+","+rs.getString("Hname")+","+rs.getString("Hsex"));
        }




    }

}
