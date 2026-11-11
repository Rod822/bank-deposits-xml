package ua.knu.bank.transform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class XslTransformerTest {

    private static final String XML = "src/main/resources/deposits.xml";
    private static final String XSL = "src/main/resources/group-by-type.xsl";

    private final XslTransformer transformer = new XslTransformer();

    @TempDir
    Path tempDir;

    @Test
    void depositsAreGroupedByType() throws Exception {
        String output = tempDir.resolve("result.xml").toString();

        assertTrue(transformer.transform(XML, XSL, output));

        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(new File(output));
        Element bank = document.getDocumentElement();
        assertEquals("bank", bank.getTagName());
        // 6 груп за типами, а вкладів так само 16
        int groups = 0;
        NodeList children = bank.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                groups++;
            }
        }
        assertEquals(6, groups);
        assertEquals(16, bank.getElementsByTagName("deposit").getLength());

        NodeList term = bank.getElementsByTagName("term");
        assertEquals(1, term.getLength());
        NodeList termDeposits =
                ((Element) term.item(0)).getElementsByTagName("type");
        assertEquals(5, termDeposits.getLength());
        for (int i = 0; i < termDeposits.getLength(); i++) {
            assertEquals("term", termDeposits.item(i).getTextContent());
        }
    }

    @Test
    void missingXslFileGivesFalse() {
        String output = tempDir.resolve("result.xml").toString();

        assertFalse(transformer.transform(XML, "no-such-file.xsl", output));
    }

    @Test
    void brokenXmlGivesFalse() {
        String output = tempDir.resolve("result.xml").toString();

        assertFalse(transformer.transform(
                "src/test/resources/broken.xml", XSL, output));
    }
}
