if ($args.Count -ne 1) {
    Write-Output 'Need 1 arg to copy script'
    exit 1
}
$script_path=$args[0]
$script=(Get-Item $script_path ).Name
$dest="${PWD}/devops/dev/postgresql/share"
Write-Debug "copy and execute script in container: $script"

copy $script_path $dest

docker exec -it pgdbmovie bash `
    -c "PGPASSWORD=password psql -U movie -h localhost -d dbmovie -f /mnt/${script}"