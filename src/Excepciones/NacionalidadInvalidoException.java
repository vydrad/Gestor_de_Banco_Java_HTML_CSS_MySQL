package Excepciones;

import java.io.IOException;

public class NacionalidadInvalidoException extends IOException{
	
	private static final long serialVersionUID = 1L;
	
	public NacionalidadInvalidoException() {
		
	}

	@Override
	public String getMessage() {
		
		return "Nacionalidad no válida";
	}

}
