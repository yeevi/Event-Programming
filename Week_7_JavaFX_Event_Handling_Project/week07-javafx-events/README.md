# Week 7: Event Handling in JavaFX

The five Java files follow the code examples in the supplied Week 7 lecture guide. The `pom.xml` is Maven project configuration added to make the source files a runnable JavaFX project.

## Project structure

```text
week07-javafx-events/
├── pom.xml
└── src/main/java/com/example/
    ├── EventPropagationDemo.java
    ├── MouseKeyDemo.java
    ├── DragDropDemo.java
    ├── LoginSuccessEvent.java
    └── CustomEventDemo.java
```

## Open in VS Code

1. Extract the ZIP file.
2. In VS Code, choose **File > Open Folder**.
3. Select the extracted `week07-javafx-events` folder.
4. Open a terminal in that folder.

This Maven setup is configured for JDK 21 and JavaFX 21.0.2.

## Run each activity

Run one command at a time:

```powershell
mvn javafx:run -Djavafx.mainClass=com.example.EventPropagationDemo
mvn javafx:run -Djavafx.mainClass=com.example.MouseKeyDemo
mvn javafx:run -Djavafx.mainClass=com.example.DragDropDemo
mvn javafx:run -Djavafx.mainClass=com.example.CustomEventDemo
```

## Note about the lecture code

The `MouseKeyDemo.java` file preserves the lecture's `root.setFocusable(true);` line exactly. If Java reports that `setFocusable` cannot be found, JavaFX's corresponding method is `setFocusTraversable(true)`. Only make that change if you encounter that compiler error.
