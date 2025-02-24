package Excepciones;

import java.io.IOException;

public class ApellidoInvalidoException extends IOException{

	private static final long serialVersionUID = 1L;
	
	public ApellidoInvalidoException() {
		
	}

	@Override
	public String getMessage() {
		
		return "Apellido inválido";
	}
	
}
