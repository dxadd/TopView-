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

//龙母管理种族评价的页面
public class LeaderEaluate {
    @FXML
    private TextField visitTextFile;
    @FXML private TextArea visitTextArea;

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

    //返回上一层
    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("leader.fxml"));
        Parent root = loader.load();
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    //查看龙谷所有评价
    public void allEvaluate(ActionEvent actionEvent) throws SQLException {
        EvaluateData evaluateData=new EvaluateData();
        List<Evaluate> list=evaluateData.queryAll();
        for(Evaluate evaluate:list){
            visitTextArea.appendText("\n\n编号 -> "+evaluate.getNo()+"\n评价时间 -> "+evaluate.getTime()+
                    "\n评价种族 -> "+evaluate.getRace()+"\n评价等价 -> "+evaluate.getGrade()+
                    "\n评价内容 -> "+evaluate.getContent()+"\n评价人 -> "+evaluate.getAuthor());
        }

    }

    //删除评价
    public void delEvaluate(ActionEvent actionEvent) throws SQLException, ParseException {
        UserEvaluate evaluate=new UserEvaluate();
        evaluate.Leader_delevaluate();
    }
}
