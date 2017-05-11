package com.chinacreator.bean;

/**
 * Created by Smile on 2017/5/10.
 */

public class Student {
    String name;
    Curosr[] cursors;

    public static class Curosr {
        String name;
        String[] teacher;

        public Curosr(String name, String[] teacher) {
            this.name = name;
            this.teacher = teacher;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getTeacher() {
            return teacher;
        }

        public void setTeacher(String[] teacher) {
            this.teacher = teacher;
        }
    }

    public Student(String name, Curosr[] cursors) {
        this.name = name;
        this.cursors = cursors;
    }

    public Curosr[] getCursors() {
        return cursors;
    }

    public void setCursors(Curosr[] cursors) {
        this.cursors = cursors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
