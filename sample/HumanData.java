package sample;

import java.sql.*;

public class HumanData {
    public void addHuman(Human one) throws SQLException {

        Connection con=Jdbc_Gragon.getConnection();
        Statement stmt=con.createStatement();
        String sql=" insert into Human" +"(Huser,Hpassword,Hname,Hsex,Hmoney) values("+"?,?,?,?,?)";
        PreparedStatement ptmt =con.prepareStatement(sql);//加载SQL语句,预编译


        ptmt.setString(1,one.getCount());    //传参给预编译符去赋值
        ptmt.setString(2,one.getPassword());
        ptmt.setString(3,one.getName());
        ptmt.setString(4,one.getSex());
        ptmt.setString(5,one.getMoney());
        ptmt.execute();  //执行SQL语句




    }

    public void updatHuman(){

    }

    public void delHuman(){

    }

    public Human queryHuman(String user) throws SQLException {
        Connection con=Jdbc_Gragon.getConnection();
        String sql=" select * from Human"+" where  Huser= ? ";
        PreparedStatement ptmt =con.prepareStatement(sql);

        ptmt.setString(1,user);
        ResultSet rs= ptmt.executeQuery();  //执行SQL语句
        Human human=new Human();
        while(rs.next()) {
            human.setCount(rs.getString("Huser"));
            human.setPassword(rs.getString("Hpassword"));
            human.setName(rs.getString("Hname"));
            human.setSex(rs.getString("Hsex"));
            human.setMoney(rs.getString("Hmoney"));
        }

        return human;
    }
}
