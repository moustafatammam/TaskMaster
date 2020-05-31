package com.projects.android.ui.model;

import java.util.Date;

public class TaskView {
    private long id;

    private String title;

    private int priority;

    private Date date;

    private String comment;

    private String label;

    private int status;

    public TaskView(String title, int priority, Date date, String comment, String label, int status) {
        this.title = title;
        this.priority = priority;
        this.date = date;
        this.comment = comment;
        this.label = label;
        this.status = status;
    }

    public TaskView(long id, String title, int priority, Date date, String comment, String label, int status) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.date = date;
        this.comment = comment;
        this.label = label;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
