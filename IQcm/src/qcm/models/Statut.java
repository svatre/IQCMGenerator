package qcm.models;

/**
 * Définit le statut d'un utilisateur
 */
public class Statut {

    private Integer id_statut;
    private String libelle;

    public Statut(int id_statut, String libelle) {
        this.id_statut = id_statut;
        this.libelle = libelle;
    }

    public int getId_statut() {
        return id_statut;
    }

    public void setId_statut(int id_statut) {
        this.id_statut = id_statut;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Statut other = (Statut) obj;
        if ((this.libelle == null) ? (other.libelle != null) : !this.libelle.equals(other.libelle)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.libelle != null ? this.libelle.hashCode() : 0);
        return hash;
    }
}
