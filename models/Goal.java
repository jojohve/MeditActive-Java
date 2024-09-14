package models;

public class Goal {
    private int id;
    private String nome;
    private String descrizione;
    private String tipologia;
    private int durata;
    private String disponibilita;

    public Goal(int id, String nome, String descrizione, String tipologia, int durata, String disponibilita) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipologia = tipologia;
        this.durata = durata;
        this.disponibilita = disponibilita;
    }    

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(String disponibilita) {
        this.disponibilita = disponibilita;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Nome: %s, Durata: %d giorni, Disponibile: %s",
                id, nome, durata, disponibilita);
    }
}