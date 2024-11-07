package com.task.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="user_todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;
    private String TaskName;
    public String TaskStatus = "false";

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    public Todo() {

    }

    public Todo(int taskId, String taskName, String TaskStatus, User users) {
        this.taskId = taskId;
        this.TaskName = taskName;
        this.users = users;
        this.TaskStatus = TaskStatus;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Todo [taskId=" + taskId + ", TaskName=" + TaskName + ", users=" + users + "]";
    }
}
