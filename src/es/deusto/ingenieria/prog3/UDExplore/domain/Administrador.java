package es.deusto.ingenieria.prog3.UDExplore.domain;

public class Administrador extends Usuario{
	
		private float salario;
		
		
		public Administrador(int codigoUsuario, String nombreUsuario, String apellido, String correoElectronico,
				String contrasenya, float salario) {
			super(codigoUsuario, nombreUsuario, apellido, correoElectronico, contrasenya);
			this.salario = salario;
		}

		public float getSalario() {
			return salario;
		}

		public void setSalario(float salario) {
			this.salario = salario;
		}


		@Override
		public String toString() {
			return "Administrador [salario=" + salario + "]";
		}
		
		
	}
