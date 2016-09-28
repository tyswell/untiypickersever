package com.tagtrade.bean;

public class FirebaseNotificaiton {
	
	private String to;
	private String priority;
	private Notification notification;
	private MessageDataNotification data;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	public MessageDataNotification getData() {
		return data;
	}
	public void setData(MessageDataNotification data) {
		this.data = data;
	}
	
	
}
