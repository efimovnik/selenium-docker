FROM bellsoft/liberica-openjdk-alpine:17.0.8

# install curl and jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker
# add required files to run the test
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh


# run the tests
ENTRYPOINT sh runner.sh