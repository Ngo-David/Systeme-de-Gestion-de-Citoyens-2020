package fr.projet.proj;

import java.io.Serializable;
import java.util.*;

public class Deces implements Serializable {

	private Date dateD�c�s;
	public int numeroId;
	int deces;

	// constructeur Deces avec en param�tre numeroId et dateD�c�s
	public Deces(int numeroId, Date dateD�c�s) {
		this.numeroId = numeroId;
		this.dateD�c�s = dateD�c�s;
	}

	public void getDeces() {
		deces = 0;	// 0 si en vie ou 1 si d�c�s
		Scanner clav = new Scanner(System.in);
		Scanner clav1 = new Scanner(System.in);
		Calendar calendar = Calendar.getInstance();

		System.out.println("Cette personne est-elle encore en vie ?");
		System.out.print("soit oui, soit non: ");
		String reponse = clav.nextLine();

		// on affiche une erreur si l'utilisateur entre autre chose que oui ou non
		if (!reponse.equals("oui") && !reponse.equals("non")) {
			System.out.println("Erreur, r�ponse incorrecte, retour au menu");
			Main.main(null);
		}

		if (reponse.equals("oui")) {
			deces = 0;
		}

		if (reponse.equals("non")) {
			System.out.println("Entrez la date du d�c�s au format JJ-MM-AAAA, ");
			System.out.print("Jour: ");
			int jour = clav1.nextInt();
			calendar.set(Calendar.DATE, jour);

			System.out.print("Mois: ");
			int mois = clav1.nextInt();
			calendar.set(Calendar.MONTH, mois - 1); // month commence � 0

			System.out.print("Ann�e: ");
			int ann�e = clav1.nextInt();
			calendar.set(Calendar.YEAR, ann�e);

			dateD�c�s = calendar.getTime(); // calendar.getTime prend la date et l'heure de l'ordinateur
			deces = 1;
		}
	}

	public void stockageDeces() {
		if (deces == 1) {
			// on r�cup�re le numId dans listecitoyen
			int num = Main.listecitoyen.size();
			// on va chercher dans le dernier index de listecitoyen et on le ram�ne ici pour l'enregistrer dans listedeces
			numeroId = Main.listecitoyen.get(num - 1).numId;

			Main.listedeces.add(new Deces(numeroId, dateD�c�s));
		}
	}

	public void etatDeces() {
		// changement d'�tat civil comme dans Mariage
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
					etatCivil = "d�c�d�(e)";
					Main.listecitoyen.add(new Citoyen(id, sexe, nom, prenom, dateN, etatCivil));
				}
			}
		}
	}
	
	//enregistr�, association Mairie Deces
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