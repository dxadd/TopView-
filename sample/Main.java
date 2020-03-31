package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.List;



public class Main extends Application {

    TextField username=new TextField();
    PasswordField password=new PasswordField();
    HBox hBox=new HBox();
    Button btn1 = new Button("登录");                         //设置登录界面按钮
    Button btn2 = new Button("注册");

    Scene scene1,scene2,scene3;

    BorderPane root = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {


        VBox vbox=new VBox();                                 //编写登录界面控件的尺寸
        hBox.getChildren().addAll(btn1,btn2);
        hBox.setSpacing(30);
        hBox.setAlignment(Pos.CENTER);
        username.setMaxWidth(400);
        password.setMaxWidth(400);
        username.setPrefHeight(50);
        password.setPrefHeight(50);
        btn1.setPrefWidth(100);
        btn2.setPrefWidth(100);
        btn1.setPrefHeight(50);
        btn2.setPrefHeight(50);

        vbox.getChildren().addAll(username,password,hBox);
        vbox.setSpacing(20);    //子控件的距离
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);
        root.setId("root");               //application样式定义

        username.setPromptText("账户");                                     //提示输入账号密码
        password.setPromptText("密码");

        scene1 = new Scene(root, 700, 700);                 //登录界面的长宽
        primaryStage.setTitle("龙之谷");
        scene1.getStylesheets().add(String.valueOf(getClass().getResource("sample.fxml")));
        primaryStage.setScene(scene1);
        primaryStage.show();

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {

                    doLogin();                                                   //登录账号


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Login one=new Login();
                try {
                    one.OnAdd();                                               //注册账号，在Login工具类进行具体的操作

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        }


    private void doLogin() throws SQLException {                                                     //登录账号界面
        String user = username.getText();
        String pass = password.getText();
        //检查用户名和密码是否正确

        HumanData H=new HumanData();
        Human human=H.queryHuman(user);
        AdminData Admindata=new AdminData();
        Admin admin=Admindata.queryAdmin(user);                                        //身份识别


                if(user.equals("龙母")&&pass.equals("000000")){
                    success();
                    LOperation();
                }
                else if(pass.equals(admin.getPasswork())){
                    String strname=admin.getSpecies();
                    success();
                    AOperation(strname);

                }
                else if (pass.equals(human.getPassword())) {
                    success();
                    Operation();


                }else {
                    System.out.println("账号或密码错误");

                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setHeaderText("出错");
                    warning.setContentText("用户名错误或密码错误！");
                    warning.showAndWait();
                    Stage primary=new Stage();

                }

    }
    private  void success(){                                                         //通用表示登录成功界面
        Alert warning = new Alert(Alert.AlertType.INFORMATION);
        warning.setHeaderText("登录成功");
        warning.setContentText("欢迎进入系统");
        warning.showAndWait();
    }
    private void LOperation() throws SQLException {                                   //驯龙高手登录成功界面
        HBox hBox=new HBox();
        HBox hbox=new HBox();
        Button button=new Button("搜索");
        Button up=new Button("更改族群信息");
        Button look=new Button("查看或删除训龙高手");
        Button add=new Button("增加驯龙高手");
        Button update=new Button("修改驯龙高手信息");
        Button all=new Button("查看全部龙族信息");
        hbox.getChildren().addAll(up,look,update,add,all);
        TextField tf=new TextField();
        TextArea ta=new TextArea() ;
        tf.setPromptText("输入种族名查找该族所有族人信息");


        hBox.getChildren().addAll(tf,button);
        HBox.setHgrow(tf,Priority.ALWAYS);
        root.setTop(hBox);
        root.setCenter(ta);
        root.setBottom(hbox);


        RaceDate r=new RaceDate();                                                      //输出所有种族信息
        List<Race> race1=r.queryAll();
        for(Race race:race1) {
            ta.appendText("编号-> " + race.getId() + "\n族名 -> " + race.getName() +"\n族长 -> "+race.getShaikh()+ "\n简介 :\n" + race.getIntro() +
                    "\n种族 -> " + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n\n");
        }

        button.setOnAction(new EventHandler<ActionEvent>() {                              //查询某个种族所有成员信息
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str1=tf.getText();
                    DragonData D = new DragonData();
                    List<Dragon> list = D.queryAll(str1);
                    for (Dragon dr : list){
                        ta.appendText("\n编号->"+dr.getId()+"      姓名" + "-> " + dr.getName() + "            性别 ->" + dr.getSex() +
                                "\n种族 ->" + dr.getSpecies()+"生日 -> " + dr.getBirthday() + "           年龄 -> " + dr.getAge() +
                                "\n简介 ->" + dr.getIntro()+"\n"+ "是否正在接受训练 -> " + dr.getTraining()+"\n\n");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        up.setOnAction(new EventHandler<ActionEvent>() {                     //更改族群信息，在UpdateRace2工具类进行
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    UpdateRace2 u= new UpdateRace2();
                    u.OnUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        update.setOnAction(new EventHandler<ActionEvent>() {                   //进入修改驯龙高手界面
            @Override
            public void handle(ActionEvent actionEvent) {
                UpdateAdmin updateAdmin=new UpdateAdmin();
                try {
                    updateAdmin.OnUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        look.setOnAction(new EventHandler<ActionEvent>() {                   //进入查看或删除驯龙高手信息界面
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    LcheckAdmin();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //  Operation();
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {                   //进入增加驯龙高手信息界面
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    UpdateAdmin admin=new UpdateAdmin();
                    admin.OnAdd();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        all.setOnAction(new EventHandler<ActionEvent>() {                   //进入查看所有龙族成员信息界面
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    LCheckDragon();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private  void LcheckAdmin() throws SQLException {                             //龙母删除或者查看驯龙高手信息
        HBox hBox = new HBox();
        Button button = new Button("搜索");
        Button delect =new Button("删除");
        Button back = new Button("返回");
        TextField tf = new TextField();
        TextArea ta = new TextArea();
        tf.setPromptText("输入对应编号查找具体信息，注意族长只能修改，不能删除");


        hBox.getChildren().addAll(tf, button,delect);
        HBox.setHgrow(tf, Priority.ALWAYS);
        root.setTop(hBox);
        root.setCenter(ta);
        root.setBottom(back);

        AdminData D = new AdminData();


        List<Admin> list = D.queryAll();
        for (Admin dr : list) {
            ta.appendText("\n编号->" + dr.getId() + "      姓名" + "-> " + dr.getName() + "            性别 ->" + dr.getSex() +
                    "\n种族 ->" + dr.getSpecies() + "           年龄 -> " + dr.getAge() +
                    "\n生日 -> "+dr.getBirthday()+"        健康状况 -> "+dr.getHealth()+"\n简介 :\n" + dr.getIntro() +
                    "\n账号 -> "+dr.getUser()+"            密码 -> "+dr.getPasswork()+"\n");
        }


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str = tf.getText();
                    Admin d=D.queryAdmin(str);
                    ta.appendText("\n编号->" + d.getId() + "      姓名" + "-> " + d.getName() + "            性别 ->" + d.getSex() +
                            "\n种族 ->" + d.getSpecies() + "           年龄 -> " + d.getAge() +
                            "\n生日 -> "+d.getBirthday()+"        健康状况 -> "+d.getHealth()+"\n简介 :\n" + d.getIntro() +
                            "\n账号 -> "+d.getUser()+"            密码 -> "+d.getPasswork()+"\n");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        delect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str = tf.getText();
                    D.delAdmin(str);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除成功");
                warning.setContentText("请确认");
                warning.showAndWait();
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    LOperation();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

    }


    private void AOperation(String Sid) throws SQLException {                                   //驯龙高手登录成功界面
        HBox hBox=new HBox();
        HBox hbox=new HBox();
        Button button=new Button("搜索");
        Button up=new Button("更改本族群信息");
        Button look=new Button("查看或删除族人信息");
        Button add=new Button("增加族人");
        Button update=new Button("更新族人信息");
        hbox.getChildren().addAll(up,look,update,add);
        TextField tf=new TextField();
        TextArea ta=new TextArea() ;
        tf.setPromptText("输入种族名查找该族所有族人信息");


        hBox.getChildren().addAll(tf,button);
        HBox.setHgrow(tf,Priority.ALWAYS);
        root.setTop(hBox);
        root.setCenter(ta);
        root.setBottom(hbox);


        RaceDate r=new RaceDate();                                                      //输出所有种族信息
        List<Race> race1=r.queryAll();
        for(Race race:race1) {
            ta.appendText("编号-> " + race.getId() + "\n族名 -> " + race.getName() +"\n族长 -> "+race.getShaikh()+ "\n简介 :\n" + race.getIntro() +
                    "\n种族 -> " + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n\n");
        }

        button.setOnAction(new EventHandler<ActionEvent>() {                              //查询某个种族所有成员信息
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str1=tf.getText();
                    DragonData D = new DragonData();
                    List<Dragon> list = D.queryAll(str1);
                    for (Dragon dr : list){
                        ta.appendText("编号->"+dr.getId()+"      姓名" + "-> " + dr.getName() + "            性别 ->" + dr.getSex() +
                                "种族 ->" + dr.getSpecies()+"生日 -> " + dr.getBirthday() + "           年龄 -> " + dr.getAge() +
                                "\n简介 ->" + dr.getIntro()+"\n"+ "是否正在接受训练 -> " + dr.getTraining()+"\n\n");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        up.setOnAction(new EventHandler<ActionEvent>() {                     //更改族群信息，在UpdateRace工具类进行
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    UpdateRace u=new UpdateRace();
                    u.OnUpdate(Sid);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        update.setOnAction(new EventHandler<ActionEvent>() {                   //进入更改族人信息界面
            @Override
            public void handle(ActionEvent actionEvent) {
                UpdateDragon update= new UpdateDragon();
                try {
                    update.OnUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        look.setOnAction(new EventHandler<ActionEvent>() {                   //进入查看或删除族人信息界面
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    CheckDragon(Sid);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
              //  Operation();
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {                   //进入增加族人信息界面
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    UpdateDragon U= new UpdateDragon();
                    U.OnAdd();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void CheckDragon(String s) throws SQLException {             //驯龙高手查询或删除族员信息界面

        HBox hBox = new HBox();
        Button button = new Button("搜索");
        Button delect =new Button("删除");
        Button back = new Button("返回");
        TextField tf = new TextField();
        TextArea ta = new TextArea();
        tf.setPromptText("输入对应编号查找具体信息");


        hBox.getChildren().addAll(tf, button,delect);
        HBox.setHgrow(tf, Priority.ALWAYS);
        root.setTop(hBox);
        root.setCenter(ta);
        root.setBottom(back);

        DragonData D = new DragonData();
        RaceDate r = new RaceDate();
        Race race = r.queryRace2(s);

        ta.appendText("编号->" + race.getId() + "\n族名" + "-> " + race.getName() + "\n简介 :\n" + race.getIntro() +
                "\n种族 ->" + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n");

        List<Dragon> list = D.queryAll(s);
        for (Dragon dr : list) {
            ta.appendText("编号->" + dr.getId() + "      姓名" + "-> " + dr.getName() + "            性别 ->" + dr.getSex() +
                    "          种族 ->" + dr.getSpecies() + "           年龄 -> " + dr.getAge() + "       简介 ->" + dr.getIntro() + "\n");
        }


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str = tf.getText();
                    Dragon d = D.queryDragon(str);
                    ta.appendText("\n编号 -> " + d.getId() + "      姓名" + "-> " + d.getName() + "           性别 ->" + d.getSex() +
                            "\n种族 ->" + d.getSpecies() +"            生日 -> "+d.getBirthday()+"             年龄 -> " + d.getAge() +
                            "\n简介 ->" + d.getIntro() + "\n"+"健康情况 -> "+d.getHealth());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        delect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str = tf.getText();
                    D.delDragon(str);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除成功");
                warning.setContentText("请确认");
                warning.showAndWait();
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AOperation(s);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private void LCheckDragon() throws SQLException {             //龙母查询龙族所有成员

        HBox hBox = new HBox();
        Button button = new Button("搜索");
        Button back = new Button("返回");
        TextField tf = new TextField();
        TextArea ta = new TextArea();
        tf.setPromptText("输入对应编号查找具体龙族人信息");


        hBox.getChildren().addAll(tf, button);
        HBox.setHgrow(tf, Priority.ALWAYS);
        root.setTop(hBox);
        root.setCenter(ta);
        root.setBottom(back);

        DragonData D = new DragonData();
        List<Dragon> Race =D.queryAllD();
        for(Dragon d:Race) {
            ta.appendText("\n编号 -> " + d.getId() + "      姓名" + "-> " + d.getName() + "           性别 ->" + d.getSex() +
                    "\n种族 ->" + d.getSpecies() +"            生日 -> "+d.getBirthday()+"             年龄 -> " + d.getAge() +
                    "\n简介 ->" + d.getIntro() + "\n"+"健康情况 -> "+d.getHealth()+"\n");
        }

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str = tf.getText();
                    Dragon d = D.queryDragon(str);
                    ta.appendText("\n编号 -> " + d.getId() + "      姓名" + "-> " + d.getName() + "           性别 ->" + d.getSex() +
                            "\n种族 ->" + d.getSpecies() +"            生日 -> "+d.getBirthday()+"             年龄 -> " + d.getAge() +
                            "\n简介 ->" + d.getIntro() + "\n"+"健康情况 -> "+d.getHealth()+"\n");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    LOperation();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

    }


    private void query(String s) throws SQLException {               //游客查看信息

        HBox hBox = new HBox();
        Button button = new Button("搜索");
        Button back = new Button("返回");
        TextField tf = new TextField();
        TextArea ta = new TextArea();
        tf.setPromptText("输入对应编号查找具体信息");


        hBox.getChildren().addAll(tf, button);
        HBox.setHgrow(tf, Priority.ALWAYS);
        root.setTop(hBox);
        root.setCenter(ta);
        root.setBottom(back);

        DragonData D = new DragonData();
        RaceDate r = new RaceDate();
        Race race = r.queryRace2(s);

        ta.appendText("编号->" + race.getId() + "\n族名" + "-> " + race.getName() + "\n简介 :\n" + race.getIntro() +
                "\n种族 ->" + race.getSpecies() + "\n位置 -> " + race.getAdress() + "\n领地大小 -> " + race.getArea() + "\n");

        List<Dragon> list = D.queryAll(s);
        for (Dragon dr : list) {
            ta.appendText("编号->" + dr.getId() + "      姓名" + "-> " + dr.getName() + "            性别 ->" + dr.getSex() +
                    "          种族 ->" + dr.getSpecies() + "           年龄 -> " + dr.getAge() + "       简介 ->" + dr.getIntro() + "\n");
        }


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String str = tf.getText();
                    Dragon d = D.queryDragon(str);
                    ta.appendText("编号->" + d.getId() + "      姓名" + "-> " + d.getName() + "           性别 ->" + d.getSex() +
                            "             种族 ->" + d.getSpecies() + "             年龄 -> " + d.getAge() + "           简介 ->" + d.getIntro() + "\n");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Operation();
            }

        });

    }
    private void Operation(){                         //游客登录成功界面

        VBox content = new VBox();
        Button[] btn=new Button[6];

        btn[0]=new Button("迅猛龙");
        btn[1]=new Button("暴龙");
        btn[2]=new Button("霸王龙");
        btn[3]=new Button("棕色龙");
        btn[4]=new Button("青龙");
        btn[5]=new Button("烈焰龙");

        content.getChildren().addAll(btn[0],btn[1],btn[2],btn[3],btn[4],btn[5]);
        content.setSpacing(20);    //子控件的距离
        content.setAlignment(Pos.CENTER);
        for(Button a:btn){
            a.setMaxWidth(200);
            a.setPrefHeight(50);
        }

        root.setCenter(content);

        btn[0].setOnAction(new EventHandler<ActionEvent>() {                   //点击按钮选择对应种族信息查看
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    query("迅猛龙");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        btn[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    query("暴龙");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        btn[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    query("霸王龙");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        btn[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    query("棕色龙");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        btn[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    query("青龙");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        btn[5].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    query("烈焰龙");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
