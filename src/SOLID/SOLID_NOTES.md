## SOLID PRINCIPLES NOTES
`S` -> Single Responsibility Principle
`O` -> Open/Close Principle
`L` -> Liskov Substitution Principle
`I` -> Interface Segmented Principle
`D` -> Dependency Inversion Control

### Advantages of SOLID
- Easy understanding of code
- Easy maintainance of code
- Reduced complexity of code
- Better decoupling of code

### Single Responsibility principle
Single responsibility principle states that a class should have only one responsibility, in other words it should only have one reason to change.


### Open closed principle
Open-Close principle states that, A class should be open for extension but closed for modification.

### Liskov Substitution Principle
If `Class B` is a subtype of `Class A` , then we should be able to replace `Object of Class A` with `Object of Class B` , without breaking the behaviour of the program, 
further the subclass should be able to extend the properties of the parent class , instead of narrowing it down.

### Interface segmented principle
Interfaces should be such that client should not implement unnecessary functions they do not need.