package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.Theme;
import ru.catstack.sc_project.objects.help_objects.PasswordDialog;
import ru.catstack.sc_project.objects.user.Teacher;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginController extends GController {

    @FXML
    public MenuButton classMenu;
    public MenuButton letterMenu;
    public TextField nameField;

    @Override
    public void onShow() throws IOException {

        if(Core.userInfo.getLetter() != null && Core.userInfo.getClassNumber() != null && Core.userInfo.getName() != null) {
            nameField.setText(Core.userInfo.getName());
            classMenu.setText(Core.userInfo.getClassNumber());
            letterMenu.setText(Core.userInfo.getLetter());
        }

        Core.userInfo.setThisTheme(null);

        for (MenuItem menuItem : classMenu.getItems()) {
            menuItem.setOnAction(event -> {
                classMenu.setText(menuItem.getText());
            });
        }
        for (MenuItem menuItem : letterMenu.getItems()) {
            menuItem.setOnAction(event -> {
                letterMenu.setText(menuItem.getText());
            });
        }
    }

    public void onStudentLogin(ActionEvent actionEvent) throws Exception {
        Core.userInfo.setThisTheme(null);
        if(!classMenu.getText().equals("Класс") && !nameField.getText().equals("") && !letterMenu.getText().equals("Буква")) {
            if (Core.teacher[Integer.parseInt(classMenu.getText())].getThemes().size() != 0) {

                List<String> choices = new ArrayList<>();
                for (Theme theme : Core.teacher[Integer.parseInt(classMenu.getText())].getThemes()) {
                    choices.add(theme.getName());
                }

                ChoiceDialog<String> dialog = new ChoiceDialog<>("Тема", choices);
                dialog.setTitle("Выберите тему");
                dialog.setHeaderText(null);
                dialog.setContentText("Выберите тему:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent() && !result.get().equals("Тема")) {
                    Core.userInfo.setThisTheme(Core.teacher[Integer.parseInt(classMenu.getText())].getThemeByName(result.get()));
                    writeUserInfo();
                    if (Core.userInfo.getThisTheme().getTriesCount() >= 1) {
                        if (Core.userInfo.getThisTheme().getHelp() != null) {
                            GApp.app.setScene(FXML_FILES.THEORY.getUrl());
                        } else {
                            GApp.app.setScene(FXML_FILES.STUDENT.getUrl());
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText("У вас закончились попытки");
                        alert.setContentText("Обратитесь к учителю, чтобы восстановить их.");
                        alert.showAndWait();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setContentText("Обратитесь к учителю, для настройки программы");
                alert.setHeaderText("Отсутствуют темы");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Введите ваше имя, фамилию, выберите класс и букву");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void onTeacherLogin(ActionEvent actionEvent) throws Exception {
        writeUserInfo();
        if(!Core.userInfo.getClassNumber().equals("Класс")) {
//            TextInputDialog inputDialog = new TextInputDialog();
//            inputDialog.setTitle("Подтверждение");
//            inputDialog.setContentText("Введите пароль");
//            inputDialog.setHeaderText(null);

            PasswordDialog passwordDialog = new PasswordDialog();

            Optional<String> password = passwordDialog.showAndWait();
            if (password.isPresent() && password.get().equals("12345"))
                GApp.app.setScene(FXML_FILES.TEACHER.getUrl());
            else if (password.isPresent()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setContentText("Неверный пароль");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Не выбран класс");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void OnAboutClick(ActionEvent actionEvent) throws Exception {
        GApp.app.setScene(FXML_FILES.ABOUT.getUrl());
    }

    private void writeUserInfo(){
        Core.userInfo.setName(nameField.getText());
        Core.userInfo.setClassNumber(classMenu.getText());
        Core.userInfo.setLetter(letterMenu.getText());
    }

    public void onReTriesClick(ActionEvent actionEvent) {
        PasswordDialog passwordDialog = new PasswordDialog();

        Optional<String> password = passwordDialog.showAndWait();
        if (password.isPresent() && password.get().equals("3434")) {
            for (int i=1; i < 12; i++) {
                Teacher teacher = Core.teacher[i];
                for (Theme theme : teacher.getThemes()) {
                    theme.setTriesCount(3);
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Информация");
            alert.setContentText("Попытки восстановлены!");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (password.isPresent()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Неверный пароль");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}
