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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//进入种族活动页面
public class RaceActivity {

    @FXML private TextField visitTextFile;
    @FXML private TextArea visitTextArea;
    private String User;

    public void setRace(String race) {
        this.race = race;
    }

    private String race;
    public void setUser(String user) {
        this.User = user;
    }

//    查询该种族的活动
    @FXML
    public void raceActivities(ActionEvent actionEvent) throws SQLException {
        ActivityData activityData=new ActivityData();
        List<Activity> list=activityData.queryActivity_Race(this.race);
        for(Activity activity:list){
            visitTextArea.appendText("\n\n活动种族 -> "+activity.getRace()  +"\n活动编号 -> "+activity.getNo()
                    +"\n活动开始时间 -> "+activity.getStartTime() + "\n活动主题 -> "+activity.getTheme());
        }
    }

    //返回按钮
    @FXML
    public void back(ActionEvent actionEvent) throws IOException, SQLException, ParseException {
        HumanData humanData=new HumanData();
        Human visit=humanData.queryHuman(this.User);
        RaceDate raceDate=new RaceDate();
        Race race1=raceDate.queryRace2(this.User);

        //检测身份是外邦人，驯龙高手还是龙母，不同身份对应不同的返回页面
        if(this.User.equals(visit.getCount())){                //检测是否为外邦人
            UserEvaluate evaluate=new UserEvaluate();
            evaluate.setevaluate(this.User,this.race);
            FXMLLoader loader=new FXMLLoader(getClass().getResource("visitActivity.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root,700,700);
            Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
        }
        else if(this.User.equals(race1.getSpecies())){                  //检查是否为驯龙高手
            FXMLLoader loader=new FXMLLoader(getClass().getResource("adminActivity.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root,700,700);
            Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
        }else{                                                  //检查是否为龙母
            FXMLLoader loader=new FXMLLoader(getClass().getResource("leader.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root,700,700);
            Stage stage=(Stage)((Node)actionEvent.getSource()) .getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
        }

    }
    //输入活动编号，进入某个活动
    @FXML
    public void activitySearch(ActionEvent actionEvent) throws SQLException, ParseException {
        ActivityData activityData=new ActivityData();
        Activity activity=activityData.queryActivity_Id(visitTextFile.getText());
        int status=activityStatus(activity);
        //根据工具函数返回的状态进行判断活动状态，选择输出对应的信息
            if(status==0) {
                visitTextArea.appendText("\n\n活动正在进行中......！");
                visitTextArea.appendText("\n\n活动种族 -> " + activity.getRace() + "\n活动主题 -> " + activity.getTheme() +
                        "\n活动内容 -> " + activity.getContent());
            }else if(status==-1){

                visitTextArea.appendText("\n\n活动尚未开始，敬请期待！");
            }else{
                visitTextArea.appendText("\n\n很遗憾，活动已经结束！");
            }
    }

    //返回该活动的状态，未开始，进行中，结束
    private int activityStatus(Activity activity) throws ParseException {

        //获得当前时间，与活动的开始，结束时间进行比较
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=df.parse(activity.getStartTime());
        Calendar now=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c2.setTime(date);
        int day=now.get(Calendar.DATE)-c2.get(Calendar.DATE)+
                (now.get(Calendar.MONTH)-c2.get(Calendar.MONTH))*30+(now.get(Calendar.YEAR)-c2.get(Calendar.YEAR))*360;
        //根据比较结果返回状态信号
        if(day>=0){
            //如果当前时间大于或等于活动时间，比较当前时间与活动结束时间
            Date enddate=df.parse(activity.getEndTime());
            c2.setTime(enddate);
            int end=now.get(Calendar.DATE)-c2.get(Calendar.DATE)+
                    (now.get(Calendar.MONTH)-c2.get(Calendar.MONTH))*30+(now.get(Calendar.YEAR)-c2.get(Calendar.YEAR))*360;
            if(end>0){
                //当前时间大于结束时间，活动已经结束，返回1
                return 1;
            }else{
                //当前时间小于活动结束时间，活动正在进行中，返回0
                return 0;
            }
        }else{
            //当前时间小于活动开始时间，活动未开始，返回-1
            return -1;
        }
    }
}
