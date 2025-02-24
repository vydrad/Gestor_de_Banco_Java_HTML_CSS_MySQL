package Excepciones;

import java.io.IOException;

public class CuilInvalidoException extends IOException{

	private static final long serialVersionUID = 1L;
	
	public CuilInvalidoException() {
		
	}

	@Override
	public String getMessage() {
		
		return "CUIL no válido";
	}
}
