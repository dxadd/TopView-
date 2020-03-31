package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaceDate {
    public void addRace(Race one) throws SQLException {

        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" insert into Race" +"(Species,Sid,Sname,Sshaikh,Sintro,Sadress,Sarea) values("+"?,?,?,?,?,?,?)";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译


        ptmt.setString(1,one.getSpecies());    //传参给预编译符去赋值
        ptmt.setString(2,one.getId());
        ptmt.setString(3,one.getName());
        ptmt.setString(4,one.getShaikh());
        ptmt.setString(5,one.getIntro());    //传参给预编译符去赋值
        ptmt.setString(6,one.getAdress());
        ptmt.setString(7,one.getArea());



        ptmt.execute();  //执行SQL语句




    }

    public void updatRace(Race one) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" update Race set" +" Species=?,Sname=?,Sshaikh=?,Sintro=?,Sadress=?,Sintro=?where Sid=? ";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译


        //传参给预编译符去赋值
        ptmt.setString(1,one.getSpecies());
        ptmt.setString(2,one.getName());
        ptmt.setString(3,one.getShaikh());
        ptmt.setString(4,one.getIntro());    //传参给预编译符去赋值
        ptmt.setString(5,one.getAdress());
        ptmt.setString(6,one.getIntro());
        ptmt.setString(7,one.getId());

        ptmt.execute();  //执行SQL语句


    }

    public void delHuman(String id) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        String sql=" select * from Race"+" where  Sid= ? ";
        PreparedStatement ptmt =con.prepareStatement(sql);

        ptmt.setString(1,id);
        ptmt.execute();  //执行SQL语句
    }

    public Race queryRace2(String name) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        String sql=" select * from Race"+" where  Sname= ? ";
        PreparedStatement ptmt =con.prepareStatement(sql);

        ptmt.setString(1,name);
        ResultSet rs= ptmt.executeQuery();  //执行SQL语句
        Race race=new Race();
        while(rs.next()) {
            race.setId(rs.getString("Sid"));
            race.setName(rs.getString("Sname"));
            race.setAdress(rs.getString("Sadress"));
            race.setArea(rs.getString("Sarea"));
            race.setIntro(rs.getString("Sintro"));
            race.setShaikh(rs.getString("Sshaikh"));
            race.setSpecies(rs.getString("Species"));
        }
        return race;
    }

    public Race queryRace(String id) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        String sql=" select * from Race"+" where  Sid= ? ";
        PreparedStatement ptmt =con.prepareStatement(sql);

        ptmt.setString(1,id);
        ResultSet rs= ptmt.executeQuery();  //执行SQL语句
        Race race=new Race();
        while(rs.next()) {
            race.setId(rs.getString("Sid"));
            race.setName(rs.getString("Sname"));
            race.setAdress(rs.getString("Sadress"));
            race.setArea(rs.getString("Sarea"));
            race.setIntro(rs.getString("Sintro"));
            race.setShaikh(rs.getString("Sshaikh"));
            race.setSpecies(rs.getString("Species"));
        }
        return race;
    }
    public List<Race> queryAll() throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        String sql=" select * from Race ";
        PreparedStatement ptmt =con.prepareStatement(sql);

        ResultSet rs= ptmt.executeQuery();  //执行SQL语句

        List<Race> R= new ArrayList<>();
        Race race=null;
        while(rs.next()) {
            race=new Race();
            race.setId(rs.getString("Sid"));
            race.setName(rs.getString("Sname"));
            race.setAdress(rs.getString("Sadress"));
            race.setArea(rs.getString("Sarea"));
            race.setIntro(rs.getString("Sintro"));
            race.setShaikh(rs.getString("Sshaikh"));
            race.setSpecies(rs.getString("Species"));
            R.add(race);
        }
        return R;
    }
}
