package prueba.center;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Clase que representa el consumidor del callcenter
 * @author Pablo Sarmiento
 *
 */
public class Telefono implements Runnable {
	/**
	 * Dispatcher para el manejo del callcenter
	 */
	private Dispatcher dispatcher;
	
	/**
	 * Empleado encargado de atender la llamada
	 */
	private Empleado empleado;
	
	/**
	 * Cyclicbarrier de control
	 */
	private CyclicBarrier cb;
	
	/**
	 * Modo para ejecucion de pruebas
	 */
	private int modo;
	
	/**
	 * Contructor de clase
	 * @param b dispatcher 
	 * @param pcb ciclycbarrier de control
	 */
	public Telefono( Dispatcher b , CyclicBarrier pcb, int pmodo){
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
		while ((empleado=dispatcher.dispatchCall( ))!= null ){
			System.out.println("Consumidor .......... recibiendo");
			try {
				procesar();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Acabo consumidor");
		try {
			cb.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (BrokenBarrierException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Procesa la llamada y cambia el estado del empleado
	 * @throws InterruptedException
	 */
	public void procesar() throws InterruptedException{
		Random ran = new Random();
		int x = ((ran.nextInt(6))+5)*1000;
		Thread.sleep(x);
		System.out.println("Consumidor .......... Procesando llamada " +empleado.getId() + " de tipo "+ empleado.getTipo());
		empleado.setEstado(true);
	}
}
