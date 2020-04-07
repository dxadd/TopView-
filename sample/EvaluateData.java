package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluateData {



    public void addEvaluate(Evaluate one) throws SQLException {

        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();

        String sql="insert into evaluate (erace,etime,eclass,econtent,eHuman) values (?,?,?,?,?)";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(1,one.getRace());    //传参给预编译符去赋值
        ptmt.setString(2,one.getTime());
        ptmt.setString(3,one.getGrade());
        ptmt.setString(4,one.getContent());
        ptmt.setString(5,one.getAuthor());
        ptmt.execute();  //执行SQL语句

    }

    public void updateEvaluate(Evaluate one) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" update Evaluate set" +" etime=?,eclass=?,econtent=? ,erace=? ,eHuman=? where  eno=?";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译

       //传参给预编译符去赋值

        ptmt.setString(1,one.getTime());
        ptmt.setString(2,one.getGrade());
        ptmt.setString(3,one.getContent());
        ptmt.setString(4,one.getRace());
        ptmt.setString(5,one.getAuthor());
        ptmt.setString(6,one.getNo());
        ptmt.execute();  //执行SQL语句
    }

    public void delEvaluate(String id) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" delete from Evaluate " +"where eno=? ";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译

        //传参给预编译符去删除
        ptmt.setString(1,id);
        ptmt.execute();  //执行SQL语句
    }

    public Evaluate queryEvaluate_Id(String id) throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from Evaluate " + "where eno=? ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(1,id);
        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        Evaluate evaluatey=null;
        while (rs.next()) {
            evaluatey=new Evaluate();
            evaluatey.setNo(rs.getString("eno"));
            evaluatey.setRace(rs.getString("eRace"));
            evaluatey.setTime(rs.getString("eTime"));
            evaluatey.setGrade(rs.getString("eclass"));
            evaluatey.setContent(rs.getString("econtent"));
            evaluatey.setAuthor(rs.getString("eHuman"));

        }
        return evaluatey;
    }

    public List<Evaluate> queryEvaluate_Race(String Race) throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from Evaluate " + "where eRace=? ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ptmt.setString(1,Race);
        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        List<Evaluate> Eva=new ArrayList<>();
        Evaluate evaluate=null;
        while (rs.next()) {
            evaluate=new Evaluate();
            evaluate.setNo(rs.getString("eno"));
            evaluate.setRace(rs.getString("eRace"));
            evaluate.setTime(rs.getString("eTime"));
            evaluate.setGrade(rs.getString("eclass"));
            evaluate.setContent(rs.getString("econtent"));
            evaluate.setAuthor(rs.getString("eHuman"));
            Eva.add(evaluate);

        }
        return Eva;
    }

    public List<Evaluate> queryEvaluate_User(String User) throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from Evaluate " + "where eHuman=? ";
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译
        ptmt.setString(1,User);
        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        List<Evaluate> Eva=new ArrayList<>();
        Evaluate evaluate=null;
        while (rs.next()) {
            evaluate=new Evaluate();
            evaluate.setNo(rs.getString("eno"));
            evaluate.setRace(rs.getString("eRace"));
            evaluate.setTime(rs.getString("eTime"));
            evaluate.setGrade(rs.getString("eclass"));
            evaluate.setContent(rs.getString("econtent"));
            evaluate.setAuthor(rs.getString("eHuman"));
            Eva.add(evaluate);

        }
        return Eva;
    }

    public List<Evaluate> queryAll() throws SQLException {
        Connection con = Jdbc_Gragon.getConnection();
        Statement stmt = con.createStatement();
        String sql = " select * from Evaluate" ;
        PreparedStatement ptmt = con.prepareStatement(sql);//加载SQL语句,预编译

        ResultSet rs = ptmt.executeQuery();  //执行SQL语句
        List<Evaluate> Eva=new ArrayList<>();
        Evaluate evaluate=null;
        while (rs.next()) {
            evaluate=new Evaluate();
            evaluate.setNo(rs.getString("eno"));
            evaluate.setRace(rs.getString("eRace"));
            evaluate.setTime(rs.getString("eTime"));
            evaluate.setGrade(rs.getString("eclass"));
            evaluate.setContent(rs.getString("econtent"));
            evaluate.setAuthor(rs.getString("eHuman"));
            Eva.add(evaluate);

        }
        return Eva;
    }

    public static void main(String[] args) {

    }

}
