package Excepciones;

import java.io.IOException;

public class CbuInvalidoException extends IOException {

	private static final long serialVersionUID = 1L;
	
	public CbuInvalidoException() {
		
	}
	
	public String getMessage() {		
		return "Cbu inválido";
	}
}

