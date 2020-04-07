package sample;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.Optional;

//龙母管理驯龙高手的工具类
public class UpdateAdmin {

    //龙母增加驯龙高手
    public void OnAdd() throws SQLException {
        VBox content = new VBox();
        TextField mName = new TextField();
        TextField mSpecies = new TextField();
        TextField mId = new TextField();
        TextField mSex = new TextField();
        TextField mBirthday = new TextField();
        TextField mHealth = new TextField();
        TextField mUser= new TextField();
        TextField mAge = new TextField();
        TextField mIntro= new TextField();
        TextField mPassword= new TextField();

        mSpecies.setPromptText("管理的种族");
        mName.setPromptText("名字");
        mId.setPromptText("编号");
        mSex.setPromptText("性别");
        mBirthday.setPromptText("生日：例2020-03-30");
        mHealth.setPromptText("健康情况");
        mUser.setPromptText("登录账号");
        mIntro.setPromptText("简介");
        mAge.setPromptText("年龄");
        mPassword.setPromptText("密码");


        content.getChildren().addAll(mId, mName, mSpecies, mSex, mBirthday, mAge,mIntro,mHealth,mUser,mPassword);
        content.setSpacing(20);    //子控件的距离

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);   //将控件放入对话框
        dlg.setTitle("添加驯龙高手");

        Optional<ButtonType> result = dlg.showAndWait(); //显示对话框，并接收结果

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            String name = String.valueOf(mName.getText());
            String species = String.valueOf(mSpecies.getText());
            String sex = String.valueOf(mSex.getText());
            String birthday= String.valueOf(mBirthday.getText());
            String intro= String.valueOf(mIntro.getText());
            String id= String.valueOf(mId.getText());
            String health= String.valueOf(mHealth.getText());
            String user= String.valueOf(mUser.getText());
            String age= String.valueOf(mAge.getText());
            String passwork= String.valueOf(mPassword.getText());


            Admin r=new Admin();
            AdminData R=new AdminData();

            r.setSpecies(species);            //将Dragon对象保存的数据存入数据库
            r.setName(name);
            r.setBirthday(birthday);
            r.setIntro(intro);
            r.setAge(age);
            r.setHealth(health);
            r.setSex(sex);
            r.setId(id);
            r.setCount(user);
            r.setPassword(passwork);
            R.addAdmin(r);

            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("添加成功");
            warning.setContentText("请确认");
            warning.showAndWait();

        }


    }

    //龙母修改驯龙高手信息
    public void OnUpdate() throws SQLException {
        VBox content = new VBox();
        TextField mName = new TextField();
        TextField mSpecies = new TextField();
        TextField mId = new TextField();
        TextField mSex = new TextField();
        TextField mBirthday = new TextField();
        TextField mHealth = new TextField();
        TextField mUser= new TextField();
        TextField mAge = new TextField();
        TextField mIntro= new TextField();
        TextField mPassword= new TextField();

        mSpecies.setPromptText("种族");
        mName.setPromptText("名字");
        mId.setPromptText("编号");
        mSex.setPromptText("性别");
        mBirthday.setPromptText("生日：例2020-03-30");
        mHealth.setPromptText("健康情况");
        mUser.setPromptText("登录账号");
        mIntro.setPromptText("简介");
        mAge.setPromptText("年龄");
        mPassword.setPromptText("密码");


        content.getChildren().addAll(mId, mName, mSpecies, mSex, mBirthday, mAge,mIntro,mHealth,mUser,mPassword);
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
            String name = String.valueOf(mName.getText());
            String species = String.valueOf(mSpecies.getText());
            String sex = String.valueOf(mSex.getText());
            String birthday= String.valueOf(mBirthday.getText());
            String intro= String.valueOf(mIntro.getText());
            String id= String.valueOf(mId.getText());
            String health= String.valueOf(mHealth.getText());
            String user= String.valueOf(mUser.getText());
            String age= String.valueOf(mAge.getText());
            String passwork= String.valueOf(mPassword.getText());


            Admin r=new Admin();
            AdminData R=new AdminData();

            r.setSpecies(species);            //将Dragon对象保存的数据存入数据库
            r.setName(name);
            r.setBirthday(birthday);
            r.setIntro(intro);
            r.setAge(age);
            r.setHealth(health);
            r.setSex(sex);
            r.setId(id);
            r.setCount(user);
            r.setPassword(passwork);
            R.updatAdmin(r);

            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("修改成功");
            warning.setContentText("请确认");
            warning.showAndWait();
        }


    }
}
