package ru.catstack.sc_project.engine;

import ru.catstack.fx_engine.engine.App;
import ru.catstack.fx_engine.impl.GApplication;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.user.UserInfo;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;

import java.io.File;

public class Main extends GApplication {

    static final int MIN_WIDTH = 864;
    static final int MIN_HEIGHT = 600;

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

        GApp.app.setScene(FXML_FILES.LOGIN.getUrl());
    }
}
