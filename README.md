# How to run the project:
1. Open your prompt
2. Navigate to the root folder of the project
3. Run: mvn clean install
4. Run: java -jar target/course-api-data-0.0.1-SNAPSHOT.jar (or open on STS IDE and execute CourseApiDataApplication as a Java Application)
5. Open POSTMAN and try to reach the endpoints listed below:

Note: The project will run under localhost:8080

## List of Endpoints:

GET /topics

Result Example: 
[
    {
        "id": "technologies",
        "name": "technologies Updated",
        "description": "Technologies Updated"
    }
]

GET /topics/{id}

Result Example:
{
    "id": "technologies",
    "name": "technologies Updated",
    "description": "Technologies Updated"
}

POST /topics

Body Example: 
{
  "id": "technologies",
  "name": "Technologies",
  "description": "Technology Topics"
}

PUT /topics/{id}

Body Example:
{
  "name": "technologies Updated",
  "description": "Technologies Updated"
}

GET /topics/{topicId}/courses

Result Example:
[
    {
        "id": "java",
        "name": "Java",
        "description": "Java Course",
        "topic": {
            "id": "technologies",
            "name": "technologies Updated",
            "description": "Technologies Updated"
        }
    }
]

GET /topics/{topicId}/courses/{courseId}

Result Example:
{
    "id": "java",
    "name": "Java",
    "description": "Java Course",
    "topic": {
        "id": "technologies",
        "name": "technologies Updated",
        "description": "Technologies Updated"
    }
}

POST /topics/{topicId}/courses

Body Example:
{
  "id": "java",
  "name": "Java",
  "description": "Java Course"
}

PUT /topics/{id}/courses/{courseId}

Body Example:
{
  "name": "Java Updated",
  "description": "Java Course Updated"
}
