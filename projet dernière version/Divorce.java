package fr.projet.proj;

import java.util.*;
import java.io.Serializable;

public class Divorce implements Serializable {

	private Date dateDivorce;
	public int divorcé1numId;
	public int divorcé2numId;

	// constructeur Divorce avec en paramètre divorcé1numId, divorcé2numId et dateDivorce
	public Divorce(int divorcé1numId, int divorcé2numId, Date dateDivorce) {
		this.divorcé1numId = divorcé1numId;
		this.divorcé2numId = divorcé2numId;
		this.dateDivorce = dateDivorce;
	}

	public void divorcé1Id() {
		Scanner clav = new Scanner(System.in);
		System.out.print("Entrez id epoux 1 à divorcer: ");
		divorcé1numId = clav.nextInt();

		int erreur = 1; // par défaut il y a une erreur
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == divorcé1numId) { // on vérifie si id existe dans la base de données
				erreur = 0; // l'id a été trouvé il n'y a plus d'erreur
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'existe pas dans la base de données, retour au menu");
			Main.main(null);
		}

		erreur = 1;
		for (int b = 0; b < Main.listemariage.size(); b++) { // recherche parmi id epoux1 et epoux2 si la personne est mariée
			if (Main.listemariage.get(b).epoux1numId == divorcé1numId || Main.listemariage.get(b).epoux2numId == divorcé1numId) {
				erreur = 0;
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'est pas mariée, retour au menu");
			Main.main(null);
		}

		erreur = 0; // cette fois on inverse, si la personne se trouve dans la listedivorce alors il y a une erreur car déjà divorcée
		for (int c = 0; c < Main.listedivorce.size(); c++) { // recherche parmi id divorcé1 et divorcé2 si la personne est déjà divorcée
			if (Main.listedivorce.get(c).divorcé1numId == divorcé1numId || Main.listedivorce.get(c).divorcé2numId == divorcé1numId) {
				erreur = 1;
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne est déjà divorcée, retour au menu");
			Main.main(null);
		}
	}

	public void divorcé2Id() {
		Scanner clav = new Scanner(System.in);

		System.out.print("Entrez id epoux 2 à divorcer: ");
		divorcé2numId = clav.nextInt();

		if (divorcé1numId == divorcé2numId) {
			System.out.println("Erreur, c'est la même personne, retour au menu");
			Main.main(null);
		}

		int erreur = 1;
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == divorcé2numId) {
				erreur = 0;
			}
		}

		if (erreur == 1) {
			System.out.println("Erreur, cette personne n'existe pas dans la base de données, retour au menu");
			Main.main(null);
		}

		erreur = 1;
		for (int b = 0; b < Main.listemariage.size(); b++) { // recherche parmi id epoux1 et epoux2 si la personne est mariée
			if (Main.listemariage.get(b).epoux1numId == divorcé2numId || Main.listemariage.get(b).epoux2numId == divorcé2numId) {
				erreur = 0;
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'est pas mariée, retour au menu");
			Main.main(null);
		}

		erreur = 0;
		for (int c = 0; c < Main.listedivorce.size(); c++) { // recherche parmi id divorcé1 et divorcé2 si la personne est déjà divorcée
			if (Main.listedivorce.get(c).divorcé1numId == divorcé2numId || Main.listedivorce.get(c).divorcé2numId == divorcé2numId) {
				erreur = 1;
			}
		}

		if (erreur == 1) {
			System.out.println("Erreur, cette personne est déjà divorcée, retour au menu");
			Main.main(null);
		}
	}

	public void dateDivorce() {
		Scanner clav3 = new Scanner(System.in);
		Calendar calendar = Calendar.getInstance();

		System.out.println("Entrez la date de décès au format JJ-MM-AAAA, ");
		System.out.print("Jour: ");
		int jour = clav3.nextInt();
		calendar.set(Calendar.DATE, jour);

		System.out.print("Mois: ");
		int mois = clav3.nextInt();
		calendar.set(Calendar.MONTH, mois - 1);

		System.out.print("Année: ");
		int année = clav3.nextInt();
		calendar.set(Calendar.YEAR, année);

		dateDivorce = calendar.getTime();
	}

	public void stockageDivorce() {
		Main.listedivorce.add(new Divorce(divorcé1numId, divorcé2numId, dateDivorce));
	}

	public void changementEtat() {
		int id, deces = 0;
		String sexe, nom, prenom, etatCivil;
		Date dateN;

		for (int a = 0; a < Main.listedeces.size(); a++) {
			if (divorcé1numId == Main.listedeces.get(a).numeroId) {
				deces = 1;
			}
		}

		if (deces == 1) {
			for (int a = 0; a < Main.listecitoyen.size(); a++) {
				if (divorcé1numId == Main.listecitoyen.get(a).numId) {
					id = Main.listecitoyen.get(a).numId;
					sexe = Main.listecitoyen.get(a).sexe;
					nom = Main.listecitoyen.get(a).nom;
					prenom = Main.listecitoyen.get(a).prenom;
					dateN = Main.listecitoyen.get(a).dateNaissance;
					Main.listecitoyen.remove(a);
					etatCivil = "décédé(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}
		}

		else {
			for (int a = 0; a < Main.listecitoyen.size(); a++) {
				if (divorcé1numId == Main.listecitoyen.get(a).numId) {
					id = Main.listecitoyen.get(a).numId;
					sexe = Main.listecitoyen.get(a).sexe;
					nom = Main.listecitoyen.get(a).nom;
					prenom = Main.listecitoyen.get(a).prenom;
					dateN = Main.listecitoyen.get(a).dateNaissance;
					Main.listecitoyen.remove(a);
					etatCivil = "divorcé(e)";
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
			if (divorcé2numId == Main.listedeces.get(a).numeroId) {
				deces = 1;
			}
		}

		if (deces == 1) {
			for (int a = 0; a < Main.listecitoyen.size(); a++) {
				if (divorcé2numId == Main.listecitoyen.get(a).numId) {
					id = Main.listecitoyen.get(a).numId;
					sexe = Main.listecitoyen.get(a).sexe;
					nom = Main.listecitoyen.get(a).nom;
					prenom = Main.listecitoyen.get(a).prenom;
					dateN = Main.listecitoyen.get(a).dateNaissance;
					Main.listecitoyen.remove(a);
					etatCivil = "décédé(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}
		}

		else {
			for (int a = 0; a < Main.listecitoyen.size(); a++) {
				if (divorcé2numId == Main.listecitoyen.get(a).numId) {
					id = Main.listecitoyen.get(a).numId;
					sexe = Main.listecitoyen.get(a).sexe;
					nom = Main.listecitoyen.get(a).nom;
					prenom = Main.listecitoyen.get(a).prenom;
					dateN = Main.listecitoyen.get(a).dateNaissance;
					Main.listecitoyen.remove(a);
					etatCivil = "divorcé(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}
		}
	}

	public void resetMariage() {
		for (int d = 0; d < Main.listemariage.size(); d++) { // si la personne est mariée alors on on doit l'enlever de listemariage
			if (Main.listemariage.get(d).epoux1numId == divorcé1numId || Main.listemariage.get(d).epoux2numId == divorcé1numId) {
				Main.listemariage.remove(d); // on enlève l'index, si l'un des epoux divorce alors l'autre aussi
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
			if (b.getDivorce() != null) { // si b est déjà connecté à un autre Divorce
				b.getDivorce().setMariage(null); // cet autre Divorce doit se déconnecter
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