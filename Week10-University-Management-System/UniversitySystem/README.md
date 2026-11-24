# University Management System
## Week 10 Final Project – Event-Driven Architecture

A JavaFX and MySQL University Management System demonstrating the Week 10 learning outcomes.

### Learning outcomes
- **LO1 – EDA Principles:** Separates the JavaFX interface from the MySQL database through `DatabaseUtil`.
- **LO2 – Producer-Consumer:** Uses JavaFX `Task` objects to load and save student records in background threads.
- **LO3 – Scheduled Tasks:** Uses `ScheduledExecutorService` to print a system heartbeat every five seconds.
- **LO4 – Event Communication:** Uses JavaFX button events, observable properties and bindings.

### Requirements
- JDK 17
- Apache Maven
- MySQL Server and MySQL Workbench
- Visual Studio Code with Java and Maven extensions (or another Java IDE)

### Database setup
1. Open MySQL Workbench and connect to your local MySQL server.
2. Open `database/university_db.sql`.
3. Execute the complete script.
4. Check that the `university_db` database and its `programs` and `students` tables exist.

### Database credentials
The application reads these environment variables:
- `MYSQL_URL` (optional; default: `jdbc:mysql://localhost:3306/university_db`)
- `MYSQL_USER` (optional; default: `root`)
- `MYSQL_PASSWORD` (set this to your own local MySQL password)

Do not commit your real database password to GitHub.

### Run the application
Open the `UniversitySystem` folder (the folder containing `pom.xml`) in VS Code, then run this command in the terminal:

```bash
mvn clean javafx:run
```

The application opens on the Dashboard. Select **Students**, enter a registration number, first name and last name, then select **Save Student**. The table should refresh after the record is saved. The terminal prints a heartbeat message every five seconds.

### Project structure
```text
UniversitySystem/
├── database/
│   └── university_db.sql
├── src/
│   └── main/
│       ├── java/com/university/
│       │   ├── App.java
│       │   ├── DatabaseUtil.java
│       │   ├── Student.java
│       │   ├── LoadStudentsTask.java
│       │   ├── StudentController.java
│       │   └── MainController.java
│       └── resources/com/university/
│           ├── MainView.fxml
│           └── StudentView.fxml
├── pom.xml
├── .gitignore
└── README.md
```
