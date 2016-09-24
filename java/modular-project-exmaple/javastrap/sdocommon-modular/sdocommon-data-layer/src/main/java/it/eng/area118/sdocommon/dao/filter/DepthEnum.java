package it.eng.area118.sdocommon.dao.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlEnum(value = String.class)
@XmlAccessorType(XmlAccessType.FIELD)
public enum DepthEnum implements Serializable {
	BARE, BASIC, NORMAL, DEEP
}
