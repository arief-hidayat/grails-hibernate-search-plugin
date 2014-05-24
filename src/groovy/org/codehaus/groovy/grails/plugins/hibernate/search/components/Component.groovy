package org.codehaus.groovy.grails.plugins.hibernate.search.components

import org.apache.lucene.search.Query

abstract class Component {

	abstract Query createQuery( )

    protected parent
}
