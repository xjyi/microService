package com.xianjinyi.gameProvider.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: xianjinyi
 * @date 2019/05/23
 */

@Data
public class AutoSignRequest {

    /**
     * 请求url
     */
    @NotBlank
    private String url;

    /**
     * 交易密码
     */
    @NotBlank
    private String cgPwd;

    /**
     * 签约跟踪码
     */
    private String trace;
}
