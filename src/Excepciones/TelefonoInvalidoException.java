package Excepciones;

import java.io.IOException;

public class TelefonoInvalidoException extends IOException{

	private static final long serialVersionUID = 1L;
	
	public TelefonoInvalidoException() {
		
	}

	@Override
	public String getMessage() {
		
		return "Telefono inválido";
	}
	
}
