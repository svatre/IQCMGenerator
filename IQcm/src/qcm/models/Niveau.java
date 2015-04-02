package qcm.models;

public class Niveau {

    private Integer idNiveau;
    private String libelle;
    private int utilisations;
    private boolean estActif;

    public Niveau(int idNiveau, String libelle, int utilisations, boolean estActif) {
        assert idNiveau > 0;
        assert libelle != null && !libelle.trim().isEmpty() : "Le Libelle ne doit être ni null ni vide";

        this.idNiveau = idNiveau;
        this.libelle = libelle;
        this.utilisations = utilisations;
        this.estActif = estActif;
        assert invariant();
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        assert idNiveau > 0;
        this.idNiveau = idNiveau;
        assert invariant();
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        assert libelle != null && !libelle.trim().isEmpty() : "Le Libelle ne doit être ni null ni vide";
        this.libelle = libelle;
        assert invariant();
    }

    public int getUtilisations() {
        return utilisations;
    }

    public boolean estActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }
    
    protected boolean invariant() {
        assert getLibelle() != null && !getLibelle().trim().isEmpty() : "Le Libelle ne doit être ni null ni vide";
        assert getIdNiveau() > 0;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Niveau other = (Niveau) obj;
        if ((this.libelle == null) ? (other.libelle != null) : !this.libelle.equals(other.libelle)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.libelle != null ? this.libelle.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return " [ " + getLibelle() + " : " + getIdNiveau() + " ] ";
    }

}
