package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

class MoneyTransferTest {

  DashboardPage dashboardPage;

  @BeforeEach
  void setup() {
    var LoginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = getAuthInfo();
    var verificationPage = LoginPage.validLogin(authInfo);
    var verificationCode = getVerificationCode();
    dashboardPage = verificationPage.validVerify(verificationCode);
  }

//  @Test
//  void shouldTransferMoneyBetweenOwnCardsV1() {
//    var verificationPage = loginPage.validLogin(authInfo);
//    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//    verificationPage.validVerify(verificationCode);
//  }

  @Test
  void shouldTransferMoneyBetweenOwnCards() {
    var firstCardinfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalance = dashboardPage.getCardBalance(firstCardinfo);
    var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
    var amount = generateValidAmount(firstCardBalance);
    var expectedBalanceFirstCard = firstCardBalance - amount;
    var expectedBalanceSecondCard = firstCardBalance + amount;
    var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
    dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount),firstCardinfo);
    var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardinfo);
    var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
    assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
    assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

  }
}
//  @Test
//  void shouldTransferMoneyBetweenOwnCardsV3() {
//    var loginPage = open("http://localhost:9999", LoginPageV3.class);
//    var authInfo = getAuthInfo();
//    var verificationPage = loginPage.validLogin(authInfo);
//    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//    verificationPage.validVerify(verificationCode);
//  }
//}

