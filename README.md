
# StudentTeamManagement-JavaFx-SpringBoot

A student team management system using a decoupled architecture:
- **Backend**: Spring Boot REST API (Maven-based)
- **Frontend**: JavaFX Desktop Application (Maven-based, MVC pattern)

This project demonstrates how a JavaFX client application communicates with a Spring Boot server to manage students, teams, and their assignments efficiently.

---

## 🗂️ Project Structure

```
StudentTeamManagement-JavaFx-SpringBoot/
├── backend-springboot/         # Spring Boot REST API (Maven project)
└── frontend-javafx/            # JavaFX GUI Application (Maven project)
```

---

## 🚀 Getting Started

> 📝 **Pre-requisites**:
> - JDK 17+ (ensure same version for both backend and frontend)
> - Maven 3.6+
> - Internet connection (for Maven to download dependencies)
> - JavaFX SDK (only needed for frontend-javafx if not using a bundled JDK)

---

### 1️⃣ Start the Spring Boot Backend

1. Open a terminal and navigate to the backend project directory:

   ```bash
   cd backend-springboot
   ```

2. Build and run the Spring Boot server:

   ```bash
   mvn spring-boot:run
   ```

3. The server should start on `http://localhost:8080` (default port).
---

### 2️⃣ Start the JavaFX Frontend

1. Open a new terminal and navigate to the frontend directory:

   ```bash
   cd frontend-javafx
   ```

2. Build and run the JavaFX app:

   ```bash
   mvn clean javafx:run
   ```

   > 🛠️ If you face JavaFX module issues, make sure your IDE is configured to use the correct JavaFX SDK and `module-info.java` is set up properly.

3. The application should open a GUI window and connect to the backend.

---

## 📦 Maven Notes

### Backend - Spring Boot
- Uses standard Maven structure
- All dependencies are managed via `pom.xml`
- Controllers expose RESTful APIs for:
  - Student Management
  - Team Management
  - Assignments and Relations

### Frontend - JavaFX
- Follows MVC structure
- Communicates with Spring Boot backend via `HttpURLConnection` or preferred REST client (like `HttpClient`)
- Requires `javafx.controls`, `javafx.fxml`, etc., in `pom.xml`

---

## 🧪 API Example (Spring Boot)

Example to test backend manually:

```bash
curl http://localhost:8080/api/students
```

---

## 🧰 Technologies Used

- Java 17+
- JavaFX 17+ (FXML + Controllers)
- Spring Boot 3.x
- Maven
- REST API (JSON communication)
- h2

---

## 📌 Tips

- Always start the **Spring Boot server first**, then the **JavaFX app**.
- If using IDEs like IntelliJ, ensure you set up:
  - JavaFX VM options in Run Configuration
  - Correct project SDK and language level
- Consider using a shared DTO structure or Swagger API documentation for consistency.

---

##contact 
-- hasnatrasool163@gmail.com
