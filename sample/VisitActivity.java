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

//龙母或者外邦人进入龙之谷所有活动的页面
public class VisitActivity {

    @FXML private TextField visitTextFile;
    @FXML private TextArea visitTextArea;
    private String User;
    public void setUser(String user) {
        this.User = user;
    }

    //查询某个种族的所有活动
    @FXML
    public void RaceSearch(ActionEvent actionEvent) throws SQLException {
    ActivityData activityData=new ActivityData();
        List<Activity> list=activityData.queryActivity_Race(visitTextFile.getText());
        for(Activity activity:list){
            visitTextArea.appendText("\n\n活动种族 -> "+activity.getRace() +"\n活动开始时间 -> "+activity.getStartTime() +
                    "\n活动主题 -> "+activity.getTheme());
        }

    }
    //查询龙之谷所有活动
    @FXML
    public void allActivities(ActionEvent actionEvent) throws SQLException {

        ActivityData activityData=new ActivityData();
        List<Activity> list=activityData.queryAll();
        for(Activity activity:list){
            visitTextArea.appendText("\n\n活动种族 -> "+activity.getRace() +"\n活动开始时间 -> "+activity.getStartTime() +
                    "\n活动主题 -> "+activity.getTheme());
        }

    }

    //返回上一层
    @FXML
    public void back(ActionEvent actionEvent) throws IOException, SQLException {

        //根据龙母或用户身份返回不同的页面
        if(this.User.equals("龙母")){              //龙母返回页面
            FXMLLoader loader=new FXMLLoader(getClass().getResource("leader.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root,700,700);
            Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }else{              //外邦人返回页面

            FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root,700,700);
            Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
        }

    }

    //进入某个种族的活动页面
    @FXML
    public void Raceinto(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("raceActivity.fxml"));
        Parent root = loader.load();
        RaceActivity controller=loader.getController();             //将用户账号传进去，方便识别用户的身份
        controller.setRace(visitTextFile.getText());
        controller.setUser(this.User);
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
}
