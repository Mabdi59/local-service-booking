# Local Service Booking System

## Database

Inside the `<project-root>/database/` directory, you'll find an executable Bash script (`.sh` file) and several SQL scripts (`.sql` files). These can be used to build and rebuild a PostgreSQL database for the booking system.

From a terminal session, execute the following commands:

```
cd <project-root>/database/
./create.sh
```

This Bash script drops the existing database, if necessary, creates a new database named `local_service_booking`, and runs the various SQL scripts in the correct order. You don't need to modify the Bash script unless you want to change the database name.

Each SQL script has a specific purpose as described here:

| File Name | Description |
| --------- | ----------- |
| `data.sql` | This script populates the database with any static setup data or test/demo data. The project team should modify this script. |
| `dropdb.sql` | This script destroys the database so that it can be recreated. It drops the database and associated users. The project team shouldn't have to modify this script. |
| `schema.sql` | This script creates all of the database objects, such as tables and sequences. The project team should modify this script. |
| `user.sql` | This script creates the database application users and grants them the appropriate privileges. The project team shouldn't have to modify this script. <br /> See the next section for more information on these users. |

### Database Users

The database superuser—meaning `postgres`—must only be used for database administration. It must not be used by applications. As such, two database users are created for the application as described here:

| Username | Description |
| -------- | ----------- |
| `local_booking_owner` | This user is the schema owner. It has full access—meaning granted all privileges—to all database objects within the `booking` schema and also has privileges to create new schema objects. This user can be used to connect to the database from PGAdmin for administrative purposes. |
| `local_booking_appuser` | The application uses this user to make connections to the database. This user is granted `SELECT`, `INSERT`, `UPDATE`, and `DELETE` privileges for all database tables and can `SELECT` from all sequences. The application datasource has been configured to connect using this user. |

## Spring Boot

Note: Spring Boot has been configured to run on port `9000` for this project. You might be used to port `8080` from earlier in development, but it's changed so as not to conflict with the frontend Vue server that you'll be running concurrently.

### Datasource

A Datasource has been configured for you in `src/resources/application.properties`. It connects to the database using the `local_booking_appuser` database user. You can change the name of this database if you want, but remember to change it here and in the `create.sh` script in the database folder:

```
# datasource connection properties
spring.datasource.url=jdbc:postgresql://localhost:5432/local_service_booking
spring.datasource.name=local_service_booking
spring.datasource.username=local_booking_appuser
spring.datasource.password=localbooking
```

### JdbcTemplate

If you look in `/src/main/java/com/localbooking/dao`, you'll see `JdbcUserDao`. This is an example of how to get an instance of `JdbcTemplate` in your DAOs. If you declare a field of type `JdbcTemplate` and add it as an argument to the constructor, Spring automatically injects an instance for you:

```java
@Service
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
```

### CORS

Any controller that'll be accessed from a client like the Vue frontend application needs the `@CrossOrigin` annotation. This tells the browser that you're allowing the client application to access this resource:

```java
@RestController
@CrossOrigin
public class AuthenticationController {
    // ...
}
```

## Security

Most of the functionality related to Security is located in the `src/main/java/com/localbooking/security` package. You shouldn't have to modify anything here, but feel free to go through the code if you want to see how things work.

### Authentication Controller

There is a single controller in the `com.localbooking.controller` package called `AuthenticationController.java`.

This controller contains the `/login` and `/register` routes and works with the Vue frontend as is. If you need to modify the user registration form, start here.

The authentication controller uses the `JdbcUserDao` to read and write data from the users table.

## Testing

### DAO Integration Tests

`com.localbooking.dao.BaseDaoTest` has been provided for you to use as a base class for any DAO integration test. It initializes a Datasource for testing and manages rollback of database changes between tests.

`com.localbooking.dao.JdbcUserDaoTest` has been provided for you as an example for writing your own DAO integration tests.

Remember that when testing, you're using a copy of the real database. The schema for the test database is defined in the same schema script for the real database, `database/schema.sql`. The data for the test database is defined separately within `src/test/resources/test-data.sql`.

