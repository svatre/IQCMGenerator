package qcm.models;

/**
 * Réponse à une question. Possède un numéro (idReponse), une note qui est
 * propre à chaque question, un libellé court et un descriptif de la réponse
 * (réponse plus détaillée), le numéro de la question en rapport avec la
 * réponse et un booleen qui détermine si la réponse est bonne ou non.
 *
 * <blockquote>
 * <strong>Invariants :</strong><br />
 * <code>idReponse_non_negatif : getIdReponse() est null ou > 0</code><br />
 * <code>idQuestion_non_negatif : getIdQuestion() > 0</code><br />
 * <code>libelle_renseigne : getLibelle() != null</code><br />
 * <code>reponse_correcte_note_positive : if(this.estCorrecte()) { this.getNote() > 0 }</code>
 * </blockquote>
 *
 * @author Ingesup
 */
public class Reponse {

    private Integer idReponse;
    private String libelle;
    private String descriptif;
    private boolean estCorrecte;
    private int note;
    private Integer idQuestion;

    public Reponse(final Integer idReponse, final String libelle,
            final String descriptif, final boolean estCorrecte, final int note, final Integer idQuestion) {
        this.idReponse = idReponse;
        this.libelle = libelle;
        this.descriptif = descriptif;
        this.estCorrecte = estCorrecte;
        this.note = note;
        this.idQuestion = idQuestion;
        assert invariant();
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(final String descriptif) {
        this.descriptif = descriptif;
        assert invariant();
    }

    public boolean estCorrecte() {
        return estCorrecte;
    }

    public void setEstCorrecte(final boolean estCorrecte) {
        this.estCorrecte = estCorrecte;
        assert invariant();
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(final int idQuestion) {
        assert idQuestion > 0;
        this.idQuestion = idQuestion;
        assert invariant();
    }

    public Integer getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(final int idReponse) {
        assert idReponse > 0;
        this.idReponse = idReponse;
        assert invariant();
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(final String libelle) {
        assert libelle != null && !libelle.matches("^\\s*$");
        this.libelle = libelle;
        assert invariant();
    }

    public int getNote() {
        return note;
    }

    public void setNote(final int note) {
        this.note = note;
        assert invariant();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reponse other = (Reponse) obj;
        if ((this.libelle == null) ? (other.libelle != null) : !this.libelle.equals(other.libelle)) {
            return false;
        }
        if (this.idQuestion != other.idQuestion) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.libelle != null ? this.libelle.hashCode() : 0);
        hash = 29 * hash + this.idQuestion;
        return hash;
    }

    protected boolean invariant() {
        assert getLibelle() != null && !getLibelle().matches("^\\s*$");
        assert getIdQuestion() > 0;
        assert getIdReponse() > 0;
        if (estCorrecte()) {
            assert getNote() > 0 : "Une réponse correcte doit avoir une note positive";
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + libelle + "]";
    }
}
