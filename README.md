# SimpleCinemaRoomRestService
Endpoints:
/seats    -  handles GET requests and returns the information about the movie theatre.
The response should contain information about the rows, columns, price and available seats. The response is a JSON object.



/purchase  - handles POST requests and marks a booked ticket as purchased.
A request should contain the following data:
row — the row number;
column — the column number.
If the purchase is successful, the response body should be as follows: 
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}



/return -     handles POST requests and allow customers to refund their tickets.
The request should have the token feature that identifies the ticket in the request body. Once you have the token, you need to identify
 the ticket it relates to and mark it as available. The response body should be as follows:
{
    "returned_ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}



/stats  - handles POST requests with URL parameters. If the URL parameters contain a password key with a super_secret value, return 
the movie theatre statistics in the following format:
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
} 
current_income — shows the total income of sold tickets.
number_of_available_seats — shows how many seats are available.
number_of_purchased_tickets — shows how many tickets were purchased.
