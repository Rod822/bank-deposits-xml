package ua.knu.bank.validation;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class XmlValidator {

    private static final Logger LOGGER =
            LogManager.getLogger(XmlValidator.class);

    // перевіряє XML-файл за XSD-схемою
    public boolean isValid(String xmlPath, String xsdPath) {
        SchemaFactory factory = SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            LOGGER.info("File {} is valid", xmlPath);
            return true;
        } catch (SAXException | IOException e) {
            LOGGER.error("File {} is not valid: {}", xmlPath,
                    e.getMessage());
            return false;
        }
    }
}
