# aws-sam

This repo is now organized as a Gradle multi-module project:

- `common` — shared Java library
- `GetAllPiratesFunction` — lambda module
- `GetPirateByIdFunction` — lambda module
- `SavePirateFunction` — lambda module

## Shared code

The shared model lives in `src/common/model/Pirate.java` and is compiled as the `:common` module.

Its package is:

```java
common.model.Pirate
```

Use it from any lambda module like this:

```java
import common.model.Pirate;

Pirate pirate = new Pirate("1", "28", "Black Pearl", "Jack Sparrow");
```

## Build layout

- Root `settings.gradle` includes all modules.
- Root `build.gradle` defines shared repository configuration.
- Each lambda module depends on `project(':common')`.

## Build

From the project root, you can build everything with:

```powershell
gradle build
```

Or, if you use AWS SAM, continue with your usual flow after the Gradle build:

```powershell
sam build
sam deploy --guided
```


