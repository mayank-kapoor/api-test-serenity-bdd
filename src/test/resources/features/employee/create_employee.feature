Feature: Create a new employee record

  @Employee
  Scenario: Employee creation with Valid Input
    Given the following employee:
      | name    | salary | age |
      | Chamini | 1000   | 30  |
    When I create an employee
    Then Api should Return Statuscode <200>
    And  Response should have status as Success



