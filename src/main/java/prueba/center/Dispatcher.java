package prueba.center;

import java.util.ArrayList;

/**
 * Dispatcher de control del callcenter
 * @author Pablo Sarmiento
 *
 */
public class Dispatcher {
	
	/**
	 * Cantidad de clientes por atender
	 */
	private    int    nProductores;
	
	/**
	 * Tamanio de la cola de mensajes 
	 */
	private    int   tamaniocola;
	
	/**
	 * Lista de llamadas entrantes
	 */
	private    ArrayList    llamadas;
	
	/**
	 * Manejador de empleados
	 */
	private ManejadorEmpleados manejadorEmpleados;
	
	/**
	 * Constructor de la clase
	 * @param nClientes numero de clientes por atender
	 * @param tamanio tamanio de cola del dispatcher
	 * @param me manejador de empleados
	 */
	public Dispatcher(int nClientes, int tamanio,ManejadorEmpleados me ){
		tamaniocola = tamanio;
		nProductores = nClientes;
		llamadas = new ArrayList();
		manejadorEmpleados=me;
	}
	
	/**
	 * Retira un cliente una vez se han procesado los mensajes
	 * y notifica si no quedan mas clientes por atender
	 */
	public synchronized void retirarCliente( ) {
		nProductores--;
		System.out.println("Se retira un productor del buffer y quedan "+ nProductores );
		if ( nProductores == 0 ){
			System.out.println("no hay mas produtores ... "+ nProductores );
			notifyAll( );}
	}
	
	/**
	 * Recibe un llamada  y notifca al empleado para que sea atendida
	 * @param n mensaje por defecto de la llamada
	 * @return true si pudo recibir la llamada o false en caso contrario
	 * @throws InterruptedException
	 */
	public synchronized boolean incomingCall( Integer n)	throws InterruptedException 
	{
		if( llamadas.size() ==tamaniocola){
				//System.out.println("Ingresando   no hay espacio buffer");		
				return false;
			}
		System.out.println("Poniendo mensaje "+ n);
		llamadas.add( n );
		notify( );
		return true;
	}
	
	/**
	 * Despacha una llamada al primer empleado que se encuentre disponible
	 * @return el empleado que atiende la llamda o null si no hay mas llamadas por atender
	 */
	public synchronized Empleado dispatchCall(){
		Integer m;
		System.out.println("Intentando recibir llamadas hay " +llamadas.size( ) +  " llamadas "+" y hay "+nProductores +"clientes");
		while ( llamadas.size( ) ==0 && nProductores != 0){
			try {
				wait( );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Empleado empleado=null;
		 if (llamadas.size( ) >0 && (empleado=manejadorEmpleados.findEmpleado())!=null){
			System.out.println(" Cambiando estado empleados y recibiendo llamada por el empleado " + empleado.getId() + " de tipo "+ empleado.getTipo());		
			m = (Integer)llamadas.remove( 0 );
			 empleado.setEstado(false);
			return empleado;
		}
		else return null;
	}
	/**
	 * @return the nProductores
	 */
	public int getnProductores() {
		return nProductores;
	}

	/**
	 * @return the mensajes
	 */
	public ArrayList getLLamadas() {
		return llamadas;
	}


	
}
