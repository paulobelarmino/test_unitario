package br.ufrn.imd.models;

import br.ufrn.imd.models.BankAccount;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BankAccountStepdefs {

    private BankAccount bankAccount;
    private BankAccount destBankAccount;

    @Given("um BankAccount com saldo de R$ {double}")
    public void um_bank_account_com_saldo_de_r$(Double double1) {
        bankAccount = new BankAccount(123456, 123, double1);
    }

   @Given("um BankAccount de destino com saldo de R$ {double}")
    public void another_bank_account_com_saldo_de_r$(Double double1) {
        destBankAccount = new BankAccount(123456, 123, double1);
    }

    @When("depositar R$ {double}")
    public void depositar_r$(Double double1) {
        bankAccount.deposit(double1);
    }

    @When("retirar R$ {double}")
    public void retirada_r$(Double double1) {
        bankAccount.withdraw(double1);
    }


    @When("tranferir R$ {double} para o BankAccount de destino")
    public void transferencia_r$(Double double1) {
        bankAccount.transfer(destBankAccount, double1);
    }


    @Then("o saldo do BankAccount deve ser R$ {double}")
    public void o_saldo_deve_ser_r$(Double double1) {
        var saldo = bankAccount.getBalance();
        assertEquals(double1, saldo);
    }


    @Then("o saldo do BankAccount de destino deve ser R$ {double}")
    public void o_saldo_destino_e_r$(Double double1) {
        var saldo = destBankAccount.getBalance();
        assertEquals(double1, saldo);
    }



}

//Cenario: Transferencia em um BankAccount com saldo
//        Dado um BankAccount com saldo de R$ 100,00
//        E um BankAccount de destino com saldo de R$ 0,00
//        Quando tranferir R$ 50,00 para o BankAccount de destino
//        Entao o saldo do BankAccount deve ser R$ 50,00
//        E o saldo do BankAccount de destino deve ser R$ 50,00
