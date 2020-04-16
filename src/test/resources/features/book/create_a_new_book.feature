Feature: Create a new book record

  @Book
  Scenario: Each book has a unique ID
    Given the following book:
      | ID | Title        | Description | PageCount | Excerpt | PublishDate              |
      | 2  | Rest Assured | Test        | 200       | Test    | 2020-03-25T04:05:02.614Z |
    When I create the book record
    Then Book Api should Return Statuscode <200>
    And  Recorded book should include the following details:
      | ID  | Title        | Description | PageCount | Excerpt | PublishDate              |
      | 2.0 | Rest Assured | Test        | 200.0     | Test    | 2020-03-25T04:05:02.614Z |


