## Project Lombok
Lombok is a java library , which helps us to reduce boiler plate code using annotations,
during compilation, it processes the annotation and inject code into our java classes,
**Lombok** is compatible with Java starting from **Java6** and supports all later versions.

### How to add lombok ?
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```

We need to add project lombok into `pom.xml`

### Top 10 lombok features used in industry
- **val and var**
  - Instead of actually writing the type , we can use these as the type of local variable declaration
  - Type will be inferred from the initializer expression
  - val: marks the local variable immutable ( variable made final )
  - var: local variable not marked as final

```java
import lombok.val;
public class LombokTest {
    public static void main(String[] args) {
        val a = 10; // this is immutable
        var b = 30; // this is mutable
        b = 40;
        System.out.println(a+b);
    }
}
```

- **@NonNull**
  - Generated a null check statement , and throws NPE if value is null
  - Can be used on parameter of a method or constructor.

```java
import lombok.NonNull;
public class NonNullExample {
    public void demoMethod(@NonNull String name) {
        System.out.println(name);
    }
}

public class NonNullExampleDecompiled {
    // example of the decompiled code
    public void demoMethod(@NonNulll String name) {
        if (name == null) {
            throw new NullPointerException("name is marked non null but is null");
        } else {
            System.out.println(name);
        }
    }
}
```

- **Getter And Setter**
  - We can annotate any field with @Getter and @Setter to let lombok generate default getter and setter methods
  - @Getter field is only applied on the non-static fields

```java
// example of access level in getter and setter
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class TestPojo {
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PROTECTED)
    String name;
    
    @Getter
    @Setter
    boolean commiteeMember;
}
```

- **@NoArgConstructore** , **RequiredArgsConstructor** , **AllArgsConstructor**
  - They generate no-arg, generates constructor with only final and @NonNull fields and constructor with all fields respectively.

- **@Data**
  - It is shortcut for the follwing
    - @ToString
    - @EqualsAndHashCode
    - @Getter on all fields
    - @Setter on all non-final fields
    - @RequiredArgConstructor

```java
@Data
public class TestPojo {
    String name;
    final Integer age;
    @NonNull String address;
}
```

- **@Value**
  - It is immutable version of @Data
  - All fields are made **private** and **final**
  - Setters are not generated
  - Class itself is made final
  - Like @Data , toString, Equal and HashCode method is generated
  - @Getter on all fields
  - @RequiredArgsConstructor (since all fields are final, constructor with all fields will get generated so its equivalent to @AllArgsConstructor)

- **@Builder**
```java
@Builder
public class TestPojo {
    String name;
    Integer age;
}
```

- **@Cleanup**
  - It ensures that given resource is automatically cleaned up before execution path exits the current scope.

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestPojo {
    public void readFile(String path) throws IOException {
        @Cleanup InputStream in = new FileInputStream(path); // this ensures that in will be closed automatically
        byte[] data = in.readAllBytes();
        System.out.println(new String(data));
    }
}
```