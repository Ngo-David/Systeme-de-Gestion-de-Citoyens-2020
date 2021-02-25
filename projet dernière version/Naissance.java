package fr.projet.proj;

import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Naissance implements Serializable {

	public Date dateNaissance;
	public int pèreNumId;
	public int mèreNumId;
	
	// constructeur Naissance avec en paramètre pèreNumId, mèreNumId et dateNaissance
	public Naissance(int pèreNumId, int mèreNumId, Date dateNaissance) {
		this.pèreNumId=pèreNumId;
		this.mèreNumId=mèreNumId;
		this.dateNaissance = dateNaissance;
	}
	
	public void pèreId() {
		Scanner clav = new Scanner(System.in);
		
		System.out.print("Entrez le numéro d'identifiant du père biologique: ");
		pèreNumId = clav.nextInt();

		int erreur=1;	//par défaut il y a une erreur
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == pèreNumId && Main.listecitoyen.get(a).sexe.equals("homme")) {	// on vérifie si id existe dans base données et si c'est un homme
				erreur = 0;	// l'id a été trouvé il n'y a plus d'erreur
			}
		}
		
		if (erreur == 1) {	// si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'existe pas dans la base de données ou n'est pas un homme, retour au menu");
			Main.main(null);
		}
	}
	
	public void mèreId() {
		Scanner clav = new Scanner(System.in);
		
		System.out.print("Entrez le numéro d'identifiant de la mère biologique: ");
		mèreNumId = clav.nextInt();
		
		if (pèreNumId == mèreNumId) {
			System.out.println("Erreur c'est la même personne, retour au menu"); // car même id
			Main.main(null);
		}
		
		int erreur=1;
		for (int b = 0; b < Main.listecitoyen.size(); b++) {
			if (Main.listecitoyen.get(b).numId == mèreNumId && Main.listecitoyen.get(b).sexe.equals("femme")) {	// on vérifie si id existe dans base données et si c'est une femme
				erreur = 0;	// l'id a été trouvé, il n'y a plus d'erreur
			}
		}
		
			if (erreur == 1) {	// si il y a une erreur, on retourne au menu, sinon on continue
				System.out.println("Erreur, cette personne n'existe pas dans la base de données ou n'est pas une femme, retour au menu");
				Main.main(null);
			}
	}
	
	public void dateNaissance() {
		Scanner clav = new Scanner(System.in);
		Calendar calendar = Calendar.getInstance();
		
		System.out.println("entrez la date de naissance de la personne au format JJ-MM-AAAA,");
		System.out.print("Jour: ");
		int jour = clav.nextInt();
		calendar.set(Calendar.DATE, jour);

		System.out.print("Mois: ");
		int mois = clav.nextInt();
		calendar.set(Calendar.MONTH, mois - 1); // month commence à 0

		System.out.print("Année: ");
		int année = clav.nextInt();
		calendar.set(Calendar.YEAR, année);
		dateNaissance = calendar.getTime();
	}
	
	public void stockageNaissance() {
		Main.listenaissance.add(new Naissance(pèreNumId, mèreNumId, dateNaissance));
	}
	
	//enregistre, association Mairie Naissance
	  private  Mairie mairie;
	  public Naissance() {}
	  public Mairie getMairie() { return (mairie); }
	  public void setMairie(Mairie a){ this.mairie=a; }
	  public void addMairie(Mairie a){ 
	    if( a != null ) { 
	      if( !a.getArray4().contains(this)) { 
	        if (mairie != null) mairie.remove(this);
	        this.setMairie(a);
	        mairie.getArray4().add(this);
	      }
	    }
	  }
	  
	//relation, père mère 
	  private  Citoyen citoyen;
	  public Citoyen getCitoyen() { return (citoyen); }
	  public void setCitoyen(Citoyen a){ this.citoyen=a; }
	  public void addCitoyen(Citoyen a){ 
	    if( a != null ) { 
	      if( !a.getArray5().contains(this)) { 
	        if (citoyen != null) citoyen.remove(this);
	        this.setCitoyen(a);
	        citoyen.getArray5().add(this);
	      }
	    }
	  }
	  
}