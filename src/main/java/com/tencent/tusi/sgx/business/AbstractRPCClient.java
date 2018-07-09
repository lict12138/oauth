/*
 * Copyright © 1998 - 2018 Tencent. All Rights Reserved
 * www.tencent.com
 * All rights reserved.
 */
package com.tencent.tusi.sgx.business;

import com.tencent.tusi.sgx.RPCCrypto;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import static com.tencent.commons.utils.IOTHolder.*;

/**
 * @author bobzbfeng
 */
public abstract class AbstractRPCClient {

    private static final String ADDRESS = "127.0.0.1";

    private static final int	port	= 8191;
    private static final int	timeout	= 100 * 1000;

    protected TTransport transport ;
    protected TProtocol protocol ;
    protected TMultiplexedProtocol mp1;
    protected RPCCrypto.Client client ;

    protected void initialParams(){
        transport = new TFramedTransport(new TSocket(cryotpServerIp(), cryptoServerPort(), timeout));
        protocol = new TCompactProtocol(transport);
        mp1  = new TMultiplexedProtocol(protocol, "cryptoService");
        client = new RPCCrypto.Client(mp1);
        open();
    }


    private void open() {
        if (transport != null && !transport.isOpen()) {
            try {
                transport.open();
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        }
    }


    public void close() {
        if (transport != null && transport.isOpen()) {
            transport.close();
        }
    }
}