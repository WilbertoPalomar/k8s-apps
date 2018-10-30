VERSION=v14
PROJECT_ID?=google_samples
PROJECT=gcr.io/${PROJECT_ID}
CASSANDRA_VERSION=3.11.2

all: kubernetes-cassandra.jar build

build-go:
	go build -a -installsuffix cgo \
		-ldflags "-s -w" \
		-o image/files/cassandra-seed.so -buildmode=c-shared go/main.go

kubernetes-cassandra.jar:
	@echo "Building kubernetes-cassandra.jar"
	docker run -v ${PWD}/java:/usr/src/app maven:3-jdk-8-onbuild-alpine mvn clean install
	cp java/target/kubernetes-cassandra*.jar image/files/kubernetes-cassandra.jar

container:
	@echo "Building ${PROJECT}/cassandra:${VERSION}"
	docker build --pull --build-arg "CASSANDRA_VERSION=${CASSANDRA_VERSION}" -t ${PROJECT}/cassandra:${VERSION} image

container-dev:
	docker build --pull --build-arg "CASSANDRA_VERSION=${CASSANDRA_VERSION}" --build-arg "DEV_CONTAINER=true" -t ${PROJECT}/cassandra:${VERSION}-dev image

build: container container-dev

push: build
	gcloud docker -- push ${PROJECT}/cassandra:${VERSION}
	gcloud docker -- push ${PROJECT}/cassandra:${VERSION}-dev

.PHONY: all build push
