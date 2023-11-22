if ($args.Count -ne 1) {
    Write-Output 'Need 1 arg to read table'
    exit 1
}
$tablename=$args[0]
docker exec -it mariadbmovie mysql -u movie -ppassword dbmovie -e "select * from ${tablename}"