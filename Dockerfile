FROM ubuntu:latest
LABEL authors="Mosquera"

ENTRYPOINT ["top", "-b"]