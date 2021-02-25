package fr.projet.proj;

import java.io.Serializable;
import java.util.*;

public class Mairie implements Serializable {

	public String nomMairie;
	public int codePostal;
	
	// constructeur Mairie avec en paramètre nomMairie et codePostal
	public Mairie(String nomMairie, int codePostal) {
		this.nomMairie = nomMairie;
		this.codePostal = codePostal;
	}

	public void mairieNom() {
		Scanner clav = new Scanner(System.in);
		System.out.print("Nom de la mairie où cette personne est née: ");
		nomMairie = clav.nextLine();

		if (nomMairie.equals("")) {
			System.out.println("Erreur, nom de mairie non détecté, retour au menu");
			Main.main(null);
		}
	}

	public void mairieCP() {
		Scanner clav = new Scanner(System.in);
		System.out.print("Code postal de la mairie: ");
		codePostal = clav.nextInt();
	}

	public void stockageMairie() {
		Main.listemairie.add(new Mairie(nomMairie, codePostal));
	}

	public void affichageCitoyens() {
		System.out.println("Liste:");
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			//subList divise par index et on peut afficher chaque personne ligne par ligne car sinon on afficherais tout le monde sur une ligne 
			List<Citoyen> liste = Main.listecitoyen.subList(a, a + 1);
			System.out.println(liste);

			// recherche conjoint éventuel dans la listemariage
			for (int b = 0; b < Main.listemariage.size(); b++) {
				if (Main.listecitoyen.get(a).numId == Main.listemariage.get(b).epoux1numId) {
					for (int c = 0; c < Main.listecitoyen.size(); c++) {
						if (Main.listecitoyen.get(c).numId == Main.listemariage.get(b).epoux2numId) {
							// la personne recherchée est dans l'index numéro c
							System.out.print("Conjoint: " + Main.listecitoyen.get(c).nom);
							System.out.println(" " + Main.listecitoyen.get(c).prenom);
						}
					}
				}
			}

			// même chose en inversant epoux1 et epoux2
			for (int b = 0; b < Main.listemariage.size(); b++) {
				if (Main.listecitoyen.get(a).numId == Main.listemariage.get(b).epoux2numId) {
					for (int c = 0; c < Main.listecitoyen.size(); c++) {
						if (Main.listecitoyen.get(c).numId == Main.listemariage.get(b).epoux1numId) {
							// la personne recherchée est dans l'index numéro c
							System.out.print("Conjoint: " + Main.listecitoyen.get(c).nom);
							System.out.println(" " + Main.listecitoyen.get(c).prenom);
						}
					}
				}
			}
		}
	}

	public void rechercheCitoyen() {
		// c'est comme affichageCitoyens mais ici on ne cherche qu'une personne
		Scanner clav = new Scanner(System.in);
		System.out.print("Entrez le numéro d'identification de la personne: ");
		int recherche = clav.nextInt();

		int erreur = 1;
		for (int a = 0; a < Main.listecitoyen.size(); a++) {
			if (Main.listecitoyen.get(a).numId == recherche) {
				System.out.println(Main.listecitoyen.subList(a, a + 1));

				// recherche conjoint éventuel dans la listemariage
				for (int b = 0; b < Main.listemariage.size(); b++) {
					if (Main.listecitoyen.get(a).numId == Main.listemariage.get(b).epoux1numId) {
						for (int c = 0; c < Main.listecitoyen.size(); c++) {
							if (Main.listecitoyen.get(c).numId == Main.listemariage.get(b).epoux2numId) {
								// la personne recherchée est dans l'index numéro c
								System.out.print("Conjoint: " + Main.listecitoyen.get(c).nom);
								System.out.println(" " + Main.listecitoyen.get(c).prenom);
							}
						}
					}
				}

				// même chose en inversant epoux1 et epoux2
				for (int b = 0; b < Main.listemariage.size(); b++) {
					if (Main.listecitoyen.get(a).numId == Main.listemariage.get(b).epoux2numId) {
						for (int c = 0; c < Main.listecitoyen.size(); c++) {
							if (Main.listecitoyen.get(c).numId == Main.listemariage.get(b).epoux1numId) {
								// la personne recherchée est dans l'index numéro c
								System.out.print("Conjoint: " + Main.listecitoyen.get(c).nom);
								System.out.println(" " + Main.listecitoyen.get(c).prenom);
							}
						}
					}
				}

				erreur = 0;
			}
		}

		if (erreur == 1) {
			System.out.println("Erreur, cette personne n'existe pas dans la liste de la mairie, retour au menu");
			Main.main(null);
		}
	}
	
	// gère, association Mairie Citoyen
	private ArrayList<Citoyen> listecitoyen;

	public Mairie() {
		listecitoyen = new ArrayList<Citoyen>();
		listedeces = new ArrayList<Deces>();
		listedivorce = new ArrayList<Divorce>();
		listemariage = new ArrayList<Mariage>();
		listenaissance = new ArrayList<Naissance>();
	}

	public ArrayList<Citoyen> getArray() {
		return (listecitoyen);
	}

	public void remove(Citoyen b) {
		listecitoyen.remove(b);
	}

	public void addCitoyen(Citoyen b) {
		if (!listecitoyen.contains(b)) {
			if (b.getMairie() != null)
				b.getMairie().remove(b);
			b.setMairie(this);
			listecitoyen.add(b);
		}
	}

	// enregistré, association Mairie Deces
	private ArrayList<Deces> listedeces;

	public ArrayList<Deces> getArray1() {
		return (listedeces);
	}

	public void remove(Deces b) {
		listedeces.remove(b);
	}

	public void addDeces(Deces b) {
		if (!listedeces.contains(b)) {
			if (b.getMairie() != null)
				b.getMairie().remove(b);
			b.setMairie(this);
			listedeces.add(b);
		}
	}

	// enregistre, association Mairie Divorce
	private ArrayList<Divorce> listedivorce;

	public ArrayList<Divorce> getArray2() {
		return (listedivorce);
	}

	public void remove(Divorce b) {
		listedivorce.remove(b);
	}

	public void addDivorce(Divorce b) {
		if (!listedivorce.contains(b)) {
			if (b.getMairie() != null)
				b.getMairie().remove(b);
			b.setMairie(this);
			listedivorce.add(b);
		}
	}

	// enregistre, association Mairie Mariage
	private ArrayList<Mariage> listemariage;

	public ArrayList<Mariage> getArray3() {
		return (listemariage);
	}

	public void remove(Mariage b) {
		listemariage.remove(b);
	}

	public void addMariage(Mariage b) {
		if (!listemariage.contains(b)) {
			if (b.getMairie() != null)
				b.getMairie().remove(b);
			b.setMairie(this);
			listemariage.add(b);
		}
	}

	// enregistre, Mairie Naissance
	private ArrayList<Naissance> listenaissance;

	public ArrayList<Naissance> getArray4() {
		return (listenaissance);
	}

	public void remove(Naissance b) {
		listenaissance.remove(b);
	}

	public void addNaissance(Naissance b) {
		if (!listenaissance.contains(b)) {
			if (b.getMairie() != null)
				b.getMairie().remove(b);
			b.setMairie(this);
			listenaissance.add(b);
		}
	}
	
}