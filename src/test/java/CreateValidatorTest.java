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
	Bank bank;

	@BeforeEach
	public void setUp() {
		createValidator = new CreateValidator();
		bank = new Bank();
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
		assertTrue(createValidator.arrayHasFourElements(inputArray));
	}

	@Test
	public void validator_determines_that_command_with_5_elements_is_invalid_for_create_checking_command() {
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_1);
		assertFalse(createValidator.arrayHasFourElements(inputArray));
	}

	@Test
	public void validator_determines_3rd_element_of_14565822_is_valid_id_value_for_create_checking_with_empty_bank() {
		Bank bank = new Bank();
		String[] inputArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_1);
		assertTrue(createValidator.idIsValid(inputArray[2], bank));
	}

	@Test
	public void validator_determines_3rd_element_of_00000000_is_valid_id_value_for_create_checking_with_empty_bank() {
		Bank bank = new Bank();
		String[] inputArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_3);
		assertTrue(createValidator.idIsValid(inputArray[2], bank));
	}

	@Test
	public void validator_determines_3rd_element_of_14565822_is_invalid_id_value_for_bank_with_account_with_same_id() {
		Bank bank = new Bank();
		bank.addAccount("Checking", 14565822, 3, 0);
		String[] inputArray = createValidator.parse(VALID_CREATE_CHECKING_COMMAND_1);
		assertFalse(createValidator.idIsValid(inputArray[2], bank));
	}

	@Test
	public void validator_determines_3rd_element_of_78_is_invalid_id_value_for_create_checking() {
		Bank bank = new Bank();
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_2);
		assertFalse(createValidator.idIsValid(inputArray[2], bank));
	}

	@Test
	public void validator_determines_3rd_element_with_negative_value_is_invalid_id_value_for_create_checking() {
		Bank bank = new Bank();
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_3);
		assertFalse(createValidator.idIsValid(inputArray[2], bank));
	}

	@Test
	public void validator_determines_3rd_element_with_decimal_value_is_invalid_id_value_for_create_checking() {
		Bank bank = new Bank();
		String[] inputArray = createValidator.parse(INVALID_CREATE_CHECKING_COMMAND_4);
		assertFalse(createValidator.idIsValid(inputArray[2], bank));
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

	@Test
	public void Create_Checking_14565822_3_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 3", bank));
	}

	@Test
	public void Create_Checking_14565822_3_point_0_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 3.0", bank));
	}

	@Test
	public void create_checking_14565822_3_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("create checking 14565822 3", bank));
	}

	@Test
	public void create_Checking_14565822_3_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("create Checking 14565822 3", bank));
	}

	@Test
	public void CREATE_CHECKING_14565822_3_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("CREATE CHECKING 14565822 3", bank));
	}

	@Test
	public void Create_Checking_14565822_0_point_2_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 0.2", bank));
	}

	@Test
	public void Create_Checking_14565822_point_2_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 .2", bank));
	}

	@Test
	public void Create_Checking_14565822_0_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 0", bank));
	}

	@Test
	public void Create_Checking_14565822_0_point_0_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 0.0", bank));
	}

	@Test
	public void Create_Checking_14565822_10_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 10", bank));
	}

	@Test
	public void Create_Checking_14565822_10_point_0_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 10.0", bank));
	}

	@Test
	public void Create_Checking_14565822_3_is_valid_for_bank_with_existing_checking_account_with_different_id() {
		bank.addAccount("Checking", 12345678, 3, 0);
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 3", bank));
	}

	@Test
	public void Create_Checking_14565822_3_is_valid_for_bank_with_existing_savings_account_with_different_id() {
		bank.addAccount("Checking", 12345678, 3, 0);
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 3", bank));
	}

	@Test
	public void Create_Checking_14565822_3_is_valid_for_bank_with_existing_cd_account_with_different_id() {
		bank.addAccount("Cd", 12345678, 3, 100);
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 3", bank));
	}

	@Test
	public void Create_Checking_14565822_3_is_valid_for_bank_with_multiple_existing_accounts_with_different_ids() {
		bank.addAccount("Checking", 12345678, 3, 0);
		bank.addAccount("Checking", 87654321, 3, 0);
		bank.addAccount("Savings", 14785991, 5, 0);
		bank.addAccount("Cd", 78649221, 7, 100);
		assertTrue(createValidator.commandIsValid("Create Checking 14565822 3", bank));
	}

	@Test
	public void Create_Checking_14565822_10_point_00001_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Checking 14565822 10.00001", bank));
	}

	@Test
	public void Create_Checking_14565822_negative_19_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Checking 14565822 -19", bank));
	}

	@Test
	public void Create_Checking_78_3_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Checking 78 3", bank));
	}

	@Test
	public void Create_Checking_145658234_3_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Checking 145658234 3", bank));
	}

	@Test
	public void Create_Checking_145658_point_22_3_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Checking 145658.22 3", bank));
	}

	@Test
	public void Create_14565822_3_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create 145658234 3", bank));
	}

	@Test
	public void Create_Checking_14565822_3_100_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Checking 145658234 3 100", bank));
	}

	@Test
	public void Create_Checking_onefourfivesixfiveeighttwotwo_3_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Checking onefourfivesixfiveeighttwotwo 3", bank));
	}

	@Test
	public void Create__Checking__14565822__3_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create  Checking  14565822  3", bank));
	}

	@Test
	public void CreateChecking145658223_is_invalid() {
		assertFalse(createValidator.commandIsValid("CreateChecking145658223", bank));
	}

	@Test
	public void Create_Checking_14565822_3_is_invalid_for_bank_with_account_with_same_id() {
		bank.addAccount("Checking", 14565822, 3, 0);
		assertFalse(createValidator.commandIsValid("Create Checking 14565822 3", bank));
	}

	@Test
	public void Create_Savings_77445566_4_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Savings 77445566 4", bank));
	}

	@Test
	public void CREATE_SAVINGS_77445566_4_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("CREATE SAVINGS 77445566 4", bank));
	}

	@Test
	public void create_savings_77445566_4_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("create savings 77445566 4", bank));
	}

	@Test
	public void Create_Savings_77445566_4_point_0_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Savings 77445566 4.0", bank));
	}

	@Test
	public void Create_Savings_77445566_0_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Savings 77445566 0", bank));
	}

	@Test
	public void Create_Savings_77445566_10_is_valid_for_empty_bank() {
		assertTrue(createValidator.commandIsValid("Create Savings 77445566 10", bank));
	}

	@Test
	public void Create_Savings_77445566_4_is_valid_for_bank_with_existing_checking_account_with_different_id() {
		bank.addAccount("Checking", 12345678, 3, 0);
		assertTrue(createValidator.commandIsValid("Create Savings 77445566 4", bank));
	}

	@Test
	public void Create_Savings_77445566_4_is_valid_for_bank_with_existing_savings_account_with_different_id() {
		bank.addAccount("Savings", 12345678, 3, 0);
		assertTrue(createValidator.commandIsValid("Create Savings 77445566 4", bank));
	}

	@Test
	public void Create_Savings_77445566_4_is_valid_for_bank_with_existing_cd_account_with_different_id() {
		bank.addAccount("Cd", 12345678, 3, 100);
		assertTrue(createValidator.commandIsValid("Create Savings 77445566 4", bank));
	}

	@Test
	public void Create_Savings_77445566_4_is_valid_for_bank_with_multiple_existing_accounts_with_different_ids() {
		bank.addAccount("Checking", 12345678, 3, 0);
		bank.addAccount("Checking", 87654321, 3, 0);
		bank.addAccount("Savings", 14785991, 5, 0);
		bank.addAccount("Cd", 78649221, 7, 100);
		assertTrue(createValidator.commandIsValid("Create Savings 77445566 4", bank));
	}

	@Test
	public void Create_Savings_77445566_10_point_00001_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Savings 77445566 10.00001", bank));
	}

	@Test
	public void Create_Savings_77445566_negative_10_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Savings 77445566 -10", bank));
	}

	@Test
	public void Create_Savings_77_10_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Savings 77 4", bank));
	}

	@Test
	public void Create_Savings_774455669_4_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Savings 774455669 4", bank));
	}

	@Test
	public void Create_Savings_7744_point_5566_4_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Savings 7744.5566 10.00001", bank));
	}

	@Test
	public void Create_Savings_77445566_4_5000_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create Savings 77445566 10 5000", bank));
	}

	@Test
	public void _77445566_10_Create_Savings_is_invalid() {
		assertFalse(createValidator.commandIsValid("77445566 10 Create Savings", bank));
	}

	@Test
	public void Savings_is_invalid() {
		assertFalse(createValidator.commandIsValid("Savings", bank));
	}

	@Test
	public void Create__Savings__77445566__4_is_invalid() {
		assertFalse(createValidator.commandIsValid("Create_Savings_77445566_4", bank));
	}

	@Test
	public void Create_Savings_77445566_4_is_invalid_for_bank_with_account_with_same_id() {
		bank.addAccount("Savings", 77445566, 4, 0);
		assertFalse(createValidator.commandIsValid("Create Savings 77445566 4", bank));
	}
}
