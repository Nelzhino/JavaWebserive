/**
 * 
 */
package co.com.buscador.dto;

import java.util.List;

/**
 * @author nelson
 *
 */
public class BuscadorDto {
	
	private String contenido;
	private List<String> urlBusqueda;
	
	/**
	 * @return the urlBusqueda
	 */
	public List<String> getUrlBusqueda() {
		return urlBusqueda;
	}

	/**
	 * @param urlBusqueda the urlBusqueda to set
	 */
	public void setUrlBusqueda(List<String> urlBusqueda) {
		this.urlBusqueda = urlBusqueda;
	}

	public BuscadorDto() {
		
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	
	
	
	
	
}
