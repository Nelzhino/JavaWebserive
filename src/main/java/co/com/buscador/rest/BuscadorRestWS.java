/**
 * 
 */
package co.com.buscador.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.buscador.dto.BuscadorDto;
import co.com.buscador.service.BuscadorService;

/**
 * @author nelson
 *
 */

@Path("/buscador")
public class BuscadorRestWS {

	private final String UTF8 = ";charset=utf-8";
	
	
	@POST
	@Path("/getListado")
	@Consumes(MediaType.APPLICATION_JSON + UTF8)
	@Produces(MediaType.APPLICATION_JSON + UTF8)
	public BuscadorDto getResultSearchGoogle(BuscadorDto buscadorDto){
		BuscadorService buscadorService = new BuscadorService();
		
		return buscadorService.getResultGoogle(buscadorDto);
	}
	
	@GET
	@Path("/holamundo")
	public void holaMundo(){
		System.out.println("dsalksjdlksa");
	}
	
}
