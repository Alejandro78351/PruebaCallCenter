package prueba.center;

/**
 * Empleado del callcenter
 * @author Pablo Sarmiento
 *
 */
public  class Empleado {
	
	/**
	 * Tipo de empleado (Operario , Supervisor, Director)
	 */
	private String tipo;
	
	/**
	 * Indica si el empleado se encuentra disponible para atender una llamada
	 */
	private boolean estado;
	
	/**
	 * Identificacion del empleado
	 */
	private int id;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Empleado(String t, int ii) {
		tipo =t;
		estado=true;
		id=ii;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the estado
	 */
	public synchronized boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public synchronized void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
