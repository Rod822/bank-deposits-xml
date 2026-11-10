package ua.knu.bank.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class XmlValidatorTest {

    private static final String XSD = "src/main/resources/deposits.xsd";
    private static final String TEST_DIR = "src/test/resources/";

    private final XmlValidator validator = new XmlValidator();

    @Test
    void correctFileIsValid() {
        assertTrue(validator.isValid(
                "src/main/resources/deposits.xml", XSD));
    }

    @Test
    void unknownTypeIsNotValid() {
        assertFalse(validator.isValid(TEST_DIR + "unknown-type.xml", XSD));
    }

    @Test
    void profitabilityOverHundredIsNotValid() {
        assertFalse(validator.isValid(TEST_DIR + "big-percent.xml", XSD));
    }

    @Test
    void missingElementIsNotValid() {
        assertFalse(validator.isValid(
                TEST_DIR + "missing-depositor.xml", XSD));
    }

    @Test
    void duplicateAccountIdIsNotValid() {
        assertFalse(validator.isValid(TEST_DIR + "duplicate-id.xml", XSD));
    }

    @Test
    void brokenXmlIsNotValid() {
        assertFalse(validator.isValid(TEST_DIR + "broken.xml", XSD));
    }

    @Test
    void missingFileIsNotValid() {
        assertFalse(validator.isValid(TEST_DIR + "no-such-file.xml", XSD));
    }

    @Test
    void missingSchemaIsNotValid() {
        assertFalse(validator.isValid(
                "src/main/resources/deposits.xml", "no-such-schema.xsd"));
    }
}
