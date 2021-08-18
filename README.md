
## This repo contains the SpringBoot and the React Github Review Karma App we've made during our Java 21-3 course.

* Clone project by execute:

`git clone git@github.com:neuefische/rem-21-3-github.git`
                                
## React in folder 'frontend'

* Build the frontend by

`npm install` 

* Run the frontend by

`npm run start`

## Spring Boot in folder 'backend'

* This project requires __Java 16__ and __MAVEN__, build by execute in the project root:

* Build the backend by

`mvn clean package`

* Run the backend by

`mvn spring-boot:run`

* This app will be automatically deployed at Heroku on pushes to the main branch. API available at 

[Hosted at Heroku](https://rem-21-3-github.herokuapp.com/api/rem213)

* Swagger is available at:

[Hosted Swagger at Heroku](http://rem-21-3-github.herokuapp.com/api/rem213/swagger-ui/)
