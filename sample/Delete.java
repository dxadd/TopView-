package sample;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//通用的删除成员的工具类
public class Delete {

    TextField id = new TextField();
    //龙母删除某个种族
    public void DeleteRace() throws SQLException {
        id.setPromptText("请输入你要删除的种族编号");
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(id);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("删除种族");

        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果

        RaceDate Race=new RaceDate();
        Race race=Race.queryRace(id.getText());

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if(race.getId().equals(id.getText())) {

                DragonData mDragon = new DragonData();
                List<Dragon> list = null;
                try {
                    list = mDragon.queryAll(race.getSpecies());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //将种族所有成员删除
                for (Dragon dr : list){
                    try {
                        mDragon.delDragon(dr.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                //删除种族
                Race.delRace_Id(id.getText());

                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除成功");
                warning.setContentText("请确定");
                warning.showAndWait();
            }else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setHeaderText("出错");
                warning.setContentText("编号错误");
                warning.showAndWait();
                Stage primary=new Stage();
            }

        }
    }

    //龙母删除某个驯龙高手
    public void DeleteAdmin() throws SQLException {
        id.setPromptText("请输入你要删除的驯龙高手编号");

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(id);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("删除驯龙高手");

        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果

        AdminData Admin=new AdminData();
        Admin admin=Admin.queryAdmin(id.getText());

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if(admin.getId().equals(id.getText())) {
                Admin.delAdmin(id.getText());
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除成功");
                warning.setContentText("请确定");
                warning.showAndWait();
            }else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setHeaderText("出错");
                warning.setContentText("编号错误");
                warning.showAndWait();
                Stage primary=new Stage();
            }

        }
    }

    //驯龙高手删除某个族员
    public void DeleteDragon(String getRace) throws SQLException {
        id.setPromptText("请输入你要删除的族员编号");

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(id);

        //添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);
        //创建对话框
        Dialog<ButtonType> dlg = new Dialog<>();
        dlg.setDialogPane(dialogPane);                                         //将控件放入对话框
        dlg.setTitle("删除族员");

        Optional<ButtonType> result = dlg.showAndWait();                      //显示对话框，并接收结果

        DragonData D=new DragonData();
        Dragon dragon=D.queryDragon(id.getText());

        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if(dragon.getSpecies().equals(getRace)) {
                D.delDragon(id.getText());
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText("删除成功");
                warning.setContentText("请确定");
                warning.showAndWait();
            }else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setHeaderText("出错");
                warning.setContentText("种族或者编号错误");
                warning.showAndWait();
                Stage primary=new Stage();
            }

        }
    }
}
