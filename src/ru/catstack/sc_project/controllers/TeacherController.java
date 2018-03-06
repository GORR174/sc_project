package ru.catstack.sc_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.Theme;
import ru.catstack.sc_project.objects.ThemeTask;
import ru.catstack.sc_project.objects.help_objects.Timer;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TeacherController extends GController {

    @FXML
    public TextField workTime;
    public MenuButton themeSelector;
    public MenuButton taskSelector;
    public TextField result2;
    public TextField result3;
    public TextField result4;
    public VBox themeConfigVBox;
    public TextField emailField;

    Timer timer;

    @Override
    public void onShow() throws IOException {
        timer = new Timer(Duration.millis(100), () -> {
            if(Core.userInfo.getThisTheme() == null)
                themeConfigVBox.setDisable(true);
            else
                themeConfigVBox.setDisable(false);
        });
        timer.run();
        Core.userInfo.setThisTheme(null);
        Core.userInfo.setThisTask(null);
        Core.teacherController = this;
        taskSelectorUpdate();
        themeSelectorUpdate();
        result2.setText("20");
        result3.setText("60");
        result4.setText("80");
        if(Core.userInfo.getThisTheme() != null) {
            workTime.setText("" + Core.teacher[Integer.parseInt(Core.userInfo.getClassNumber())].getThemes().get(0).getTime());
            result2.setText("20");
            result3.setText("60");
            result4.setText("80");
        }
        themeSelector.setOnMouseClicked(event -> {
            if(Core.userInfo.getThisTheme() != null) {
                Core.userInfo.getThisTheme().setTime(Integer.parseInt(workTime.getText()));
                Core.userInfo.getThisTheme().setqCount(Core.userInfo.getThisTheme().getTasks().size());
                Core.userInfo.getThisTheme().setResult2(Integer.parseInt(result2.getText()));
                Core.userInfo.getThisTheme().setResult3(Integer.parseInt(result3.getText()));
                Core.userInfo.getThisTheme().setResult4(Integer.parseInt(result4.getText()));
            }
            themeSelectorUpdate();
        });
    }

    public void onBackClick(ActionEvent actionEvent) throws Exception {
        GApp.app.setScene(FXML_FILES.LOGIN.getUrl());
        timer.stop();
    }

    public void onSaveClick(ActionEvent actionEvent) throws IOException {


        if (Core.userInfo.getThisTheme() != null) {
            Core.userInfo.getThisTheme().setTime(Integer.parseInt(workTime.getText()));
            Core.userInfo.getThisTheme().setqCount(Core.userInfo.getThisTheme().getTasks().size());
            Core.userInfo.getThisTheme().setResult2(Integer.parseInt(result2.getText()));
            Core.userInfo.getThisTheme().setResult3(Integer.parseInt(result3.getText()));
            Core.userInfo.getThisTheme().setResult4(Integer.parseInt(result4.getText()));
            if(!emailField.getText().replaceAll(" ", "").equals("")) {
                Core.userInfo.getThisTheme().setEmail(emailField.getText().replaceAll(" ", ""));
            } else {
                Core.userInfo.getThisTheme().setEmail("");
            }
        }

        boolean canSave = true;

        for (Theme theme : Core.teacher[Integer.parseInt(Core.userInfo.getClassNumber())].getThemes()) {
            if(theme.getqCount()>theme.getTasks().size())
                canSave = false;
        }

        if(canSave) {

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("teacher.json"), Core.teacher);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Информация");
            alert.setContentText("Сохранено!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("В одной из тем, количество заданий теста превышает общее количество заданий темы");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    public void onAddTheme(ActionEvent actionEvent) {
        if(Core.userInfo.getThisTheme() != null) {
            Core.userInfo.getThisTheme().setTime(Integer.parseInt(workTime.getText()));
            Core.userInfo.getThisTheme().setqCount(Core.userInfo.getThisTheme().getTasks().size());
            Core.userInfo.getThisTheme().setResult2(Integer.parseInt(result2.getText()));
            Core.userInfo.getThisTheme().setResult3(Integer.parseInt(result3.getText()));
            Core.userInfo.getThisTheme().setResult4(Integer.parseInt(result4.getText()));
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Добавить новую тему");
        dialog.setHeaderText(null);
        dialog.setContentText("Введите название темы:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            Core.teacher[Integer.parseInt(Core.userInfo.getClassNumber())].getThemes().add(new Theme(result.get()));
        }

        themeSelectorUpdate();
    }

    public void onChangeTheme(ActionEvent actionEvent) {
        if(Core.userInfo.getThisTheme() != null) {
            Core.userInfo.getThisTheme().setTime(Integer.parseInt(workTime.getText()));
            Core.userInfo.getThisTheme().setqCount(Core.userInfo.getThisTheme().getTasks().size());
            Core.userInfo.getThisTheme().setResult2(Integer.parseInt(result2.getText()));
            Core.userInfo.getThisTheme().setResult3(Integer.parseInt(result3.getText()));
            Core.userInfo.getThisTheme().setResult4(Integer.parseInt(result4.getText()));
            TextInputDialog dialog = new TextInputDialog(Core.userInfo.getThisTheme().getName());
            dialog.setTitle("Изменить тему");
            dialog.setHeaderText(null);
            dialog.setContentText("Введите новое название темы:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                Core.userInfo.getThisTheme().setName(result.get());
                themeSelector.setText(result.get());
            }
        }

        themeSelectorUpdate();
    }

    public void onDeleteTheme(ActionEvent actionEvent) {
        Core.teacher[Integer.parseInt(Core.userInfo.getClassNumber())].getThemes().remove(Core.userInfo.getThisTheme());
        Core.userInfo.setThisTheme(null);
        themeSelectorUpdate();
        themeSelector.setText("Тема");
        taskSelector.setText("Задание");
    }

    public void onAddNewTask(ActionEvent actionEvent) throws Exception {
        if(Core.userInfo.getThisTheme() != null) {
            GApp.app.addModalWindow(FXML_FILES.ADD_TASK.getUrl(), "Добавить новую задачу", GApp.app.getStage(), false);
        }
    }

    public void onTaskChange(ActionEvent actionEvent) throws Exception {
        if(Core.userInfo.getThisTheme() != null && Core.userInfo.getThisTask() != null) {
            GApp.app.addModalWindow(FXML_FILES.EDIT_TASK.getUrl(), "Редактировать задачу", GApp.app.getStage(), false);
        }
    }

    public void onTaskDelete(ActionEvent actionEvent) {
        if(Core.userInfo.getThisTheme() != null) {
            Core.userInfo.getThisTheme().getTasks().remove(Core.userInfo.getThisTask());
            taskSelector.setText("Задание");
            Core.userInfo.getThisTask().setText("Задание");
        }
        taskSelectorUpdate();
        taskSelector.setText("Задание");
    }

    public void themeSelectorUpdate(){
        taskSelectorUpdate();
        themeSelector.getItems().remove(0, themeSelector.getItems().size());
        for (Theme theme : Core.teacher[Integer.parseInt(Core.userInfo.getClassNumber())].getThemes()) {
            themeSelector.getItems().add(new MenuItem(theme.getName()));
        }
        for (MenuItem menuItem : themeSelector.getItems()) {
            Label lbl = new Label(menuItem.getText());
            lbl.setPrefWidth(256);
            menuItem.setText("");
            menuItem.setGraphic(lbl);
            menuItem.setOnAction(event -> {
                themeSelector.setText(lbl.getText());
                Core.userInfo.setThisTheme(Core.teacher[Integer.parseInt(Core.userInfo.getClassNumber())].getThemeByName(lbl.getText()));
                taskSelectorUpdate();
                workTime.setText("" + Core.userInfo.getThisTheme().getTime());
                result2.setText("" + Core.userInfo.getThisTheme().getResult2());
                result3.setText("" + Core.userInfo.getThisTheme().getResult3());
                result4.setText("" + Core.userInfo.getThisTheme().getResult4());
                emailField.setText("" + Core.userInfo.getThisTheme().getEmail());
                taskSelector.setText("Задание");
            });
        }
    }

    public void taskSelectorUpdate(){
        if(Core.userInfo.getThisTask() != null)
            taskSelector.setText(Core.userInfo.getThisTask().getText());
        taskSelector.getItems().remove(0, taskSelector.getItems().size());
        if(Core.userInfo.getThisTheme() != null) {
            for (ThemeTask themeTask : Core.userInfo.getThisTheme().getTasks()) {
                taskSelector.getItems().add(new MenuItem(themeTask.getText()));
            }
            for (MenuItem menuItem : taskSelector.getItems()) {
                Label lbl = new Label(menuItem.getText());
                lbl.setPrefWidth(256);
                menuItem.setText("");
                menuItem.setGraphic(lbl);
                menuItem.setOnAction(event -> {
                    taskSelector.setText(lbl.getText());
                    Core.userInfo.setThisTask(Core.userInfo.getThisTheme().getTaskByText(lbl.getText()));
                });
            }
        }
    }

    public void onTheoryClick(ActionEvent actionEvent) throws Exception {
        if(Core.userInfo.getThisTheme() != null) {
            GApp.app.addModalWindow(FXML_FILES.ADD_THEORY.getUrl(), "Теория", GApp.app.getStage(), false);
        }
    }
}
