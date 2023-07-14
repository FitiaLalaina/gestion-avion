package statistique;

import java.text.DecimalFormat;

public class Ratio {
    
    double valeur = 0;
    double produit = 0;

    public void setValeur(double valeur) throws Exception {
        if (valeur < 0) throw new Exception("Valeur doit etre positif");
        this.valeur = valeur;
    }
    
    public double getValeur() {
        return valeur;
    }
    
    public void setProduit(double produit) throws Exception {
        if (produit < 0) throw new Exception("Produit doit etre positif");
        this.produit = produit;
    }

    public double getProduit() {
        return produit;
    }

    public String getProduitRound() {
        return format(this.getProduit());
    }
    
    public String getValeurRound() {
        return format(this.getValeur());
    }
    
    public String getRatioRound() {
        return format(this.getRatio());
    }

    public double getRatio() {
        return this.getProduit() / this.getValeur();
    }

    public Ratio(double valeur, double produit) throws Exception {
        this.setValeur(valeur);
        this.setProduit(produit);
    }

    public static String format(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

}
