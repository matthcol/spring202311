# Empty all tables in DB
& "${PSScriptRoot}/copyexecscript.ps1" "${PWD}/devops/sql/data.sql"
& "${PSScriptRoot}/copyexecscript.ps1" "${PWD}/devops/sql/pgrealignsequence.sql"
