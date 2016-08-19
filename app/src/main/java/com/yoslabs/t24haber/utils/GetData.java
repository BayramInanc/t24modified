package com.yoslabs.t24haber.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetData {

	private OkHttpClient client = new OkHttpClient();

	public String run(String url) {
		Request request = new Request.Builder().url(url).build();
		Response response = null;
		InputStream inputStream = null;
		try { response = client.newCall(request).execute(); inputStream = response.body().byteStream();}
		catch (Exception e) { e.printStackTrace(); response = null; }
		if(response != null && response.isSuccessful()) return convertStreamToString(inputStream);
		else return null;
	}

	private String convertStreamToString(InputStream inputStream) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		try { while((line = bufferedReader.readLine()) != null) result += line; inputStream.close(); }
		catch (Exception e) { e.printStackTrace(); }
        return result;
	}

}
