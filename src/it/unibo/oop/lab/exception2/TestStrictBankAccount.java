package it.unibo.oop.lab.exception2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * JUnit test to test
 * {@link StrictBankAccount}.
 * 
 */
public final class TestStrictBankAccount {

	private final double TRASACTION_AMOUNT = 1_000;
	private final double INIT_BALANCE = 10_000;
    /**
     * Used to test Exceptions on {@link StrictBankAccount}.
     */
    @Test
    public void testBankOperations() {
        /*
         * 1) Creare due StrictBankAccountImpl assegnati a due AccountHolder a
         * scelta, con ammontare iniziale pari a 10000 e nMaxATMTransactions=10
         * 
         * 2) Effetture un numero di operazioni a piacere per verificare che le
         * eccezioni create vengano lanciate soltanto quando opportuno, cioè in
         * presenza di un id utente errato, oppure al superamento del numero di
         * operazioni ATM gratuite.
         */
    	final BankAccount acc1 = new StrictBankAccount(1, 10_000, 10);
    	final BankAccount acc2 = new StrictBankAccount(2, 10_000, 10);
    	final AccountHolder hold1 = new AccountHolder("Mario", "Rossi", 1);
    	final AccountHolder hold2 = new AccountHolder("Marco", "Verdi", 2);
    	
    	try {
    		acc2.deposit(hold2.getUserID(), TRASACTION_AMOUNT);
    		acc1.deposit(hold1.getUserID(), TRASACTION_AMOUNT);
    	} catch (WrongAccountHolderException 
    			| NotEnoughFoundsException 
    			| TransactionsOverQuotaException e) {
    		fail(e.getMessage());
    	}
    	assertEquals("[CHECKING BALANCE AFTER DEPOSIT]", acc1.getBalance(), INIT_BALANCE + TRASACTION_AMOUNT, 0.00001);
    	assertEquals("[CHECKING BALANCE AFTER DEPOSIT]", acc2.getBalance(), INIT_BALANCE + TRASACTION_AMOUNT, 0.00001);
    	
    	try {
    		acc1.withdraw(hold1.getUserID(), TRASACTION_AMOUNT);
    		acc2.withdraw(hold2.getUserID(), TRASACTION_AMOUNT);
    	} catch (WrongAccountHolderException
    			| NotEnoughFoundsException
    			| TransactionsOverQuotaException e) {
    		fail(e.getMessage());
    	}
    	assertEquals("[CHECKING BALANCE AFTER WITHDRAW]", acc1.getBalance(), INIT_BALANCE, 0.00001);
    	assertEquals("[CHECKING BALANCE AFTER WITHDRAW]", acc2.getBalance(), INIT_BALANCE, 0.00001);
    	
    	//WrongAccountHolderException tests
    	try {
    		acc1.deposit(hold2.getUserID(), TRASACTION_AMOUNT);
    		fail();
    	} catch (WrongAccountHolderException e) {
    		assertNotNull(e.getMessage());
    		assertFalse(e.getMessage().isEmpty());
    	}
    	
    	try {
    		acc2.deposit(hold1.getUserID(), TRASACTION_AMOUNT);
    		fail();
    	} catch (WrongAccountHolderException e) {
    		assertNotNull(e.getMessage());
    		assertFalse(e.getMessage().isEmpty());
    	}
    	
    	//NotEnoughFoundsException tests
    	try {
    		acc1.withdraw(hold1.getUserID(), TRASACTION_AMOUNT * 11);
    		fail();
    	} catch (NotEnoughFoundsException e) {
    		assertNotNull(e.getMessage());
    		assertFalse(e.getMessage().isEmpty());
    	}
    	
    	try {
    		acc2.withdraw(hold2.getUserID(), TRASACTION_AMOUNT * 11);
    		fail();
    	} catch (NotEnoughFoundsException e) {
    		assertNotNull(e.getMessage());
    		assertFalse(e.getMessage().isEmpty());
    	}
    	
    	//TransactionsOverQuotaException tests
    	try {
    		for (int i = 0; i <= 10; i++) {
    			acc1.depositFromATM(hold1.getUserID(), TRASACTION_AMOUNT);
    		}
    		fail();
    	} catch (TransactionsOverQuotaException e) {
    		assertNotNull(e.getMessage());
    		assertFalse(e.getMessage().isEmpty());
    	}
    	
    	try {
    		for (int i = 0; i <= 10; i++) {
    			acc2.depositFromATM(hold2.getUserID(), TRASACTION_AMOUNT);
    		}
    		fail();
    	} catch (TransactionsOverQuotaException e) {
    		assertNotNull(e.getMessage());
    		assertFalse(e.getMessage().isEmpty());
    	}
    }
}
