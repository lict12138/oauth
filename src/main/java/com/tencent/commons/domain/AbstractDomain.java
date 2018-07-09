package com.tencent.commons.domain;

import com.tencent.commons.utils.DateUtils;
import com.tencent.commons.utils.UUIDGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 * 2016/1/30
 *
 * @author bobzbfeng
 */
public abstract class AbstractDomain implements Serializable {
    private static final long serialVersionUID = -4429635460660746630L;

    protected int id;

    protected String uuid = UUIDGenerator.generate();

    protected Date createTime = DateUtils.now();


    /**
     * Logic delete
     * true is deleted, default false
     */
    protected boolean archived = false;


    public AbstractDomain() {
    }

    public boolean isArchived() {
        return archived;
    }


    public String getUuid() {
        return uuid;
    }

    public String getCreateTime() {
        return DateUtils.toDateTime(createTime);
    }

    public Date getRealCreateTime(){
        return createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "{" +
                "uuid='" + uuid + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }
}
