package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 *
 * @author itwang
 */
public class Controller implements Initializable {
    @FXML
    private Button btn_login;
    @FXML private TextField account;
    @FXML private PasswordField password;
    private new_Main application;
    private String sentRace=null;


    @FXML
    public void LOGIN_M(ActionEvent event) throws SQLException, IOException {
        String user = account.getText();
        String pass = password.getText();
        HumanData H=new HumanData();
        Human human=H.queryHuman(user);
        AdminData Admindata=new AdminData();
        Admin admin=Admindata.queryAdmin(user);


        //身份识别
        //检测是否为龙母
        if(user.equals("龙母")&&pass.equals("000000")){
            successLogin();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("leader.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root,700,700);
            Stage stage=(Stage)((Node)event.getSource()) .getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }
        //检测是否为驯龙高手
        else if(pass.equals(admin.getPassword())){

            successLogin();

            FXMLLoader loader=new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root = loader.load();
            AdminController controller=loader.getController();
            controller.getRace(admin.getSpecies());               //获取驯龙高手所管理的种族名称,并将它传递过去AdminController类，跳转到登录成功后界面
            Scene scene=new Scene(root,700,700);
            Stage stage=(Stage)((Node)event.getSource()) .getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }
        //检测是否为外邦人
        else if (pass.equals(human.getPassword())) {
            successLogin();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            UserController controller=loader.getController();
            controller.setGetUser(human.getCount());               //获取用户名,并将它传递过去UserController类，跳转到登录成功后界面
            Scene scene=new Scene(root,700,700);
            Stage stage=(Stage)((Node)event.getSource()) .getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }else {
            System.out.println("账号或密码错误");
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setHeaderText("出错");
            warning.setContentText("用户名错误或密码错误！");
            warning.showAndWait();
            Stage primary=new Stage();

        }
    }

    //通用表示登录成功界
    private  void successLogin(){
        Alert warning = new Alert(Alert.AlertType.INFORMATION);
        warning.setHeaderText("登录成功");
        warning.setContentText("欢迎进入系统");
        warning.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //游客注册
    @FXML
    public void REGINSTER_M(ActionEvent actionEvent) throws SQLException {
        Login human=new Login();                      //注册即加入人族的信息表，默认只有人族需要注册
        human.OnAdd();

    }

    public static void main(String[] args) {
        launch(args);
    }
}