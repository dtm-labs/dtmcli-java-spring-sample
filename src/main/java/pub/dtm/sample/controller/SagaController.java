package pub.dtm.sample.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.dtm.client.DtmClient;
import pub.dtm.client.model.feign.ServiceMessage;
import pub.dtm.client.saga.Saga;

/**
 * @author horseLk
 * @date 2022-05-08 17:02
 */
@RestController
public class SagaController {
    private static final Logger LOG = LogManager.getLogger(TccController.class);

    @Autowired
    private DtmClient dtmClient;

    private static final String svc = "http://localhost:8888";

    @RequestMapping("testSagaHttp")
    public String testSage() {

        try {
            // create saga transaction
            Saga saga = dtmClient
                    .newSaga()
                    .add(svc + "/TransOut", svc + "/TransOutCompensate", "")
                    .add(svc + "/TransIn", svc + "/TransInCompensate", "")
                    .enableWaitResult();

            saga.submit();
        } catch (Exception e) {
            LOG.error("saga submit error", e);
            return "fail";
        }
        return "success";
    }

    @RequestMapping("testSagaMs")
    public String testSagaMs() {
        String serviceName = "dtmcli-spring-sample";
        try {
            Saga saga = dtmClient
                    .newSaga()
                    .add(new ServiceMessage(serviceName, "/TransOut"), new ServiceMessage(serviceName, "/TransOutCompensate"), "")
                    .add(new ServiceMessage(serviceName, "/TransIn"), new ServiceMessage(serviceName, "/TransInCompensate"), "")
                    .enableWaitResult();
            saga.submit();
        } catch (Exception e) {
            LOG.error("saga submit error", e);
            return "fail";
        }
        return "success";
    }
}
