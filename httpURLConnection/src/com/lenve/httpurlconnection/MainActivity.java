package com.lenve.httpurlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText passwd, name, nickname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		passwd = (EditText) this.findViewById(R.id.passwd_et);
		name = (EditText) this.findViewById(R.id.name_et);
		nickname = (EditText) this.findViewById(R.id.nickname_et);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			TransObject to = new TransObject();
			to.execute(new Person(passwd.getText().toString(), name.getText()
					.toString(), nickname.getText().toString()));
			break;

		default:
			break;
		}
	}

	class TransObject extends AsyncTask<Person, Void, String> {

		@Override
		protected String doInBackground(Person... params) {
			StringBuffer sb = new StringBuffer();
			Person p = params[0];
			BufferedReader reader = null;
			HttpURLConnection con = null;
			ObjectOutputStream oos = null;
			try {
				URL url = new URL("http://192.168.1.106/android/to");
				con = (HttpURLConnection) url.openConnection();
				// 设置允许输出，默认为false
				con.setDoOutput(true);
				con.setConnectTimeout(5 * 1000);
				con.setReadTimeout(10 * 1000);
				// 请求方式为POST请求
				con.setRequestMethod("POST");
				oos = new ObjectOutputStream(con.getOutputStream());
				// 向服务端写数据
				oos.writeObject(p);
				// 获得服务端的返回数据
				InputStreamReader read = new InputStreamReader(
						con.getInputStream());
				reader = new BufferedReader(read);
				String line = "";
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					con.disconnect();
				}
			}
			return sb.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null && "OK".equals(result)) {
				Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}
