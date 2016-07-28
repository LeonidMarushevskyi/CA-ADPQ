package com.tabordasolutions.cws.parentportal.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

public class MessageDAOTest {
    private SessionFactory sessionFactory;
    private MessageDAO dao;
    private UserDAO userDao;
    private ConversationDAO conversationDao;

    @Before
    public void setup(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();
        dao = new MessageDAO(sessionFactory);
        userDao = new UserDAO(sessionFactory);
        conversationDao = new ConversationDAO(sessionFactory);
    }

    @After
    public void teardown() {sessionFactory.close();}

    //@Test
    public void testCreateMessages(){
        Message savedMessage = dao.create(buildMessage(new User(), new User()));
        assertTrue("Expected conversation to be greater than 0", savedMessage.getId() > 0);
    }

    //@Test
    public void testFindingASavedMessages(){
        Message savedMessage = dao.create(buildMessage(new User(), new User()));
        Message foundMessage = dao.find(savedMessage.getId());
        assertEquals("Expected to find the previously saved conversation", savedMessage.getId(), foundMessage.getId());
    }

    //@Test
    public void testFindingMessagesBySender(){
        User wilma = buildUser("wilma", "Flinstone", "BamBam", "123 Bedrock", "AZ", "HollyRock", "12311", "http://myimage.jpg", "me@myisp.com");
        User betty = buildUser("betty", "Rubble", "BamBam", "124 Bedrock", "AZ", "HollyRock", "12311", "http://myimage.jpg", "br@myisp.com");
        setupDB(wilma, betty);
        List<Message> foundMessages = dao.findMessagesBySender(wilma);
        assertEquals("Expected to only find one message", 1, foundMessages.size());
        assertEquals("Expected find user a sender", wilma.getFullName(), foundMessages.get(0).getAuthor().getFullName());
    }

    private void setupDB(User sender, User recipient){
        userDao.create(sender);
        userDao.create(recipient);
        Message msg1 = buildMessage(sender, recipient);
        Message msg2 = buildMessage(recipient, sender);
        Conversation conversation = buildConversation( sender, recipient, msg1);
        conversationDao.create(conversation);

        msg1.setConversation(conversation);
        dao.create(msg1);
        msg2.setConversation(conversation);
        dao.create(msg2);
    }
    private Conversation buildConversation(User sender, User reciever, Message msg){
        Conversation conversation = new Conversation();
        conversation.setInitializer(sender.getFullName());
        conversation.setSender(sender.getFullName());
        conversation.setReceiver(reciever.getFullName());
        conversation.setDateCreated(new Date());
        conversation.setDateUpdated(new Date());
        conversation.setSubject(msg.getSubject());
        conversation.setBaseMessage(msg);
        conversation.setRead(false);

        return conversation;
    }

    private Message buildMessage(User sender, User recipient) {
        return new Message(0L, new Date(), sender, recipient, "String subject", "String body");
    }

    private User buildUser(String fname, String lname, String ico, String addr, String st, String city, String zip, String url, String email){
        User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setInCareOf(ico);
        user.setStreetAddress1(addr);
        user.setState(st);
        user.setCity(city);
        user.setZip(zip);
        user.setImageUrl(url);
        user.setEmail(email);
        return user;
    }
}
