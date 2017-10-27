package ru.catstack.sc_project.objects;

import java.util.ArrayList;

public class Theme {

    private String name = "Название темы";
    private String help = null;
    private int time = 30;
    private int qCount = 15;
    private int result2 = 20;
    private int result3 = 60;
    private int result4 = 80;
    private int result5 = 100;
    private ArrayList<ThemeTask> tasks = new ArrayList<>();

    public Theme(){

    }

    public Theme(String name) {
        this.name = name;
    }

    public Theme(String name, int time, int qCount) {
        this.name = name;
        this.time = time;
        this.qCount = qCount;
    }

    public ArrayList<ThemeTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<ThemeTask> tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThemeTask getTaskByText(String text){
        ThemeTask rTask = null;
        for (ThemeTask task : tasks) {
            if(task.getText().equals(text))
                rTask = task;
        }
        return rTask;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getqCount() {
        return qCount;
    }

    public void setqCount(int qCount) {
        this.qCount = qCount;
    }

    public int getResult2() {
        return result2;
    }

    public void setResult2(int result2) {
        this.result2 = result2;
    }

    public int getResult3() {
        return result3;
    }

    public void setResult3(int result3) {
        this.result3 = result3;
    }

    public int getResult4() {
        return result4;
    }

    public void setResult4(int result4) {
        this.result4 = result4;
    }

    public int getResult5() {
        return result5;
    }

    public void setResult5(int result5) {
        this.result5 = result5;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
