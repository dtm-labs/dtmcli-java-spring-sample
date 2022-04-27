package pub.dtm.sample.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.dtm.client.constant.Constants;
import pub.dtm.client.model.responses.DtmResponse;

@RestController
public class TransController {
    private static final Logger logger = LogManager.getLogger(TransController.class);

    @RequestMapping("TransOutTry")
    public Object TransOutTry() {
        logger.info("TransOutTry");
        return DtmResponse.buildDtmResponse(Constants.SUCCESS_RESULT);
    }

    @RequestMapping("TransOutConfirm")
    public Object TransOutConfirm() {
        logger.info("TransOutConfirm");
        return DtmResponse.buildDtmResponse(Constants.SUCCESS_RESULT);

    }

    @RequestMapping("TransOutCancel")
    public Object TransOutCancel() {
        logger.info("TransOutCancel");
        return DtmResponse.buildDtmResponse(Constants.SUCCESS_RESULT);

    }

    @RequestMapping("TransInTry")
    public Object TransInTry() {
        logger.info("TransInTry");
        return DtmResponse.buildDtmResponse(Constants.SUCCESS_RESULT);

    }

    @RequestMapping("TransInConfirm")
    public Object TransInConfirm() {
        logger.info("TransInConfirm");
        return DtmResponse.buildDtmResponse(Constants.SUCCESS_RESULT);
    }

    @RequestMapping("TransInCancel")
    public Object TransInCancel() {
        logger.info("TransInCancel");
        return DtmResponse.buildDtmResponse(Constants.SUCCESS_RESULT);
    }
}
