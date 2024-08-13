package models;

public class goals {

    int id;
    String nome;
    String descrizione;
    String tipologia;
    int durata;
    Boolean disponibilità;

    goals(int id, String nome, String descrizione, String tipologia, int durata, Boolean disponibilità) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipologia = tipologia;
        this.durata = durata;
        this.disponibilità = disponibilità;
    }
}