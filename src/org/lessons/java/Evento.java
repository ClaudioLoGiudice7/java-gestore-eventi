package org.lessons.java;

import java.time.LocalDate;

public class Evento {
    private String title;
    private LocalDate date;
    private int totalSeats;
    private int reservedSeats;

    public Evento(String title, LocalDate date, int totalSeats) throws Exception {
        if (date.isBefore(LocalDate.now())) {
            throw new Exception("La data inserita è già passata");
        }

        if (totalSeats <= 0) {
            throw new Exception("Il numero di posti inserito non è valido");
        }

        this.title = title;
        this.date = date;
        this.totalSeats = totalSeats;
        this.reservedSeats = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void reserve() throws Exception {
        if (date.isBefore(LocalDate.now()) || reservedSeats >= totalSeats) {
            throw new Exception("Impossibile effettuare la prenotazione");
        }

        reservedSeats++;
    }

    public void cancel() throws Exception {
        if (date.isBefore(LocalDate.now()) || reservedSeats <= 0) {
            throw new Exception("Impossibile effettuare la disdetta");
        }

        reservedSeats--;
    }

    @Override
    public String toString() {
        return date + " - " + title;
    }
}