import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateValidatorTest {
	public static final String VALID_CREATE_CHECKING_COMMAND_1 = "Create Checking 14565822 3";
	public static final String VALID_CREATE_CHECKING_COMMAND_2 = "CREATE CHECKING 14565822 3";
	public static final String VALID_CREATE_CHECKING_COMMAND_3 = "Create Checking 00000000 3";
	public static final String VALID_CREATE_CHECKING_COMMAND_4 = "Create Checking 14565822 0";
	public static final String VALID_CREATE_CHECKING_COMMAND_5 = "Create Checking 14565822 10";
	public static final String INVALID_CREATE_CHECKING_COMMAND_1 = "Create Checking 14565822 3 what";
	public static final String INVALID_CREATE_CHECKING_COMMAND_2 = "Create Checking 78 3";
	public static final String INVALID_CREATE_CHECKING_COMMAND_3 = "Create Checking -1456582 3";
	public static final String INVALID_CREATE_CHECKING_COMMAND_4 = "Create Checking 1456582.2 3";
	public static final String INVALID_CREATE_CHECKING_COMMAND_5 = "Create Checking 14565822 -1";
	public static final String INVALID_CREATE_CHECKING_COMMAND_6 = "Create Checking 14565822 11.4";
	CreateValidator createValidator;

	@BeforeEach
	public void setUp() {
		createValidator = new CreateValidator();
	}

	@Test
	public void validator_yields_array_of_correct_length_for_create_checking_command() {
		String[] actualArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_1);
		assertEquals(4, actualArray.length);
	}

	@Test
	public void validator_converts_input_command_string_to_all_lowercase_for_create_checking_command() {
		String[] expectedArray = { "create", "checking", "14565822", "3" };
		String[] actualArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_2);

		for (int i = 0; i < expectedArray.length; i++) {
			assertEquals(expectedArray[i], actualArray[i]);
		}
	}

	@Test
	public void validator_determines_that_command_with_4_elements_is_valid_for_create_checking_command() {
		String[] inputArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_1);
		assertTrue(createValidator.arrayHasFourArrayElements(inputArray));
	}

	@Test
	public void validator_determines_that_command_with_5_elements_is_invalid_for_create_checking_command() {
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_1);
		assertFalse(createValidator.arrayHasFourArrayElements(inputArray));
	}

	@Test
	public void validator_determines_3rd_element_of_14565822_is_valid_id_value_for_create_checking() {
		String[] inputArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_1);
		assertTrue(createValidator.idIsValid(inputArray[2]));
	}

	@Test
	public void validator_determines_3rd_element_of_00000000_is_valid_id_value_for_create_checking() {
		String[] inputArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_3);
		assertTrue(createValidator.idIsValid(inputArray[2]));
	}

	@Test
	public void validator_determines_3rd_element_of_78_is_invalid_id_value_for_create_checking() {
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_2);
		assertFalse(createValidator.idIsValid(inputArray[2]));
	}

	@Test
	public void validator_determines_3rd_element_with_negative_value_is_invalid_id_value_for_create_checking() {
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_3);
		assertFalse(createValidator.idIsValid(inputArray[2]));
	}

	@Test
	public void validator_determines_3rd_element_with_decimal_value_is_invalid_id_value_for_create_checking() {
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_4);
		assertFalse(createValidator.idIsValid(inputArray[2]));
	}

	@Test
	public void validator_determines_4th_element_of_3_is_valid_apr_value_for_create_checking() {
		String[] inputArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_1);
		assertTrue(createValidator.aprIsValid(inputArray[3]));
	}

	@Test
	public void validator_determines_4th_element_of_0_is_valid_apr_value_for_create_checking() {
		String[] inputArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_4);
		assertTrue(createValidator.aprIsValid(inputArray[3]));
	}

	@Test
	public void validator_determines_4th_element_of_10_is_valid_apr_value_for_create_checking() {
		String[] inputArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_5);
		assertTrue(createValidator.aprIsValid(inputArray[3]));
	}

	@Test
	public void validator_determines_4th_element_with_negative_value_is_invalid_apr_value_for_create_checking() {
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_5);
		assertFalse(createValidator.aprIsValid(inputArray[3]));
	}

	@Test
	public void validator_determines_4th_element_above_10_is_invalid_apr_value_for_create_checking() {
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_6);
		assertFalse(createValidator.aprIsValid(inputArray[3]));
	}

}
