package ua.knu.bank;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ua.knu.bank.parser.ParserType;

class BankApplicationTest {

    private static final String XSD = "src/main/resources/deposits.xsd";
    private static final String XSL = "src/main/resources/group-by-type.xsl";

    // файл, який записує main
    private final File mainOutput = new File("deposits-by-type.xml");

    @TempDir
    Path tempDir;

    @AfterEach
    void deleteMainOutput() {
        mainOutput.delete();
    }

    @Test
    void runWorksWithEveryParser() {
        for (ParserType type : ParserType.values()) {
            File output = tempDir.resolve(type + ".xml").toFile();
            BankApplication application = new BankApplication(
                    "src/main/resources/deposits.xml", XSD, XSL,
                    output.getPath());

            assertTrue(application.run(type));
            assertTrue(output.exists());
        }
    }

    @Test
    void runStopsWhenXmlIsNotValid() {
        File output = tempDir.resolve("result.xml").toFile();
        BankApplication application = new BankApplication(
                "src/test/resources/big-percent.xml", XSD, XSL,
                output.getPath());

        assertFalse(application.run(ParserType.SAX));
        assertFalse(output.exists());
    }

    @Test
    void mainWritesGroupedFile() {
        BankApplication.main(new String[0]);

        assertTrue(mainOutput.exists());
    }

    @Test
    void mainUsesChosenParser() {
        BankApplication.main(new String[] {"stax"});

        assertTrue(mainOutput.exists());
    }

    @Test
    void mainUsesSaxForUnknownParser() {
        BankApplication.main(new String[] {"json"});

        assertTrue(mainOutput.exists());
    }
}
