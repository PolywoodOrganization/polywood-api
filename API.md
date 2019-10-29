API overview
============

## Content

- [Notes](#notes)
- [Methods](#methods)

___

## Database


![Schema](schema_db.png)



###### Modify 
Add, delete and update casting

###### Fetch
Ask movies and actors data


___

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
| `id`        | ImdbId              |

#### Getting a movie image by Id :

    GET /movies/image/:id

| Attribute   | Description         |
| ----------- | ------------------- |
| `id`        | ImdbId              |

#### Getting a movie casting by Id :

    GET /movies/casting/:id

| Attribute   | Description         |
| ----------- | ------------------- |
| `id`        | ImdbId              |


## Entities

#### Movie

| Attribute                | Description                                              | Nullable |
|--------------------------|----------------------------------------------------------|----------|
| `ImdbId`                 | IMDB Movie ID and primary Key                            | no       |
| `title`                  | Title of the movie                                       | no       |
| `releaseYear`            | Release Year                                             | no       |
| `releaseDate`            | Release Date                                             | no       |
| `genre`                  | Movie genre                                              | no       |
| `writers`                | List of writers                                          | no       |
| `actors`                 | List of actors                                           | no       |