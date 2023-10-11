package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement transferButton = $("[data-test-id = 'action-transfer']");
    private SelenideElement amountInput = $("[data-test-id = 'amount']");
    private SelenideElement fromInput = $("[data-test-id = 'from']");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $("[data-test-id = 'error-message']");


public TransferPage() {
    transferHead.shouldBe(Condition.visible);
}

public DashboardPage makeValidTransfer(String amountToTransfer,DataHelper.CardInfo cardInfo) {
makeTransfer(amountToTransfer, cardInfo);
return new DashboardPage();
}
public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
    amountInput.setValue(amountToTransfer);
    fromInput.setValue(cardInfo.getCardNumber());
    transferButton.click();
    }
}
