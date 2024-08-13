package models;

import java.util.Date;
import java.text.SimpleDateFormat;

public class User {

    private int id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String indirizzo;
    private String documentoId;

    public User(int id, String nome, String cognome, Date dataDiNascita, String indirizzo, String documentoId) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.indirizzo = indirizzo;
        this.documentoId = documentoId;
    }

    // Getters & setters
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "User {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", cognome = '" + cognome + '\'' +
                ", dataDiNascita = " + sdf.format(dataDiNascita) +
                ", indirizzo = '" + indirizzo + '\'' +
                ", documentoId = '" + documentoId + '\'' +
                '}';
    }
}