package org.bandex;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.XMLReader;

/**
 * Servlet implementation class Main
 */
@WebServlet("/cardapio.html")
public class HtmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HtmlServlet() {
		super();
	}

	protected boolean isValid(PrintWriter out) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(true);

			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			schemaFactory.setErrorHandler(new SimpleErrorHandler(out));

			String xsdfile = "/restaurante.xsd";

			ServletContext context = getServletContext();

			InputStream is = context.getResourceAsStream(xsdfile);

			StreamSource source = new StreamSource(is);

			factory.setSchema(schemaFactory.newSchema(new Source[] { source }));

			SAXParser parser = factory.newSAXParser();

			XMLReader reader = parser.getXMLReader();
			// reader.setErrorHandler(null);

			reader.parse("http://www.pcasc.usp.br/restaurante.xml");
			// reader.parse("file:///home/andre/workspace/BandexView/WebContent/restaurante.xml");
			return true;
		} catch (Exception e) {
			e.printStackTrace(out);
			return false;
		}
	}

	public void toHTML(PrintWriter out, boolean all, boolean mobile) {
		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();
			StreamSource XSLSource;
			String xslfile;
			if (all) {
				if (mobile)
					xslfile = "/restaurante-tudo-mobile.xsl";
				else
					xslfile = "/restaurante-tudo.xsl";
			} else {
				if (mobile)
					xslfile = "/restaurante-sobremesa-mobile.xsl";
				else
					xslfile = "/restaurante-sobremesa.xsl";
			}

			ServletContext context = getServletContext();

			InputStream is = context.getResourceAsStream(xslfile);

			XSLSource = new StreamSource(is);

			Transformer transformer = tFactory.newTransformer(XSLSource);

			StreamSource XMLSource = new StreamSource(
					"http://www.pcasc.usp.br/restaurante.xml");

			StreamResult result = new StreamResult(out);

			transformer.transform(XMLSource, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String all = request.getParameter("all");
		boolean mobile = Detector.isMobileBrowser(request);
		if (all == null)
			toHTML(out, true, mobile);
		else if (all.compareTo("true") == 0)
			toHTML(out, true, mobile);
		else
			toHTML(out, false, mobile);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
