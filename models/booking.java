package models;

import java.util.Date;

public class Booking {

    private int id;
    private int idCorso;
    private int idUtente;
    private Date dataInizio;
    private Date dataFine;

    public Booking(int id, int idCorso, int idUtente, Date dataInizio, Date dataFine) {
        this.id = id;
        this.idCorso = idCorso;
        this.idUtente = idUtente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    // Getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCorso() {
        return idCorso;
    }

    public void setIdCorso(int idCorso) {
        this.idCorso = idCorso;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "Booking {" +
                "id = " + id +
                ", idCorso = " + idCorso +
                ", idUtente = " + idUtente +
                ", dataInizio = " + dataInizio +
                ", dataFine = " + dataFine +
                '}';
    }
}