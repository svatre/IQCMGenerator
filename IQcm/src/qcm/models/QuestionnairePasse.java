package qcm.models;

import java.sql.Date;

/**
 * Classe qui définit un questionnaire passé par un utilisateur (et finit)
 * pour ensuite être enregistrer en base de données
 */
public class QuestionnairePasse {

    private int idQuestionnaire;
    private String libelleQuestionnaire;
    private int idUser;
    private int note;
    private Date date;
    private Integer temps;

    public QuestionnairePasse(int idQuestionnaire, String libelleQuestionnaire, int idUser, int note, Date date, Integer temps) {
        this.idQuestionnaire = idQuestionnaire;
        this.libelleQuestionnaire = libelleQuestionnaire;
        this.idUser = idUser;
        this.note = note;
        this.date = date;
        this.temps = temps;
    }

    public QuestionnairePasse(int idQuestionnaire, int idUser) {
        this.idQuestionnaire = idQuestionnaire;
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        assert date != null;
        this.date = date;
        assert invariant();
    }

    public int getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public void setIdQuestionnaire(int idQuestionnaire) {
        assert idQuestionnaire > 0;
        this.idQuestionnaire = idQuestionnaire;
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

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
        assert invariant();
    }

    public String getLibelleQuestionnaire() {
        return libelleQuestionnaire;
    }

    public void setLibelleQuestionnaire(String libelleQuestionnaire) {
        this.libelleQuestionnaire = libelleQuestionnaire;
    }

    public Integer getTemps() {
        return temps;
    }

    public void setLimiteTemps(Integer temps) {
        this.temps = temps;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QuestionnairePasse other = (QuestionnairePasse) obj;
        if (this.idQuestionnaire != other.idQuestionnaire) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idQuestionnaire;
        hash = 29 * hash + this.idUser;
        return hash;
    }

    protected boolean invariant() {
        assert getIdUser() > 0;
        assert getIdQuestionnaire() > 0;
        return true;
    }

    @Override
    public String toString() {
        return "[" + getIdQuestionnaire() + ":" + getIdUser() + ":" + getNote() + "]";
    }
}
