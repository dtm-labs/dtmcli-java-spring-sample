package pub.dtm.sample.consumer;

import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pub.dtm.client.model.feign.ServiceMessage;
import pub.dtm.client.tcc.Tcc;
import pub.dtm.sample.param.TransReq;

import java.util.HashMap;
import java.util.Map;

public class TccConsumer {
    private static final Logger LOG = LogManager.getLogger(TccConsumer.class);

    private static final String svc = "http://localhost:8888";

    private static final String appName = "dtmcli-spring-sample";

    public static void tccTransForHttp(Tcc tcc) throws Exception {
        Response outResponse = tcc.callBranch( "", svc + "/TransOutTry", svc + "/TransOutConfirm",
                svc + "/TransOutCancel");
        LOG.info("out response is {}", outResponse);
        Response inResponse = tcc.callBranch("", svc + "/TransInTry", svc + "/TransInConfirm",
                svc + "/TransInCancel");
        LOG.info("in response is {}", inResponse);
    }

    public static void  tccTransForMicroService(Tcc tcc) throws Exception {
        Map<String, String> outRes = null;
        tcc.callBranch(new HashMap<>(), new ServiceMessage(appName, "/TransOutTry"),
                new ServiceMessage(appName, "/TransOutConfirm"), new ServiceMessage(appName, "/TransOutCancel"));
        LOG.info("out res is {}", outRes);
        Map<String, String> inRes = null;
        tcc.callBranch(new HashMap<>(), new ServiceMessage(appName, "/TransInTry"),
                new ServiceMessage(appName, "/TransInConfirm"), new ServiceMessage(appName, "/TransInCancel"));
        LOG.info("in res is {}", inRes);
    }

    /**
     * 定义tcc事务函数，内部需要通过callBranch注册事务子分支
     *
     * @param tcc
     * @return
     */
    public static void tccBarrierTrans(Tcc tcc) throws Exception {
        // 用户1 转出30元
        Response outResponse = tcc.callBranch(new TransReq(1, -30), svc + "/barrierTransOutTry", svc + "/barrierTransOutConfirm",
                svc + "/barrierTransOutCancel");
        LOG.info("out response is {}", outResponse);
        // 用户2 转入30元
        Response inResponse = tcc.callBranch(new TransReq(2, 30), svc + "/barrierTransInTry", svc + "/barrierTransInConfirm",
                svc + "/barrierTransInCancel");
        LOG.info("in response is {}", inResponse);
    }

    /**
     * 定义tcc事务函数，内部需要通过callBranch注册事务子分支， 转账金额大于余额 转账失败
     *
     * @param tcc
     * @return
     */
    public static void tccBarrierTransError(Tcc tcc) throws Exception {
        // 用户1 转出100000元
        Response outResponse = tcc.callBranch(new TransReq(1, -100000), svc + "/barrierTransOutTry", svc + "/barrierTransOutConfirm",
                svc + "/barrierTransOutCancel");
        LOG.info("out response is {}", outResponse);
        // 用户2 转入100000元
        Response inResponse = tcc.callBranch(new TransReq(2, 100000), svc + "/barrierTransInTry", svc + "/barrierTransInConfirm",
                svc + "/barrierTransInCancel");
        LOG.info("in response is {}", inResponse);
    }

    public static void tccBarrierTransMs(Tcc tcc) throws Exception {
        Map<String, String> outRes = null;
        tcc.callBranch(new TransReq(1, -30), new ServiceMessage(appName, "/barrierTransOutTry"),
                new ServiceMessage(appName, "/barrierTransOutConfirm"), new ServiceMessage(appName, "/barrierTransOutCancel"));
        LOG.info("out res is {}", outRes);
        Map<String, String> inRes = null;
        tcc.callBranch(new TransReq(2, 30), new ServiceMessage(appName, "/barrierTransInTry"),
                new ServiceMessage(appName, "/barrierTransInConfirm"), new ServiceMessage(appName, "/barrierTransInCancel"));
        LOG.info("in res is {}", inRes);
    }


    public static void tccBarrierTransErrorMs(Tcc tcc) throws Exception {
        Map<String, String> outRes = null;
        tcc.callBranch(new TransReq(1, -100000), new ServiceMessage(appName, "/barrierTransOutTry"),
                new ServiceMessage(appName, "/barrierTransOutConfirm"), new ServiceMessage(appName, "/barrierTransOutCancel"));
        LOG.info("out res is {}", outRes);
        Map<String, String> inRes = null;
        tcc.callBranch(new TransReq(2, 100000), new ServiceMessage(appName, "/barrierTransInTry"),
                new ServiceMessage(appName, "/barrierTransInConfirm"), new ServiceMessage(appName, "/barrierTransInCancel"));
        LOG.info("in res is {}", inRes);
    }
}
