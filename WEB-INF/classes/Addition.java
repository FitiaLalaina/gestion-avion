package statistique;

import connection.BddObject;
import connection.annotation.ForeignKey;
import connection.annotation.ColumnName;
import parcelle.Engrai;

public class Addition extends BddObject {
    
    @ColumnName("id_addition")
    String idAddition;
    @ForeignKey
    Engrai engrai;
    Double min;
    Double max;
    Double valeur;

    public void setIdAddition(String idAddition) {
        this.idAddition = idAddition;
    }

    public String getIdAddition() {
        return idAddition;
    }

    public void setEngrai(Engrai engrai) throws Exception {
        if (engrai == null) throw new Exception("Engrai est null");
        this.engrai = engrai;
    }

    public Engrai getEngrai() {
        return engrai;
    }

    public void setMin(Double min) throws Exception {
        if (min < 0) throw new Exception("Min doit etre positif");
        this.min = min;
    }
    
    public void setMax(Double max) throws Exception {
        if (max < 0) throw new Exception("Max doit etre positif");
        this.max = max;
    }
    
    public void setValeur(Double valeur) throws Exception {
        this.valeur = valeur;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public Double getValeur() {
        return valeur;
    }

    public Addition() throws Exception {
        super();
        this.setTable("addition");
        this.setConnection("PostgreSQL");
        this.setPrimaryKeyName("idAddition");
    }

}