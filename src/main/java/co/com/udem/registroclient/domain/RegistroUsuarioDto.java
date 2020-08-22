package co.com.udem.registroclient.domain;
import static java.util.Objects.requireNonNull;
import org.springframework.beans.factory.annotation.Autowired;


public class RegistroUsuarioDto {
	
	private Long idUsuario;
	private String nombres;
	private String apellidos;
    private String identificacion;
	private String direccion;
	private String telefono;
	private String email;
	private String password;
	
	@Autowired
	private TipoIdentificacionDto tipoIdentificacionDto;	

	public RegistroUsuarioDto(Long idUsuario, String nombres, String apellidos, String identificacion, String direccion,
			String telefono, String email, String password, TipoIdentificacionDto tipoIdentificacionDto) {

		this.idUsuario = idUsuario;
		this.nombres = requireNonNull(nombres);
		this.apellidos = apellidos;
		this.identificacion = requireNonNull(identificacion);
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = requireNonNull(email);
		this.password = requireNonNull(password);
		this.tipoIdentificacionDto = tipoIdentificacionDto;
	}


	public RegistroUsuarioDto() {
	
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getIdentificacion() {
		return identificacion;
	}


	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public TipoIdentificacionDto getTipoIdentificacionDto() {
		return tipoIdentificacionDto;
	}


	public void setTipoIdentificacionDto(TipoIdentificacionDto tipoIdentificacionDto) {
		this.tipoIdentificacionDto = tipoIdentificacionDto;
	}

	
	


}
