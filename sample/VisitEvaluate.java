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

//用户查看我的评价的页面
public class VisitEvaluate {
    @FXML
    private TextField visitTextFile;
    @FXML private TextArea visitTextArea;
    private String User;

    public void setUser(String user) {
        this.User = user;
    }

    //返回上一层，用户登录成功页面
    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    //查看该用户的所有评价
    public void allEvaluate(ActionEvent actionEvent) throws SQLException {
        EvaluateData evaluateData=new EvaluateData();
        List<Evaluate> list=evaluateData.queryEvaluate_User(this.User);
        for(Evaluate evaluate:list){
            visitTextArea.appendText("\n\n编号 -> "+evaluate.getNo()+"\n评价时间 -> "+evaluate.getTime()+
                    "\n评价种族 -> "+evaluate.getRace()+"\n评价等价 -> "+evaluate.getGrade()+
                    "\n评价内容 -> "+evaluate.getContent()+"\n评价人 -> "+evaluate.getAuthor());
        }

    }

    //根据用户评价的编号，删除用户评价，在UserEvaluate工具类进行
    public void delEvaluate(ActionEvent actionEvent) throws SQLException, ParseException {
        UserEvaluate evaluate=new UserEvaluate();
        evaluate.deleevaluate(this.User);
    }

    //修改用户的某个评价等级及评价内容，在UserEvaluate工具类进行
    public void updateEvaluate(ActionEvent actionEvent) throws SQLException, ParseException {
        UserEvaluate evaluate=new UserEvaluate();
        evaluate.updateevaluate(this.User);
    }

    //查询某个种族获得的所有评价
    public void RaceSearch(ActionEvent actionEvent) throws SQLException {
        EvaluateData evaluateData=new EvaluateData();
        List<Evaluate> list=evaluateData.queryEvaluate_Race(visitTextFile.getText());
        for(Evaluate evaluate:list){
            visitTextArea.appendText("\n\n编号 -> "+evaluate.getNo()+"\n评价时间 -> "+evaluate.getTime()+
                    "\n评价种族 -> "+evaluate.getRace()+"\n评价等价 -> "+evaluate.getGrade()+
                    "\n评价内容 -> "+evaluate.getContent()+"\n评价人 -> "+evaluate.getAuthor());
        }

    }
}
