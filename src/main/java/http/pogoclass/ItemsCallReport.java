package http.pogoclass;

/**
 * Created by Tester3
 */
public class ItemsCallReport {

    private String calldate;
    private Boolean intercall;
    private String sim_tarif_name;
    private String dialstatus;
    private Integer sim_id;
    private Integer billsec;
    private String orig;
    private String dst;
    private Integer channel_id;
    private Integer location_id;
    private String id;
    private String uniqueid;
    private Integer duration;
    private Integer hangupcause;
    private String src;
    private Integer sim_tarif_id;

    public String getCalldate() {
        return calldate;
    }

    public void setCalldate(String calldate) {
        this.calldate = calldate;
    }

    public Boolean getIntercall() {
        return intercall;
    }

    public void setIntercall(Boolean intercall) {
        this.intercall = intercall;
    }

    public String getSim_tarif_name() {
        return sim_tarif_name;
    }

    public void setSim_tarif_name(String sim_tarif_name) {
        this.sim_tarif_name = sim_tarif_name;
    }

    public String getDialstatus() {
        return dialstatus;
    }

    public void setDialstatus(String dialstatus) {
        this.dialstatus = dialstatus;
    }

    public Integer getSim_id() {
        return sim_id;
    }

    public void setSim_id(Integer sim_id) {
        this.sim_id = sim_id;
    }

    public Integer getBillsec() {
        return billsec;
    }

    public void setBillsec(Integer billsec) {
        this.billsec = billsec;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getHangupcause() {
        return hangupcause;
    }

    public void setHangupcause(Integer hangupcause) {
        this.hangupcause = hangupcause;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getSim_tarif_id() {
        return sim_tarif_id;
    }

    public void setSim_tarif_id(Integer sim_tarif_id) {
        this.sim_tarif_id = sim_tarif_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [calldate = " + calldate + ", intercall = " + intercall + ", sim_tarif_name = " + sim_tarif_name + ", dialstatus = " + dialstatus + ", sim_id = " + sim_id + ", billsec = " + billsec + ", orig = " + orig + ", dst = " + dst + ", channel_id = " + channel_id + ", location_id = " + location_id + ", id = " + id + ", uniqueid = " + uniqueid + ", duration = " + duration + ", hangupcause = " + hangupcause + ", src = " + src + ", sim_tarif_id = " + sim_tarif_id + "]";
    }
}
