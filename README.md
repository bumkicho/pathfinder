# Spring Boot Pathfinder

### About

	Pathfinder is a community site where people who are looking to change their career paths can come share their journeys.
	This is backend, work in progress, developed with Java, Spring Boot, JPA, JWT, PostgreSQL.
	Frontend will be developed with Angular. Hosted in Heroku with email service for registration.

### Endpoints

#### Register

	POST /api/authentication/register HTTP/1.1
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

#### Verification

	GET /api/authentication/verify/... HTTP/1.1
	Host: localhost:8080

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

#### Add role

	POST /api/internal/addrole HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...
	Content-Type: application/json
	Content-Length: 70

	{
		"roleName":"role",
		"description": "role description"
	}

#### add contact(s) and address(s)

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
	Authorization: Bearer ...
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

#### add activity type

	POST /api/crm/activity/addtype HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...
	Content-Type: application/json
	Content-Length: 63

	{
		"typeCode":"type",
		"description":"type description"
	}

#### add activity(s)

	POST /api/crm/activity/add HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...
	Content-Type: application/json

	{
		"elementcount":2,
		"elements":[
			{
				"emailAddress":"testuser@email.com",
				"activity":[
					{
					"subject":"subject",
					"notes":"notes",
					"scheduledStartDt":"yyyy-MM-ddTHH:mm:ss.SSS",
					"scheduledEndDt":"yyyy-MM-dd'T'HH:mm:ss.SSS",
					"activityType":"type"
					}
				]
			}
		]
	}

#### add subreddit

	POST /api/reddit/subreddit HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...
	Content-Type: application/json
	Content-Length: 64

	{
		"name":"test 1",
		"description":"test subreddit 1"
	}

#### add post

	POST /api/reddit/post HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...
	Content-Type: application/json

	{
		"elementcount":2,
		"elements":[
			{
				"subredditid":1,
				"posts":[
					{
					"postName":"post1",
					"description":"post1 description"
					},
					{
					"postName":"post2",
					"description":"post2 description"
					}
				]
			}
		]
	}

#### get post by id

	GET /api/reddit/post/1 HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...

#### get all subreddit

	GET /api/reddit/subreddit HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...

#### get subreddit by id

	GET /api/reddit/subreddit/3 HTTP/1.1
	Host: localhost:8080
	Authorization: Bearer ...

