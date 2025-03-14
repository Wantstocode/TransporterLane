## TransporterLane
The Transporter Assignment on Lanes project is a Spring Boot application that optimizes the assignment of transporters to lanes to minimize costs, maximize transporter usage, and ensure full lane coverage. The application is built using Spring Boot, PostgreSQL (Dockerized), and Gradle (Groovy DSL).</br></br>

## 🚀 How to Run the Project

1.**Clone the Repository**📥</br>
   git clone https://github.com/YourGitHubUsername/TransporterLane.git</br>
   cd TransporterLane</br></br>
   
2. **Start PostgreSQL in Docker**🐳 </br>
   docker-compose up -d</br>
   1. PostgreSQL → **http://localhost:5432**
   2. PGAdmin UI → **http://localhost:5050**</br></br>

3. **Build & Run the Project**⚙️</br>
   ./gradlew clean build  </br>
   ./gradlew bootRun</br>
   The app will start at **http://localhost:8080** 🎉</br></br>

4. **API Testing**📡</br>
   Swagger UI →**http://localhost:8080/swagger-ui/index.html**</br>
   Postman Example:</br>
  curl -X 'POST' '**http://localhost:8080/api/v1/transporters/input**'</br>
  curl -X 'POST' '**http://localhost:8080/api/v1/transporters/assignmen**t'</br></br>

5. **SonarQube Configuration (Code Quality & Test Coverage)** 📊</br>
   1. Running SonarQube Installed on Your Machine</br>
      1. Start SonarQube manually if it's not running.</br>
      2. Configure build.gradle for SonarQube.</br>
      3. Run SonarQube Analysis:<br>
          ./gradlew sonarqube</br>
      4. View Reports in Browser:<br>
          Open http://localhost:9000/dashboard?id=TollPlazaFinder</br>
      
   2. Running SonarQube in Docker</br>
      1. Start SonarQube in Docker:<br>
          docker run -d --name sonarqube -p 9000:9000 sonarqube<br>
      2. Run the Analysis:<br>
          ./gradlew sonarqube<br>
      3. Check Reports:<br>
          Open http://localhost:9000/dashboard?id=TollPlazaFinder<br>



## Swagger UI - API 1 (Submit Input Data)
![Screenshot (98)](https://github.com/user-attachments/assets/0160e7d6-722c-4e47-a95f-d234d727c370)

## Swagger UI - API 2 (Get Optimized Assignment)
![Screenshot (99)](https://github.com/user-attachments/assets/7371622a-d707-4b7f-a9e4-a7d9e5864df5)

## PostgreSQL Database - pgAdmin UI 
![Screenshot (103)](https://github.com/user-attachments/assets/8a8c167c-94d9-48a4-a3b2-9067456472d9)

