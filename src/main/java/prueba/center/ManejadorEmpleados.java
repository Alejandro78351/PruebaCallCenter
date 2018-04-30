package prueba.center;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que administra los empleados del call center
 * @author Pablo Sarmiento
 *
 */
public class ManejadorEmpleados {
	
	/**
	 * Arreglo de empleados del callcenter
	 */
	private Empleado[] empleados;
	
	/**
	 * numero de operarios
	 */
	private int numOperarios;
	
	/**
	 * numero de supervisores
	 */
	private int numSupervisores;
	
	/**
	 * numero de directores
	 */
	private int numDirectores;
	
	/**
	 * Constructor de la clase
	 * @param pnumOperararios numero de operarios
	 * @param pnumSupervisores numero de supervisores
	 * @param pnumDirectores numero de directores
	 */
	public ManejadorEmpleados(int pnumOperararios, int pnumSupervisores, int pnumDirectores) {
		empleados= new Empleado[pnumOperararios+pnumSupervisores+pnumDirectores];
		numOperarios=pnumOperararios;
		numSupervisores=pnumSupervisores;
		numDirectores=pnumDirectores;
		crearEmpleados();
	}
	
	/**
	 * Busca al primer empleado disponible
	 * @return primer empleado disponible
	 */
	public  Empleado  findEmpleado(){
		
		for (int i = 0; i < empleados.length; i++) {
			Empleado empleado =empleados[i];
			if (empleado.isEstado()==true) {
				return empleado;
			}
		}
		return null;
		
	}
	
	
	/**
	 * Crea lista de empleado como disponibles
	 * @param numOperararios numero de operarios
	 * @param numSupervisores numero de supervisores
	 * @param numDirectores numero de directores
	 */
	   public  void crearEmpleados(){
			for (int i = 0; i < numOperarios; i++) {
				Empleado em= new Empleado("o", i);
				System.out.println("Creando empleado " + em.getTipo() +" y estaccc " + em.isEstado()+" id " + em.getId());
				empleados[i]=em;
			}
			for (int i = numOperarios; i < (numOperarios+numSupervisores); i++) {
				Empleado em= new Empleado("s", i);
				System.out.println("Creando empleado " + em.getTipo() +" y estawww " + em.isEstado()+" id " + em.getId());
				empleados[i]=em;
			}
			for (int i = (numOperarios+numSupervisores); i <(numOperarios+numSupervisores+numDirectores) ; i++) {
				Empleado em= new Empleado("d", i);
				System.out.println("Creando empleado " + em.getTipo() +" y esta... " + em.isEstado()+" id " + em.getId());
				empleados[i]=em;
			}
		}

	/**
	 * @return the empleados
	 */
	public Empleado[] getEmpleados() {
		return empleados;
	}

	/**
	 * @param empleados the empleados to set
	 */
	public void setEmpleados(Empleado[] empleados) {
		this.empleados = empleados;
	}
	   
}
