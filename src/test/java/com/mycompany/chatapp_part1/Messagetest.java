package com.mycompany.chatapp_part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message(2, "08575975889", "Hi Mike, can you join us for dinner tonight?");
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "A".repeat(251);
        Message msg = new Message(1, "+27718693002", longText);
        assertFalse(msg.checkMessageLength());
    }

    @Test
    public void testMessageHashCreation() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        msg.setMessageID("0012345678");
        String expectedHash = "00:1:HITONIGHT";
        assertEquals(expectedHash, msg.createMessageHash());
    }

    @Test
    public void testSentMessageOptions() {
        Message msg = new Message(1, "+27718693002", "Hello World");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
}