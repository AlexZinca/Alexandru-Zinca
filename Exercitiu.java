

/*Exercitiul ales presupune realizarea unui program de recrutare pentru candidati. Astfel utilizatorul
 poate realiza urmatoarele actiuni :adaugarea persoanelor pe lista, stergerea acestora, modificarea unui atribut
 al unei persoane, ordonarea acestora, selectarea unui anumit numar de candidati si afisarea listei cu candidati.

Am creat o clasa intitulata “persoana” pentru care am generat un constructor. Astfel obiectul de tip persoana
are atributele nume, prenume, experienta si proiecte, utilizatorul introducand de la tastatura valorile 
corespunzatoare fiecaruia dintre atribute.
Am incercat sa separ functiile cat mai mult, pentru a organiza mai bine functionalitatea programului
Modul in care am gândit ordonarea candidatilor este urmatorul:
In primul rand se compara experienta, urmata de intrebarea daca persoanele care candideaza au lucrat si 
proiecte semnificative postului pentru care concureaza. In continuare ,in cazul in care candidatii au aceleasi 
valori ale atributelor , ordinea in care vor fi plasati in lista va fi alfabetica.
Selectia unui anumit numar de candidati se va face pe lista ordonata. In cazul in care pe ultima pozitie admisa 
concureaza mai multe persoane cu aceleasi valori ale atributelor, se va afisa un mesaj.
Am recurs la realizarea unui meniu pentru usurinta alegerii actiunilor, indicele fiecarei functii din meniu 
fiind corespunzator cu tasta care activeaza functia respectiva.

*/



package assessment_Zinca_Alexandru;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Exercitiu {
	
	public static class persoana 
	{

		public String nume;
		public String prenume;
		public String experienta;
		public String proiecte;
		public persoana(String nume, String prenume, String experienta, String proiecte) {
			super();
			this.nume = nume;
			this.prenume = prenume;
			this.experienta = experienta;
			this.proiecte = proiecte;
		}	
		
	}

	static ArrayList<persoana> candidati=new ArrayList<persoana>();
	
	private static void adaugaPersoana()
	{
		Scanner t=new Scanner(System.in);
		
		System.out.println("Introduceti un nume");
		String nume=t.next();
		System.out.println("Introduceti un prenume");
		String prenume=t.next();
		System.out.println("Introduceti experienta");
		String experienta=t.next();
		System.out.println("Persoana a participat in proiecte semnificative pentru post ?");
		String proiecte=t.next();
		
		persoana candidat=new persoana(nume,prenume,experienta,proiecte);
		candidati.add(candidat);
		
	}
	
	private static void stergePersoana()
	{
		Scanner t=new Scanner(System.in);
		System.out.println("Introduceti numarul persoanei pe care doriti sa o stergeti din lista");
		
		int i=t.nextInt();
		candidati.remove(i);
	}
	
	private static void ordonare()
	{
		for(int i=0;i<candidati.size()-1;i++)
		{
			String str1[]=impartireString(candidati.get(i).experienta);
			for(int j=i+1;j<=candidati.size()-1;j++)
			{
				String str2[]=impartireString(candidati.get(j).experienta);
				if((str1[1].equals("luna")||str1[1].equals("luni"))&&(str2[1].equals("an")||str2[1].equals("ani")))
				{
					Collections.swap(candidati, i, j);
				}
				else if(((str1[1].equals("an")||str1[1].equals("ani"))&&(str2[1].equals("an")||str2[1].equals("ani")))||
					((str1[1].equals("luna")||str1[1].equals("luni"))&&(str2[1].equals("luna")||str2[1].equals("luni"))))
					{
						if(Integer.parseInt(str1[0])<Integer.parseInt(str2[0]))
						{
							Collections.swap(candidati, i, j);
						}
						else if(Integer.parseInt(str1[0])==Integer.parseInt(str1[0]))
						     {
						     	if(candidati.get(i).proiecte.equalsIgnoreCase("nu")&&candidati.get(j).proiecte.equalsIgnoreCase("da"))
								{
									Collections.swap(candidati, i, j);
								}
								else if(candidati.get(i).proiecte.equals(candidati.get(j).proiecte))
								     {
									 if(candidati.get(i).nume.compareTo(candidati.get(j).nume)>0)
									 {
										Collections.swap(candidati, i, j);
									 }
									 else if(candidati.get(i).nume.compareTo(candidati.get(j).nume)==0)
									      {
										 if(candidati.get(i).prenume.compareTo(candidati.get(j).prenume)>0)
										 {
											Collections.swap(candidati, i, j);
										 }
									      }
								     }
							}
						}
					
			}
			
			
		}
	}
	
	private static void modifica()
	{
		Scanner t = new Scanner(System.in);
		System.out.println("Introduceti indicele persoanei pentru care doriti sa modificati o valoare");
		
		int nr= t.nextInt();
		
		System.out.println(("Introduceti parametrul pentru care doriti sa schimbati valoarea din nume, prenume, experienta, alteProiecte"));
		
		String parametrulModificat = t.next();
		parametrulModificat=parametrulModificat.toLowerCase();
		
		switch(parametrulModificat)
		{
		case "nume":
			System.out.println("Introduceti numele modificat");
			candidati.get(nr).nume=t.next();
			break;
		case "prenume":
			System.out.println("Introduceti prenumele modificat");
			candidati.get(nr).prenume=t.next();
			break;
		case "experienta":
			System.out.println("Introduceti experienta modificata");
			candidati.get(nr).experienta=t.next();
			break;
		case "alteProiecte":
			System.out.println("Candidatul a participat in proiecte semnificative pentru post ?");
			candidati.get(nr).proiecte=t.next();
			break;
		}
	}
	
	private static void selectie()
	{
		Scanner t = new Scanner(System.in);
		
		System.out.println("Introduceti numarul de persoane pe care doriti sa le selectati");
		int nrAles= t.nextInt();
		
		int nrCandidati=candidati.size();
		
		ordonare();
		
		if((!candidati.get(nrAles-1).experienta.equals(candidati.get(nrAles).experienta))||
		   (!candidati.get(nrAles-1).proiecte.equals(candidati.get(nrAles).proiecte)))
		   {
				for(int i=0;i<nrAles;i++)
				{
					System.out.println(candidati.get(i).nume+" "+candidati.get(i).prenume);
				}
		   }else
				{
					System.out.println("Sunt mai mult de " +nrAles+" persoane care pot fi alese");
				}
	}
	
	public static void afisareLista()
	{
		for(int i=0;i<candidati.size();i++)
		{
			System.out.println(i+". "+candidati.get(i).nume+" "+candidati.get(i).prenume);
		}
		
	}
	private static void activareMeniu()
	{
		System.out.println("\nMENIU"
							+"\n\t1 - Adaugare persoana"
							+"\n\t2 - Sterge persoana"
							+"\n\t3 - Modifica parametrii persoane"
							+"\n\t4 - Ordonare lista persoane"
							+"\n\t5 - Selectare unui numar ales de persoane"
							+"\n\t6 - Afisare lista "
							+"\n\t7 - Inchide program\n"
							);
	}

	private static String[] impartireString(String cuvant)
	{
		String str1="";
		String str2="";
		boolean flag=Character.isDigit(cuvant.charAt(1));
		
		if(flag)
		{
			str1=cuvant.substring(0,2);
			str2=cuvant.substring(2,cuvant.length());
		}else
		{
			str1=cuvant.substring(0,1);
			str2=cuvant.substring(1,cuvant.length());
		}
		
		String str[]= {"",""};
		str[0]=str1;
		str[1]=str2;
		return str;
	}
	
	public static void main(String[] args) {
			
		System.out.println("Formatul in care se vor introduce datele pentru candidati este urmatorul:\n"
				+ "\tNume, 		unde se scrie numele de familie\n"
				+ "\tPrenume, 	unde se scrie prenumele\n"
				+ "\tExperienta, 	unde se completeaza cu un numar alipit de cuvintele \"luna,luni,an,ani\", spre exemplu \"1luna\", \"5ani\", \"20ani\"\n"
				+ "\tAlte Proiecte,  unde se completeaza cu \"Da\" respectiv \"Nu\"");
		
		Scanner t =new Scanner(System.in);
		int nr=0;
		
		while(nr!=7)
		{
				activareMeniu();
			
				nr=t.nextInt();
		
				switch(nr)
				{
					case 1: adaugaPersoana();
							break;
					case 2: stergePersoana();
							break;
					case 3: modifica();
							break;
					case 4: ordonare();
							break;
					case 5: selectie();
		 					break;
					case 6: afisareLista();
							break;
					case 7: System.out.println("\nProgramul s-a terminat");
						    System.exit(0);
					default:System.out.println("\nIntroduceti o alta valoare din intervalul 1-7");
							
				}
		}	
	}
}
