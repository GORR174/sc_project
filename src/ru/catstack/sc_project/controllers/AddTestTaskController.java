package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.ThemeTask;
import ru.catstack.sc_project.resources.Core;

public class AddTestTaskController extends GController {

    @FXML
    public TextArea taskText;
    public TextField rightAnswerField;
    public TextField answer2Field;
    public TextField answer3Field;
    public TextField answer4Field;

    @Override
    public void onShow() throws Exception {

    }


    public void onCancelClick(ActionEvent actionEvent) {
        GApp.app.closeAllWindows();
    }

    public void onAddClick(ActionEvent actionEvent) {

        ThemeTask themeTask = new ThemeTask(taskText.getText(), rightAnswerField.getText(), null);
        themeTask.setAnswer2(answer2Field.getText());
        themeTask.setAnswer3(answer3Field.getText());
        themeTask.setAnswer4(answer4Field.getText());
        themeTask.setTestTask(true);

        Core.userInfo.getThisTheme().getTasks().add(themeTask);

        GApp.app.closeAllWindows();
        Core.teacherController.taskSelectorUpdate();
    }
}
