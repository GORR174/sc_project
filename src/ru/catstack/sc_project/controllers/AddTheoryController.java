package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.impl.HavingImage;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;

public class AddTheoryController extends GController implements HavingImage {

    @FXML
    public TextArea taskText;

    @Override
    public void onShow() throws Exception {
        if (Core.userInfo.getThisTheme().getHelp() != null) {
            taskText.setText(Core.userInfo.getThisTheme().getHelp());
        }
    }


    public void onCancelClick(ActionEvent actionEvent) {
        GApp.app.closeAllWindows();
    }

    public void onAddClick(ActionEvent actionEvent) {

        if (!taskText.getText().replaceAll(" ", "").equals("")) {
            Core.userInfo.getThisTheme().setHelp(taskText.getText());
        } else {
            Core.userInfo.getThisTheme().setHelp(null);
        }

        GApp.app.closeAllWindows();
        Core.teacherController.themeSelectorUpdate();
    }

    public void onParagraphClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "§");
        setFocus();
    }

    public void onDegreesClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "°");
        setFocus();
    }

    public void onAngleClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "∠");
        setFocus();
    }

    public void onNumberRootClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "√");
        setFocus();
    }

    public void onPwr2Click(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "²");
        setFocus();
    }

    public void onPwr3Click(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "³");
        setFocus();
    }

    public void onLOEClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "≤");
        setFocus();
    }

    public void onMOEClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "≥");
        setFocus();
    }

    public void onSomeEqualClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "≈");
        setFocus();
    }

    public void setFocus() {
        taskText.requestFocus();
    }

    public void onAddImageClick(ActionEvent actionEvent) throws Exception {
        GApp.app.addModalWindow(FXML_FILES.IMAGES.getUrl(), "Выбрерите изображение", GApp.app.getStage(), false);
    }

    @Override
    public void update() {
        taskText.insertText(taskText.getCaretPosition(), "<img=\"" + Core.userInfo.getThisImage() + "\">\n");
        taskText.requestFocus();
    }
}
