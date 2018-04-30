package prueba.center;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * Clase de prueba callcenter
 * @author Pablo
 *
 */
public class AppTest 
{
	/**
	 * Cyclicbarrier de control
	 */
	private CyclicBarrier cb;

	/**
	 * Tamanio del buffer
	 */
	static    final    int    TAMANIO_BUFFER	=  10;

	/**
	 * Dispatcher de prueba
	 */
	private   Dispatcher    dispatcher;

	/**
	 * Manejador de empleados de  prueba
	 */
	private  ManejadorEmpleados manejadorEmpleados;

	/**
	 * total de empleados de prueba
	 */
	private int totalEmpleados;

	/**
	 * total clientes de prueba
	 */
	private int totalClientes;

	/**
	 * iniciailiza la prueba
	 * @param numOperararios numero de operarios
	 * @param numSupervisores numero de supervisores
	 * @param numDirectores numero de directores
	 * @param numClientes numero de clientes
	 */
	public void principal( int numOperararios, int numSupervisores, int numDirectores, int numClientes){
		totalClientes=numClientes;
		totalEmpleados=numOperararios+numSupervisores+numDirectores;
		manejadorEmpleados= new ManejadorEmpleados(numOperararios,numSupervisores,numDirectores);	
		dispatcher = new Dispatcher(totalClientes,TAMANIO_BUFFER, manejadorEmpleados);
	}

	/**
	 * Test que prueba 10 llamadas de forma concurrente y verifica que 
	 * terminan de la forma esperada
	 */
	@Test(timeout=60000)
	public void testEscenarioBase()  {

		cb= new CyclicBarrier(21);
		principal(3, 5, 2, 10); 
		ExecutorService executor = Executors.newFixedThreadPool(21);		   
		for ( int i = 0; i < totalEmpleados; i++ ){
			Telefono c =new Telefono( dispatcher,cb,1);
			executor.execute(c);
		}
		for ( int i = 0; i < totalClientes; i++ ){
			Cliente p= new Cliente(dispatcher,cb,1);
			executor.execute(p);
		}

		try {
			cb.await();
			cb.await();
			assertEquals(0, dispatcher.getnProductores());
			assertEquals(0, dispatcher.getLLamadas().size());

		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test que prueba 10 llamadas de forma secuencial rapida y verifica que se atiendan
	 * todos los clientes
	 */
	@Test(timeout=60000)
	public void testLlamadasenLineaBase() {
		cb= new CyclicBarrier(21);
		principal(5, 3, 2, 10); 
		ExecutorService executor = Executors.newFixedThreadPool(21);		   
		for ( int i = 0; i < totalEmpleados; i++ ){
			Telefono c =new Telefono( dispatcher,cb,0);
			executor.execute(c);
		}
		for ( int i = 0; i < totalClientes; i++ ){
			Cliente p= new Cliente(dispatcher,cb,0);
			executor.execute(p);
		}

		try {
			cb.await();
			assertEquals(0, dispatcher.getnProductores());
			assertEquals(0, dispatcher.getLLamadas().size());

		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test que prueba mas de 10 llamadas de forma concurrentes y verifica que
	 * se atiendan todos los clientes
	 */
	@Test(timeout=60000)
	public void testMayorNumeroLLamadas() {
		cb= new CyclicBarrier(31);
		principal(5, 3, 2, 20); 
		ExecutorService executor = Executors.newFixedThreadPool(31);		   
		for ( int i = 0; i < totalEmpleados; i++ ){
			Telefono c =new Telefono( dispatcher,cb,1);
			executor.execute(c);
		}
		for ( int i = 0; i < totalClientes; i++ ){
			Cliente p= new Cliente(dispatcher,cb,1);
			executor.execute(p);
		}

		try {
			cb.await();
			cb.await();
			assertEquals(0, dispatcher.getnProductores());
			assertEquals(0, dispatcher.getLLamadas().size());

		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Test que prueba mas de 10 llamadas de forma secuencial rapida y verifica que termine de 
	 * atender todos los clientes
	 */
	@Test(timeout=60000)
	public void testMayorLlamadasenLinea() {
		cb= new CyclicBarrier(31);
		principal(5, 3, 2, 20); 
		ExecutorService executor = Executors.newFixedThreadPool(31);		   
		for ( int i = 0; i < totalEmpleados; i++ ){
			Telefono c =new Telefono( dispatcher,cb,0);
			executor.execute(c);
		}
		for ( int i = 0; i < totalClientes; i++ ){
			Cliente p= new Cliente(dispatcher,cb,0);
			executor.execute(p);
		}
		try {
			cb.await();
			assertEquals(0, dispatcher.getnProductores());
			assertEquals(0, dispatcher.getLLamadas().size());

		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
