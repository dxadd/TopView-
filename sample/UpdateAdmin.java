package sample;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.Optional;

public class UpdateAdmin {


    public void OnAdd() throws SQLException {
        VBox content = new VBox();
        TextField name = new TextField();
        TextField species = new TextField();
        TextField id = new TextField();
        TextField sex = new TextField();
        TextField birthday = new TextField();
        TextField health = new TextField();
        TextField user= new TextField();
        TextField age = new TextField();
        TextField intro= new TextField();
        TextField password= new TextField();

        species.setPromptText("种族");
        name.setPromptText("名字");
        id.setPromptText("编号");
        sex.setPromptText("性别");
        birthday.setPromptText("生日：例2020-03-30");
        health.setPromptText("健康情况");
        user.setPromptText("登录账号");
        intro.setPromptText("简介");
        age.setPromptText("年龄");
        password.setPromptText("密码");


        content.getChildren().addAll(id, name, species, sex, birthday, age,intro,health,user,password);
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

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            String n = String.valueOf(name.getText());
            String s = String.valueOf(species.getText());
            String se = String.valueOf(sex.getText());
            String b= String.valueOf(birthday.getText());
            String i= String.valueOf(intro.getText());
            String did= String.valueOf(id.getText());
            String h= String.valueOf(health.getText());
            String u= String.valueOf(user.getText());
            String a= String.valueOf(age.getText());
            String p= String.valueOf(password.getText());


            Admin r=new Admin();
            AdminData R=new AdminData();

            r.setSpecies(s);            //将Dragon对象保存的数据存入数据库
            r.setName(n);
            r.setBirthday(b);
            r.setIntro(i);
            r.setAge(a);
            r.setHealth(h);
            r.setSex(se);
            r.setId(did);
            r.setUser(u);
            r.setIntro(i);
            r.setPasswork(p);
            R.addAdmin(r);

        }

        Alert warning = new Alert(Alert.AlertType.INFORMATION);
        warning.setHeaderText("添加成功");
        warning.setContentText("请确认");
        warning.showAndWait();
    }

    public void OnUpdate() throws SQLException {
        VBox content = new VBox();
        TextField name = new TextField();
        TextField species = new TextField();
        TextField id = new TextField();
        TextField sex = new TextField();
        TextField birthday = new TextField();
        TextField health = new TextField();
        TextField user= new TextField();
        TextField age = new TextField();
        TextField intro= new TextField();
        TextField password= new TextField();

        species.setPromptText("种族");
        name.setPromptText("名字");
        id.setPromptText("编号");
        sex.setPromptText("性别");
        birthday.setPromptText("生日：例2020-03-30");
        health.setPromptText("健康情况");
        user.setPromptText("登录账号");
        intro.setPromptText("简介");
        age.setPromptText("年龄");
        password.setPromptText("密码");


        content.getChildren().addAll(id, name, species, sex, birthday, age,intro,health,user,password);
        content.setSpacing(20);    //子控件的距离

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);   //将控件放入对话框
        dlg.setTitle("更改驯龙高手信息");

        Optional<ButtonType> result = dlg.showAndWait(); //显示对话框，并接收结果

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            String n = String.valueOf(name.getText());
            String s = String.valueOf(species.getText());
            String se = String.valueOf(sex.getText());
            String b= String.valueOf(birthday.getText());
            String i= String.valueOf(intro.getText());
            String did= String.valueOf(id.getText());
            String h= String.valueOf(health.getText());
            String u= String.valueOf(user.getText());
            String a= String.valueOf(age.getText());
            String p= String.valueOf(password.getText());


            Admin r=new Admin();
            AdminData R=new AdminData();

            r.setSpecies(s);            //将Dragon对象保存的数据存入数据库
            r.setName(n);
            r.setBirthday(b);
            r.setIntro(i);
            r.setAge(a);
            r.setHealth(h);
            r.setSex(se);
            r.setId(did);
            r.setUser(u);
            r.setIntro(i);
            r.setPasswork(p);
            R.updatAdmin(r);

        }

        Alert warning = new Alert(Alert.AlertType.INFORMATION);
        warning.setHeaderText("修改成功");
        warning.setContentText("请确认");
        warning.showAndWait();
    }
}
