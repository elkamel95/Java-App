package javaapplication1;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//imprimer une facteur 
public class Impression implements Printable {
    	public ArrayList<String> lignes;
    	public ArrayList<String> Phrase;
String commandprix;
	public Impression(String path,ArrayList<String> Phrase,String commandprix){
            this. commandprix= commandprix;
		lignes = new ArrayList<String>();
                this.Phrase=Phrase;
		BufferedReader fluxEntree=null;
		String ligneLue;
		try {
			fluxEntree = new BufferedReader(new FileReader(path));

			ligneLue = fluxEntree.readLine();
			while(ligneLue!=null){
				lignes.add(ligneLue);
				ligneLue = fluxEntree.readLine();
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		}
		/* On définit une marge pour l'impression */
		int marge=30;

		/* On récupère les coordonnées des bords de la page */
		int x = (int)pageFormat.getImageableX();
		int y = (int)pageFormat.getImageableY();
		int w = (int)pageFormat.getImageableWidth();
		int h = (int)pageFormat.getImageableHeight();
int ligne=60;
		/* Dessin d'un cadre gris clair*/
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(x+10, y+10, w-20, h-20);

		/* On écrit une ligne de titre en rouge, en gras de taille 18 */
		graphics.setFont(new Font("Arial", Font.BOLD, 18));
		graphics.setColor(Color.RED);
		graphics.drawString("------------EPI Projet Gestion des produits------------ \n", x + marge, y+marge);

		/* On écrit une ligne en noir de taille 14 */
		graphics.setFont(new Font("Arial", Font.BOLD, 14));
		graphics.setColor(Color.black);
		graphics.drawString("FACTEUR", x+marge+50, y+marge+20);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Date date = new Date();
     
               graphics.drawString("Date :"+dateFormat.format(date).toString(), x+marge+50, y+marge+40);
	graphics.setFont(new Font("Arial", Font.PLAIN, 14));
		graphics.setColor(Color.BLACK);
                for( int i=0 ;i<Phrase.size();i++){
                    System.out.println(Phrase.get(i));
		graphics.drawString(Phrase.get(i), x+marge+50, y+marge+ligne);
                ligne=ligne+20;
                }
            
                	graphics.drawString(commandprix, x+marge+100, y+marge+ligne+50);

		return PAGE_EXISTS;
        }
}