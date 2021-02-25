package fr.projet.proj;

import java.util.*;
import java.io.Serializable;

public class Divorce implements Serializable {

	private Date dateDivorce;
	public int divorc�1numId;
	public int divorc�2numId;

	// constructeur Divorce avec en param�tre divorc�1numId, divorc�2numId et dateDivorce
	public Divorce(int divorc�1numId, int divorc�2numId, Date dateDivorce) {
		this.divorc�1numId = divorc�1numId;
		this.divorc�2numId = divorc�2numId;
		this.dateDivorce = dateDivorce;
	}

	public void divorc�1Id() {
		Scanner clav = new Scanner(System.in);
		System.out.print("Entrez id epoux 1 � divorcer: ");
		divorc�1numId = clav.nextInt();

		int erreur = 1; // par d�faut il y a une erreur
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == divorc�1numId) { // on v�rifie si id existe dans la base de donn�es
				erreur = 0; // l'id a �t� trouv� il n'y a plus d'erreur
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'existe pas dans la base de donn�es, retour au menu");
			Main.main(null);
		}

		erreur = 1;
		for (int b = 0; b < Main.listemariage.size(); b++) { // recherche parmi id epoux1 et epoux2 si la personne est mari�e
			if (Main.listemariage.get(b).epoux1numId == divorc�1numId || Main.listemariage.get(b).epoux2numId == divorc�1numId) {
				erreur = 0;
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'est pas mari�e, retour au menu");
			Main.main(null);
		}

		erreur = 0; // cette fois on inverse, si la personne se trouve dans la listedivorce alors il y a une erreur car d�j� divorc�e
		for (int c = 0; c < Main.listedivorce.size(); c++) { // recherche parmi id divorc�1 et divorc�2 si la personne est d�j� divorc�e
			if (Main.listedivorce.get(c).divorc�1numId == divorc�1numId || Main.listedivorce.get(c).divorc�2numId == divorc�1numId) {
				erreur = 1;
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne est d�j� divorc�e, retour au menu");
			Main.main(null);
		}
	}

	public void divorc�2Id() {
		Scanner clav = new Scanner(System.in);

		System.out.print("Entrez id epoux 2 � divorcer: ");
		divorc�2numId = clav.nextInt();

		if (divorc�1numId == divorc�2numId) {
			System.out.println("Erreur, c'est la m�me personne, retour au menu");
			Main.main(null);
		}

		int erreur = 1;
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == divorc�2numId) {
				erreur = 0;
			}
		}

		if (erreur == 1) {
			System.out.println("Erreur, cette personne n'existe pas dans la base de donn�es, retour au menu");
			Main.main(null);
		}

		erreur = 1;
		for (int b = 0; b < Main.listemariage.size(); b++) { // recherche parmi id epoux1 et epoux2 si la personne est mari�e
			if (Main.listemariage.get(b).epoux1numId == divorc�2numId || Main.listemariage.get(b).epoux2numId == divorc�2numId) {
				erreur = 0;
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'est pas mari�e, retour au menu");
			Main.main(null);
		}

		erreur = 0;
		for (int c = 0; c < Main.listedivorce.size(); c++) { // recherche parmi id divorc�1 et divorc�2 si la personne est d�j� divorc�e
			if (Main.listedivorce.get(c).divorc�1numId == divorc�2numId || Main.listedivorce.get(c).divorc�2numId == divorc�2numId) {
				erreur = 1;
			}
		}

		if (erreur == 1) {
			System.out.println("Erreur, cette personne est d�j� divorc�e, retour au menu");
			Main.main(null);
		}
	}

	public void dateDivorce() {
		Scanner clav3 = new Scanner(System.in);
		Calendar calendar = Calendar.getInstance();

		System.out.println("Entrez la date de d�c�s au format JJ-MM-AAAA, ");
		System.out.print("Jour: ");
		int jour = clav3.nextInt();
		calendar.set(Calendar.DATE, jour);

		System.out.print("Mois: ");
		int mois = clav3.nextInt();
		calendar.set(Calendar.MONTH, mois - 1);

		System.out.print("Ann�e: ");
		int ann�e = clav3.nextInt();
		calendar.set(Calendar.YEAR, ann�e);

		dateDivorce = calendar.getTime();
	}

	public void stockageDivorce() {
		Main.listedivorce.add(new Divorce(divorc�1numId, divorc�2numId, dateDivorce));
	}

	public void changementEtat() {
		int id, deces = 0;
		String sexe, nom, prenom, etatCivil;
		Date dateN;

		for (int a = 0; a < Main.listedeces.size(); a++) {
			if (divorc�1numId == Main.listedeces.get(a).numeroId) {
				deces = 1;
			}
		}

		if (deces == 1) {
			for (int a = 0; a < Main.listecitoyen.size(); a++) {
				if (divorc�1numId == Main.listecitoyen.get(a).numId) {
					id = Main.listecitoyen.get(a).numId;
					sexe = Main.listecitoyen.get(a).sexe;
					nom = Main.listecitoyen.get(a).nom;
					prenom = Main.listecitoyen.get(a).prenom;
					dateN = Main.listecitoyen.get(a).dateNaissance;
					Main.listecitoyen.remove(a);
					etatCivil = "d�c�d�(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}
		}

		else {
			for (int a = 0; a < Main.listecitoyen.size(); a++) {
				if (divorc�1numId == Main.listecitoyen.get(a).numId) {
					id = Main.listecitoyen.get(a).numId;
					sexe = Main.listecitoyen.get(a).sexe;
					nom = Main.listecitoyen.get(a).nom;
					prenom = Main.listecitoyen.get(a).prenom;
					dateN = Main.listecitoyen.get(a).dateNaissance;
					Main.listecitoyen.remove(a);
					etatCivil = "divorc�(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}
		}
	}

	public void changementEtat2() {
		int id, deces = 0;
		String sexe, nom, prenom, etatCivil;
		Date dateN;

		for (int a = 0; a < Main.listedeces.size(); a++) {
			if (divorc�2numId == Main.listedeces.get(a).numeroId) {
				deces = 1;
			}
		}

		if (deces == 1) {
			for (int a = 0; a < Main.listecitoyen.size(); a++) {
				if (divorc�2numId == Main.listecitoyen.get(a).numId) {
					id = Main.listecitoyen.get(a).numId;
					sexe = Main.listecitoyen.get(a).sexe;
					nom = Main.listecitoyen.get(a).nom;
					prenom = Main.listecitoyen.get(a).prenom;
					dateN = Main.listecitoyen.get(a).dateNaissance;
					Main.listecitoyen.remove(a);
					etatCivil = "d�c�d�(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}
		}

		else {
			for (int a = 0; a < Main.listecitoyen.size(); a++) {
				if (divorc�2numId == Main.listecitoyen.get(a).numId) {
					id = Main.listecitoyen.get(a).numId;
					sexe = Main.listecitoyen.get(a).sexe;
					nom = Main.listecitoyen.get(a).nom;
					prenom = Main.listecitoyen.get(a).prenom;
					dateN = Main.listecitoyen.get(a).dateNaissance;
					Main.listecitoyen.remove(a);
					etatCivil = "divorc�(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}
		}
	}

	public void resetMariage() {
		for (int d = 0; d < Main.listemariage.size(); d++) { // si la personne est mari�e alors on on doit l'enlever de listemariage
			if (Main.listemariage.get(d).epoux1numId == divorc�1numId || Main.listemariage.get(d).epoux2numId == divorc�1numId) {
				Main.listemariage.remove(d); // on enl�ve l'index, si l'un des epoux divorce alors l'autre aussi
			}
		}
	}
	
	// enregistre, Mairie Divorce
	private Mairie mairie;

	public Divorce() {
	}

	public Mairie getMairie() {
		return (mairie);
	}

	public void setMairie(Mairie a) {
		this.mairie = a;
	}

	public void addMairie(Mairie a) {
		if (a != null) {
			if (!a.getArray2().contains(this)) {
				if (mairie != null)
					mairie.remove(this);
				this.setMairie(a);
				mairie.getArray2().add(this);
			}
		}
	}

	// concerne, association Divorce Mariage
	private Mariage mariage;

	public void addMariage(Mariage b) {
		if (b != null) {
			if (b.getDivorce() != null) { // si b est d�j� connect� � un autre Divorce
				b.getDivorce().setMariage(null); // cet autre Divorce doit se d�connecter
			}
			this.setMariage(b);
			b.setDivorce(this);
		}
	}

	public Mariage getMariage() {
		return (mariage);
	}

	public void setMariage(Mariage b) {
		this.mariage = b;
	}
	
}