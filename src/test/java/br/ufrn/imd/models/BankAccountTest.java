package br.ufrn.imd.models;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private final BankAccountTestFixture fixture = new BankAccountTestFixture();
    private BankAccount bankAccount;

    @BeforeEach
    public void Setup()
    {
        bankAccount = fixture.getNewBankAccount();
    }

    @Test
    @Description("Bank Account should have an account number and agency number")
    public void testBankAccountShouldHaveAccountNumberAndAgency(){
        assertNotNull(bankAccount.getAccountNumber());
        assertNotNull(bankAccount.getAgency());
    }

    @Test
    public void testDepositShouldChangeTheBalance(){
        bankAccount.deposit(100);
        assertEquals(100, bankAccount.getBalance());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10, -5, -3, -15})
    public void testDepositShouldNotBeZeroOrNegativeValue(double value){
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10, -5, -3, -15})
    public void testWihtdrawShouldNotBeZeroOrNegativeValue(double value){
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10, -5, -3, -15})
    public void testTransferShouldNotBeZeroOrNegativeValue(double value){
        BankAccount beneficiaryAccount = new BankAccount(12344, 5, 300);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(beneficiaryAccount, value));
    }

    @Test
    public void testWihtdrawShouldNotBeMoreThenBalance(){
        double value = bankAccount.getBalance() + 1;
        assertThrows(IllegalArgumentException.class , () -> bankAccount.withdraw(value));
    }

    @Test
    public void testTransferShouldNotBeMoreThenBalance(){
        double value = bankAccount.getBalance() + 1;
        BankAccount destBankAccount = new BankAccount(12344, 5, 300);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(destBankAccount, value));
    }

    




}//(end)



