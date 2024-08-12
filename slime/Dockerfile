# The base image
FROM openjdk:17-alpine AS builder

# Set the working directory
WORKDIR /workspace/app

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
WORKDIR /workspace/app
COPY --from=builder /workspace/app/target/slime-0.0.1.jar /app.jar

RUN mkdir -p /whatap
COPY --from=whatap/kube_mon /data/agent/micro/whatap.agent.kube.jar /whatap
COPY ./whatap.conf /whatap/

# Run the application
ENTRYPOINT ["java","-javaagent:/whatap/whatap.agent.kube.jar", "--add-opens=java.base/java.lang=ALL-UNNAMED","-Dwhatap.micro.enabled=true","-jar","/app.jar"]