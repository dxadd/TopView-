package sample;

import Valley.Leader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//龙母登录成功后的页面
public class LeaderController {

    @FXML private TextField LeaderTextFile;
    @FXML private TextArea LeaderTextArea;

    //查询某个种族的具体信息
    @FXML
    public void leaderSearch(ActionEvent actionEvent) throws SQLException {
        String getRace=LeaderTextFile.getText();
        System.out.println(getRace);

        RaceDate r = new RaceDate();
        LeaderTextArea.appendText(getRace+"\n\n");
        Race race=r.queryRace2(getRace);
        //先输出种族信息和所有成员信息
        LeaderTextArea.appendText("种族编号->" + race.getId() + "\n族名 -> " + race.getName() +
                "\n族长编号 -> " + race.getShaikh() +"\n简介 :\n" + race.getIntro() +
                "\n种族 ->" + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n\n");
        DragonData D=new DragonData();
        List<Dragon> list=D.queryAll(getRace);
        for (Dragon dr : list) {
            LeaderTextArea.appendText("编号 -> "+dr.getId()+"              姓名 -> " + dr.getName() + "            性别 ->" + dr.getSex() +
                    "\n种族 ->" + dr.getSpecies()+"           年龄 -> " + dr.getAge() +"              生日 -> "+dr.getBirthday()+
                    "\n简介 ->" + dr.getIntro()+
                    "\n"+ "是否正在接受训练 -> " + dr.getTraining()+"             健康状况 -> " + dr.getHealth() + "\n\n");
        }
    }

    //查询所有种族信息
    @FXML
    public void leaderQueryAll(ActionEvent actionEvent) throws SQLException {
        RaceDate r = new RaceDate();
        List<Race> listrace = r.queryAll();
        for(Race race:listrace) {
            LeaderTextArea.appendText("种族编号->" + race.getId() + "\n族名 -> " + race.getName() +
                    "\n族长编号 -> " + race.getShaikh() +"\n简介 :\n" + race.getIntro() +
                    "\n种族 ->" + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n\n");
        }
    }

    //更新种族信息
    @FXML
    public void updateRace(ActionEvent actionEvent) throws SQLException {
        UpdateRace mRace= new UpdateRace();
        mRace.updateRace();
    }

    //增加种族
    @FXML
    public void addRace(ActionEvent actionEvent) throws SQLException {
        UpdateRace mRace= new UpdateRace();
        mRace.OnAdd();
    }

    //删除种族
    @FXML
    public void deleteRace(ActionEvent actionEvent)  throws SQLException {
        Delete delete=new Delete();
        delete.DeleteRace();

    }

    //退出登录
    @FXML
    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
    //进入驯龙高手页面
    public void LeaderAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Leader_Update_Admin.fxml"));
        Parent root = loader.load();
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    //进入龙谷活动页面
    public void LeaderActivity(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("visitActivity.fxml"));
        Parent root = loader.load();
        VisitActivity controller=loader.getController();
        controller.setUser("龙母");
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    //进入外邦人对龙谷的种族的评价的页面
    public void LeaderEvaluate(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("leaderEaluate.fxml"));
        Parent root = loader.load();
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
}
