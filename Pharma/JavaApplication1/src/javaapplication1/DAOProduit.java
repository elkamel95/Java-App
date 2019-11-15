package javaapplication1;




public class DAOProduit {
private int id ;
private String Titre;
private int prix ;
private int quantite ;
private String Date;
private String Datef;

private String ref ;
private String Icon;
private String QRCode;
DAOProduit(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getDatef() {
        return Datef;
    }

    public void setDatef(String Datef) {
        this.Datef = Datef;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String Icon) {
        this.Icon = Icon;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public DAOProduit(int id, String Titre, int prix, int quantite, String Date, String Datef, String ref, String Icon, String QRCode) {
        this.id = id;
        this.Titre = Titre;
        this.prix = prix;
        this.quantite = quantite;
        this.Date = Date;
        this.Datef = Datef;
        this.ref = ref;
        this.Icon = Icon;
        this.QRCode = QRCode;
    }
}