package it.xpug.kata.birthday_greetings;

import java.io.IOException;
import java.text.ParseException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public class Main {

    public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {
        BirthdayService service = new BirthdayService();
        service.sendGreetings("employee_data.txt", new XDate(), "localhost", 25);
    }
}
