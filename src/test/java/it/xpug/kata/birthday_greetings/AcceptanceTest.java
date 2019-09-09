package it.xpug.kata.birthday_greetings;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AcceptanceTest {

    private BirthdayService birthdayService;

    @BeforeEach
    void setUp() {
        birthdayService = new BirthdayService();
    }

    @Test
    void willSendGreetings_whenItsSomebodysBirthday() throws Exception {
        try (SimpleSmtpServer mailServer = SimpleSmtpServer.start(SimpleSmtpServer.AUTO_SMTP_PORT)) {

            birthdayService.sendGreetings("employee_data.txt", new XDate("2008/10/08"), "localhost", mailServer.getPort());

            assertThat(mailServer.getReceivedEmails()).hasSize(1);

            SmtpMessage message = mailServer.getReceivedEmails().get(0);
            assertAll(
                    () -> assertThat(message.getBody()).isEqualTo("Happy Birthday, dear John!"),
                    () -> assertThat(message.getHeaderValue("Subject")).isEqualTo("Happy Birthday!"));

            List<String> recipients = message.getHeaderValues("To");
            assertAll(() -> assertThat(recipients).hasSize(1),
                    () -> assertThat(recipients.get(0)).isEqualTo("john.doe@example.com"));
        }
    }

    @Test
    void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        try (SimpleSmtpServer mailServer = SimpleSmtpServer.start(SimpleSmtpServer.AUTO_SMTP_PORT)) {
            birthdayService.sendGreetings("employee_data.txt", new XDate("2008/01/01"), "localhost", mailServer.getPort());

            assertThat(mailServer.getReceivedEmails()).hasSize(0);
        }
    }
}