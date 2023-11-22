${BASE_DIR}="${PWD}\devops\dev\mariadb"

docker stop mariadbmovie
docker rm mariadbmovie

docker run --name mariadbmovie `
  -p 3306:3306  `
  -e "MARIADB_DATABASE=dbmovie" `
  -e "MARIADB_USER=movie" `
  -e "MARIADB_PASSWORD=password" `
  -e "MARIADB_ROOT_PASSWORD=password" `
  -v "${BASE_DIR}/share:/mnt" `
  -d mariadb:10
