# coronethw

Backend Senior SW engineer home assignment

The purpose of this assignment is to examine the candidate’s professional skills:
•	Java, Spring
•	Inter-service communication
•	Communication with the database
•	Building scalable software architecture
•	Research
•	Code quality

Task definition:
The purpose of our program is to listen email messages and scan them for credit card numbers, if found, our program will create an event in the database for the detected email.
Specifications:
•	Expected traffic is 300 email messages per second
•	All messages are unique, no need to check for duplicates
Requirements:
•	Please develop a simple scalable solution and think about how to split logic into microservices
•	Use NoSQL database to store events, preferable MongoDB
•	For inter-service communication use asynchronous message broker by your preference
•	Email messages will be sent to the web service “/message” with method POST, no need for security layer


Email DTO:
{
  "id": "ceafra-asfda-asdfdv-nfh-xcx",
  "sender": "sender@coro.net",
  "recipients": [
    "rec1@coro.net",
    "rec2@coro.net"
  ],
  "subject": "My Mastercard is 5425233430109903",
  "body": "Hi Joe, here are my credit card numbers 5425-2334-3010-9903 and 4917484589897107, thank you.",
  "sentTime": 1642426114024
}


Event Model:
{
  "id": "61141944gee4fa065f9ch25a",
  "messageId": "ceafra-asfda-asdfdv-nfh-xcx",
  "creationTime": 1642426545189,
  "sender": "sender@coro.net",
  "recipients": [
    "rec1@coro.net",
    "rec2@coro.net"
  ],
  "detectedions": [
    "5425233430109903",
    "4917484589897107"
  ]
}

