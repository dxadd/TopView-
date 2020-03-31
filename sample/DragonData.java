package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DragonData {

    public void addDragon(Dragon one) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" insert into Dragon" +"(Did,Dname,Dspecies,Dsex,Dbirthday,Dage,Dintro,Dhealth,Dtrainingvarchar) values("+"?,?,?,?,?,?,?,?,?)";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译


            ptmt.setString(1,one.getId());    //传参给预编译符去赋值
            ptmt.setString(2,one.getName());
            ptmt.setString(3,one.getSpecies());
            ptmt.setString(4,one.getSex());
            ptmt.setString(5,one.getBirthday());    //传参给预编译符去赋值
            ptmt.setString(6,one.getAge());
            ptmt.setString(7,one.getIntro());
            ptmt.setString(8,one.getHealth());
            ptmt.setString(9,one.getTraining());

            ptmt.execute();  //执行SQL语句




    }

    public void updatGragon(Dragon one) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" update Dragon set" +" Dname=?,Dspecies=?,Dsex=?,Dbirthday=?,Dage=?,Dintro=?,Dhealth=?,Dtrainingvarchar=? where Did=? ";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译


        //传参给预编译符去赋值
        ptmt.setString(1,one.getName());
        ptmt.setString(2,one.getSpecies());
        ptmt.setString(3,one.getSex());
        ptmt.setString(4,one.getBirthday());    //传参给预编译符去赋值
        ptmt.setString(5,one.getAge());
        ptmt.setString(6,one.getIntro());
        ptmt.setString(7,one.getHealth());
        ptmt.setString(8,one.getTraining());
        ptmt.setString(9,one.getId());

        ptmt.execute();  //执行SQL语句



    }

    public void delDragon(String id) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" delete from Dragon " +"where Did=? ";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译

        //传参给预编译符去删除
        ptmt.setString(1,id);
        ptmt.execute();  //执行SQL语句
    }

    public Dragon queryDragon(String id) throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from Dragon " + "where Did=? ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(1,id);
        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        Dragon dragon = new Dragon();
        while (rs.next()) {
            dragon.setId(rs.getString("Did"));
            dragon.setName(rs.getString("Dname"));
            dragon.setSpecies(rs.getString("Dspecies"));
            dragon.setSex(rs.getString("Dsex"));
            dragon.setBirthday(rs.getString("Dbirthday"));
            dragon.setAge(rs.getString("Dage"));
            dragon.setIntro(rs.getString("Dintro"));
            dragon.setHealth(rs.getString("Dhealth"));
            dragon.setTraining(rs.getString("Dtrainingvarchar"));

        }
        return dragon;
    }
    public List<Dragon> queryAll(String race) throws SQLException {

        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();

        String sql = " select * from Dragon"+" where Dspecies = ? ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(1,race);
        ResultSet rs = ptmt.executeQuery();  //执行SQL语句

        List<Dragon> dr=new ArrayList<Dragon>();
        Dragon dragon=null;

        while (rs.next()) {
            dragon=new Dragon();
            dragon.setId(rs.getString("Did"));
            dragon.setName(rs.getString("Dname"));
            dragon.setSpecies(rs.getString("Dspecies"));
            dragon.setSex(rs.getString("Dsex"));
            dragon.setBirthday(rs.getString("Dbirthday"));
            dragon.setAge(rs.getString("Dage"));
            dragon.setIntro(rs.getString("Dintro"));
            dragon.setHealth(rs.getString("Dhealth"));
            dragon.setTraining(rs.getString("Dtrainingvarchar"));
            dr.add(dragon);

        }
        return dr;
    }
    public List<Dragon> queryAllD() throws SQLException {

        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();

        String sql = " select * from Dragon";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ResultSet rs = ptmt.executeQuery();  //执行SQL语句

        List<Dragon> dr=new ArrayList<Dragon>();
        Dragon dragon=null;

        while (rs.next()) {
            dragon=new Dragon();
            dragon.setId(rs.getString("Did"));
            dragon.setName(rs.getString("Dname"));
            dragon.setSpecies(rs.getString("Dspecies"));
            dragon.setSex(rs.getString("Dsex"));
            dragon.setBirthday(rs.getString("Dbirthday"));
            dragon.setAge(rs.getString("Dage"));
            dragon.setIntro(rs.getString("Dintro"));
            dragon.setHealth(rs.getString("Dhealth"));
            dragon.setTraining(rs.getString("Dtrainingvarchar"));
            dr.add(dragon);

        }
        return dr;
    }
}
