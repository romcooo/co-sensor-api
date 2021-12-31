# Endpoints

(Examples [here](EXAMPLES.md))

- /cities
  - POST - creates a new "user city" (201), 403 (in case city with provided name already exists)
    - use the city to authenticate on all other urls (username={cityName}, password={password})
  - PATCH - changes password (204) || 401
  
---

- /districts
    - GET - 200 gets all districts for authenticated city (only names and codes) || 401
    - POST - 200 create new district for authenticated city. Doesn't validate whether a district with identical name exists. || 401
    
- /districts/1
  - GET - 200 gets data (including readings per sensor) || 401 || 404 not found
  
- /districts/1/sensors
  - GET - 200 gets all sensors (codes only)  || 401 || 404
  - POST - 200 adds a sensor. Doesn't validate whether a sensor with identical name exists. || 401 || 404

---

- /sensors/1
  - GET - 200 returns sensor data || 401 || 404

- /sensors/1/measurements
  - POST - 200 adds a reading || 401 || 404
  
---

