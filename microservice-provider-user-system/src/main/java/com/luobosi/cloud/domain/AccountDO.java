/*
 * Copyright (C) 2009-2016 Hangzhou 2Dfire Technology Co., Ltd. All rights reserved
 */
package com.luobosi.cloud.domain;

import com.luobosi.cloud.domain.base.Base;
import lombok.Data;

/**
 * AccountDO
 *
 * @author luobosi@2dfire.com
 * @since 2017-11-16
 */
@Data
public class AccountDO extends Base {
    /** 主键id */
    private String accountId;
    /** 用户名 */
    private String username;
    /** 手机号 */
    private String mobile;
    /** 密码 */
    private String password;
    /** 用户状态 0-暂停中 1-已开通 */
    private Integer accountStatus;
    /** 邮箱 */
    private String mailbox;
    /** 真实姓名 */
    private String realName;
}