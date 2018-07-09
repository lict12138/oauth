package com.tencent.iot.domain;

import com.tencent.commons.utils.IOTHolder;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 硬件信息
 */
public class SE implements Serializable{
    private static final long serialVersionUID = -4884518816939879722L;
    int		downCounter;
	String	hid;
	String	upSeed;
	String	downSeed;
	String	macSeed;
	String	rsapubkey;
	String	eccpubkey;
	String	encryptedkey;
	int		symmetricType;
	int		asymmetricType;
	int		algType;

    String masterKeyName;

	public int getAlgType() {
		return algType;
	}

	public void setAlgType(int algType) {
		this.algType = algType;
	}

	public int getSymmetricType() {
		return symmetricType;
	}

	public void setSymmetricType(int symmetricType) {
		this.symmetricType = symmetricType;
	}

	public int getAsymmetricType() {
		return asymmetricType;
	}

	public void setAsymmetricType(int asymmetricType) {
		this.asymmetricType = asymmetricType;
	}

	public String getMacSeed() {
		return macSeed;
	}

	public void setMacSeed(String macSeed) {
		this.macSeed = macSeed;
	}

	public String getEncryptedkey() {
		return encryptedkey;
	}

	public void setEncryptedkey(String encryptedkey) {
		this.encryptedkey = encryptedkey;
	}

	public String getEccpubkey() {
		return eccpubkey;
	}

	public void setEccpubkey(String eccpubkey) {
		this.eccpubkey = eccpubkey;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getUpSeed() {
		return upSeed;
	}

	public void setUpSeed(String upSeed) {
		this.upSeed = upSeed;
	}

	public String getDownSeed() {
		return downSeed;
	}

	public void setDownSeed(String downSeed) {
		this.downSeed = downSeed;
	}

	public String getRsapubkey() {
		return rsapubkey;
	}

	public void setRsapubkey(String rsapubkey) {
		this.rsapubkey = rsapubkey;
	}

	public int getDownCounter() {
		return downCounter;
	}

	public void setDownCounter(int downCounter) {
		this.downCounter = downCounter;
	}

    public String getMasterKeyName() {
        return masterKeyName;
    }

    public String finalMasterKeyName(){
        return StringUtils.isEmpty(masterKeyName)? IOTHolder.MASTER_KEY:masterKeyName;
    }

    public void setMasterKeyName(String masterKeyName) {
        this.masterKeyName = masterKeyName;
    }

    @Override
    public String toString() {
        return "SE{" +
                "downCounter=" + downCounter +
                ", hid='" + hid + '\'' +
                ", algType=" + algType +
                '}';
    }
}
