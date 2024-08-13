package models;

import java.util.Date;

public class booking {
    int id;
    int id_corso;
    int id_utente;
    Date data_inizio;
    Date data_fine;

    booking(int id, int id_corso, int id_utente, Date data_inizio, Date data_fine) {
        this.id = id;
        this.id_corso = id_corso;
        this.id_utente = id_utente;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
    }
}