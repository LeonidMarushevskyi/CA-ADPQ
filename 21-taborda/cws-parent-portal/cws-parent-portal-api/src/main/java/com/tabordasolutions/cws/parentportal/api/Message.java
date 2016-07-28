package com.tabordasolutions.cws.parentportal.api;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;

@Entity
@Table(name = "messages")
@JsonSerialize(using=MessageSerializer.class)
public class Message implements Comparable<Message> {
    private long id;

    private User author;
    private User recipient;
    private Date dateCreated;
    private String subject;
    private String body;

    private Conversation conversation;

    public Message()
    { 
    	this.dateCreated = new Date();
    }

    public Message(long id, Date dateCreated, User author, User recipient, String subject, String body) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.author = author;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "message_id_seq")
    @SequenceGenerator(name="message_id_seq",sequenceName="message_id_seq",allocationSize=1)
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Column(name = "body")
    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    @Column(name = "date_created")
    public Date getDateCreated() { return dateCreated; }

    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    @ManyToOne
    @JoinColumn(name="author")
    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    @ManyToOne
    @JoinColumn(name="recipient")
    public User getRecipient() { return recipient; }

    public void setRecipient(User recipient) { this.recipient = recipient; }

    @Column(name = "subject")
    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    @ManyToOne
    @JoinColumn(name="conversation_id")
    public Conversation getConversation() { return conversation; }

    public void setConversation(Conversation conversation) { this.conversation = conversation; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (author != null ? !author.equals(message.author) : message.author != null) return false;
        if (recipient != null ? !recipient.equals(message.recipient) : message.recipient != null) return false;
        if (dateCreated != null ? !dateCreated.equals(message.dateCreated) : message.dateCreated != null) return false;
        if (subject != null ? !subject.equals(message.subject) : message.subject != null) return false;
        if (body != null ? !body.equals(message.body) : message.body != null) return false;
        return !(conversation != null ? !conversation.equals(message.conversation) : message.conversation != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", recipient=" + recipient +
                ", dateCreated=" + dateCreated +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
    @Override
    public int compareTo(Message o) {
        return dateCreated.compareTo(o.getDateCreated());
    }
}
