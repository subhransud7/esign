package com.digio.esign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    private List<Signers> signers;
    private int expire_in_days;
    private String display_on_page;
    private String file_name;
    private String file_data;
    private Boolean send_sign_link;
    private Boolean will_self_sign;
    private String signatory;
    private String callback;
    private String comment;
    private Boolean sequential;
    private Object sign_coordinates;
    private Boolean notify_signers;

    public List<Signers> getSigners() {
        return signers;
    }

    public void setSigners(List<Signers> signers) {
        this.signers = signers;
    }

    public int getExpire_in_days() {
        return expire_in_days;
    }

    public void setExpire_in_days(int expire_in_days) {
        this.expire_in_days = expire_in_days;
    }

    public String getDisplay_on_page() {
        return display_on_page;
    }

    public void setDisplay_on_page(String display_on_page) {
        this.display_on_page = display_on_page;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
