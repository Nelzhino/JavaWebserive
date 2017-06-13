/**
 * 
 */
package co.com.buscador.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import co.com.buscador.dto.BuscadorDto;

/**
 * @author nelson
 *
 */
public class BuscadorService {

	private static final String GOOGLE = "https://www.google.com.co/search";
	private static final String H3_A_HREF = "//h3/a/@href";
	private final static String URL_SEARCH = "/url?q=";

	public BuscadorDto getResultGoogle(BuscadorDto buscadorDto) {
		// emulamos un navegador web
		WebClient webClient = new WebClient();
		HtmlForm form = null;
		HtmlTextInput textField = null;
		HtmlSubmitInput button = null;
		final HtmlPage pageResultado; // La pagina que nos devolverá el resultado
		
		try {
			// Pagina donde se hara la consulta
			HtmlPage page1 = webClient.getPage(GOOGLE);

			// nombre del formulario
			form = page1.getFormByName("f");
			// el valor "f" no es arbitrario es el nombre del formulario web de
			// google

			// nombre de la caja de texto
			textField = form.getInputByName("q");
			// el valor "q" no es arbitrario es el nombre de la caja de texto
			// del formulario web de google

			// nombre del boton del formulario
			button = form.getInputByName("btnG");
			// el valor "btnG" no es arbitrario es el nombre del boton del
			// formulario web de google

			// llenamos la caja de texto
			textField.setValueAttribute(buscadorDto.getContenido());

			// hacemos click en el boton del formulario y asignamos el resultado
			// a la pagina pageResultado
			pageResultado = button.click();

			// Se obtiene las busquedas por medio de la url.
			@SuppressWarnings("unchecked")
			List<DomAttr> list = (List<DomAttr>) pageResultado.getByXPath(H3_A_HREF);
			// Recorro la opciones y valido que solo vengan las
			if (!list.isEmpty()) {
				List<String> urls = new ArrayList<String>();
				int cont = 0;
				for (DomAttr object : list) {
					String url = object.getValue();
					if (url.contains(URL_SEARCH) && cont!=3) {
						urls.add(url.substring(URL_SEARCH.length()));
						cont++;
					}
				}
				if(!urls.isEmpty()){
					buscadorDto.setUrlBusqueda(urls);
				}
			}
			// cerramos el navegador emulado, para liberar todo esto de la
			// memoria
			webClient.closeAllWindows();

		} catch (IOException ex) {
			Logger.getLogger(BuscadorService.class.getName()).log(Level.TRACE, null, ex);
			return null;
		} catch (FailingHttpStatusCodeException ex) {
			Logger.getLogger(BuscadorService.class.getName()).log(Level.TRACE, null, ex);
			return null;
		}

		return buscadorDto;
	}

}
