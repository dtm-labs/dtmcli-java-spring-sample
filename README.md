# dtmcli-java-spring-sample

## Start dtm
For how to start dtm, please refer to [github.com/dtm-labs/dtm](https://github.com/dtm-labs/dtm)

Specify the following configuration: (use command `dtm -c conf.yml` or `go run main.go -c conf.yml`)
``` yml
MicroService:
  Driver: 'dtm-driver-springcloud' # name of the driver to handle register/discover
  Target: '{"Addr":"127.0.0.1:8848,127.0.0.1:8848","Type":"nacos", "InstanceConfig":{"ServiceName":"dtmService","Enable":true,"Healthy":true,"Weight":10},"ClientConfig":{"NamespaceId":"c3dc917d-906a-429d-90a9-85012b41014e","UserName":"nacos","Password":"nacos","NotLoadCacheAtStart":true}}'
  EndPoint: 'localhost:36789'
```

## Start sample
``` bash
mvn exec:java -Dexec.mainClass="pub.dtm.sample.DtmcliJavaSampleV2Application"
```

## Fire a request
``` bash
curl localhost:8888/testSagaMs

curl localhost:8888/tccms
```
