package models;

import java.util.Date;

public class users {

    int id;
    String nome;
    String cognome;
    Date data_di_nascita;
    String indirizzo;
    String documento_id;

    users(int id, String nome, String cognome, Date data_di_nascita, String indirizzo, String documento_id) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.data_di_nascita = data_di_nascita;
        this.indirizzo = indirizzo;
        this.documento_id = documento_id;
    }

}