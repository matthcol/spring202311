if ($args.Count -ne 1) {
    Write-Output 'Need 1 arg to read table'
    exit 1
}
$tablename=$args[0]

docker exec -it pgdbmovie bash `
    -c "PGPASSWORD=password psql -U movie -h localhost -d dbmovie -c 'select * from ${tablename}'"