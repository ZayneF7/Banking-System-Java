package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawValidatorTest {
	public static final String VALID_WITHDRAW_COMMAND_1 = "Withdraw 12345678 10";
	public static final String VALID_WITHDRAW_COMMAND_2 = "WITHDRAW 12345678 10";
	Bank bank;
	WithdrawValidator withdrawValidator;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		withdrawValidator = new WithdrawValidator(bank);
	}

	@Test
	public void validator_accepts_0_as_a_valid_withdraw_amount_for_checking_account() {
		assertTrue(withdrawValidator.validWithdrawForChecking("0"));
	}

	@Test
	public void validator_accepts_0_point_0_as_a_valid_withdraw_amount_for_checking_account() {
		assertTrue(withdrawValidator.validWithdrawForChecking("0.0"));
	}

	@Test
	public void validator_accepts_1_as_a_valid_withdraw_amount_for_checking_account() {
		assertTrue(withdrawValidator.validWithdrawForChecking("1"));
	}

	@Test
	public void validator_accepts_400_as_a_valid_withdraw_amount_for_checking_account() {
		assertTrue(withdrawValidator.validWithdrawForChecking("400"));
	}

	@Test
	public void validator_accepts_400_point_0_as_a_valid_withdraw_amount_for_checking_account() {
		assertTrue(withdrawValidator.validWithdrawForChecking("400.0"));
	}

	@Test
	public void validator_accepts_399_as_a_valid_withdraw_amount_for_checking_account() {
		assertTrue(withdrawValidator.validWithdrawForChecking("399"));
	}

	@Test
	public void validator_accepts_50_point_5_as_a_valid_withdraw_amount_for_checking_account() {
		assertTrue(withdrawValidator.validWithdrawForChecking("50.5"));
	}

	@Test
	public void validator_takes_400_point_1_as_an_invalid_withdraw_amount_for_checking_account() {
		assertFalse(withdrawValidator.validWithdrawForChecking("400.1"));
	}

	@Test
	public void validator_takes_401_as_an_invalid_withdraw_amount_for_checking_account() {
		assertFalse(withdrawValidator.validWithdrawForChecking("401"));
	}

	@Test
	public void validator_takes_neg_1_an_invalid_withdraw_amount_for_checking_account() {
		assertFalse(withdrawValidator.validWithdrawForChecking("-1"));
	}

	@Test
	public void validator_takes_fourhundred_as_an_invalid_withdraw_amount_for_checking_account() {
		assertFalse(withdrawValidator.validWithdrawForChecking("fourhundred"));
	}

	@Test
	public void validator_accepts_0_as_a_valid_withdraw_amount_for_savings_account() {
		assertTrue(withdrawValidator.validWithdrawForSavings("0"));
	}

	@Test
	public void validator_accepts_0_point_0_as_a_valid_withdraw_amount_for_savings_account() {
		assertTrue(withdrawValidator.validWithdrawForSavings("0.0"));
	}

	@Test
	public void validator_accepts_1_as_a_valid_withdraw_amount_for_savings_account() {
		assertTrue(withdrawValidator.validWithdrawForSavings("1"));
	}

	@Test
	public void validator_accepts_1000_as_a_valid_withdraw_amount_for_checking_account() {
		assertTrue(withdrawValidator.validWithdrawForSavings("1000"));
	}

	@Test
	public void validator_accepts_1000_point_0_as_a_valid_withdraw_amount_for_savings_account() {
		assertTrue(withdrawValidator.validWithdrawForSavings("1000.0"));
	}

	@Test
	public void validator_accepts_999_as_a_valid_withdraw_amount_for_savings_account() {
		assertTrue(withdrawValidator.validWithdrawForSavings("999"));
	}

	@Test
	public void validator_accepts_555_point_5_as_a_valid_withdraw_amount_for_savings_account() {
		assertTrue(withdrawValidator.validWithdrawForSavings("555.5"));
	}

	@Test
	public void validator_takes_1000_point_1_as_an_invalid_withdraw_amount_for_savings_account() {
		assertFalse(withdrawValidator.validWithdrawForSavings("1000.1"));
	}

	@Test
	public void validator_takes_1001_as_an_invalid_withdraw_amount_for_savings_account() {
		assertFalse(withdrawValidator.validWithdrawForSavings("1001"));
	}

	@Test
	public void validator_takes_neg_1_an_invalid_withdraw_amount_for_savings_account() {
		assertFalse(withdrawValidator.validWithdrawForSavings("-1"));
	}

	@Test
	public void validator_takes_onethousand_as_an_invalid_withdraw_amount_for_savings_account() {
		assertFalse(withdrawValidator.validWithdrawForSavings("onethousand"));
	}

	@Test
	public void validator_accepts_0_as_a_valid_withdraw_amount_for_cd_account_with_0_balance() {
		bank.addAccount("cd", 12345678, 3.0, 0);
		assertTrue(withdrawValidator.validWithdrawForCd("12345678", "0"));
	}

	@Test
	public void validator_accepts_400_as_a_valid_withdraw_amount_for_cd_account_with_0_balance() {
		bank.addAccount("cd", 12345678, 3.0, 0);
		assertTrue(withdrawValidator.validWithdrawForCd("12345678", "400"));
	}

	@Test
	public void validator_accepts_400_as_a_valid_withdraw_amount_for_cd_account_with_400_balance() {
		bank.addAccount("cd", 12345678, 3.0, 400);
		assertTrue(withdrawValidator.validWithdrawForCd("12345678", "400"));
	}

	@Test
	public void validator_accepts_800_point_5_as_a_valid_withdraw_amount_for_cd_account_with_400_balance() {
		bank.addAccount("cd", 12345678, 3.0, 400);
		assertTrue(withdrawValidator.validWithdrawForCd("12345678", "800.5"));
	}

	@Test
	public void validator_takes_0_as_an_invalid_withdraw_amount_for_cd_account_with_400_balance() {
		bank.addAccount("cd", 12345678, 3.0, 400);
		assertFalse(withdrawValidator.validWithdrawForCd("12345678", "0"));
	}

	@Test
	public void Withdraw_12345678_0_is_valid_for_checking_account_with_same_id_0_balance_and_0_months() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertTrue(withdrawValidator.commandIsValid("Withdraw 12345678 0"));
	}

	@Test
	public void Withdraw_12345678_0___is_valid_for_checking_account_with_same_id_0_balance_and_0_months() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertTrue(withdrawValidator.commandIsValid("Withdraw 12345678 0  "));
	}

	@Test
	public void WITHDRAW_12345678_0_is_valid_for_checking_account_with_same_id_0_balance_and_0_months() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertTrue(withdrawValidator.commandIsValid("WITHDRAW 12345678 0"));
	}

	@Test
	public void withdraw_12345678_0_is_valid_for_checking_account_with_same_id_100_balance_and_0_months() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 0"));
	}

	@Test
	public void withdraw_12345678_100_is_valid_for_checking_account_with_same_id_100_balance_and_0_months() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 100"));
	}

	@Test
	public void withdraw_12345678_400_is_valid_for_checking_account_with_same_id_100_balance_and_0_months() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 400"));
	}

	@Test
	public void withdraw_12345678_400_is_valid_for_checking_account_with_same_id_100_balance_and_10_months() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		bank.getAccounts().get(12345678).increaseNumOfMonths(10);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 400"));
	}

	@Test
	public void withdraw_12345678_0_is_valid_for_savings_account_with_same_id_100_balance_and_1_month() {
		bank.addAccount("savings", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		bank.getAccounts().get(12345678).increaseNumOfMonths(1);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 0"));
	}

	@Test
	public void withdraw_12345678_1000_is_valid_for_savings_account_with_same_id_1500_balance_and_1_month() {
		bank.addAccount("savings", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(1500);
		bank.getAccounts().get(12345678).increaseNumOfMonths(1);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 1000"));
	}

	@Test
	public void withdraw_12345678_1000_is_valid_for_savings_account_with_same_id_0_balance_and_1_month() {
		bank.addAccount("savings", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).increaseNumOfMonths(1);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 1000"));
	}

	@Test
	public void withdraw_12345678_0_is_valid_for_cd_account_with_same_id_0_balance_and_12_months() {
		bank.addAccount("cd", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).increaseNumOfMonths(12);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 0"));
	}

	@Test
	public void withdraw_12345678_100_is_valid_for_cd_account_with_same_id_0_balance_and_12_months() {
		bank.addAccount("cd", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).increaseNumOfMonths(12);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 100"));
	}

	@Test
	public void withdraw_12345678_1000_is_valid_for_cd_account_with_same_id_100_balance_and_12_months() {
		bank.addAccount("cd", 12345678, 3.0, 100);
		bank.getAccounts().get(12345678).increaseNumOfMonths(12);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 1000"));
	}

	@Test
	public void withdraw_12345678_1000_is_valid_for_cd_account_with_same_id_100_balance_and_20_months() {
		bank.addAccount("cd", 12345678, 3.0, 100);
		bank.getAccounts().get(12345678).increaseNumOfMonths(20);
		assertTrue(withdrawValidator.commandIsValid("withdraw 12345678 1000"));
	}

	@Test
	public void withdraw_12345678_100_is_invalid_when_account_with_same_id_does_not_exist() {
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678 100"));
	}

	@Test
	public void withdraw_12345678_100_foobar_is_invalid() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678 100 foobar"));
	}

	@Test
	public void withdraw_12345678_neg_1_is_invalid() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678 -1"));
	}

	@Test
	public void withdraw_is_invalid() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid("withdraw"));
	}

	@Test
	public void withdraw_12345678_is_invalid() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678"));
	}

	@Test
	public void withdraw_100_is_invalid() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid("withdraw 100"));
	}

	@Test
	public void _12345678_100_is_invalid() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid("12345678 100"));
	}

	@Test
	public void _Withdraw_12345678_100_is_invalid() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid(" Withdraw 12345678 100"));
	}

	@Test
	public void Withdraw__12345678__100_is_invalid() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid(" Withdraw  12345678  100"));
	}

	@Test
	public void withdraw_12345678_500_is_invalid_for_checking_account_with_same_id() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678 500"));
	}

	@Test
	public void withdraw_12345678_1100_is_invalid_for_savings_account_with_same_id() {
		bank.addAccount("savings", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).increaseNumOfMonths(1);
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678 1100"));
	}

	@Test
	public void withdraw_12345678_100_is_invalid_for_savings_account_with_0_months() {
		bank.addAccount("savings", 12345678, 3.0, 0);
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678 100"));
	}

	@Test
	public void withdraw_12345678_100_is_invalid_for_cd_account_with_0_months() {
		bank.addAccount("cd", 12345678, 3.0, 100);
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678 100"));
	}

	@Test
	public void withdraw_12345678_100_is_invalid_for_cd_account_with_11_months() {
		bank.addAccount("cd", 12345678, 3.0, 100);
		bank.getAccounts().get(12345678).increaseNumOfMonths(11);
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678 100"));
	}

	@Test
	public void withdraw_12345678_0_is_invalid_for_cd_account_with_100_balance() {
		bank.addAccount("cd", 12345678, 3.0, 100);
		bank.getAccounts().get(12345678).increaseNumOfMonths(12);
		assertFalse(withdrawValidator.commandIsValid("withdraw 12345678 0"));
	}
}
