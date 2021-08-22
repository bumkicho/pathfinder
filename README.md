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
