package com.isl.gbm.rbibond.externalservice.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class XmlFormatter {

    private static final Logger logger = LoggerFactory.getLogger(XmlFormatter.class);

    public static void prettyFormat(String xml) {
        try {
            // Create a new transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Set the output properties to make the XML pretty
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // Prepare the input and output streams
            StringReader stringReader = new StringReader(xml);
            StringWriter stringWriter = new StringWriter();

            // Perform the transformation (pretty-printing)
            transformer.transform(new StreamSource(stringReader), new StreamResult(stringWriter));

            // Get the formatted XML string
            String formattedXml = stringWriter.toString();
            //logger.info("Formatted XML:\n{}", formattedXml);

        } catch (TransformerException e) {
            logger.warn("Exception occurred: ", e);
        }
    }
}
