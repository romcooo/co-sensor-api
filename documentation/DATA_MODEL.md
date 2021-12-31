## Data Model

@startuml
class City {
name: String
}

class District {
name: String
cityName: String
}
District "*" --* "1" City

class Sensor {
name: String
districtName: String
cityName: String
}
Sensor "*" --* "1" District

class Reading {
timestamp: timestamp
level: Double
}
Reading "*" --* "1" Sensor

@enduml
