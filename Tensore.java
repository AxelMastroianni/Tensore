import java.util.*;
public class Tensore {
	private ArrayList<double[][]> tensore=new ArrayList<double[][]>();
	private ArrayList<Double> determinanti=new ArrayList<Double>();
	private double[][] matrice;
	private int dimensione=0;
	private Random r=new Random();
	
	public Tensore(int dimensione) {
		if(dimensione>1) {
			this.dimensione=dimensione;
			//mettere un parametro buffer che contiene le n matrici del tensore
			for(int i=0;i<dimensione;i++) {
				tensore.add(new double[dimensione][dimensione]);
				inizializzaMatrice(tensore.get(i));
			}
		}
		else
			throw new IllegalArgumentException();
	}
	/**
	 * solo per provare se calcola il determinante correttamente di una singola matrice
	 * @param dimensione
	 * @param nome
	 */
	public Tensore(int dimensione,String nome) {
		this.dimensione=dimensione;
		matrice=new double[dimensione][dimensione];
	}

	private void inizializzaMatrice(double[][] matrice) {
		for(int i=0;i<dimensione;i++) {
			for(int j=0;j<dimensione;j++)
				matrice[i][j]=r.nextDouble()*10;
		}
	}
	
	private double[] cercaPrimaRigaNonNulla(double[][] matrice, int partenza) {
		for(int j=partenza;j<dimensione;j++) {
			if(Math.abs(matrice[j][partenza])>0.0000001)
				return matrice[j];
		}
		return null;
	}
	
	private void sommaRighe(double[] riga1, double[] riga2) {
		for(int i=0;i<dimensione;i++)
			riga2[i]+=riga1[i];
	}
	
	private void moltiplicaPerScalare(double[] riga, double scalare) {
		for(int i=0;i<dimensione;i++)
			riga[i]*=scalare;
	}
	
	public void generaTriangolareInferiore(double[][] matrice) {
		for(int i=0;i<dimensione-1;i++) {
			if(Math.abs(matrice[i][i])<0.0000001) {
				double[] rigaConPrimoNonZero=cercaPrimaRigaNonNulla(matrice,i);
				if(rigaConPrimoNonZero==null)
					continue;//ha trovato tutti zeri sulla colonna i-esima
				double[] tmp=matrice[i];
				matrice[i]=rigaConPrimoNonZero;
				rigaConPrimoNonZero=tmp;
			}
			for(int j=i+1;j<dimensione;j++) {
				double x=-matrice[j][i]/matrice[i][i];
				moltiplicaPerScalare(matrice[i],x);
				sommaRighe(matrice[i],matrice[j]);
			}
		}
	}
	
	public double[][] getMatrice(){
		return matrice;
	}
	/**
	 * stampa una singola matrice
	 * @param matrice
	 */
	public void stampaMatrice(double[][] matrice) {
		for(int i=0;i<dimensione;i++) {
			for(int j=0;j<dimensione;j++)
				System.out.printf("%f\t",matrice[i][j]);
			System.out.println();
		}
	}
	
	private double calcolaDeterminante(double[][] matrice) {
		double determinante=1;
		for(int i=0;i<dimensione;i++)
			determinante*=matrice[i][i];
		return determinante;
	}
	
	public void calcolaDeterminanti() {
		for(int i=0;i<tensore.size();i++) {
			generaTriangolareInferiore(tensore.get(i));
			determinanti.add(calcolaDeterminante(tensore.get(i)));
		}
	}
	
	public double determinanteTensore() {
		double determinante=0;
		for(int i=0;i<determinanti.size();i++)
			determinante+=determinanti.get(i);
		return determinante;
	}

}
