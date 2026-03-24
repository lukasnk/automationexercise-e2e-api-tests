# Automation UI Tests

UI tests built with Playwright and JUnit 5.

## Requirements
- Java 17
- Maven

## Setup
Create a `.env` file in the project root:

```env
LOGIN_EMAIL=your_email
LOGIN_PASSWORD=your_password
```

## Run tests
All tests (headless by default):

```bash
mvn test
```

Browser mode:

```bash
mvn test -Dheadless=false
```

## Demo failing test and artifacts
There is a tagged failing test for demo/debugging.

Run only that test:

```bash
mvn -DincludeTags=demo-failure test
```

Artifacts are written to:

```
target/artifacts/<ClassName>_<methodName>/
```

## CI secrets
Set GitHub Actions secrets:
- `LOGIN_EMAIL`
- `LOGIN_PASSWORD`