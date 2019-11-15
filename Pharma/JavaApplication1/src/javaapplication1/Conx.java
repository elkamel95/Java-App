package javaapplication1;


import java.io.File;
import qrcode.CrunchifyQRCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Conx  extends AbstractTableModel implements TableModelListener{
	private static Connection cnx;
	DefaultTableModel model = new DefaultTableModel(); 
	TableModelEvent mx ;
	private static Statement st;

	public Connection bD() throws ClassNotFoundException{
        	try {
			String dBurl = "jdbc:mysql://localhost:3306/tp4";
		    Class.forName( "com.mysql.jdbc.Driver" );
		    cnx = DriverManager.getConnection(dBurl,"root","");
		    st = cnx.createStatement();
			
			System.out.println("connection etabli");
			} catch (SQLException e) {
			e.printStackTrace();
			}
            return cnx;
        
        }
     public Users Login(String Login, String Motpasse) throws SQLException {
         Users U =new Users();
		 st=cnx.createStatement();
	 PreparedStatement p1=cnx.prepareStatement("SELECT * FROM `utilisateur` WHERE `Login`=? and `Password`=?");

p1.setString(1, Login);
p1.setString(2, Motpasse);

ResultSet res=p1.executeQuery();
while (res.next()){
    U.setLogin(res.getString("Login"));
    U.setMotpasse(res.getString("Password"));
U.setRole(res.getInt("Role"));

}
            return U;

		 
		
		
     }
          public Users Register(String Login, String Motpasse,int role) throws SQLException {
         Users U =new Users();
		 st=cnx.createStatement();
	 PreparedStatement p1=cnx.prepareStatement("INSERT INTO `utilisateur`(`id`, `Login`, `Password`, `Role`) VALUES (null,?,?,?)");

p1.setString(1, Login);
p1.setString(2, Motpasse);
p1.setInt(3, -1);

p1.executeUpdate();
            JOptionPane.showMessageDialog(null," Activation de compte en coure .......","Activation",JOptionPane.INFORMATION_MESSAGE);

          return U;

		 
		
		
     }
	public Conx() throws ClassNotFoundException {

model.addColumn("id"); 
model.addColumn("Nom"); 
model.addColumn("Prix"); 
model.addColumn("Quantité"); 
model.addColumn("Date de creation "); 
model.addColumn("Date de modification  "); 
model.addColumn("référence"); 
model.addColumn("Icon"); 
model.addColumn("QRcode"); 
	
	bD();
	
	
}
	public DefaultTableModel RechercheProduit(String  Titre) throws SQLException {
	 st=cnx.createStatement();
int row=0;
	 PreparedStatement p1=cnx.prepareStatement("select * from `tb_produits` where Titre =? ORDER BY `id` DESC ");
         p1.setString(1, Titre);
		ResultSet rep=	p1.executeQuery();
		ArrayList<DAOProduit>	l= new ArrayList<DAOProduit>();
		while(rep.next()) {
			DAOProduit DAOProduits =new DAOProduit();

			DAOProduits.setId(rep.getInt("id"));
			DAOProduits.setTitre(rep.getString("Titre"));
			DAOProduits.setPrix(rep.getInt("prix"));
			DAOProduits.setQuantite(rep.getInt("quantite"));
			DAOProduits.setDate(rep.getString("Date_creation"));
			DAOProduits.setRef(rep.getString("ref"));
           DAOProduits.setIcon(rep.getString("image"));
           DAOProduits.setDatef(rep.getString("Date_Update"));
                      int id= rep.getInt("id");

               CrunchifyQRCode cr = new CrunchifyQRCode(FournisseurWithIdProduit(id),id);

           DAOProduits.setQRCode(cr.mainQRCode());
l.add(DAOProduits);
		}
int i=0;

// Create a couple of columns 


// Append a row 
model.setRowCount(0);
for( Object s :l) {

	model.addRow(new Object[]{l.get(i).getId(),l.get(i).getTitre(),l.get(i).getPrix(),l.get(i).getQuantite(),l.get(i).getDate(),l.get(i).getDatef(),l.get(i).getRef(),new JLabel(new ImageIcon(l.get(i).getIcon())),new JButton (new  ImageIcon(l.get(i).getQRCode()))});
i++;
}
		return model;	
	}
        
                public 	DefaultTableModel RechercheFournisseur(String  Titre) throws SQLException {
		 st=cnx.createStatement();
                 int IdFournisseur = 0;
                  int id= 0;
                                                      System.out.println("dsqd");

                         fournisseur f=new fournisseur();
                         		ArrayList<fournisseur>	fournisseurList= new ArrayList<fournisseur>();
PreparedStatement p =cnx.prepareStatement("SELECT * FROM `fournisseur` WHERE `nom` =? ");
p.setString(1, Titre);
ResultSet RepFournisseur =p.executeQuery();
		while(RepFournisseur.next()) {
		IdFournisseur=	RepFournisseur.getInt("id_f");

		 }
                    System.out.println(IdFournisseur);
           PreparedStatement pp =cnx.prepareStatement("SELECT * FROM `produit_fournisseu` WHERE `id_f` =? ");
pp.setInt(1, IdFournisseur);
              ResultSet repFou=pp.executeQuery();
		while(repFou.next()) {

           id=      repFou.getInt("id");
                }
                                    System.out.println(id);

	 ResultSet repProduit=st.executeQuery("SELECT * FROM tb_produits   where id ="+id+" ");
		ArrayList<DAOProduit>	l= new ArrayList<DAOProduit>();
		while(repProduit.next()) {
			DAOProduit DAOProduits =new DAOProduit();
			DAOProduits.setId(repProduit.getInt("id"));
			DAOProduits.setTitre(repProduit.getString("Titre"));
			DAOProduits.setPrix(repProduit.getInt("prix"));
			DAOProduits.setQuantite(repProduit.getInt("quantite"));
			DAOProduits.setDate(repProduit.getString("Date_creation"));
			DAOProduits.setRef(repProduit.getString("ref"));
           DAOProduits.setIcon(repProduit.getString("image"));
           DAOProduits.setDatef(repProduit.getString("Date_Update"));

l.add(DAOProduits);
		 }
                
                int i=0;

// Create a couple of columns 


// Append a row 
model.setRowCount(0);
for( Object s :l) {
	model.addRow(new Object[]{l.get(i).getId(),l.get(i).getTitre(),l.get(i).getPrix(),l.get(i).getQuantite(),l.get(i).getDate(),l.get(i).getDatef(),l.get(i).getRef(),new JLabel(new ImageIcon(l.get(i).getIcon())),new JButton (new  ImageIcon(l.get(i).getQRCode()))});

i++;
}
		return model;
        
        }
                public ArrayList<Users> getUsers () throws SQLException{
   
      		ArrayList<Users>	l= new ArrayList<Users>();

		 st=cnx.createStatement();
	 PreparedStatement p1=cnx.prepareStatement("SELECT * FROM `utilisateur`");


ResultSet res=p1.executeQuery();
while (res.next()){
       Users U =new Users();
    U.setLogin(res.getString("Login"));
    U.setMotpasse(res.getString("Password"));
        U.setId(res.getInt("id"));

U.setRole(res.getInt("Role"));
l.add(U);

}
            return l;

                
                }
                
         public void SetRole (int id) throws SQLException{
         
         PreparedStatement p =cnx.prepareStatement("UPDATE `utilisateur` SET `Role` = '1' WHERE `utilisateur`.`id` = ? ");
p.setInt(1, id);
         p.executeUpdate();
         }
        public 	fournisseur FournisseurWithIdProduit(int  id_p) throws SQLException {
		 st=cnx.createStatement();
                 int IdFournisseur = 0;
                  int id= 0;
                         fournisseur f=new fournisseur();

              ResultSet repFou=st.executeQuery("SELECT * FROM `produit_fournisseu` WHERE `id` ="+id_p+" ");
		while(repFou.next()) {

           id=      repFou.getInt("id_f");
                }
PreparedStatement p =cnx.prepareStatement("SELECT * FROM `fournisseur` WHERE `id_f` =? ");
p.setInt(1, id);
ResultSet RepFournisseur =p.executeQuery();
		while(RepFournisseur.next()) {
		IdFournisseur=	RepFournisseur.getInt("id_f");
String email =RepFournisseur.getString("email");
String nom =RepFournisseur.getString("nom");
String tel =RepFournisseur.getString("Tel");
f.setEmail(email);
f.setNom(nom);
f.setTel(tel);
f.setId(IdFournisseur);
		 }

                
                int i=0;


		return f;
        
        }
        
public static String selectRemoveImage(int id) throws SQLException {
	 st=cnx.createStatement();
String image="";	 

PreparedStatement p1=cnx.prepareStatement("select * from `tb_produits` where id =?");
         p1.setInt(1, id);
		ResultSet rep=	p1.executeQuery();
		ArrayList<DAOProduit>	l= new ArrayList<DAOProduit>();
		while(rep.next()) {

	
  image=   rep.getString("image");
      

	
	
	
	
}

		return image;

}
public DefaultTableModel  selectProduit() throws SQLException {
	 st=cnx.createStatement();
int row=0;
	 PreparedStatement p1=cnx.prepareStatement("select * from `tb_produits` ORDER BY `id` DESC");
		ResultSet rep=	p1.executeQuery();
		ArrayList<DAOProduit>	l= new ArrayList<DAOProduit>();
		while(rep.next()) {
			DAOProduit DAOProduits =new DAOProduit();

			DAOProduits.setId(rep.getInt("id"));
			DAOProduits.setTitre(rep.getString("Titre"));
			DAOProduits.setPrix(rep.getInt("prix"));
			DAOProduits.setQuantite(rep.getInt("quantite"));
			DAOProduits.setDate(rep.getString("Date_creation"));
			DAOProduits.setRef(rep.getString("ref"));
           DAOProduits.setIcon(rep.getString("image"));
           DAOProduits.setDatef(rep.getString("Date_Update"));
           int id= rep.getInt("id");
    CrunchifyQRCode cr = new CrunchifyQRCode(  FournisseurWithIdProduit(id),id);
               DAOProduits.setQRCode(cr.mainQRCode());

l.add(DAOProduits);
		}
int i=0;

// Create a couple of columns 


// Append a row 
model.setRowCount(0);
for( Object s :l) {

	model.addRow(new Object[]{l.get(i).getId(),l.get(i).getTitre(),l.get(i).getPrix(),l.get(i).getQuantite(),l.get(i).getDate(),l.get(i).getDatef(),l.get(i).getRef(),new JLabel(new ImageIcon(l.get(i).getIcon())),new JButton (new  ImageIcon(l.get(i).getQRCode()))});
i++;
}
		return model;
	
	
	
	
}
public ArrayList<DAOProduit>  GetProduit() throws SQLException {
	 st=cnx.createStatement();
int row=0;
	 PreparedStatement p1=cnx.prepareStatement("select * from `tb_produits` ORDER BY `id` DESC");
		ResultSet rep=	p1.executeQuery();
		ArrayList<DAOProduit>	l= new ArrayList<DAOProduit>();
		while(rep.next()) {
			DAOProduit DAOProduits =new DAOProduit();

			DAOProduits.setId(rep.getInt("id"));
			DAOProduits.setTitre(rep.getString("Titre"));
			DAOProduits.setPrix(rep.getInt("prix"));
			DAOProduits.setQuantite(rep.getInt("quantite"));
			DAOProduits.setDate(rep.getString("Date_creation"));
			DAOProduits.setRef(rep.getString("ref"));
           DAOProduits.setIcon(rep.getString("image"));
           DAOProduits.setDatef(rep.getString("Date_Update"));
           int id= rep.getInt("id");
    CrunchifyQRCode cr = new CrunchifyQRCode( FournisseurWithIdProduit(id),id);
               DAOProduits.setQRCode(cr.mainQRCode());

l.add(DAOProduits);
		}

		return l;
	
	
	
	
}
public static String  AjouterP(String Titre,int prix,int quantite,String id_f,String image,String fournisseur ){
try{
//STEP 4: Execute a query
String query="INSERT INTO `tb_produits` (`id`, `Titre`, `prix`, `quantite`, `Date_creation`, `ref`, `image`, `Date_Update`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);";
PreparedStatement p1 = cnx.prepareStatement(query);
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Date date = new Date();
	System.out.println(image); 

p1.setString(1,Titre);
p1.setInt(2, prix);
p1.setInt(3, quantite);
p1.setString(4, dateFormat.format(date).toString());
p1.setString(5, id_f);
p1.setString(6, image);
p1.setString(7,  dateFormat.format(date).toString());

p1.executeUpdate();
PreparedStatement p =cnx.prepareStatement("SELECT * FROM `fournisseur` WHERE `nom` =? ");
p.setString(1, fournisseur);
ResultSet RepFournisseur =p.executeQuery();
PreparedStatement p1Produit=cnx.prepareStatement("select * from `tb_produits` where Titre =?");
         p1Produit.setString(1, Titre);
		ResultSet rep=	p1Produit.executeQuery();
		ArrayList<DAOProduit>	l= new ArrayList<DAOProduit>();
                int idProduit =0;
		while(rep.next()) {
idProduit= rep.getInt("id");
}
int IdFournisseur = 0;
		while(RepFournisseur.next()) {
		IdFournisseur=	RepFournisseur.getInt("id_f");

		 }
String query2="INSERT INTO `produit_fournisseu` (`idfp`, `id_f`, `id`) VALUES (NULL, ?,?)";
PreparedStatement p2 = cnx.prepareStatement(query2);
p2.setInt(1,IdFournisseur);
p2.setInt(2, idProduit);
p2.executeUpdate();

       cnx.commit();
}catch(SQLException e){
}
return "votre commande à ajouter avec succes ";
}
public  void   updateProduit(int id ,String Titre,int prix,int quantite,String Date,String id_f ) throws ClassNotFoundException{
try{

String query="UPDATE `tb_produits` SET `Titre`=?,`prix`=?,`quantite`=?,`Date_Update`=?,`ref`=? WHERE id =?";
PreparedStatement p = cnx.prepareStatement(query);
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Date date = new Date();
p.setString(1,Titre);
p.setInt(2, prix);
p.setInt(3, quantite);
p.setString(4, dateFormat.format(date).toString());
p.setString(5, id_f);
p.setInt(6,id);
p.executeUpdate();
    System.out.print(prix);
    System.out.print(quantite);

}catch(SQLException e){
    System.out.println(e);
}
/**
    try {
            jTable1.setModel(this.selectProduit());

        } catch (SQLException ex) {
        }
        jTable1.repaint();*/
}

public static String  delete(int id ){
try{
//STEP 4: Execute a query
String query="DELETE FROM `tb_produits` WHERE `tb_produits`.`id` ="+id +"" ;
st=cnx.createStatement();
String imgRemove=selectRemoveImage(id);
DeleteFiles(imgRemove);
st.executeUpdate(query);
}catch(SQLException e){
}
return "produit supprime";
}
public static  void DeleteFiles(String path) {
    File file = new File(path);
    System.out.println("Called deleteFiles");

        file.delete();
    
}
public DefaultTableModel getModel () {

	return model;
	
	
}
@Override
public int getColumnCount() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public int getRowCount() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public Object getValueAt(int arg0, int arg1) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void tableChanged(TableModelEvent e) {
    fireTableChanged(mx);
	
}

public ArrayList<Object> getCommande() throws SQLException{
    
    
     	 st=cnx.createStatement();
	 PreparedStatement p1=cnx.prepareStatement("SELECT `id`, `prixTotale`, `dateCreation`, `idutilisateur` FROM `commande` ");
		ResultSet rep=	p1.executeQuery();
		ArrayList<Object>l= new ArrayList<Object>();
		while(rep.next()) {
		l.add((int)rep.getInt("id"));
               l.add((String)rep.getString("prixTotale"));
		l.add((String)rep.getString("dateCreation"));

		}

		return l;



}
public ArrayList<Object> getCommande(int idCaissier){
            return null;




}
public ArrayList<Object> getCommande(String Date) throws SQLException{
    	 st=cnx.createStatement();
	 PreparedStatement p1=cnx.prepareStatement("SELECT `id`, `prixTotale`, `dateCreation`, `idutilisateur`,dat FROM `commande`where dat=? ");
	p1.setString(1, Date);
         ResultSet rep=	p1.executeQuery();
		ArrayList<Object>l= new ArrayList<Object>();
		while(rep.next()) {
		l.add((int)rep.getInt("id"));
               l.add((String)rep.getString("prixTotale"));
		l.add((String)rep.getString("dateCreation"));
                l.add((String)rep.getString("dat"));

		}

		return l;




}
public  void   setPrixCommande(int prix ) throws ClassNotFoundException{
try{

String query="UPDATE `commande` SET `prixTotale`=? WHERE `dat`=?";
PreparedStatement p = cnx.prepareStatement(query);
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	Date date = new Date();
p.setInt(1,prix);

p.setString(2, dateFormat.format(date).toString());


}catch(SQLException e){
    System.out.println(e);
}
/**
    try {
            jTable1.setModel(this.selectProduit());

        } catch (SQLException ex) {
        }
        jTable1.repaint();*/
}
public  void   setLogin(String nom, String MotPasse) throws ClassNotFoundException{
try{

String query="UPDATE `utilisateur` SET `Password` = ?,`Login` = ? WHERE `utilisateur`.`id` = 3";
PreparedStatement p = cnx.prepareStatement(query);

p.setString(1,nom);

p.setString(2,MotPasse);

p.executeUpdate();
}catch(SQLException e){
    System.out.println(e);
}
/**
    try {
            jTable1.setModel(this.selectProduit());

        } catch (SQLException ex) {
        }
        jTable1.repaint();*/
}
public static String  setCommande( String prixTotal, int idutilisateur  ){
try{
//STEP 4: Execute a query
String query="INSERT INTO `commande` (`id`, `prixTotale`, `dateCreation`, `idutilisateur`,`dat`) VALUES (NULL, ?, ?, ?,?);";
PreparedStatement p1 = cnx.prepareStatement(query);
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Date date = new Date();
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
	Date date2 = new Date();
p1.setString(1,prixTotal);

p1.setString(2, dateFormat.format(date).toString());
p1.setInt(3,3);
p1.setString(4, dateFormat2.format(date2).toString());


p1.executeUpdate();
}catch(SQLException e){
}
return "votre commande à ajouter avec succes ";
}
}

