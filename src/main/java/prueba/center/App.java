package prueba.center;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
	private    static    final    int    N_PRODUCTORES    =   15;
	private    static    final    int    TAMAÑO_BUFFER	=  15;
    private    static    Dispatcher    buffer;
	private    static  ManejadorEmpleados manejadorEmpleados;
	private static ArrayList<Empleado> empleados;

			public    static    void    main(String[]    args)    {
			manejadorEmpleados= new ManejadorEmpleados(10,10,10);
			buffer = new Dispatcher(N_PRODUCTORES,TAMAÑO_BUFFER, manejadorEmpleados);
			 ExecutorService executor = Executors.newFixedThreadPool(21);	
//			for ( int i = 0; i < 10; i++ ){
//				new Consumidor( buffer).start();}
//			for ( int i = 0; i < N_PRODUCTORES; i++ ){
//				new Productor( buffer, 1).start();}
			
			}
			
}
