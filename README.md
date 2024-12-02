# Conjugations App Backend

This is the back-end API for the Conjugations App, providing the necessary endpoints and logic to support spaced repetition, user authentication, progress tracking, and data management.

## Features

- **RESTful API**: Exposes endpoints for managing user data, conjugation sets, and spaced repetition schedules.
- **User Authentication**: Secure user registration, login, and password recovery.
- **Database Management**: Stores user progress, conjugation tables, and settings.
- **Cloud Deployment**: Hosted on AWS for scalability and reliability.
- **Push Notifications**: Sends reminders for practice sessions using Firebase Cloud Messaging.

## Tech Stack

- **Framework**: Spring Boot
- **Database**: MySQL
- **Hosting**: AWS Elastic Beanstalk
- **Authentication**: OAuth2 / JWT
- **Notifications**: Firebase Cloud Messaging

## Installation and Setup

1. Clone the repository:
   ```
   https://github.com/clement-perrier/conjugation-app-back.git
2. Configure your database:
   - Create a MySQL database.
   - Update the database connection details in the application.properties file:
   ```
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
3. Install dependencies and build project
   ```
   ./mvnw clean install
4. Run the application
   ```
   ./mvnw spring-boot:run
5. The API will be available at:
   ```
   http://localhost:8080
# API Endpoints
| Method      | Endpoint | Description |
| ----------- | -------- | ----------- |
| POST        | /auth/login | Log in user |
| POST        | /auth/signup | Register a new user |
| POST        | /auth/refreshToken | Refresh token |
| POST        | /auth/changePassword | Request password change |
| POST        | /auth/changePassword | Update user password |
| POST        | /auth/logout | Logout the user |
| GET         | /user?userId=_user_id_ | Get user informations |
| GET         | /learningLanguages | Get all learning languages available in the app |
| GET         | /tenses/languageId=_language_id_ | Get all the tenses for a specified learning language |
| GET         | /verbs/languageId=_language_id_ | Get all the verbs for a specified learning language |
| GET         | /pronouns/languageId=_language_id_ | Get all the pronouns for a specified learning language |
| GET         | /tables/languageId=_language_id_ | Get all the conjugation tables for a specified learning language |
| GET         | /batchesByUserAndLanguage?userId=_user_id_&languageId=_language_id_ | Get all sets of an user for a specified learning language |
| POST        | /newBatch | Create a new set |
| PUT         | /updateBatch | Update an existing set |
| PUT         | /updateUserLearningLanguageList?userId=_user_id_&learningLanguageId=_learning_language_id_ | Update user learning language list  with a new learning language |
| PUT         | /updateUserDefaultLearningLanguage?userId=_user_id_&learningLanguageId=_learning_language_id_ | Update user default learning language |
| PUT         | /updateUserDeviceToken?userId=_user_id_&deviceToken=_device_token_ | Update user device token |
| DELETE      | /deleteBatch?batchId=_batch_id_ | Remove an user set |

# Contact

For questions or support, please contact:
- Author: Clément Perrier
- Email: clement.perrier25@gmail.com
- LinkedIn: https://www.linkedin.com/in/cl%C3%A9ment-perrier-a5173a152
