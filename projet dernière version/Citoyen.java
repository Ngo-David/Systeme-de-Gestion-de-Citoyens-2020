package fr.projet.proj;

import java.io.Serializable;
import java.text.SimpleDateFormat;	//https://www.jmdoudoux.fr/java/dej/chap-utilisation_dates.htm
import java.util.*;

public class Citoyen implements Serializable {

	public int numId;
	public String nom;
	public String prenom;
	public String sexe;
	public Date dateNaissance;
	public String etatCivil;

	// constructeur Citoyen avec en param�tre numId, sexe, nom, prenom, dateNaissance, etatCivil
	public Citoyen(int numId, String sexe, String nom, String prenom, Date dateNaissance, String etatCivil) {
		this.numId = numId;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.etatCivil = etatCivil;
	}

	public void valNumId() {
		Scanner clav = new Scanner(System.in);

		System.out.print("Entrez le num�ro d'identifiant de la personne: ");
		numId = clav.nextInt();

		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == numId) { // on recherche si l'id n'a pas d�j� �t� attribu�
				System.out.println("Erreur, ce num�ro d'identifiant existe d�j�, retour au menu");
				Main.main(null);
			}
		}
	}

	public void valSexe() {
		Scanner clav = new Scanner(System.in);
		System.out.print("Entrez le sexe de la personne, homme ou femme: ");
		sexe = clav.nextLine();

		if (!sexe.equals("homme") && !sexe.equals("femme")) {
			System.out.println("Erreur, il faut que ce soit un homme ou une femme, retour au menu");
			Main.main(null);
		}
	}

	public void valNom() {
		Scanner clav = new Scanner(System.in);

		System.out.print("Entrez le Nom de naissance de la personne: ");
		nom = clav.nextLine();

		if (nom.equals("")) {
			System.out.println("Erreur, aucun Nom d�tect�, retour au menu");
			Main.main(null);
		}
	}

	public void valPrenom() {
		Scanner clav = new Scanner(System.in);
		System.out.print("Entrez le Pr�nom de la personne: ");
		prenom = clav.nextLine();

		if (prenom.equals("")) {
			System.out.println("Erreur, aucun Pr�nom d�tect�, retour au menu");
			Main.main(null);
		}
	}

	public void valDate() {
		Scanner clav = new Scanner(System.in);
		// calendar permet de r�cup�rer les informations sur la date et l'heure, l'heure pourra �tre enlev�e plus tard
		Calendar calendar = Calendar.getInstance();	
		System.out.println("Entrez la date de naissance de la personne au format JJ-MM-AAAA,");

		System.out.print("Jour: ");
		int jour = clav.nextInt();

		calendar.set(Calendar.DATE, jour);

		System.out.print("Mois: ");
		int mois = clav.nextInt();
		calendar.set(Calendar.MONTH, mois - 1); // month commence � 0

		System.out.print("Ann�e: ");
		int ann�e = clav.nextInt();
		calendar.set(Calendar.YEAR, ann�e);

		dateNaissance = calendar.getTime();	// calendar a aussi besoin de l'heure d'o� getTime mais on pourra l'enlever plus tard pour n'afficher que la date
	}

	public void valEtatpard�faut() {
		etatCivil = "c�libataire";	// si la personne est encore en vie alors son �tat civil est "c�libataire" par d�faut 
	}

	public void stockageCitoyen() {
		// instanciation d'un objet: new Citoyen(numId, sexe, nom, prenom) et stockage de l'objet: listecitoyen.add
		Main.listecitoyen.add(new Citoyen(numId, sexe, nom, prenom, dateNaissance, etatCivil));
	}

	public String toString() { // utile pour le choix 5, pour mettre les informations en String pour pouvoir afficher du texte
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd-MM-yyyy"); // on change le format car on veut juste la date et pas l'heure
		return String.format("Num�ro identifiant: %d, sexe: %s, Nom: %s, Pr�nom: %s, Date de naissance: %s, Etat civil: %s", numId, sexe, nom, prenom, formater.format(dateNaissance), etatCivil);
	}

	public void stockageCitoyenNaissance() {
		// pour �viter de devoir redemander la date de naissance du nouveau n�, on va plut�t la r�cup�rer dans listenaissance
		int date = Main.listenaissance.size();
		// on va chercher dans le dernier index de listenaissance et on le ram�ne ici pour l'enregistrer dans listecitoyen
		dateNaissance = Main.listenaissance.get(date - 1).dateNaissance;
		Main.listecitoyen.add(new Citoyen(numId, sexe, nom, prenom, dateNaissance, etatCivil));
	}
	
	//g�re, gestion Mairie Citoyen
	private Mairie mairie;

	public Citoyen() {
		listenaissance = new ArrayList<Naissance>();
		listemariage = new ArrayList<Mariage>();
	}

	public Mairie getMairie() {
		return (mairie);
	}

	public void setMairie(Mairie a) {
		this.mairie = a;
	}

	public void addMairie(Mairie a) {
		if (a != null) {
			if (!a.getArray().contains(this)) {
				if (mairie != null)
					mairie.remove(this);
				this.setMairie(a);
				mairie.getArray().add(this);
			}
		}
	}

	//concerne, association Deces Citoyen
	private Deces deces;

	public void addDeces(Deces a) {
		if (a != null) {
			if (a.getCitoyen() != null) { // si a est d�j� connect� � un autre Citoyen
				a.getCitoyen().setDeces(null); // cet autre Citoyen doit se d�connecter
			}
			this.setDeces(a);
			a.setCitoyen(this);
		}
	}

	public void setDeces(Deces a) {
		this.deces = a;
	}

	public Deces getDeces() {
		return (deces);
	}

	// branche pere mere relation
	private ArrayList<Naissance> listenaissance;

	public ArrayList<Naissance> getArray5() {
		return (listenaissance);
	}

	public void remove(Naissance b) {
		listenaissance.remove(b);
	}

	public void addNaissance(Naissance b) {
		if (!listenaissance.contains(b)) {
			if (b.getCitoyen() != null)
				b.getCitoyen().remove(b);
			b.setCitoyen(this);
			listenaissance.add(b);
		}
	}

	//epoux1 epoux2, association Citoyen Mariage
	private ArrayList<Mariage> listemariage;

	public ArrayList<Mariage> getArray6() {
		return (listemariage);
	}

	public void remove(Mariage b) {
		listemariage.remove(b);
	}

	public void addMariage(Mariage b) {
		if (!listemariage.contains(b)) {
			if (b.getCitoyen() != null)
				b.getCitoyen().remove(b);
			b.setCitoyen(this);
			listemariage.add(b);
		}
	}

}