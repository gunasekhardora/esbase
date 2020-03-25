# ESBase

## Getting started

- `mvn assembly:assembly`
- `java -jar target/esbase-jar-with-dependencies.jar`

### To start ZooKeeper locally
- In ZK root dir `bin/zkServer.sh start`
- To check status `bin/zkServer.sh status`

### To run SolrCloud locally and point it to ZK
- In Solr root dir `bin/solr -c  -a "-XX:-OmitStackTraceInFastThrow -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:4044" -z localhost:2181`

### To monitor app locally
- `curl "localhost:9090/api/monitor"`
