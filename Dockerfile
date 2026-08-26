FROM ubuntu:latest
LABEL authors="pravin"

ENTRYPOINT ["top", "-b"]