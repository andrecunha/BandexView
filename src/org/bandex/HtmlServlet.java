package org.bandex;

import java.io.IOException;
import java.io.PrintWriter;

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
			factory.setValidating(false);
			factory.setNamespaceAware(true);

			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			schemaFactory.setErrorHandler(new SimpleErrorHandler(out));

			// TODO: Ver como tratar isso aqui.
			StreamSource source = new StreamSource(
					"file:///home/andre/workspace/BandexView/WebContent/restaurante.xsd");
			factory.setSchema(schemaFactory.newSchema(new Source[] { source }));

			SAXParser parser = factory.newSAXParser();

			XMLReader reader = parser.getXMLReader();
			// reader.setErrorHandler(null);

			// reader.parse("http://www.pcasc.usp.br/restaurante.xml");
			reader.parse("file:///home/andre/workspace/BandexView/WebContent/restaurante.xml");
			return true;
		} catch (Exception e) {
			e.printStackTrace(out);
			return false;
		}
	}

	public void toHTML(PrintWriter out, boolean all) {
		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();
			StreamSource XSLSource;
			if (all) {
				XSLSource = new StreamSource(
						"file:///home/andre/workspace/BandexView/WebContent/restaurante-tudo.xsl");
			} else {
				XSLSource = new StreamSource(
						"file:///home/andre/workspace/BandexView/WebContent/restaurante-sobremesa.xsl");
			}
			Transformer transformer = tFactory.newTransformer(XSLSource);

			/*
			 * StreamSource XMLSource = new StreamSource(
			 * "http://www.pcasc.usp.br/restaurante.xml");
			 */
			StreamSource XMLSource = new StreamSource(
					"file:///home/andre/workspace/BandexView/WebContent/restaurante.xml");

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
		if (all == null)
			toHTML(out, true);
		else if (all.compareTo("true") == 0)
			toHTML(out, true);
		else
			toHTML(out, false);
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
