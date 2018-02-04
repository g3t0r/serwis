package com.example.service.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private User sender;
	@JsonIgnore
	@ManyToOne
	private User receiver;
	private String content;

	public Message(User sender, User receiver, String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}

	public Message() {
	}

	//Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", sender=" + sender +
				", receiver=" + receiver +
				", content='" + content + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Message)) return false;
		Message message = (Message) o;
		return Objects.equals(id, message.id) &&
				Objects.equals(sender, message.sender) &&
				Objects.equals(receiver, message.receiver) &&
				Objects.equals(content, message.content);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, sender, receiver, content);
	}
}
