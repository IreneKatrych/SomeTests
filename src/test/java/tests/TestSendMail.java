// filename: tests/TestSendMail.java

package tests;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import pageobjects.SendMail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSendMail extends Base {

    private SendMail sendMail;

    //credentials
    private final String mail1 = "test1forpractice";
    private final String mail2 = "test2forpractice";
    private final String mailEnd = "@meta.ua";
    private final   String password = "MJgshLUi4PeyjNs";

    //message
    private final String user1 = "Test User1";
    private final String subject = "Tests";
    private final String message = "Huston,\n" +
            " we have some problems";

    //Loading page
    @Before
    public void setUp() {sendMail = new SendMail(driver);}

    //Log in
    @Test
    public void A_logInToMailBox() {
        sendMail.loginToMailWith(mail1, password);
        assertTrue("Failed to login", sendMail.successLoginMessagePresent());
        sendMail.logOutFromMailBox();
    }

    //Send mail
    @Test
    public void B_sendMail() {
        sendMail.loginToMailWith(mail1, password);
        sendMail.successLoginMessagePresent();
        assertTrue("Mail wasn't sent",
                sendMail.successSendMessage(mail2 + mailEnd, subject, message));
        sendMail.logOutFromMailBox();
    }

    //Check income mail
    @Test
    public void C_checkIncomeMail() {
        sendMail.loginToMailWith(mail2, password);
        sendMail.successLoginMessagePresent();
        assertTrue("message doesn't come",
                  sendMail.checkIfMailComes( user1,mail1 + mailEnd, subject, message));
        sendMail.logOutFromMailBox();
    }

}