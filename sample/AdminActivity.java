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
import java.text.ParseException;
import java.util.List;

//驯龙高手进入管理活动的主页面
public class AdminActivity {

    @FXML
    private TextField adminTextFile;
    @FXML private TextArea adminTextArea;
    private String getRace;

    public void getRace(String name){
        this.getRace=name;
    }

    //查询某个种族的活动
    public void adminSearch(ActionEvent actionEvent) throws SQLException {
        ActivityData activityData=new ActivityData();
        List<Activity> list=activityData.queryActivity_Race(adminTextFile.getText());
        for(Activity activity:list){
            adminTextArea.appendText("\n\n活动种族 -> "+activity.getRace() +"\n活动开始时间 -> "+activity.getStartTime() +
                    "\n活动主题 -> "+activity.getTheme()+"\n活动编号 -> "+activity.getNo());
        }
    }

    //修改本种族的活动
    public void updateActivity(ActionEvent actionEvent) throws SQLException, ParseException {
        Admin_updateActivity activity=new Admin_updateActivity();
        activity.updateActivity(this.getRace);
    }

    //增加本种族的活动
    public void addActivity(ActionEvent actionEvent) throws SQLException, ParseException {
        Admin_updateActivity activity=new Admin_updateActivity();
        activity.addActivity(this.getRace);
    }

    //删除本种族的某个活动
    public void deleteActivity(ActionEvent actionEvent) throws SQLException, ParseException {
        Admin_updateActivity activity=new Admin_updateActivity();
        activity.deleteActivity(this.getRace);
    }

    //查询龙之谷所有活动
    public void allActivity(ActionEvent actionEvent) throws SQLException {
        ActivityData activityData=new ActivityData();
        List<Activity> list=activityData.queryAll();
        for(Activity activity:list){
            adminTextArea.appendText("\n\n活动种族 -> "+activity.getRace() +"\n活动开始时间 -> "+activity.getStartTime() +
                    "\n活动主题 -> "+activity.getTheme()+"\n活动编号 -> "+activity.getNo());
        }
    }

    //返回上一层
    public void back(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent root = loader.load();
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    //进入某个种族的活动页面
    public void admininto(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("raceActivity.fxml"));
        Parent root = loader.load();
        RaceActivity controller=loader.getController();
        controller.setRace(adminTextFile.getText());
        controller.setUser(this.getRace);
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
}
