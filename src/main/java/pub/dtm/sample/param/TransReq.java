package pub.dtm.sample.param;

public class TransReq {
    /**
     * 用户id
     */
    private int userId;

    /**
     * 转入/转出金额
     */
    private int amount;

    public TransReq(int userId, int amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
