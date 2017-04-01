package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.resources.Core;

public class EditTaskController extends GController{

    @FXML
    public TextArea taskText;
    public TextField result;

    @Override
    public void onShow() throws Exception {
        taskText.setText(Core.userInfo.getThisTask().getText());
        result.setText(Core.userInfo.getThisTask().getResult());
    }

    public void onCancelClick(ActionEvent actionEvent) {
        GApp.app.closeAllWindows();
    }

    public void onSaveClick(ActionEvent actionEvent) {
        Core.userInfo.getThisTask().setText(taskText.getText());
        Core.userInfo.getThisTask().setResult(result.getText());

        GApp.app.closeAllWindows();
        Core.teacherController.themeSelectorUpdate();
    }
}
