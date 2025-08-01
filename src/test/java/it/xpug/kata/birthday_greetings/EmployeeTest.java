package it.xpug.kata.birthday_greetings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    public void testBirthday() throws Exception {
        Employee employee = new Employee("foo", "bar", "1990/01/31", "a@b.c");
        
        assertThat(employee.isBirthday(new XDate("2008/01/30")))
            .as("not his birthday")
            .isFalse();
        assertThat(employee.isBirthday(new XDate("2008/01/31")))
            .as("his birthday")
            .isTrue();
    }
}
