${BASE_DIR}="${PWD}/devops/dev/postgresql"

docker stop pgdbmovie
docker rm pgdbmovie
docker run --name pgdbmovie `
  -p 5432:5432  `
  -e "POSTGRES_DB=dbmovie" `
  -e "POSTGRES_USER=movie" `
  -e "POSTGRES_PASSWORD=password" `
  -v "${BASE_DIR}/share:/mnt" `
  -d postgres:16
