# gateway
Gateway Microservice with a RESTful Controller

get | /hardware | optional parameter: currencyParam
will return a List of HardwareComponents
if no currencyParam is given, the List will return prices in Euro

get | /products | optional parameter: currencyParam
will return a List of Products including its HardwareComponents
if no currencyParam is given, the List will return prices in Euro

post | /products/create | requestbody: name, int[]
with a given name and an integer array containing hardware component IDs,
a product will be created in the backend

Currently it cant be run in docker container. Conditions before the start:: rabbit server and keycloak server should be running
