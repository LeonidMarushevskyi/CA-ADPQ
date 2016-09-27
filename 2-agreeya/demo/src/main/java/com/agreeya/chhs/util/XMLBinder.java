package com.agreeya.chhs.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Utility class for converting Java objects to/from XML.
 * @author AgreeYa Solutions
 */
public class XMLBinder {

	/**
	 * Converts the given object into an XML string.
	 * 
	 * @param obj
	 * @return
	 * @throws JAXBException
	 */
	public static String marshal(Object obj) throws JAXBException {
		Writer outStream = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		jaxbContext.createMarshaller().marshal(obj, outStream);
		return outStream.toString();
	}

	/**
	 * Converts the input XML string into the corresponding Java object.
	 * The type of the returned object is inferred from the input object instance.
	 * 
	 * @param obj
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	public static Object unmarshal(Object obj, String xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(obj.getClass());
		return jc.createUnmarshaller().unmarshal(new StringReader(xml));
	}

	/**
	 * Converts the input XML string into the corresponding Java object.
	 * 
	 * @param clazz
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	public static Object unmarshal(Class clazz, String xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		return jc.createUnmarshaller().unmarshal(new StringReader(xml));
	}

}
