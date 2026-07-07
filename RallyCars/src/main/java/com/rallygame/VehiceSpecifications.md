# Vehicle Specifications

## Överblick
Alla implementationer av följande fordon ska vara subklasser av abstrakta klassen "Vehicle". 
De abstrakta metoderna ska implementeras enligt dessa specifikationer, 
och avvikande beteende ska överskrida eller använda superklassens implementeringar.

### Tips:
- Kolla på default implementeringarna i Vehicle och vad de abstrakta metoderna är
- Kolla på hur Road och RoadType är implementerat, vad är de för datastruktur?
- Börja med att implementera RallyCar (den har inget avvikande beteende)

## RallyCar
- Surface Speed: Asphalt 60, Dirt 60, Gravel 60, Snow 50
- Turn Speed: 90% on rough terrain (Dirt/Gravel/Snow), 50% on asphalt
- Fuel Consumption: 3
- Tank Size: 70
- Refueling Rate: 8
- Acceleration Rate: 12

## Buggy
- Surface Speed: 50, konstant på alla vägtyper
- Turn Speed: 50 (samma som surface speed)
- Fuel Consumption: 1 
- Tank Size: 30 
- Refueling Rate: 10 
- Acceleration Rate: 6
- AVVIKANDE BETEENDE: 3-turn delay after refueling (Det tar längre att starta upp motorn)

## SportsCar
- Surface Speed: Asphalt 120, Snow 30, Gravel 60, Dirt 50 
- Turn Speed: 60% of surface speed
- Fuel Consumption: 2
- Tank Size: 50 
- Refueling Rate: 10 
- Acceleration Rate: 10
- AVVIKANDE BETEENDE: Breaks down every 10 turns in snow, takes 2 turns to repair

## SnowMobile
- Surface Speed: Asphalt 20, Snow 90, Dirt 40, Gravel 30 
- Turn Speed: 90 on snow (full speed), 10 on other surfaces
- Fuel Consumption: 2 
- Tank Size: 20 
- Refueling Rate: 10 
- Acceleration Rate: 20
- Special: Cuts through snow roads (only requires half distance of snow road before going to next)



