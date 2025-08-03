# ./k6/Dockerfile
FROM grafana/k6:latest

WORKDIR /app

COPY ./script.js .

ENTRYPOINT ["k6", "run", "script.js"]
