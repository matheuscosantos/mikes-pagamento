Feature: Queue Listener

  Scenario: Process order payment from SQS queue
    Given the Order Payment Service is available
    When a message is received on the SQS queue with orderId "e7f3a7ab-cb6f-480c-b77f-eefca352a942"
    Then the order payment should be processed successfully