# Transaction Viewer

Your manager has asked you to design and implement a mobile app to help the firm
executives who are always flying around the globe. They need a list of every
product the company trades with, and the sales of those products, which are made
in different currencies.

Your application should be composed of two screens:

* [ok]  In the first one, your application should
  * [ok]  give the users the choice of which product they want to see, and
  * [ok]  show the count of transactions for that product.
* [ok]  When the user selects a product, the application must show
  * [ok]  each of the transactions related to that product,
  * [ok]  the amount of each transaction converted to GBP and
  * [ok]  the sum of all of these transactions in GBP.
  
In a real environment, you will probably download all the data you need from the
Internet. To simplify this task, so you don’t need to worry about network
management, your application should be designed to be able to work with two
JSON files:

  transactions.json : holds an array of dictionaries where each one represents
  a transaction related to a product, indicated by its stock keeping unit (SKU),
  with a given currency and amount.
  rates.json : specifies the conversion rate from one currency to another.
