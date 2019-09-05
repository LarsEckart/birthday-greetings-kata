package it.xpug.kata.birthday_greetings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XDateTest {

    @Test
    void getters() throws Exception {
        XDate date = new XDate("1789/01/24");
        assertThat(date.getMonth()).isEqualTo(1);
        assertThat(date.getDay()).isEqualTo(24);
    }

    @Test
    void isSameDate() throws Exception {
        XDate date = new XDate("1789/01/24");
        XDate sameDay = new XDate("2001/01/24");
        XDate notSameDay = new XDate("1789/01/25");
        XDate notSameMonth = new XDate("1789/02/25");

        assertThat(date.isSameDay(sameDay)).isTrue();
        assertThat(date.isSameDay(notSameDay)).isFalse();
        assertThat(date.isSameDay(notSameMonth)).isFalse();
    }

    @Test
    void equality() throws Exception {
        XDate base = new XDate("2000/01/02");
        XDate same = new XDate("2000/01/02");
        XDate different = new XDate("2000/01/04");

        assertThat(base).isEqualTo(same);
        assertThat(base).isNotEqualTo(different);
    }
}
