package org.bandex;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 * Servlet implementation class PDFServlet
 */
@WebServlet("/cardapio.pdf")
public class PdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PdfServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void toPDF(OutputStream out, boolean all) {
		try {
			System.out.println("Preparing...");

			// Setup input and output files
			URL xmlfile = new URL("http://www.pcasc.usp.br/restaurante.xml");
			/*URL xmlfile = new URL(
					"file:///home/andre/workspace/BandexView/WebContent/restaurante.xml");
			*/
			File xsltfile;
			if (all) {
				xsltfile = new File(
						"/home/andre/workspace/BandexView/WebContent/restaurante-fo-tudo.xsl");
			} else {
				xsltfile = new File(
						"/home/andre/workspace/BandexView/WebContent/restaurante-fo-sobremesa.xsl");
			}

			System.out.println("Input: XML (" + xmlfile + ")");
			System.out.println("Stylesheet: " + xsltfile);
			System.out.println();
			System.out.println("Transforming...");

			// configure fopFactory as desired
			FopFactory fopFactory = FopFactory.newInstance();

			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			// configure foUserAgent as desired

			// Setup output
			// OutputStream out = new java.io.FileOutputStream(pdffile);
			// out = new java.io.BufferedOutputStream(out);

			try {
				// Construct fop with desired output format
				Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF,
						foUserAgent, out);

				// Setup XSLT
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory
						.newTransformer(new StreamSource(xsltfile));

				// Set the value of a <param> in the stylesheet
				// transformer.setParameter("versionParam", "2.0");

				// Setup input for XSLT transformation
				Source src = new StreamSource(xmlfile.openStream());

				// Resulting SAX events (the generated FO) must be piped through
				// to FOP
				Result res = new SAXResult(fop.getDefaultHandler());

				// Start XSLT transformation and FOP processing
				transformer.transform(src, res);
			} finally {
				out.close();
			}

			System.out.println("Success!");
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OutputStream out = response.getOutputStream();
		String all = request.getParameter("all");
		if (all == null)
			toPDF(out, true);
		else if (all.compareTo("true") == 0)
			toPDF(out, true);
		else
			toPDF(out, false);
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
