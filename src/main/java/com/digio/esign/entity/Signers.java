package com.digio.esign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Signers {
    private String identifier;
    private String name;
    private String reason;
    private String sign_type;


}
