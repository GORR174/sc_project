package ru.catstack.sc_project.objects.user;

import ru.catstack.sc_project.objects.Theme;
import ru.catstack.sc_project.objects.ThemeTask;

public class UserInfo {

    private String name;
    private String classNumber;
    private String letter;

    private ThemeTask thisTask;
    private Theme thisTheme;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public ThemeTask getThisTask() {
        return thisTask;
    }

    public void setThisTask(ThemeTask thisTask) {
        this.thisTask = thisTask;
    }

    public Theme getThisTheme() {
        return thisTheme;
    }

    public void setThisTheme(Theme thisTheme) {
        this.thisTheme = thisTheme;
    }
}
