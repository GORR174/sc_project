package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.resources.FXML_FILES;

public class AskTaskController extends GController {

    @Override
    public void onShow() throws Exception {
        
    }

    public void onTestClick(ActionEvent actionEvent) throws Exception {
        GApp.app.closeWindow(FXML_FILES.ADD_TASK_SELECTOR.getUrl());
        GApp.app.addModalWindow(FXML_FILES.ADD_TEST_TASK.getUrl(), "Добавить новую задачу", GApp.app.getStage(), false);
    }

    public void onAnswerClick(ActionEvent actionEvent) throws Exception {
        GApp.app.closeWindow(FXML_FILES.ADD_TASK_SELECTOR.getUrl());
        GApp.app.addModalWindow(FXML_FILES.ADD_TASK.getUrl(), "Добавить новую задачу", GApp.app.getStage(), false);
    }
}
