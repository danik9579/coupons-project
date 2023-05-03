package com.coupons.timertask;

import com.coupons.logic.CouponsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

@Component
public class ExpiredCouponsTimerTask extends TimerTask {
    private CouponsLogic couponsLogic;

    @Autowired
    public ExpiredCouponsTimerTask(CouponsLogic couponsLogic) {
        this.couponsLogic = couponsLogic;
    }

    @Override
    public void run() {
        Calendar now = Calendar.getInstance();
        Date date = now.getTime();
        couponsLogic.removeExpiredCoupons(date);
    }
}
