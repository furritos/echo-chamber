# {{ echo chamber }}
## Spring Boot Map-Based Repository

Purpose-built REST server Maven project that can be used to capture POST requests and webhooks.  Built in a lightweight manner by utilizing Java `Map` objects instead of a full blown database backend via [`spring-data-keyvalue`](https://github.com/spring-projects/spring-data-keyvalue) module

Features include: 

 * Key-based repository using [`spring-data-keyvalue`](https://github.com/spring-projects/spring-data-keyvalue) module.
 * Configured to support [`init.d`](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment-install.html)  system services.
 * API that allows the capture of `POST` events; Payloads can be then read via `GET` calls.

Features to be worked on:

 * Expired API requests are not cleared out after a fixed amount of time (even though the payload accomodates an `expires` value).  Currently, the only way to clear out expired requests is to restart the application.
 * UI to complement the API-access only functionality currently available.
 * More functional features...

### Usage

**NOTE**: This project is built with Java JDK 1.8.

Clone the project

 * `git clone https://github.com/furritos/echo-chamber`

Run the project	

 * `mvn spring-boot:run`

## API

 * GET `UUID` Key: `http://localhost:8080/api/uuid`

 * POST JSON Payload: `http://localhost:8080/api/webhook/{uuid}`
   * `POST` any `JSON` payload you want in this endpoint.  See examples below for further details.

 * GET Payloads for given `UUID`: `http://localhost:8080/webhook/{uuid}`
   *  `GET` a list of all `JSON` payloads that were submitted through the `POST` endpoint.

### Example

First thing you'll want to do is get an API key:

`curl -H "Accept: application/json" http://localhost:8080/api/uuid`

```
{
	"apikey": "9e601ff3-60f8-4c1f-86d7-c94d0d9c6a5a",
	"expires": "2019-03-11T04:10:51.285",
	"message": "SUCCESS | OK",
	"payloads": []
}
```
- - -

From there, `POST` any `JSON` payload you want:

`curl -X POST -H "Content-type: application/json" -d '{"name":"bob","role":{"thing":"thing2"}}' http://localhost:8080/api/webhook/9e601ff3-60f8-4c1f-86d7-c94d0d9c6a5a`

- - -

Finally, `GET` a list of payloads for your `UUID`:

`curl -H "Accept: application/json" http://localhost:8080/api/webhook/9e601ff3-60f8-4c1f-86d7-c94d0d9c6a5a`

```
{
	"apikey": "9e601ff3-60f8-4c1f-86d7-c94d0d9c6a5a",
	"expires": "2019-03-11T04:10:51.285",
	"message": "SUCCESS | OK",
	"payloads": [{
		"name": "bob",
		"role": {
			"thing": "thing2"
		}
	}]
}
```