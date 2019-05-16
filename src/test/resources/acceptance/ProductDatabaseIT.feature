Feature: Product Queries

  Scenario: I can get a successful healthcheck
    Given When I query the healthcheck endpoint
    Then I get a success status code

