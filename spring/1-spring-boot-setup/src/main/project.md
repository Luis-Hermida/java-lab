# Spring Boot Demo Project - API Documentation

## Project Overview
This is a Spring Boot demonstration project showcasing dependency injection patterns and REST API endpoints. The project includes multiple approaches to dependency injection and various coach-based workout services.

---

## API Endpoints

### 1. HelloWorldController - Basic REST Endpoints

#### GET `/`
- **Description**: Returns a simple "Lombok is working!!!!" message via a DTO
- **Method**: GET
- **Response**: String message demonstrating Lombok DTO functionality
- **Dependency Injection**: None (demonstrates property values)
- **Details**: Uses `@Value` annotation to inject properties from `application.properties`

#### GET `/custom`
- **Description**: Returns custom pet names (dog and cat) from application properties
- **Method**: GET
- **Response**: String with format: "Dog name: {dogName} Cat name: {catName}"
- **Dependency Injection**: Property-based via `@Value` annotation
- **Details**: Loads `dog.name` and `cat.name` from configuration file

---

### 2. CoachFieldController - Field Injection Example

#### GET `/coachField`
- **Description**: Returns daily workout from Cricket Coach using field injection
- **Method**: GET
- **Response**: String containing the daily cricket workout
- **Dependency Injection**: **Field Injection** (not recommended)
  - Coach dependency injected directly into private field
  - Uses `@Autowired` and `@Qualifier("cricketCoach")`
- **Details**: Demonstrates Cricket Coach implementation
- **Note**: Field injection is discouraged in modern Spring applications

---

### 3. CoachSetterController - Setter Injection Example

#### GET `/coachSetter`
- **Description**: Returns daily workout from Basketball Coach using setter injection
- **Method**: GET
- **Response**: String containing the daily basketball workout
- **Dependency Injection**: **Setter Injection**
  - Coach dependency injected via setter method `setCoachLaLaLaLa()`
  - Uses `@Autowired` on setter method and `@Qualifier("basketBallCoach")`
- **Details**: Demonstrates Basketball Coach implementation
- **Note**: Setter injection is more flexible than field injection

---

### 4. CoachContructorController - Constructor Injection Example

#### GET `/coachConstructor`
- **Description**: Returns daily workout from Football Coach (default Coach bean)
- **Method**: GET
- **Response**: String containing the daily football workout
- **Dependency Injection**: **Constructor Injection**
  - Primary Coach injected via constructor (defaults to FootBallCoach via `@Primary`)
  - No `@Qualifier` needed for primary bean
- **Details**: Demonstrates Constructor Injection - the most recommended approach
- **Best Practice**: Constructor injection is recommended as it makes dependencies explicit and enables immutability

#### GET `/swimCoach`
- **Description**: Returns daily workout from Swim Coach using constructor injection
- **Method**: GET
- **Response**: String containing the daily swimming workout
- **Dependency Injection**: **Constructor Injection**
  - Swim Coach injected via constructor using `@Qualifier("swimCoach")`
- **Details**: Shows using `@Qualifier` to specify which bean to inject when multiple implementations exist
- **Return**: "Practice swimming for 212 hours"

---

## Dependency Injection Methods Demonstrated

### Field Injection (Not Recommended)
```java
@Autowired
@Qualifier("cricketCoach")
private Coach coach;
```
- **Status**: Discouraged
- **Reason**: Difficult to test, makes dependencies implicit

### Setter Injection (Acceptable)
```java
@Autowired
public void setCoach(@Qualifier("basketBallCoach") Coach coach) {
    this.coach = coach;
}
```
- **Status**: Acceptable but less common
- **Reason**: Flexible but dependencies less explicit

### Constructor Injection (Recommended)
```java
@Autowired
public CoachController(Coach coach, @Qualifier("swimCoach") Coach swimCoach) {
    this.coach = coach;
    this.swimCoach = swimCoach;
}
```
- **Status**: Best Practice
- **Reason**: Dependencies are explicit, immutable, testable

---

## Coach Implementations

The project includes multiple Coach implementations:
- **FootBallCoach**: Primary coach (default when no qualifier specified)
- **BasketBallCoach**: Basketball workout coach
- **CricketCoach**: Cricket workout coach
- **SwimCoach**: Swimming workout coach (bean registered in CoachConfig)
- **GolfCoach**: Golf workout coach

---

## Configuration

- **Bean Configuration**: `CoachConfig.java` - Defines the SwimCoach bean
- **Application Properties**: `application.properties` - Contains property values for dog.name and cat.name
- **Qualifier Usage**: Used to specify which Coach implementation to inject when multiple are available

---

## Key Concepts Demonstrated

1. **Spring Beans**: Understanding how Spring manages and instantiates beans
2. **Dependency Injection**: Three different approaches to DI
3. **Qualifiers**: Using `@Qualifier` to resolve ambiguous bean dependencies
4. **Primary Beans**: Using `@Primary` to designate default bean
5. **Configuration Classes**: `@Configuration` for custom bean definitions
6. **Lombok**: DTO with Lombok annotations for simplified code
7. **Property Injection**: Loading values from properties files
