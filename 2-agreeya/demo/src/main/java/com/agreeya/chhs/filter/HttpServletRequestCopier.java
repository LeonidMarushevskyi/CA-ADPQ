package com.agreeya.chhs.filter;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * REST specific filter to copy Request 
 * @author AgreeYa Solutions
 *
 */
public class HttpServletRequestCopier extends HttpServletRequestWrapper {

	private final byte[] buffer;

	private ServletInputStream sis;

	private BufferedReader reader;

	public byte[] getBuffer() {
		return buffer;
	}

	public HttpServletRequestCopier(HttpServletRequest request)
			throws IOException {
		super(request);
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		copyStream(request.getInputStream(), byteArrayOutputStream);
		buffer = byteArrayOutputStream.toByteArray();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		if (reader == null) {
			String characterEncoding = this.getCharacterEncoding();
			if (characterEncoding == null) {
				reader = new BufferedReader(new InputStreamReader(
						this.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(
						this.getInputStream(), characterEncoding));
			}
		}
		return reader;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (sis == null) {
			final ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			sis = new ServletInputStream() {
				@Override
				public int read() throws IOException {
					return bais.read();
				}
			};
		}
		return sis;
	}

	private void copyStream(InputStream input, OutputStream output)
			throws IOException {
		final byte[] bytes = new byte[1024];
		int length;
		while ((length = input.read(bytes)) != -1) {
			output.write(bytes, 0, length);
		}
	}
}
