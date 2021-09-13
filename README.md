# Courier Tracking Application

This is a spring-boot project for a courier tracking application

## Used Technolojies

 -  Java 11
  - Spring Boot
  - JPA Hibernate
  - PostgreSQL
  - Maven


### Structure of Spring Boot Project
```bash
├── CouriertrackingApplication.java
├── Controller
│   ├── CourierRest.java
│   ├── CourierTrackingRest.java
├── Exception
│   ├── NotFoundException.java
├── Model
│ └── Dto
│	├──CourierDto.java
│	├──CourierTrackingDto.java
│	├──StoreModel.java
│ └── Entity
│ 	├──Courier.java
│ 	├──CourierLog.java
│ 	├──CourierTracking.java
│ └── Response
│ 	├──CourierLogResponse.java
│ 	├──CourierResponse.java
│ 	├──ModelResponse.java
│ 	├──TotalDistanceResponse.java
│   ├── LatitudeAndLongitude.java
├── Populator
  └── Impl
        ├── CourierLogPopulatorImp.java
    ├── CourierLogPopulator.java
├── Repository
│   ├── CourierLogRepository.java
│   ├── CourierRepository.java
│   ├── CourierTrackingRepository.java
└── Service
  └── Impl
        ├── CourierServiceImp.java
        ├── CourierTrackingServiceImp.java
    ├── CourierService.java
    ├── CourierTrackingServiceImp.java

    
```
### How Using the System 
--------

#### Courier 

  - You can get courier list with requests like this:
```sh
   @GetMapping("list")
    public ResponseEntity<ModelResponse> getCourierList(){
        return new ResponseEntity<>(courierService.getAllCourier(), HttpStatus.OK);
    }
```
  - You can save courier with requests like this:
```sh
   @PostMapping("save")
    public CourierDto saveCourier(@RequestBody CourierDto courierDto){
        return courierService.saveCourier(courierDto);
    }
```
- You can count total travel courier with requests like this:
```sh
 @PostMapping("total_distance")
    public ResponseEntity<?>  totalDistanceCourier(@RequestBody CourierDto courierDto){
        return new ResponseEntity<>(courierService.countTracking(courierDto),HttpStatus.OK);
    }

```
#### CourierTracking

- You can logging if courier enters radius of 100 meters from Migros
stores with requests like this:
```sh
 @PostMapping("log")
    public ResponseEntity<?> loggingCourier(@RequestBody CourierTrackingDto courierTrackingDto) throws IOException, NotFoundException {
        return new ResponseEntity<>(courierTrackingService.logingCourier(courierTrackingDto), HttpStatus.OK);
    }

```
### Postman Requests    
--------
##### GET localhost:8080/courier/list
```bash
{
    "model": [
        {
            "id": 1,
            "courierName": "Zeki"
        },
        {
            "id": 155,
            "courierName": "Hüseyin"
        },
        {
            "id": 156,
            "courierName": "Deneme"
        },
        {
            "id": 157,
            "courierName": "Deneme1"
        }
    ]
}
```
##### POST localhost:8080/courier/save
###### Request
```bash
{"courierName":"Deneme"}
```

##### POST localhost:8080/courier/total_distance
###### Request
```bash
{"courierName":"Deneme"}
```
###### Response
```bash
{
    "model": {
        "courierName": "Zeki",
        "totalDistance": 209865.39
    }
}
```
##### POST localhost:8080/couriertracking/log
###### Request
```bash
{
    "courierName":"Zeki",
    "time":"2021-09-08 07:08:05",
    "latitude":41.0066851,
    "longitude": 28.6552262
}
```
###### Response
```bash
{
    "model": {
        "id": 163,
        "courierName": "Zeki",
        "storeName": "Beylikdüzü 5M Migros",
        "time": "2021-09-08 07:08:05"
    }
}
```
