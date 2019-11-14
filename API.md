API overview
============

## Content

- [Notes](#Notes)
- [Methods](#Methods specifications)

___
## Notes


### Security

Every request for data must have **token** informed in header. 

```html
Authorization: Bearer <token>
```

### Database schema

![Schema](schema_db.png)


### Operations 

###### Modify 
Add, delete and update casting

###### Fetch
Ask movies and actors data

## Methods specifications

### Movies

#### Fetching all movies:

    GET /movies?page=0&size=10&sort=title
    
| Parameter     | Description                               | Optional |
| -----------   | ----------------------------------------- | -------- |
| `page`        | Page offset                               |   Yes    |
| `size`        | Size limit / number of movies to fetch    |   Yes    |
| `sort`        | Column name for sorting                   |   Yes    |

Sort parameter should be a valid column name from [Movie](#movie) Entity. 

Returns a [Movie](#movie).

#### Getting a movie by Id :

    GET /movies/:id

| Attribute   | Description         |
| ----------- | ------------------- |
| `id`        | imdbid              |

#### Getting a movie image by Id :

    GET /movies/image/:id

| Attribute   | Description         |
| ----------- | ------------------- |
| `id`        | imdbid              |

#### Getting a movie casting by Id :

    GET /movies/casting/:id

| Attribute   | Description         |
| ----------- | ------------------- |
| `id`        | imdbid              |

### Actors

#### Fetching all actor:

    GET /actors?page=0&size=10&sort=title
    
| Parameter     | Description                               | Optional |
| -----------   | ----------------------------------------- | -------- |
| `page`        | Page offset                               |   Yes    |
| `size`        | Size limit / number of actors to fetch    |   Yes    |
| `sort`        | Column name for sorting                   |   Yes    |

Sort parameter should be a valid column name from [Actor](#actor) Entity. 

Returns a list of [Actor](#actor).

#### Getting an actor by Id :

    GET /actors/:id

| Attribute   | Description         |
| ----------- | ------------------- |
| `id`        | actorid             |

Returns a [Actor](#actor).

#### Getting a movie image by Id :

**🚧 Work in progress : not avalaibale yet**

    GET /actors/image/:id

| Attribute   | Description         |
| ----------- | ------------------- |
| `id`        | actorid             |

#### Getting an actor filmography by Id :

    GET /actors/filmography/:id

| Attribute   | Description         |
| ----------- | ------------------- |
| `id`        | actorid             |

Return a list of [Movie](#movie)

## Entities

#### Movie

| Attribute                | Description                                              | Nullable |
|--------------------------|----------------------------------------------------------|----------|
| `imdbid`                 | IMDB Movie ID and primary Key                            | no       |
| `title`                  | Title of the movie                                       | no       |
| `releaseyear`            | Release Year                                             | no       |
| `releasedate`            | Release Date                                             | no       |
| `genre`                  | Movie genre                                              | no       |
| `writers`                | List of writers                                          | no       |
| `actors`                 | List of actors                                           | no       |

#### Actor

| Attribute              | Description                                              | Nullable |
|------------------------|----------------------------------------------------------|----------|
| `actorid` 🔑           | Actor id and primary Key                                 | no       |
| `name`                 | Name of the actor                                        | no       |
| `moviecount`           | Number of movies done by Actor                           | no       |
| `ratingsum`            | Number of rating given by users                          | no       |
| `normalizedmovierank`  | Actor rank over others                                   | no       |
| `googlehits`           | Actor number of hits on google search                    | no       |
| `normalizedgooglerank` | Actor rank on google                                     | yes       |
| `normalizedrating`     | Actor global rating by users                             | no       |

#### Casting

| Attribute              | Description                                              | Nullable |
|------------------------|----------------------------------------------------------|----------|
| `movieid`  🔑          | Actor id and primary Key                                 | no       |
| `actorid`  🔑          | Name of the actor                                        | no       |

Database was build with Talend with the following [bolywood dataset](https://www.kaggle.com/mitesh58/bollywood-movie-dataset)