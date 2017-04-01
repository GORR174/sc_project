package ru.catstack.sc_project.resources;

import ru.catstack.sc_project.controllers.TeacherController;
import ru.catstack.sc_project.objects.user.Student;
import ru.catstack.sc_project.objects.user.Tasks;
import ru.catstack.sc_project.objects.user.Teacher;
import ru.catstack.sc_project.objects.user.UserInfo;

public class Core {
    public static Teacher[] teacher = new Teacher[12]; //changed
    public static Student student;
    public static UserInfo userInfo;
    public static TeacherController teacherController;
    public static Tasks tasks;
}
