package com.evantagesoft.vo.auth;
import com.evantagesoft.entities.auth.Otp;
import com.evantagesoft.vo.account.AccountVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class OtpVo {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationVo.class);

    private Long id;
    private String mobileOtp;
    private String emailOtp;
    private AccountVo accountVo;
    private Date expiredDate;
    private Date createdDate;
    private int used;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMobileOtp() {
        return mobileOtp;
    }
    public void setMobileOtp(String mobileOtp) {
        this.mobileOtp = mobileOtp;
    }
    public String getEmailOtp() {
        return emailOtp;
    }
    public void setEmailOtp(String emailOtp) {
        this.emailOtp = emailOtp;
    }
    public AccountVo getAccountVo() {
        return accountVo;
    }
    public void setAccountVo(AccountVo accountVo) {
        this.accountVo = accountVo;
    }
    public Date getExpiredDate() {
        return expiredDate;
    }
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getUsed() {
        return used;
    }
    public void setUsed(int used) {
        this.used = used;
    }

    public static Otp getAuth(OtpVo otpVo){
        Otp otp= null;
        if(otpVo!=null){
            otp= new Otp();
            String code= new DecimalFormat("000000").format(new Random().nextInt(999999));
            otp.setId(otpVo.getId());
            otp.setUsed(0);
            otp.setMobileOtp(code);
            otp.setEmailOtp(code);
            if(!StringUtils.isEmpty(otpVo.getCreatedDate())) {
                try {
                    otp.setCreatedDate(new SimpleDateFormat("dd-MM-yyyy").parse(""+otpVo.getCreatedDate()));
                } catch (Exception e) {
                    logger.error("",e);
                }
            } else{
                otp.setCreatedDate(new Date());
                otp.setExpiredDate(expiredDateMaker());
            }
            otp.setAccount(otpVo.getAccountVo() != null
                    ? AccountVo.getAccount(otpVo.getAccountVo()) : null);
        }
        return otp;
    }

    public static OtpVo getAuthVo(Otp otp){
        OtpVo otpVo= null;
        if(otp !=null){
            otpVo= new OtpVo();
            otpVo.setId(otp.getId());
            otpVo.setMobileOtp(otp.getMobileOtp());
            otpVo.setEmailOtp(otp.getEmailOtp());
            otpVo.setCreatedDate(otp.getCreatedDate());
            otpVo.setExpiredDate(otp.getExpiredDate());
            otpVo.setUsed(otp.getUsed());
            otpVo.setAccountVo(otp.getAccount() != null ? AccountVo.getAccountVo(otp.getAccount()) : null);
        }
        return otpVo;
    }

    public static Otp updateAuth(Otp otpVo){
        Otp otp= null;
        if(otpVo!=null){
            otp= new Otp();
            otp.setId(otpVo.getId());
            otp.setMobileOtp(otpVo.getMobileOtp());
            otp.setEmailOtp(otpVo.getEmailOtp());
            otp.setAccount(otpVo.getAccount());
            otp.setUsed(1);
            otp.setCreatedDate(otpVo.getCreatedDate());
            otp.setExpiredDate(otpVo.getExpiredDate());
        }
        return otp;
    }

    public static Date expiredDateMaker(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        return cal.getTime();
    }

}
