package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.resources.Core;

public class EditTestTaskController extends GController{

    @FXML
    public TextArea taskText;
    public TextField rightAnswerField;
    public TextField answer2Field;
    public TextField answer3Field;
    public TextField answer4Field;


    @Override
    public void onShow() throws Exception {
        taskText.setText(Core.userInfo.getThisTask().getText());
        rightAnswerField.setText(Core.userInfo.getThisTask().getResult());
        answer2Field.setText(Core.userInfo.getThisTask().getAnswer2());
        answer3Field.setText(Core.userInfo.getThisTask().getAnswer3());
        answer4Field.setText(Core.userInfo.getThisTask().getAnswer4());
    }

    public void onCancelClick(ActionEvent actionEvent) {
        GApp.app.closeAllWindows();
    }

    public void onSaveClick(ActionEvent actionEvent) {
        Core.userInfo.getThisTask().setText(taskText.getText());
        Core.userInfo.getThisTask().setResult(rightAnswerField.getText());
        Core.userInfo.getThisTask().setAnswer2(answer2Field.getText());
        Core.userInfo.getThisTask().setAnswer3(answer3Field.getText());
        Core.userInfo.getThisTask().setAnswer4(answer4Field.getText());

        GApp.app.closeAllWindows();
        Core.teacherController.themeSelectorUpdate();
    }
}
