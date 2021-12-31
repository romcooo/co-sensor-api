If you changed the used port, replace it in commands below (default is 8081).

All data is bound to authentication - if you authenticate as user "Wien", you get access to your districts and data only.

## Preloaded data:
There's a bit of data pre-loaded on app startup. Open your command line in [example_requests](example_requests) and run:
### Check existing data:
- Get all districts:  
`curl --user Wien:testpass localhost:8081/districts`

- Get one district:  
`curl --user Wien:testpass localhost:8081/districts/2`

- Get one sensor (one with 2 measurements):  
`curl --user Wien:testpass localhost:8081/sensors/3`

- Get one sensor (one with no measurements):  
`curl --user Wien:testpass localhost:8081/sensors/4`

### Add your own data:
- Add a district  
`curl --user Wien:testpass -H "Content-Type: application/json" localhost:8081/districts -d @post_district_penzing.json`

- Add sensor (to newly created district - use district id returned in "Add district")  
`curl --user Wien:testpass -H "Content-Type: application/json" localhost:8081/districts/7/sensors -d @post_sensor_penzing_1.json`

- Add sensor measurement (to newly created sensor - again, use different id if needed)  
`curl --user Wien:testpass -H "Content-Type: application/json" localhost:8081/sensors/8/measurements -d @post_measuremenet_penzing_1_m_1.json`

- Get all districts again to check  
`curl --user Wien:testpass localhost:8081/districts`
  - now your district should be present

- Get measurements for your district  
`curl --user Wien:testpass localhost:8081/districts/7`

- Create a new city  
`curl -H "Content-Type: application/json" localhost:8081/cities -d @post_city_barcelona.json`

You can use the credentials you send in the cities POST request to create and read data for the new city.  
The new city will have no data pre-loaded, so you will have to create it yourself.    
`curl --user Barcelona:spanishPASSWORD localhost:8081/districts` - will return empty array  
Create new data, eg.  
`curl --user Barcelona:spanishPASSWORD -H "Content-Type: application/json" localhost:8081/districts -d @post_district_gracia.json`