
//import statemnets are here
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * this class StringZipInputStream reads the compressed file and enables it to
 * convert from the compressed to uncompressed file.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class StringZipInputStream {
	private int codeLength = 12; // code length allow the LZ compression for
									// storing the keys, using 2^12 we can
									// represent 4096 keys
	public static BufferedInputStream in;
	public int readingkeysCount = 0;
	String[] readingMap; // array for storing
	int buffer;
	int bufferCount = 0;
	int keysPossible = 3500; // number of keys
	//

	/**
	 * Creates a new input stream with a default buffer size. also creates an
	 * array reading map for storing the key value pair and initializing the
	 * same with ascii values of all the characters
	 * 
	 * @param out
	 */
	public StringZipInputStream(InputStream out) {
		in = new BufferedInputStream(out);
		readingMap = new String[4096];
		for (int charater = 0; charater < 256; charater++)
			readingMap[readingkeysCount++] = "" + (char) charater;
		// readingkeysCount = 256;

	}

	//
	/**
	 * this method read the compressed file and return uncompressed line.
	 * 
	 * @return
	 */
	public String read() {
		String Line = "";
		Integer[] byteCodes = compressFileReader();
		if (byteCodes == null)
			return null;
		int index = 0;
		int nextCode = byteCodes[index++];
		String val = readingMap[nextCode];

		while (index < byteCodes.length - 1) {
			Line += val;
			nextCode = byteCodes[index++];
			String s = readingMap[nextCode];
			if (s == null) {
				@SuppressWarnings("unused")
				int i = 0;
			}
			if (readingkeysCount == nextCode)
				s = val + val.charAt(0);
			if (readingkeysCount < 256 + keysPossible)
				readingMap[readingkeysCount++] = val + s.charAt(0);
			val = s;
		}
		Line += val;
		return Line + '\n';
	}

	//
	/**
	 * t his method reads bytes of 8 bits from compress file and rearrange the
	 * bits in the codLength size(12).
	 * 
	 * @return array that contains the integer value corresponding to extracted
	 *         bits of codLength size(12)
	 */
	public Integer[] compressFileReader() {
		int index = 0;
		String input = "";
		try {
			int nextChar = 0;
			while (nextChar != (int) '\n') {
				nextChar = 0;
				for (int i = 0; i < codeLength; i++) {
					nextChar <<= 1;
					if (bufferCount == 0) {
						buffer = in.read();
						bufferCount = 8;
					}
					if (buffer == -1) {// in.close();
						return null;
					}
					bufferCount--;
					if ((buffer >> (bufferCount) & 1) == 1)
						nextChar |= 1;
				}
				input = input + nextChar + ",";
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] str = input.split(",");
		Integer[] inputArray = new Integer[str.length];
		for (String st : str)
			inputArray[index++] = Integer.parseInt(st);
		return inputArray;

	}

	public String togel(String str) {
		int len = 0;
		String output = "";
		while (len < str.length()) {
			if (str.charAt(len) == '0')
				output = output + "1";
			else
				output += "0";
			len++;
		}
		return output;
	}

	/**
	 * this method is used to put zeroes equal to the length on the front or
	 * back of the bit set.
	 * 
	 * @param binary
	 *            it is a bit set
	 * @param length
	 *            number of zeros to be included before or after the bit set.
	 * @param back
	 *            to decide to put the zeroes at the back or front of the bit
	 *            set
	 * @return
	 */
	public String padding(String binary, int length, boolean back) {
		while (length > 0) {
			if (!back)
				binary = "0" + binary;
			else
				binary = binary + "0";
			length--;
		}
		return binary;

	}

	/**
	 * Closes this input stream and releases any system resources associated
	 * with the stream.
	 */
	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * public static void main(String[] args){ StringZipInputStream
	 * aStringZipInputStream = null; try { aStringZipInputStream = new
	 * StringZipInputStream( new FileInputStream("words.compress")); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } System.out.println(aStringZipInputStream.read());
	 * System.out.println(aStringZipInputStream.read());
	 * 
	 * } }
	 */
}
