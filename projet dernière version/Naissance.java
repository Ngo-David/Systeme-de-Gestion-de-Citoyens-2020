package fr.projet.proj;

import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Naissance implements Serializable {

	public Date dateNaissance;
	public int p�reNumId;
	public int m�reNumId;
	
	// constructeur Naissance avec en param�tre p�reNumId, m�reNumId et dateNaissance
	public Naissance(int p�reNumId, int m�reNumId, Date dateNaissance) {
		this.p�reNumId=p�reNumId;
		this.m�reNumId=m�reNumId;
		this.dateNaissance = dateNaissance;
	}
	
	public void p�reId() {
		Scanner clav = new Scanner(System.in);
		
		System.out.print("Entrez le num�ro d'identifiant du p�re biologique: ");
		p�reNumId = clav.nextInt();

		int erreur=1;	//par d�faut il y a une erreur
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == p�reNumId && Main.listecitoyen.get(a).sexe.equals("homme")) {	// on v�rifie si id existe dans base donn�es et si c'est un homme
				erreur = 0;	// l'id a �t� trouv� il n'y a plus d'erreur
			}
		}
		
		if (erreur == 1) {	// si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'existe pas dans la base de donn�es ou n'est pas un homme, retour au menu");
			Main.main(null);
		}
	}
	
	public void m�reId() {
		Scanner clav = new Scanner(System.in);
		
		System.out.print("Entrez le num�ro d'identifiant de la m�re biologique: ");
		m�reNumId = clav.nextInt();
		
		if (p�reNumId == m�reNumId) {
			System.out.println("Erreur c'est la m�me personne, retour au menu"); // car m�me id
			Main.main(null);
		}
		
		int erreur=1;
		for (int b = 0; b < Main.listecitoyen.size(); b++) {
			if (Main.listecitoyen.get(b).numId == m�reNumId && Main.listecitoyen.get(b).sexe.equals("femme")) {	// on v�rifie si id existe dans base donn�es et si c'est une femme
				erreur = 0;	// l'id a �t� trouv�, il n'y a plus d'erreur
			}
		}
		
			if (erreur == 1) {	// si il y a une erreur, on retourne au menu, sinon on continue
				System.out.println("Erreur, cette personne n'existe pas dans la base de donn�es ou n'est pas une femme, retour au menu");
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
		calendar.set(Calendar.MONTH, mois - 1); // month commence � 0

		System.out.print("Ann�e: ");
		int ann�e = clav.nextInt();
		calendar.set(Calendar.YEAR, ann�e);
		dateNaissance = calendar.getTime();
	}
	
	public void stockageNaissance() {
		Main.listenaissance.add(new Naissance(p�reNumId, m�reNumId, dateNaissance));
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
	  
	//relation, p�re m�re 
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