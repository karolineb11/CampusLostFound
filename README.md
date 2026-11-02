# Campus Lost & Found Portal

A beginner-friendly full-stack Java project built using Spring Boot, Thymeleaf, HTML, CSS and MySQL.

## Requirements

- Java 17 or later
- MySQL Server (running locally)
- Maven
- VS Code or IntelliJ IDEA

## 1. Create the database

Open MySQL Workbench and run:

```sql
CREATE DATABASE campus_lost_found;
```

The application can also create the database automatically if your MySQL user has permission.

## 2. Set your MySQL password

Open:

`src/main/resources/application.properties`

Replace:

```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

with your actual MySQL root password. If your MySQL root account has no password, leave the value empty:

```properties
spring.datasource.password=
```

Keep the username as `root` if that is the account you use. If you use another MySQL username, update that too.

## 3. Run the project

Open the `CampusLostFound` folder in VS Code.

Open Terminal → New Terminal, make sure the terminal is inside the folder containing `pom.xml`, then run:

```bash
mvn spring-boot:run
```

Wait until the terminal displays that Spring Boot has started.

## 4. Open the website

Visit:

http://localhost:8080

## Features

- Dashboard with total, lost, found and open report counts
- Create lost/found item reports
- Search by item name, category or location
- Contact the person who submitted a report by email
- Update report status: Open, Claimed or Returned
- Delete reports
- Store data in MySQL

## Troubleshooting

### `Access denied for user 'root'@'localhost'`
Your MySQL password in `application.properties` does not match your MySQL account. Correct it and restart the app.

### `Communications link failure`
Make sure the MySQL server is running and that the connection URL uses the correct port (default: 3306).

### `mvn is not recognized`
Maven is not available in your terminal. Install/configure Maven or use the Maven setup already present on your computer.

### Port 8080 is already in use
Stop the other application using port 8080, or change `server.port=8080` to `server.port=8081` in `application.properties`. Then open `http://localhost:8081`.

## Project structure

```text
CampusLostFound/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/com/campus/lostfound/
        │   ├── CampusLostFoundApplication.java
        │   ├── controller/ItemController.java
        │   ├── model/FoundItem.java
        │   ├── model/ItemType.java
        │   ├── model/ItemStatus.java
        │   ├── repository/FoundItemRepository.java
        │   └── service/FoundItemService.java
        └── resources/
            ├── application.properties
            ├── static/css/style.css
            └── templates/
                ├── index.html
                └── item-form.html
```
