package qcm.models;

/**
 * DŽfinit un thÃ¨me de questionnaire
 * @author Ingesup
 */
public class Theme {

    private Integer idTheme;
    private String libelle;
    private int idUser;
    private int utilisations;
    private boolean estActif;

    public Theme(Integer idTheme, int idUser, String libelle, int utilisations, boolean estActif) {
        assert libelle != null && !libelle.matches("^\\s*$") : "Le Libelle ne doit tre ni null ni vide";
        assert idTheme == null || idTheme > 0 : "idTheme doit tre non nŽgatif (id: " + idTheme + " )";
        assert idUser > 0;
        this.idTheme = idTheme;
        this.libelle = libelle;
        this.idUser = idUser;
        this.utilisations = utilisations;
        this.estActif = estActif;
        assert invariant();
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        assert idTheme > 0 : "idTheme doit tre non nŽgatif (id: " + idTheme + " )";
        this.idTheme = idTheme;
        assert invariant();
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        assert idUser > 0;
        this.idUser = idUser;
        assert invariant();
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        assert libelle != null && !libelle.matches("^\\s*$") : "Le Libelle ne doit tre ni null ni vide";
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
        assert getLibelle() != null && !getLibelle().matches("^\\s*$") : "Le Libelle ne doit tre ni null ni vide";
        assert getIdUser() > 0;
        assert getIdTheme() == null || getIdTheme() > 0;
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
        final Theme other = (Theme) obj;
        if ((this.libelle == null) ? (other.libelle != null) : !this.libelle.equals(other.libelle)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.libelle != null ? this.libelle.hashCode() : 0);
        return hash;
    }
}
