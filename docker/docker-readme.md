# docker readme

> 도커 컴포즈로 개발DB 올리는 방법을 소개합니다!

`docker-compose.yml` 디렉토리에서 명령어를 실행한다.
```shell
docker compose up
```

- pgadmin은 `localhost:5050`처럼 로컬호스트로 접속한다.
- pgadmin에서 db에 접속할때는 아래 명령어로 IP를 확인한다.
```shell
docker ps | grep pg_container | awk '{print $1}'
docker inspect af84 | grep IPAddress
```

postgis등 관련 라이브러리 설치를 위해 도커 빌드파일을 구성해줬다.
```text
FROM postgres:14.1
RUN apt-get update && apt-get  install -y postgresql-14-postgis-3  
CMD ["/usr/local/bin/docker-entrypoint.sh","postgres"]
```

또는, 도커가 올라오면 터미널에 직접 붙어서 설치해줘도 된다.
```shell
apt-get update && apt-get install -y postgresql-14-postgis-3
```