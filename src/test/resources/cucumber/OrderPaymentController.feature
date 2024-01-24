Feature: Order Payment Controller

  Scenario: Process order payment webhook
    Given the Order Payment is available
    When the order payment webhook is triggered with orderId "e7f3a7ab-cb6f-480c-b77f-eefca352a942" and status "ACCEPTED"
    Then should be processed successfully