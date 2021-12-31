# Examples

## /cities

### POST

- request:
  ```
  {
    name: "Barcelona",
    password: "whatever"
  }
  ```
  
### PATCH

- request:
```
  {
    password: "changed"
  }
```

---

## /districts
### POST
- request:
```
  {
    name: "Penzing"
  }
```

## /districts/{id}

### GET - returns 200 + data (including readings per sensor) || 401 || 404 not found

- response:
```json
{
    "id": 2,
    "name": "Wahring",
    "cityName": "Wien",
    "sensors": [
        {
            "id": 4,
            "name": "wahring_2",
            "measurements": [],
            "districtName": "Wahring",
            "cityName": "Wien"
        },
        {
            "id": 3,
            "name": "wahring_1",
            "measurements": [
                {
                    "timestamp": "2021-12-31T15:12:17.393+00:00",
                    "level": 2.13
                },
                {
                    "timestamp": "2021-12-31T15:12:17.393+00:00",
                    "level": 2.13
                }
            ],
            "districtName": "Wahring",
            "cityName": "Wien"
        }
    ],
    "_links": {
        "self": {
            "href": "http://localhost:8081/districts/2"
        },
        "districts": {
            "href": "http://localhost:8081/districts"
        },
        "sensors": {
            "href": "http://localhost:8081/districts/2/sensors"
        }
    }
}
```

## /districts/{id}/sensors
### POST - adds a sensor
```
{ name: "wahring_3" }
```

---

## /sensors/{id}/readings
### POST - adds a reading || 401
- request:
```
  { 
    timestamp: 2021-12-27T12:00:00.000,
    level: 9.56
  }
```
    
---
