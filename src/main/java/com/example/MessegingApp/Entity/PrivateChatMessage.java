package com.example.MessegingApp.Entity;

public class PrivateChatMessage {
    private String sender;
    private String recipient;
    private String content;

    public PrivateChatMessage() {}

    public PrivateChatMessage(String sender, String recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
