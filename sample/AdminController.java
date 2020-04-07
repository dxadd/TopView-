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

//驯龙高手登录成功界面
public class AdminController implements Initializable {
    @FXML private TextField userTextFile;
    @FXML private TextArea UserTextArea;
    private String getRace;

    public void getRace(String name){
        this.getRace=name;
    }
    //查询某个种族
    @FXML
    public void adminSearch(ActionEvent actionEvent) throws SQLException {

        String getRace=userTextFile.getText();
        System.out.println(getRace);

        RaceDate r = new RaceDate();
        UserTextArea.appendText(getRace+"\n\n");
        Race race=r.queryRace2(getRace);
        //先输出种族信息和所有成员信息
        UserTextArea.appendText("种族编号->" + race.getId() + "\n族名 -> " + race.getName() +
                "\n族长编号 -> " + race.getShaikh() +"\n简介 :\n" + race.getIntro() +
                "\n种族 ->" + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n\n");
        DragonData D=new DragonData();
        List<Dragon> list=D.queryAll(getRace);
        for (Dragon dr : list) {
            UserTextArea.appendText("编号 -> "+dr.getId()+"              姓名 -> " + dr.getName() + "            性别 ->" + dr.getSex() +
                    "\n种族 ->" + dr.getSpecies()+"           年龄 -> " + dr.getAge() +"              生日 -> "+dr.getBirthday()+
                    "\n简介 ->" + dr.getIntro()+
                    "\n"+ "是否正在接受训练 -> " + dr.getTraining()+"             健康状况 -> " + dr.getHealth() + "\n\n");
        }

    }

    //查询全部种族信息
    @FXML
    public void adminQuery(ActionEvent actionEvent) throws SQLException{
        RaceDate r = new RaceDate();
        List<Race> listrace = r.queryAll();
        for(Race race:listrace) {
            UserTextArea.appendText("种族编号->" + race.getId() + "\n族名 -> " + race.getName() +
                    "\n族长编号 -> " + race.getShaikh() +"\n简介 :\n" + race.getIntro() +
                    "\n种族 ->" + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n\n");
        }
    }

    //驯龙高手更改管理族群的信息
    @FXML
    public void updateRace(ActionEvent actionEvent) throws SQLException {
        System.out.println(getRace);
        RaceDate mRace=new RaceDate();
        String id=mRace.queryRace2(getRace).getId();                   //通过种族名获得种族编号
        UpdateRace u=new UpdateRace();
        u.OnUpdate(id,getRace);

    }

    //驯龙高手增加龙族成员
    @FXML
    public void addDragon(ActionEvent actionEvent)throws SQLException {
        System.out.println(getRace);
        UpdateDragon U= new UpdateDragon();
        U.OnAdd(getRace);
    }

    //驯龙高手修改族员信息
    @FXML
    public void updateDragon(ActionEvent actionEvent){
        System.out.println(getRace);
        UpdateDragon update= new UpdateDragon();
        try {
            update.OnUpdate(getRace);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //删除龙族成员
    @FXML
    public void deleteDragon(ActionEvent actionEvent)throws SQLException {
        Delete delete=new Delete();
        delete.DeleteDragon(getRace);
    }
    //返回上一层
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //驯龙高手进入活动管理页面
    @FXML
    public void adminActivity(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("adminActivity.fxml"));
        Parent root = loader.load();
        AdminActivity activity=loader.getController();
        activity.getRace(getRace);
        Scene scene=new Scene(root,700,700);
        Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    //驯龙高手查看外邦人对本所管理族群的评价
    @FXML
    public void raceEvaluate(ActionEvent actionEvent) throws SQLException {
        EvaluateData evaluateData=new EvaluateData();
        List<Evaluate> list=evaluateData.queryEvaluate_Race(this.getRace);
        for(Evaluate evaluate:list){
            UserTextArea.appendText("\n\n编号 -> "+evaluate.getNo()+"\n评价时间 -> "+evaluate.getTime()+
                    "\n评价种族 -> "+evaluate.getRace()+"\n评价等价 -> "+evaluate.getGrade()+
                    "\n评价内容 -> "+evaluate.getContent()+"\n评价人 -> "+evaluate.getAuthor());
        }
    }
}