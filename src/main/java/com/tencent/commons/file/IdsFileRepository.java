package com.tencent.commons.file;


/**
 * 2016/2/18
 *
 * @author bobzbfeng
 */

public interface IdsFileRepository {


    void saveIdsFile(IdsFile idsFile);

    IdsFile findByGuid(String guid);

    void removeIdsFile(IdsFile idsFile);

}