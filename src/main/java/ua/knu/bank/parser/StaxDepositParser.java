package ua.knu.bank.parser;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class StaxDepositParser extends DepositParser {

    @Override
    protected void readFile(String xmlPath, DepositBuilder builder)
            throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try (InputStream input = new FileInputStream(xmlPath)) {
            XMLStreamReader reader = factory.createXMLStreamReader(input);
            try {
                readDeposits(reader, builder);
            } finally {
                reader.close();
            }
        }
    }

    private void readDeposits(XMLStreamReader reader, DepositBuilder builder)
            throws Exception {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                String element = reader.getLocalName();
                if (element.equals(DepositBuilder.DEPOSIT)) {
                    builder.startDeposit(reader.getAttributeValue(null,
                            DepositBuilder.ACCOUNT_ID));
                } else if (!element.equals("bank")) {
                    // getElementText читає текст до закриваючого тегу
                    builder.setField(element, reader.getElementText().trim());
                }
            } else if (event == XMLStreamConstants.END_ELEMENT
                    && reader.getLocalName().equals(DepositBuilder.DEPOSIT)) {
                builder.endDeposit();
            }
        }
    }
}
