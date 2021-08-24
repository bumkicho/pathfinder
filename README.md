# Spring Boot Pathfinder

### Endpoints

#### Sign in

	POST /api/authentication/signin HTTP/1.1
	Host: localhost:8080
	Content-Type: application/json

	{
		"userName": "user",
		"userEmail": "email",
		"userPassword": "password"
	}

#### Log in

	POST /api/authentication/login HTTP/1.1
	Host: localhost:8080
	Content-Type: application/json

	{
		"userName":"user",
		"userPassword":"password"
	}

#### Update user

	PUT /api/internal/updateuser HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...
	Content-Type: application/json

	{
		"username":"user",
		"rolename":"role",
		"status": "Active"
	}

#### add contact

POST /api/crm/contact/add HTTP/1.1
Host: localhost:8080
Authorization: Bearer ...
Content-Type: application/json

{
    "elementcount":1,
    "elements":[
        {
            "firstName":"test",
            "lastName":"user",
            "emailAddress":"testuser@email.com",
            "addressLine1":"",
            "addressLine2":"",
            "city":"city",
            "state":"ST",
            "postalCode1":"00001",
            "postalCode2":""
        }
    ]
}

#### update address

	PUT /api/crm/contact/updateaddress HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ....
	Content-Type: application/json

	{
		"emailAddress":"testuser@email.com",
		"addressLine1":"address line 1",
		"addressLine2":"",
		"city":"City",
		"state":"ST",
		"postalCode1":"00002",
		"postalCode2":""
	}

#### get all contacts

	GET /api/crm/contact HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...