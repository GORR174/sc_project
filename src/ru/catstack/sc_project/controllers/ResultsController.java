package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;
import ru.catstack.sc_project.utils.MailUtils;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultsController extends GController{
    @FXML
    public VBox resultBox;

    @Override
    public void onShow() throws Exception {

        int correctlyAnswers = 0;
        int mark;
        float ps;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();

        File file = new File("Ответы-" + dateFormat.format(date) + ".txt");
        FileWriter fileWriter = new FileWriter(file);

        String resultsString = "Ученик: " + Core.userInfo.getName() + System.lineSeparator() +
                "Класс: " + Core.userInfo.getClassNumber() + " " + Core.userInfo.getLetter() + System.lineSeparator() +
                "Тема: " + Core.userInfo.getThisTheme().getName() + System.lineSeparator() + System.lineSeparator() +
                "Результаты: " + System.lineSeparator();

        for (int i=0; i<Core.tasks.getTasks().size(); i++) {

            String isCorrectly = Core.tasks.getTasks().get(i).isCorrectly() ? "верно!" : "неверно!";
            Label label = new Label("Задание " + (i+1) + ": " + isCorrectly);

            if(Core.tasks.getTasks().get(i).isCorrectly())
                correctlyAnswers++;

            if(Core.tasks.getTasks().get(i).isCorrectly())
                label.setTextFill(new Color(0 / 180f, 180 / 255f, 0 / 255f, 1));
            else
                label.setTextFill(new Color(180 / 255f, 0 / 255f, 0 / 255f, 1));

            label.setOpaqueInsets(new Insets(10, 0, 0, 0));
            label.setFont(Font.font(18));

            resultBox.getChildren().add(label);

            resultsString += label.getText() + System.lineSeparator();

        }

        ps = (float)correctlyAnswers / ((float)Core.tasks.getTasks().size()/100f);

        if(ps <= Core.userInfo.getThisTheme().getResult2())
            mark = 2;
        else if(ps <= Core.userInfo.getThisTheme().getResult3())
            mark = 3;
        else if(ps <= Core.userInfo.getThisTheme().getResult4())
            mark = 4;
        else
            mark = 5;

        Label label = new Label("Оценка: " + mark);
        label.setOpaqueInsets(new Insets(40, 0, 0, 0));
        label.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 24));

        resultBox.getChildren().add(label);

        resultsString += System.lineSeparator() + "Оценка: " + mark;

        fileWriter.write(resultsString);

        fileWriter.flush();

        if(!Core.userInfo.getThisTheme().getEmail().equals("")) {
            try {

                String endMessage = "\n\n\n----------------------------------\nЕсли у вас есть какие-либо вопросы, " +
                        "касающиеся программы, напишите на этот адрес: catstack.team@gmail.com";

                MailUtils.sendMessage(Core.userInfo.getThisTheme().getEmail(), "Результаты по тесту \"" +
                        Core.userInfo.getThisTheme().getName() + "\"", resultsString + endMessage);
            } catch (Exception ex) {
                System.out.println("Can't send email");
                System.out.println(Core.userInfo.getThisTheme().getEmail());
                ex.printStackTrace();
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setContentText("Количество оставшихся попыток по данной теме: " + Core.userInfo.getThisTheme().getTriesCount());
        alert.setHeaderText("Тест окончен!");
        alert.showAndWait();

    }

    public void onAgainClick(ActionEvent actionEvent) throws Exception {
        if (Core.userInfo.getThisTheme().getTriesCount() >= 1) {
            GApp.app.setScene(FXML_FILES.STUDENT.getUrl());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("У вас закончились попытки");
            alert.setContentText("Обратитесь к учиителю, чтобы восстановить их.");
            alert.showAndWait();
        }
    }

    public void onBackClick(ActionEvent actionEvent) throws Exception {
        GApp.app.setScene(FXML_FILES.LOGIN.getUrl());
    }

    public void onTheoryClick(ActionEvent actionEvent) throws Exception {
        if (Core.userInfo.getThisTheme().getHelp() != null) {
            GApp.app.setScene(FXML_FILES.THEORY.getUrl());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText(null);
            alert.setHeaderText("Отсутствует блок теории для данной темы");
            alert.showAndWait();
        }
    }
}
