/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.commons.domain;

import java.io.Serializable;

/**
 * @author bobzbfeng
 */
public class AbstractDto implements Serializable{
    private static final long serialVersionUID = -3391393363950192321L;

    protected String uuid
            ;
    protected String createTime;

    protected boolean archived;

    public AbstractDto() {
    }

    public AbstractDto(AbstractDomain domain) {
        this.uuid = domain.getUuid();
        this.createTime = domain.getCreateTime();
        this.archived = domain.isArchived();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}