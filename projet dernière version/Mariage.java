package fr.projet.proj;

import java.io.Serializable;
import java.util.*;

public class Mariage implements Serializable {

	private Date dateMariage;
	public int epoux1numId;
	public int epoux2numId;

	// constructeur Mariage avec en param�tre epoux1numId, epoux2numId et dateMariage
	public Mariage(int epoux1numId, int epoux2numId, Date dateMariage) {
		this.epoux1numId = epoux1numId;
		this.epoux2numId = epoux2numId;
		this.dateMariage = dateMariage;
	}

	public void epoux1Id() {
		Scanner clav = new Scanner(System.in);
		System.out.print("Entrez le num�ro d'identifiant du premier �poux: ");
		epoux1numId = clav.nextInt();

		int erreur = 1; // par d�faut il y a une erreur
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == epoux1numId) { // on v�rifie si id existe dans la base de donn�es
				erreur = 0; // l'id a �t� trouv� il n'y a plus d'erreur
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'existe pas dans la base de donn�es, retour au menu");
			Main.main(null);
		}

		erreur = 0; // cette fois on inverse, si la personne se trouve dans la listemariage alors il y a une erreur car d�j� mari�e
		for (int c = 0; c < Main.listemariage.size(); c++) { // recherche parmi id epoux1 et epoux2 si la personne est d�j� mari�e
			if (Main.listemariage.get(c).epoux1numId == epoux1numId || Main.listemariage.get(c).epoux2numId == epoux1numId) {
				erreur = 1;
			}
		}

		if (erreur == 1) { // s'il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne est d�j� mari�e, retour au menu");
			Main.main(null);
		}
	}

	public void epoux2Id() {
		Scanner clav = new Scanner(System.in);

		System.out.print("Entrez le num�ro d'identifiant du deuxi�me �poux: ");
		epoux2numId = clav.nextInt();

		if (epoux1numId == epoux2numId) {
			System.out.println("Erreur c'est la m�me personne, retour au menu"); // car m�me id
			Main.main(null);
		}

		int erreur = 1;
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == epoux2numId) { // on v�rifie si id existe dans la base de donn�es
				erreur = 0; // l'id a �t� trouv�, il n'y a plus d'erreur
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne n'existe pas dans la base de donn�es, retour au menu");
			Main.main(null);
		}

		erreur = 0;
		for (int b = 0; b < Main.listemariage.size(); b++) { // recherche parmi id epoux1 et epoux2 si la personne est d�j� mari�e
			if (Main.listemariage.get(b).epoux1numId == epoux2numId || Main.listemariage.get(b).epoux2numId == epoux2numId) {
				erreur = 1;
			}
		}

		if (erreur == 1) { // si il y a une erreur, on retourne au menu, sinon on continue
			System.out.println("Erreur, cette personne est d�j� mari�e, retour au menu");
			Main.main(null);
		}
	}

	public void dateMariage() {
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
		dateMariage = calendar.getTime();
	}

	public void valEtatapr�smariage() {
		// changement d'etat civil
		int id;
		String sexe, nom, prenom, etatCivil;
		Date dateN;

		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (epoux1numId == Main.listecitoyen.get(a).numId) {
				// on sauvegarde tout sauf etatCivil
				id = Main.listecitoyen.get(a).numId;
				sexe = Main.listecitoyen.get(a).sexe;
				nom = Main.listecitoyen.get(a).nom;
				prenom = Main.listecitoyen.get(a).prenom;
				dateN = Main.listecitoyen.get(a).dateNaissance;
				// on enl�ve tout l'index a
				Main.listecitoyen.remove(a);
				// pour recr�er l'objet � l'identique mais avec un etat civil diff�rent
				etatCivil = "mari�(e)";
				Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
			}
		}
	}

	public void valEtatapr�smariage2() {
		// m�me chose en inversant epoux1numId avec epoux2numId
		int id;
		String sexe, nom, prenom, etatCivil;
		Date dateN;

		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (epoux2numId == Main.listecitoyen.get(a).numId) {
				// on sauvegarde tout sauf etatCivil
				id = Main.listecitoyen.get(a).numId;
				sexe = Main.listecitoyen.get(a).sexe;
				nom = Main.listecitoyen.get(a).nom;
				prenom = Main.listecitoyen.get(a).prenom;
				dateN = Main.listecitoyen.get(a).dateNaissance;
				// on enl�ve tout l'index a
				Main.listecitoyen.remove(a);
				// pour recr�er l'objet � l'identique mais avec un etat civil diff�rent
				etatCivil = "mari�(e)";
				Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
			}
		}
	}

	public void valEtatapr�smariageDeces() {
		int id, deces = 0, deces2 = 0;
		String sexe, nom, prenom, etatCivil;
		Date dateN;
		
		// on cherche si epoux1 est d�c�d�(e) car si il/elle l'est alors son �tat civil ne change pas 
		for (int a = 0; a < Main.listedeces.size(); a++) {
			if (epoux1numId == Main.listedeces.get(a).numeroId) {
				deces = 1;	// deces passe � 1 si la personne est d�c�d�e
			}
		}

		if (deces == 1) {
			for (int b = 0; b < Main.listecitoyen.size(); b++) {
				if (epoux1numId == Main.listecitoyen.get(b).numId) {
					id = Main.listecitoyen.get(b).numId;
					sexe = Main.listecitoyen.get(b).sexe;
					nom = Main.listecitoyen.get(b).nom;
					prenom = Main.listecitoyen.get(b).prenom;
					dateN = Main.listecitoyen.get(b).dateNaissance;
					Main.listecitoyen.remove(b);
					// si la personne mari�e est d�c�d�e, son �tat civil est "d�c�d�(e)" (car l'�tat civil est pass� � "mari�(e))
					etatCivil = "d�c�d�(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}
			
			// comme pour epoux1, on cherche si epoux2 est d�c�d�(e) car si il/elle l'est alors son �tat civil ne change pas 
			for (int c = 0; c < Main.listedeces.size(); c++) {
				if (epoux2numId == Main.listedeces.get(c).numeroId) {
					deces2 = 1;
				}
			}

			// si epoux1 est d�c�d�(e) alors l'etat civil de epoux2 est "veuf(ve)"
			if (deces2 != 1) { // si �tat civil de epooux1 est "d�c�d�(e)" alors on ne change pas en veuf(ve)
				for (int d = 0; d < Main.listecitoyen.size(); d++) {
					if (epoux2numId == Main.listecitoyen.get(d).numId) {
						// on sauvegarde tout sauf etatCivil
						id = Main.listecitoyen.get(d).numId;
						sexe = Main.listecitoyen.get(d).sexe;
						nom = Main.listecitoyen.get(d).nom;
						prenom = Main.listecitoyen.get(d).prenom;
						dateN = Main.listecitoyen.get(d).dateNaissance;
						// on enl�ve tout l'index d
						Main.listecitoyen.remove(d);
						// pour recr�er l'objet � l'identique mais avec un etat civil diff�rent
						etatCivil = "veuf(ve)";
						Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
					}
				}
			}
		}
	}

	public void valEtatapr�smariageDeces2() {
		// les m�mes �tapes mais en inversant epoux1 et epoux2
		int id, deces = 0, deces2 = 0;
		String sexe, nom, prenom, etatCivil;
		Date dateN;

		for (int a = 0; a < Main.listedeces.size(); a++) {
			if (epoux2numId == Main.listedeces.get(a).numeroId) {
				deces = 1;
			}
		}

		if (deces == 1) {
			for (int b = 0; b < Main.listecitoyen.size(); b++) {
				if (epoux2numId == Main.listecitoyen.get(b).numId) {
					id = Main.listecitoyen.get(b).numId;
					sexe = Main.listecitoyen.get(b).sexe;
					nom = Main.listecitoyen.get(b).nom;
					prenom = Main.listecitoyen.get(b).prenom;
					dateN = Main.listecitoyen.get(b).dateNaissance;
					Main.listecitoyen.remove(b);
					etatCivil = "d�c�d�(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}

			for (int c = 0; c < Main.listedeces.size(); c++) {
				if (epoux1numId == Main.listedeces.get(c).numeroId) {
					deces2 = 1;
				}
			}

			// si epoux2 est d�c�d�(e) alors l'etat civil de epoux1 est "veuf(ve)"
			if (deces2 != 1) { // si �tat civil de epooux1 est "d�c�d�(e)" alors on ne change pas en veuf(ve)
				for (int d = 0; d < Main.listecitoyen.size(); d++) {
					if (epoux1numId == Main.listecitoyen.get(d).numId) {
						// on sauvegarde tout sauf etatCivil
						id = Main.listecitoyen.get(d).numId;
						sexe = Main.listecitoyen.get(d).sexe;
						nom = Main.listecitoyen.get(d).nom;
						prenom = Main.listecitoyen.get(d).prenom;
						dateN = Main.listecitoyen.get(d).dateNaissance;
						// on enl�ve tout l'index d
						Main.listecitoyen.remove(d);
						// pour recr�er l'objet � l'identique mais avec un etat civil diff�rent
						etatCivil = "veuf(ve)";
						Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
					}
				}
			}
		}
	}

	public void stockageMariage() {
		Main.listemariage.add(new Mariage(epoux1numId, epoux2numId, dateMariage));
	}
	
	// concerne, Divorce Mariage
	private Divorce divorce;

	public void addDivorce(Divorce a) {
		if (a != null) {
			if (a.getMariage() != null) { 
				a.getMariage().setDivorce(null); 
			}
			this.setDivorce(a);
			a.setMariage(this);
		}
	}

	public void setDivorce(Divorce a) {
		this.divorce = a;
	}

	public Divorce getDivorce() {
		return (divorce);
	}

	// enregistre, association Mairie Mariage
	private Mairie mairie;

	public Mariage() {
	}

	public Mairie getMairie() {
		return (mairie);
	}

	public void setMairie(Mairie b) {
		this.mairie = b;
	}

	public void addMairie(Mairie b) {
		if (b != null) {
			if (!b.getArray3().contains(this)) {
				if (mairie != null)
					mairie.remove(this);
				this.setMairie(b);
				mairie.getArray3().add(this);
			}
		}
	}

	// branche epoux1 epoux2, Citoyen Mariage
	private Citoyen citoyen;

	public Citoyen getCitoyen() {
		return (citoyen);
	}

	public void setCitoyen(Citoyen a) {
		this.citoyen = a;
	}

	public void addCitoyen(Citoyen a) {
		if (a != null) {
			if (!a.getArray6().contains(this)) {
				if (citoyen != null)
					citoyen.remove(this);
				this.setCitoyen(a);
				citoyen.getArray6().add(this);
			}
		}
	}

}