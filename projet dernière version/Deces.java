package fr.projet.proj;

import java.io.Serializable;
import java.util.*;

public class Deces implements Serializable {

	private Date dateDécès;
	public int numeroId;
	int deces;

	// constructeur Deces avec en paramètre numeroId et dateDécès
	public Deces(int numeroId, Date dateDécès) {
		this.numeroId = numeroId;
		this.dateDécès = dateDécès;
	}

	public void getDeces() {
		deces = 0;	// 0 si en vie ou 1 si décès
		Scanner clav = new Scanner(System.in);
		Scanner clav1 = new Scanner(System.in);
		Calendar calendar = Calendar.getInstance();

		System.out.println("Cette personne est-elle encore en vie ?");
		System.out.print("soit oui, soit non: ");
		String reponse = clav.nextLine();

		// on affiche une erreur si l'utilisateur entre autre chose que oui ou non
		if (!reponse.equals("oui") && !reponse.equals("non")) {
			System.out.println("Erreur, réponse incorrecte, retour au menu");
			Main.main(null);
		}

		if (reponse.equals("oui")) {
			deces = 0;
		}

		if (reponse.equals("non")) {
			System.out.println("Entrez la date du décès au format JJ-MM-AAAA, ");
			System.out.print("Jour: ");
			int jour = clav1.nextInt();
			calendar.set(Calendar.DATE, jour);

			System.out.print("Mois: ");
			int mois = clav1.nextInt();
			calendar.set(Calendar.MONTH, mois - 1); // month commence à 0

			System.out.print("Année: ");
			int année = clav1.nextInt();
			calendar.set(Calendar.YEAR, année);

			dateDécès = calendar.getTime(); // calendar.getTime prend la date et l'heure de l'ordinateur
			deces = 1;
		}
	}

	public void stockageDeces() {
		if (deces == 1) {
			// on récupère le numId dans listecitoyen
			int num = Main.listecitoyen.size();
			// on va chercher dans le dernier index de listecitoyen et on le ramène ici pour l'enregistrer dans listedeces
			numeroId = Main.listecitoyen.get(num - 1).numId;

			Main.listedeces.add(new Deces(numeroId, dateDécès));
		}
	}

	public void etatDeces() {
		// changement d'état civil comme dans Mariage
		int id;
		String sexe, nom, prenom, etatCivil;
		Date dateN;

		if (deces == 1) {
			for (int a = 0; a < Main.listecitoyen.size(); a++) {
				if (numeroId == Main.listecitoyen.get(a).numId) {
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
	}
	
	//enregistré, association Mairie Deces
	private Mairie mairie;

	public Deces() {
	}

	public Mairie getMairie() {
		return (mairie);
	}

	public void setMairie(Mairie b) {
		this.mairie = b;
	}

	public void addMairie(Mairie b) {
		if (b != null) {
			if (!b.getArray1().contains(this)) {
				if (mairie != null)
					mairie.remove(this);
				this.setMairie(b);
				mairie.getArray1().add(this);
			}
		}
	}

	//concerne, association Deces Citoyen
	private Citoyen citoyen;

	public void addCitoyen(Citoyen b) {
		if (b != null) {
			if (b.getDeces() != null) { 
				b.getDeces().setCitoyen(null); 
			}
			this.setCitoyen(b);
			b.setDeces(this);
		}
	}

	public Citoyen getCitoyen() {
		return (citoyen);
	}

	public void setCitoyen(Citoyen b) {
		this.citoyen = b;
	}
	
}