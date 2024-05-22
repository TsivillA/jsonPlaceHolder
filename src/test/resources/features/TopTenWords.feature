Feature: Count top 10 most used words from response

  @Get
  Scenario Outline: Get all post and rank top 10 most occurred words
    Given the getAll request is sent to the service
    Then the response with status code "<statusCode>" should be received
    And ranking of words should be displayed
    Examples:
      | statusCode    |
      | 200           |