/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author 2
 */
public class Vendeur extends javax.swing.JFrame {
       public  Conx c = null;
        int prixTotal=0;
         int Case=0;
        ArrayList<PQT> listPQT=new  ArrayList<PQT> ();
       javax.swing.DefaultListModel defaultListModel=new javax.swing.DefaultListModel();

Boolean repExiste =false;


    /**
     * Creates new form NewJFrame
     */
    public Vendeur() throws SQLException {
          int n = 0;
        try {
            c = new Conx();
                n=    c.selectProduit().getRowCount(); 



        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Vendeur.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    initComponents();
      this.setLocationRelativeTo(null);

quantite.setLayout(new GridLayout(n,1));
    if(n<=5){
                    jPanel3.setLayout(new GridLayout(1,5));
    }else{
            jPanel3.setLayout(new GridLayout(n/5+1,5));

    }

    for(int i=0; i<n ;i++){
          JButton b=new JButton();
    b.setIcon(new javax.swing.ImageIcon( c.GetProduit().get(i).getIcon())); // NOI18N

          b.setOpaque(true);
    b.setPreferredSize(new Dimension(30, 30));
      jPanel3.add(b);  
          // as @Hovercraft Full Of Eels suggested
     quantite.repaint();
       jPanel3.revalidate();    // as @Hovercraft Full Of Eels suggested
     jPanel3.repaint();
           int     prix = c.GetProduit().get(i).getPrix();
           b.setText("prix commande :"+Integer.toString(prix)+"DT");
               String Titre = c.GetProduit().get(i).getTitre();
               int getQuantite = c.GetProduit().get(i).getQuantite();
   PQT pqt =new PQT();
   pqt.setPrix(prix);
   pqt.setQt(getQuantite);
   
    pqt.setTitre(Titre);          
    listPQT.add(pqt);
               b.addActionListener(new ActionListener() 
     
     {
         int prixprod =0;
         int s=0;
  JPanel     p=  new JPanel();
                  // JTextField qt =new JTextField(5);
   javax.swing.DefaultListModel ModeleQt=new javax.swing.DefaultListModel();

              @Override
              public void actionPerformed(ActionEvent ae) {
//                  p.add(qt);
                  if(getQuantite>s){
                  if(defaultListModel.getSize()!=0){
                  for(int y=0;y<defaultListModel.getSize();y++){
           if(defaultListModel.getElementAt(y).equals(Titre)) {
               repExiste=true;
               break;
           }
         
              
                  }
              }
                
                  
                  if(!repExiste){
                      
                          defaultListModel.addElement(Titre+"                  "+prix+"DT");
                  commande.setModel(defaultListModel); 
                  s++;
                         int sommeprod=prix;
                      prixTotal+=sommeprod;
//                qt.setText(Integer.toString(s));
     somme.setText("prix commande :"+Integer.toString(prixTotal)+"DT");

                   repExiste=true;
       quantite.add(p);
          quantite.revalidate(); 
                  }else{
                      
                      
                   
                     
                      s=s+1;     
//                      qt.setText(Integer.toString(s));
           java.awt.EventQueue.invokeLater(new Runnable() {
                                  @Override
                                  public void run() {
                                                              calcule();

                                  }
                                       public synchronized int calcule(){
                         
                      int sommeprod=prix;
                      prixTotal+=sommeprod;
     somme.setText("prix commande :"+Integer.toString(prixTotal)+"DT");
                        return prixTotal;
                        }
                              });
                 
                  }
                                                  repExiste=false;

              }
              else{
     
javax.swing.JOptionPane.showMessageDialog
(null,"<html>quantite epuisé  <br> nom produit :"+Titre+"<br>"
        + " quantite:"+getQuantite+"</html>","",JOptionPane.ERROR_MESSAGE);

     }
              }

          });

     {
    
    
    }
    }
 }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        somme = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        commande = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        quantite = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jButton2.setText("Confirmer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        somme.setForeground(new java.awt.Color(204, 0, 51));
        somme.setText("prix commande :");

        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setText("prix totale");

        jButton1.setText("supprimer produit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("supprimer tous les produits  ");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(somme, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(42, 42, 42)
                .addComponent(jButton3)
                .addGap(34, 34, 34)
                .addComponent(jButton2)
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(somme)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(commande);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("Caisse");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        quantite.setLayout(new java.awt.GridLayout(1, 1, 0, 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(251, 251, 251)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(quantite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane2.setViewportView(jPanel2);

        getContentPane().add(jScrollPane2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     // TODO add your handling code here:
        if(commande.getSelectedIndex()!=-1){
    

defaultListModel.remove(commande.getSelectedIndex())  ;


        
        
        
        
        }else{
               javax.swing.JOptionPane.showMessageDialog
(null,"Sélectionner le produit qui le  doit supprimer","Erreur de sélection",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        defaultListModel.removeAllElements();
        //commande.removeAll();""
        prixTotal=0;
     somme.setText("prix commande :"+Integer.toString(prixTotal)+"DT");
        commande.repaint();
        commande.revalidate();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        PrinterJob job = PrinterJob.getPrinterJob();
               ArrayList<String> l=new ArrayList<String>();
               for(int i=0;i<defaultListModel.size();i++){
               l.add(defaultListModel.getElementAt(i).toString());
               
               }
		job.setPrintable(new Impression("D:\\fichier.txt",l,somme.getText()));

		boolean doPrint = job.printDialog();
		if(doPrint) {
			try {
				job.print();
			}
			catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
	
        System.out.print(  c.setCommande(somme.getText().toString(),3));  
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vendeur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendeur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendeur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendeur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Vendeur().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Vendeur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> commande;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel quantite;
    private javax.swing.JLabel somme;
    // End of variables declaration//GEN-END:variables

  
}
