package pub.dtm.sample.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.dtm.client.DtmClient;
import pub.dtm.sample.consumer.TccConsumer;

@RestController
public class TccController {
    private static final Logger LOG = LogManager.getLogger(TccController.class);
    @Autowired
    private DtmClient dtmClient;

    @GetMapping("/tccHttp")
    public String tccTestForHttp() {
        LOG.info("in tccTest Controller");
        try {
            dtmClient.tccGlobalTransaction(TccConsumer::tccTransForHttp);
        } catch (Exception e) {
            LOG.error("tccGlobalTransaction error", e);
            return "fail";
        }
        LOG.info("out tccTest Controller, {}", "success");
        return "success";
    }

    @GetMapping("/tccms")
    public String tccTestForMicroService() {
        LOG.info("in tccTest Controller");
        try {
            dtmClient.tccGlobalTransaction(TccConsumer::tccTransForMicroService);
        } catch (Exception e) {
            LOG.error("tccGlobalTransaction error", e);
            return "fail";
        }
        LOG.info("out tccTest Controller, {}", "success");
        return "success";
    }

    /**
     * 具有子事务屏障功能的tcc demo (转账成功)
     *
     * @return
     */
    @RequestMapping("tccBarrierHttp")
    public String tccBarrier() {
        LOG.info("in tccTest Controller");
        try {
            dtmClient.tccGlobalTransaction(TccConsumer::tccBarrierTrans);
        } catch (Exception e) {
            LOG.error("tccGlobalTransaction error", e);
            return "fail";
        }
        LOG.info("out tccTest Controller, {}", "success");
        return "success";
    }

    /**
     * 具有子事务屏障功能的tcc demo (转账失败)
     *
     * @return
     */
    @RequestMapping("tccBarrierErrorHttp")
    public String tccBarrierError() {
        LOG.info("in tccTest Controller");
        try {
            dtmClient.tccGlobalTransaction(TccConsumer::tccBarrierTransError);
        } catch (Exception e) {
            LOG.error("tccGlobalTransaction error", e);
            return "fail";
        }
        LOG.info("out tccTest Controller, {}", "success");
        return "success";
    }

    @RequestMapping("tccBarrierMs")
    public String tccBarrierMs() {
        LOG.info("in tccTest Controller");
        try {
            dtmClient.tccGlobalTransaction(TccConsumer::tccBarrierTransMs);
        } catch (Exception e) {
            LOG.error("tccGlobalTransaction error", e);
            return "fail";
        }
        LOG.info("out tccTest Controller, {}", "success");
        return "success";
    }

    @RequestMapping("tccBarrierErrorMs")
    public String tccBarrierErrorMs() {
        LOG.info("in tccTest Controller");
        try {
            dtmClient.tccGlobalTransaction(TccConsumer::tccBarrierTransErrorMs);
        } catch (Exception e) {
            LOG.error("tccGlobalTransaction error", e);
            return "fail";
        }
        LOG.info("out tccTest Controller, {}", "success");
        return "success";
    }
}
