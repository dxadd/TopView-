package sample;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


//操纵用户评价的工具类
public class UserEvaluate {

    TextField grade = new TextField();
    TextField content= new TextField();
    TextField no = new TextField();

    //用户设置评价，传入用户名和用户查看的种族，设置为该评价的用户和种族
    public void setevaluate(String user,String race) throws SQLException, ParseException {
        grade.setPromptText("评价等级：1-5");
        content.setPromptText("评价内容");
        VBox vBox=new VBox();
        vBox.setSpacing(20);
        vBox.getChildren().addAll(grade,content);
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(vBox);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("对种族进行评价");

        //获得评价的时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        int days=0;
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date(System.currentTimeMillis());
        String now=simpleDateFormat.format(date1);

        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果
        EvaluateData evaluateData=new EvaluateData();
        Evaluate evaluate=new Evaluate();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            evaluate.setAuthor(user);
            evaluate.setGrade(grade.getText());
            evaluate.setContent(content.getText());
            evaluate.setTime(now);
            evaluate.setRace(race);
            evaluateData.addEvaluate(evaluate);

            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("评价成功");
            warning.setContentText("请确定");
            warning.showAndWait();
        }
    }

    //用户修改评价，传入用户名作为评价账号
    public void updateevaluate(String user) throws SQLException, ParseException {
        no.setPromptText("评价编号");
        grade.setPromptText("评价等级：1-5");
        content.setPromptText("评价内容");
        VBox vBox=new VBox();
        vBox.setSpacing(20);
        vBox.getChildren().addAll(no,grade,content);
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(vBox);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("修改评价");

        //获得评价的时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        int days=0;
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date(System.currentTimeMillis());
        String now=simpleDateFormat.format(date1);

        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果
        EvaluateData evaluateData=new EvaluateData();
        Evaluate evaluate=new Evaluate();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if(user.equals(evaluate.getAuthor())) {                //检查该评价对应的账号是否该用户
                evaluate.setAuthor(user);
                evaluate.setGrade(grade.getText());
                evaluate.setContent(content.getText());
                evaluate.setTime(now);
                evaluate.setRace(evaluateData.queryEvaluate_Id(no.getText()).getRace());           //获得编号原先对应的种族，并将它设置不变
                evaluate.setNo(no.getText());
                evaluateData.updateEvaluate(evaluate);

                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("修改成功");
                warning.setContentText("请确定");
                warning.showAndWait();
            }else{
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("修改失败");
                warning.setContentText("评价不存在");
                warning.showAndWait();
            }
        }
    }

    //用户删除评价
    public void deleevaluate(String user) throws SQLException, ParseException {
        no.setPromptText("评价编号");
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(no);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("删除评价");

        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果
        EvaluateData evaluateData=new EvaluateData();
        Evaluate evaluate=evaluateData.queryEvaluate_Id(no.getText());
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if(user.equals(evaluate.getAuthor())) {                //检查该评价对应的账号是否该用户
                evaluateData.delEvaluate(no.getText());

                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除成功");
                warning.setContentText("请确定");
                warning.showAndWait();
            }else{
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除失败");
                warning.setContentText("评价不存在");
                warning.showAndWait();
            }
        }
    }

    //龙母删除评价
    public void Leader_delevaluate() throws SQLException, ParseException {
        no.setPromptText("评价编号");
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(no);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("删除评价");

        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果
        EvaluateData evaluateData=new EvaluateData();
        Evaluate evaluate=evaluateData.queryEvaluate_Id(no.getText());
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if(no.getText().equals(evaluate.getNo())) {
                evaluateData.delEvaluate(no.getText());

                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除成功");
                warning.setContentText("请确定");
                warning.showAndWait();
            }else{
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除失败");
                warning.setContentText("评价不存在");
                warning.showAndWait();
            }
        }
    }
}
