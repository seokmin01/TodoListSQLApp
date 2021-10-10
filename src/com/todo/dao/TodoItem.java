package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String category;
    private String desc;
    private String due_date;
    private String current_date;


    public TodoItem(String title, String category, String desc, String due_date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	
        this.title=title;
        this.category=category;
        this.desc=desc;
        this.due_date=due_date;
        this.current_date=sdf.format(new Date());
    }
    
    public TodoItem(String title, String category, String desc, String due_date, String current_date) {
    	this.title=title;
    	this.category=category;
        this.desc=desc;
        this.due_date=due_date;
        this.current_date=current_date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	@Override
	public String toString() {
		return "[" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date;
	}
    
	public String toSaveString() {
		return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
	}
}
