package com.unicommerce.utilities.model.tracelog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class TenantDTO {
    private String code;
    private String name;
    private String accessUrl;

    public TenantDTO(String code, String name, String accessUrl) {
        this.code = code;
        this.name = name;
        this.accessUrl = accessUrl;
    }
    public TenantDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
