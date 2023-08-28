# Overview

This project provides an opportunity to validate some data types by different options.

## Status

| Type | Status |
| :---: | :---: |
| Hexlet status | [![Hexlet CI](https://github.com/kudrDaniel/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/kudrDaniel/java-project-78/actions/workflows/hexlet-check.yml) |
| Build status | [![Java CI](https://github.com/kudrDaniel/java-project-78/actions/workflows/build-check.yml/badge.svg)](https://github.com/kudrDaniel/java-project-78/actions/workflows/build-check.yml) |
| Maintainability | [![Maintainability](https://api.codeclimate.com/v1/badges/ec524f0c93682e1c3ab9/maintainability)](https://codeclimate.com/github/kudrDaniel/java-project-78/maintainability) |
| Code coverage | [![Test Coverage](https://api.codeclimate.com/v1/badges/ec524f0c93682e1c3ab9/test_coverage)](https://codeclimate.com/github/kudrDaniel/java-project-78/test_coverage) |

# How to use

## Get it

### Step 1

[Download ZIP](https://github.com/kudrDaniel/java-project-78/archive/refs/heads/main.zip) and extract.

### Step 2

For start using go to _java-project-78-main/app_ and type in shell `./gradlew --console plain jshell`

> [!IMPORTANT]
> To start this validator __jdk version must be 20__

## Using

### Validator

Validator class provide three _public_ methods that returns new instance of schema:

```
Validator v = new Validator();

Map<String, BaseSchema> schemas = Map.of(
	"name", v.string(),
	"age", v.number(),
	"data", v.map
);
```

### BaseSchema

BaseSchema is abstract class of all schemas and provide common version of _isValid()_ method that must be overriden in child classes:

```
schemas.get("name").isValid(null); // true
schemas.get("age").isValid(null); // true
schemas.get("data").isValid(null); // true
```

### StringSchema

StringSchema class provide four _public_ methods. Three of them set checks of this schema

```
StringSchema schema = v.string();

schema.required();
schema.isValid("user"); // true
schema.minLength(6);
schema.isValid("Jonny Cash"); //true
schema.contains("ell");
schema.isValid("Hello!"); // true
```

### NumberSchema

NumberSchema class provide four _public_ methods. Three of them set checks of this schema

```
NumberSchema schema = v.number();

schema.required();
schema.isValid(-1); // true
schema.positive();
schema.isValid(1); // true
schema.range(2, 2);
schema.isValid(2); // true
```

### MapSchema

StringSchema class provide four _public_ methods. Three of them set checks of this schema

```
MapSchema schema = v.map();
Map<String, BaseSchema> rules = Map.of(
	"name", v.string().required(),
	"age", v.number().positive()
);

schema.required();
schema.isValid(Map.of()); // true
schema.sizeof(2);
schema.isValid(Map.of(
	"name", null,
	"age", null
)); // true
schema.shape(rules);
schema.isValid(Map.of(
	"name", "Boris",
	"age", 34
)); // true
```

# Example

[![asciicast](https://asciinema.org/a/605079.svg)](https://asciinema.org/a/605079)
