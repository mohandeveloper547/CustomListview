package sample.customlistview.utils;

/**
 * Created by shyam.yammanuru on 21/04/2016.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class WebServiceCalls {

	public static String getData(String url) {
		StringBuffer response = null;
		// optional default is GET
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("content-type", "application/json");

			// add request header

			int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'GET' request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			response = new StringBuffer("");

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "11111";
		}

		return response.toString();
	}
	
//	public static String getPost(String url) {
//		// Create data variable for sent values to server
//		String text = "";
//		BufferedReader reader = null;
//
//		try {
//			// Defined URL where to send data
//			URL postURL = new URL(url);
//
//			// Send POST data request
//
//			URLConnection conn = postURL.openConnection();
//			conn.setDoOutput(true);
//
//			// Get the server response
//
//			reader = new BufferedReader(new InputStreamReader(
//					conn.getInputStream()));
//			StringBuilder sb = new StringBuilder();
//			String line = null;
//
//			// Read Server Response
//			while ((line = reader.readLine()) != null) {
//				// Append server response in string
//				sb.append(line + "\n");
//			}
//
//			text = sb.toString();
//		} catch (Exception ex) {
//
//		} finally {
//			try {
//				reader.close();
//			}
//
//			catch (Exception ex) {
//			}
//		}
//
//		// Show response on activity
//		return text;
//	}
//
//	public static String getDataPost(String data, String url) {
//		// Create data variable for sent values to server
//		String text = "";
//		BufferedReader reader = null;
//
//		try {
//			// Defined URL where to send data
//			URL postURL = new URL(url);
//
//			// Send POST data request
//
//			URLConnection conn = postURL.openConnection();
//			conn.setDoOutput(true);
//			OutputStreamWriter wr = new OutputStreamWriter(
//					conn.getOutputStream());
//			wr.write(data);
//			wr.flush();
//
//			// Get the server response
//
//			reader = new BufferedReader(new InputStreamReader(
//					conn.getInputStream()));
//			StringBuilder sb = new StringBuilder();
//			String line = null;
//
//			// Read Server Response
//			while ((line = reader.readLine()) != null) {
//				// Append server response in string
//				sb.append(line + "\n");
//			}
//
//			text = sb.toString();
//		} catch (Exception ex) {
//
//		} finally {
//			try {
//				reader.close();
//			}
//
//			catch (Exception ex) {
//			}
//		}
//
//		// Show response on activity
//		return text;
//	}


}
