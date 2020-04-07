package sample;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

//驯龙高手管理种族活的工具类
public class Admin_updateActivity {

    TextField theme = new TextField();
    TextField endTime = new TextField();
    TextField startTime = new TextField();
    TextField content= new TextField();
    TextField no = new TextField();

    //驯龙高手增加种族活动
    public void addActivity(String race) throws SQLException, ParseException {
        theme.setPromptText("活动主题");
        content.setPromptText("活动内容");
        startTime.setPromptText("活动开始时间");
        endTime.setPromptText("活动结束时间");
        no.setPromptText("活动编号，例：0003");

        VBox vBox=new VBox();
        vBox.setSpacing(20);
        vBox.getChildren().addAll(no,theme,content,startTime,endTime);
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(vBox);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("增加活动");


        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果
        ActivityData activityData=new ActivityData();
        Activity activity=new Activity();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            activity.setRace(race);
            activity.setNo(no.getText());
            activity.setTheme(theme.getText());
            activity.setContent(content.getText());
            activity.setStartTime(startTime.getText());
            activity.setEndTime(endTime.getText());
            activityData.addActivity(activity);

            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("添加活动成功");
            warning.setContentText("请确定");
            warning.showAndWait();
        }
    }

    //驯龙高手修改某个种族活动
    public void updateActivity(String race) throws SQLException, ParseException {
        theme.setPromptText("活动主题");
        content.setPromptText("活动内容");
        startTime.setPromptText("活动开始时间");
        endTime.setPromptText("活动结束时间");
        no.setPromptText("活动编号，例：0003");

        VBox vBox=new VBox();
        vBox.setSpacing(20);
        vBox.getChildren().addAll(no,theme,content,startTime,endTime);
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(vBox);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("修改活动信息");


        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果
        ActivityData activityData=new ActivityData();
        Activity activity=new Activity();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            activity.setRace(race);
            activity.setNo(no.getText());
            activity.setTheme(theme.getText());
            activity.setContent(content.getText());
            activity.setStartTime(startTime.getText());
            activity.setEndTime(endTime.getText());
            activityData.updatActivity(activity);

            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("添加活动成功");
            warning.setContentText("请确定");
            warning.showAndWait();
        }
    }

    //驯龙高手删除某个种族活动
    public void deleteActivity(String race) throws SQLException, ParseException {
        no.setPromptText("活动编号");
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(no);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("删除活动");

        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果
        ActivityData activityData=new ActivityData();
        Activity activity=activityData.queryActivity_Id(no.getText());
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if(race.equals(activity.getRace())) {
                activityData.delActivity(no.getText());

                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除成功");
                warning.setContentText("请确定");
                warning.showAndWait();
            }else{
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除失败");
                warning.setContentText("活动不存在");
                warning.showAndWait();
            }
        }
    }

}
