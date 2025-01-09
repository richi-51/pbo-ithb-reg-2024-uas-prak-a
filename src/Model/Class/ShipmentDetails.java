package src.Model.Class;

import java.util.Date;

import src.Model.Enum.Status;

public class ShipmentDetails {
    private int id_shipment, trans_id;
    private Status status;
    private String current_position;
    private String evidence;
    private Date date;
    private String updated_by;


    // Contructor
    public ShipmentDetails(int id_shipment, int trans_id, Status status, String current_position, String evidence,
            Date date, String updated_by) {
        this.id_shipment = id_shipment;
        this.trans_id = trans_id;
        this.status = status;
        this.current_position = current_position;
        this.evidence = evidence;
        this.date = date;
        this.updated_by = updated_by;
    }


    // Getter and Setter
    public int getId_shipment() {
        return id_shipment;
    }
    public void setId_shipment(int id_shipment) {
        this.id_shipment = id_shipment;
    }
    public int getTrans_id() {
        return trans_id;
    }
    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getCurrent_position() {
        return current_position;
    }
    public void setCurrent_position(String current_position) {
        this.current_position = current_position;
    }
    public String getEvidence() {
        return evidence;
    }
    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getUpdated_by() {
        return updated_by;
    }
    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}
