package ua.knu.bank.parser;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxDepositParser extends DepositParser {

    @Override
    protected void readFile(String xmlPath, DepositBuilder builder)
            throws Exception {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        parser.parse(new File(xmlPath), new DepositSaxHandler(builder));
    }
}
