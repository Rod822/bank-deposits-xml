package ua.knu.bank.parser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomDepositParser extends DepositParser {

    @Override
    protected void readFile(String xmlPath, DepositBuilder builder)
            throws Exception {
        DocumentBuilder documentBuilder =
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(new File(xmlPath));

        NodeList deposits =
                document.getElementsByTagName(DepositBuilder.DEPOSIT);
        for (int i = 0; i < deposits.getLength(); i++) {
            Element deposit = (Element) deposits.item(i);
            builder.startDeposit(
                    deposit.getAttribute(DepositBuilder.ACCOUNT_ID));

            // пропускаємо пробіли між тегами, беремо тільки елементи
            NodeList fields = deposit.getChildNodes();
            for (int j = 0; j < fields.getLength(); j++) {
                Node field = fields.item(j);
                if (field.getNodeType() == Node.ELEMENT_NODE) {
                    builder.setField(field.getNodeName(),
                            field.getTextContent().trim());
                }
            }
            builder.endDeposit();
        }
    }
}
