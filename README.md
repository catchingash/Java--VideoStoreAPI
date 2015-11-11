# Project: VideoStoreAPI

The overall goal of this project is to create a system that a video store (remember those?) could use to track their inventory of rental videos and their collection of customers.

We will use [Java](https://en.wikipedia.org/wiki/Java_(programming_language)) to construct a RESTful API. The goal of this API is to quickly serve information about the store's video collection, customer information, and to update rental status. This repository provides two JSON datafiles to serve as the initial seeds for this system.

`movies.json` contains information about the videos available to rent at the store. The data is presented as an array of objects, with each object having the following key-value pairs:

- `title`: The title of the film
- `overview`: A short plot synopsis
- `release_date`: When the film was originally released
- `inventory`: How many copies of the film the video store owns

`customers.json` contains information about the customers that have rented with the store in the past. The data is presented as, you guessed it, an array of objects, with each object have the following key-value pairs:

- `id`: A unique identifier
- `name`: The customer's name
- `registered_at`: When the customer first visited the store
- The customer's physical address, composed of:
  - `address`
  - `city` 
  - `state`
  - `postal_code`
- `phone`: Primary contact phone number
- `account_credit`: For reason we'd rather not get into, the store owes all of their customers a little bit of money; this amount is made available to customers as credit toward future rentals.

## Project Baseline

- Read the API Requirements below and create a pseudo-code "routes" file that
  - defines the _endpoints_ your API will need
  - the _HTTP verbs_ each endpoint will use
  - and any data that must be provided to the endpoint in order for it to do its work.
- Create a new Java/Spring app to serve as the API.
- Create a route that responds to `/zomg` that serves a json-encoded "it works!" method.
- Seed your database using the provided json data.

## API Requirements

The API you build should have the following capabilities. The schema of your database/datastore, and the structure of the endpoints are completely up to you.

### Authentication
- There is not an authentication requirement for this project; assume all users interacting with the API are video store employees.

### Customers
- Retrive a list of all customers ("/customers/index")
- Retrive a subset of customers ("customers/index?sort=____")
  - Given a sort column, return _n_ customer records
  - Sort columns are
    - `name`
    - `registered_at`
    - `postal_code`

### Movies
- Retrieve a list of all movies
- Retrieve a subset of movies
  - Given a sort column, return _n_ movie records
  - Sort columns are
    - `title`
    - `release_date`
- Look a movie up by title to see
  - its synopsis
  - release date
  - and inventory total

### Interface
- This part of the project is purely an API; all interactions should happen over HTTP requests. There is no front-end, user-facing interface.

### Testing
- We will use [JUnit](http://junit.org/) for tests.

### Building and Running

- Install Maven:
  - `brew install maven`
- In IntelliJ, Import a project (you can do this from the first screen when IntelliJ loads)
  - If you need to get there from an open project, go to File -> Close Project
  - Select the pom.xml file in this project to import.  Leave the settings as default.
- Once the project loads, select Make Project from the Build menu to make sure everything builds.
- To set up debugging:
  - From the menu, choose Run -> Edit Configurations...
  - Press the + button at the top left, then choose "Remote"
  - On the configuration tab that comes up on the right, leave Host as localhost and change port to 8000.
  - At the top of the dialog name this configuration something like "DebugTomcat"
  - Press OK
- To run the server:
  - Open a terminal at the root folder of this project.
  - `mvn tomcat:run`
  - Go to http://localhost:8080/VideoStoreAPI/foo/name
- To debug the server:
  - Set breakpoints
  - Open a terminal at the root folder of this project.
  - `mvnDebug tomcat:run`
  - Make sure your run configuration in the dropdown at the top right is "DebugTomcat" or whatever you named your debug configuraiton, then press the bug button.
  - Go to http://localhost/VideoStoreAPI/foo/name and verify your breakpoints.

#### Dependencies
- [postgresql-9.4-1205-jdbc41.jar](https://jdbc.postgresql.org/download.html)
