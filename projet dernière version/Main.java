package fr.projet.proj;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		System.out.println("---------------");
		System.out.println("1. Mariage");
		System.out.println("2. Divorce");
		System.out.println("3. Naissance");
		System.out.println("4. Etat d'une personne");
		System.out.println("5. Affichage de la liste des personnes");
		System.out.println("6. Saisie des personnes");
		System.out.println("7. Quitter le programme");
		System.out.print("Votre choix? ");
		int reponse = clavier.nextInt();
		System.out.println("---------------");

		if (reponse == 1) {
			Mariage mariage = new Mariage();
			mariage.epoux1Id();
			mariage.epoux2Id();
			mariage.dateMariage();
			mariage.stockageMariage();
			mariage.valEtatapr�smariage();
			mariage.valEtatapr�smariage2();
			mariage.valEtatapr�smariageDeces();
			mariage.valEtatapr�smariageDeces2();
			System.out.println("Enregistr�.");
			Main.main(args);
		}

		if (reponse == 2) {
			Divorce divorce = new Divorce();
			divorce.divorc�1Id();
			divorce.divorc�2Id();
			divorce.dateDivorce();
			divorce.stockageDivorce();
			divorce.resetMariage();
			divorce.changementEtat();
			divorce.changementEtat2();
			System.out.println("Enregistr�.");
			Main.main(args);
		}

		if (reponse == 3) {

			Naissance naissance = new Naissance();
			naissance.p�reId();
			naissance.m�reId();
			naissance.dateNaissance();
			naissance.stockageNaissance();
			// le nouveau n� devient un nouveau citoyen
			Citoyen citoyen = new Citoyen();
			citoyen.valNumId();
			citoyen.valSexe();
			citoyen.valNom();
			citoyen.valPrenom();
			citoyen.stockageCitoyenNaissance();
			System.out.println("Enregistr�.");
			Main.main(args);
		}

		if (reponse == 4) {
			Mairie mairie = new Mairie();
			mairie.rechercheCitoyen();
			Main.main(args);
		}

		if (reponse == 5) {
			Mairie mairie = new Mairie();
			mairie.affichageCitoyens();
			Main.main(args);
		}

		if (reponse == 6) {
			Citoyen citoyen = new Citoyen();
			citoyen.valNumId();
			citoyen.valSexe();
			citoyen.valNom();
			citoyen.valPrenom();
			citoyen.valDate();
			citoyen.valEtatpard�faut();
			Mairie mairie = new Mairie();
			mairie.mairieNom();
			mairie.mairieCP();
			mairie.stockageMairie();
			Deces deces = new Deces();
			deces.getDeces();
			citoyen.stockageCitoyen();
			deces.stockageDeces();
			deces.etatDeces();
			System.out.println("Enregistr�.");
			Main.main(args);
		}

		if (reponse == 7) {
			try {
				Citoyen citoyen = new Citoyen();
				FileOutputStream fos = new FileOutputStream("sauvegarde");
				ObjectOutputStream ooa = new ObjectOutputStream(fos);
				ObjectOutputStream oob = new ObjectOutputStream(fos);
				ObjectOutputStream ooc = new ObjectOutputStream(fos);
				ObjectOutputStream ood = new ObjectOutputStream(fos);
				ObjectOutputStream ooe = new ObjectOutputStream(fos);
				ObjectOutputStream oof = new ObjectOutputStream(fos);
				ooa.writeObject(listecitoyen);
				oob.writeObject(listemariage);
				ooc.writeObject(listedivorce);
				ood.writeObject(listenaissance);
				ooe.writeObject(listemairie);
				oof.writeObject(listedeces);
				ooa.close();
				oob.close();
				ooc.close();
				ood.close();
				ooe.close();
				oof.close();
				fos.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			System.out.println("Sauvegarde et arr�t du programme.");
			System.exit(0);
		}

		else {
			System.out.println("Erreur, ce num�ro ne correspond � aucun menu");
			Main.main(args);
		}
	}
	
	// Base de donn�es, on va stocker dans les tableaux dynamiques (ArrayList) les objets
	public static ArrayList<Mariage> listemariage = new ArrayList<Mariage>();
	public static ArrayList<Divorce> listedivorce = new ArrayList<Divorce>();
	public static ArrayList<Naissance> listenaissance = new ArrayList<Naissance>();
	public static ArrayList<Citoyen> listecitoyen = new ArrayList<Citoyen>();
	public static ArrayList<Mairie> listemairie = new ArrayList<Mairie>();
	public static ArrayList<Deces> listedeces = new ArrayList<Deces>();
}
