package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "conversations")
@JsonSerialize(using=CreateConversationSerializer.class)
public class Conversation {
    private long id;

    private String initializer;
    private String sender;
    private String receiver;
    private Date dateCreated;
    private Date dateUpdated;
    private String subject;

    private Message baseMessage;
    boolean read;

    private Set<Message> messages;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "conversation_id_seq")
    @SequenceGenerator(name="conversation_id_seq",sequenceName="conversation_id_seq",allocationSize=1)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transient
    public String getInitializer() {
        return initializer;
    }

    public void setInitializer(String initializer) {
        this.initializer = initializer;
    }

    @Transient
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Transient
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Column(name = "datecreated")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "dateupdated")
    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Transient
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Transient
    public Message getBaseMessage() { return loadBaseMessage(); }

    public void setBaseMessage(Message baseMessage) {
        this.baseMessage = baseMessage;
    }

    @Column(name = "read")
    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @OneToMany( mappedBy="conversation", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ElementCollection(targetClass=Message.class)
    public Set<Message> getMessages() { 
    	TreeSet<Message> sortedMessages = new TreeSet<Message>();
    	sortedMessages.addAll(messages != null ? messages : new HashSet<Message>()); 
    	return sortedMessages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    private Message loadBaseMessage(){
        Message emptyMessage = new Message();
        if (messages == null || baseMessage == null) { return  emptyMessage; }
        List<Message> sorted = new ArrayList<Message>(messages);
        Collections.sort(sorted, Collections.reverseOrder());
        return sorted.isEmpty() ? emptyMessage : sorted.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversation that = (Conversation) o;

        if (id != that.id) return false;
        if (read != that.read) return false;
        if (initializer != null ? !initializer.equals(that.initializer) : that.initializer != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (receiver != null ? !receiver.equals(that.receiver) : that.receiver != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(that.dateUpdated) : that.dateUpdated != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (baseMessage != null ? !baseMessage.equals(that.baseMessage) : that.baseMessage != null) return false;
        return !(messages != null ? !messages.equals(that.messages) : that.messages != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (initializer != null ? initializer.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (read ? 1 : 0);
        return result;
    }

    public void updateMessage(Message message){
        if(message != null){
            this.setBaseMessage(message);
            String name = message.getAuthor() != null ? message.getAuthor().getFullName() : "";
            this.setInitializer(name);
            this.setSender(name);
            name = message.getRecipient() != null ? message.getRecipient().getFullName() : "";
            this.setReceiver(name);
            this.setSubject(message.getSubject());
        }
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", initializer='" + initializer + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", subject='" + subject + '\'' +
                ", baseMessage=" + baseMessage +
                ", read=" + read +
                ", messages=" + messages +
                '}';
    }
}
