package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il titolo dell'evento: ");
        String title = scanner.nextLine();

        LocalDate date = null;
        boolean validDate = false;
        boolean correctDateFormat = false;
        do {
            System.out.print("Inserisci la data dell'evento (GG-MM-AAAA): ");
            String dateString = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            try {
                date = LocalDate.parse(dateString, formatter);

                if (date.isBefore(LocalDate.now())) {
                    System.out.println("La data inserita è già passata. Riprova.");
                } else {
                    validDate = true;
                    correctDateFormat = true;
                }

                correctDateFormat = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato data errato. Riprova.");
            }
        } while (!validDate || !correctDateFormat);

        int totalSeats = 0;
        boolean validTotalSeats = false;
        do {
            System.out.print("Inserisci il numero di posti totali della location: ");
            totalSeats = scanner.nextInt();
            if (totalSeats < 1 || totalSeats > 100000) {
                System.out.println("Il numero di posti deve essere compreso tra 1 e 100.000. Riprova.");
            } else {
                validTotalSeats = true;
            }
        } while (!validTotalSeats);

        try {
            Evento evento = new Evento(title, date, totalSeats);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = evento.getDate().format(dateFormatter);
            System.out.println("Evento creato: " + formattedDate + " - " + evento.getTitle());

            int reservationsCount = 0;
            boolean validReservationsCount = false;
            do {
                System.out.print("Inserisci il numero di prenotazioni da effettuare: ");
                reservationsCount = scanner.nextInt();
                if (reservationsCount < 1 || reservationsCount > totalSeats) {
                    System.out.println("Il numero di prenotazioni deve essere compreso tra 1 e 100.000. Riprova.");
                } else {
                    validReservationsCount = true;
                }
            } while (!validReservationsCount);

            for (int i = 0; i < reservationsCount; i++) {
                try {
                    evento.reserve();
                    System.out.println("Prenotazione effettuata. Posti prenotati: " + evento.getReservedSeats());
                } catch (Exception e) {
                    System.out.println("Errore nella prenotazione: " + e.getMessage());
                }
            }

            System.out.println("Posti prenotati: " + evento.getReservedSeats());
            System.out.println("Posti disponibili: " + (evento.getTotalSeats() - evento.getReservedSeats()));

            int cancellationsCount = 0;
            boolean validCancellationsCount = false;
            do {
                System.out.print("Inserisci il numero di disdette da effettuare (Se non vuoi effettuare disdette inserisci 0): ");
                cancellationsCount = scanner.nextInt();
                if (cancellationsCount < 0 || cancellationsCount > evento.getReservedSeats()) {
                    System.out.println("Il numero di disdette deve essere compreso tra 0 e " + evento.getReservedSeats() + ". Riprova.");
                } else {
                    validCancellationsCount = true;
                }
            } while (!validCancellationsCount);

            for (int i = 0; i < cancellationsCount; i++) {
                try {
                    evento.cancel();
                    System.out.println("Disdetta effettuata. Posti prenotati: " + evento.getReservedSeats());
                } catch (Exception e) {
                    System.out.println("Errore nella disdetta: " + e.getMessage());
                }
            }

            System.out.println("Posti prenotati: " + evento.getReservedSeats());
            System.out.println("Posti disponibili: " + (evento.getTotalSeats() - evento.getReservedSeats()));
        } catch (Exception e) {
            System.out.println("Errore durante la creazione dell'evento: " + e.getMessage());
        }

        scanner.close();
    }
}