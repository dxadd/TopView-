package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//用户登录成功页面
public class UserController implements Initializable {
    @FXML private TextField userTextFile;
    @FXML private TextArea UserTextArea;
    private String getUser;

    //通过Controller登录控制页面传入用户名
    public void setGetUser(String getUser) {
        this.getUser = getUser;
    }

    public  UserController() {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //用户搜索某个种族，输出种族信息及种族成员信息
    public void userSearch(ActionEvent actionEvent) throws SQLException {
        String getRace=userTextFile.getText();
        System.out.println(getRace);

        RaceDate r = new RaceDate();
        UserTextArea.appendText(getRace+"\n\n");
        Race race=r.queryRace2(getRace);
        //先输出种族信息和所有成员信息
        UserTextArea.appendText("族名" + " -> " + race.getName() + "\n简介 :\n" + race.getIntro() +
                "\n种族 ->" + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n\n");
        DragonData D=new DragonData();
        List<Dragon> list=D.queryAll(getRace);
        for (Dragon dr : list) {
            UserTextArea.appendText("姓名" + "-> " + dr.getName() + "            性别 ->" + dr.getSex() +
                    "          种族 ->" + dr.getSpecies()+"           年龄 -> " + dr.getAge() +"\n简介 ->" + dr.getIntro()+
                    "\n"+ "是否正在接受训练 -> " + dr.getTraining()+"             健康状况 -> " + dr.getHealth() + "\n\n");
        }

    }

    //用户查询全部种族信息
    public void userQuery(ActionEvent actionEvent) throws SQLException{
        RaceDate r = new RaceDate();
        List<Race> listrace = r.queryAll();
        for(Race race:listrace) {
            UserTextArea.appendText("编号->" + race.getId() + "\n族名" + "-> " + race.getName() + "\n简介 :\n" + race.getIntro() +
                    "\n种族 ->" + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n\n");
        }
    }
    //返回登录界面
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
    //用户进入龙之谷活动页面
    @FXML
    public void queryActivity(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("visitActivity.fxml"));
        Parent root = loader.load();
        VisitActivity controller=loader.getController();
        controller.setUser(this.getUser);
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    //用户进入我的评价页面
    @FXML
    public void visitEvaluate(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("visitEvaluate.fxml"));
        Parent root = loader.load();
        VisitEvaluate controller=loader.getController();
        controller.setUser(this.getUser);
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
}

