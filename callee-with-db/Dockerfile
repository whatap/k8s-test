FROM openjdk:17-alpine

WORKDIR /app

COPY target/callee-with-db-0.0.1.jar /app/

#---------------------------
RUN wget "https://api.whatap.io/agent/whatap.agent.java.tar.gz" &&  \
    tar -zxvf whatap.agent.java.tar.gz && \
    rm -f whatap.agent.java.tar.gz

#RUN cd whatap &&  \
#    AGENT=$(find . -maxdepth 1 -type f -name "whatap.agent-*.jar") && \
#    echo "Found agent: $AGENT" && \
#    java -cp ${AGENT} whatap.agent.setup.Rename -from ${AGENT} -to whatap.agent.kube.jar &&


RUN cd whatap &&  \
    AGENT=$(find . -maxdepth 1 -type f -name "whatap.agent-*.jar") && \
    echo "Found agent: $AGENT" && \
    java -cp ${AGENT} whatap.agent.setup.Rename -from ${AGENT} -to whatap.agent.kube.jar
#---------------------------

#---------------------
#RUN mkdir -p /whatap
#COPY --from=whatap/kube_mon /data/agent/micro/whatap.agent.kube.jar /whatap
#COPY ./whatap.conf /whatap/
#---------------------

CMD ["java","-javaagent:/app/whatap.agent.kube.jar","-Dwhatap.micro.enabled=true","--add-opens=java.base/java.lang=ALL-UNNAMED","-jar", "/app/callee-with-db-0.0.1.jar"]