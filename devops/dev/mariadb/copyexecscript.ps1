if ($args.Count -ne 1) {
    Write-Output 'Need 1 arg to copy script'
    exit 1
}
$script_path=$args[0]
$script=(Get-Item $script_path ).Name
$dest="${PWD}/devops/dev/mariadb/share"
Write-Debug "copy and execute script in container: $script"

copy $script_path $dest

docker exec -it mariadbmovie bash `
    -c "mysql -u movie -ppassword dbmovie < /mnt/${script}"