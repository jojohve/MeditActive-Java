package models;

public class Goal {

    private int id;
    private String nome;
    private String descrizione;
    private String tipologia;
    private int durata;
    private String disponibilità;

    public Goal(int id, String nome, String descrizione, String tipologia, int durata, String disponibilità) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipologia = tipologia;
        this.durata = durata;
        this.disponibilità = disponibilità;
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

    public String getDisponibilità() {
        return disponibilità;
    }

    public void setDisponibilità(String disponibilità) {
        this.disponibilità = disponibilità;
    }

    @Override
    public String toString() {
        return "Goal {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", descrizione = '" + descrizione + '\'' +
                ", tipologia = '" + tipologia + '\'' +
                ", durata = " + durata +
                ", disponibilità = " + disponibilità +
                '}';
    }
}