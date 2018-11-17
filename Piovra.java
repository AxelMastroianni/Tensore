import it.unibs.fp.mylib.InputDati;

public class Piovra {
	
	public static void main(String[] args) {
		int dimensione=InputDati.leggiIntero("Inserisci la dimensione del tensore: ");
		Tensore t=new Tensore(dimensione);
		t.calcolaDeterminanti();
		double determinante=t.determinanteTensore();
		System.out.println("Il determinante è: "+determinante);
	}

}
