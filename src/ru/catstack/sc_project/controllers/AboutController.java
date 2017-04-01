package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.resources.FXML_FILES;

public class AboutController extends GController{

    @Override
    public void onShow() throws Exception {

    }


    public void OnBackClick(ActionEvent actionEvent) throws Exception {
        GApp.app.setScene(FXML_FILES.LOGIN.getUrl());
    }
}
