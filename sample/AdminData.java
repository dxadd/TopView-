package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminData {


    public AdminData(){
    }

    public void addAdmin(Admin one) throws SQLException {

        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();

        String sql=" insert into administrator" +"(Gid,Gname,Gspecies,Gsex,"+
                    "Gbirthday,Gage,Gintro,Ghealth,Guser,Ghpassword) values("+"?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译


        ptmt.setString(1,one.getId());    //传参给预编译符去赋值
        ptmt.setString(2,one.getName());
        ptmt.setString(3,one.getSpecies());
        ptmt.setString(4,one.getSex());
        ptmt.setString(5,one.getBirthday());
        ptmt.setString(6,one.getAge());
        ptmt.setString(7,one.getIntro());
        ptmt.setString(8,one.getHealth());
        ptmt.setString(9,one.getCount());
        ptmt.setString(10,one.getPassword());
        ptmt.execute();  //执行SQL语句




    }

    public void updatAdmin(Admin one) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" update administrator set" +" Gname=?,Gspecies=?,Gsex=?,Gbirthday=?,Gage=?,Gintro=?,Ghealth=?,Guser=?,Ghpassword=? where Gid=? ";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译


        //传参给预编译符去赋值
        ptmt.setString(1,one.getName());
        ptmt.setString(2,one.getSpecies());
        ptmt.setString(3,one.getSex());
        ptmt.setString(4,one.getBirthday());    //传参给预编译符去赋值
        ptmt.setString(5,one.getAge());
        ptmt.setString(6,one.getIntro());
        ptmt.setString(7,one.getHealth());
        ptmt.setString(8,one.getCount());
        ptmt.setString(9,one.getPassword());
        ptmt.setString(10,one.getId());

        ptmt.execute();  //执行SQL语句



    }

    public void delAdmin(String id) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" delete from administrator " +"where Gid=? ";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译

        //传参给预编译符去删除
        ptmt.setString(1,id);
        ptmt.execute();  //执行SQL语句
    }

    public Admin queryAdmin(String id) throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from administrator " + "where Gid=? ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(1,id);
        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        Admin dragon = new Admin();
        while (rs.next()) {
            dragon.setId(rs.getString("Gid"));
            dragon.setName(rs.getString("Gname"));
            dragon.setSpecies(rs.getString("Gspecies"));
            dragon.setSex(rs.getString("Gsex"));
            dragon.setBirthday(rs.getString("Gbirthday"));
            dragon.setAge(rs.getString("Gage"));
            dragon.setIntro(rs.getString("Gintro"));
            dragon.setHealth(rs.getString("Ghealth"));
            dragon.setCount(rs.getString("Guser"));
            dragon.setPassword(rs.getString("Ghpassword"));
        }
        return dragon;
    }

    public List<Admin> queryAdmin_Race(String race) throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from administrator " + "where Gspecies=? ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(1,race);
        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        List<Admin> dr=new ArrayList<Admin>();
        Admin dragon;
        while (rs.next()) {
            dragon=new Admin();
            dragon.setId(rs.getString("Gid"));
            dragon.setName(rs.getString("Gname"));
            dragon.setSpecies(rs.getString("Gspecies"));
            dragon.setSex(rs.getString("Gsex"));
            dragon.setBirthday(rs.getString("Gbirthday"));
            dragon.setAge(rs.getString("Gage"));
            dragon.setIntro(rs.getString("Gintro"));
            dragon.setHealth(rs.getString("Ghealth"));
            dragon.setCount(rs.getString("Guser"));
            dragon.setPassword(rs.getString("Ghpassword"));
            dr.add(dragon);

        }
        return dr;
    }

    public List<Admin> queryAll() throws SQLException {

        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();

        String sql = " select * from administrator ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ResultSet rs = ptmt.executeQuery();  //执行SQL语句

        List<Admin> dr=new ArrayList<Admin>();
        Admin dragon;

        while (rs.next()) {
            dragon=new Admin();
            dragon.setId(rs.getString("Gid"));
            dragon.setName(rs.getString("Gname"));
            dragon.setSpecies(rs.getString("Gspecies"));
            dragon.setSex(rs.getString("Gsex"));
            dragon.setBirthday(rs.getString("Gbirthday"));
            dragon.setAge(rs.getString("Gage"));
            dragon.setIntro(rs.getString("Gintro"));
            dragon.setHealth(rs.getString("Ghealth"));
            dragon.setCount(rs.getString("Guser"));
            dragon.setPassword(rs.getString("Ghpassword"));
            dr.add(dragon);

        }
        return dr;
    }

}
