import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Main {
	private static final String Account = "";
	private static final String Password = "";

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮��������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����POST��������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection������Ӧ��������
			out = new PrintWriter(conn.getOutputStream());
			// ������������
			out.print(param);
			// flush�������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("���� POST ���������쳣��" + e);
			// e.printStackTrace();
		}
		// ʹ��finally�����ر���������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ex.printStackTrace();
			}
		}
		return result;
	}
	public static String inputStream2String(java.io.InputStream in)
			throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL url = null;
		try {
			url = new URL("http://www.baidu.com/");
			java.io.InputStream in = url.openStream();
			String re = inputStream2String(in);
			in.close();
			if (re.indexOf("Redirect") == -1) {
				System.out.println("���ӳɹ�");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("�޷�����");
			String sr = sendPost("https://wac.shnu.edu.cn/login.html",
					"buttonClicked=4&err_flag=0Z&info_flag=0&info_msg=0&username="
							+ Account + "&password=" + Password
							+ "&redirectUrl=www.baidu.com");
			while (sr.indexOf("Password") == -1) {
				sr = sendPost("https://wac.shnu.edu.cn/login.html",
						"buttonClicked=4&err_flag=0Z&info_flag=0&info_msg=0&username="
								+ Account + "&password=" + Password
								+ "&redirectUrl=www.baidu.com");
			}
			//System.out.println(sr);
		}
	}
}
