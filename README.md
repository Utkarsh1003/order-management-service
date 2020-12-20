# Order Management Service

This is a simple dropwizard project for order management service.

## Table of Content
- [Problem Statement](#problem-statement)
- [Hot to run](#how-to-run)
- [APIs integerated](#apis)

## Problem Statement

Design & Build APIs that provide the capability to check and reserve orders that we can serve on particular delivery date.
An order can be served only if all items (with requested quantities) can be served. In order to do this, there are a bunch of criteria that we want to consider:
-  We want to have a check based on available items in the Warehouse. Warehouse has items which are available for different delivery dates. If item(s) are not available, we cannot take the order. Items start to become unavailable as and when we reserve orders.
    
    Eg. Warehouse has 100 units of Item *I1* for 15 Oct 20
    If we have reserved 99 units of *I1* for orders on 15 Oct 20, we can take only order of 1 more quantity for *I1*.
    
-  We also want to have a check based on item category. Every Item belongs to a Category.

    Eg. All fruits, vegetable items belong to a category called *F_N_V*.
    
    We want to be able to throttle maximum items that can be shipped for a given category on a given delivery date.
    This is irrespective of the Warehouse that serves the customer.
    
    Eg. Category *F_N_V* has a threshold of 150 units on 15 Oct 20
    Combined orders for a date on *F_N_V* should not be more than 150 units
    
We can fulfil the order only if all the criteria are met.

## How to run
Running the server
- Clone project to you local system
- Go into your project directory and run ```java -jar target/OrderManagementService-1.0-SNAPSHOT.jar server src/main/resources/config.yml```

The Dropwizard application is now listening on port 8080. You can use the following APIs to test it.
The application will shut down gracefully by pressing ***^C*** on mac OR ***Ctrl+C*** on windows

## APIs
#### Check item availability
  *Request*
  ```
  curl -L -X POST 'http://localhost:8080/v1/order/availability' -H 'Content-Type: application/json' -d '{
      "customerId": "user1",
      "warehouseId": "warehouse_1",
      "deliveryDate": "01-01-2020",
      "items":[
          {
              "itemId": "item_1",
              "itemName": "Apple",
              "itemCategory": "F_N_V",
              "quantity": 10
          },
          {
              "itemId": "item_2",
              "itemName": "Onion",
              "itemCategory": "F_N_V",
              "quantity": 10
          }
      ]
  }'
  ```
  *Response*
  ```
  {
      "canFulfil": true
  }
  ```

#### Reserve Order
  *Request*
  ```
  curl -L -X POST 'http://localhost:8080/v1/order/reserve' -H 'Content-Type: application/json' -d '{
      "customerId": "user1",
      "warehouseId": "warehouse_1",
      "deliveryDate": "01-01-2020",
      "items":[
          {
              "itemId": "item_1",
              "itemName": "Apple",
              "itemCategory": "F_N_V",
              "quantity": 10
          },
          {
              "itemId": "item_2",
              "itemName": "Onion",
              "itemCategory": "F_N_V",
              "quantity": 10
          }
      ]
  }'
  ```
  *Response*
  ```
  In case of Success
  {
      "code": "Success",
      "data": {
          "reserved": true,
          "message": "Success"
      }
  }

  In case of failure
  {
      "code": "Failed",
      "data": {
          "reserved": false,
          "message": "Insufficient quantities!"
      }
  }
  ```
