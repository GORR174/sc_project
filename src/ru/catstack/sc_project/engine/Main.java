package ru.catstack.sc_project.engine;

import ru.catstack.fx_engine.engine.App;
import ru.catstack.fx_engine.impl.GApplication;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.user.UserInfo;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;

public class Main extends GApplication {

    public static void main(String[] args) {
        Main main = new Main();

        main.config.width = 864;
        main.config.height = 486;
        main.config.minWidth = 864;
        main.config.minHeight = 486;
        main.config.title = "Компьютерные тесты «Вычислительные навыки»";

        new App(main);
    }

    @Override
    public void onStart() throws Exception {

        Core.userInfo = new UserInfo();

        GApp.app.setScene(FXML_FILES.LOGIN.getUrl());
    }
}
