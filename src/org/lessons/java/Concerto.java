package org.lessons.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {
    private LocalTime hour;
    private BigDecimal price;

    public Concerto(String title, LocalDate date, int totalSeats, LocalTime hour, BigDecimal price) throws Exception {
        super(title, date, totalSeats);
        this.hour = hour;
        this.price = price;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFormattedDateAndHour() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return getDate().format(dateTimeFormatter) + " " + hour.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getFormattedPrice() {
        return price.setScale(2).toString() + "euro";
    }

    @Override
    public String toString() {
        return getFormattedDateAndHour() + " - " + getTitle() + " - " + getFormattedPrice();
    }
}
