package com.featurerich.application;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feature {

    private String module;

    private String path;

    private Integer priority;

    private String messageCode;

    private String preAuthorizeValue;
}
