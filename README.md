# Simplified Calendar API
####Running the application

1. Start PostgreSQL Database
  ```properties
  docker-compose up simplified-calendar-postgresql 
  ```
1.  Start Application 
  ```properties
  ./gradlew bootRun 
  ```
####Open API Documentation
[Swagger](http://localhost:8080/simplified-calendar-api/swagger-ui.html)

##### Create a single Event

```properties
curl -X \
  POST "http://localhost:8080/simplified-calendar-api/api/v1/events" \
  -H  "accept: */*" \
  -H  "Content-Type: */*" \
  -d "{\
        "name": "GUNS N' ROSES CONCERT",\
        "startDateTime": "2020-12-25T20:00:00.000Z",\
        "duration":180\
  }"
```

##### Create a Weekly endless Event

```properties
curl \
  -X POST "http://localhost:8080/simplified-calendar-api/api/v1/events" \
  -H  "accept: */*" \
  -H  "Content-Type: */*" \
  -d "{\
            "name":"Work out",\
            "startDateTime":"2020-11-11T06:00:00.000Z",\
            "duration":60,\
            "recurrence":{\
                "frequencyType":"WEEKLY",\
                "daysOfWeek":["MONDAY","WEDNESDAY","FRIDAY"]\
            }\
  }"
```
##### Query events

```properties
curl -X \
  GET "http://localhost:8080/simplified-calendar-api/api/v1/events?fromDate=2020-12-01&toDate=2020-12-31" \
  -H  "accept: */*"
```
##### Delete an event

```properties
curl -X DELETE "http://localhost:8080/simplified-calendar-api/api/v1/events/1" -H  "accept: */*"

```
