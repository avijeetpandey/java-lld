# Patterns

## Strategy design pattern

Strategy patterns is basically used to define a set of algorithms or methods and lets you change them on the fly for example.

- A payment strategy
  - Pay by card or UPI
- A sorting strategy
  - Mergesort or quick sort
- A discout strategy

It follows **Open/Close** principle.

## Observer pattern

This design pattern is basically used to Pub/Sub method that notifies the changes in the state for the system.
Widely used in `Notification based system` or `Side effect based systems`

## Factory pattern

This is a creational pattern and is used mostly when the creation of lot of objects are involved.

## Proxy design pattern

This pattern is basically used in case where in we need to do certain operation before or after the request came , for example in a firewall or chaching etc so before htting a real server it goes to the proxy first.