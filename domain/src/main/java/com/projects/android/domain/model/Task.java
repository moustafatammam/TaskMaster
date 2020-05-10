package com.projects.android.domain.model;

import java.util.Date;

public class Task {

    private String title;
    private final long id;
    private int priority;
    private Date date;
    private String comment;
    private String label;
    private boolean status;

    public Task(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Task task = (Task) obj;
        return id == task.id;
    }

    @Override
    public String toString() {
        return "Task {" +
                "id-" + id +
                ", title-" + title +
                ", comment" + comment +
                ", date" + date +
                ", priority" + priority +
                ", label" + label +
                "}";

    }
}
