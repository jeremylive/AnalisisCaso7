package progra;

import java.util.ArrayList;

public class AlgoritmoProbatorioProbabilistico 
{
    private final double total_del_intervalo = Math.pow(2, 12);
    private int[][] lista_intervalo_espectro;
    private double[] lista_porcentaje_razon;
    private int numero_random;

	/*
	 * 
	 */
    private int[][] obtenerRangos(int tamano_lista,double[] razon)
    {
    	int sumatoria = 0;
    	int rango = 0;
    	lista_intervalo_espectro = new int[tamano_lista][2];
    	
  
    	for (int indice_porcentaje = 0; indice_porcentaje < lista_porcentaje_razon.length; indice_porcentaje++) 
    	{
    		sumatoria += razon[indice_porcentaje];
    		rango = (int) Math.floor(total_del_intervalo / lista_porcentaje_razon.length * sumatoria);
    
    		if (indice_porcentaje != 0) 
    		{
    			lista_intervalo_espectro[indice_porcentaje][0] = lista_intervalo_espectro[indice_porcentaje - 1][1] + 1;
    		}
    		lista_intervalo_espectro[indice_porcentaje][1] = rango;
    	}
    	return lista_intervalo_espectro;
    }
    
    /*
     * 
     */
    private double[] cambiarRazones(double[] razones, int[] indices, boolean isSuccessful) 
    {
        for (int indice = 0; indice < indices.length; indice++) 
        {
          razones[indices[indice]] = isSuccessful? razones[indices[indice]] + 0.1 : razones[indices[indice]] - 0.1;
        }
        return razones;
    }
    
    /*
     * 
     */
    public int encontrarPar(int idCromosoma) 
    {
      return (int) Math.floor(idCromosoma * lista_porcentaje_razon.length / total_del_intervalo);
    }
    
    /*
     * Metodos necesarios para el funcionamiento propio del programa
     */
    public void createListaPorcentajeRazon(int largo_lista_vectores)
    {
    	this.lista_porcentaje_razon = new double[largo_lista_vectores];
    }
    
    public void setListaPorcenatajeRazon(int pIndice, int pValor)
    {
    	this.lista_porcentaje_razon[pIndice] = pValor;
    }
    
    public int getLargoListaPorcentajeRazon()
    {
    	return lista_porcentaje_razon.length;
    }
    
    public void createListaIntervaloEspectro(int pLargo_vectores_posibles)
    {
    	this.lista_intervalo_espectro = obtenerRangos(pLargo_vectores_posibles, lista_porcentaje_razon);    	
    }
    
    public void calcularNumeroRandom()
    {
    	this.numero_random = (int) (Math.random() * getTotalDelIntervalo());
    }
    
    public void actualizarRazon(int[] pIndices_escogidos, boolean pBandera)
    {
    	lista_porcentaje_razon = cambiarRazones(lista_porcentaje_razon, pIndices_escogidos, pBandera);
    }
    
    public void actualizarEspectro()
    {
    	 lista_intervalo_espectro = obtenerRangos(lista_intervalo_espectro.length, lista_porcentaje_razon);
    }
    
    
    /*
     * Gets y Sets 
     */
    public int getNumeroRandom()
    {
    	return numero_random;
    }
    
    public double getTotalDelIntervalo()
    {
        return total_del_intervalo;
    }
    
    public int[][] getLista_intervalo_espectro() 
    {
        return lista_intervalo_espectro;
    }

    public void setLista_intervalo_espectro(int[][] lista_intervalo_espectro) 
    {
        this.lista_intervalo_espectro = lista_intervalo_espectro;
    }

    public double[] getListaRazon() {
        return lista_porcentaje_razon;
    }

    public void setLista_porcentaje_razon(double[] lista_porcentaje_razon) 
    {
        this.lista_porcentaje_razon = lista_porcentaje_razon;
    } 
}
