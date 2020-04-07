package sample;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

//用户注册页面的对话框工具类
public class Login extends Dialog<Boolean> {
    VBox content = new VBox();

    TextField _name = new TextField();
    PasswordField _password = new PasswordField();
    TextField name = new TextField();
    TextField sex = new TextField();
    //注册成为游客，自动加入人族信息库
    public void OnAdd() throws SQLException {
        _name.setPromptText("账户");          //提示输入账号密码
        _password.setPromptText("密码");
        name.setPromptText("姓名");//提示输入姓名
        sex.setPromptText("性别");//提示输入性别

        content.getChildren().addAll(_name, _password, name,sex);
        content.setSpacing(10);    //子控件的距离

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);   //将控件放入对话框
        dlg.setTitle("注册个人信息");

        Optional<ButtonType> result = dlg.showAndWait(); //显示对话框，并接收结果

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {           //如果用户按了确定按钮，执行添加人族信息命令
            String count = String.valueOf(_name.getText());
            String code = String.valueOf(_password.getText());
            String cname = String.valueOf(name.getText());
            String csex = String.valueOf(sex.getText());

            HumanData H=new HumanData();       //将注册的信息放入一个Human类进行保存

            Human h=new Human();
            h.setCount(count);
            h.setPassword(code);
            h.setName(cname);
            h.setSex(csex);
            h.setMoney("300");
            H.addHuman(h);           //将Human对象保存的数据存入数据库


            Alert warning = new Alert(Alert.AlertType.INFORMATION);
            warning.setHeaderText("注册成功");
            warning.setContentText("请重新登录");
            warning.showAndWait();

        }
    }
    public static void main(String[] args) {

    }
}
