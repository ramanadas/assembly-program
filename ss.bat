@echo off
SETLOCAL

echo Starting Docker MySQL service...
docker-compose up -d mysql

echo Waiting for MySQL service to be ready...
:check_mysql_health
FOR /F "tokens=*" %%i IN ('docker inspect --format="{{.State.Health.Status}}" $(docker-compose ps -q mysql)') DO (
    SET "STATUS=%%i"
)
IF "%STATUS%" == "healthy" (
    GOTO mysql_ready
) ELSE (
    TIMEOUT /T 1 > NUL
    GOTO check_mysql_health
)

:mysql_ready
echo MySQL is healthy.

echo Starting Docker app service...
docker-compose up -d app

echo Both services are up and running.
ENDLOCAL
