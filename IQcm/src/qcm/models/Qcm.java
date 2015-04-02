package qcm.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import qcm.services.Helper;

public class Qcm {

    private Questionnaire questionnaire;
    private int idUser;
    private boolean estFini;
    private Map<Integer, List<Integer>> userReponses;
    private Iterator<Integer> iterateur;
    private int note;

    public Qcm(final int idQuestionnaire, final int idUser) {
        assert idQuestionnaire > 0;
        assert idUser > 0;
        this.idUser = idUser;
        userReponses = new HashMap<Integer, List<Integer>>();
        this.questionnaire = Helper.getQuestionnaireById(idQuestionnaire);
        List<Question> questions = questionnaire.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            userReponses.put(questions.get(i).getIdQuestion(), new ArrayList<Integer>());
        }
        SortedSet set = new TreeSet(userReponses.keySet());
        iterateur = set.iterator();
        this.estFini = false;
        assert invariant();
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getNote() throws SQLException {
        setNote();
        return note;
    }

    public boolean estFini() {
        return estFini;
    }

    public void setEstFini(boolean estFini) {
        this.estFini = estFini;
        assert invariant();
    }

    public Integer getQuestionSuivante() {
        Integer idQuestion = null;
        if (iterateur.hasNext()) {
            idQuestion = iterateur.next();
        }
        return idQuestion;
    }

    public Map<Integer, List<Integer>> getUserReponses() {
        return userReponses;
    }

    public void setUserReponses(Integer idQuestion, List<Integer> reponses) {
        assert idQuestion != null && idQuestion > 0;
        assert userReponses.containsKey(idQuestion);
        assert reponses != null;
        this.userReponses.put(idQuestion, reponses);
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
        final Qcm other = (Qcm) obj;
        if (this.questionnaire.getIdQuestionnaire() != other.getQuestionnaire().getIdQuestionnaire()) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.questionnaire.getIdQuestionnaire();
        hash = 53 * hash + this.idUser;
        return hash;
    }

    protected boolean invariant() {
        assert !estFini();
        assert getUserReponses() != null && getUserReponses().size() > 0;
        assert getQuestionnaire() != null;
        assert getQuestionSuivante() > 0;
        return true;
    }

    public void save() throws SQLException {
        assert invariant();
        new Helper().save(this);
        assert estFini();
    }

    public void reset() {
        assert !estFini();
        iterateur = userReponses.keySet().iterator();
        assert invariant();
    }

    private void setNote() throws SQLException {
        assert estFini;
        int score = 0;
        List<Integer> reponses = null;
        for (Integer idQuestion : userReponses.keySet()) {
            reponses = userReponses.get(idQuestion);
            for (Integer reponse : reponses) {
                score += Helper.getNoteByReponse(reponse);
            }
        }
        if (score < 0) {
            score = 0;
        }
        note = score;
    }
}
