package statistique;

import parcelle.Engrai;
import statistique.Ratio;

public class Composition {
    
    Engrai engrai;
    double pourcentage;
    Composition[] compositions = new Composition[0];

    public void setEngrai(Engrai engrai) throws Exception {
        if (engrai ==  null) throw new Exception("Engrai est null");
        this.engrai = engrai;
    }

    public Engrai getEngrai() {
        return engrai;
    }
    
    public void setPourcentage(double pourcentage) throws Exception {
        if (pourcentage < 0) throw new Exception("Pourcentage doit etre positif");
        this.pourcentage = pourcentage;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setCompositions(Composition[] compositions) {
        this.compositions = compositions;
    }

    public Composition[] getCompositions() {
        return compositions;
    }

    public String toString() {
        String compo = "";
        for (Composition composition : this.getCompositions())
            compo += composition.getEngrai().getNom() + ": " + Ratio.format(composition.getPourcentage() * 100) + "% ";
        return compo;
    }

    public Composition() {

    }

    public Composition(Engrai engrai, double pourcentage) throws Exception {
        this.setEngrai(engrai);
        this.setPourcentage(pourcentage);
    }

}
