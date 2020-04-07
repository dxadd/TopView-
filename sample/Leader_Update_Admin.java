package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//龙母进入管理驯龙高手的页面
public class Leader_Update_Admin {

    @FXML private TextField LeaderTextFile;
    @FXML private TextArea LeaderTextArea;
    //增加驯龙高手
    @FXML
    public void addAdmin(ActionEvent actionEvent) throws SQLException {
        UpdateAdmin admin=new UpdateAdmin();            //在 UpdateAdmin工具类，调用OnAdd方法进行添加
        admin.OnAdd();
    }

    //修改驯龙高手的信息
    @FXML
    public void updateAdmin(ActionEvent actionEvent) throws SQLException {
        UpdateAdmin updateAdmin=new UpdateAdmin();           //在 UpdateAdmin工具类行，调用OnUpadte方法进行添加
        updateAdmin.OnUpdate();
    }

    //查询所有驯龙高手的信息
    @FXML
    public void queryAdmin(ActionEvent actionEvent) throws SQLException {

        AdminData mAdmin = new AdminData();
        //显示所有驯龙高手的所有信息
        List<Admin> list = mAdmin.queryAll();
        for (Admin dr : list) {
            LeaderTextArea.appendText("\n编号->" + dr.getId() + "      姓名" + "-> " + dr.getName() + "            性别 ->" + dr.getSex() +
                    "\n种族 ->" + dr.getSpecies() + "           年龄 -> " + dr.getAge() +
                    "\n生日 -> "+dr.getBirthday()+"        健康状况 -> "+dr.getHealth()+"\n简介 :\n" + dr.getIntro() +
                    "\n账号 -> "+dr.getCount()+"            密码 -> "+dr.getPassword()+"\n");
        }
    }

    //删除驯龙高手
    @FXML
    public void deleteAdmin(ActionEvent actionEvent) throws SQLException {
        Delete delete=new Delete();
        delete.DeleteAdmin();
    }

    //返回上一层
    @FXML
    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("leader.fxml"));
        Parent root = loader.load();
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
    //根据驯龙高手编号查询某个驯龙高手
    @FXML
    public void leaderSearch(ActionEvent actionEvent) throws SQLException {
        AdminData adminData=new AdminData();
        Admin admin=adminData.queryAdmin(LeaderTextFile.getText());
        if(LeaderTextFile.getText().equals(admin.getId())) {
            LeaderTextArea.appendText("\n编号->" + admin.getId() + "      姓名" + "-> " + admin.getName() + "            性别 ->" + admin.getSex() +
                    "\n种族 ->" + admin.getSpecies() + "           年龄 -> " + admin.getAge() +
                    "\n生日 -> " + admin.getBirthday() + "        健康状况 -> " + admin.getHealth() + "\n简介 :\n" + admin.getIntro() +
                    "\n账号 -> " + admin.getCount() + "            密码 -> " + admin.getPassword() + "\n");
        }else{
            LeaderTextArea.appendText("搜索信息错误，请重新输入");
        }
    }
    //查询某个种族的所有驯龙高手
    @FXML
    public void leaderQueryRace(ActionEvent actionEvent) throws SQLException {
        AdminData mAdmin = new AdminData();
        RaceDate raceDate=new RaceDate();
        Race race=raceDate.queryRace2(LeaderTextFile.getText());
        //显示所有驯龙高手的所有信息
        List<Admin> list = mAdmin.queryAdmin_Race(LeaderTextFile.getText());
        if(LeaderTextFile.getText().equals(race.getSpecies())) {
            for (Admin dr : list) {
                LeaderTextArea.appendText("\n编号->" + dr.getId() + "      姓名" + "-> " + dr.getName() + "            性别 ->" + dr.getSex() +
                        "\n种族 ->" + dr.getSpecies() + "           年龄 -> " + dr.getAge() +
                        "\n生日 -> " + dr.getBirthday() + "        健康状况 -> " + dr.getHealth() + "\n简介 :\n" + dr.getIntro() +
                        "\n账号 -> " + dr.getCount() + "            密码 -> " + dr.getPassword() + "\n");
            }
        }else{
            LeaderTextArea.appendText("搜索信息错误，请重新输入");
        }
    }
}
