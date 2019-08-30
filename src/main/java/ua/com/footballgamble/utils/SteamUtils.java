package ua.com.footballgamble.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class SteamUtils {
	public static StreamedContent getStreamedContent(String url) {
		/*
		 * RegEx for URL
		 * ^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]
		 */
		if (StringUtils.isBlank(url)) {
			return null;
		}
		InputStream inputStream = null;
		try {
			inputStream = new URL(url).openStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (inputStream == null) {
			return null;
		}
		StreamedContent streamedContent = new DefaultStreamedContent(inputStream, "image/png");
		return streamedContent;
	}
}
