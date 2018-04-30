package prueba.center;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Clase para probar la consulta de empleados disponibles
 * @author usuario
 *
 */
public class EmpleadoTest {

	/**
	 * Manejador de empleados
	 */
	private ManejadorEmpleados manejadorEmpleados;

	/**
	 *  Verifica que se retorne null si no hay empleados disponibles  
	 */
	@Test()
	public void testFindEmpleadoNull()  {
	   manejadorEmpleados= new ManejadorEmpleados(2,2,4);
	   
	   for (Empleado empleado : manejadorEmpleados.getEmpleados()) {
		empleado.setEstado(false);
	   }
	   Empleado ee=manejadorEmpleados.findEmpleado();
	   assertNull(ee);
	 }
	
	/**
	 *  Verifica que se retorne el primer empleado disponible  
	 */
	@Test()
	public void testFindEmpleadoOperario()  {
	   manejadorEmpleados= new ManejadorEmpleados(2,2,4);
	   Empleado ee=manejadorEmpleados.findEmpleado();
	   assertEquals(ee.getTipo(), "o");
	 }
	

	/**
	 *  Verifica que se retorne el primer empleado disponible  cuando no hay operarios
	 *  ni directores
	 */
	@Test()
	public void testFindEmpleadoSupervisor()  {
	   manejadorEmpleados= new ManejadorEmpleados(3,2,4);
	   for (Empleado empleado : manejadorEmpleados.getEmpleados()) {
			if (empleado.getTipo().equals("o")||empleado.getTipo().equals("d")) {
				   empleado.setEstado(false);
			}
		 }
	   Empleado ee=manejadorEmpleados.findEmpleado();
	   assertEquals(ee.getTipo(), "s");
	   
	 }
	
	/**
	 *  Verifica que se retorne el primer empleado disponible  cuando no hay operarios
	 *  ni supervisores
	 */
	@Test()
	public void testFindEmpleadoDirector()  {
		   manejadorEmpleados= new ManejadorEmpleados(3,2,4);
		   for (Empleado empleado : manejadorEmpleados.getEmpleados()) {
				if (empleado.getTipo().equals("o")||empleado.getTipo().equals("s")) {
					   empleado.setEstado(false);
				}
			 }
		   Empleado ee=manejadorEmpleados.findEmpleado();
		   assertEquals(ee.getTipo(), "d");
	 }
}
