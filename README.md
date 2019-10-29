# Polywood API

You can find **API documentation** [there](API.md)


### Introduction

This an API for Polywood project that communicate with 2 services :
> [Movies service](#https://github.com/PolywoodOrganization/polywood-film-service)

> [Actors service](#https://github.com/PolywoodOrganization/polywood-actor-service)

This API allow to fetch movies and actors from a bolywood database.
This database has been created from IMDB data with [Talend](#https://fr.talend.com/) software. 

    API port : 8083
    
#### Technologies

    Spring Boot
    MySQL
    Maven

#### Launch

In order to launch this API on your computer you must :
- create locally a `polywood` database and import [polywood.sql](#polywood.sql) script
- launch `polywood-film-service` application
- launch `polywood-actor-service` application