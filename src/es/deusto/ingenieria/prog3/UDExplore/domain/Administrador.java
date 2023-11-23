package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Administrador extends Usuario{
	
		int salario;
		
		
		public Administrador(String nomUsuario, String email, String contrasenya, int salario) {
			super(nomUsuario, email, contrasenya, 1);
			this.salario = salario;
		}

		public Administrador(String nomUsuario, String email, String contrasenya) {
			super(nomUsuario, email, contrasenya, 1);
		}

		
		public int getSalario() {
			return salario;
		}

		public void setSalario(int salario) {
			this.salario = salario;
		}


		@Override
		public String toString() {
			return "Administrador [salario=" + salario + "]";
		}
		
		
	}
