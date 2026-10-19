package com.mycompany.chatapp_part1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private String sendOption;

    public Message() {
    }

    public Message(int messageNumber, String recipientCell, String messageText) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    public String generateMessageID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        if (this.recipientCell == null || this.recipientCell.length() > 10) {
            return false;
        }
        return this.recipientCell.startsWith("+");
    }

    public String checkRecipientCellStatus() {
        if (checkRecipientCell()) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or exceeds 10 characters.";
        }
    }

    public boolean checkMessageLength() {
        return this.messageText != null && this.messageText.length() <= 250;
    }

    public String createMessageHash() {
        if (this.messageID == null || this.messageID.length() < 2 || this.messageText == null || this.messageText.trim().isEmpty()) {
            return "";
        }

        String firstTwoDigits = this.messageID.substring(0, 2);
        String[] words = this.messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");

        return (firstTwoDigits + ":" + this.messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                this.sendOption = "Sent";
                return "Message successfully sent.";
            case 2:
                this.sendOption = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                this.sendOption = "Stored";
                return "Message successfully stored.";
            default:
                this.sendOption = "Unknown";
                return "Invalid option selected.";
        }
    }

    public String printMessageDetails() {
        return "Message ID: " + this.messageID + "\n"
                + "Message Hash: " + this.messageHash + "\n"
                + "Recipient: " + this.recipientCell + "\n"
                + "Message: " + this.messageText;
    }

    public String storeMessageJSON() {
        String jsonContent = "{\n"
                + "  \"messageID\": \"" + this.messageID + "\",\n"
                + "  \"messageNumber\": " + this.messageNumber + ",\n"
                + "  \"recipientCell\": \"" + this.recipientCell + "\",\n"
                + "  \"messageText\": \"" + this.messageText + "\",\n"
                + "  \"messageHash\": \"" + this.messageHash + "\"\n"
                + "}";

        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write(jsonContent + "\n");
            return "Message successfully stored in messages.json file.";
        } catch (IOException e) {
            return "Error writing to JSON file: " + e.getMessage();
        }
    }

    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public String getSendOption() { return sendOption; }
}