package pl.jkan.creditcard;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCardTest {

    @Test
    public void allowAssignLimitToCard() {

        Account account = new Account();
        CreditCard card = new CreditCard(account);

        card.setLimit(1000);
        Assertions.assertEquals(1000.0, card.getLimit(), 1);

        card.setLimit(3000);
        Assertions.assertEquals(3000.0, card.getLimit(), 1);
    }

    @Test
    public void withdrawShouldThrowsExceptionForBlockedCard(){
        Account account = new Account();
        CreditCard card = new CreditCard(account);

        card.setBlocked(true);
        Assertions.assertThrows(RuntimeException.class, ()-> card.withdraw(1000.0));
    }

    @Test
    public void withdrawShouldThowExceptionForNotEnoughMoney(){
        Account account = new Account();
        CreditCard card = new CreditCard(account);

        account.setMoney(1000);
        Assertions.assertThrows(RuntimeException.class, ()-> card.withdraw(2000));
    }

    @Test
    public void withdrawShouldWithdrawMoney(){
        Account account = new Account();
        CreditCard card = new CreditCard(account);

        account.setMoney(1000);
        card.withdraw(500);
        Assertions.assertEquals(500, account.getMoney());
    }

    @Test
    public void repayDebtShouldThrowExceptionForNotEnoughMoney(){
        Account account = new Account();
        CreditCard card = new CreditCard(account);

        account.setMoney(1000);
        card.setDebt(2000);
        Assertions.assertThrows(RuntimeException.class, card::repayDebt);
    }

    @Test
    public void repayDebtShouldTakeOutTheMoney(){
        Account account = new Account();
        CreditCard card = new CreditCard(account);

        account.setMoney(1000);
        card.setDebt(500);
        card.repayDebt();
        Assertions.assertEquals(500, account.getMoney());
    }
}

