# Java-Trade-Tariff-Manager-

A Java program that loads tariff rules and trade data, updates product prices based on country/category rules,
evaluates trade requests against minimum tariffs, and generates an updated trade report. This is a program that my colleague (Luyun Chen) & I, worked on for our class Comp249: Object-Oriented Programming II. The project includes a File-based execution which reads Tariffs.txt, TradeData.txt, and TradeRequests.txt, then writes UpdatedTradeData.txt. And a Testing section that tests to validate class behavior (constructors, cloning, equality, linked-list operations, and trade evaluation logic).

## Features
- Load **Tariffs.txt** into a custom linked-list (`TariffList`) and ignore duplicate tariff rules.
- Load **TradeData.txt**, apply price adjustments, sort products, and generate **UpdatedTradeData.txt**.
- Load **TradeRequests.txt**, evaluate each request as:
  - Accepted
  - Conditionally Accepted (with surcharge)
  - Rejected
- Interactive tariff search by destination/origin/category.

## Project Structure
- `TradeManager.java` — main driver (file loading, processing, report generation, request evaluation)
- `TariffList.java` — custom linked list for tariffs + searching + trade evaluation policy
- `Tariff.java` — tariff entity (copy constructor, clone, equals, toString)
- `Product.java` — product entity (Comparable for sorting by product name)
- `TradeRequest.java` — trade request entity
- `TariffPolicy.java` — interface for evaluating trade outcomes

## Input Files
Place these files in the same location your code expects (project currently reads from `src/...`):
- `Tariffs.txt` — format: `destination,origin,category,minimumTariff`
- `TradeData.txt` — format: `productName,country,category,initialPrice`
- `TradeRequests.txt` — format: `requestID,origin,destination,category,tradeValue,proposedTariff`

### Example `Tariffs.txt`
China,USA,Electronics,25
USA,China,Agriculture,30
Germany,USA,Automobiles,10
