package prueba.center;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Clase que representa el cliente del callcenter
 * @author Pablo S
 *
 */
public class Cliente implements Runnable {

	/**
	 * Dispatcher
	 */
	private Dispatcher dispatcher;
	
	/**
	 * Cyclicbarrier de control
	 */
	private CyclicBarrier cb;
	
	/**
	 * Modo para ejecucion de pruebas
	 */
	private int modo;
	
	/**
	 * Contructor de la clase
	 * @param b dispatcher
	 * @param pcb ciclycbarrier
	 * @param pmodo modo
	 */
	public Cliente ( Dispatcher b, CyclicBarrier pcb, int pmodo)
	{
		dispatcher = b;
		cb=pcb;
		modo=pmodo;
	}
	
	/**
	 * Run
	 */
	public void run( ){
		
		try {
			if (modo==1) {
				cb.await();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (BrokenBarrierException e1) {
			e1.printStackTrace();
		}
			try {
				int n = new Integer( 1 );
				while ( !dispatcher.incomingCall(n) );{
					System.out.println("Productooooooor ............ enviando");
				}
			} catch (
					InterruptedException e ){
			};
		
		System.out.println("Productooooooor ............ sacando cliente");
			dispatcher.retirarCliente( );
			
			try {
				cb.await();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (BrokenBarrierException e1) {
				e1.printStackTrace();
			}
	}
}