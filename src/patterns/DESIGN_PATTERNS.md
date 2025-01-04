## Design patterns notes
Design patterns are the principles of Object oriented programming , which helps us to build scalable, secure and good software solutions.

### Strategy Design pattern
Strategy design pattern is helpful when the child classes has different set of implementation than the parent class.

We should use the strategy design pattern in the following scenarios
- If there are multiple implementation of the same functionality, and they can be interchangebly used based on different context
- When we want to do selection on runtime
- When we want to reduce if-else conditionals

### Observer design pattern
In observer design pattern there are mainly two components
- Observable
- Observer

So the observable notifies the observers, whenever there is a state change this is the crux of observable pattern, in other words `Observer pattern` is a pattern that defines one to many dependency between
objects, so that when one object changes all its dependents are notified.