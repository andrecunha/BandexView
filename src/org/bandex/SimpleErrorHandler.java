/*
 * $Id: $
 *
 * The contents of this file are subject to the Mozilla Public License 
 * Version 1.1 (the "License"); you may not use this file except in 
 * compliance with the License. You may obtain a copy of the License at 
 * http://www.mozilla.org/MPL/ 
 *
 * Software distributed under the License is distributed on an "AS IS" basis, 
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License 
 * for the specific language governing rights and limitations under the License.
 *
 * The Original Code is XML Hammer code. (org.xmlhammer.*)
 *
 * The Initial Developer of the Original Code is Edwin Dankert. Portions created 
 * by the Initial Developer are Copyright (C) 2005 - 2006 the Initial Developer. 
 * All Rights Reserved.
 *
 * Contributor(s): Edwin Dankert <edankert@gmail.com>
 */
package org.bandex;

import java.io.PrintWriter;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/**
 * A simple error handler for SAX, that simply prints out
 * the errors.
 */
public class SimpleErrorHandler implements ErrorHandler {
	
	/**
	 * The PrintWriter where the errors will be printed to.
	 */
	private PrintWriter out;
	
	public SimpleErrorHandler (PrintWriter out) {
		this.out = out;
	}
	
    public void warning(SAXParseException e) {
        e.printStackTrace(out);
    }

    public void error(SAXParseException e) {
    	e.printStackTrace(out);
    }

    public void fatalError(SAXParseException e) {
    	e.printStackTrace(out);
    }
}