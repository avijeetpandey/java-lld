## OOPS 
OOPS stands for `Object oriented programming` , which means everything in this paradigm revolves around real world objects

### OOPS vs Procedural programming
- **Procedural Programming**
  - Programs are divided into parts called functions
  - It does not provides a proper way of hiding data, importance to functions are given and data moves freely
  - Overloading is not possible
  - Inheritance is not possible
  - Code reusability is not possible
  - Ex Pascal, C, Fortran etc
- **OOPS**
  - Program is divided into objects
  - Objects provide data hiding , importance to data is given
  - Overloading is possible
  - Inheritance is possible
  - Code reusability is present
  - Ex Java, C#, Python, C++ etc

### What is an object?
The resemblance of real world object in code are called objects.

### Pillars of OOPS
- **Abstraction**
  - Data abstraction mean hiding the implementation details from the end user 
  - It can be achieved through Interface and Abstract class
  - **Pros**
    - It increases the security and confidentiality , the user does needs to know every underlying detail before getting started
- **Inheritance**
  - Capability of a class to inherit properties from their parent class
  - It can inherit both functions and variables , so we do not have to write them again in child class
  - Can be achieved using extends keyword or through interface
  - **Type of inheritance**
    - Single inheritance
    - Multilevel inheritance
    - Hierarchical inheritance
    - Multiple inheritance
  - **Advantages of inheritance**
    - Code reusability
    - We can achieve polymorphism using inheritance
- **Data Encapsulation**
  - Encapsulation bundles data and the code working on that data into a single unit
  - It is also known as data hiding
  - Steps to achieve encapsulation
    - Declare a variable of a class as private
    - Provide public getters and setters to modify and view the value of the variables
    - **Pros**
      - Loosely coupled code
      - Better access control and security
- **Polymorphism**
  - Poly means many and morphism means form
  - A same method , behaves differently in different situations
  - Example
    - A person can be father, husband and employee
    - Water can be liquid, solid and gas
  - **Types of polymorphism**
    - Compile time / Static polymorphism / Method overloading
    - Run time / Dynamic polymorphism / Method overriding