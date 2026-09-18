# Gym Membership

A small Java domain model for managing members, membership tiers, workouts, and
membership statistics.

## Features

- Add members with unique positive IDs.
- Update a member's membership status.
- Record workouts for existing members.
- Calculate paid-member conversion and average workout durations.
- Reject invalid member/workout data instead of silently accepting it.

`BRONZE` is treated as the free tier; `SILVER` and `GOLD` are paid tiers.

## Run the example

The project has no external dependencies. From the repository root:

```powershell
mvn compile exec:java
```

The example uses Java assertions for its checks. To enable them when running
directly, add `-ea` to the `java` command.

Run the automated test suite with:

```powershell
mvn test
```

## Project structure

```text
src/
  main/java/com/gymmembership/
    application/       Application entry point
    domain/model/      Member, workout, tier, and statistics models
    domain/service/    Membership management and business rules
  test/java/com/gymmembership/
    domain/model/      Model validation tests
    domain/service/    Membership behavior tests
```
