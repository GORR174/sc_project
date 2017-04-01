package ru.catstack.sc_project.objects;

public class StudentTask {

    private ThemeTask task;
    private boolean isCorrectly = false;
    private String lastResult = "не решено";

    public StudentTask(ThemeTask task) {
        this.task = task;
    }

    public ThemeTask getTask() {
        return task;
    }

    public void setTask(ThemeTask task) {
        this.task = task;
    }

    public boolean isCorrectly() {
        return isCorrectly;
    }

    public void setCorrectly(boolean correctly) {
        isCorrectly = correctly;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }
}
