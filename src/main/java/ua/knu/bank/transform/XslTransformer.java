package ua.knu.bank.transform;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XslTransformer {

    private static final Logger LOGGER =
            LogManager.getLogger(XslTransformer.class);

    // перетворює XML за XSL-файлом і записує результат у outputPath
    public boolean transform(String xmlPath, String xslPath,
            String outputPath) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer =
                    factory.newTransformer(new StreamSource(new File(xslPath)));
            transformer.transform(new StreamSource(new File(xmlPath)),
                    new StreamResult(new File(outputPath)));
            LOGGER.info("File {} was transformed into {}", xmlPath,
                    outputPath);
            return true;
        } catch (TransformerException e) {
            LOGGER.error("Cannot transform {}: {}", xmlPath, e.getMessage());
            return false;
        }
    }
}
