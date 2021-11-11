# KN WMS User Group Assignment

## Kühne+Nagel WMS User Group Assignment

This project uses Spring Boot, Spring Data JPA, Hibernate, Liquibase, Lombok, PostgreSQL, and Docker.

Check `build.gradle` for more specific info.

This code implements the following functionality:

1. Supervisor can create User group.
2. Supervisor can edit a Group.
3. Supervisor can deactivate and reactivate a group.
4. Supervisor can search Groups.

## Running the Application

### Database

You are required to use **Docker** for the database to run.

Creating and running the container with the right configuration is done by running this command in your preferred CLI.

```bash
docker run --name kn-wms -e POSTGRES_PASSWORD=password123 -p 5432:5432 -d postgres
```

If you wish for whatever reason to drop all data and return to the initial data,
uncomment `spring.liquibase.drop-first=true` in `application.properties` in the resources folder and run the
application.

You might want to comment that line back out if you want to keep any data saved in there.

### Application

To run the application itself, you run the following command.

```bash
./gradlew bootRun
```

This application uses Liquibase migrations. Therefore, a migration is created on the very first start-up.

# Endpoints

## Group

### GET: /api/user-groups

Returns list of all groups.

Example of response:

```json
[
  {
    "id": 1,
    "name": "Admin",
    "active": true
  },
  {
    "id": 2,
    "name": "Supervisor",
    "active": true
  },
  {
    "id": 3,
    "name": "Operator",
    "active": true
  },
  {
    "id": 4,
    "name": "Test Group",
    "active": false
  }
]
```

### GET: /api/user-groups/{id}

Returns specific group by ID.

Example of response:

```json
{
  "id": 4,
  "name": "Test Group",
  "active": false
}
```

### GET: /api/user-groups/search/{text}

Returns a list of groups containing TEXT.

Example of response:

```json
[
  {
    "id": 2,
    "name": "Supervisor",
    "active": true
  },
  {
    "id": 3,
    "name": "Operator",
    "active": true
  }
]
```

### GET: /api/user-groups/active

Returns a list of active groups.

Example of response:

```json
[
  {
    "id": 1,
    "name": "Admin",
    "active": true
  },
  {
    "id": 2,
    "name": "Supervisor",
    "active": true
  },
  {
    "id": 3,
    "name": "Operator",
    "active": true
  }
]
```

### GET: /api/user-groups/inactive

Returns a list of inactive groups.

Example of response:

```json
{
  "id": 4,
  "name": "Test Group",
  "active": false
}
```

### POST: /api/user-groups

Creates a user group.

Example of request:

```json
{
  "name": "Important Group Name",
  "active": true
}
```

Example of response:

```json
{
  "id": 5,
  "name": "Important Group Name",
  "active": true
}
```

### PUT: /api/user-groups

Updates a user group.

Example of request:

```json
{
  "id": 4,
  "name": "Test Grouppppp",
  "active": false
}
```

Example of response:

```json
{
  "id": 4,
  "name": "Test Grouppppp",
  "active": false
}
```

### PUT: /api/user-groups/{id}/activate

Activates group with specified ID.

Example of response:

```json
{
  "id": 4,
  "name": "Test Group",
  "active": true
}
```

### PUT: /api/user-groups/{id}/deactivate

Activates group with specified ID.

Example of response:

```json
{
  "id": 4,
  "name": "Test Group",
  "active": false
}
```