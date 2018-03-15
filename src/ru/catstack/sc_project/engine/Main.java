package ru.catstack.sc_project.engine;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.WindowEvent;
import ru.catstack.fx_engine.engine.App;
import ru.catstack.fx_engine.impl.GApplication;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.help_objects.PasswordDialog;
import ru.catstack.sc_project.objects.user.UserInfo;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;
import ru.catstack.sc_project.utils.TeacherFileLoader;

import java.io.File;
import java.util.Optional;

public class Main extends GApplication {

    static final int MIN_WIDTH = 864;
    static final int MIN_HEIGHT = 602;

    public static void main(String[] args) {
        Main main = new Main();

        main.config.width = MIN_WIDTH;
        main.config.height = MIN_HEIGHT;
        main.config.minWidth = MIN_WIDTH;
        main.config.minHeight = MIN_HEIGHT;
        main.config.title = "Компьютерные тесты по математике";

        File assetsFile = new File("assets");

        if (!assetsFile.exists()) {
            assetsFile.mkdir();
        }

        new App(main);
    }

    

    @Override
    public void onStart() throws Exception {

        Core.userInfo = new UserInfo();

        TeacherFileLoader.load();

        GApp.app.setScene(FXML_FILES.LOGIN.getUrl());

        GApp.app.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                PasswordDialog passwordDialog = new PasswordDialog();

                Optional<String> password = passwordDialog.showAndWait();
                if (password.isPresent() && password.get().equals("12345")) {

                } else if (password.isPresent()) {
                    event.consume();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setContentText("Неверный пароль");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                } else {
                    event.consume();
                }
            }
        });
    }
}
