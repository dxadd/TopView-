package sample;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.Optional;

public class UpdateRace {
    VBox content = new VBox();

    TextField name = new TextField();    TextField species = new TextField();
    TextField shaikh = new TextField();
    TextField adress = new TextField();
    TextField area = new TextField();
    TextField intro= new TextField();

    public void OnUpdate(String id) throws SQLException {
        species.setPromptText("种族");
        name.setPromptText("族名");          //提示输入账号密码
        shaikh.setPromptText("族长编号");
        adress.setPromptText("位置");//提示输入性别
        area.setPromptText("领地大小");
        intro.setPromptText("简介");//提示输入性别


        content.getChildren().addAll(species,name,shaikh,adress,area,intro);
        content.setSpacing(10);    //子控件的距离

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);   //将控件放入对话框
        dlg.setTitle("更改族群信息");

        Optional<ButtonType> result = dlg.showAndWait(); //显示对话框，并接收结果

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            String n = String.valueOf(name.getText());
            String s = String.valueOf(species.getText());
            String sh = String.valueOf(shaikh.getText());
            String a = String.valueOf(adress.getText());
            String i= String.valueOf(intro.getText());
            String ar= String.valueOf(area.getText());

            Race r=new Race();
            RaceDate R=new RaceDate();

            r.setSpecies(s);            //将Human对象保存的数据存入数据库
            r.setName(n);
            r.setShaikh(sh);
            r.setIntro(i);
            r.setAdress(a);
            r.setArea(ar);
            r.setId(id);
            R.updatRace(r);



            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("x修改成功");
            warning.setContentText("请确定");
            warning.showAndWait();

        }
    }
}
