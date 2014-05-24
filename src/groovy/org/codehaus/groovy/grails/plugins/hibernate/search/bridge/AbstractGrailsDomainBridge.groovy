package org.codehaus.groovy.grails.plugins.hibernate.search.bridge

import grails.util.Holders
import org.hibernate.search.bridge.TwoWayStringBridge

/** 
* This class is the commen base for nameBridge and Idbridge
* It magicly gets the required domain class (with the magick findBy* methods)
*/
abstract class AbstractGrailsDomainBridge<Domain> implements TwoWayStringBridge{
	protected Class getGrailsDomain(){
		return Holders.grailsApplication.getClassForName(
			Domain.class.getName()
		)
	}
}
