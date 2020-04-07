package sample;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.Optional;

//驯龙高手更改或增加族人信息
public class UpdateDragon {

    //驯龙高手增加族人
    public void OnAdd(String species) throws SQLException {
        VBox content = new VBox();
        TextField name = new TextField();
        TextField id = new TextField();
        TextField sex = new TextField();
        TextField birthday = new TextField();
        TextField health = new TextField();
        TextField training = new TextField();
        TextField age = new TextField();
        TextField intro= new TextField();

        name.setPromptText("名字");
        id.setPromptText("编号");
        sex.setPromptText("性别");
        birthday.setPromptText("生日：例2020-03-30");
        health.setPromptText("健康情况");
        training.setPromptText("是否正在接受训练");
        intro.setPromptText("简介");
        age.setPromptText("年龄");


        content.getChildren().addAll(id, name, sex, birthday, age,intro,health,training);
        content.setSpacing(20);    //子控件的距离

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);   //将控件放入对话框
        dlg.setTitle("增加族员");

        Optional<ButtonType> result = dlg.showAndWait(); //显示对话框，并接收结果

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            String n = String.valueOf(name.getText());
            String se = String.valueOf(sex.getText());
            String b= String.valueOf(birthday.getText());
            String i= String.valueOf(intro.getText());
            String did= String.valueOf(id.getText());
            String h= String.valueOf(health.getText());
            String t= String.valueOf(training.getText());
            String a= String.valueOf(age.getText());


            Dragon r=new Dragon();
            DragonData R=new DragonData();

            r.setSpecies(species);            //将Dragon对象保存的数据存入数据库
            r.setName(n);
            r.setBirthday(b);
            r.setIntro(i);
            r.setAge(a);
            r.setHealth(h);
            r.setSex(se);
            r.setId(did);
            r.setTraining(t);
            r.setIntro(i);
            R.addDragon(r);

            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("修改成功");
            warning.setContentText("请确认");
            warning.showAndWait();

        }


    }

    //驯龙高手修改族人信息
    public void OnUpdate(String species) throws SQLException {
        VBox content = new VBox();
        TextField name = new TextField();
        TextField id = new TextField();
        TextField sex = new TextField();
        TextField birthday = new TextField();
        TextField health = new TextField();
        TextField training = new TextField();
        TextField age = new TextField();
        TextField intro = new TextField();

        name.setPromptText("名字");
        id.setPromptText("编号");
        sex.setPromptText("性别");
        birthday.setPromptText("生日：例2020-03-30");
        health.setPromptText("健康情况");
        training.setPromptText("是否正在接受训练");
        intro.setPromptText("简介");
        age.setPromptText("年龄");


        content.getChildren().addAll(id, name, sex, birthday, age, intro, health, training);
        content.setSpacing(20);    //子控件的距离

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);   //将控件放入对话框
        dlg.setTitle("更改族群成员信息");

        Optional<ButtonType> result = dlg.showAndWait(); //显示对话框，并接收结果
        DragonData R = new DragonData();
        Dragon r = R.queryDragon(id.getText());
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if(r.getSpecies().equals(species)) {
                String mName = String.valueOf(name.getText());
                String mSex = String.valueOf(sex.getText());
                String mBirthday = String.valueOf(birthday.getText());
                String mIndro = String.valueOf(intro.getText());
                String mId = String.valueOf(id.getText());
                String mHealth = String.valueOf(health.getText());
                String mTraining = String.valueOf(training.getText());
                String mAge = String.valueOf(age.getText());



                r.setSpecies(species);            //将Dragon对象保存的数据存入数据库
                r.setName(mName);
                r.setBirthday(mBirthday);
                r.setIntro(mIndro);
                r.setAge(mAge);
                r.setHealth(mHealth);
                r.setSex(mSex);
                r.setId(mId);
                r.setTraining(mTraining);
                r.setIntro(mIndro);
                R.updatGragon(r);

                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("修改成功");
                warning.setContentText("请确认");
                warning.showAndWait();
            }else{
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setHeaderText("出错");
                warning.setContentText("种族或者编号错误");
                warning.showAndWait();
                Stage primary=new Stage();
            }
        }
    }

}
