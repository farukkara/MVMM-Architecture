API Documentation
Overview

Hi i am Faruk Kara, this project provides a set of RESTful APIs for retrieving and manipulating data. The APIs are built using Retrofit and are available at the following base URL: https://example.com/api/.
Endpoints
Get all Data

bash

GET /data

Parameters
Name	Type	Required	Description
Example Request

javascript

GET https://example.com/api/data

Example Response

json

{
    "data": [
        {
            "id": 1,
            "name": "John Doe",
            "age": 30
        },
        {
            "id": 2,
            "name": "Jane Smith",
            "age": 25
        },
        ...
    ]
}

Get Data by ID

bash

GET /data/{id}

Parameters
Name	Type	Required	Description
id	int	Yes	The ID of the data to retrieve
Example Request

ruby

GET https://example.com/api/data/1

Example Response

json

{
    "data": {
        "id": 1,
        "name": "John Doe",
        "age": 30
    }
}

Create Data

bash

POST /data

Parameters
Name	Type	Required	Description
name	string	Yes	The name of the data
age	int	Yes	The age of the data
Example Request

json

POST https://example.com/api/data

{
    "name": "Jane Smith",
    "age": 25
}

Example Response

json

{
    "data": {
        "id": 2,
        "name": "Jane Smith",
        "age": 25
    }
}

Update Data

bash

PUT /data/{id}

Parameters
Name	Type	Required	Description
id	int	Yes	The ID of the data to update
name	string	Yes	The new name of the data
age	int	Yes	The new age of the data
Example Request

ruby

PUT https://example.com/api/data/2

{
    "name": "Jane Doe",
    "age": 26
}

Example Response

json

{
    "data": {
        "id": 2,
        "name": "Jane Doe",
        "age": 26
    }
}

Delete Data

bash

DELETE /data/{id}

Parameters
Name	Type	Required	Description
id	int	Yes	The ID of the data to delete
Example Request

ruby

DELETE https://example.com/api/data/2

Example Response

json

{
    "message":

