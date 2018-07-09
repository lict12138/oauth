package com.tencent.tusi.sgx;

public class Hex {
	public static byte[] toByteArray(String hexString) {
		try {
			hexString = hexString.replaceAll(" ", "");
			if (hexString.length() % 2 == 0) {
				int len = hexString.length() / 2;
				byte[] byteArray = new byte[len];
				int index = 0;
				for (int i = 0; i < len; i++) {
					try {
						byteArray[i] = (byte) Integer.parseInt(hexString.substring(index, index + 2), 16);
						index += 2;
					} catch (NumberFormatException e) {
						throw new RuntimeException(e);
					}
				}
				return byteArray;
			}
			throw new RuntimeException("Error Hex length.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String toHexString(byte[] buffer) {
		return toHexString(buffer, 0);
	}

	public static String toHexString(byte[] buffer, int offset) {
		if (null == buffer) {
			return "";
		}
		// ���յ�ƴװ�ַ���
		StringBuilder res = new StringBuilder();
		for (int i = offset; i < buffer.length; i++) {
			res.append(byteToHex(buffer[i]));
		}
		return res.toString();
	}

	public static String byteToHex(byte byteValue) {
		// ת��16�����ַ���
		String hs = "";
		String tmp = "";
		// ����ת��ʮ�����Ʊ�ʾ
		tmp = (Integer.toHexString(byteValue & 0XFF));
		if (tmp.length() == 1) {
			hs = hs + "0" + tmp;
		} else {
			hs = hs + tmp;
		}
		tmp = null;
		return hs.toUpperCase(); // ת�ɴ�д
	}

	public static byte[] reverse(byte[] array) {
		int arraysize = array.length;
		byte[] reverse = new byte[arraysize];
		int dec = arraysize - 1;
		for (int i = 0; i < arraysize; i++) {
			reverse[i] = array[dec--];
		}
		return reverse;
	}

	public static String strReverse(String str) {
		int last = str.length() / 2 - 1;
		byte bin[] = toByteArray(str);
		for (int i = 0; i < str.length() / 4; i++) {
			byte b = bin[i];
			bin[i] = bin[last - i];
			bin[last - i] = b;
		}
		return toHexString(bin);
	}

}
