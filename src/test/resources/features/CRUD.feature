Feature: JsonPlaceHolder CRUD operations

  @Post
  Scenario Outline: Create a post and validate status code with response body
    Given user enters details for sending post request: "<title>", "<body>", "<userId>"
    When the post request is sent to the service
    Then the response with status code "<statusCode>" should be received
    And successful response with post details: "<title>", "<body>", "<userId>" should be received
    Examples:
      | title          | body                 | userId  | statusCode    |
      | Greetings      | hello                | 1       | 201           |
      | Second one     | Second test case     | 2       |  201          |

  @Get
  Scenario Outline: Get a post and validate status code with response body
    Given user enters details for sending get request: "<id>"
    When the get request is sent to the service
    Then the response with status code "<statusCode>" should be received
    And successful response with post details should be received
    Examples:
      | statusCode    |id     |
      | 200           | 1     |
      | 200           | 20    |

  @Put
  Scenario Outline: Update a post and validate status code with response body
    Given user enters details for sending put request: "<id>" "<title>", "<body>", "<userId>"
    When the put request is sent to the service
    Then the response with status code "<statusCode>" should be received
    And successful response with post details: "<title>", "<body>", "<userId>" should be received
    Examples:
      |id | title          | body                 | userId  | statusCode    |
      |1  | Change1        | it is changed        | 1       | 200           |
      |10 | Change2        | it also is changed   | 2       | 200           |

  @Delete
  Scenario Outline: Delete a post and validate status code
    Given user enters details for sending delete request: "<id>"
    When the delete request is sent to the service
    Then the response with status code "<statusCode>" should be received
    Examples:
      |id | statusCode    |
      |1  | 200           |
      |10 | 200           |