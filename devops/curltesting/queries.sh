# get all movie
curl -X 'GET' 'http://localhost:8081/api/movie'

# get movie by id
curl -X 'GET' 'http://localhost:8081/api/movie/1'

# add movie
 curl -X 'POST' \
   'http://localhost:8081/api/movie' \
   -H 'accept: */*' \
   -H 'Content-Type: application/json' \
   -d '{
   "title": "The Batman",
   "year": 2022,
   "duration": 120
 }'

 #update
 curl -X 'PUT' \
   'http://localhost:8081/api/movie' \
   -H 'accept: */*' \
   -H 'Content-Type: application/json' \
   -d '{
   "title": "The Batman",
   "year": 2021,
   "duration": null,
   "synopsis": "super movie with Batman",
   "posterUri": "url to poster",
   "id": 5
 }'

 # delete
 curl -X 'DELETE' \
   'http://localhost:8081/api/movie/{id}?id=1' \
   -H 'accept: */*'

# add person
 curl -X 'POST' \
    'http://localhost:8081/api/person' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
    "name": "John Doe",
    "birthdate": "2003-12-15"
  }'

# ------------  Invalid data  ------------------

 curl -X 'POST' \
   'http://localhost:8081/api/movie' \
   -H 'accept: */*' \
   -H 'Content-Type: application/json' \
   -d '{
   "title": "The Batman",
   "duration": 120
 }'

 curl -X 'POST' \
   'http://localhost:8081/api/person' \
   -H 'accept: */*' \
   -H 'Content-Type: application/json' \
   -d '{
   "name": "John Doe",
   "birthdate": "aaaa"
 }'

curl -X 'POST' \
   'http://localhost:8081/api/person' \
   -H 'accept: */*' \
   -H 'Content-Type: application/json' \
   -d '{
   "name": "John Doe",
   "birthdate": "2023-02-29"
 }'

 curl -X 'POST' \
    'http://localhost:8081/api/person' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
    "name": "John Doe",
    "birthdate": "2023-12-15"
  }'








