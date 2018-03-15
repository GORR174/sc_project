package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.StudentTask;
import ru.catstack.sc_project.objects.help_objects.Time;
import ru.catstack.sc_project.objects.help_objects.Timer;
import ru.catstack.sc_project.objects.user.Student;
import ru.catstack.sc_project.objects.user.Tasks;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;
import ru.catstack.sc_project.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class StudentController extends GController{

    @FXML
    public Label latestTime;
    public Label thisTask;
    public Label taskText;
    public TextField resultField;
    public Label lastResult;
    public ImageView taskImage;
    public BorderPane imageBorderPane;
    public VBox radioVBox;


    private boolean trigger = false;

    private Time time = new Time(Core.userInfo.getThisTheme().getTime());
    private Timer timer;

    @Override
    public void onShow() throws Exception {

        Core.tasks = new Tasks(Core.userInfo.getThisTheme());

        Core.student = new Student();

        onTaskChanged();
        latestTime.setText("Осталось времени: " + time.getFormatedTime());
        timer = new Timer(1, () -> {
            if(time != null) {
                time.subtract(1);
                latestTime.setText("Осталось времени: " + time.getFormatedTime());
                long seconds = time.getSeconds();
                if (seconds < 0) {
                    try {
                        exit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (seconds < 60 && seconds >= 30)
                    latestTime.setTextFill(new Color(255 / 255f, 150 / 255f, 0, 1));
                else if (time.getSeconds() < 30)
                    latestTime.setTextFill(new Color(255 / 255f, 0, 0, 1));
            }
        });
        timer.run();

        resultField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    onSendClick(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Core.userInfo.getThisTheme().setTriesCount(Core.userInfo.getThisTheme().getTriesCount()-1);
    }

    public void onEndClick(ActionEvent actionEvent) throws Exception {
        exit();
    }

    public void onSendClick(ActionEvent actionEvent) throws Exception {
        StudentTask thisStudentTask = Core.tasks.getTasks().get(Core.student.thisTaskNumber-1);
        if(!thisStudentTask.getTask().isTestTask()) {
            thisStudentTask.setLastResult(resultField.getText());

            if (thisStudentTask.getTask().getResult().equalsIgnoreCase(resultField.getText()))
                thisStudentTask.setCorrectly(true);
            else
                thisStudentTask.setCorrectly(false);
        } else {
            thisStudentTask.setCorrectly(false);
            for (Node node : radioVBox.getChildren()) {
                if(((RadioButton)node).isSelected()) {
                    thisStudentTask.setLastResult(((RadioButton)node).getText());
                    if (thisStudentTask.getTask().getResult().equalsIgnoreCase(((RadioButton)node).getText()))
                        thisStudentTask.setCorrectly(true);
                }
            }
        }

        if(Core.student.thisTaskNumber < Core.userInfo.getThisTheme().getqCount())
            Core.student.thisTaskNumber++;
        onTaskChanged();
    }

    public void onPreviousClick(ActionEvent actionEvent) throws Exception {
        if(Core.student.thisTaskNumber > 1)
            Core.student.thisTaskNumber--;
        onTaskChanged();
    }

    public void onNextClick(ActionEvent actionEvent) throws Exception {
        if(Core.student.thisTaskNumber < Core.userInfo.getThisTheme().getqCount())
            Core.student.thisTaskNumber++;
        onTaskChanged();
    }

    private void exit() throws Exception {
        GApp.app.setScene(FXML_FILES.RESULTS.getUrl());
        time = null;
        if(timer != null)
        timer.stop();
    }

    private void onTaskChanged() throws Exception {
        StudentTask thisStudentTask = Core.tasks.getTasks().get(Core.student.thisTaskNumber-1);
        thisTask.setText("" + Core.student.thisTaskNumber + "/" + Core.userInfo.getThisTheme().getqCount());
        lastResult.setText("Ваш ответ: " + thisStudentTask.getLastResult());
        taskText.setText(thisStudentTask.getTask().getText());
        resultField.setText("");
        radioVBox.getChildren().clear();
        resultField.setDisable(false);
        if(!thisStudentTask.getTask().isTestTask()) {
            radioVBox.setVisible(false);
            radioVBox.setMinHeight(0);
            radioVBox.setMaxHeight(0);
            if (thisStudentTask.getTask().getImageFile() != null) {
                Image thisTaskImage = ImageUtils.getAssetsImage(thisStudentTask.getTask().getImageFile());
                taskImage.setImage(thisTaskImage);
                imageBorderPane.setVisible(true);
                imageBorderPane.setMinHeight(206);
                imageBorderPane.setMaxHeight(206);
                taskText.setMinHeight(Region.USE_COMPUTED_SIZE);
            } else {
                taskImage.setImage(null);
                imageBorderPane.setVisible(false);
                imageBorderPane.setMinHeight(4);
                imageBorderPane.setMaxHeight(4);
                taskText.setMinHeight(taskText.getPrefHeight() + 100);
            }
        } else {
            taskImage.setImage(null);
            imageBorderPane.setVisible(true);
            imageBorderPane.setMinHeight(0);
            imageBorderPane.setMaxHeight(0);
            taskText.setMinHeight(Region.USE_COMPUTED_SIZE);
            resultField.setDisable(true);

            radioVBox.setVisible(true);
            radioVBox.setMinHeight(220);
            radioVBox.setMaxHeight(220);

            ToggleGroup group = new ToggleGroup();

            ArrayList<RadioButton> radioButtons = new ArrayList<>();
            radioButtons.add(new RadioButton(thisStudentTask.getTask().getResult()));
            if(!thisStudentTask.getTask().getAnswer2().equals(""))
                radioButtons.add(new RadioButton(thisStudentTask.getTask().getAnswer2()));
            if(!thisStudentTask.getTask().getAnswer3().equals(""))
                radioButtons.add(new RadioButton(thisStudentTask.getTask().getAnswer3()));
            if(!thisStudentTask.getTask().getAnswer4().equals(""))
                radioButtons.add(new RadioButton(thisStudentTask.getTask().getAnswer4()));

            int size = radioButtons.size();
            for (int i = 0; i < size; i++) {
                int r = new Random().nextInt(radioButtons.size());
                radioVBox.getChildren().add(radioButtons.get(r));
                radioButtons.remove(r);
            }

            ((RadioButton)radioVBox.getChildren().get(0)).setSelected(true);
            for (Node node : radioVBox.getChildren()) {
                ((RadioButton)node).setToggleGroup(group);
                ((RadioButton) node).setPadding(new Insets(24, 0, 0 ,0));
            }
        }
        boolean all = false;
        for (StudentTask studentTask : Core.tasks.getTasks()) {
            if(studentTask.getLastResult().equals("не решено"))
                all = true;
        }
        if(!all && !trigger) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText("Вы ответили на все вопросы");
            alert.setContentText("Закончить работу?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                exit();
            } else {
                trigger = true;
            }
        }
    }
}
