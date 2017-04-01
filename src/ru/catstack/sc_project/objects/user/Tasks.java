package ru.catstack.sc_project.objects.user;

import ru.catstack.sc_project.objects.StudentTask;
import ru.catstack.sc_project.objects.Theme;
import ru.catstack.sc_project.objects.ThemeTask;

import java.util.ArrayList;
import java.util.Random;

public class Tasks {

    private ArrayList<StudentTask> tasks = new ArrayList<>();

    public Tasks(Theme theme){
        ArrayList<ThemeTask> tasksHelp = new ArrayList<>(theme.getTasks());
        for (int i=0; i<theme.getqCount(); i++) {
            if(tasksHelp.size()>0) {
                int randomTaskNumber = new Random().nextInt(tasksHelp.size());
                ThemeTask helpTask = tasksHelp.get(randomTaskNumber);

                tasks.add(new StudentTask(helpTask));
                tasksHelp.remove(helpTask);
            }
        }
    }

    public ArrayList<StudentTask> getTasks() {
        return tasks;
    }
}
