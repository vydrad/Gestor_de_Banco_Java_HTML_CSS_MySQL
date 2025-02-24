package Excepciones;

import java.io.IOException;

public class NombreInvalidoException extends IOException{

	private static final long serialVersionUID = 1L;
	
	public NombreInvalidoException() {
		
	}

	@Override
	public String getMessage() {
		
		return "Nombre inválido";
	}
	
	
}
