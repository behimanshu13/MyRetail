## Target Case Study - myRetail RESTful service

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.

Build an application that performs the following actions: 
1) Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793). Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
2) Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail). Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics
3) Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response
4) BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store

## Solution

##### API consists of:
1) Retrieve the product details by given product_id in the API request
2) Create a new product with POST service
3) Update the product with product_id if it exists, and create one if it doesn't, using PUT request
4) Fetching requests and posting them on Firebase DB, and extracting some from external API
5) Test cases for Controller class

##### Technology stack:
1)	Spring Tool Suite - https://spring.io/tools
2)	Firebase DB - https://firebase.google.com/
3)	Junit/Mockito - https://site.mockito.org/
4) Maven Repositories - https://mvnrepository.com/
5) Postman - https://www.postman.com/
6) Java 1.8
7) Github link - https://github.com/behimanshu13/myRetail

##### Pre-requisites/Setup:
1) Download the zip file directly, or by accessing the github link
2) Import the project into STS IDE (or any IDE of choice, preferrably eclipse)
3) Right click on project folder, or drill down to MyRetailApplication.java file in com.myRetail.products folder and run as Springboot app

##### Postman API calls and responses:
Once the app is running, go to postman and try the following request calls. Listed are the most important calls for consideration
Apart from these calls, other exception/error cases are also tried to be handled with required error messages.
**************************************************************************************************************************************
###### 1) GET with valid product_id
GET Request: localhost:8080/products/13860431

Response: 
{
    "id": "13860431",
    "current_price": {
        "price": 644.49,
        "currency_code": "INR"
    },
    "title": "The Hunters (DVD)"
}
**************************************************************************************************************************************
###### 2) POST a new product
POST Request: localhost:8080/products/createProduct

Body: 
{
   "id":13860434,
   "current_price":{
      "price":87,
      "currency_code":"GBP"
   }
}

Response: 
{
    "updateStatus": "Successfully stored in DB",
    "updatedTime": "2020-03-29T03:50:26.566311000Z"
}
**************************************************************************************************************************************
###### 3) Update existing product
PUT Request: localhost:8080/products/13860434

Body: 
{
   "id":13860434,
   "current_price":{
      "price":64.49,
      "currency_code":"CAD"
   }
}

Response: 
{
    "updateStatus": "Successfully stored in DB",
    "updatedTime": "2020-03-29T03:50:26.566311000Z"
}
**************************************************************************************************************************************
###### 4) GET with invalid product_id (also includes product_id that does not exist in database)
GET Request: localhost:8080/products/138604390

Response: 
{
    "errorMessage": "Product ID: 138604390 does not exist",
    "errorCode": 404
}
**************************************************************************************************************************************
###### 5) Trying to post product which already exists
POST Request: localhost:8080/products/createProduct

Body: 
{
   "id":13860434,
   "current_price":{
      "price":87,
      "currency_code":"GBP"
   }
}

Response: 
{
    "errorMessage": "Product ID already exists",
    "errorCode": 409
}
**************************************************************************************************************************************
###### 5) Trying to put product with varying product_id in request and request body
PUT Request: localhost:8080/products/13860434

Body: 
{
   "id":13860435,
   "current_price":{
      "price":64.49,
      "currency_code":"CAD"
   }
}

Response: 
{
    "errorMessage": "Product ID in parameter and request body should be same",
    "errorCode": 400
}
**************************************************************************************************************************************
##### Test the application:
1) Unit test cases have been written for Controller class. In order to test it, please right click on ProductControllerTest.java
2) Run as Junit test case

