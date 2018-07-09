package com.tencent.tusi.sgx;

public interface Crypto {

	public byte[] Sign(byte[] message, String privateKey);
	public boolean Verify(byte[] message, byte[] sig, String publicKey);
	public  boolean Verifyex(byte[] message, byte[] sig, byte[] pub, boolean isSM);
	public SEKey requestSEKey(String masterKey,String hid, byte[] pubKey);
	public byte[] encryptData(String masterKey, int type, byte[] data, byte[] downSeed, byte[] macSeed);
	public byte[] decryptData(String masterKey, int type, byte[] data, byte[] upSeed, byte[] macSeed);
	public  byte[] encrypt(int type, byte[] data, byte[] downSeed, byte[] macSeed);
	public byte[] decrypt(int type, byte[] data, byte[] upSeed, byte[] macSeed);
}
