package ua.knu.bank.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class DepositSaxHandler extends DefaultHandler {

    private final DepositBuilder builder;
    private final StringBuilder text = new StringBuilder();

    public DepositSaxHandler(DepositBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) {
        if (qName.equals(DepositBuilder.DEPOSIT)) {
            builder.startDeposit(
                    attributes.getValue(DepositBuilder.ACCOUNT_ID));
        }
        text.setLength(0);
    }

    // текст елемента може прийти кількома шматками
    @Override
    public void characters(char[] ch, int start, int length) {
        text.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(DepositBuilder.DEPOSIT)) {
            builder.endDeposit();
        } else {
            builder.setField(qName, text.toString().trim());
        }
    }
}
