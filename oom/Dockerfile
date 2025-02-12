# The base image
FROM openjdk:17 AS builder

# Set the working directory
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# This is a workaround for Docker issue #15858 (Docker can't cache the modules layer properly)
# We'll copy the modules' files separately, so Docker can cache them properly
COPY pom.xml pom.xml

# Copy rest of the project
COPY src src

# Build the application
RUN ./mvnw install -DskipTests

# This will create a smaller final image
FROM openjdk:17-alpine

# Arguments
WORKDIR /app

COPY --from=builder /app/target/oom-0.0.1.jar /app.jar

RUN wget "https://api.whatap.io/agent/whatap.agent.java.tar.gz" &&  \
    tar -zxvf whatap.agent.java.tar.gz && \
    rm -f whatap.agent.java.tar.gz

RUN cd whatap &&  \
    AGENT=$(find . -maxdepth 1 -type f -name "whatap.agent-*.jar") && \
    echo "Found agent: $AGENT" && \
    java -cp ${AGENT} whatap.agent.setup.Rename -from ${AGENT} -to whatap.agent.kube.jar

COPY ./whatap.conf /app/whatap

# Run the application
ENTRYPOINT ["java","-javaagent:/app/whatap/whatap.agent.kube.jar", "--add-opens=java.base/java.lang=ALL-UNNAMED","-Dwhatap.micro.enabled=true","-jar","/app.jar"]