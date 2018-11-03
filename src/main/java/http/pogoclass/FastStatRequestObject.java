package http.pogoclass;

/**
 * Created by Tester
 */
public class FastStatRequestObject {

    private String timestamp;
    private String id;
    private String duration;
    private String gate_id;
    private String tariff_id;
    private String calls;
    private String success;
    private String carrier_id;
    private String location_id;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGate_id() {
        return gate_id;
    }

    public void setGateId(String gate_id) {
        this.gate_id = gate_id;
    }

    public String getTariff_id() {
        return tariff_id;
    }

    public void setTariffId(String tariff_id) {
        this.tariff_id = tariff_id;
    }

    public String getCalls() {
        return calls;
    }

    public void setCalls(String calls) {
        this.calls = calls;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCarrier_id() {
        return carrier_id;
    }

    public void setCarrierId(String carrier_id) {
        this.carrier_id = carrier_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocationId(String location_id) {
        this.location_id = location_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [timestamp = " + timestamp + ", id = " + id + ", duration = " + duration + ", gate_id = " + gate_id + ", tariff_id = " + tariff_id + ", calls = " + calls + ", success = " + success + ", carrier_id = " + carrier_id + ", location_id = " + location_id + "]";
    }
}
