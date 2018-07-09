package com.tencent.commons.file;


import com.tencent.commons.domain.AbstractDomain;
import com.tencent.commons.web.context.BeanProvider;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.Assert;

/**
 * 定义一个文件信息
 *
 * @author bobzbfeng
 */
public class IdsFile extends AbstractDomain {

    private static final long serialVersionUID = -5200293878666693774L;


    protected transient FileWarehouse fileWarehouse = BeanProvider.getBean(FileWarehouse.class);

    protected transient IdsFileRepository fileRepository = BeanProvider.getBean(IdsFileRepository.class);


    //Original file name
    protected String name;

    protected String path;
    //e.g. txt;   file suffix
    protected String suffix;

    //If temp file or not, default false
    protected boolean temp = false;
    //File original size, unit: byte
    protected long size;

    protected transient byte[] data;

    public IdsFile() {
    }

    public IdsFile(String name, byte[] data) {
        Assert.notNull(name, "name is null");
        this.name = name;
        this.suffix = FilenameUtils.getExtension(name);
        this.data = data;
        this.size = data.length;
    }

    public String name() {
        return name;
    }

    //Update suffix at the same time
    public IdsFile name(String name) {
        this.name = name;
        this.suffix = FilenameUtils.getExtension(name);
        return this;
    }


    public String suffix() {
        return suffix;
    }

    public byte[] data() {
        if (data == null) {
            data = fileWarehouse.read(path);
        }
        return data;
    }

    public String path() {
        return path;
    }


    public void saveFile() {
        this.path = fileWarehouse.write(this.data);
        this.fileRepository.saveIdsFile(this);
    }

    public void deleteFile() {
        fileWarehouse.delete(this.path);
        this.fileRepository.removeIdsFile(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{temp=").append(temp);
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append(", suffix='").append(suffix).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public boolean temp() {
        return temp;
    }

    public IdsFile temp(boolean temp) {
        this.temp = temp;
        return this;
    }

    public long size() {
        return size;
    }

    public IdsFile size(long size) {
        this.size = size;
        return this;
    }


}