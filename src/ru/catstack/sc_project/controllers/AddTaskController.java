package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.ThemeTask;
import ru.catstack.sc_project.resources.Core;

public class AddTaskController extends GController {

    @FXML
    public TextArea taskText;
    public TextField result;

    @Override
    public void onShow() throws Exception {
        
    }

    public void onAddClick(ActionEvent actionEvent) {
        Core.userInfo.getThisTheme().getTasks().add(new ThemeTask(taskText.getText(), result.getText()));
        GApp.app.closeAllWindows();
        Core.teacherController.taskSelectorUpdate();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        GApp.app.closeAllWindows();
    }
}
