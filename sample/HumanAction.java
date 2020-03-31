package sample;

import java.sql.SQLException;
import java.util.List;

public class HumanAction {

    public static void main(String[] args) throws SQLException {
        String str="4003";
        DragonData D=new DragonData();
        D.delDragon(str);
//        HumanData H=new HumanData();
//        Human human=H.queryHuman("dxad");
//        System.out.println( human.getCount() + "," +human.getPassword()+","+ human.getName() + "," + human.getSex());
//
//        DragonData D=new DragonData();
//        Dragon d=D.queryDragon("2003");
//        System.out.println(d.getId()+" , "+d.getName()+" , "+d.getSpecies()+" , "+d.getIntro());
//
//        List<Dragon> list=D.queryAll("烈焰龙") ;
//        for(Dragon dr : list){
//            System.out.println(dr.getId()+" , "+dr.getName()+" , "+dr.getSpecies()+" , "+dr.getIntro());
//        }
//
//            AdminData A=new AdminData();
//          Admin one=new Admin();
//
//          A.delAdmin("007");
//
//            one.setId("002");
//            one.setName("付轨");
//            one.setSpecies("暴龙");
//            one.setUser("002");
//            one.setPasswork("123002");
//            one.setHealth("差");
//            one.setIntro("老当益壮");
//            one.setSex("男");
//            one.setAge("421");
//            one.setBirthday("1599=09-23");
//             A.updatAdmin(one);

//        Race r=new Race();
//        RaceDate R=new RaceDate();
//
//        r.setSpecies("比翼鸟");
//        r.setName("比翼鸟");
//        r.setShaikh("002");
//        r.setIntro("无他");
//        r.setAdress("梵音谷");
//        r.setArea("仅仅方圆1公里");
//        r.setId("8000");
//        R.updatRace(r);
//        r=R.queryRace("4000");
//        System.out.println(r.getName());

    }
}
