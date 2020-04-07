package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityData {
    public void addActivity(Activity one) throws SQLException {

        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();

        String sql=" insert into Activity" +"(Ano,Arace,Atime,Athem,"+
                "Acontent,Aend) values("+"?,?,?,?,?,?)";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译


        ptmt.setString(1,one.getNo());    //传参给预编译符去赋值
        ptmt.setString(2,one.getRace());
        ptmt.setString(3,one.getStartTime());
        ptmt.setString(4,one.getTheme());
        ptmt.setString(5,one.getContent());
        ptmt.setString(6,one.getEndTime());
        ptmt.execute();  //执行SQL语句

    }

    public void updatActivity(Activity one) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" update Activity set" +" Arace=?,Atime=?,Athem=?,Acontent=?,Aend=? where Ano=? ";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(6,one.getNo());    //传参给预编译符去赋值
        ptmt.setString(1,one.getRace());
        ptmt.setString(2,one.getStartTime());
        ptmt.setString(3,one.getTheme());
        ptmt.setString(4,one.getContent());
        ptmt.setString(5,one.getEndTime());
        ptmt.execute();  //执行SQL语句
    }

    public void delActivity(String id) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" delete from Activity " +"where Ano=? ";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译

        //传参给预编译符去删除
        ptmt.setString(1,id);
        ptmt.execute();  //执行SQL语句
    }

    public Activity queryActivity_Id(String id) throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from Activity " + "where Ano=? ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(1,id);
        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        Activity activity=null;
        while (rs.next()) {
            activity=new Activity();
            activity.setNo(rs.getString("Ano"));
            activity.setRace(rs.getString("Arace"));
            activity.setStartTime(rs.getString("Atime"));
            activity.setTheme(rs.getString("Athem"));
            activity.setContent(rs.getString("Acontent"));
            activity.setEndTime(rs.getString("Aend"));

        }
        return activity;
    }

    public List<Activity> queryActivity_Race(String Race) throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from Activity " + "where Arace=? ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(1,Race);
        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        List<Activity> Act=new ArrayList<>();
        Activity activity;
        while (rs.next()) {
            activity=new Activity();
            activity.setNo(rs.getString("Ano"));
            activity.setRace(rs.getString("Arace"));
            activity.setStartTime(rs.getString("Atime"));
            activity.setTheme(rs.getString("Athem"));
            activity.setContent(rs.getString("Acontent"));
            activity.setEndTime(rs.getString("Aend"));
            Act.add(activity);
        }
        return Act;
    }

    public List<Activity> queryAll() throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from Activity " ;
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        List<Activity> activities=new ArrayList<>();
        Activity activity;
        while (rs.next()) {
            activity=new Activity();
            activity.setNo(rs.getString("Ano"));
            activity.setRace(rs.getString("Arace"));
            activity.setStartTime(rs.getString("Atime"));
            activity.setTheme(rs.getString("Athem"));
            activity.setContent(rs.getString("Acontent"));
            activity.setEndTime(rs.getString("Aend"));
            activities.add(activity);
        }
        return activities;
    }
}
