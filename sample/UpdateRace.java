package sample;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.Optional;

//龙母或者驯龙高手管理族群信息工具类
public class UpdateRace {

    //龙母更改种族的信息
    public void updateRace() throws SQLException {

        VBox content = new VBox();
        TextField species = new TextField();
        TextField shaikh = new TextField();                  //族长编号
        TextField adress = new TextField();
        TextField area = new TextField();
        TextField intro= new TextField();
        TextField name = new TextField();
        TextField id = new TextField();

        id.setPromptText("种族编号");
        species.setPromptText("种族");
        name.setPromptText("族名");
        shaikh.setPromptText("族长编号");
        adress.setPromptText("位置");
        area.setPromptText("领地大小");
        intro.setPromptText("简介");


        content.getChildren().addAll(id,species,name,shaikh,adress,area,intro);
        content.setSpacing(10);    //子控件的距离

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                           //将控件放入对话框
        dlg.setTitle("更改族群信息");

        Optional<ButtonType> result = dlg.showAndWait();         //显示对话框，并接收结果

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            String mName = String.valueOf(name.getText());
            String mId = String.valueOf(id.getText());
            String mSpecies = String.valueOf(species.getText());
            String mShaikh= String.valueOf(shaikh.getText());
            String mAdress = String.valueOf(adress.getText());
            String mIntro= String.valueOf(intro.getText());
            String mArea= String.valueOf(area.getText());

            Race r=new Race();
            RaceDate R=new RaceDate();

            r.setSpecies(mSpecies);            //将Race对象保存的数据存入数据库
            r.setName(mName);
            r.setShaikh(mShaikh);
            r.setIntro(mIntro);
            r.setAdress(mAdress);
            r.setArea(mArea);
            r.setId(mId);
            R.updatRace(r);

            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("修改成功");
            warning.setContentText("请确定");
            warning.showAndWait();

        }

    }
    //龙母增加种族
    public void OnAdd() throws SQLException {

        VBox content = new VBox();
        TextField species = new TextField();
        TextField shaikh = new TextField();                  //族长编号
        TextField adress = new TextField();
        TextField area = new TextField();
        TextField intro= new TextField();
        TextField name = new TextField();
        TextField id = new TextField();

        id.setPromptText("种族编号");
        species.setPromptText("种族");
        name.setPromptText("族名");
        shaikh.setPromptText("族长编号");
        adress.setPromptText("位置");
        area.setPromptText("领地大小");
        intro.setPromptText("简介");


        content.getChildren().addAll(id,species,name,shaikh,adress,area,intro);
        content.setSpacing(10);    //子控件的距离

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                           //将控件放入对话框
        dlg.setTitle("增加族群");

        Optional<ButtonType> result = dlg.showAndWait();         //显示对话框，并接收结果

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            String mName = String.valueOf(name.getText());
            String mId = String.valueOf(id.getText());
            String mSpecies = String.valueOf(species.getText());
            String mShaikh= String.valueOf(shaikh.getText());
            String mAdress = String.valueOf(adress.getText());
            String mIntro= String.valueOf(intro.getText());
            String mArea= String.valueOf(area.getText());

            Race r=new Race();
            RaceDate R=new RaceDate();

            r.setSpecies(mSpecies);            //将Race对象保存的数据存入数据库
            r.setName(mName);
            r.setShaikh(mShaikh);
            r.setIntro(mIntro);
            r.setAdress(mAdress);
            r.setArea(mArea);
            r.setId(mId);
            R.addRace(r);

            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("添加成功");
            warning.setContentText("请确定");
            warning.showAndWait();

        }
    }

    //驯龙高手更改自己管理的种族的信息
    public void OnUpdate(String id,String species) throws SQLException {

        VBox content = new VBox();
        TextField shaikh = new TextField();                  //族长编号
        TextField adress = new TextField();
        TextField area = new TextField();
        TextField intro= new TextField();
        TextField name = new TextField();

        name.setPromptText("族名");
        shaikh.setPromptText("族长编号");
        adress.setPromptText("位置");
        area.setPromptText("领地大小");
        intro.setPromptText("简介");


        content.getChildren().addAll(name,shaikh,adress,area,intro);
        content.setSpacing(10);    //子控件的距离

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                           //将控件放入对话框
        dlg.setTitle("更改本族群信息");

        Optional<ButtonType> result = dlg.showAndWait();         //显示对话框，并接收结果

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            String mName = String.valueOf(name.getText());
            String mShaikh= String.valueOf(shaikh.getText());
            String mAdress = String.valueOf(adress.getText());
            String mIntro= String.valueOf(intro.getText());
            String mArea= String.valueOf(area.getText());

            Race r=new Race();
            RaceDate R=new RaceDate();

            r.setSpecies(species);            //将Race对象保存的数据存入数据库
            r.setName(mName);
            r.setShaikh(mShaikh);
            r.setIntro(mIntro);
            r.setAdress(mAdress);
            r.setArea(mArea);
            r.setId(id);
            R.updatRace(r);

            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("修改成功");
            warning.setContentText("请确定");
            warning.showAndWait();

        }
    }
}
