package Excepciones;

public class CorreoInvalidoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public CorreoInvalidoException() {
		
	}

	@Override
	public String getMessage() {
		
		return "Correo no válido";
	}
}
